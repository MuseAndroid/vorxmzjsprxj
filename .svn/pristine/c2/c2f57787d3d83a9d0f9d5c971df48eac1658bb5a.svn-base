#ifndef __RSKSWDER_H__
#define __RSKSWDER_H__

#include "RSKSW_define.h"

#ifdef __ANDROID__
#include "../cmvp/include/ks_der.h"
#else
#include "ks_der.h"
#endif

#ifdef  __cplusplus
extern "C" {
#endif

int		RSKSW_IntToByte (ks_uint8 *buf, int a);
int		RSKSW_ByteToInt (ks_uint8 *buf);

int		__rsksw_set_len(ks_uint8 * location, int len);							/* Length Encode : only support case length is less than 64K */
int		__rsksw_gen_len(ks_uint8 * location, int len);							/* Length Encode : only support case length is less than 64K */
int		__rsksw_gen_len2(ks_uint8 * location, int len);							/* Length Encode : only support case length is less than 2^32bytes */
int		__RSKSW_SEQUENCE(ks_uint8 *buf, ks_uint8 **location, int l);					/* SEQUENCE : length is less than 64K */
int		__RSKSW_CONTEXTSPECIFIC(ks_uint8 *buf, ks_uint8 **location, ks_uint8 spec, int l);	/* Context-Specific : P/C = 1 (constructed), length is less than 64K */
int		__RSKSW_CONTEXTSPECIFIC0(ks_uint8 *buf, ks_uint8 **location, ks_uint8 spec, int l);	/* Context-Specific : P/C = 0 (primitive), length is less than 64K */
int		__RSKSW_INTEGER(ks_uint8 *buf, int value);								/* Two's complement is used. But this assume value is not negative */
int		__RSKSW_BITSTRING(ks_uint8 *buf, ks_uint8 *data, int len, ks_uint8 unused_bit);		/* BITSTRING : length is less than 64K */
int		__RSKSW_OCTETSTRING(ks_uint8 *buf, ks_uint8 *data, int len);
int		__RSKSW_NULL(ks_uint8 *buf);
int		__RSKSW_PRINTABLESTRING(ks_uint8 *buf, char *str);
int     __RSKSW_UTF8(ks_uint8 *buf, char *str);

int		__rsksw_get_len(ks_uint8 *buf, int *len);
int		__RSKSW_dSEQUENCE(ks_uint8 *buf, int *len);								/* decode SEQUENCE : length is less than 64K */
int		__RSKSW_dCONTEXTSPECIFIC(ks_uint8 *buf, int *len, int *spec);			/* decode Context-Specific : P/C = 1 (constructed), length is less than 64K */
int		__RSKSW_dINTEGER(ks_uint8 *buf, int *num);								/* decode INTEGER : value is less than 32K */
int		__RSKSW_dBITSTRING(ks_uint8 *out, ks_uint8 *buf, int *len, int *unused_bit);	/* BITSTRING : length is less than 64K */
int		__RSKSW_dOCTETSTRING(ks_uint8 *out, ks_uint8 *buf, int *len);
int		__RSKSW_dUTF8STRING(ks_uint8 *out, ks_uint8 *buf, int *len);

#ifdef  __cplusplus
}
#endif

#endif
