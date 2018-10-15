//
//  RSKSWChannelEncrypt.h
//  TouchEn_wireless
//
//  Created by sjyang on 2017. 2. 2..
//  Copyright © 2017년 RaonSecure. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface RSKSWChannelEncrypt : NSObject
//{
//    RSKSWSidManager *sidManager;
//
//}
@property (nonatomic, retain) NSData *serverCert;
@property (nonatomic, retain) NSString *sid;
@property (nonatomic, assign) BOOL isOAEP;
+ (RSKSWChannelEncrypt *) getInstance;
+ (int)getLibraryState;

- (NSString *)generateSid:(NSString *)aValue;

- (int)chainVerify:(NSData *)aCert caCert:(NSData *)aCACert;

// 130402 : ksbiz2 enc,dec
- (int)encrypt:(char *)plain length:(int)plainLen encrypted:(char *)cipher encoding:(int)encoding;
- (int)decrypt:(char *)cipher length:(int)cipherLen plain:(char *)plain encoding:(int)encoding;

- (void)reset;

@end
