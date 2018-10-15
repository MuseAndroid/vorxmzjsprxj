#import <Foundation/Foundation.h>
//#import "RSSecureData.h"
#import "RSKSW_define.h"
#import "RSKSW_error.h"

@interface RSKSWCertificate : NSObject {
    int idx;
@private
}

@property (nonatomic, readonly) NSData *cert;
@property (nonatomic, readonly) NSData *key;
@property (nonatomic, readonly) NSData *certHashHex_Sha2;
@property (nonatomic, readonly) NSString *e1;
//@property (nonatomic, retain) NSString* cn;
@property (nonatomic, assign) int idx;
@property (nonatomic, readonly) int mode;
//@property (nonatomic, readonly) NSString* certPath;
- (id)initWithCert:(NSData*)aCert;
- (id)initWithCert:(NSData*)aCert withKey:(NSData*)aKey;

- (BOOL)isPinBio;

- (NSString *)getPEMCert;
- (int)selfVerify:(NSString *)idv password:(NSString *)password;

- (NSData*) getVid;

// 인증서 간단 정보
- (NSString *)getPolicy;
//- (NSString *) getCertificatePolicyTranslator:(int)lnagType;    //15.02.03
- (NSString *)getSubjectCn;//SubjectName
- (NSString *)getIssuerCn;
//ca 한글
- (NSString *)getIssuerCnKorean;

// 인증서 상세 정보
- (NSString *)getVersion;
- (NSString *)getSerialNumber;
- (NSString *)getAlgorithm;
- (NSString *)getSignatureAlgorithm;	//130326 GPKI
- (NSString *)getIssuerDn;
- (NSString *)getSubjectDn;//Subject
// 인증서 만료 여부
- (int)isExpired;
- (NSString *)getNotBefore;
- (NSString *)getNotAfter;
- (NSString *)getNotAfterDate;

- (NSString *)getIssuerOrg;
- (NSString *)getUsage;
- (NSString *)getSubjectLocality;
- (NSString *)getSubjectStateOrProvinceName;
//인증서 정책 스트링.
- (NSString *)getPolicyOid;
//인증서 공개키
- (NSData *)getPublicKey;
- (NSString *)getPublicKeyHex;//ASN(pubkey)
- (NSString*)getCertAndKeyXML;//xml형태로 인증서와 키를 추출


//인증서암호확인
- (int) checkPassword:(NSString *)password;
//- (int) checkCertRSPassword:(RSSecureData *)password;

@end
