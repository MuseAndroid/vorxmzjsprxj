#ifndef  __ANDROID__


#ifndef __RSKSWSOCKET_H__
#define __RSKSWSOCKET_H__

#ifdef WIN32
#include <winsock.h>
#else
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h> /* for AIX */
#include <string.h>
/* #include <sys/time.h> */
#endif


/*
   SOCKET definition :
   typedef u_int   SOCKET; (in WIN32)
   there is no definition of SOCKET in unix OS 
   it need to define SOCKET type to 'int' in unix OS...
*/
#ifndef WIN32
#define SOCKET int
#endif

#ifdef  __cplusplus
extern "C" {
#endif

int __rs_net_CLNTSOCK_INIT	(void);
    int SRVSOCK_INIT (int port);
int __rs_net_CONNECT (char *IP, unsigned short port, int timeout);
int __rs_net_RECV			(SOCKET s, unsigned char *vbuf, int len);
int __rs_net_SEND			(SOCKET s, unsigned char *vbuf, int len);
void __rs_net_CLOSESOCK		(SOCKET sock);
int __rs_net_GETSOCKERR		(void);
int ACCEPT (SOCKET master, int timeout);

#ifdef  __cplusplus
}
#endif

#endif

#endif
