#import <Foundation/Foundation.h>

//#define NET_INVALID_TYPE -500
//#define NET_CONN_FAIL -1000
//#define NET_RECV_FAIL -1001
//#define NET_SEND_FAIL -1002

//fefined in the client
#define CODE_ERR_SAVE_CERT      @"CM100"

//below are defined in the server
#define CODE_ERR_CONNECTION     @"NT100"
#define CODE_ERR_SOCKET         @"NT101"
#define CODE_ERR_BIND           @"NT102"
#define CODE_ERR_LISTERN        @"NT103"
#define CODE_ERR_ACCEPT         @"NT104"
#define CODE_ERR_WRITE          @"NT105"
#define CODE_ERR_READ           @"NT106"

#define CODE_ERR_SHOULD_R1_First    @"NL108"

#define CODE_ERR_DBOPEN         @"DB107"
#define CODE_ERR_INSERT         @"DB108"
#define CODE_ERR_DELETE         @"DB109"
#define CODE_ERR_UPDATE         @"DB110"
#define CODE_ERR_SELECT         @"DB111"
#define CADE_ERR_DBEXEC         @"DB112"

#define CODE_ERR_INVALID_INPUT  @"PT113"
#define CODE_ERR_INVALID_TYPE   @"PT114"
#define CODE_ERR_NO_CERTIFICATE @"PT115"
#define CODE_ERR_TIMEOUT        @"PT116"
#define CODE_ERR_NO_EXIST_INDEX @"PT117"
#define CODE_ERR_EXIST_INDEX    @"PT118"

#define CODE_SUCC_R1            @"SC200"
#define CODE_SUCC_R2            @"SC201"
#define CODE_SUCC_S1            @"SC202"
#define CODE_SUCC_S2            @"SC203"


@interface RSKSWICRProtocol : NSObject {
    NSData* seed;
    NSData* index;
    NSData* key;
    NSData* iv;
}

@property (nonatomic, readonly) NSString* lastErrorCode;
@property (nonatomic, readonly) NSString* lastErrorMessage;
@property (nonatomic, readonly) NSString* generatedNumber;
@property (nonatomic, readonly) NSData* certData;
@property (nonatomic, readonly) NSData* keyData;
@property (nonatomic, readonly) NSData* kmCertData;
@property (nonatomic, readonly) NSData* kmKeyData;

@property (nonatomic, retain) NSString* serverIP;
@property (assign) NSUInteger serverPort;

@property (assign) int securityLevel;

//User Interface

+ (int)getLibraryState;

//ip 사용예
//    "::ffff:211.239.0.1" "::ffff:D3EF:1" "211.239.0.1"
- (id) initWithIP:(NSString*)ip port:(NSUInteger)port;
- (id) initWithIP:(NSString*)ip port:(NSUInteger)port siteCode:(NSString *)aSiteCode;
- (int) import1;
- (int) import2;
- (int) export1:(NSData*)exportCert exportKey:(NSData*)exportKey confirmNumber:(NSString*)confirumNumber;
- (int) export1:(NSData*)exportCert exportKey:(NSData*)exportKey kmCert:(NSData *)kmCert kmKey:(NSData *)kmKey confirmNumber:(NSString *)confirumNumber;

//checkPassword : RSKSWCertificate 함수로 사용.

@end
