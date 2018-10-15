#ifndef _RSKSW_HYBRID_ENV_H_
#define _RSKSW_HYBRID_ENV_H_

//#include "KSClient.h"
//#include "Util.h"
#include "RSKSW_base64.h"
#include "RSKSW_hmac.h"
#include "RSKSW_sha.h"

//KSH length define
#define RSKSWH_SID_LEN     20
//#define RSKSWH_SID_LEN     32
#define RSKSWH_IV_LEN      16
#define RSKSWH_KEY_LEN     16
#define RSKSWH_MACKEY_LEN  20
#define RSKSWH_MAC_LEN     20
#define RSKSWH_DUMMY_LEN   8


//KSH struct
typedef struct{
	ks_uint8 sid[RSKSWH_SID_LEN];
    ks_uint8 iv[RSKSWH_IV_LEN];
    ks_uint8 key[RSKSWH_KEY_LEN];
    ks_uint8 mackey[RSKSWH_MACKEY_LEN];
    ks_uint8 dummy[RSKSWH_DUMMY_LEN];
    
    ks_uint32 sidLen;
    ks_uint32 ivLen;
    ks_uint32 keyLen;
    ks_uint32 mackeyLen;
    ks_uint32 dummyLen;
    
    int isMake;
} RSKSWH_ENC_CTX;

#ifdef  __cplusplus
extern "C" {
#endif
    
    int RSKSWH_SEED_CBC_Encrypt_DB(ks_uint8 *psOutput, ks_uint8 *psInput, KSW_WORD nInputLength,  ks_uint8 *psIV, ks_uint8 *psKey ,ks_uint8 *srcIV , ks_uint8 *srcKEY);
    int RSKSWH_SEED_CBC_Decrypt_DB(ks_uint8 *psOutput, ks_uint8 *psInput, KSW_WORD nInputLength,  ks_uint8 *psIV, ks_uint8 *psKey ,ks_uint8 *srcIV , ks_uint8 *srcKEY);
    
    int RSKSWH_Envelope(RSKSWH_ENC_CTX *kshenc_ctx,
                 ks_uint8 *output,
                 ks_uint32 output_max_length,
                 ks_uint8 *input, ks_uint32 input_length ,
                 ks_uint8 *cert, int cert_length,
                 int encAlg,
                 int encodingFlag
                 );
    
    
    int RSKSWH_Decode(RSKSWH_ENC_CTX* sercerEnc_ctx , RSKSWH_ENC_CTX* dbEnc_ctx ,
                    ks_uint8 *output,
                    ks_uint32 output_max_length,
                    ks_uint8 *input, ks_uint32 input_length ,
                    int decodingFlag,
                    int encodingFlag);

    
#ifdef __cplusplus
} // of extern "C"
#endif

#endif
