#ifndef __RSKSWX509_H__
#define __RSKSWX509_H__

#include "RSKSW_pkcs1.h"
#include "RSKSW_asntypes.h"

#ifdef	__cplusplus
extern "C" {
#endif

//
//	Version
//
typedef INTEGER Version;

Version 	*RSKSW_BIN_To_Version					(BIN *bin);
void		RSKSW_Version_Free 					(Version *version);

//
//	CertificateSerialNumber
//
typedef INTEGER CertificateSerialNumber;

CertificateSerialNumber 
			*RSKSW_BIN_To_CertificateSerialNumber	(BIN *bin);
void		RSKSW_CertificateSerialNumber_Free 	(CertificateSerialNumber *certificateSerialNumber);

//
//	Validity
//
typedef struct _Validity {
	Time	*notBefore;
	Time	*notAfter;
} Validity;

Validity	*RSKSW_BIN_To_Validity 				(BIN *bin);
void		RSKSW_Validity_Free					(Validity *validity);

//
//	AttributeType
//
typedef OBJECT_IDENTIFIER	AttributeType;

AttributeType	*RSKSW_BIN_To_AttributeType		(BIN *bin);
void		__RSKSW_AttributeType_New				(AttributeType *attributeType);

//
//	AttributeValue
//
typedef ASN_ANY 			AttributeValue;

AttributeValue *RSKSW_BIN_To_AttributeValue		(BIN *bin);
void		RSKSW_AttributeValue_Free				(AttributeValue *attributeValue);

//
//	AttributeTypeAndValue
//
typedef struct _AttributeTypeAndValue {
	AttributeType	*type;
	AttributeValue	*value;
} AttributeTypeAndValue;

AttributeTypeAndValue *RSKSW_BIN_To_AttributeTypeAndValue	(BIN *bin);
void		RSKSW_AttributeTypeAndValue_Free		(AttributeTypeAndValue *attributeTypeAndValue);

//
//	RelativeDistinguishedName
//
typedef struct _RelativeDistinguishedName {
	AttributeTypeAndValue	*attributeTypeAndValue;
} RelativeDistinguishedName;

RelativeDistinguishedName *RSKSW_BIN_To_RelativeDistinguishedName(BIN *bin);
void		__RSKSW_RelativeDistinguishedName_New	(RelativeDistinguishedName *relativeDistinguishedName);

//
//	RDNSequence
//
typedef struct _RDNSequence {
	RelativeDistinguishedName	*rdn;
	struct _RDNSequence 		*next;
} RDNSequence;

RDNSequence *RSKSW_BIN_To_RDNSequence				(BIN *bin);
void		__RSKSW_RDNSequence_New 				(RDNSequence *rdnSequence);

//
//	Name
//
typedef struct _Name {
	BIN 		*bin;
	RDNSequence *rdnSequence;
} Name;
	
Name		*RSKSW_BIN_To_Name 					(BIN *bin);
void		RSKSW_Name_Free						(Name *name);

//
//	SubjectPublicKeyInfo
//
typedef struct _SubjectPublicKeyInfo {
	BIN 				*bin;
	AlgorithmIdentifier *algorithm;
	BIT_STRING			*subjectPublicKey;
} SubjectPublicKeyInfo;

SubjectPublicKeyInfo 
			*RSKSW_BIN_To_SubjectPublicKeyInfo 	(BIN *bin);
void		RSKSW_SubjectPublicKeyInfo_Free		(SubjectPublicKeyInfo *subjectPublicKeyInfo);

//
//	UniqueIdentifier
//
typedef BIT_STRING	KSUniId;

KSUniId
			*RSKSW_BIN_To_UniId (BIN *bin);
void		RSKSW_UniId_Free			(KSUniId *uniId);

//
//	Extensions
//

typedef struct _Extension {
    BIN        objID;
    int      critical;  
    BIN         extnValue;
} Extension;

typedef struct _Extensions {
	struct _Extensions *next;
    Extension extension;
} Extensions;

//typedef BIN Extensions;

Extensions	*RSKSW_BIN_To_Extensions				(BIN *bin);
void		RSKSW_Extensions_Free					(Extensions *extensions);


//
//	TBSCertificate
//
typedef struct _TBSCertificate {
	Version 				*version;
	CertificateSerialNumber *serialNumber;
	AlgorithmIdentifier 	*signature;
	Name					*issuer;
	Validity				*validity;
	Name					*subject;
	SubjectPublicKeyInfo	*subjectPublicKeyInfo;
	KSUniId		*issuerUniqueID;	 // optional, v2 or v3
	KSUniId		*subjectUniqueID;	 // optional, v2 or v3
	Extensions				*extensions;		 // optional, v3
} TBSCertificate;

TBSCertificate *RSKSW_BIN_To_TBSCertificate		(BIN *bin);
void		__RSKSW_TBSCertificate_Free				(TBSCertificate *tbsCertificate);

//
//	X509Certificate
//
typedef struct _X509Certificate {
	TBSCertificate		*tbsCertificate;
	AlgorithmIdentifier *signatureAlgorithm;
	BIT_STRING			*signature;
	BIN 				*binFingerPrint;
} X509Certificate;
X509Certificate *RSKSW_Data_To_X509Certificate(ks_uint8 *cert, int certLen);
X509Certificate *RSKSW_BIN_To_X509Certificate		(BIN *bin);
X509Certificate *RSKSW_PEM_To_X509Certificate		(char *pem);
void		__RSKSW_X509Certificate_Free 			(X509Certificate *x509Certificate);
int			RSKSW_getCertificate_TBSCertificate	(ks_uint8*out, BIN *bin);
//
//	util
//
void		__RSKSW_X509Certificate_Print			(X509Certificate *x509Certificate, int tab_width);

char		*RSKSW_getCertificate_Serial_Number	(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Subject			(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Subject_Email	(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Subject_ID		(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Subject_Name 	(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Issuer			(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Issuer_Name		(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Valid_From		(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Valid_To 		(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Validity 		(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Finger_Print 	(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Subject_Locality            (X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Subject_StateOrProvinceName (X509Certificate *x509Certificate);
   
//char	   *RSKSW_getCertificate_Extension		(X509Certificate *x509Certificate);
//added by solarsun 2005 08.14
char        *RSKSW_getCertificate_Usage(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Policy			(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_DN				(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_OU				(X509Certificate *x509Certificate);
int 		__RSKSW_Check_Expired					(X509Certificate *x509Certificate);
int 		KS_Check_Sign						(X509Certificate *x509Certificate, RSKSW_PublicKey *rsa);
unsigned char *RSKSW_getCertificate_Vid			(X509Certificate *x509);
//added by choi 2009 08 19
//20130326 GPKI change function name
char		*RSKSW_getCertificate_SignatureAlgorithm		(X509Certificate *x509Certificate);

//20130326 GPKI
char		*RSKSW_getCertificate_Algorithm		(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Version			(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Valid_From_Format		(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Valid_To_Format		(X509Certificate *x509Certificate);
char		*RSKSW_getCertificate_Valid_To_Format2    (X509Certificate *x509Certificate);
//added by choi 2011.07.26    
char        *RSKSW_getCertificate_Organizationname(X509Certificate *x509Certificate);
//인증서 oid 가져오기. certmanager에서 가져옴.
int         RSKSW_getCertificate_Oid(char *output, X509Certificate *x509Certificate);
    
    int RSKSW_getCertificate_Alg(X509Certificate *x509Certificate);
/**
 *
 */
RSKSW_PublicKey *RSKSW_X509Certificate_To_PublicKey(X509Certificate *x509Certificate);

int RSKSW_Verify_Chain(KSW_BYTE *serverCert, int serverCertLen, KSW_BYTE *caCert, int caCertLen);

#ifdef	__cplusplus
}
#endif

#endif
