#ifndef __RSKSW_SYM_H___
#define __RSKSW_SYM_H___

#ifdef __ANDROID__
#include "../cmvp/include/ks_cmvp.h"
#else
#include "ks_cmvp.h"
#endif
#include "RSKSW_define.h"

#ifdef __cplusplus
extern "C" {
#endif
    
//    int
//    KSW_KEY_SecKey_EncodeKey(KSC_SYM_SecretKey *csKey,
//                             int keyID,
//                             ks_uint8 *key,
//                             ks_uint32 keyLength);
//    
//    KS_ASYM_PublicKey * RSKSW_ASYM_PublicKey_New(void);
//    void RSKSW_ASYM_PublicKey_Delete(KS_ASYM_PublicKey *pkPubKey);
    int RSKSW_ASYM_PublicKey_Encode(ks_uint8 *output,				// optional
                             ks_uint32 *outputLength,
                             KS_ASYM_PublicKey *pkPubKey,
                                 KS_ASYM_Parameters *pkParams);	// optional
    int RSKSW_ASYM_PublicKey_Decode(KS_ASYM_PublicKey *pkPubKey,
                                  int pkeyID,
                                  ks_uint8 *input,
                                  ks_uint32 inputLength,
                                  KS_ASYM_Parameters *pkParams);	// optional
    
//    KS_ASYM_PrivateKey * RSKSW_ASYM_PrivateKey_New(void);
    void RSKSW_ASYM_PrivateKey_Delete(KS_ASYM_PrivateKey *pkPrivKey);
    
    int RSKSW_ASYM_PrivateKey_Encode(ks_uint8 *output,				// optional
                              ks_uint32 *outputLength,
                              KS_ASYM_PrivateKey *pkPrivKey,
                              KS_ASYM_Parameters *pkParam);			// optional
    int RSKSW_ASYM_PrivateKey_Decode(KS_ASYM_PrivateKey *pkPrivKey,
                                   int pkeyID,
                                   ks_uint8 *input,
                                   ks_uint32 inputLength,
                                   KS_ASYM_Parameters *param);
    
//    KS_ASYM_Parameters * RSKSW_ASYM_Parameters_New(void);
//    void RSKSW_ASYM_Parameters_Delete(KS_ASYM_Parameters *pkParams);
    int RSKSW_ASYM_Parameters_Encode(ks_uint8 *output,					// optional
                               ks_uint32 *outputLength,
                               KS_ASYM_Parameters *pkParams);
    int RSKSW_ASYM_Parameters_Decode(KS_ASYM_Parameters *pkParams,
                                   int pkeyID,
                                   ks_uint8 *input, 
                                   ks_uint32 inputLength);
    int RSKSW_ASYM_SIGN_RSA         (unsigned char *output, int *output_len, unsigned char *input, int input_len, unsigned char *prikey, int prikey_len, int signId, int msgId);
    int RSKSW_ASYM_SIGN_KCDSA       (unsigned char *output, int *output_len, unsigned char *input, int input_len, unsigned char *param, int param_len, unsigned char *pubkey, int pubkey_len, unsigned char *prikey, int prikey_len, int signId, int msgId);
    int RSKSW_ASYM_VERIFY           (unsigned char *sign, int sign_len, unsigned char *msg, int msg_len, unsigned char *pubkey, int pubkey_len, unsigned char *param, int param_len, int signId, int msgId);
    int RSKSW_ASYM_ENCRYPT          (unsigned char *output, int* output_len, unsigned char *input, int input_len, unsigned char *pubkey, int pubkey_len, int encId);
    int RSKSW_ASYM_DECRYPT          (unsigned char *output, int* output_len, unsigned char *input, int input_len, unsigned char *prikey, int prikey_len, int encId);
    
    int RSKSW_ASYM_GEN_KEY_PAIR_RSA(unsigned char *out_pub, int *out_pub_len, unsigned char *out_priv, int *out_priv_len, int bitLength);
    
#endif
    
#ifdef __cplusplus
}
#endif
