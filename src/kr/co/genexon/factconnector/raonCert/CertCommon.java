package kr.co.genexon.factconnector.raonCert;

import com.raonsecure.ksw.RSKSWLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CertCommon {

    private static String sid = "";
    private static String serverCert = "";

    public static String baseUrl;
    public static String sidUrl;
    public static String certUrl;
    public static String encUrl;
    public static String encUrl1;
    public static String signUrl, simpleSignUrl;
    public static String signEncUrl;

    // 0: 가상키패드 연동안함, 1: 가상키패드에서 복호화, 2: Key# wireless에서 복호화
    private static int withMTK = 2;
    // 0: 기존 인증서 프로세스, 1: 바이오 인증서 프로세스
    private static int withBio = 0;

    public static void setBaseUrl(String ip, String port) {
        if (ip.equals("") || ip == null || ip.length() == 0) {
            ip = "ksbiz.raonsecure.com"; // 지넥슨향 인증서 서버로 교체(ip_address, domain 모두 가능)
//			ip = "10.0.4.80";
        }

        if (port.equals("") || port == null || port.length() == 0) {
            port = "8080"; // 변경 필요 (지넥슨 향)
        }
        baseUrl = "https://" + ip + ":" + port;
        sidUrl = baseUrl + "/ksbiz/sid.jsp";
        certUrl = baseUrl + "/ksbiz/servercert.jsp";
        encUrl = baseUrl + "/kshybrid/encResult_cs.jsp";
        encUrl1 = baseUrl + "/kshybrid/encResultJson_cs.jsp";
        signUrl = baseUrl + "/kshybrid/signAction_cs.jsp";
        simpleSignUrl = baseUrl + "/kshybrid/simpleSignAction_cs.jsp";
        signEncUrl = baseUrl + "/kshybrid/signEncAction_cs.jsp";
    }

    public static void setWithMTK(int withMTK) {
        CertCommon.withMTK = withMTK;
    }

    public static int getWithMTK() {
        return CertCommon.withMTK;
    }

    public static void setWithBio(int withBio) {
        CertCommon.withBio = withBio;
    }
    public static int getWithBio() {
        return CertCommon.withBio;
    }

    public static void setE2(String e2) {

        // 2017.11.10 바이오인증서 관련 변경
        File file = new File("/data/data/com.raonsecure.kswireless2cs/cache/file_e2.txt");
        FileWriter fw = null;
        try{
            fw = new FileWriter(file);
            fw.write(e2);
            fw.close() ;
        } catch (Exception e) {
            RSKSWLog.e("raon", "e2 when issued:" + "creating e2 on a file is failed.");
            e.printStackTrace();
        }
    }

    public static String getE2(){

        // 2017.11.10 바이오인증서 관련 변경
        File file = new File("/data/data/com.raonsecure.kswireless2cs/cache/file_e2.txt");
        FileReader fr = null;
        BufferedReader br = null;
        String e2 = null;

        try{
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            e2 = br.readLine();
            br.close();
            fr.close();
        } catch (Exception e) {
            RSKSWLog.e("raon", "e2 when signed:" + "getting e2 on a file is failed.");
            e.printStackTrace();
        }

        return e2;
    }
}
