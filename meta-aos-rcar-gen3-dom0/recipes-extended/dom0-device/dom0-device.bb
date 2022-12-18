SUMMARY = "Internal virtual network (guest side)"

PV = "0.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit systemd

SRC_URI = " \
    file://dom0-device.service \
    file://aos-servicemanager-device.conf \
    file://dom0-add-device \
    file://dom0-rm-device \
"

S = "${WORKDIR}"

FILES_${PN} = " \
    ${systemd_system_unitdir}/dom0-device.service \
    ${sysconfdir}/systemd/system/aos-servicemanager.service.d/aos-servicemanager-device.conf \
    ${libdir}/xen/bin/dom0-add-device \
    ${libdir}/xen/bin/dom0-rm-device \
"

SYSTEMD_SERVICE_${PN} = "dom0-device.service"

RDEPENDS_${PN} = "systemd bash"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/dom0-device.service ${D}${systemd_system_unitdir}/

    # Override aos-servicemanager.service configuration
    install -d ${D}${sysconfdir}/systemd/system/aos-servicemanager.service.d
    install -m 0644 ${WORKDIR}/aos-servicemanager-device.conf ${D}${sysconfdir}/systemd/system/aos-servicemanager.service.d

    # Install dom0-add/rm-device scripts
    install -d ${D}${libdir}/xen/bin
    install -m 0744 ${WORKDIR}/dom0-add-device ${D}${libdir}/xen/bin
    install -m 0744 ${WORKDIR}/dom0-rm-device ${D}${libdir}/xen/bin

}
