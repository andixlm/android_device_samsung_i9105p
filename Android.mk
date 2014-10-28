LOCAL_PATH := $(call my-dir)

ifeq ($(TARGET_DEVICE),s2vep)

include $(call all-makefiles-under,$(LOCAL_PATH))

endif
