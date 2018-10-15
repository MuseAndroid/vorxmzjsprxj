
#ifndef __RSKSWUTIL_H__
#define __RSKSWUTIL_H__

#include <stdlib.h>
#include <strings.h>
#include "RSKSW_define.h"


#define ks_malloc	malloc
#define ks_free		free
#define RSKSW_sprintf		sprintf
#define RSKSW_strcpy      strcpy
#ifdef __cplusplus
extern "C" {
#endif
int RSKSW_hexencode(ks_uint8* out, ks_uint8* in, int inLen, int mode); //mode1: upper, mode0: lower
int RSKSW_hexdecode(ks_uint8* out, ks_uint8* buf, int inlen);

int RSKSW_encode(char* out, char *src, int srcLen, int encoding);
int RSKSW_decode(char* out, char *src, int srcLen, int encoding);

int RSKSW_PEM_encode (char *out, ks_uint8 *in, int in_len, char *pem_header);
int RSKSW_PEM_decode (ks_uint8 *output, char *pem, int pem_len);
    
void			*RSKSW_memcpy ( void *_out, const void *_in, unsigned int _num );
void			*RSKSW_memset (void *_src, int _char, unsigned int _num);
void			*RSKSW_memmove ( void *_dst, const void *_src, unsigned int _num );
int				RSKSW_memcmp (const void *arg1, const void *arg2, unsigned len);
    
unsigned int	RSKSW_strlen ( const char *  _src );
int				RSKSW_strcmp ( const char *  _src1, const char *  _src2 );
int				RSKSW_strncmp ( const char * _src1, const char * _src2, unsigned int _num );
//char*			RSKSW_strcpy ( char * _dst, const char * _src );
char*			RSKSW_strcat ( char * _dst, const char * _src );

//int             RSKSW_sprintf(char *_dest, char *fmt, ...); 

#ifdef __cplusplus
}
#endif

#endif
