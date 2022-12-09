# Aos related tasks
INITRAMFS_MAXSIZE="141072"

IMAGE_POSTPROCESS_COMMAND += "set_image_version; "

IMAGE_INSTALL_append = " runx \
    openssh-sshd \
    haveged \
    dom0-network \
    dom0-device \
"

set_image_version() {
    install -d ${DEPLOY_DIR_IMAGE}/aos
    echo "VERSION=\"${DOM0_IMAGE_VERSION}\"" > ${DEPLOY_DIR_IMAGE}/aos/version
}
