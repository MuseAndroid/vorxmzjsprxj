#ifndef _RSKSW_PKCS8UTIL_H_
#define _RSKSW_PKCS8UTIL_H_

#include "RSKSW_pkcs8.h"
#include "RSKSW_define.h"

int p8_changePassword(ks_uint8 *out, int *outLen, ks_uint8 *in, int inLen, ks_uint8 *oldPasswd, int oldPasswdLen, ks_uint8 *newPasswd, int newPasswdLen);

//return 1: pbes, 2:pbes2, -:err
int p8_checkPassword(ks_uint8 *in, int inLen, ks_uint8 *currentPasswd, int currentPasswdLen);

#endif

