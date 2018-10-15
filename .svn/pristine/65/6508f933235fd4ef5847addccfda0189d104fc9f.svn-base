package kr.co.genexon.factconnector.implementation;

import kr.co.genexon.factconnector.BaseActivity;
import kr.co.genexon.factconnector.HNEngine;
import kr.co.genexon.factconnector.LockPasswordActivity;
import kr.co.genexon.factconnector.SendResultRunnable;
import kr.co.genexon.factconnector.raonCert.CertInputPwd;
import kr.co.genexon.factconnector.raonCert.CertListManager;
import kr.co.genexon.factconnector.raonCert.ICRSImportCert;
import m.client.android.library.core.bridge.InterfaceJavascript;
import m.client.android.library.core.utils.PLog;
import m.client.android.library.core.view.AbstractActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.softsecurity.transkey.TransKeyActivity;
import com.softsecurity.transkey.TransKeyWebCtrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ExtendWNInterface Class
 *
 * 사용자 정의 확장 인터페이스 구현
 *
 */
public class ExtendWNInterface extends InterfaceJavascript {

	HNEngine hnEngine = new HNEngine(callerObject, this);
	CertListManager certList = new CertListManager(callerObject, this);
	ICRSImportCert importCert = new ICRSImportCert(callerObject, this);
    CertInputPwd inputCertPwd = new CertInputPwd(callerObject, this);
	protected static final String TAG = "ExtendWNInterface";
	public static int LOCKSCREEN_COCE = 2018;
	public static int LOCKSCREEN_COCE1 = 2019;
	public static int LOCKSCREEN_COCE2 = 2222;
	public static int LOCKSCREEN_DELETE = 2999;
	public static int SECURE_KEYPAD_1 = 1777;
	public static int SECURE_KEYPAD_2 = 1778;
    public static int INPUT_CERT_PWD = 1790;
//	KisaCrypto kisaCrypto = new KisaCrypto(callerObject, this);
	public static String resultCallback = "";
	JSONObject extentionParam;
	String threadIdx;
	String jobIdx;
	Intent i;
	BaseActivity baseActivity;
	TransKeyWebCtrl transKeyCtrl;

	/**
	 * 아래 생성자 메서드는 반드시 포함되어야 한다. 
	 * @param callerObject
	 * @param webView
	 */
	public ExtendWNInterface(AbstractActivity callerObject, WebView webView) {
		super(callerObject, webView);
	}
	
	/**
	 * 보안 키보드 데이터 콜백 함수 
	 * @param data 
	 */
	public void wnCBSecurityKeyboard(Intent data) {  
		// callerObject.startActivityForResult(newIntent,libactivities.ACTY_SECU_KEYBOARD);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	// 사용자 정의 네이티브 확장 메서드 구현
	
	//
	// 아래에 네이티브 확장 메서드들을 구현한다.
	// 사용 예
	//
	public String exWNTestReturnString(String receivedString) {
		String newStr = "I received [" + receivedString + "]";
		return newStr;
	}
	
	/**
	 * WebViewClient의 shouldOverrideUrlLoading()을 확장한다.
	 * @param view
	 * @param url
	 * @return 
	 * 		InterfaceJavascript.URL_LOADING_RETURN_STATUS_NONE	확장 구현을하지 않고 처리를 모피어스로 넘긴다.
	 * 		InterfaceJavascript.URL_LOADING_RETURN_STATUS_TRUE	if the host application wants to leave the current WebView and handle the url itself
	 * 		InterfaceJavascript.URL_LOADING_RETURN_STATUS_FALSE	otherwise return false.
	 */
	public int extendShouldOverrideUrlLoading(WebView view, String url) {

		// Custom url schema 사용 예
//		if(url.startsWith("custom:")) {
//		    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//		    callerObject.startActivity( intent ); 
//    		return InterfaceJavascript.URL_LOADING_RETURN_STATUS_FALSE;
//    	}
		
		return InterfaceJavascript.URL_LOADING_RETURN_STATUS_NONE;
	}
	
	/**
	 * 페이지 로딩이 시작되었을때 호출된다.
	 * @param view
	 * @param url
	 * @param favicon
	 */
	public void onExtendPageStarted (WebView view, String url, Bitmap favicon) {
		PLog.i("", ">>> 여기는 ExtendWNInterface onPageStarted입니다!!!");
	}
	
	/**
	 * 페이지 로딩이 완료되었을때 호출된다.
	 * @param view
	 * @param url
	 */
	public void onExtendPageFinished(WebView view, String url) {
		PLog.i("", ">>> 여기는 ExtendWNInterface onPageFinished!!!");
	}
	
	/**
	 * Give the host application a chance to handle the key event synchronously
	 * @param view
	 * @param event
	 * @return True if the host application wants to handle the key event itself, otherwise return false
	 */
	public boolean extendShouldOverrideKeyEvent(WebView view, KeyEvent event) {
		
		return false;
	}
	
	/**
	 * onActivityResult 발생시 호출.
	 */
	public void onExtendActivityResult(Integer requestCode, Integer resultCode, Intent data) {
		PLog.i("", ">>> 여기는 ExtendWNInterface onExtendActivityResult!!!  requestCode => [ " + requestCode + " ], resultCode => [ " + resultCode + " ]");
	}
	
	/*
	public String syncTest(String param1, String param2) {
		return param1 + param2;
	}
	
	public void asyncTest(final String callback) {
		callerObject.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				String format = "javascript:%s('abc', 1, {'a':'b'});";
				String jsString = String.format(format, callback);
				PLog.d("asyncTest", jsString);
    			webView.loadUrl(jsString);
			}
		});
	}
	*/

	@JavascriptInterface
	public void exWNCheckFirst(String jsonData) {
		try {
			JSONObject jsonObject  = new JSONObject(jsonData);
			String text = jsonObject.getString("TEXT");
			JSONObject resultValue = new JSONObject();
			resultValue.put("msg", text);

			String callback = jsonObject.get("CALLBACK").toString();

			callerObject.runOnUiThread(new SendResultRunnable(callback, "", resultValue, this.webView));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@JavascriptInterface
	public void exWNgetInsureData(String jsonData) {
		try {
			JSONObject jsonObject  = new JSONObject(jsonData);
			final Map<String, String> reqParam = new HashMap<String, String>();
			reqParam.put("name", jsonObject.getString("name"));
			reqParam.put("jumin", jsonObject.getString("jumin"));
			reqParam.put("telNum", jsonObject.getString("telNum"));
			reqParam.put("telCom", jsonObject.getString("telCom"));
			reqParam.put("searchGbn", jsonObject.getString("searchGubun"));
			reqParam.put("s4Code", jsonObject.getString("s4Code"));
			reqParam.put("m6Code", jsonObject.getString("m6Code"));

			resultCallback = jsonObject.get("CALLBACK").toString();

			callerObject.runOnUiThread(new Runnable(){
				@Override
				public void run() {
					hnEngine.setSJWData(reqParam);
				}
//				Engine mEngine;
//				mEngine = Engine.getInstatnce(callerObject.getApplicationContext());
//            	hnEngine.initEngine(mEngine);
			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@JavascriptInterface
	public void exWNsetExtParams(String jsonData) {
		try {
			JSONObject jsonObject  = new JSONObject(jsonData);
			resultCallback = jsonObject.get("CALLBACK").toString();
			String jobCD = jsonObject.getString("jobCode");
			jobIdx = jsonObject.getString("jobIndex");
			threadIdx = jsonObject.getString("threadIndex");

			extentionParam = new JSONObject();
			switch(jobCD) {
				case "1002":
					extentionParam.put("jobIndex", jobIdx);
					extentionParam.put("threadIndex", threadIdx);
					extentionParam.put("jobCode", jobCD);
					break;
				case "1001":
					String reqSecureNo = jsonObject.getString("data");
					extentionParam.put("jobIndex", jobIdx);
					extentionParam.put("threadIndex", threadIdx);
					extentionParam.put("reqSecureNo", reqSecureNo);
					extentionParam.put("jobCode", jobCD);
					break;
				case "1100":
					String reqSMSAuthNo = jsonObject.getString("data");
					extentionParam.put("jobIndex", jobIdx);
					extentionParam.put("threadIndex", threadIdx);
					extentionParam.put("reqSMSAuthNo", reqSMSAuthNo);
					extentionParam.put("jobCode", jobCD);
					break;
			}

			callerObject.runOnUiThread(new Runnable(){
				@Override
				public void run() {
					hnEngine.additionParam(extentionParam);
				}
//				Engine mEngine;
//				mEngine = Engine.getInstatnce(callerObject.getApplicationContext());
//            	hnEngine.initEngine(mEngine);
			});
		} catch (Exception e) {
			e.printStackTrace();
			try {
				JSONObject errorJson = new JSONObject();
				errorJson.put("resultCode", "9100");
				JSONObject errorData = new JSONObject();
				errorData.put("threadIndex", "0");
				errorData.put("jobIndex", "0");
				JSONObject errorBody = new JSONObject();
				errorBody.put("errorCode", "0");
				errorBody.put("errorMsg", "엔진이 실행되지 않았습니다");
				errorData.put("data", errorBody);
				errorJson.put("resultData", errorData);
				exWNSendData(errorJson);
			} catch (Exception e1) {

			}
		}
	}

//	@JavascriptInterface
//	public void exWNEncryptData(String jsonData) {
//		try {
//			JSONObject jsonObject  = new JSONObject(jsonData);
//			final Map<String, String> reqParam = new HashMap<String, String>();
//			reqParam.put("originData", jsonObject.getString("data"));
//
//			resultCallback = jsonObject.get("CALLBACK").toString();
//
//			callerObject.runOnUiThread(new Runnable(){
//				@Override
//				public void run() {
//					kisaCrypto.setOriginData(reqParam);
//				}
////				Engine mEngine;
////				mEngine = Engine.getInstatnce(callerObject.getApplicationContext());
////            	hnEngine.initEngine(mEngine);
//			});
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//	}

	@JavascriptInterface
	public void exWNgetInsureCompanyData(String jsonData) {
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			String insureData = jsonObject.getString("data");
			Log.d(TAG, "1차 json 값 = " + jsonObject);
			final JSONArray dataArray = new JSONArray(insureData);
			Log.d(TAG, "2차 jsonarray 값 = " + dataArray);
			Log.d(TAG, "jsonarray 길이 = " + dataArray.length());
			resultCallback = jsonObject.get("CALLBACK").toString();

			callerObject.runOnUiThread(new Runnable(){
				@Override
				public void run() {
					hnEngine.setInsureData(dataArray);
				}
			});
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	@JavascriptInterface
	public void exWNgetLockPassword(String jsonData) {
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			final String titleStr = jsonObject.getString("title");
			final String descStr = jsonObject.getString("desc");
			final String option = jsonObject.getString("option");
			resultCallback = jsonObject.get("CALLBACK").toString();


			callerObject.runOnUiThread(new Runnable(){
				@Override
				public void run() {
					i = new Intent(callerObject, LockPasswordActivity.class);
					i.putExtra("title", titleStr);
					i.putExtra("desc", descStr);
					i.putExtra("option", option);
					// 엑티비티가 종료된 후 BaseActivity에서 OnActivityResult를 통해 후처리 진행됨
					if (option.equals("0")) {
						callerObject.startActivityForResult(i, LOCKSCREEN_COCE);
						callerObject.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					} else {
						callerObject.startActivityForResult(i, LOCKSCREEN_COCE2);
						callerObject.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @JavascriptInterface
    public void exWNShowSecureKeyPad(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            final String title = jsonObject.getString("title");
            final int maxLeng = jsonObject.getInt("max_length");
            final int minLeng = jsonObject.getInt("min_length");
            final String type = jsonObject.getString("type");
            final String hint = jsonObject.getString("placeholder");

            resultCallback = jsonObject.getString("CALLBACK");
            callerObject.runOnUiThread(new Runnable(){
                @Override
                public void run() {
                	if (type.equals("A")) {
						callerObject.startActivityForResult(getIntentParam(TransKeyActivity.mTK_TYPE_KEYPAD_QWERTY_LOWER,
								TransKeyActivity.mTK_TYPE_TEXT_PASSWORD, title, hint,
								maxLeng, "최대 글자 "+ maxLeng +"자를 입력하셨습니다.", minLeng, "최소 글자 "+ minLeng +"글자미만입니다.", 0, 2), SECURE_KEYPAD_1);
					} else {
						callerObject.startActivityForResult(getIntentParam(TransKeyActivity.mTK_TYPE_KEYPAD_NUMBER,
								TransKeyActivity.mTK_TYPE_TEXT_PASSWORD, title, hint,
								maxLeng, "최대 글자"+ maxLeng +"자를 입력하셨습니다.", minLeng, "최소 글자 "+ minLeng +"글자미만입니다.", 0, 2), SECURE_KEYPAD_2);
					}
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void exWNgetCertList(String jsonData) {
	    try {
	        JSONObject jsonObject = new JSONObject(jsonData);

	        resultCallback = jsonObject.getString("CALLBACK");
	        callerObject.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    certList.loadCertList();
                }
            });
        } catch (Exception e) {
	        e.printStackTrace();
        }
    }

    @JavascriptInterface
	public void exWNimportCertNumber(String jsonData) {
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			final String url = jsonObject.getString("url");
			final String port = jsonObject.getString("port");
			resultCallback = jsonObject.getString("CALLBACK");
			callerObject.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					importCert.setImportCertPort(url, port);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@JavascriptInterface
	public void exWNimportCert(String jsonData) {
		try {
			JSONObject jsonObject = new JSONObject(jsonData);

			resultCallback = jsonObject.getString("CALLBACK");
			callerObject.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					importCert.getCertFile();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@JavascriptInterface
	public void exWNcertCheckPasswd(String jsonData) {
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			final int certIndex = jsonObject.getInt("index");
			resultCallback = jsonObject.getString("CALLBACK");
			callerObject.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// 실행될 프로세스 작성
					inputCertPwd.inputCertPwd(certIndex);
				}
			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@JavascriptInterface
	public void exWNSendDeviceID(String jsonData) {
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			resultCallback = jsonObject.getString("CALLBACK");
			callerObject.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// 실행될 프로세스 작성
					hnEngine.sendDeviceID();
				}
			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void exWNSendData(JSONObject jsonData) {
		try {
			callerObject.runOnUiThread(
					new SendResultRunnable(resultCallback, "", jsonData, this.webView)
//				Engine mEngine;
//				mEngine = Engine.getInstatnce(callerObject.getApplicationContext());
//            	hnEngine.initEngine(mEngine);
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param keyPadType       키패드 타입
	 * @param textType         키보드 입력 타입
	 * @param label            키패드입력필드의 입력 라벨
	 * @param hint             입력필드 힌트 텍스트
	 * @param maxLength        최대 입력길이값
	 * @param maxLengthMessage 최대 입력길이 초과시 메세지
	 * @param line3Padding     쿼티형 키패드에서 입력완료 버튼과 삭제 버튼의 간격
	 * @param reduceRate       에디트 박스안의 글자 크기조절값
	 * @return 설정된 Intent
	 * @author whkim
	 */
	public Intent getIntentParam(int keyPadType, int textType, String label, String hint,
								 int maxLength, String maxLengthMessage, int minLength, String minLengthMessage, int line3Padding, int reduceRate) {
		Intent newIntent = new Intent(callerObject.getApplicationContext(),
				TransKeyActivity.class);

		/**
		 * 키패드 타입
		 *			TransKeyActivity.mTK_TYPE_KEYPAD_NUMBER			:	숫자전용
		 *			TransKeyActivity.mTK_TYPE_KEYPAD_QWERTY_LOWER	:	소문자 쿼티
		 *			TransKeyActivity.mTK_TYPE_KEYPAD_QWERTY_UPPER	:	대문자 쿼티
		 *			TransKeyActivity.mTK_TYPE_KEYPAD_ABCD_LOWER		:	소문자 순열자판
		 *			TransKeyActivity.mTK_TYPE_KEYPAD_ABCD_UPPER		:	대문자 순열자판
		 *			TransKeyActivity.mTK_TYPE_KEYPAD_SYMBOL			:	심벌자판
		 */
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_KEYPAD_TYPE, keyPadType);

		/**
		 * 키보드가 입력되는 형태
		 * 			TransKeyActivity.mTK_TYPE_TEXT_IMAGE 				:	보안 텍스트 입력
		 *			TransKeyActivity.mTK_TYPE_TEXT_PASSWORD 			:	패스워드 입력
		 *			TransKeyActivity.mTK_TYPE_TEXT_PASSWORD_EX 			:	마지막 글자 보여주는 패스워드 입력
		 *			mTK_TYPE_TEXT_PASSWORD_IMAGE                        :   Text Area에 이미지 * 표시
		 *			TransKeyActivity.mTK_TYPE_TEXT_PASSWORD_LAST_IMAGE	:	마지막 글자를 제외한 나머지를 *표시.
		 */
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_INPUT_TYPE, textType);

		/**
		 * securekey 설정 옵션
		 * 	타입	:	TransKeyActivity.mTK_TYPE_CRYPT_SERVER	:	생성한 키를 할당하도록 설정 (서버와 키를 미리 정의하여 복호화)
		 * 			TransKeyActivity.mTK_TYPE_CRYPT_LOCAL	:	로컬에서 키패드를 호출할때마다 내부적으로 키를 자동으로 생성하여 할당되도록 설정.
		 * @code
		 * 		1. 생성한 키 설정.
		 * 			byte[] secureKey = { 'M', 'O', 'B', 'I', 'L', 'E', 'T', 'R', 'A', 'N', 'S', 'K', 'E', 'Y', '1', '0' };
		 * 			newIntent.putExtra(TransKeyActivity.mTK_PARAM_CRYPT_TYPE, TransKeyActivity.mTK_TYPE_CRYPT_SERVER);
		 * 			newIntent.putExtra(TransKeyActivity.mTK_PARAM_SECURE_KEY, secureKey);
		 *		2. 내부적으로 키 자동생성
		 *			newIntent.putExtra(TransKeyActivity.mTK_PARAM_CRYPT_TYPE, TransKeyActivity.mTK_TYPE_CRYPT_LOCAL);
		 */
//        byte[] secureKey = {'M', 'o', 'b', 'i', 'l', 'e', 'T', 'r', 'a', 'n', 's', 'K', 'e', 'y', '1', '0'};
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_CRYPT_TYPE, TransKeyActivity.mTK_TYPE_CRYPT_SERVER);
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SECURE_KEY, secureKey);
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_CRYPT_TYPE, TransKeyActivity.mTK_TYPE_CRYPT_LOCAL);

		//	키패드입력화면의 입력필드 라벨
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_NAME_LABEL, label);

		//	입력필드에 스페이스바 입력을 무시
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_SPACE, false);

		/**
		 * 최대 입력 길이 설정 ( Default : 설정이 없을 경우 16글자까지 입력가능하도록 설정된다.)
		 * 암호화한 데이터의 경우 보안성 강화 적용된 데이터의 경우 입력 글자수에 상관없이 최대 입력 길이 설정값만큼
		 * 항상 데이터가 채워져서 암호화 데이터가 적용되므로 사용목적에 따라 적당한 값을 사용하는 것을 권장.
		 */

		// Maxlength 설정
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_INPUT_MAXLENGTH, maxLength);

		//	maxLength 설정된 값보다 길이가 초과할 경우 보여줄 메세지 설정.
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_MAX_LENGTH_MESSAGE, maxLengthMessage);

		// Minlength 설정
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_INPUT_MINLENGTH, minLength);
//
//        //	minLength 설정된 값보다 길이가 미만일 경우 보여줄 메세지 설정.
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_MIN_LENGTH_MESSAGE, minLengthMessage);

		//	입력 필드에 보여지는 힌트를 설정.
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_SET_HINT, hint);

		//	Hint 텍스트 사이즈를 설정.(단위 dip, 0이면 디폴트 크기로 보여준다.)
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_SET_HINT_TEXT_SIZE, 0);

		//	커서를 보여준다.
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_SHOW_CURSOR, true);

		//	에디트 박스안의 글자 크기를 조절한다.
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_EDIT_CHAR_REDUCE_RATE, reduceRate);

		//	심볼 변환 버튼을 비활성화 시킨다.
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_SYMBOL, false);

		//	심볼 변환 버튼을 비활성화 시킬 경우 팝업 메시지를 설정한다.
		// newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_SYMBOL_MESSAGE, "심볼키는 사용할 수 없습니다.");

		// 숫자키패드에 '취소' 버튼 추가여부
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_NUMPAD_USE_CANCEL_BUTTON, true);

		/**
		 * TransKeyActivity.mTK_PARAM_KEYPAD_MARGIN			:	쿼티형 키패드에서 입력완료 버튼과 삭제 버튼의 간격을 조정. 입력안할시 기본으로 설정.
		 * TransKeyActivity.mTK_PARAM_KEYPAD_TOP_MARGIN		:	버튼의 상하 공간을 값(dip)만큼 늘림
		 * @code
		 * 		newIntent.putExtra(TransKeyActivity.mTK_PARAM_KEYPAD_MARGIN, 0);
		 *		newIntent.putExtra(TransKeyActivity.mTK_PARAM_KEYPAD_TOP_MARGIN, 0);
		 */
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_KEYPAD_MARGIN, line3Padding);

		//	TalkBack 설정 시 음성이 나올 수 있도록 설정.
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_TALKBACK, true);

		//	SHIFT고정 옵션 설정
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_SHIFT_OPTION, true);

		// 전체삭제 X버튼 사용여부 설정
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_CLEAR_BUTTON, true);


		/**
		 * 	키패드 언어 설정 - 키패드 배열의 버튼에는 적용되지않음. 네비게이션바 버튼과 커맨드 버튼에 적용됨.
		 * 		- 반드시 해당 언어의 리소스가 포함되어있는지 확인필요.
		 *
		 * 		키패드 언어타입 정의
		 * 			TransKeyActivity.mTK_Language_Korean		:	한국어(default)
		 *			TransKeyActivity.mTK_Language_English		:	영어
		 *			TransKeyActivity.mTK_Language_Chinese		:	중국어
		 *			TransKeyActivity.mTK_Language_Japanese		:	일본어
		 *			TransKeyActivity.mTK_Language_Vietnames		:	베트남어
		 *			TransKeyActivity.mTK_Language_Mongolian		:	몽골어
		 *			TransKeyActivity.mTK_Language_Thai			:	태국어
		 *			TransKeyActivity.mTK_Language_Indonesian	:	인도네시아어
		 */
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_LANGUAGE, TransKeyActivity.mTK_Language_Korean);

		//	네비바 사용여부를 설정
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_NAVIBAR, false);

		/**
		 *	newIntent.putExtra(TransKeyActivity.mTK_PARAM_NOTICE_MESSAGE, "입력한 PIN이 서로 다릅니다.\n 다시 시도해 주세요.");
		 *	newIntent.putExtra(TransKeyActivity.mTK_PARAM_INFO_MESSAGEs, "보내기/받기 PIN은 삼성월렛을 통해 \n돈을 보내거나 충전/환급할 때 필요합니다");
		 */
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_NOTICE_MESSAGE, "메시지 메인 텍스트를 삽입합니다.");
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_INFO_MESSAGE, "메시지 디스크립션을 삽입합니다.");


		newIntent.putExtra(TransKeyActivity.mTK_PARAM_SAMEKEY_ENCRYPT_ENABLE, true);

		//	키패드 입력시 입력필드에 표시되는 값이 *(아스테리스크)로 변경되기전 딜레이 타임을 설정 (단위 second)
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_HIDE_TIMER_DELAY, 5);

		//	최상단 버튼의 top margin을 설정(단위 dp)
		//newIntent.putExtra(TransKeyActivity.mTK_PARAM_KEYPAD_HIGHEST_TOP_MARGIN, 2);

		//	키패드 왼쪽, 오른쪽 마진을 설정
		// newIntent.putExtra(TransKeyActivity.mTK_PARAM_KEYPAD_LEFT_RIGHT_MARGIN, 1);
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_ALERTDIALOG_TITLE, "mTranskey alert");

		newIntent.putExtra(TransKeyActivity.mTK_PARAM_PREVENT_CAPTURE, true);

//         드래그기능을 막고 최초 터치값으로 입력되도록 설정
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_DRAG_EVENT, true);

//         심볼키패드에서 불필요한 키 더미화
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_CUSTOM_DUMMY_STRING, "!@");

//         순차적인 더미 이미지 다르게 표현하기위한 옵션
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_CUSTOM_DUMMY, true);

//         커서를 이미지로 사용하는 옵션
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_CUSTOM_CURSOR, true);

//         비대칭키 사용시 공개키 설정하는 옵션
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_RSA_PUBLICK_KEY, publicKey);

//        쿼티 자판 높이 설정
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_QWERTY_HEIGHT, (float)1.5);

//        넘버 자판 높이 설정
//	    newIntent.putExtra(TransKeyActivity.mTK_PARAM_NUMBER_HEIGHT, (float)0.5);

		//풀뷰 타이틀 테스트
		newIntent.putExtra(TransKeyActivity.mTK_PARAM_TITLE_LABEL, "mTransKey Title Label");

		// 버튼효과 막기
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_BUTTON_EFFECT, true);

//        String originalPassword = "acc4af609fad57f1cd870a5d94092a24be5fd974";
//        byte[] pbkdfKey = toByteArray(originalPassword);
//        byte[] PBKDF2_SALT = {1, 6, 0, 7, 4, 4, 4, 4, 8, 8, 7, 1, 4, 3, 3, 3, 3, 3, 3, 3};
//        int PBKDF2_IT = 512;
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_PBKDF2_RANDKEY, pbkdfKey);
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_PBKDF2_SALT, PBKDF2_SALT);
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_PBKDF2_IT, PBKDF2_IT);
		return newIntent;
	}
}
