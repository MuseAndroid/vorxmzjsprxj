package kr.co.genexon.factconnector.push;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import kr.co.genexon.factconnector.BaseActivity;
import m.client.android.library.core.managers.ActivityHistoryManager;
import m.client.android.library.core.view.MainActivity;
import m.client.push.library.PushManager;
import m.client.push.library.common.PushConstants;

public class MessageArrivedReceiver extends BroadcastReceiver {

    JSONObject callbackJSON;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MessageArrivedReceiver", "MessageArrivedReceiver action:: " + intent.getAction());

        try {
            // TODO: 긴급 메세지 여부를 체크해야함
            // 알림에 대한 판단
            //String isAlarmOn = CommonLibUtil.getUserConfigInfomation("regiService", context);
            //Log.d("MessageArrivedReceiver", "MessageArrivedReceiver isAlarmOn:: " + isAlarmOn);
            //if (!TextUtils.isEmpty(isAlarmOn) && "true".equals(isAlarmOn)) {

            if (intent.getAction().equals(context.getPackageName() + PushConstants.ACTION_UPNS_MESSAGE_ARRIVED)) {
                // 디바이스 Badge 값 변경
                final String jsonData = intent.getExtras().getString("JSON");
                final JSONObject params = new JSONObject(jsonData);
                final String title = params.getString("MESSAGE");
                final String ext = params.getString("EXT");
                final String editExt = ext.substring(2);
                Log.d("푸시테스트", "ext 값 = " + editExt);
                final JSONObject jsonDumi = new JSONObject(editExt);
                final JSONObject jsonExt = new JSONObject(jsonDumi.getJSONObject("data").toString());
                final String screenCode = jsonExt.getString("code");
                final String titleMsg = jsonExt.getString("msg");
                Log.d("푸시테스트", "정보 값 = code:" + screenCode + ", msg:" + titleMsg);
                callbackJSON = new JSONObject();
                callbackJSON.put("body", title);
                JSONObject extData = new JSONObject();
                extData.put("code", screenCode);
                extData.put("msg", titleMsg);
                callbackJSON.put("data", extData);
                Log.d("푸시테스트", "작성된 callback JSON = " + callbackJSON);
                final String badgeNo = params.getString("BADGENO");
                try {
                    if (!TextUtils.isEmpty(badgeNo) && Integer.parseInt(badgeNo) > 0) {
//                        PushManager.getInstance().setDeviceBadgeCount(context, badgeNo);
                    }
                }
                catch(NumberFormatException e) {
                    // 존재하지 않거나 0보다 작다면 무시..
                }

                final MainActivity activity = (MainActivity) ActivityHistoryManager.getInstance().getTopActivity();
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        activity.getWebView().loadUrl("javascript:onReceiveAndroidNotification(" + callbackJSON + ")");
                    }
                });
                if (PushNotificationManager.isRestrictedScreen(context)) {
                    PushNotificationManager.showNotificationPopupDialog(context, intent, PushConstants.STR_UPNS_PUSH_TYPE);
                }
                else {
                    PushNotificationManager.createNotification(context, intent, PushConstants.STR_UPNS_PUSH_TYPE);
                }
            }
            else if (intent.getAction().equals(context.getPackageName() + PushConstants.ACTION_GCM_MESSAGE_ARRIVED)) {
                // 디바이스 Badge 값 변경
                final String jsonData = intent.getExtras().getString("JSON");
                JSONObject jsonMsg = new JSONObject(jsonData);
                //JSONObject aps = jsonMsg.getJSONObject("aps");
                JSONObject jsonMps = new JSONObject(jsonMsg.getJSONObject("mps").toString());
                JSONObject jsonAps = new JSONObject(jsonMsg.getJSONObject("aps").toString());
                final String title = jsonAps.getString("alert");
                final String ext = jsonMps.getString("ext");
                final String editExt = ext.substring(2);
                Log.d("푸시테스트", "ext 값 = " + editExt);
                final JSONObject jsonDumi = new JSONObject(editExt);
                final JSONObject jsonExt = new JSONObject(jsonDumi.getJSONObject("data").toString());
                final String screenCode = jsonExt.getString("code");
                final String titleMsg = jsonExt.getString("msg");
                Log.d("푸시테스트", "정보 값 = code:" + screenCode + ", msg:" + titleMsg);
                callbackJSON = new JSONObject();
                callbackJSON.put("body", title);
                JSONObject extData = new JSONObject();
                extData.put("code", screenCode);
                extData.put("msg", titleMsg);
                callbackJSON.put("data", extData);
                Log.d("푸시테스트", "작성된 callback JSON = " + callbackJSON);
                JSONObject aps = jsonMsg.getJSONObject("aps");
                final String badgeNo = aps.getString("badge");


                try {
                    if (!TextUtils.isEmpty(badgeNo) && Integer.parseInt(badgeNo) > 0) {
//                        PushManager.getInstance().setDeviceBadgeCount(context, badgeNo);
                    }
                }
                catch(NumberFormatException e) {
                    // 존재하지 않거나 0보다 작다면 무시..
                }
                final MainActivity activity = (MainActivity) ActivityHistoryManager.getInstance().getTopActivity();
                activity.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        activity.getWebView().loadUrl("javascript:onReceiveAndroidNotification(" + callbackJSON + ")");
                    }
                });
                if (PushNotificationManager.isRestrictedScreen(context)) {
                    PushNotificationManager.showNotificationPopupDialog(context, intent, PushConstants.STR_GCM_PUSH_TYPE);
                }
                else {
                    PushNotificationManager.createNotification(context, intent, PushConstants.STR_GCM_PUSH_TYPE);
                }

            }
            //}
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
