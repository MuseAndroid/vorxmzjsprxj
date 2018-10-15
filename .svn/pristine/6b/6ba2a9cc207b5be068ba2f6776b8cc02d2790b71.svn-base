#ifndef _RSKSW_PKCS8_H_
#define _RSKSW_PKCS8_H_

#include "RSKSW_pkcs5.h"
/* 
PrivateKeyInfo ::= SEQUENCE {
	version Version,
	privateKeyAlgorithm PrivateKeyAlgorithmIdentifier,
	privateKey PrivateKey,
	attributes [0] IMPLICIT Attribytes OPTIONAL }

Version ::= INTEGER
PrivateKeyAlgorithmIdentifier ::= AlgorithmIdentifier
PrivateKey ::= OCTET STRING
Attributes ::= SET OF Attribute
*/

/*
EncryptedPrivateKeyInfo ::= SEQUENCE {
	encryptionAlgorithm		EncryptionAlgorithmIdentifier,
	encryptedData			EncryptedData }

EncryptionAlgorithmIdentifier ::= AlgorithmIdentifier
EncryptedData ::= OCTET STRING
*/

#ifdef __cplusplus
extern "C" {
#endif
// success:ret>0, err:ret<0
int
RSKSW_PKCS8_PBES_Encode (unsigned char *out, int *out_len, unsigned char *in, int in_len, 
				   unsigned char *pwd, int pwd_len, unsigned char *random);

// success:ret>0, err:ret<0
int
RSKSW_PKCS8_PBES2_Encode (unsigned char *out, int *out_len, unsigned char *in, int in_len, 
					unsigned char *pwd, int pwd_len, unsigned char *random, int iteration, int alg_id);
int
RSKSW_PKCS8_PBES2_EncodeEx (unsigned char *out, int *out_len, unsigned char *in, int in_len, unsigned char *pwd, int pwd_len, unsigned char *random, int iteration, int alg_id, int object_id, char *objectName);

// pbes: 1, pbes2:2, err:ret<0
    
int
RSKSW_PKCS8_PBES_Decode (unsigned char *out, int *out_len, unsigned char *in, int in_len, 
				   unsigned char *pwd, int pwd_len);

int
RSKSW_PKCS8_Decode_EncPriKeyInfo (unsigned char *keyInfo, int *keyInfo_len, 
							unsigned char *encryptedPriKeyInfo, int encryptedPriKeyInfo_len, 
							unsigned char *pwd, int pwd_len);

int
RSKSW_PKCS8_PBES_Decode_VidRandom (unsigned char *vrandom, int *vrandom_len, 
							 unsigned char *out, int *out_len, 
							 unsigned char *in, int in_len, unsigned char *pwd, int pwd_len);

#ifdef __cplusplus
}
#endif
#endif
