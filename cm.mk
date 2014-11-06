# Release name
PRODUCT_RELEASE_NAME := GT-I9105P

# Bootanimation
TARGET_SCREEN_WIDTH := 480
TARGET_SCREEN_HEIGHT := 800

# Inherit some common CM stuff.
$(call inherit-product, vendor/cm/config/common_full_phone.mk)

# Inherit device configuration
$(call inherit-product, device/samsung/s2vep/full_s2vep.mk)

## Device identifier. This must come after all inclusions
PRODUCT_DEVICE := s2vep
PRODUCT_NAME := cm_s2vep
PRODUCT_BRAND := samsung
PRODUCT_MANUFACTURER := samsung
PRODUCT_MODEL := GT-I9105P
