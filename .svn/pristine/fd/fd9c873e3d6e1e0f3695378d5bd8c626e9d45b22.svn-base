//
//  RSKSWUtils.h
//  TouchEn_wireless
//
//  Created by sjyang on 2017. 2. 2..
//  Copyright © 2017년 RaonSecure. All rights reserved.
//
//


#import <Foundation/Foundation.h>

#import "RSKSW_define.h"


@interface RSKSWUtils : NSObject    {
    NSData *index;
    NSData *iv;
    NSData *key;
}
@property (nonatomic, retain) NSData *index;
@property (nonatomic, retain) NSData *iv;
@property (nonatomic, retain) NSData *key;
+ (NSString *) getVersion;
+ (NSData *) genSecRandom:(int)length;
+ (NSMutableString *)encode:(NSData *)src encoding:(int)encodingFlag error:(int *)err;
+ (NSMutableData *)decode:(NSString *)src encoding:(int)decodingFlag error:(int *)err;
+ (NSData *)extractCert:(NSString *)certInfo option:(int)opt error:(int *)err;
+ (NSString *)getOsVersion;
+ (NSString *)UDID;//return
+ (NSString *)UUID;//return
+ (NSString *)getPathinDocuments:(NSString *)filePathOnDoc;
@end

int _getPasswdFrom_mTEnc(unsigned char* output, int* outputLen, unsigned char* random, int randomLen, unsigned char* encData, int encDataLen);
int __get_mTSeedKey(unsigned char* output, int* outLen, unsigned char* random, int randomLen);
