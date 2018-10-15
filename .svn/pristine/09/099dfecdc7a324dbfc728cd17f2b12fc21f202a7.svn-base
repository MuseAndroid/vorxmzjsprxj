#include "RSKSW_error.h"

#ifndef	__RSKSW_CMPERR_H__
#define	__RSKSW_CMPERR_H__

#define SETERR(ret)	__rsksw_set_err(__FILE__, __LINE__, (ret))

#ifdef  __cplusplus
extern "C" {
#endif

int __rsksw_set_err(char *file, int line, int code);
void __rsksw_set_pki_error(char *in);
	void __rsksw_set_pki_error_zero();

#ifdef  __cplusplus
}
#endif
#endif
