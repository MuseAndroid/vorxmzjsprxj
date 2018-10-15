#ifndef _RSKSW_MD5_H_
#define _RSKSW_MD5_H_

#include "RSKSW_define.h"

typedef struct {
	KSW_WORD		hashID;
	KSW_BYTE		hashValueSize;
	KSW_WORD		state[5];
	KSW_WORD		count[2];
	KSW_BYTE		buffer[64];
} HashContext;

#define MD5_DIGEST_LENGTH 16


/*****
 *****		Function ProtoTypes
 *****/

#ifdef __cplusplus
extern "C" {
#endif


KSW_WORD	RSKSW_Md5			(KSW_BYTE *hashValue, KSW_BYTE *input, KSW_WORD inputByteLength );

#ifdef __cplusplus
} // of extern "C"
#endif

#endif
