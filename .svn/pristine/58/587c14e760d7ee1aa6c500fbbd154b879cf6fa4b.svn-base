#ifndef __RSKSW_PKCS1_H_
#define __RSKSW_PKCS1_H_

#include "RSKSW_define.h"
#include "RSKSW_bigint.h"
#include "RSKSW_rand.h"

/***
 ***	Basic Types
 ***/
typedef struct{
    BigInt		*n;		/*	modulus	*/
	BigInt		*e;		/*	publicExponent	*/
} RSKSW_PublicKey;


typedef struct{
	BigInt		*n;		/*	modulus	*/
	BigInt		*e;		/*	publicExponent	*/
	BigInt		*d;		/*	privateExponent	*/
	BigInt		*p;		/*	prime1	*/
	BigInt		*q;		/*	prime2	*/
	BigInt		*dP;	/*	exponent1	*/
	BigInt		*dQ;	/*	exponent2	*/
	BigInt		*qInv;	/*	coefficient	*/
} RSKSW_PrivateKey;


/*****
 *****
 *****		Function Prototypes
 *****
 *****/

#ifdef __cplusplus
extern "C" {
#endif
int GetFlagPSS();
int GetFlagOAEP();
void SetFlagPSS(int value);
void SetFlagOAEP(int value);
    
RSKSW_PrivateKey	*RSKSW_PrivateKeyCreate		(void );
RSKSW_PublicKey     *RSKSW_PublicKeyCreate		(void );
void                RSKSW_PrivateKeyFree		(RSKSW_PrivateKey *key );
void                RSKSW_PublicKeyFree			(RSKSW_PublicKey *key );
KSW_WORD			RSKSW_PrivateKeyEncode		(KSW_BYTE *byteSeq, RSKSW_PrivateKey *key, int *resultSize );
KSW_WORD			RSKSW_PrivateKeyDecode		(RSKSW_PrivateKey *key, KSW_BYTE *byteSeq, int seqByteLength );
KSW_WORD			RSKSW_PKCS1_FoolishKeyDecode		(RSKSW_PrivateKey *key, KSW_BYTE *byteSeq, int seqByteLength );
KSW_WORD			RSKSW_PublicKeyEncode		(KSW_BYTE *byteSeq, RSKSW_PublicKey *key, int *resultSize );
KSW_WORD			RSKSW_PublicKeyDecode		(RSKSW_PublicKey *key, KSW_BYTE *byteSeq, int seqByteLength );

KSW_WORD			RSKSW_PKCS1_I2OSP					(KSW_BYTE *byteSeq, BigInt *input, int seqLength );
KSW_WORD			RSKSW_PKCS1_OS2IP					(BigInt *output, KSW_BYTE *input, int byteLength );

KSW_WORD RSKSW_PKCS1_Sign_Hash(KSW_BYTE *signature, KSW_BYTE *hashValue, KSW_BYTE *key, int key_len, int hashID);
KSW_WORD 			RSKSW_PKCS1_Sign_Data				(KSW_BYTE *signature, KSW_BYTE *input, int input_len, KSW_BYTE *key, int keyLen, int hashID);
KSW_WORD 			RSKSW_PKCS1_Sign_Data_Koscom		(KSW_BYTE *signature, KSW_BYTE *input, int input_len, KSW_BYTE *key, int key_len, int hashID);
KSW_WORD 			RSKSW_PKCS1_Sign_Verify			(KSW_BYTE *signature, int signature_len, KSW_BYTE *msg, int msg_len, KSW_BYTE *key, int key_len, int hashID);
KSW_WORD 			RSKSW_PKCS1_Sign_Verify_Koscom	(KSW_BYTE *signature, int signature_len, KSW_BYTE *input, int input_len, KSW_BYTE *key, int key_len, int hashID);
KSW_WORD RSKSW_PKCS1_EME_Encrypt(KSW_BYTE *cipher, KSW_BYTE *plain, KSW_BYTE *key, int keyLen, int plainLength );
KSW_WORD RSKSW_PKCS1_EME_Decrypt			(KSW_BYTE *plain, KSW_BYTE *cipher, KSW_BYTE *key, int keyLen, int *plainLength, int cipherLength );

int				RSKSW_Pubkey_Copy(RSKSW_PublicKey *dest, RSKSW_PublicKey *src);
int				RSKSW_Prikey_Copy(RSKSW_PrivateKey *dest, RSKSW_PrivateKey *src);

#ifdef __cplusplus
} // of extern "C"
#endif


#endif
