package kr.co.genexon.factconnector.raonCert;

import com.raonsecure.ksw.RSKSWCertificate;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class CertificationItem extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;
    public static final String POLICY = "policy";
    public static final String SUBJECTNAME = "subjectName";
    public static final String EXPIREDTIME = "expiredTime";
    public static final String ISSUERNAME = "issuerName";
    public static final String EXPIREDIMG = "expiredImg";
    public RSKSWCertificate userCert;
    public String userCertPath;
    boolean selectable = true;

    public CertificationItem(RSKSWCertificate userCert)
            throws UnsupportedEncodingException {
        this.userCert = userCert;
        this.put(POLICY, "구분 : " + userCert.getPolicy());
        this.put(SUBJECTNAME, userCert.getSubjectDn());
        this.put(EXPIREDTIME, "만료일 : " + userCert.getNotAfterDate());
        this.put(ISSUERNAME, "발급자 : " + userCert.getIssuerOrg());
        int expiredT = userCert.isExpired();
        this.put(EXPIREDIMG, Integer.valueOf(expiredT));
    }

    public RSKSWCertificate getCertItem() {
        return userCert;
    }
}
