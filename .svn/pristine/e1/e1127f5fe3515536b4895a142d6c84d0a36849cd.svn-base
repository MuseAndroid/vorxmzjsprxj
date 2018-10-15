//
//  RSKSWCmp.h
//  RSKSW2
//
//  Created by sjyang on 2017. 11. 2..
//  Copyright © 2017년 RaonSecure. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "RSKSWShared.h"
#import "RSKSW_define.h"
@interface RSKSWCmp : RSKSWShared

@property (nonatomic, readonly) NSData *mCert;
@property (nonatomic, readonly) NSData *mKey;
@property (nonatomic, readonly) NSData *mKmCert;
@property (nonatomic, readonly) NSData *mKmKey;
@property (nonatomic, readonly) NSData *mCaCert;
@property (nonatomic, readonly) NSData *mRootCert;
//certMode: CERT_DEFAULT, CERT_BIO, ...
- (instancetype)initWithIp:(NSString *)ip port:(int)port caName:(int)caName;
- (int)issueInit:(NSString *)refNum authNum:(NSString *)authCode
          pubkey:(NSData *)pubkey popsign:(NSData *)popsign
           isNew:(BOOL)isNew;
- (int)updateInit:(RSKSWCertificate *)oldCert password:(NSString *)pwd expub:(NSData *)expub;
- (int)issueMain;
- (int)updateMain;
- (int)issueFinal:(NSString *)pwd cert:(NSData *)cert authnrId:(int)authnrId authnrName:(NSString *)authnrName;
- (int)updateFinal:(NSString *)pwd objCert:(RSKSWCertificate *)cert authnrId:(int)authnrId authnrName:(NSString *)authnrName;
- (int)cmpSave;
- (NSString *)getCmpError;
@end
