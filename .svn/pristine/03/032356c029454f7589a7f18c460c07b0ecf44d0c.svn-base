package kr.co.genexon.factconnector.raonCert;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.raonsecure.ksw.RSKSWCertManager;
import com.raonsecure.ksw.RSKSWCertificate;
import com.raonsecure.ksw.RSKSWException;
import com.raonsecure.ksw.RSKSWUtils;
import com.softsecurity.transkey.TransKeyActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import kr.co.genexon.factconnector.BaseActivity;
import kr.co.genexon.factconnector.implementation.ExtendWNInterface;
import m.client.android.library.core.view.AbstractActivity;

public class CertInputPwd extends BaseActivity{

    public static RSKSWCertificate userCert;
    boolean isInApp;
    public static RSKSWCertManager manager;
    public AbstractActivity parentActivity;
    private ExtendWNInterface extendInterface;
    private Context certInputPwdContext;
    Intent intent;
    SharedPreferences keyPref;
    public static byte[] random = null;

    public CertInputPwd(AbstractActivity callerObject, ExtendWNInterface extendWNInterface) {
        parentActivity = callerObject;
        extendInterface = extendWNInterface;
        certInputPwdContext = callerObject.getApplicationContext();
    }

    public void inputCertPwd(int idx) {
        ArrayList<?> userCertsList = CertListManager.getUserCerts();
        try {
            userCert = (RSKSWCertificate) userCertsList.get(idx);

            Log.d("inputPWD", "cert값 = " + userCert.getPolicy());
            Log.d("inputPWD", "인증서 경로 = " + userCert.getDirPath());
            Log.d("inputPWD", "인증서 경로 1 = " + userCert.getCertPath());
            Log.d("inputPWD", "인증서 경로 2 = " + userCert.getKeyPath());

            // 인증서 & 인증서 키 경로 저장

            keyPref = certInputPwdContext.getSharedPreferences("keyValue", MODE_PRIVATE);
            SharedPreferences.Editor editor = keyPref.edit();
            editor.putString("CertPath", userCert.getCertPath());
            editor.putString("KeyPath", userCert.getKeyPath());
            editor.apply();

            // 인증서 & 인증서 키 경로 저장

            manager = RSKSWCertManager.getInstance(certInputPwdContext);
            try {
                random = RSKSWUtils.getSecRandom(20);
            } catch (RSKSWException e) {
                e.printStackTrace();
            }
            manager.setCertPwdModeMTranskey(random);

            intent = getIntentParam(
                    TransKeyActivity.mTK_TYPE_KEYPAD_QWERTY_LOWER,
                    TransKeyActivity.mTK_TYPE_TEXT_PASSWORD_EX,
                    "인증서 비밀번호",
                    "인증서 비밀번호를 입력하세요",
                    30, "30자리까지 입력 가능합니다.",
                    6, "6자리 이상 입력해야 합니다.",
                    5, 20, false);
            parentActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    parentActivity.startActivityForResult(intent, ExtendWNInterface.INPUT_CERT_PWD);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            try {
                JSONObject callJson = new JSONObject();
                callJson.put("resultCode", "9000");
                callJson.put("resultData", "인증서가 선택되지 않았습니다.\n선택 후 비밀번호를 입력하시기 바랍니다");
                extendInterface.exWNSendData(callJson);
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param keyPadType		키패드 타입
     * @param textType			키보드 입력 타입
     * @param label				키패드입력필드의 입력 라벨
     * @param hint				입력필드 힌트 텍스트
     * @param maxLength			최대 입력길이값
     * @param maxLengthMessage	최대 입력길이 초과시 메세지
     * @param line3Padding		쿼티형 키패드에서 입력완료 버튼과 삭제 버튼의 간격
     * @param reduceRate		에디트 박스안의 글자 크기조절값
     * @param useAtmMode		ATM뱅킹모드 설정
     * @return	설정된 Intent
     */
    public Intent getIntentParam(int keyPadType, int textType, String label, String hint,
                                 int maxLength, String maxLengthMessage, int minLength, String minLengthMessage, int line3Padding, int reduceRate, boolean useAtmMode) {
        Intent newIntent = new Intent(certInputPwdContext, TransKeyActivity.class);

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
         *          TransKeyActivity.mTK_TYPE_TEXT_PASSWORD_IMAGE       :   Text Area에 이미지 * 표시
         *			TransKeyActivity.mTK_TYPE_TEXT_PASSWORD_LAST_IMAGE	:	마지막 글자를 제외한 나머지를 *표시.
         */
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_INPUT_TYPE, textType);

        //	키패드입력화면의 입력필드 라벨
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_NAME_LABEL, label);

        //	입력필드에 스페이스바 입력을 무시
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_SPACE, false);

        /**
         * 최대 입력 길이 설정 ( Default : 설정이 없을 경우 16글자까지 입력가능하도록 설정된다.)
         * 암호화한 데이터의 경우 보안성 강화 적용된 데이터의 경우 입력 글자수에 상관없이 최대 입력 길이 설정값만큼
         * 항상 데이터가 채워져서 암호화 데이터가 적용되므로 사용목적에 따라 적당한 값을 사용하는 것을 권장.
         */
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_INPUT_MAXLENGTH, maxLength);

        //	maxLength 설정된 값보다 길이가 초과할 경우 보여줄 메세지 설정.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_MAX_LENGTH_MESSAGE, maxLengthMessage);

        // Minlength 설정
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_INPUT_MINLENGTH, minLength);

        //	minLength 설정된 값보다 길이가 미만일 경우 보여줄 메세지 설정.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_MIN_LENGTH_MESSAGE, minLengthMessage);


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
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_CRYPT_TYPE, TransKeyActivity.mTK_TYPE_CRYPT_SERVER);
//		byte[] secureKey = { 'M', 'O', 'B', 'I', 'L', 'E', 'T', 'R', 'A', 'N', 'S', 'K', 'E', 'Y', '1', '0' };
//		newIntent.putExtra(TransKeyActivity.mTK_PARAM_SECURE_KEY, secureKey);

        newIntent.putExtra(TransKeyActivity.mTK_PARAM_PBKDF2_RANDKEY, random);

        //	입력 필드에 보여지는 힌트를 설정.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SET_HINT, hint);

        //	Hint 텍스트 사이즈를 설정.(단위 dip, 0이면 디폴트 크기로 보여준다.)
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SET_HINT_TEXT_SIZE, 0);

        //	커서를 보여준다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SHOW_CURSOR, false);

        //	전체삭제 버튼 사용여부를 설정한다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_CLEAR_BUTTON, false);

        // 숫자키패드에 '취소' 버튼 추가여부
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_NUMPAD_USE_CANCEL_BUTTON, true);

        //	에디트 박스안의 글자 크기를 조절한다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_EDIT_CHAR_REDUCE_RATE, reduceRate);

        //	심볼 변환 버튼을 비활성화 시킨다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_SYMBOL, false);

        //	심볼 변환 버튼을 비활성화 시킬 경우 팝업 메시지를 설정한다.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_DISABLE_SYMBOL_MESSAGE, "심볼키는 사용할 수 없습니다.");

        //  mTK_PARAM_CIPHER_DATA , mTK_PARAM_CIPHER_DATA_EX 일때 동일키 입력 암호화 사용여부 (true : 사용 / false : 사용안함)
//        newIntent.putExtra(TransKeyActivity.mTK_PARAM_SAMEKEY_ENCRYPT_ENABLE, true);


        /**
         * TransKeyActivity.mTK_PARAM_KEYPAD_MARGIN			:	쿼티형 키패드에서 입력완료 버튼과 삭제 버튼의 간격을 조정. 입력안할시 기본으로 설정.
         * TransKeyActivity.mTK_PARAM_KEYPAD_TOP_MARGIN		:	버튼의 상하 공간을 값(dip)만큼 늘림
         * @code
         * 		newIntent.putExtra(TransKeyActivity.mTK_PARAM_KEYPAD_MARGIN, 0);
         *		newIntent.putExtra(TransKeyActivity.mTK_PARAM_KEYPAD_TOP_MARGIN, 0);
         */


        //	TalkBack 설정 시 음성이 나올 수 있도록 설정.
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_TALKBACK, false);

        //	캡쳐 방지 기능 활성화
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_PREVENT_CAPTURE, false);

        //	키패드 입력시 입력필드에 표시되는 값이 *(아스테리스크)로 변경되기전 딜레이 타임을 설정 (단위 second)
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_HIDE_TIMER_DELAY, 6);

        //	키패드 show 애니메이션 활성화
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_KEYPAD_ANIMATION, false);

        /**
         * ATM뱅킹 모드 설정
         *		- 쿼티하단 취소/확인/백스페이스바 버튼 삭제 -> 스페이스바 Only
         *		- 숫자하단 기능키 한 줄 제거됨
         */
        newIntent.putExtra(TransKeyActivity.mTK_PARAM_USE_ATM_MODE, useAtmMode);

        return newIntent;
    }
}
