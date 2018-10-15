#ifndef	__RSKSWDEBUG_H__
#define	__RSKSWDEBUG_H__

#ifdef __ANDROID__
    #include <android/log.h>
    #define RSKSW_printf LOGE

    #define LOG_TAG "RaonTag"

    #define LOGV(...)	__android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG, __VA_ARGS__)
    #define LOGD(...)	__android_log_print(ANDROID_LOG_DEBUG,   LOG_TAG, __VA_ARGS__)
    #define LOGI(...)	__android_log_print(ANDROID_LOG_INFO,    LOG_TAG, __VA_ARGS__)
    #define LOGW(...)	__android_log_print(ANDROID_LOG_WARN,    LOG_TAG, __VA_ARGS__)
    #define LOGE(...)	__android_log_print(ANDROID_LOG_ERROR,   LOG_TAG, __VA_ARGS__)
#else
#define RSKSW_printf printf
#endif


static int		_rsksw_debug_flag = 0;

#define HERE RSKSW_printf("[[%s%d]\n",__FILE__,__LINE__)

void
RSKSW_print_bin(char *title, unsigned char *data, int len);


#endif


