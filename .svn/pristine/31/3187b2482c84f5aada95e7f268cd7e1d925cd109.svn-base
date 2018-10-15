#ifndef __RSKSWBIGINT_H_
#define __RSKSWBIGINT_H_

#include "RSKSW_define.h"

/* =================
 *    Basis types 
 * =================
 */

typedef struct{
    int			MemLength;
	int			Length;
	int			Sign;
	KSW_WORD		*Data;
} BigInt;



/* ===============
 *    Constants
 * ===============
 */


/***
 ***	If only 32 bit operations are available,
 ***

#define		INT32_Add(R, C, A, B, T )			\
{												\
	R = A + B;									\
	C = (((A ^ B ) & ~R ) | (A & B ) ) >> 31;	\
}



  ***
  ***
  ***/

#ifdef __cplusplus
extern "C" {
#endif

/* =========================
 *    Function prototypes
 * =========================
 */
 
int			KSW_Big_AbsoluteCompare		( BigInt *x, BigInt	*y);									// comparing with absolute values
KSW_WORD		KSW_Big_AbsoluteDecrease	( BigInt *x );
KSW_WORD		KSW_Big_AbsoluteIncrease	( BigInt *x );
KSW_WORD     KSW_Big_Add                 ( BigInt *x, BigInt *y, BigInt *z);
int			KSW_Big_BinaryExpansion		( KSW_BYTE *x, BigInt *y);									// binary representation
int			KSW_Big_ByteLength			( BigInt *x );
KSW_WORD		KSW_Big_ByteSequenceToInt	( BigInt *x, KSW_BYTE *y, int z );
int         KSW_Big_Compare             ( BigInt *x, BigInt *y);                                    // returns sign ( x - y)
KSW_WORD     KSW_Big_Copy                ( BigInt *x, BigInt *y);                                    // x <- y
BigInt      *KSW_Big_Create             ( int     x );												// BigInt creation
KSW_WORD		KSW_Big_DerDecode			( BigInt *x, KSW_BYTE *y, int z );
KSW_WORD		KSW_Big_DerEncode			( KSW_BYTE *x, BigInt *y, int *z );
KSW_WORD		KSW_Big_DerLength			( BigInt *x );
KSW_WORD     KSW_Big_DivByWord           ( BigInt *x, KSW_WORD *r, BigInt *y, KSW_WORD z);             // x = y / z
KSW_WORD     KSW_Big_Divide              ( BigInt *x, BigInt *r, BigInt *y, BigInt *z);              // y = x * z + r
KSW_WORD     KSW_Big_ExtendedGCD         ( BigInt *z, BigInt *a, BigInt *b, BigInt *x, BigInt *y);	// ax + by = gcd ( x, y)
void        KSW_Big_Free                ( BigInt *x);                                               // memory free
KSW_WORD     KSW_Big_GCD                 ( BigInt *x, BigInt *y, BigInt *z);                         // x = gcd ( y, z)
KSW_WORD		KSW_Big_IntToByteSequence	( KSW_BYTE *x, BigInt *y, int z );
KSW_WORD     KSW_Big_LeftShiftByBit      ( BigInt *x, BigInt *y, int shiftBitLength);				// x = y << shiftBitLength
KSW_WORD     KSW_Big_LeftShiftByWord     ( BigInt *x, BigInt *y, int shiftWordLength);				// x = y << ( shiftWordLength * 32)
KSW_WORD     KSW_Big_ModReduction        ( BigInt *x, BigInt *y, BigInt *z);                         // x = y ( mod z)
KSW_WORD		KSW_Big_ModReductionByWord	( BigInt *x, KSW_WORD y );
KSW_WORD		KSW_Big_ModExp  			( BigInt *x, BigInt *y, BigInt *z, BigInt *w);				// x = y^z  ( mod w )
KSW_WORD		KSW_Big_ModExpMont			( BigInt *x, BigInt *y, BigInt *z, BigInt *w);
KSW_WORD		KSW_Big_ModExpWindow		( BigInt *x, BigInt *y, BigInt *z, BigInt *w);
KSW_WORD		KSW_Big_ModExpWindowMont	( BigInt *x, BigInt *y, BigInt *z, BigInt *w);				// z = x^y mod m ( slide-window mwthod )
KSW_WORD     KSW_Big_ModInverse          ( BigInt *x, BigInt *y, BigInt *z);							// x = y^{-1} ( mod z)
KSW_WORD		KSW_Big_MontgomeryInit		( BigInt *x, KSW_WORD *y, BigInt *z );						// x = R^2 mod m and y = -m^(-1) mod b     
KSW_WORD		KSW_Big_MontgomeryReduction	( BigInt *z, BigInt *x, BigInt *m, KSW_WORD c);				// z = x(R^(-1)) mod m, where R = b^(length od m)
KSW_WORD		KSW_Big_MontgomeryMult		( BigInt *z, BigInt *x, BigInt *y, BigInt *m, KSW_WORD c);	// z = xy(R^(-1)) mod m, where R = b^(length od m)
KSW_WORD		KSW_Big_MontgomerySquare	( BigInt *z, BigInt *x, BigInt *m, KSW_WORD c);				// z = (x^2)(R^(-1)) mod m, where R = b^(length od m)
KSW_WORD     KSW_Big_Mult                ( BigInt *x, BigInt *y, BigInt *z);                         // x = y * z
KSW_WORD     KSW_Big_MultByWord          ( BigInt *x, BigInt *y, KSW_WORD z);                         // x = y * z
KSW_WORD     KSW_Big_Print               ( char   *x, BigInt *y);
KSW_WORD		KSW_Big_Realloc				( BigInt *x, int wordLength);
KSW_WORD     KSW_Big_Reset               ( BigInt *x, int y);                                        // x <- y
KSW_WORD     KSW_Big_RightShiftByBit     ( BigInt *x, BigInt *y, int shiftBitLength);				// x = y >> shiftBitLength
KSW_WORD     KSW_Big_RightShiftByWord    ( BigInt *x, BigInt *y, int shiftWordLength);				// x = y >> ( shiftWordLength * 32)
KSW_WORD     KSW_Big_Square              ( BigInt *x, BigInt *y);                                    // x = y ^ 2
KSW_WORD     KSW_Big_Sub                 ( BigInt *x, BigInt *y, BigInt *z);                         // x = y - z

int			KSW_Big_ModMul				(BigInt *result, BigInt *inputA, BigInt *inputB, BigInt *mod );	// x = y*z  ( mod w )
KSW_WORD		KSW_Big_GetFilledBitNum		( BigInt *a);


#ifdef __cplusplus
} // of extern "C"
#endif

																								
/* ==================
 *	  Error Define
 * ==================
 */

/* =====================
 *    Error Code
 * =====================
 */

#define		INVALID_INPUT					0xff
#define		MALLOC_FAILURE					1
#define		NOT_SUPPORTED_ALG				2
#define		INVALID_KEY						3
#define		VERIFY_FAIL						4

#define		BIGINT_ERROR						0x80010000
#define		BIGINT_INVALID_INPUT				(BIGINT_ERROR + INVALID_INPUT )
#define		BIGINT_MALLOC_FAILURE				(BIGINT_ERROR + 1 )
#define		BIGINT_DIVIDE_BY_ZERO				(BIGINT_ERROR + 0x21 )
#define		BIGINT_GCD_IS_NOT_ONE				(BIGINT_ERROR + 0x22 )
#define		BIGINT_DER_ERROR					(BIGINT_ERROR + 0x23 )


#endif
