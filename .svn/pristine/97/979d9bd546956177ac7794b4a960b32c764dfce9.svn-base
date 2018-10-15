#ifndef __RSKSWBIN_H__
#define __RSKSWBIN_H__

#include "RSKSW_define.h"

#ifdef  __cplusplus
extern "C" {
#endif

typedef struct _BIN {
    int     length;
    ks_uint8   *value;
} BIN;

BIN		*RSKSW_BIN_New				(int len, unsigned char *data);
BIN		*RSKSW_BIN_Copy				(BIN *bin);
BIN		*RSKSW_BIN_Merge				(BIN *bin1, BIN *bin2);
void	 RSKSW_BIN_Free				(BIN *bin);

typedef struct _BLIST {
	BIN 			*bin;
	struct _BLIST	*next;
} BLIST;

BLIST	*RSKSW_BLIST_New				();
BLIST	*RSKSW_BLIST_Copy				(BLIST *blist);
void	 RSKSW_BLIST_Free				(BLIST *blist);

#ifdef  __cplusplus
}
#endif

#endif
