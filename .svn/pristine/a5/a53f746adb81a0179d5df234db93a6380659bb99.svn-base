var jobCD;
var jobIdx;
var threadIdx;

M.onReady(function(e) {
    registPush();
});

M.onHide(function(e) { });

M.onRestore(function(e) { });

function checkFirst() {
    M.execute("exWNCheckFirst", {
        TEXT: "안녕하세요",
        CALLBACK: M.response.on( function( result ) {
            console.log(JSON.stringify(result));
        }).toString()
    });
}

function getInsureData() {
    var user_name = document.getElementById("userName").value;
    var user_birth = document.getElementById("userBirth").value;
    var user_idNo = document.getElementById("userIdNo").value;
    var user_fId = user_birth.concat(user_idNo);
    var telcom_target = document.getElementById("selTelCom");
    var user_telNum = document.getElementById("userTelNum").value;
    console.log(user_name);
    console.log(user_birth);
    console.log(user_idNo);
    console.log(user_fId);
    console.log(user_telNum);
    console.log(telcom_target.options[telcom_target.selectedIndex].value);
    M.execute("exWNgetInsureData", {
        name: user_name,
        jumin: user_fId,
        telCom: telcom_target.options[telcom_target.selectedIndex].value,
        telNum: user_telNum,
        searchGubun: "0",
        s4Code: "0001",
        m6Code: "012110",
        CALLBACK: M.response.on(function( result ) {
            console.log(JSON.stringify(result));
            jobCD = result.resultCode;
            var jsonData = result.resultData;
            threadIdx = jsonData.threadIndex;
            jobIdx = jsonData.jobIndex;
            var detailData = jsonData.data;
            var error = detailData.errorCode;
            document.getElementById("captchaImg").src = detailData;
            if (error == '9000') {
                var errorData = jsonData.data;
                console.log(errorData.errorCode);
                alert(errorData.errorCode);
            }
        }).toString()
    });
}

function getInsureData1() {
    var user_name = document.getElementById("userName").value;
    var user_birth = document.getElementById("userBirth").value;
    var user_idNo = document.getElementById("userIdNo").value;
    var user_fId = user_birth.concat(user_idNo);
    var telcom_target = document.getElementById("selTelCom");
    var user_telNum = document.getElementById("userTelNum").value;
    console.log(user_name);
    console.log(user_birth);
    console.log(user_idNo);
    console.log(user_fId);
    console.log(user_telNum);
    console.log(telcom_target.options[telcom_target.selectedIndex].value);
    M.execute("exWNgetInsureData", {
        name: user_name,
        jumin: user_fId,
        telCom: telcom_target.options[telcom_target.selectedIndex].value,
        telNum: user_telNum,
        searchGubun: "0",
        s4Code: "0200",
        m6Code: "012120",
        CALLBACK: M.response.on(function( result ) {
            console.log(JSON.stringify(result));
            jobCD = result.resultCode;
            var jsonData = result.resultData;
            threadIdx = jsonData.threadIndex;
            jobIdx = jsonData.jobIndex;
            var detailData = jsonData.data;
            var error = detailData.errorCode;
            document.getElementById("captchaImg").src = detailData;
            if (error == '9000') {
                var errorData = jsonData.data;
                console.log(errorData.errorCode);
            }
        }).toString()
    });
}

function setExtInsureData() {
    M.execute("exWNsetExtParams", {
    threadIndex: threadIdx,
    jobIndex: jobIdx,
    jobCode: '1002',
    CALLBACK: M.response.on( function( result ) {
        console.log(JSON.stringify(result));
        jobCD = result.resultCode;
        var jsonData = result.resultData;
        threadIdx = jsonData.threadIndex;
        jobIdx = jsonData.jobIndex;
        if (jobCD == '1001') {
            document.getElementById("captchaImg").src = jsonData.data;
        } else {
            var errorData = jsonData.data;
            console.log(errorData.errorMsg);
            console.log(errorData.errorCode);
        }
        }).toString()
    });
}

function setExtInsureData1() {
    M.execute("exWNsetExtParams", {
        threadIndex: threadIdx,
        jobIndex: jobIdx,
        jobCode: '1001',
        data: document.getElementById("captchaCD").value,
        CALLBACK: M.response.on( function( result ) {
            console.log(JSON.stringify(result));
            jobCD = result.resultCode;
            var jsonData = result.resultData;
            threadIdx = jsonData.threadIndex;
            jobIdx = jsonData.jobIndex;
            if (jobCD == '9001') {
                var errorData = jsonData.data;
                console.log(errorData.errorMsg);
                console.log(errorData.errorCode);
            } else {
                if (jsonData.data != null) {
                    document.getElementById("captchaImg").src = jsonData.data;
                }
            }
        }).toString()
    });
}

function setExtInsureData2() {
    M.execute("exWNsetExtParams", {
        threadIndex: threadIdx,
        jobIndex: jobIdx,
        jobCode: '1100',
        data: document.getElementById("smsAuthCD").value,
        CALLBACK: M.response.on( function( result ) {
            console.log(JSON.stringify(result));
            jobCD = result.resultCode;
            var jsonData = result.resultData;
            threadIdx = jsonData.threadIndex;
            jobIdx = jsonData.jobIdx;
            var jsonData1 = jsonData.data;
            var name = jsonData1.name;
            var telNum = jsonData1.telNum;
            var os = jsonData1.os;
            var uuid = jsonData1.deviceID;
            if (jobCD == '0000') {
                console.log('이름 : ' + name);
                console.log('핸드폰번호 : ' + telNum);
                console.log('운영체제 : ' + os);
                console.log('UUID : ' + uuid);
                document.getElementById("resultText").value = jsonData1.jsonResult;
            } else {
                var errorData = jsonData.data;
                console.log(errorData.errorMsg);
                console.log(errorData.errorCode);
            }
        }).toString()
    });
}

function getInsureCompanyData() {
    M.execute("exWNgetInsureCompanyData", {
              data: [
//                     {"index":"0","reqUserId":"testman", "reqUserPass":"1111", "reqPhoneNo":"01011112222", "reqEmail":"test@test.com","reqAddress":"서울시 송파구 문정동 송파대로 16길 12"},
                     {"s4code":"0101", "reqEmail":"test@test.com", "reqPhoneNo":"01044447777", "":""},
                     {"s4code":"0261", "reqEmail":"test@test.com", "reqPhoneNo":"01044447777"},
                     {"s4code":"0103", "reqEmail":"test@test.com", "reqPhoneNo":"01044447777"},
                     {"s4code":"0104", "reqEmail":"test@test.com", "reqPhoneNo":"01044447777"}],
              CALLBACK: M.response.on(function(result){
                                      console.log(result);
                                      }).toString()
              });
}

function setName() {}

function setBirth() {}

function setIden() {}

function setTel() {}

function registPush() {
    var result = M.plugin('push').remote.isRegisteredService();
    console.log( JSON.stringify(result) );

    M.plugin('push').remote.registerServiceAndUser({
        cuid: "GNX01",
        name: "GNX01",
        callback: function(result) {
            if (result.status == "SUCCESS") {
                console.log("FC","푸쉬 등록이 완료되었습니다. ");
                /*alert("푸쉬 등록 완료했습니다. ");*/
            } else {
                console.log("FC","푸쉬 등록이 실패되었습니다.  > " + result.error );
                /*alert("푸쉬 등록이 실패되었습니다.  > " + result.error);*/
            }
        }
    });
}

function setSecurePwd(){
    M.execute("exWNgetLockPassword", {
              title:"잠금 비밀번호 설정",
              desc: "앱 실행 시 입력할 암호를 설정하세요.",
              option:"0",
              CALLBACK: M.response.on(function(result){
                                      console.log(JSON.stringify(result));
                                      }).toString()
              });
}

function getSecurePwd() {
    M.execute("exWNgetLockPassword", {
              title:"비밀번호 인증",
              desc: "앱 잠금 비밀번호를 입력하세요.",
              option:"1",
              CALLBACK: M.response.on(function(result){
                                      console.log(JSON.stringify(result));
                                      }).toString()
              });
}

function onReceiveAndroidNotification(result) {
    console.log(result);
}

function getCertList() {
    M.execute("exWNgetCertList", {
        CALLBACK: M.response.on(function(result){
                              console.log(JSON.stringify(result));
                              alert(JSON.stringify(result.resultData));
                              }).toString()
        });
}

function getimportCertNumber() {
    M.execute("exWNimportCertNumber", {
        url:"221.139.104.56",
        port:"10500",
        CALLBACK: M.response.on(function(result){
            console.log(JSON.stringify(result));
            if (result.resultCode == '0000') {
                document.getElementById("resultText").value = JSON.stringify(result.resultData);
            } else {
                alert(result.resultData);
            }
        }).toString()
    });
}

function getimportCert() {
    M.execute("exWNimportCert", {
        CALLBACK: M.response.on(function(result){
            if (result.resultCode == '0000') {
                console.log("저장되었습니다");
            } else {
                console.log(result.resultData);
                alert(result.resultData);
            }
        }).toString()
    });
}

function certCheckPasswd(){
    M.execute("exWNcertCheckPasswd", {
        index: 0,
        CALLBACK: M.response.on(function(result){
            console.log(JSON.stringify(result));
            if(result.resultCode == "0000") {
                alert("현재 인증서 비밀번호와 일치합니다.");
            } else if (result.resultCode == "1000") {
                alert("취소하였습니다");
            } else {
                if (result.resultData == '') {
                    alert("현재 인증서 비밀번호와 일치하지 않습니다.");
                } else {
                    alert(result.resultData);
                }
            }
        }).toString()
    });
}
function showSecureKeyPadEx(){
    M.execute("exWNShowSecureKeyPad",{
        title: "비밀번호",
        max_length:30,
        min_length:2,
        type:"A",
        placeholder: "테스트 문구입니다.",
        CALLBACK: M.response.on( function( result ) {
            console.log(JSON.stringify(result));
        }).toString()
    });
}

function showSecureNumKeypad() {
    M.execute("exWNShowSecureKeyPad", {
        title: "숫자키패드",
        max_length: 15,
        min_length: 2,
        type: "B",
        placeholder: "숫자를 입력해주세요",
        CALLBACK: M.response.on( function( result ) {
            var data = result.resultData;
            console.log(data.security_data);
            console.log(data.dummy_data);
        }).toString()
    });
}

function sendDeviceID() {
    M.execute("exWNSendDeviceID", {
        CALLBACK: M.response.on( function( result ) {
            console.log(JSON.stringify(result));
            if (result.resultCode == '0000') {
                alert(result.resultData);
            } else {
                alert('문제가 발생했습니다');
            }
        }).toString()
    })
}
