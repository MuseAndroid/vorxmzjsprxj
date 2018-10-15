package kr.co.genexon.factconnector.raonCert;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.raonsecure.ksw.RSKSWCertManager;
import com.raonsecure.ksw.RSKSWCertificate;
import com.raonsecure.ksw.RSKSWException;
import com.raonsecure.ksw.RSKSWICRProtocol;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Hashtable;

import kr.co.genexon.factconnector.R;
import kr.co.genexon.factconnector.implementation.ExtendWNInterface;
import m.client.android.library.core.view.AbstractActivity;

public class ICRSImportCert {

    RSKSWCertManager manager;
    RSKSWICRProtocol icrp;
    RSKSWCertificate userCert;
    String codeR1 = "";
    String messageR1 = "";
    String codeR2 = "";
    String messageR2 = "";
    String userRandomNumber = "";
    ProgressDialog dialog;

    byte[] userCertByte;
    byte[] userKeyByte;
    byte[] userKmCertByte;
    byte[] userKmKeyByte;

    boolean isPaused = false;
    boolean isSave = false;

    public AbstractActivity parentActivity;
    private ExtendWNInterface extendInterface;
    private Context importCertContext;

    public ICRSImportCert (AbstractActivity callerObject, ExtendWNInterface extendWNInterface) {
        parentActivity = callerObject;
        extendInterface = extendWNInterface;
        importCertContext = callerObject.getApplicationContext();
        manager = RSKSWCertManager.getInstance(importCertContext);
    }

    public void setImportCertPort(String url, String port) {
        int ret;
        if ((ret = RSKSWICRProtocol.getLibraryState(importCertContext)) < 0) {
            Toast.makeText(importCertContext, "License Not Allowed : "+ ret, Toast.LENGTH_LONG)
                    .show();
        }

        String icrsIp = url;
        int icrsPort = Integer.parseInt(port);
        String stockCode = "00000";
        Log.d("raon", "ICRS : " + icrsIp + " : " + icrsPort);
        manager = RSKSWCertManager.getInstance(importCertContext);
        Log.d("raon", "man save to "+manager.getCertSavingMode());

        //구 프로토콜
        RSKSWICRProtocol.securityLevel = RSKSWICRProtocol.RSKSWConstCRSecLevel_SHA1;
//			RSKSWICRProtocol.securityLevel = RSKSWICRProtocol.RSKSWConstCRSecLevel_SHA256;
        if (icrsIp != null)
            try {
                //구 프로토콜
                icrp = new RSKSWICRProtocol(importCertContext, icrsIp, icrsPort);
                //신 프로토콜
//				icrp = new RSKSWICRProtocol(KSW_Activity_ICRSImportCert.this, icrsIp, icrsPort, stockCode.getBytes());
            } catch (RSKSWException e) {
                codeR1 = "객체생성실패";
                messageR1 = e.errorCode + " - " + e.getMessage();
            }

        import1Start();
    }

    // 인증 번호 생성 요청
    public void import1Start() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Hashtable<?, ?> messageHashtable = new Hashtable<String, Object>();
                // 인증 번호 생성 요청 실제 API
                messageHashtable = icrp.import1();
                codeR1 = (String) messageHashtable.get("CODE");
                messageR1 = (String) messageHashtable.get("MESSAGE");
                userRandomNumber = icrp.generatedNumber();

                if (codeR1.equals("SC200")) {
                    // 인증번호 생성 성공z
                    String num1 = userRandomNumber.substring(0, 4);
                    String num2 = userRandomNumber.substring(4, 8);
                    String num3 = userRandomNumber.substring(8, 12);
                    Log.d("importCert", "인증번호 = " + num1 + " - " + num2 + " - " + num3);
                    sendCallback("0000", num1, num2, num3);
                    handler.sendMessage(handler.obtainMessage(1));
                } else {
                    // 인증번호 생성 실패
                    if (codeR1.equals("PT118")) {
                        handler.sendMessage(handler.obtainMessage(6));
                    } else if (codeR1.equals("NT300")) {
                        codeR1 = "네트워크 오류";
                        messageR1 = "네트워크 접속이 실패 하였습니다.";
                        handler.sendMessage(handler.obtainMessage(3));
                    } else {
                        handler.sendMessage(handler.obtainMessage(3));
                    }
                }
            }
        }).start();
    }

    // 인증서 가져오기 (공용 저장)
    public void import2Start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                manager = RSKSWCertManager.getInstance(importCertContext);
                Hashtable<?, ?> messageHashtable = new Hashtable<String, Object>();
                // 인증서 가져오기 실제 API
                try {
                    messageHashtable = icrp.import2();
                    codeR2 = (String) messageHashtable.get("CODE");
                    messageR2 = (String) messageHashtable.get("MESSAGE");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    codeR2 = "PT115";
//                    sendImportCallback("9000", "");
                }

                manager.setCertSavingMode(RSKSWCertManager.CERT_IN_SDCARD);
                if (codeR2.equals("SC201")) {
                    userCertByte = icrp.getCert();
                    userKeyByte = icrp.getKey();
                    try {
                        // 모바일 기기에 인증서 저장
                        isSave = manager.saveCert(userCertByte, userKeyByte);
                        Log.e("raon",userCertByte.length+ " len " + userKeyByte.length);
                        handler.sendMessage(handler.obtainMessage(2));
                    } catch (IOException e) {
                        handler.sendMessage(handler.obtainMessage(5));
                    }

                    if (isSave) {
                        handler.sendMessage(handler.obtainMessage(1));
                    } else {
                        handler.sendMessage(handler.obtainMessage(2));
                    }
                }else if (codeR2.equals("SC203")) {
                    userCertByte = icrp.getCert();
                    userKeyByte = icrp.getKey();
                    userKmCertByte = icrp.getKmCert();
                    userKmKeyByte = icrp.getKmKey();
                    try {
                        // 모바일 기기에 인증서 저장
                        isSave = manager.saveCert(userCertByte, userKeyByte,userKmCertByte,userKmKeyByte);
                        Log.e("raon",userCertByte.length+ " len " + userKeyByte.length);
                        handler.sendMessage(handler.obtainMessage(2));
                    } catch (IOException e) {
                        handler.sendMessage(handler.obtainMessage(5));
                    }

                    if (isSave) {
                        handler.sendMessage(handler.obtainMessage(1));
                    } else {
                        handler.sendMessage(handler.obtainMessage(2));
                    }
                }

                else if (codeR2.equals("PT115")) {
                    handler.sendMessage(handler.obtainMessage(8));
                } else if (codeR1.equals("NT300")) {
                    codeR2 = "네트워크 오류";
                    messageR2 = "네트워크 접속이 실패 하였습니다.";
                    handler.sendMessage(handler.obtainMessage(4));
                } else {
                    handler.sendMessage(handler.obtainMessage(4));
                }
            }
        }).start();
    }

    /*private AlertDialog importCertDialog(String title, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(importCertContext).setIcon(R.mipmap.ic_factcon)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                });

        return dialog.create();
    }*/

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String num1 = userRandomNumber.substring(0, 4);
                String num2 = userRandomNumber.substring(4, 8);
                String num3 = userRandomNumber.substring(8, 12);
                Toast.makeText(importCertContext,
                        "승인번호 생성이 성공하였습니다.",
                        Toast.LENGTH_SHORT).show();
//                btn_Import.setClickable(true);
//                btn_Import_App.setClickable(true);
            } else if (msg.what == 2) {
                Toast.makeText(importCertContext,
                        "인증서 가져오기가 성공하였습니다.",
                        Toast.LENGTH_LONG).show();
                sendImportCallback("0000", "");
//                importCertDialog("인증서 가져오기 성공", "인증서 가져오기가 성공하였습니다.").show();
            } else if (msg.what == 3) {
//                importCertDialog(codeR1, messageR1).show();
                sendImportCallback("9000", messageR2);
            } else if (msg.what == 4) {
//                importCertDialog(codeR2, messageR2).show();
                sendImportCallback("9000", messageR2);
            } else if (msg.what == 5) {
//                importCertDialog("인증서 저장 알림", "인증서 저장에 문제가 생겼습니다.").show();
                sendImportCallback("9000", "인증서 저장에 문제가 생겼습니다.");
            } else if (msg.what == 6) {
                import1Start();
            } else if (msg.what == 8) {
//                dialog.dismiss();
//                importCertDialog(codeR2,
//                        "인증서 내보내기를 하는 기기의 인증서 내보내기 과정을 먼저 수행하세요.").show();
                Toast.makeText(importCertContext, "인증서 내보내기를 하는 기기의 인증서 내보내기 과정을 먼저 수행하세요.", Toast.LENGTH_SHORT).show();
                sendImportCallback("9000", "인증서 내보내기를 하는 기기의 인증서 내보내기 과정을 먼저 수행하세요.");
            } else if (msg.what == 100) {
                import2Start();
            }  else if (msg.what == 101) {
//                import2Start_app();
            }
        }
    };

    private void sendCallback(String code, String n1, String n2, String n3) {
        if (code.equals("0000")) {
            try {
                String number = n1 + n2 + n3;
                JSONObject callJson = new JSONObject();
                callJson.put("resultCode", code);
                callJson.put("resultData", number);
                extendInterface.exWNSendData(callJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    private void sendImportCallback(String code, String data) {
        if (code.equals("0000")) {
            try {
                JSONObject callJson = new JSONObject();
                callJson.put("resultCode", code);
                callJson.put("resultData", "");
                extendInterface.exWNSendData(callJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                JSONObject callJson = new JSONObject();
                callJson.put("resultCode", code);
                callJson.put("resultData", data);
                extendInterface.exWNSendData(callJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void getCertFile() {
        try {
            handler.sendMessageDelayed(handler.obtainMessage(100), 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
