
#ifndef __RSKSWDEFINE_H__
#define __RSKSWDEFINE_H__

//#ifndef NULL
//#define		NULL				0x00
//#endif
//encoding type
typedef enum    {
    RSKSWConstCRSecLevel_SHA1,
    RSKSWConstCRSecLevel_SHA256
} RSKSWConstCRSecLevel;

typedef enum    {
    RSKSWConstCertExpMode_NORMAL = 1,
    RSKSWConstCertExpMode_ALMOST = 0,
    RSKSWConstCertExpMode_EXPIRED= -1
} RSKSWConstCertExpMode;

#define IS_BIOCERT_MODE(mode) (mode & 0x0010)

typedef enum    {
    RSKSWConstCertPwdMode_DEFAULT,
    RSKSWConstCertPwdMode_MTRANSKEY,
    RSKSWConstCertPwdMode_RSSECUREDATA,
    RSKSWConstCertPwdMode_BIOPIN = 0x10,
    RSKSWConstCertPwdMode_BIOPIN_MT = 0x12
} RSKSWConstCertPwdMode;


//인증서 설정 옵션 - 채널암호화, QR복사서버 인증서 설정시 사용가능
//문자열 인코딩 옵션
#define     RSKSW_ENCODING_BINARY   0
#define     RSKSW_ENCODING_HEXUP    1
#define     RSKSW_ENCODING_HEXLOW   2
#define     RSKSW_ENCODING_BASE64   3
#define     RSKSW_ENCODING_PEMCERT  4
#define     RSKSW_READ_PATH 5  //파일로부터 바이너리 읽음

/* CMP */
// CA FLAG
#define CMP_CAFLAG_SIGNKOREA_4BANK      10
// CA Name
#define CMP_CA_XECURE                   100
#define CMP_CA_YESSIGN                  200
#define CMP_CA_SIGNKOREA                300
#define CMP_CA_SIGNKOREA_4BANK          CMP_CA_SIGNKOREA + CMP_CAFLAG_SIGNKOREA_4BANK
#define CMP_CA_SIGNGATE                 400
#define CMP_CA_CROSSCERT                500

//for issue
#define REAL_KOSCOM_IP @"ca.signkorea.com"
#define TEST_KOSCOM_IP @"test.signkorea.com"
#define KOSCOM_PORT 4099

#define REAL_YESSIGN_IP @"ca.yessign.or.kr"
#define TEST_YESSIGN_IP @"snoopy.yessign.or.kr"
#define YESSIGN_PORT 4512

#define REAL_SIGNGATE_IP @"ca.signgate.com"
#define TEST_SIGNGATE_IP @"gemini.signgate.com"
#define SIGNGATE_PORT 4502

//cert mode
typedef enum    {
    RSKSWConstCertMode_DEFAULT,
    RSKSWConstCertMode_BIO_FINGERPRINT  = 0x02,
    RSKSWConstCertMode_BIO_PIN          = 0x09,
} RSKSWConstCertMode;

#define		RSKSW_ENCALGID_DES		0
#define		RSKSW_ENCALGID_3DES		1
#define		RSKSW_ENCALGID_SEED		10
#define		RSKSW_ENCALGID_AES		12

#define		RSKSW_PENCALGID_RSA		1
#define		RSKSW_PENCALGID_RSA_OAEP	5

#define     MASK08				(0xffL)				//  8 bit mask
#define     MASK16				(0xffffL)			// 16 bit mask
#define     MASK32				(0xffffffffL)		// 32 bit mask
#define		MASKMSB				(0x80000000L)		// MSB of KSW_WORD
#define     WORD_BITLENGTH		32					// bit  length of KSW_WORD
#define     WORD_BYTELENGTH		4					// byte length of KSW_WORD

#define		ks_uint8				unsigned char
#define		ks_uint16				unsigned short
#define		ks_uint32				unsigned int
//#define		int32				int

typedef		unsigned char		KSW_BOOL;
typedef		unsigned char		KSW_BYTE;				
typedef		unsigned int		KSW_WORD;				

#define		KSW_ALGID_MD2					5
#define		KSW_ALGID_MD5					7
#define		KSW_ALGID_SHA1					8
#define		KSW_ALGID_SHA256					9


#ifndef true
#define		true				1
#endif
#ifndef false
#define		false				0
#endif

#endif
