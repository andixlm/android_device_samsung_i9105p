# Include common BoardConfig
-include device/samsung/bcm281x5-common/BoardConfigCommon.mk

# Kernel
TARGET_KERNEL_CONFIG := cyanogenmod_s2vep_defconfig
TARGET_KERNEL_SOURCE := kernel/samsung/s2vep

# Bluetooth
BOARD_BLUETOOTH_BDROID_BUILDCFG_INCLUDE_DIR := device/samsung/s2vep/bluetooth

# Recovery
TARGET_RECOVERY_FSTAB := device/samsung/s2vep/ramdisk/fstab.capri_ss_s2vep
TARGET_USE_CUSTOM_LUN_FILE_PATH := "/sys/class/android_usb/android0/f_mass_storage/lun%d/file"

# inherit from the proprietary version
-include vendor/samsung/bcm281x5-common/BoardConfigVendor.mk
