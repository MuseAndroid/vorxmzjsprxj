package kr.co.genexon.factconnector;

import android.util.Log;
import android.webkit.WebView;

import org.json.JSONObject;

public class SendResultRunnable implements Runnable {

    public static final String TAG = "SendResultRunnable";

    private String serviceName;
    private String status;
    private String callback;
    private WebView webView;
    private JSONObject result;


    public SendResultRunnable(String callback, String serviceName,JSONObject result, WebView webview) {
        this.serviceName = serviceName;
        this.result = result;
        this.callback = callback;
        this.webView  = webview;
    }

    @Override
    public void run() {
        StringBuffer sb = new StringBuffer("javascript:(" + this.callback);
        sb.append("(");
        sb.append(result.toString());
        sb.append("));");
        Log.i(TAG, sb.toString());
        webView.loadUrl(sb.toString());
    }

}
