//
//  SpiderCert.h
//  espiderOSX
//
//  Created by htjulia on 5/31/16.
//  Copyright (c) 2016 Heenam. All rights reserved.
//

#import <Foundation/Foundation.h>


#define ENGINE_CERT_VERSION 				@"cert.version" 			// 3								: version
#define ENGINE_CERT_SERIAL                  @"cert.serialNumber" 		// 99868064							: serial
#define ENGINE_CERT_ISSURE_NAME_CN          @"cert.issuername.CN" 		// SignKorea CA2 					: common name
#define ENGINE_CERT_ISSURE_NAME_C 			@"cert.issuername.C" 		// KR 								: country
#define ENGINE_CERT_ISSURE_NAME_O 			@"cert.issuername.O" 		// SignKorea 						: organization
#define ENGINE_CERT_ISSURE_NAME_OU          @"cert.issuername.OU" 		// AccreditedCA 					: organization unit
#define ENGINE_CERT_SUBJECT_NAME_CN 		@"cert.subjectname.CN" 		// User()008801520151102488000177 	: common name
#define ENGINE_CERT_SUBJECT_NAME_C          @"cert.subjectname.C" 		// KR 								: country
#define ENGINE_CERT_SUBJECT_NAME_O          @"cert.subjectname.O" 		// SignKorea 						: organization
#define ENGINE_CERT_SUBJECT_NAME_OU 		@"cert.subjectname.OU" 		// personal4IB|KMB					: organization unit
#define ENGINE_CERT_VALIDITY_NOT_BEFORE  	@"cert.validity.notBefore"	// 1446393840 						: not valid before
#define ENGINE_CERT_VALIDITY_NOT_AFTER	 	@"cert.validity.notAfter" 	// 1478066399						: not valit after
#define ENGINE_CERT_SIGNATURE_ALGORISM      @"cert.signature.algorism" 	// 668								: Signature algorism
#define ENGINE_CERT_KEY_ALGORISM 			@"cert.key.algorism" 		// 6								: publickey algorism
#define ENGINE_CERT_FINGER_PRINT_MD5 		@"cert.fingerprint.MD5" 	// finger print MD5                 : md5 hex string
#define ENGINE_CERT_FINGER_PRINT_SHA1 		@"cert.fingerprint.SHA1"; 	// finger print SHA1                : sha1 hex string
#define ENGINE_CERT_DER_FILE_PATH 			@"cert.der.path" 			// der file path                    : der file path
#define ENGINE_CERT_KEY_FILE_PATH 			@"cert.key.path" 			// key file path                    : key file path
#define ENGINE_CERT_EXT_POLICY_ID 			@"cert.extension.policyid" 	// policy id                        : pid

@interface SpiderCert : NSObject

/**
 * @abstract e-spider sharedCert is sigleton class method
 *
 * @return spiderCert {SpiderCert}
 * @since e-spider v1.2.2 and later.
 */
+ (id)sharedCert;


//only mac support
#if (TARGET_OS_MAC && !(TARGET_OS_EMBEDDED || TARGET_OS_IPHONE))
/**
 * @abstract e-spider getCertifications
 *
 * @param location {NSString} certification location "" is system cert else external drive
 * @param length {NSUInteger *} return certification count
 * @return certifications json string(array) {NSString}
 * @since e-spider v1.2.2 and later.
 */
- (NSString *)getCertifications:(NSString *)location length:(NSUInteger *)length;


/**
 * @abstract e-spider getExternalDrive
 *
 * @return exteranl mount {NSArray}
 * @since e-spider v1.2.2 and later.
 */
- (NSArray *)getExternalDrive;


/**
 * @abstract e-spider getSystemPath
 *
 * @return mac certification location {NSString}
 * @since e-spider v1.2.2 and later.
 */
- (NSString *)getSystemPath;
#endif


/**
 * @abstract e-spider getCertification
 *
 * @param derpath {NSString} der format cert file path
 * @param keypath {NSString} der format key file path
 * @return certifications json string {NSString}
 * @since e-spider v1.2.2 and later.
 */
- (NSString *)getCertification:(NSString *)derpath keypath:(NSString *)keypath;


/**
 * @abstract e-spider getCertificationsFromPath
 *
 * @param fullPath {NSString} certification path (all full scan search)
 * @param length {NSUInteger *} return certification count
 * @return certifications json string {NSString}
 * @since e-spider v1.2.2 and later.
 */
- (NSString *)getCertificationsFromPath:(NSString *)fullPath length:(NSUInteger *)length;


/**
 * @abstract e-spider getCertificationsFromPath
 *
 * @param keypath {NSString} der format key file path
 * @param password {NSString} key file password
 * @return password correct {BOOL}
 * @since e-spider v1.2.2 and later.
 */
- (BOOL)checkPassword:(NSString *)keypath password:(NSString *)password;


/**
 * @abstract e-spider getCertificationsFromPath
 *
 * @param keypath {NSString} der format key file path
 * @param oldPassword {NSString} key file password
 * @param newPassword {NSString} new password
 * @return change correct {BOOL}
 * @since e-spider v1.2.2 and later.
 */
- (BOOL)changePassword:(NSString *)keypath oldPassword:(NSString *)oldPassword newPassword:(NSString *)newPassword;


/**
 * @abstract e-spider exportCertification
 *
 * @discussion certification export p12
 * @param derpath {NSString} der format cert file path
 * @param keypath {NSString} der format key file path
 * @param certPassword {NSString} key file password
 * @param exportPassword {NSString} export password
 * @return export success {BOOL}
 * @since e-spider v1.2.2 and later.
 */
- (BOOL)exportCertification:(NSString *)savefile derpath:(NSString *)derpath keypath:(NSString *)keypath certPassword:(NSString *)certPassword exportPassword:(NSString *)exportPassword;


/**
 * @abstract e-spider importCertification
 *
 * @discussion certification import p12
 * @param p12filePath {NSString} p12 file path
 * @param saveDerFile {NSString} saving der format cert file path
 * @param saveKeyFile {NSString} saving der format key file path
 * @param importPassword {NSString} key file password
 * @param newPassword {NSString} new password
 * @return import success {BOOL}
 * @since e-spider v1.2.2 and later.
 */
- (BOOL)importCertification:(NSString *)p12filePath saveDerFile:(NSString *)saveDerFile saveKeyFile:(NSString *)saveKeyFile importPassword:(NSString *)importPassword newPassword:(NSString *)newPassword;


/**
 * @abstract e-spider importCertificationB64
 *
 * @discussion certification import p12
 * @param b64String {NSString} p12 base64 string
 * @param saveDerFile {NSString} saving der format cert file path
 * @param saveKeyFile {NSString} saving der format key file path
 * @param importPassword {NSString} key file password
 * @param newPassword {NSString} new password
 * @return import success {BOOL}
 * @since e-spider v1.2.2 and later.
 */
- (BOOL)importCertificationB64:(NSString *)b64String saveDerFile:(NSString *)saveDerFile saveKeyFile:(NSString *)saveKeyFile importPassword:(NSString *)importPassword newPassword:(NSString *)newPassword;


/**
 * @abstract e-spider exportCertificationB64
 *
 * @discussion certification export p12 base64 format
 * @param derpath {NSString} der format cert file path
 * @param keypath {NSString} der format key file path
 * @param certPassword {NSString} key file password
 * @param exportPassword {NSString} export password
 * @return export base64 string {NSString} null or "" is fail
 * @since e-spider v1.2.2 and later.
 */
- (NSString *)exportCertificationB64:(NSString *)derpath keypath:(NSString *)keypath certPassword:(NSString *)certPassword exportPassword:(NSString *)exportPassword;



@end
