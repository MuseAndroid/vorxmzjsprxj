
#ifndef __RSKSW_CMP_H__
#define __RSKSW_CMP_H__

#include "RSKSW_cmpproto.h"

#define CERT_NEW                1
#define CERT_RECOVERY           2

/* Option Flag */
#define SETOPT_KEYSIZE_BIT            0xA1
#define SETOPT_MSGDUMP                0xA2


#ifdef  __cplusplus
extern "C" {
#endif
#ifndef __ANDROID__
    
    int
    RSKSW_CMP_Issue(/* out */
                    unsigned char *usrcert,     int *usrcert_len,
                    unsigned char *prikey,     int *prikey_len,
                    unsigned char *usrkmcert,     int *usrkmcert_len,
                    unsigned char *kmprikey,     int *kmprikey_len,
                    unsigned char *cacert,     int *cacert_len,
                    unsigned char *rootcert, int *rootcert_len,
                    /* in */
                    char *passwd, int pwd_len,
                    int ca_name,
                    char *ca_ip, int ca_port,
                    char *ref_num,
                    char *auth_code
                    );
    
    //외부생성키인경우
    //1. RSKSW_POPOSigningKeyInput() 로 획득한값을외부키로서명(=popsign)
    //2. pubkey와 popsign를 입력받아 인증서발급
    //3. 인증서반환
    int
    RSKSW_CMP_IssueWithExKeypair(/*out*/
                                 unsigned char *usrcert,     int *usrcert_len,
                                 unsigned char *usrkmcert,     int *usrkmcert_len,
                                 unsigned char *cacert,     int *cacert_len,
                                 unsigned char *rootcert, int *rootcert_len,
                                 /*in*/
                                 unsigned char *pubkey, int pubkey_len,
                                 unsigned char *popsign, int popsign_len,
                                 int ca_name,
                                 char *ip, int port,
                                 char *ref_num,
                                 char *auth_code, int isNew/*CERT_NEW, CERT_RECOVERY*/);
    
    int
    RSKSW_CMP_Recovery(unsigned char *usrcert,     int *usrcert_len,
                       unsigned char *prikey,     int *prikey_len,
                       unsigned char *usrkmcert,     int *usrkmcert_len,
                       unsigned char *kmprikey,     int *kmprikey_len,
                       unsigned char *cacert,     int *cacert_len,
                       unsigned char *rootcert, int *rootcert_len,
                       char *passwd, int pwd_len,
                       int ca_name,
                       char *ip, int port,
                       char *ref_num,
                       char *auth_code);
    
    int RSKSW_CMP_Update(/*out*/
                         unsigned char *usrcert,     int *usrcert_len,
                         unsigned char *prikey,     int *prikey_len,
                         unsigned char *cacert,     int *cacert_len,
                         unsigned char *rootcert, int *rootcert_len,
                         /*in*/
                         unsigned char *oldcert,     int oldcert_len,
                         unsigned char *oldkey,     int oldkey_len,
                         unsigned char *kmCert,     int *kmCertLen,
                         unsigned char *kmPri,     int *kmPriLen,
                         char *passwd,    int pwd_len,
                         int ca_name,    int keySize,
                         char *ip, int port
                         );
    int RSKSW_CMP_UpdateWithExKeypair_Init(
                                           unsigned char *oldcert,     int oldcert_len,
                                           unsigned char *pubkey, int pubkey_len,
                                           int ca_name, int keySize,
                                           char *ip, int port
                                           
                                           );
    int RSKSW_CMP_Certificate_Init(int ca_name, char *ref_num, char *auth_code,
                                   ks_uint8 *pubkey, int pubkey_len, ks_uint8 *popsign, int popsign_len,
                                   int keySize, int isNew);
    int RSKSW_CMP_Update_Init(char *passwd, int passwd_len,
                              int ca_name,
                              unsigned char *oldcert, int oldcert_len,
                              unsigned char *oldkey, int oldkey_len,
                              ks_uint8 *pubkey, int pubkey_len,
                              int keySize);
    int RSKSW_CMP_Final(unsigned char *passwd, int passwd_len, int objId, char *objName);
    
    int
    RSKSW_CMP_Revocation(int ca_name,
                         char *ca_ip, int ca_port,
                         unsigned char *usrcert,     int usrcert_len,
                         unsigned char *prikey,     int prikey_len,
                         unsigned char *certadd,     int certadd_len,
                         char *passwd
                         );
    
    char *RSKSW_CMP_GetError(void);
#else
    void RSKSW_CMP_GetError(unsigned char *out);
#endif
    void RSKSW_CMP_Init_Ctx(void);
    int RSKSW_POPOSigningKeyInput(unsigned char *buf, unsigned char *pubkey, int pubkey_len);
    //for onepass
    //SubjectPublicKeyInfo
    int RSKSW_SubjectPublicKeyInfo(ks_uint8 *buf, ks_uint8 *pubkey, int pubkey_len);
    //pop source //for onepass
    int RSKSW_POPOSignatureSrc(ks_uint8 *buf, ks_uint8 *pubkey, int pubkey_len);
    void RSKSW_setPOPOSignature(ks_uint8 *in, int in_len);
    int RSKSW_PKIProtectionSrc(ks_uint8 *buf, int body_type);
    //body_type : CMP_BODYTYPE_KUR->CMP_BODYTYPE_CONF
    void RSKSW_setPKIProtectionSign(ks_uint8 *in, int in_len, int bodyType);//RSKSW_setPKIProtectionSign->RSKSW_setProtectionSignature
    
    //out: char[20]
    void RSKSW_CMP_Random4Exkey(unsigned char *out);
    int
    __rsksw_certTemplatePublicKey(ks_uint8 *buf, int id);
    int RSKSW_CMP_Get_UserCert(unsigned char *out);
    int RSKSW_CMP_Get_UserKey(unsigned char *out);
    int RSKSW_CMP_Get_KmCert(unsigned char *out);
    int RSKSW_CMP_Get_KmKey(unsigned char *out);
    int RSKSW_CMP_Get_CaCert(unsigned char *out);
    int RSKSW_CMP_Get_RootCert(unsigned char *out);
    
    //out : ios - NULL
    //      and - send data
    int RSKSW_PKIMessage(ks_uint8 *out, int body_type);
    //in : ios - NULL
    //     and - received data
    int RSKSW_dPKIMessage(ks_uint8 *in, int in_len, int body_type);
#ifdef  __cplusplus
}
#endif

#endif

