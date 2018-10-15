#import <Foundation/Foundation.h>
@interface RSKSWLogger : NSObject  {
}

+ (void) setLogOn: (BOOL)logOn;
+ (void) print: (NSString*) message, ...;
+ (void) printWithTitle:(NSString*)title message:(NSString*) message, ...;
+ (void) printCString: (char*) message, ...;
+ (void) printBin: (NSString*)title bytes:(char *)bytes len:(int)len;
+ (void) writeToFile:(NSString *)aFileName data:(char *)aData dataLen:(int)dataLen;

@end
