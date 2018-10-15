#ifndef	__RSKSWCMS_H__
#define	__RSKSWCMS_H__

#include "RSKSW_define.h"

#define SIGNALGID_SHA1WithRSA   		2
#define	SIGNALGID_SHA256WithRSA			20	//added by choi
#define SIGNALGID_SHA1WithKCDSA			6
#define SIGNALGID_SHA256WithKCDSA       9

#ifdef  __cplusplus
extern "C" {
#endif


/**
 *	RSKSW_CMS_Sign :
 *
 *  Make SignedData
 *
 *	output : output 		: pointer to output buffer
 *	input  : data			: pointer to input data
 *			 data_length	: length of input
 *			 cert			: pointer to cert
 *			 cert_length	: length of cert
 *			 key			: pointer to decrypted private key
 *			 key_length		: length of key
 *           signingTime    : set when useSignedAttr is true
 *       signingTime_length : 13-millisec, 10-sec, 0-generate
 *			 signAlg		: signature algorithm
 *			 useSignedAttr	: 0 - do not use signedAttrs, other - use signedAttrs
 *			 addCert		: 0 - no cert, 1 - signer only
 *	return : output_length	: length of output
 **/
  
int RSKSW_CMS_Sign(ks_uint8 *output, 
			ks_uint8 *data, int data_length, 
			ks_uint8 *cert, int cert_length,
			ks_uint8 *key, int key_length,
			int signAlg,
			int useSignedAttr,
			int addCert);
int RSKSW_CMS_Sign_SigningTime(ks_uint8 *output,
         ks_uint8 *data, int data_length, ks_uint8 *cert,
         int cert_length, ks_uint8 *ekey, int ekey_length,
          ks_uint8* signingTime, int signingTime_length, int signAlg,
         int useSignedAttr, int addCert);
    
//원문없이 해시값 입력받음
int RSKSW_CMS_Sign_Hash(ks_uint8 *output, ks_uint8 *cert,
              int cert_length, ks_uint8 *ekey, int ekey_length, ks_uint8* signingTime, int signingTime_length, int signAlg,
              int useSignedAttr, ks_uint8* p_hash, int p_hash_length,
              int addCert);

int RSKSW_CMS_fromPkcs1(ks_uint8 *output, ks_uint8 *data, int data_length,
            ks_uint8 *cert, int cert_length,
            ks_uint8* p1_sign, int p1_sign_length,
            int signAlg);
int RSKSW_CMS_fromPkcs1_withAttr(ks_uint8 *output, ks_uint8 *data, int data_length,
            ks_uint8 *cert, int cert_length,
            ks_uint8* p1_sign, int p1_sign_length,
            ks_uint8* signingTime, int signingTime_length,
            int signAlg);

int RSKSW_CMS_Sign_Koscom(ks_uint8 *output, 
			ks_uint8 *data, int data_length, 
			ks_uint8 *cert, int cert_length,
			ks_uint8 *key, int key_length,
			int signAlg,
			int useSignedAttr,
			int addCert);
    
int RSKSW_CMS_Sign_withAllOptions(ks_uint8 *output, ks_uint8 *data, int data_length,
            ks_uint8 *cert, int cert_length, ks_uint8 *ekey, int ekey_length,
            ks_uint8* p1_sign, int p1_sign_length,
            ks_uint8* signingTime, int signingTime_length,
            int signAlg, int useSignedAttr,
            ks_uint8* p_hash, int p_hash_length,
                                  int addCert);
int RSKSW_Simple_Sign(ks_uint8 *output,
			ks_uint8 *data, int data_length, 
			ks_uint8 *key, int key_length,
			int signAlg);
int RSKSW_Simple_Sign_Hash(ks_uint8 *output,
             ks_uint8 *hash,
             ks_uint8 *key, int key_length, int signAlg);

int RSKSW_Simple_Sign_Koscom(ks_uint8 *output, 
			ks_uint8 *data, int data_length, 
			ks_uint8 *key, int key_length,
			int signAlg);
int RSKSW_Simple_Sign_GPKI(ks_uint8 *output,
             ks_uint8 *data, int data_length,
             ks_uint8 *pubKey, int pubLen,
             ks_uint8 *priKey, int priLen, int signAlg);

/**
 *	RSKSW_CMS_Envelope :
 *
 *  Make EnvelpedData
 *
 *	output : output 		: pointer to output buffer
 *	input  : data			: pointer to input data
 *			 data_length	: length of input
 *			 cert			: pointer to the recipient cert
 *			 cert_length	: length of cert
 *			 encAlg			: content encryption algorithm
 *	return : output_length	: length of output
 */

int RSKSW_CMS_Envelope(ks_uint8 *output,
				 ks_uint8 *data, int data_length, 
				 ks_uint8 *cert, int cert_length,
				 int encAlg);

/**
 *	RSKSW_CMS_EnvIDNandRandom :
 *
 *  Epub(k)|Ek(IDN|RAND)
 *
 *	output : output 		: pointer to output buffer
 *	input  : idn			: pointer to input idn
 *			 idn_length		: length of idn
 *			 rand			: pointer to rand
 *			 rand_length	: length of rand
 *			 cert			: pointer to the recipient cert
 *			 cert_length	: length of cert
 *	return : output_length	: length of output
 */

int RSKSW_CMS_EnvIDNandRandom(ks_uint8 *output,
						ks_uint8 *idn, int idn_length, 
						ks_uint8 *rand, int rand_length,
						ks_uint8 *cert, int cert_length);

/**
 *  Added by choi 2012.08.23
 *	RSKSW_CMS_EnvRandom :
 *
 *  Epub(k)|Ek(IDN|RAND)
 *
 *	output : output 		: pointer to output buffer
 *	input  :
 *			 rand			: pointer to rand
 *			 rand_length	: length of rand
 *			 cert			: pointer to the recipient cert
 *			 cert_length	: length of cert
 *	return : output_length	: length of output
 */

int RSKSW_CMS_EnvRandom(ks_uint8 *output,
                        ks_uint8 *rand, int rand_length,
                        ks_uint8 *cert, int cert_length);

/**
 *	CMS_GetParam : get key and iv genarated by RSKSW_CMS_Envelope()
 *
 *	key : output 		: pointer to output buffer (max = 24)
 *	iv  : output		: pointer to output buffer ( = 16)
 *	return : key length or 0
 */
//int
//CMS_GetParam (ks_uint8 *key, ks_uint8 *iv);
//int
//MAKE_AUTH_ATTRIBUTE(unsigned char *output, int *output_len, unsigned char *data, int data_len);
//int
//MAKE_PKCS7_SIGNED_ENVELOPE_FOR_KCP(unsigned char *output, int *output_len, unsigned char *data, int data_len, unsigned char *attribute, int attribute_len, unsigned char *p1, int p1_len, unsigned char *recip_cert, int recip_cert_len, unsigned char *cert, int cert_len);

int RSKSW_CMS_SignFile(ks_uint8 *output,
                 ks_uint8 *cert, int cert_length,
                 ks_uint8 *key, int key_length,
                 ks_uint8 *fileInfo, int fileInfo_length,
                 ks_uint8 *hash, int hash_length,
                 ks_uint8 *signingTime, int signingTime_length,
                 int signAlg,
                 int useSignedAttr,
                 int addCert);
    
    int RSKSW_AuthenticatedAttributes(ks_uint8 *output, ks_uint8 *data, int data_length, ks_uint8 *p_hash, int p_hash_length, int signAlg, ks_uint8* signingTime, int signingTime_length);
#ifdef  __cplusplus
}
#endif

#endif
