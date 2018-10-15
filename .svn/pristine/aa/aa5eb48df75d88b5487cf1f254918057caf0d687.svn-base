#import <Foundation/Foundation.h>
#include "RSKSW_error.h"

@interface RSKSWQRRelayError : NSObject {
    int         code;
    NSMutableString    *message;
}
@property (nonatomic, assign) int code;
@property (nonatomic, retain) NSMutableString *message;

- (id)init;
- (void)setMessageWithCode:(int)newCode;
- (void)setCode:(int)newCode message:(NSString *)newMessage;
- (void)addDesc:(NSString *)desc;
@end
