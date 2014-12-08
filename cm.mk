$(call inherit-product, vendor/cm/config/common_full_phone.mk)

$(call inherit-product, vendor/cm/config/nfc_enhanced.mk)

$(call inherit-product, device/samsung/i9105p/full_i9105p.mk)

PRODUCT_DEVICE := i9105p
PRODUCT_NAME := cm_i9105p

PRODUCT_BUILD_PROP_OVERRIDES += \
	PRODUCT_NAME=s2vepxx \
	TARGET_DEVICE=s2vep \
	BUILD_FINGERPRINT="samsung/s2vepxx/s2vep:4.2.2/JDQ39/I9105PXXUBNG1:user/release-keys" \
	PRIVATE_BUILD_DESC="s2vepxx-user 4.2.2 JDQ39 I9105PXXUBNG1 release-keys"
