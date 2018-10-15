//
//  KSCertManager.h
//

#import <Foundation/Foundation.h>

#import "RSKSWCertificate.h"
#import "RSKSWShared.h"
#import "RSSecureData.h"
#import "RSKSW_define.h"

@class KS2SidManager;
@interface RSKSWCertManager : RSKSWShared {
    NSMutableArray* arrCert;
    int currIndex;
    NSData* lastCopiedCert;
}


@property (nonatomic, retain) NSMutableArray* arrCert;
@property (nonatomic, retain) NSData* lastCopiedCert;
//@property (nonatomic, retain) NSData *serverCert;
//@property (nonatomic, retain) NSString *sid;
@property (nonatomic, assign) int lastErrCode;
@property (nonatomic, retain) NSString *lastErrMsg;
@property (nonatomic, assign) BOOL isSofo;

@property (nonatomic, assign) BOOL isPSS;
@property (nonatomic, assign) BOOL isOAEP;

@property (nonatomic, retain) NSData *mTRandomKey;
@property (nonatomic, retain) RSSecureData *mSecureData;

+ (RSKSWCertManager *) getInstance;//CertManager 객체 획득(Singleton)
- (instancetype)init __attribute__((deprecated));

+ (int)getLibraryState;

//외부 인증서 저장
- (BOOL)saveCert:(NSData*)certData key:(NSData*)keyData;
//인증서삭제
- (BOOL)delCert:(int)idx;

//modes//
//RSKSWConstCertMode_DEFAULT,
//RSKSWConstCertMode_BIO_FINGERPRINT  = 0x02,
//RSKSWConstCertMode_BIO_PIN          = 0x09,
-(void)delAllCert:(int)mode;//키체인에 저장된 특정모드의 인증서 모두 삭제
- (int)count; //arrCert에 저장되어 있는 인증서 갯수 반환
- (void)reloadCerts;
//manage labeled key
@property (nonatomic, retain) NSString *label;

//auth

////인증서암호변경
- (int)changePassword:(int)idx oldPassword:(NSString *)oldPassword newPassword:(NSString *) newPassword;
////인증서암호확인
- (int)checkPassword:(int)idx currentPassword:(NSString *)currentPassword;

//전자서명
- (int)cmsSign:(int)idx password:(NSString*)password signSource:(NSData*)signSource signResult:(char*)signResult;
//증권전산 전자서명 (ver1.1.8 subjectDN 뽑아오도록 변경)
- (int)koscomSign:(int)idx password:(NSString*)password signSource:(NSData*)signSource signResult:(char*)signResult;
//축약서명 (xc241에서 검증)
- (int)simpleSign:(int)idx password:(NSString*)password signSource:(NSData*)signSource signResult:(char*)signResult;
//증권전산 축약서명 (ver1.1.8 추가 - 증권전산에서 검증)
- (int)koscomSimpleSign:(int)idx password:(NSString*)password signSource:(NSData*)signSource signResult:(char*)signResult;

//pdf서명
- (int) cmsSignDetached:(int)idx password:(NSString*)password signingTime:(NSData*)signingTime fileHash:(NSData *)fileHash signResult:(char*)signResult;

//caName: yessign OR koscom
//testFlag: 0-> real 1-> test
//- (int)issue:(NSString*)refNum authCode:(NSString*)authCode ip:(NSString *)ip port:(int)port password:(NSString*)password caNum:(int)caNum isReIssue:(BOOL)isReIssue error:(int *)err;
//- (int)update:(int)idx password:(NSString *) password caName:(NSString*)caName testFlag:(NSUInteger)testFlag;

// add interface matched to android.
// reIssue: true->reIssue, false->issue
// caNum : CMP_CA_YESSIGN, CMP_CA_SIGNKOREA, CMP_CA_SIGNGATE, CMP_CA_CROSSCERT
- (int)issue:(NSString*)refNum authCode:(NSString*)authCode
    password:(NSString*)password
          ip:(NSString *)ip port:(int)port
       caNum:(int)caNum isReIssue:(BOOL)isReIssue;
- (int)update:(int)idx password:(NSString *)password
           ip:(NSString *)ip port:(int)port caNum:(int)caNum;

- (NSString *)issueGetLastError;

- (RSKSWCertificate*)getCert:(int)idx; //저장된 인증서를 Certificate 객체 형태로 반환
- (RSKSWCertificate*)getCertBySubjectDN:(NSString *)dn; //ver1.1.8 (dn에 해당하는 idx 리턴 API 추가)

//added ver3.0.12
- (BOOL)havingKmCert:(int)idx;
- (NSData*)getKmCert:(int)idx;
- (NSData*)getKmKey:(int)idx;


- (NSData *)getRandom:(int)idx password:(NSString *)password error:(int *)err; //key파일에서 random 값 추출
- (int) xwVid:(int)idx password:(NSString*)password idn:(NSString*)idn serverCert:(NSData*)aServerCert result:(char*)result; ////주민번호 + Random 값을 envelop. XecureWeb 연동용

+ (BOOL)isFirstRun; //check first run after installed



// 130528 : filtering arrCert with given filter
- (void)filterCertListWithIssuer:(NSArray *)issuers;
- (void)filterCertListWithIssuerCN:(NSArray *)issuers;
- (void)filterCertListWithPolicyOid:(NSArray *)policies;
- (void)filterExpiredCert;


// 140630 : enhanced password check
// 매뉴얼 참고
- (int)checkComplexity:(NSString *)password;// option:(int)opt;

//서명(include timestamp)
- (int)cmsSign:(int)idx password:(NSString*)password signSource:(NSData*)signSource signingTime:(NSData*)signingTime signResult:(char*)signResult;
//파일전자서명
- (int)cmsSignFile:(int)idx password:(NSString*)password fileInfo:(NSData*)fileInfo fileHash:(NSData*)fileHash signingTime:(NSData*)signingTime signResult:(char*)signResult;

//- (NSMutableData *)getPassword:cert from:(NSString *)encPwd error:(int *)err;

- (int)issueBioPin:(NSData *)pin refNum:(NSString*)refNum authCode:(NSString*)authCode password:(NSString*)password ip:(NSString *)ip port:(int)port caNum:(int)caNum isReIssue:(BOOL)isReIssue;
- (NSString *)getBioPinPassword:(NSData *)pin hexE:(NSString *)hexE;
@end


