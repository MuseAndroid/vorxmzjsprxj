package kr.co.genexon.factconnector;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.heenam.espider.Engine;
import com.heenam.espider.EngineInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import kr.co.genexon.factconnector.implementation.ExtendWNInterface;
import m.client.android.library.core.view.AbstractActivity;

public class HNEngine extends BaseActivity implements EngineInterface {

    public static Engine mEngine; // Espider Engine
    private ArrayList<HashMap<String, HashMap<String, String>>> mJobList; // 스크래핑 될 jobList
    private Context engineContext;

    public String raonKey = "";
    public String plainKey = "";

    private ArrayList<String> mProgressNameListData; //조회할 업무 Name (데이터와 상관 없음)
    private ArrayList<String> mJobResultJsonList; //모듈조회시 결과 데이터

    public String userName = "";
    public String userJumin = "";
    public String userTelNum = "";
    public String userTelCom = "";
    public String searchGubun = "";
    public String s4Code = "";
    public String m6Code = "";
    public String jobCode = "";
    private String mCertPath = "";
    private String mKeyPath = "";
    private String mCertPassword = "";
    private String mIdentify_No = "";

    public int threadCount;
    public int jobCount;
    public boolean companyFlag = false;
    public boolean errorFlag = false;
    public AbstractActivity parentActivity;
    private ExtendWNInterface extendInterface;
    SharedPreferences cipherStrPref;
    SharedPreferences keyPref;
    JSONObject bohumJsonResult = new JSONObject();
    JSONArray bohumResultArr = new JSONArray();
    JSONArray allResultArr = new JSONArray();

    public HNEngine(AbstractActivity callerObject, ExtendWNInterface interFace) {
        parentActivity = callerObject;
        extendInterface = interFace;
        engineContext = callerObject.getApplicationContext();
        initEngine(callerObject.getApplicationContext());
    }

    public void initEngine(Context context) {
        Log.d("engine", "실행되니?");
        try {
            Log.d("engine", "정상 init 됨");
            mEngine = Engine.getInstatnce(context);

            mEngine.setInterface(this);
            mEngine.setLicenseKey("ced0cb1e-9b90-11e8-8dd9-80c16e782f98");

            mJobResultJsonList = new ArrayList<String>();
            // jobList
            mProgressNameListData = new ArrayList<String>();
            mJobList = new ArrayList<HashMap<String, HashMap<String, String>>>();

            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_VERSION = [%s]", mEngine.getVersion()));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_APP_ID = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_APP_ID)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_APP_VERSION = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_APP_VERSION)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_UNIQUE_ID = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UNIQUE_ID)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_UUID = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UUID)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_MANUFACTURER = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_MANUFACTURER)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_MODEL = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_MODEL)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_OS_NAME = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_OS_NAME)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_OS_VERSION = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_OS_VERSION)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_PLATFORM = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_PLATFORM)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_PLATFORM_NAME = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_PLATFORM_NAME)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_USER_NAME = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_USER_NAME)));
            Log.d(HNEngine.class.getSimpleName(), String.format("ENGINE_DEVICE_LOCALE_NAME = [%s]", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_LOCALE_NAME)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendDeviceID() {
        try {
            JSONObject idResult = new JSONObject();
            String uuid = mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UUID);
            if (!uuid.equals(null) || !uuid.equals("")) {
                idResult.put("resultCode", "0000");
                idResult.put("resultData", uuid);
            } else {
                idResult.put("resultCode", "9000");
                idResult.put("resultData", "");
            }
            extendInterface.exWNSendData(idResult);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void startEngine() {
        try {
            mEngine.stopEngine(); //기존 엔진이 실행중이면, 중단
            mEngine.setThreadCount(16); //스레드의 개수 설정(모바일에서 성능이 낮은 폰을 지원하기 위해 최대 16개로 제한한다)
            mEngine.setAutoStop(true); //엔진 자동중단 실행 여부
            if (companyFlag) {
                // 보험사 검색 시 인증서 적용위해 라온 모듈 옵션 적용
                mEngine.setOptionsRaon(
                        true,
                        raonKey,
                        "4D6F62696C655472616E734B65793130",
                        false,
                        Engine.CipherAlgorism.SE_CIPHER_SEED_CBC,
                        Engine.CipherDataType.SE_CIPHER_TYPE_HEXSTRING);
            }
            mEngine.startEngine(); //엔진 기동
            mEngine.startJob(); //추가된 모듈리스트 조회
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 신정원 크롤링 데이터 세팅
     * 서버에서 내려오는 이름, 주민번호, 핸드폰번호, 통신사 값을 세팅
     */
    public void setSJWData(Map<String, String> parameter) {
        try {
            if (companyFlag) {
                companyFlag = false;
                errorFlag = false;
            }
            userName = parameter.get("name");
            userJumin = parameter.get("jumin");
            userTelNum = parameter.get("telNum");
            userTelCom = parameter.get("telCom");
            searchGubun = parameter.get("searchGbn");
            s4Code = parameter.get("s4Code");
            m6Code = parameter.get("m6Code");

            HashMap<String, String> moduleInfo = new HashMap<String, String>(); // 실행 할 모듈정보
            HashMap<String, String> loginInfo = new HashMap<String, String>(); // Login 정보 Parameter
            HashMap<String, String> paramInfo = new HashMap<String, String>(); // 스크래핑에 필요한 추가정보 parameter
            HashMap<String, String> paramExInfo = new HashMap<String, String>(); // 스크래핑에 필요한 추가정보 parameter2
            HashMap<String, HashMap<String, String>> executeModule = new HashMap<String, HashMap<String, String>>(); // 엔진 실행에 필요한 모든 정보

            /**
             * 서비스 명세서를 확인 하여 모듈 정보를 세팅 합니다.
             * O2Code - 기관코드 : IS(Insurance - 보험)
             * S4Code - 보험사 구분코드 : 0001 - 신정원, 0101 - 메리츠화재 등등 (e-spider 엔진명세서 참조)
             * M6Code - 서비스코드 : 0100010 - 인증서인증, 012110 - 내보험다보여(주민번호 + 인증서 + 휴대폰인증 + 캡챠이미지), 012120 - 내보험찾아줌
             */
            // 모듈 정보
            moduleInfo.put(Engine.ENGINE_JOB_COUNTRY_KEY, "KR"); //국가코드
            moduleInfo.put(Engine.ENGINE_JOB_ORGANIZATION_KEY, "IS"); //O2CODE
            moduleInfo.put(Engine.ENGINE_JOB_SUBORGANIZATION_KEY, s4Code); //S4CODE - 유동적으로 변경됨
            moduleInfo.put(Engine.ENGINE_JOB_MODULECODE_KEY, m6Code); //M6CODE - 유동적으로 변경됨

            /**
             * 서비스 명세서를 확인하여 필요한 로그인 정보를 세팅 합니다.
             */
            // 로그인 정보
            loginInfo.put("reqUserName", userName);
            loginInfo.put("reqIdentity", userJumin);
            loginInfo.put("reqTelecom", userTelCom);		// 0 SKT, 1 KT, 2 LGU+, 3 알뜰폰(SKT), 4 알뜰폰(KT), 5 알뜰폰(LGU)
            loginInfo.put("reqPhoneNo", userTelNum);
            loginInfo.put("reqSearchGbn", searchGubun);     // 0 - 휴대폰인증, 1 - 신용카드인증, 2 - 인증서인증
            loginInfo.put("reqTimeOut", "170"); // 보안숫자/SMS 제한시간, 단위 초,  (미입력시 기본값 사용 최대 사이트 제한시간 미만 적용) default :60s (최소시간 : 30s , 최대시간 : 170s)

            /**
             * 서비스 명세서를 확인하여 필요한 파라미터 정보를 세팅 합니다.
             */
            // 모듈 실행될 전체 데이터
            executeModule.put(Engine.ENGINE_JOB_MODULE_KEY, moduleInfo);
            executeModule.put(Engine.ENGINE_JOB_PARAM_LOGIN_KEY, loginInfo);
            executeModule.put(Engine.ENGINE_JOB_PARAM_INFO_KEY, paramInfo);
            executeModule.put(Engine.ENGINE_JOB_PARAMEXT_INFO_KEY, paramExInfo);

            if (mJobResultJsonList != null) {
                mJobResultJsonList = new ArrayList<String>();
                mJobList = new ArrayList<HashMap<String, HashMap<String, String>>>();
            }

            if (s4Code.equals("0200")) {
                mProgressNameListData.add("내보험찾아줌");
            } else {
                mProgressNameListData.add("보험다보여");
            }
            mJobResultJsonList.add(new JSONObject().toString());
            mJobList.add(executeModule);
            startEngine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setInsureData(JSONArray insureArray) {
        try {
            int insureS4Code;
            String strS4Code;
            companyFlag = true;
            cipherStrPref = engineContext.getSharedPreferences("cipherData", MODE_PRIVATE);
            raonKey = cipherStrPref.getString("CipherString", "");
            plainKey = cipherStrPref.getString("plainPwd", "");
            if (mJobResultJsonList != null) {
                mJobResultJsonList = new ArrayList<String>();
                mJobList = new ArrayList<HashMap<String, HashMap<String, String>>>();
            }
            JSONObject bohumObj = new JSONObject();
            for (int i = 0; i < insureArray.length(); i++) {
                if (insureArray.getJSONObject(i).getString("s4code") != null && !insureArray.getJSONObject(i).getString("s4code").equals("")) {
                    insureS4Code = Integer.parseInt(insureArray.getJSONObject(i).getString("s4code"));
                    strS4Code = insureArray.getJSONObject(i).getString("s4code");
                    if (userJumin == null) {
                        userJumin = insureArray.getJSONObject(i).getString("");
                    }
                    JSONObject tempJson = insureArray.getJSONObject(i);
                    switch (insureS4Code) {
                        case 203:
                            /** ============== 삼성생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", tempJson.getString("reqUserId"));
                            bohumObj.put("reqUserPass", tempJson.getString("reqUserPass"));
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", tempJson.getString("reqAddress"));
                            bohumObj.put("disp_name", "보험사조회 - 삼성생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 234:
                            /** ============== 미래에셋생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", tempJson.getString("reqUserId"));
                            bohumObj.put("reqUserPass", tempJson.getString("reqUserPass"));
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", tempJson.getString("reqTelCom"));
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 미래에셋생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 201:
                            /** ============== 한화생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", tempJson.getString("reqUserId"));
                            bohumObj.put("reqUserPass", tempJson.getString("reqUserPass"));
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 한화생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 152:
                            /** ============== 더케이손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", tempJson.getString("reqAddress"));
                            bohumObj.put("disp_name", "보험사조회 - 더케이손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 193:
                            /** ============== BNP파리바카디프손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", tempJson.getString("reqUserPass"));
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - BNP파리바카디프손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 218:
                            /** ============== KB생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", tempJson.getString("reqUserPass"));
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - KB생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 233:
                            /** ============== KDB생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", tempJson.getString("reqTelCom"));
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - KDB생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 104:
                            /** ============== MG손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - MG손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 105:
                            /** ============== 흥국화재 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 흥국화재"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 108:
                            /** ============== 삼성화재 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 삼성화재"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 110:
                            /** ============== KB손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - KB손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 111:
                            /** ============== DB손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - DB손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 151:
                            /** ============== AIG손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", tempJson.getString("reqPhoneNo"));
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", tempJson.getString("reqEmail"));
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - AIG손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 101:
                            /** ============== 메리츠화재 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 메리츠화재"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 102:
                            /** ============== 한화손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 한화손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 103:
                            /** ============== 롯데손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 롯데손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 109:
                            /** ============== 현대해상 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 현대해상"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 112:
                            /** ============== AXA손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - AXA손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 171:
                            /** ============== NH농협손해보험 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - NH농협손해보험"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 194:
                            /** ============== 처브손보 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 처브손보"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 202:
                            /** ============== ABL생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - ABL생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 205:
                            /** ============== 교보생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 교보생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 211:
                            /** ============== 신한생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 신한생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 217:
                            /** ============== 현대라이프생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 현대라이프생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 231:
                            /** ============== DGB생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - DGB생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 242:
                            /** ============== 농협생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 농협생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 243:
                            /** ============== 교보라이프플래닛 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 교보라이프플래닛"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 251:
                            /** ============== 라이나생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 라이나생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 261:
                            /** ============== 푸르덴셜생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 푸르덴셜생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 262:
                            /** ============== 오렌지라이프 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 오렌지라이프"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 263:
                            /** ============== 하나생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 하나생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 272:
                            /** ============== 매트라이트생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 매트라이트생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 274:
                            /** ============== 동양생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 동양생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 277:
                            /** ============== 처브라이프생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 처브라이프생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 291:
                            /** ============== IBK연금보험 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - IBK연금보험"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 501:
                            /** ============== 우체국보험 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 우체국보험"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 204:
                            /** ============== 흥국생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - 흥국생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 252:
                            /** ============== AIA생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - AIA생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 271:
                            /** ============== DB생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - DB생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 278:
                            /** ============== BNP파리바카디프생명 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - BNP파리바카디프생명"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                        case 502:
                            /** ============== MG새마을금고 ============== */
                            bohumObj.put("s4code", strS4Code); //S4CODE
                            bohumObj.put("reqUserId", "");
                            bohumObj.put("reqUserPass", "");
                            bohumObj.put("reqPhoneNo", "");
                            bohumObj.put("reqTelCom", "");
                            bohumObj.put("reqEmail", "");
                            bohumObj.put("reqAddress", "");
                            bohumObj.put("disp_name", "보험사조회 - MG새마을금고"); // 현재 스크래핑 될 모듈 정보
                            exeInsureEngine(bohumObj);
                            break;
                    }
                }
            }
            startEngine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exeInsureEngine(JSONObject insureObj) {
        try {
            Log.d("HeenamEngine", "넘어온 값 = " + insureObj);
            HashMap<String, String> moduleInfo = new HashMap<String, String>(); // 실행 할 모듈정보
            HashMap<String, String> loginInfo = new HashMap<String, String>(); // Login 정보 Parameter
            HashMap<String, String> paramInfo = new HashMap<String, String>(); // 스크래핑에 필요한 추가정보 parameter
            HashMap<String, String> paramExInfo = new HashMap<String, String>(); //업무에 필요한 추가정보 Parameter2
            HashMap<String, HashMap<String, String>> executeModule = new HashMap<String, HashMap<String, String>>(); // 엔진 실행에 필요한 모든 정보

            /**
             * 서비스 명세서를 확인하여 필요한 로그인 정보를 세팅 합니다.
             */
            // 로그인 정보
            keyPref = engineContext.getSharedPreferences("keyValue", MODE_PRIVATE);
            mCertPath = keyPref.getString("CertPath", "");
            mKeyPath = keyPref.getString("KeyPath", "");
            Log.d("engineValue", "인증서 .der 값 = " + mCertPath);
            Log.d("engineValue", "인증서 .key 값 = " + mKeyPath);
            loginInfo.put("reqCertFile", mCertPath); //조회할 인증서 signCert.der파일경로
            loginInfo.put("reqKeyFile", mKeyPath); //조회할 인증서 signPri.key파일경로
            loginInfo.put("reqCertPass", plainKey); //인증서 패스워드
            loginInfo.put("reqIdentity", mIdentify_No); //주민등록번호

            // 모듈 정보
            moduleInfo.put(Engine.ENGINE_JOB_COUNTRY_KEY, "KR"); //국가코드
            moduleInfo.put(Engine.ENGINE_JOB_ORGANIZATION_KEY, "IS"); //O2CODE
            moduleInfo.put(Engine.ENGINE_JOB_MODULECODE_KEY, "010010"); //M6CODE
            moduleInfo.put(Engine.ENGINE_JOB_SUBORGANIZATION_KEY, insureObj.getString("s4code"));

            if (getJsonString(insureObj, "reqUserId") != null) {
                paramInfo.put("reqUserId", insureObj.getString("reqUserId"));
            } else {
                paramInfo.put("reqUserId", "");
            }
            if (getJsonString(insureObj, "reqUserPass") != null) {
                paramInfo.put("reqUserPass", insureObj.getString("reqUserPass"));
            } else {
                paramInfo.put("reqUserPass", "");
            }
            if (getJsonString(insureObj, "reqPhoneNo") != null) {
                paramInfo.put("reqPhoneNo", insureObj.getString("reqPhoneNo"));
            } else {
                paramInfo.put("reqPhoneNo", "");
            }
            if (getJsonString(insureObj, "reqTelecom") != null) {
                paramInfo.put("reqTelecom", insureObj.getString("reqTelecom"));
            } else {
                paramInfo.put("reqTelecom", "");
            }
            if (getJsonString(insureObj, "reqEmail") != null) {
                paramInfo.put("reqEmail", insureObj.getString("reqEmail"));
            } else {
                paramInfo.put("reqEmail", "");
            }
            if (getJsonString(insureObj, "reqAddress") != null) {
                paramInfo.put("reqAddress", insureObj.getString("reqAddress"));
            } else {
                paramInfo.put("reqAddress", "");
            }

            // 모듈 실행될 전체 데이터
            executeModule.put(Engine.ENGINE_JOB_MODULE_KEY, moduleInfo);
            executeModule.put(Engine.ENGINE_JOB_PARAM_LOGIN_KEY, loginInfo);
            executeModule.put(Engine.ENGINE_JOB_PARAM_INFO_KEY, paramInfo);
            executeModule.put(Engine.ENGINE_JOB_PARAMEXT_INFO_KEY, paramExInfo);

            mJobResultJsonList.add(new JSONObject().toString());
            mJobList.add(executeModule); //모듈 실행리스트에 추가

            mProgressNameListData.add(insureObj.getString("disp_name")); // 현재 스크래핑 될 모듈 정보
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 엔진에 실행중인 모듈List의 갯수를 엔진에 전달하는 함수.
     */
    @Override
    public int numberOfJobInEngine(Engine engine) {
        Log.i(this.getClass().getName(), "job 갯수 - " + mJobList.size());
        return mJobList.size();
    }

    /**
     * 실행할 모듈정보(moduleInfo)를 엔진에 전달하는 Callback 함수
     */
    @Override
    public HashMap<String, String> engineGetJob(Engine engine, int jobIndex) {
        Log.i(this.getClass().getName(), "engineGetJob jobIndex[" + String.valueOf(jobIndex) + "]");

        if (mJobList.size() <= jobIndex)
            return null;

        HashMap<String, HashMap<String, String>> element = mJobList.get(jobIndex);
        if (element.containsKey(Engine.ENGINE_JOB_MODULE_KEY)) {
            return element.get(Engine.ENGINE_JOB_MODULE_KEY);
        }

        return null;
    }

    /**
     * 실행할 로그인정보(loginInfo) 및 파라메터(paramInfo)를 엔진에 전달하는 Callback 함수
     */
    @Override
    public String engineGetParam(Engine engine, int threadIndex, int jobIndex, String requireJSONString, boolean bSynchronous) {
        Log.i(this.getClass().getName(), "engineGetParam threadIdx[" + String.valueOf(threadIndex) + "] jobIndex[" + String.valueOf(jobIndex)
                + "] requireJSONString[" + requireJSONString + "] bSynchronous [" + String.valueOf(bSynchronous) + "]");

        try {
            JSONObject requireJson = new JSONObject(requireJSONString);

            if (bSynchronous) {

                if (requireJson.has(Engine.ENGINE_JOB_PARAM_LOGIN_KEY)) { //로그인정보(loginInfo) 엔진에 전달
                    JSONObject reqJobItem = (JSONObject) requireJson.get(Engine.ENGINE_JOB_PARAM_LOGIN_KEY);
                    HashMap<String, String> jobSourceItem = (HashMap<String, String>) ((HashMap<String, HashMap<String, String>>) mJobList.get(jobIndex))
                            .get(Engine.ENGINE_JOB_PARAM_LOGIN_KEY);

                    Iterator<String> itr = jobSourceItem.keySet().iterator();
                    while (itr.hasNext()) {
                        String key = (String) itr.next();
                        reqJobItem.put(key, jobSourceItem.get(key));
                    }
                }

                if (requireJson.has(Engine.ENGINE_JOB_PARAM_INFO_KEY)) { //파라메터(paramInfo) 엔진에 전달
                    JSONObject reqJobItem = (JSONObject) requireJson.get(Engine.ENGINE_JOB_PARAM_INFO_KEY);
                    HashMap<String, String> jobSourceItem = (HashMap<String, String>) ((HashMap<String, HashMap<String, String>>) mJobList.get(jobIndex))
                            .get(Engine.ENGINE_JOB_PARAM_INFO_KEY);

                    Iterator<String> itr = jobSourceItem.keySet().iterator();
                    while (itr.hasNext()) {
                        String key = (String) itr.next();
                        reqJobItem.put(key, jobSourceItem.get(key));
                    }
                }

                if (requireJson.has(Engine.ENGINE_JOB_PARAMEXT_INFO_KEY)) { //파라메터(paramInfo) 엔진에 전달
                    JSONObject reqJobItem = (JSONObject) requireJson.get(Engine.ENGINE_JOB_PARAMEXT_INFO_KEY);
                    HashMap<String, String> jobSourceItem = (HashMap<String, String>) ((HashMap<String, HashMap<String, String>>) mJobList.get(jobIndex))
                            .get(Engine.ENGINE_JOB_PARAMEXT_INFO_KEY);

                    Iterator<String> itr = jobSourceItem.keySet().iterator();
                    while (itr.hasNext()) {
                        String key = (String) itr.next();
                        reqJobItem.put(key, jobSourceItem.get(key));
                    }
                }

                String retString = requireJson.toString();
                Log.d(this.getClass().getName(), "requireJson :" + requireJson.toString());
                return retString;
            } else {
                //ParamEx
                if (requireJson.has(Engine.ENGINE_JOB_PARAMEXT_INFO_KEY)) {
                    JSONObject reqJobItem = requireJson.getJSONObject(Engine.ENGINE_JOB_PARAMEXT_INFO_KEY);
                    //2Way로 처리 해야 하는 파라미터를 추가적으로 진행합니다. (Alert형태로 진행하였음.)
                     threadCount = threadIndex;
                     jobCount = jobIndex;
                    getParam2Way(reqJobItem, threadIndex, jobIndex);
                } else {
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //2Way방식으로 진행되는 화면을 진행한다.
    private void getParam2Way(JSONObject reqJobItem, int threadIndex, int jobIndex) {
        try {
            if (reqJobItem.has("reqSecureNo")) {//보안문자 화면을 그리도록 해보자
                Log.i("heenam engine", "캡챠이미지 - " + reqJobItem.getString("reqSecureNo"));
                JSONObject resultJson = new JSONObject();
                resultJson.put("resultCode", "1001");
                JSONObject jsonData = new JSONObject();
                jsonData.put("threadIndex", String.valueOf(threadIndex));
                jsonData.put("jobIndex", String.valueOf(jobIndex));
                jsonData.put("data", reqJobItem.getString("reqSecureNo"));
                resultJson.put("resultData", jsonData);
                extendInterface.exWNSendData(resultJson);
            } else if (reqJobItem.has("reqSMSAuthNo")) { //sms인증번호를 넣어주자.
                JSONObject resultJson = new JSONObject();
                resultJson.put("resultCode", "1100");
                JSONObject jsonData = new JSONObject();
                jsonData.put("threadIndex", String.valueOf(threadIndex));
                jsonData.put("jobIndex", String.valueOf(jobIndex));
                jsonData.put("data", "");
                resultJson.put("resultData", jsonData);
                extendInterface.exWNSendData(resultJson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 엔진이 모듈을 실행 후 결과를 가져올 때 호출됩니다.(각 조회된 데이터를 처리합니다.)
     */
    @Override
    public void engineResult(Engine engine, int threadIndex, final int jobIndex, int error, String userError, final String errorMessage, String resultJsonString) {
        Log.i(this.getClass().getName(), "engineResult threadIdx[" + String.valueOf(threadIndex) + "] jobIndex[" + String.valueOf(jobIndex) + "] error["
                + String.valueOf(error & 0xFFFF) + "] userError[" + userError + "] errorMessage [" + errorMessage + "] resultJsonString [" + resultJsonString
                + "]");
        String s4code = mJobList.get(jobIndex).get("module").get("suborganization");
        // Error 처리
        if (error != 0) { // Error == 0 이면 정상, 아니면 Error처리 합니다.
            if (!errorMessage.equals("")) { //Error 메세지가 공백으로 내려왔을 경우
                mJobResultJsonList.set(jobIndex, "[" + mProgressNameListData.get(jobIndex) + "]\n[" + errorMessage + "]\n\n"); //모듈 실행중 에러가 발생했을 시 내려온 에러 메세지를 Array에 담습니다. ( 정상처리된 모듈들의 조회 내용과 함께 표시하기 위해 )
                if (!companyFlag) {
                    sendError(threadIndex, jobIndex, error, errorMessage);
                } else {
                    makeError(threadIndex, s4code,jobIndex, error, errorMessage);
                }
            } else { //Error 메세지가 내려왔을 경우
                mJobResultJsonList.set(jobIndex, "[" + mProgressNameListData.get(jobIndex) + "]\n[" + "errorCode =" + String.valueOf(error & 0xFFFF) + "]\n\n"); //에러 일 때 내려온 에러메세지가 없다면, 에러 코드를 Array에 담습니다.( 정상처리된 모듈들의 조회 내용과 함께 표시하기 위해 )
                if (!companyFlag) {
                    sendError(threadIndex, jobIndex, error, "");
                } else {
                    makeError(threadIndex, s4code,jobIndex, error, "");
                }
            }
        } else {
            mJobResultJsonList.set(jobIndex, resultJsonString);//모듈이 에러없이 정상 처리되었을 때의 데이터를 조회결과Array에 담습니다.
            try {
                JSONObject resultJson = new JSONObject();
                JSONObject resultData = new JSONObject();
                resultData.put("threadIndex", threadIndex);
                resultData.put("jobIndex", jobIndex);
                if (companyFlag) {
                    resultData.put("errorCode", "0");
                    resultData.put("s4code", s4code);
                    resultData.put("os", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UNIQUE_ID));
                    resultData.put("deviceID", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UUID));
                    resultData.put("data", resultJsonString);
                } else {
                    resultJson.put("resultCode", "0000");
                    JSONObject resultData1 = new JSONObject();
                    resultData1.put("name", userName);
                    resultData1.put("telNum", userTelNum);
                    resultData1.put("os", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UNIQUE_ID));
                    resultData1.put("deviceID", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UUID));
                    resultData1.put("jsonResult", resultJsonString);
                    resultData.put("data", resultData1);
                }
                resultJson.put("resultData", resultData);
                if (!companyFlag) {
                    extendInterface.exWNSendData(resultJson);
//                    mEngine.stopEngine();
                } else {
                    bohumResult(resultData, jobIndex);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void bohumResult(JSONObject jsonObject, int jobIdx) {
        try {
            bohumResultArr.put(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void makeError(int threadIndex, String s4Code, int jobIndex, int error, final String errorMessage) {
        try {
            JSONObject errorData = new JSONObject();
            errorData.put("threadIndex", threadIndex);
            errorData.put("jobIndex", jobIndex);
            errorData.put("s4code", s4Code);
            errorData.put("errorCode", String.valueOf(error & 0xFFFF));
            errorData.put("os", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UNIQUE_ID));
            errorData.put("deviceID", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UUID));
            JSONObject errorBody = new JSONObject();
            errorBody.put("errorMessage", errorMessage);
            errorData.put("data", errorBody);
            errorFlag = true;
            bohumResult(errorData, jobIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendError(int threadIndex, int jobIndex, int error, final String errorMessage) {
        try {
            JSONObject engineError = new JSONObject();
            engineError.put("resultCode", "9000");
            JSONObject errorData = new JSONObject();
            errorData.put("threadIndex", threadIndex);
            errorData.put("jobIndex", jobIndex);
            JSONObject errorDetail = new JSONObject();
            errorDetail.put("name", userName);
            errorDetail.put("telNum", userTelNum);
            errorDetail.put("os", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UNIQUE_ID));
            errorDetail.put("deviceID", mEngine.getDeviceInfo(Engine.ENGINE_DEVICE_UUID));
            errorDetail.put("errorCode", String.valueOf(error & 0xFFFF));
            errorDetail.put("errorMessage", errorMessage);
            errorData.put("data", errorDetail);
            engineError.put("resultData", errorData);
            extendInterface.exWNSendData(engineError);
//            mEngine.stopEngine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 엔진시스템의 Error가 발생시호출됩니다.
     */
    @Override
    public void engineSystemError(Engine engine, int error, String errorMsg) {
        Log.i(this.getClass().getName(), "engineSystemError error[" + String.valueOf(error & 0xFFFF) + "] errormessage [" + errorMsg + "]");
    }

    /**
     * 엔진의 상태를 가져옵니다. (status 값이 0 이면 모든 작업이 완료상태)
     */
    @Override
    public void engineStatus(Engine engine, int status) {
        Log.i(this.getClass().getSimpleName(), "엔진 상태 - " + status);
        try {
            if (status == 0) {
                if (companyFlag) {
                    bohumJsonResult.put("resultCode", "0000");
                    bohumJsonResult.put("resultData", bohumResultArr);
                    extendInterface.exWNSendData(bohumJsonResult);
//                    engine.stopEngine();
                } else {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 실행중인 모듈의 진행중인 상태를 status로 확인할 수 있습니다.(시작, 진행중, 완료 등등)
     */
    @Override
    public void engineJobStatus(Engine engine, int threadIndex, final int jobIndex, final int status) {
        Log.i(this.getClass().getName(), "engineJobStatus threadIdx[" + String.valueOf(threadIndex) + "] jobIndex[" + String.valueOf(jobIndex) + "] status["
                + String.valueOf(status) + "]");
    }

    /**
     * 실행중인 모듈의 진행상태를 percent로 확인할 수 있습니다.(Progress를 처리 가능.)
     */
    @Override
    public void engineJobPercent(Engine engine, int threadIndex, int jobIndex, final int percent) {
        Log.i(this.getClass().getName(), "engineJobPercent threadIdx[" + String.valueOf(threadIndex) + "] jobIndex[" + String.valueOf(jobIndex) + "] percent["
                + String.valueOf(percent) + "]");
    }

    public void additionParam(JSONObject jsonParam) {
        try {
            JSONObject paramEx = new JSONObject();
            jobCode = jsonParam.getString("jobCode");
            switch (jobCode) {
                case "1002":
                    paramEx.put("reqSecureNo", "");
                    paramEx.put("reqSecureNoRefresh", "1");
                    break;
                case "1001":
                    String secureNo = jsonParam.getString("reqSecureNo");
                    paramEx.put("reqSecureNo", secureNo);
                    paramEx.put("reqSecureNoRefresh", "0");
                    break;
                case "1100":
                    String smsAuthNo = jsonParam.getString("reqSMSAuthNo");
                    paramEx.put("reqSMSAuthNo", smsAuthNo);
                    break;
            }
            JSONObject reqJobItem = new JSONObject();
            reqJobItem.put(Engine.ENGINE_JOB_PARAMEXT_INFO_KEY, paramEx);
            mEngine.setParamData(Integer.parseInt(jsonParam.getString("threadIndex")), Integer.parseInt(jsonParam.getString("jobIndex")), reqJobItem.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJsonString(JSONObject json, String key) {
        String outValue = null;
        if (json.has(key)) {
            try {
                outValue = json.getString(key);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return outValue;
    }
}
