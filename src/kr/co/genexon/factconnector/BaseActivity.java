package kr.co.genexon.factconnector;

import kr.co.genexon.factconnector.implementation.ExtendWNInterface;
import kr.co.genexon.factconnector.raonCert.CertInputPwd;
import m.client.android.library.core.utils.Logger;
import m.client.android.library.core.view.AbstractActivity;
import m.client.android.library.core.view.MainActivity;
import m.client.push.library.PushManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.gson.JsonIOException;
import com.raonsecure.crypto.KSHex;
import com.raonsecure.ksw.RSKSWCertManager;
import com.raonsecure.ksw.RSKSWCertificate;
import com.raonsecure.ksw.RSKSWChannelEncrypt;
import com.raonsecure.ksw.RSKSWException;
import com.raonsecure.ksw.RSKSWFileManager;
import com.raonsecure.ksw.RSKSWICRProtocol;
import com.raonsecure.ksw.RSKSWLog;
import com.raonsecure.ksw.RSKSWQRRelay;
import com.raonsecure.ksw.RSKSWUtils;
import com.softsecurity.transkey.Global;
import com.softsecurity.transkey.TransKeyActivity;
import com.softsecurity.transkey.TransKeyCipher;
import com.softsecurity.transkey.TranskeyResultData;

import java.util.ArrayList;

/**
 * BaseActivity Class
 * 
 * @author 김태욱(<a mailto="tukim@uracle.co.kr">tukim@uracle.co.kr</a>)
 * @version v 1.0.0
 * @since Android 2.1 <br>
 *        <DT><B>Date: </B>
 *        <DD>2013.08.01</DD>
 *        <DT><B>Company: </B>
 *        <DD>Uracle Co., Ltd.</DD>
 * 
 * 모피어스 내에서 제공되는 모든 Web 페이지의 기본이 되는 Activity
 * html 화면은 모두 BaseActivity 상에서 출력된다.
 * 제어를 원하는 이벤트들은 overriding 하여 구현하며, 각각 페이지 별 이벤트는 화면 단위로 분기하여 처리한다.
 * 플랫폼 내부에서 사용하는 클래스로 해당 클래스의 이름은 변경할 수 없다.
 * 
 * Copyright (c) 2001-2011 Uracle Co., Ltd. 
 * 166 Samseong-dong, Gangnam-gu, Seoul, 135-090, Korea All Rights Reserved.
 */

/**
 * keyStore 정보
 * alias PW - fConnector2017!
 * key PW - fConnector2018!
 * alias name - unknown
 * */

public class BaseActivity extends MainActivity {

	private static final int LOCKSCREEN_COCE = 2018;
	public static String LockScreenCallbackString = "";
	public Context baseContext;
	String pwData = "";
	String pwData1 = "";

	/**
     *  URL 정의
     *  */
	// test
    String URL = "http://m-abl.factfinder.me";
    // real
//    String URL = "https://m-abl.factfinder.me";

    private static final int REQUEST_READ_PHONE = 1;

	private static String[] ALL_USED_PERMISSIONS = {
            android.Manifest.permission.READ_PHONE_STATE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
    };

    String[] plainTextBuf = {"", ""};
    String[] cipherTextBuf = {"", ""};
    String[] dummyTextBuf = {"", ""};

    int curViewIndex = 0;
    int onClickIndex = 0;

    RSKSWCertManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getWindow().getDecorView();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            WebView.setWebContentsDebuggingEnabled(true);

			PushManager.getInstance().initPushServer(this.getApplicationContext());

			baseContext = this;

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (view != null) {
                getWindow().setStatusBarColor(Color.parseColor("#465de1"));
            }
        } else if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(Color.parseColor("#465de1"));
        }


        checkPermisson();
    }

    /**
	 * Webview가 시작 될 때 호출되는 함수
	 */
	@Override
	public void onPageStarted (WebView view, String url, Bitmap favicon) {
		super.onPageStarted(view, url, favicon);
	}
	
	/**
	 * Webview내 컨텐츠가 로드되고 난 후 호출되는 함수
	 */
	@Override
	public void onPageFinished(WebView view, String url)  {
		super.onPageFinished(view, url);
				
//		try {
//			//InfoEvent
//			JSONObject _objInfoEvent = new JSONObject(getWebView().getWNInterfaceManager().getInterfaceJS().wn2InfoEvent());
//			Logger.print(_objInfoEvent.toString(4));
//			
//			//InfoApp
//			JSONObject _objInfoApp = new JSONObject(getWebView().getWNInterfaceManager().getInterfaceJS().wn2InfoApp());
//			Logger.print(_objInfoApp.toString(4));
//			
//			//InfoStack
//			JSONArray _arrayInfoStack = new JSONArray(getWebView().getWNInterfaceManager().getInterfaceJS().wn2InfoStack());
//			Logger.print(_arrayInfoStack.toString(4));
//			
//			//InfoDevice
//			JSONObject _objInfoDevice = new JSONObject(getWebView().getWNInterfaceManager().getInterfaceJS().wn2InfoDevice());
//			Logger.print(_objInfoDevice.toString(4));
//			
//			JSONObject _objTmp = new JSONObject();
//			_objTmp.put("path", "app://res");
//		
//			//FileInfo
//			JSONObject _objFileInfo = new JSONObject(getWebView().getWNInterfaceManager().getInterfaceJS().wn2FileInfo(_objTmp.toString()));
//			Logger.print(_objFileInfo.toString(4));
//						
//			_objTmp.put("path", "ext://getExternalFilesDir.txt");
//			
//			_objFileInfo = new JSONObject(getWebView().getWNInterfaceManager().getInterfaceJS().wn2FileInfo(_objTmp.toString()));
//			Logger.print(_objFileInfo.toString(4));
//			
//			_objTmp.put("path", "doc://sample1");
//			_objTmp.put("type", "DIR");
//			 
//			_objFileInfo = new JSONObject(getWebView().getWNInterfaceManager().getInterfaceJS().wn2FileInfo(_objTmp.toString()));
//			Logger.print(_objFileInfo.toString(4));
//						
////			getWebView().getWNInterfaceManager().getInterfaceJS().wnCopyResourceFiles("res", "res", "100000");
//
//			_objTmp.put("encode", "UTF-8");
//			_objTmp.put("path", "doc://getFilesDir.txt");
//			_objTmp.put("indicator", "테스트 메세지 TEST !@#$%123");
//			
//			getWebView().getWNInterfaceManager().getInterfaceJS().wn2FileRead(_objTmp.toString(), "SAMPLE CALLBACK");
//			
//			_objTmp.put("encode", "UTF-8");
//			_objTmp.put("path", "app://res/README");
//			_objTmp.put("indicator", "테스트 메세지 TEST !@#$%123");
//			
//			getWebView().getWNInterfaceManager().getInterfaceJS().wn2FileRead(_objTmp.toString(), "SAMPLE CALLBACK");
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
				
//		try {
//			JSONObject _objTmp = new JSONObject();
//			_objTmp.put("indicator", "복사중입니다.");
//			_objTmp.put("source", "app://");
//			_objTmp.put("destination", "ext://res");
//			_objTmp.put("overwrite", "YES");
//						
//			JSONArray _ja = new JSONArray();
//			_ja.put(_objTmp.toString());
//			_ja.put("onProgress");
//			_ja.put("onResult");
//			
//			long _elapsedTime = System.currentTimeMillis();
//			
//			getWebView().getWNInterfaceManager().getInterfaceJS().wnCommonInterface("wn2FileMove", _ja.toString());
//			Logger.print("_elapsedTime : " + (System.currentTimeMillis() - _elapsedTime));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("BaseActivity", "눌리니???");
        Log.d("BaseActivity", "현재 위치 : " + this.getWebView().getUrl());
        if (this.getWebView().getUrl().equals(URL+"/main/m_main.go") || this.getWebView().getUrl().equals(URL+"/i_analysis.go")) {
            Log.d("BaseActivity", "종료한다!!!");
            exitPopup();
        } else {
            this.getWebView().goBack();
        }
    }

    @Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == ExtendWNInterface.LOCKSCREEN_COCE) {
			if (resultCode == RESULT_OK) {
				Log.d("BaseActivity", "activity result 값 = " + data.getStringExtra("pwResult"));
				pwData = data.getStringExtra("pwResult");
                Intent reInputPasswd = new Intent(this, LockPasswordActivity.class);
                reInputPasswd.putExtra("title", "잠금 비밀번호 재확인");
                reInputPasswd.putExtra("desc", "확인을 위해 한번 더 입력해 주세요");
                reInputPasswd.putExtra("option", "0");
                startActivityForResult(reInputPasswd, ExtendWNInterface.LOCKSCREEN_COCE1);
				overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			}
		} else if (requestCode == ExtendWNInterface.LOCKSCREEN_COCE1) {
			if (resultCode == RESULT_OK) {
                try {
                    Log.d("BaseActivity", "activity result 값 = " + data.getStringExtra("pwResult"));
                    pwData1 = data.getStringExtra("pwResult");
                    JSONObject pwJson = new JSONObject();
                    pwJson.put("resultCode", "0000");
                    pwJson.put("resultData", pwData1);
                    if (pwData.equals(pwData1)) {
                        runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", pwJson, this.getWebView()));
                    } else {
                        Toast.makeText(this, "비밀번호를 다시 확인해 주시기 바랍니다", Toast.LENGTH_SHORT).show();
                        Intent reInputPasswd = new Intent(this, LockPasswordActivity.class);
                        reInputPasswd.putExtra("title", "잠금 비밀번호 재확인");
                        reInputPasswd.putExtra("desc", "확인을 위해 한번 더 입력해 주세요");
                        reInputPasswd.putExtra("option", "0");
                        startActivityForResult(reInputPasswd, ExtendWNInterface.LOCKSCREEN_COCE1);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
		} else if (requestCode == ExtendWNInterface.LOCKSCREEN_COCE2) {
            if (resultCode == RESULT_OK) {
                try {
                    Log.d("BaseActivity", "activity result 값 = " + data.getStringExtra("pwResult"));
                    pwData1 = data.getStringExtra("pwResult");
//                    Toast.makeText(this, "비밀번호가 설정되었습니다", Toast.LENGTH_SHORT).show();
                    JSONObject pwJson1 = new JSONObject();
                    pwJson1.put("resultCode", "0000");
                    pwJson1.put("resultData", pwData1);
                    runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", pwJson1, this.getWebView()));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            } else if (resultCode == ExtendWNInterface.LOCKSCREEN_DELETE) {
                try {
                    Log.d("BaseActivity", "activity result 값 = " + data.getStringExtra("pwResult"));
                    pwData1 = data.getStringExtra("pwResult");
                    JSONObject dPwJson = new JSONObject();
                    dPwJson.put("resultCode", "1000");
                    dPwJson.put("resultData", "");
                    runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", dPwJson, this.getWebView()));
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == ExtendWNInterface.SECURE_KEYPAD_1) {
		    if (resultCode == RESULT_OK) {
		        Log.d("SecureKeypad", "문자 넘어온 값 = " + data);
		        keypadResult(requestCode, resultCode, data);
            } else if (resultCode == RESULT_CANCELED) {
                try {
                    JSONObject cancelJSON = new JSONObject();
                    cancelJSON.put("resultCode", "1000");
                    cancelJSON.put("resultData", "");
                    runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", cancelJSON, this.getWebView()));

                } catch (Exception e) {

                }
            }
        } else if (requestCode == ExtendWNInterface.SECURE_KEYPAD_2) {
		    if (resultCode == RESULT_OK) {
                Log.d("SecureKeypad", "숫자 넘어온 값 = " + data);
                keypadResult(requestCode, resultCode, data);
            } else if (resultCode == RESULT_CANCELED) {
                try {
                    JSONObject cancelJSON = new JSONObject();
                    cancelJSON.put("resultCode", "1000");
                    cancelJSON.put("resultData", "");
                    runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", cancelJSON, this.getWebView()));

                } catch (Exception e) {

                }
            }
        } else if (requestCode == ExtendWNInterface.INPUT_CERT_PWD) {
		    if (resultCode == RESULT_OK) {
                Log.d("SecureKeypad", "숫자 넘어온 값 = " + data);
                checkCertPwd(requestCode, resultCode, data);
            } else if (resultCode == RESULT_CANCELED) {
                try {
                    JSONObject cancelJSON = new JSONObject();
                    cancelJSON.put("resultCode", "1000");
                    cancelJSON.put("resultData", "");
                    runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", cancelJSON, this.getWebView()));

                } catch (Exception e) {

                }
            }
        }
	}

	private void keypadResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_CANCELED) {
            if (data != null) {
                String errMsg = data.getStringExtra(TransKeyActivity.mTK_PARAM_ERROR_MESSAGE);
                if (errMsg == null) errMsg = "";
                if (Global.debug) Log.e("Error", errMsg);
                try {
                    JSONObject cancelJSON = new JSONObject();
                    JSONObject cancelData = new JSONObject();
                    cancelJSON.put("resultCode", "1000");
                    cancelJSON.put("resultData", "");
                    runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", cancelJSON, this.getWebView()));

                } catch (Exception e) {

                }
            }
        } else {
            ArrayList<TranskeyResultData> resultDataList = data.getParcelableArrayListExtra(TransKeyActivity.mTK_PARAM_RESULTS_ARRAY);
            if (resultDataList != null) {
                if (resultDataList != null && resultDataList.size() > 0) {
                    TranskeyResultData resultData = resultDataList.get(0);
                    String cipherData = resultData.cipherData;
                    String cipherDataEx = resultData.cipherDataEx;
                    String cipherDataExWithPadding = resultData.cipherDataExWithPadding;
                    String dummyData = resultData.dummyData;
                    byte[] secureKey = resultData.secureKey;
                    int iRealDataLength = resultData.dataLength;

                    if (iRealDataLength == 0)
                        return;

                    StringBuffer plainData = null;

                    try {
                        TransKeyCipher tkc = new TransKeyCipher("SEED");
                        tkc.setSecureKey(secureKey);

                        byte pbPlainData[] = new byte[iRealDataLength];

                        if (tkc.getDecryptCipherDataExWithPadding(cipherData, pbPlainData)) {
                            plainData = new StringBuffer(new String(pbPlainData));

                            for (int i = 0; i < pbPlainData.length; i++)
                                pbPlainData[i] = 0x01;
                        } else {
                            //복호화 실패
                            plainData = new StringBuffer("plainData decode x...");
                        }
                    } catch (Exception e) {
                        if (Global.debug) Log.d("STACKTRACE", e.getStackTrace().toString());
                    }

                    plainData.delete(0, plainData.length());
                }
                if (resultDataList != null && resultDataList.size() > 1) {
                    TranskeyResultData resultData = resultDataList.get(1);
                    String cipherData = resultData.cipherData;
                    String dummyData = resultData.dummyData;
                    byte[] secureKey = resultData.secureKey;
                    int iRealDataLength = resultData.dataLength;

                    if (iRealDataLength == 0)
                        return;
                    StringBuffer plainData = null;

                    try {
                        TransKeyCipher tkc = new TransKeyCipher("SEED");
                        tkc.setSecureKey(secureKey);

                        byte pbPlainData[] = new byte[iRealDataLength];
                        if (tkc.getDecryptCipherDataExWithPadding(cipherData, pbPlainData)) {
                            plainData = new StringBuffer(new String(pbPlainData));

                            for (int i = 0; i < pbPlainData.length; i++)
                                pbPlainData[i] = 0x01;
                        } else {
                            //복호화 실패
                            plainData = new StringBuffer("plainData decode fail...");
                        }
                    } catch (Exception e) {
                        if (Global.debug) Log.d("STACKTRACE", e.getStackTrace().toString());
                    }

                    plainData.delete(0, plainData.length());
                }
            } else {
                String cipherData = data.getStringExtra(TransKeyActivity.mTK_PARAM_CIPHER_DATA_EX);
                String secureData = data.getStringExtra(TransKeyActivity.mTK_PARAM_SECURE_DATA);
                String dummyData = data.getStringExtra(TransKeyActivity.mTK_PARAM_DUMMY_DATA);
                try {
                    JSONObject resultJsonData = new JSONObject();
                    JSONObject jsonResultData = new JSONObject();
                    resultJsonData.put("resultCode", "0000");
                    jsonResultData.put("security_data", secureData);
                    jsonResultData.put("dummy_data", dummyData);
                    resultJsonData.put("resultData", jsonResultData);
                    runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", resultJsonData, this.getWebView()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void checkCertPwd(int requestCode, int resultCode, Intent data) {
        manager = CertInputPwd.manager;
        RSKSWCertificate userCert = CertInputPwd.userCert;
        byte []_pwd = null;
        byte[] random = CertInputPwd.random;
        if (resultCode == Activity.RESULT_CANCELED) {
            if (data != null) {
                String errMsg = data.getStringExtra(TransKeyActivity.mTK_PARAM_ERROR_MESSAGE);
                if (errMsg == null) errMsg = "";
                if (Global.debug) Log.e("Error", errMsg);
            }
        } else {
            ArrayList<TranskeyResultData> resultDataList = data.getParcelableArrayListExtra(TransKeyActivity.mTK_PARAM_RESULTS_ARRAY);
            if (resultDataList != null) {
                if (resultDataList != null && resultDataList.size() > 0) {
                    TranskeyResultData resultData = resultDataList.get(0);
                    String cipherData = resultData.cipherData;
                    String cipherDataEx = resultData.cipherDataEx;
                    String cipherDataExWithPadding = resultData.cipherDataExWithPadding;
                    String dummyData = resultData.dummyData;
                    byte[] secureKey = resultData.secureKey;
                    int iRealDataLength = resultData.dataLength;

                    if (iRealDataLength == 0)
                        return;

                    StringBuffer plainData = null;

                    try {
                        TransKeyCipher tkc = new TransKeyCipher("SEED");
                        tkc.setSecureKey(secureKey);

                        byte pbPlainData[] = new byte[iRealDataLength];
                        if (tkc.getDecryptCipherDataExWithPadding(cipherData, pbPlainData)) {
                            plainData = new StringBuffer(new String(pbPlainData));

                            for (int i = 0; i < pbPlainData.length; i++)
                                pbPlainData[i] = 0x01;
                        } else {
                            //복호화 실패
                            plainData = new StringBuffer("plainData decode x...");
                        }
                    } catch (Exception e) {
                        if (Global.debug) Log.d("STACKTRACE", e.getStackTrace().toString());
                    }

                    plainData.delete(0, plainData.length());
                }
                if (resultDataList != null && resultDataList.size() > 1) {
                    TranskeyResultData resultData = resultDataList.get(1);
                    String cipherData = resultData.cipherData;
                    String dummyData = resultData.dummyData;
                    byte[] secureKey = resultData.secureKey;
                    int iRealDataLength = resultData.dataLength;

                    if (iRealDataLength == 0)
                        return;
                    StringBuffer plainData = null;

                    try {
                        TransKeyCipher tkc = new TransKeyCipher("SEED");
                        tkc.setSecureKey(secureKey);

                        byte pbPlainData[] = new byte[iRealDataLength];
                        if (tkc.getDecryptCipherDataExWithPadding(cipherData, pbPlainData)) {
                            plainData = new StringBuffer(new String(pbPlainData));

                            for (int i = 0; i < pbPlainData.length; i++)
                                pbPlainData[i] = 0x01;
                        } else {
                            //복호화 실패
                            plainData = new StringBuffer("plainData decode fail...");
                        }
                    } catch (Exception e) {
                        if (Global.debug) Log.d("STACKTRACE", e.getStackTrace().toString());
                    }

                    plainData.delete(0, plainData.length());
                }
            } else {
                String qwertyCipherString = null;
                String cipherData = data.getStringExtra(TransKeyActivity.mTK_PARAM_CIPHER_DATA_EX);
                byte[] secureKey = data.getByteArrayExtra(TransKeyActivity.mTK_PARAM_SECURE_KEY);
                String plainSecureKey = "";
                try {
                    TransKeyCipher tkc = new TransKeyCipher("SEED");
                    tkc.setSecureKey(secureKey);

                    qwertyCipherString = tkc.getPBKDF2DataEncryptCipherDataEx(cipherData);
                    try {
                        byte passwd[] = RSKSWUtils.getPasswdFromTranskeyEncData(random, KSHex.decode(qwertyCipherString));
                        plainSecureKey = new String(passwd);
                        Log.d("raon", "복호화 passwd : " + new String(passwd));
                    } catch (Exception e) {

                    }
                    SharedPreferences cipherStrPref = getSharedPreferences("cipherData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = cipherStrPref.edit();
                    editor.putString("CipherString", qwertyCipherString);
                    editor.putString("plainPwd", plainSecureKey);
                    editor.apply();
//					boolean backLogFlag = RSKSWLog.FLAG;
                    //RSKSWLog.FLAG = true;
                    RSKSWLog.printHex("random", random);
                    Log.d("raon", "cipherData : " + cipherData);
                    RSKSWLog.printHex("secureKey", secureKey);
                    Log.d("raon", "qwertyCipherString : " + qwertyCipherString);
                    //RSKSWLog.FLAG = backLogFlag;

                }catch(Exception e) {
                    e.printStackTrace();
                }

                try {
                    _pwd = RSKSWUtils.decode(qwertyCipherString, RSKSWUtils.RSKSW_ENCODING_HEXLOW);
                } catch (RSKSWException e) {
                    e.printStackTrace();
                    //hex decode failed.
                }
                boolean isCorrectPassword = manager.checkPassword(userCert, _pwd);
                Log.d("TAG", "값 보여줘 - " + manager.checkPassword(userCert, _pwd));

                if (!isCorrectPassword) {
                    String resultMsg = "현재 인증서 비밀번호가 일치하지 않습니다.";
                    Log.d("checkPWD", resultMsg + ", " + isCorrectPassword);
                    try {
                        JSONObject resultJsonData = new JSONObject();
                        resultJsonData.put("resultCode", "9000");
                        resultJsonData.put("resultData", "");
                        runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", resultJsonData, this.getWebView()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    String resultMsg = "현재 인증서 비밀번호와 일치합니다.";
                    Log.d("checkPWD", resultMsg + ", " + isCorrectPassword);
                    try {
                        JSONObject resultJsonData = new JSONObject();
                        resultJsonData.put("resultCode", "0000");
                        resultJsonData.put("resultData", "");
                        runOnUiThread(new SendResultRunnable(ExtendWNInterface.resultCallback, "", resultJsonData, this.getWebView()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void checkPermisson() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                ActivityCompat.requestPermissions(this, ALL_USED_PERMISSIONS, REQUEST_READ_PHONE);
            } else {
                ActivityCompat.requestPermissions(this, ALL_USED_PERMISSIONS, REQUEST_READ_PHONE);
            }
        }
    }

    private void exitPopup() {
        new AlertDialog.Builder(this)
                .setTitle("종료")
                .setMessage("종료하시겠습니까?")
                .setPositiveButton("종료", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("취소", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}
