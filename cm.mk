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

# Set build fingerprint / ID / Product Name ect.
PRODUCT_BUILD_PROP_OVERRIDES += PRODUCT_NAME=s2vepxx TARGET_DEVICE=s2vep BUILD_FINGERPRINT="samsung/s2vepxx/s2vep:4.2.2/JDQ39/I9105PXXUBNG1:user/release-keys" PRIVATE_BUILD_DESC="s2vepxx-user 4.2.2 JDQ39 I9105PXXUBNG1 release-keys"
