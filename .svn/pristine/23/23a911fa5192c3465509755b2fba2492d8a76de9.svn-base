//package kr.co.genexon.factconnector.kisaCrypto;
//
//import android.util.Log;
//
//import kr.co.genexon.factconnector.BaseActivity;
//import kr.co.genexon.factconnector.implementation.ExtendWNInterface;
//import m.client.android.library.core.view.AbstractActivity;
//
//import org.json.JSONObject;
//import org.kisa.*;
//
//import java.util.Map;
//
//public class KisaCrypto extends BaseActivity {
//
//    public static KisaCrypto kCrypto;
//
//    String originText;
//    String encryptText;
//    String decryptText;
//    public AbstractActivity parentActivity;
//    private ExtendWNInterface extendInterface;
//
//    static {
//        System.loadLibrary("KISACrypto");
//    }
//
//    public static final byte[] key = {
//        0x01, 0x02, 0x03, 0x04,
//        0x05, 0x06, 0x07, 0x08,
//        0x09, 0x0A, 0x0B, 0x0C,
//        0x0D, 0x0E, 0x0F, 0x10
//    };
//
//    public static final byte[] iv = {
//        0x01, 0x01, 0x01, 0x01,
//        0x01, 0x01, 0x01, 0x01,
//        0x01, 0x01, 0x01, 0x01,
//        0x01, 0x01, 0x01, 0x01
//    };
//
//    SEEDCBC seed = new SEEDCBC();
//
//    byte[] plainText = new byte[128];
//    byte[] cipherText = new byte[144];
//    byte[] decryptedText = new byte[144];
//
//    int outputTextLen = 0;
//
//
//
//    public KisaCrypto(AbstractActivity cObject, ExtendWNInterface interFace) {
//        parentActivity = cObject;
//        extendInterface = interFace;
//    }
//
//    public void setOriginData(Map<String, String> originMap) {
//        originText = originMap.get("originData");
//        seed.init(SEEDCBC.ENC, key, iv);
//        byte[] encryptData = startEncrypt();
//        try {
//            JSONObject jsonData = new JSONObject();
//            jsonData.put("encryptedData", encryptData);
//            extendInterface.exWNSendData(jsonData);
//        } catch (Exception e) {
//
//        }
//    }
//
//    public byte[] startEncrypt() {
//        plainText = originText.getBytes();
//        Log.d("CryptoProcess", "byte화 된 텍스트 = " + plainText);
//        outputTextLen = seed.process(plainText, 0, plainText.length, cipherText, 0);
//        outputTextLen += seed.close(cipherText, outputTextLen);
//        Log.d("CryptoProcess", "암호화 된 값 길이 = " + outputTextLen + "암호화 값 = " + cipherText);
//        return cipherText;
//    }
//}
