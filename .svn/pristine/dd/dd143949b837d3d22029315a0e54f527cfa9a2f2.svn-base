#ifndef _RSKSW_PKCS5_H_
#define _RSKSW_PKCS5_H_

#define PKCS5OID_PBEWithSHA1And3KeyTDES		3
#define PKCS5OID_PBEWithSHA1AndSEED			4		/* KSIGN */
#define PKCS5OID_PBEWithSHA1AndSEED1		15

#define PKCS5OID_PBES2				13
#define PKCS5OID_PBEWith3KeyTDES	7

#define PKCS5OID_BIOMETRIC                  0x6500
#define PKCS5OID_BIOMETRIC_NFC              (PKCS5OID_BIOMETRIC + 1)
#define PKCS5OID_BIOMETRIC_FINGERPRINT      (PKCS5OID_BIOMETRIC + 2)
#define PKCS5OID_BIOMETRIC_FACEPRINT        (PKCS5OID_BIOMETRIC + 3)
#define PKCS5OID_BIOMETRIC_VOICEPRINT       (PKCS5OID_BIOMETRIC + 4)
#define PKCS5OID_BIOMETRIC_EYEPRINT         (PKCS5OID_BIOMETRIC + 5)
#define PKCS5OID_BIOMETRIC_IRISPRINT        (PKCS5OID_BIOMETRIC + 6)
#define PKCS5OID_BIOMETRIC_HANDPRINT        (PKCS5OID_BIOMETRIC + 7)
#define PKCS5OID_BIOMETRIC_AUTOSIGNING      (PKCS5OID_BIOMETRIC + 8)
#define PKCS5OID_BIOMETRIC_PIN              (PKCS5OID_BIOMETRIC + 9)
#define PKCS5OID_BIOMETRIC_PATTERN              (PKCS5OID_BIOMETRIC + 10)
#define IS_PKCS5OID_BIOMETRIC(x)    ((x&PKCS5OID_BIOMETRIC) == PKCS5OID_BIOMETRIC)

#define PKCS5OID_PKCS12KDF				12
#define PKCS5OID_PBEWithSHA1AndARIA		    20		



#ifdef __cplusplus
extern "C" {
#endif

int 
RSKSW_PKCS5_PBES_Encrypt (unsigned char *out, int *out_len, unsigned char *in, int in_len, 
					unsigned char *pwd, int pwd_len, unsigned char *salt, 
					int iteration, int alg_id);

int
RSKSW_PKCS5_PBES_Decrypt (unsigned char *out, int *out_len, unsigned char *in, int in_len, 
					unsigned char *pwd, int pwd_len, unsigned char *salt, int salt_len,
					int iteration, int alg_id);

int 
RSKSW_PKCS5_PBES2_Encrypt (unsigned char *out, int *out_len, unsigned char *in, int in_len, 
					 unsigned char *pwd, int pwd_len, unsigned char *salt, int salt_len, 
					 int iteration, int alg_id, unsigned char *iv, int ivLen);

int 
RSKSW_PKCS5_PBES2_Decrypt (unsigned char *out, int *out_len, unsigned char *in, int in_len, 
					 unsigned char *pwd, int pwd_len, unsigned char *salt, int salt_len, 
					 int iteration, int alg_id, unsigned char *iv, int ivLen);
    
int
RSKSW_PKCS5_PBKDF2 (unsigned char *key, int key_len, unsigned char *pwd, int pwd_len, unsigned char *salt, int salt_len, int iteration);

#ifdef __cplusplus
}
#endif

#endif
