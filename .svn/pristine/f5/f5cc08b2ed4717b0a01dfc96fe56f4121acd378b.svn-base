//
//  RSKSWShared.h
//  RSKSW2
//
//  Created by sjyang on 2017. 11. 3..
//  Copyright © 2017년 RaonSecure. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RSKSWCertificate.h"
#import "RSSecureData.h"
#import "RSKSW_define.h"
@interface RSKSWShared : NSObject
@property (nonatomic, assign) int certPwdMode;
@property (nonatomic, retain) NSData *mTRandomKey;
@property (nonatomic, retain) RSSecureData *mSecureData;
@property (nonatomic, retain) NSMutableData *pin;
@property (nonatomic, retain) NSMutableString *e2;

- (void)setCertPwdModeDefault;
- (void)setCertPwdModeMTranskey:(NSData *)randomKey;
- (void)setCertPwdModeRSSecureData:(RSSecureData *)secData;
- (void)setCertPwdModeBioPin:(NSData *)randomKey;
- (NSMutableData *)getPassword:(RSKSWCertificate *)cert from:(NSString *)encPwd error:(int *)err;
@end
