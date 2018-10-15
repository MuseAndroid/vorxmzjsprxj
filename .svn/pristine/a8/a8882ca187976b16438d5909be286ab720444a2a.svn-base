// 평문을 입력받아 암호화하여 저장.
// 사용후 평문을 메모리에서 지울수 있는 함수를 제공한다.
#import <Foundation/Foundation.h>
#import <CommonCrypto/CommonCryptor.h>

#define RS_SECDATA_FLAG_RAND        0
#define RS_SECDATA_FLAG_SET_AES     1

#define ERR_RS_SECDATA_NORMAL               0
#define ERR_RS_SECDATA_PLAINTEXTISEMPTY     -20
#define ERR_RS_SECDATA_INVALID_INPUT        -21

typedef struct _RSData {
    unsigned char *value;
    unsigned int length;
} RSData; // SignKorea Data type

@interface RSSecureData : NSObject
- (id) init;
//iv,key가 외부에서 관리되는 경우 사용한다.
- (id)initWithFlag:(int)flag iv:(NSData *)iv key:(NSData *)key;
//평문 설정.
- (void)setData:(unsigned char*)data length:(unsigned int)length;

//직렬화된 데이터를 받아 객체화한다. iv,key는 외부관리되는 경우 입력하고 직렬화데이터에 포함되어 있으면 nil로 설정한다.
- (id)initWithSerialized:(NSString *)data iv:(NSData *)iv key:(NSData *)key;

//객체를 직렬화한다.
- (NSString *)toString;

//암호화되어있는 값을 복호화하여 평문을 리턴한다.
//getData수행후에는 clearData를 필히 수행하여 메모리에 남지 않도록 한다.
- (RSData *)getData;
//평문 및 평문 암호화에 쓰인 파라미터를 모두 초기화한다.
- (void)clearAll;
//메모리에 남아있는 평문을 삭제한다.
- (void)clearPlain;
//버전정보를 가져온다.
- (NSString*)getVersion;
//에러코드 반환.
- (int)getErrorCode;
@end
