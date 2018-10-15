//
//  RSKSWFileManager.h
//  RSKSW2
//
//  Created by sjyang on 2017. 9. 20..
//  Copyright © 2017년 RaonSecure. All rights reserved.
//
#import "RSKSW_define.h"

#import <Foundation/Foundation.h>

@interface RSKSWFileManager : NSObject
+ (RSKSWFileManager *) getInstance;
+ (int)getLibraryState;
@property(nonatomic, retain) NSData *seed;

- (int)encrypt:(NSString *)inFilePath outFile:(NSString *)outFilePath;
- (int)decrypt:(NSString *)inFilePath outFile:(NSString *)outFilePath;
- (int)decryptByLine_Init:(NSString*)inputFilePath;
- (int)decryptByLine_Update:(KSW_BYTE *)output;
- (int)decryptByLine_Final;
@end
