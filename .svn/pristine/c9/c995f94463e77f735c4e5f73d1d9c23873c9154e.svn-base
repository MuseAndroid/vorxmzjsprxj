package kr.co.genexon.factconnector.raonCert;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.raonsecure.ksw.RSKSWCertManager;
import com.raonsecure.ksw.RSKSWCertificate;
import com.raonsecure.ksw.RSKSWException;
import com.raonsecure.ksw.RSKSWLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import kr.co.genexon.factconnector.implementation.ExtendWNInterface;
import m.client.android.library.core.view.AbstractActivity;

public class CertListManager {

    public AbstractActivity parentActivity;
    private ExtendWNInterface extendInterface;
    private Context certlistContext;
    ArrayList<CertificationItem> userCertItem = null;
    private static ArrayList<?> userCerts = null;

    public static ArrayList<?> getUserCerts() {
        return userCerts;
    }

    public CertListManager(AbstractActivity callerObject, ExtendWNInterface exInterface) {
        parentActivity = callerObject;
        extendInterface = exInterface;
        certlistContext = callerObject.getApplicationContext();
    }

    public void loadCertList() {
        try {

            if (userCerts != null) {
                userCerts.clear();
            }

            RSKSWCertManager manager;
            if ((manager = RSKSWCertManager.getInstance(certlistContext)) == null) {
                Toast.makeText(certlistContext, "License Not Allowed : " + RSKSWCertManager.getLibraryState(certlistContext), Toast.LENGTH_LONG)
                        .show();
                return;
            }

            RSKSWLog.e("raontest", "load cert mode:" + manager.getCertLoadingMode());

            manager.setCertLoadingMode(RSKSWCertManager.CERT_IN_SDCARD
                    | RSKSWCertManager.CERT_FILTER_NPKI
                    | RSKSWCertManager.CERT_FILTER_GPKI
            );

            userCerts = manager.getArrCert();
        } catch (RSKSWException e) {
            e.printStackTrace();
            certListDialog("인증서 목록 초기화 실패", "인증서 목록 생성에 실패하였습니다.").show();
        }

        if (userCerts == null || userCerts.size() == 0) {
            Toast.makeText(certlistContext,
                    "인증서 리스트에 인증서가 없습니다.",
                    Toast.LENGTH_LONG).show();
        } else {
            userCertItem = new ArrayList<>();
            for (int i = 0; i < userCerts.size(); i++) {
                try {
                    userCertItem.add(new CertificationItem((RSKSWCertificate) userCerts.get(i)));
                } catch (UnsupportedEncodingException e) {
                    certListDialog("인증서 목록 초기화 실패", "인증서 목록 생성에 실패하였습니다.").show();
                }
            }

            Log.d("RAON_CERT", "인증서 목록 = " + userCertItem);


            JSONObject certListJson = new JSONObject();
            try {
                JSONArray jArray = new JSONArray();
                for (int j = 0; j < userCertItem.size(); j++) {
                    JSONObject jObject = new JSONObject();
                    jObject.put("policy", userCertItem.get(j).get("policy"));
                    jObject.put("cnname", "cn : " + userCertItem.get(j).getCertItem().getSubjectCn());
                    Log.d("test", "cn만 뽑을 수 있니???1 - " + userCertItem.get(j).get("subjectName"));

                    Log.d("test", "cn만 뽑을 수 있니???2 - " + userCertItem.get(j).getCertItem().getSubjectCn());
                    jObject.put("endDate", userCertItem.get(j).get("expiredTime"));
                    jObject.put("issuer", userCertItem.get(j).get("issuerName"));
                    jArray.put(jObject);
                }
                certListJson.put("resultCode", "0000");
                certListJson.put("resultData", jArray);

                extendInterface.exWNSendData(certListJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private AlertDialog certListDialog(String title, String msg) {
        DialogInterface.OnClickListener DialogOnClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        };

        AlertDialog.Builder dialog = new AlertDialog.Builder(certlistContext);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setPositiveButton("확인", DialogOnClickListener);

        return dialog.create();
    }
}
