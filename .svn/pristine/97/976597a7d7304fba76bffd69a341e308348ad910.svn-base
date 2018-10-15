#import <Foundation/Foundation.h>
#import "RSKSWQRRelayError.h"
#define QR_SVRCERT_DEFAULT  0
#define QR_SVRCERT_BASE64   1
#define QR_SVRCERT_HEX      2
#define QR_SVRCERT_PEM      3
#define QR_SVRCERT_BINARY   4
#define QR_SVRCERT_INVALID      9


@interface RSKSWQRRelay : NSObject {
    int                 maxTrial;
    int                 totalTrial;
    BOOL                relayState;
    
    NSMutableData       *responseData; //웹통신시 사용하는 버퍼
    NSURLConnection     *urlConnection; //웹통신 connection
    
    int securityLevel;
}

@property (nonatomic, retain) RSKSWQRRelayError *qr_error;
@property (nonatomic, assign) int maxTrial;
@property (nonatomic, retain) id gDelegate;
@property (nonatomic, assign) SEL gSelector;
@property (nonatomic, readonly) NSData *serverCert;
@property (nonatomic, readonly) NSData *cert;
@property (nonatomic, readonly) NSData *key;
@property (nonatomic, readonly) NSData *kmCert;
@property (nonatomic, readonly) NSData *kmKey;

@property (assign) int                            securityLevel;

@property (assign) NSString *serverUrl;

+ (int)getLibraryState;
// 131105
- (id)initWithServerCert:(NSData *)aServerCert;
- (BOOL)checkCamera;
/*
 * @function    : decodeQR
 * @input       : qrData - scaned qrcode data
 * @return true : success
 *        false : fail to decode qrcode or non relay | payment qrcode
 */
- (BOOL)decodeQR:(NSString *)qrData;


/*
 * @function    : requestCert
 * @return      : NSMutableURLRequest
 *                Send a request to server.
 *                You can't use this func when 'decodeQR' failed.
 */
- (void)requestCert:(SEL)selector delegate:(id)delegate;


/*
 * @function    : getCert
 * @input       : responseData - received data from server.
 * @return true : success to get certification.
 *        false : fail.
 */
- (BOOL)getCertFromServer:(NSMutableData *)responseData;



/*
 * @function    : saveCertWithCertManager
 * @input       : manager - certmanager defined in app delegate.
 * @return true : cert is save.
 *        false : fail to save.
 */
@end
