SUMMARY = "Internal virtual network (guest side)"

PV = "0.1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit systemd

SRC_URI = " \
    file://eth0.network \
    file://eth0.link \
    file://systemd-networkd-wait-online.conf \
    file://dom0-network.service \
    file://aos-servicemanager-depends.conf \
"

S = "${WORKDIR}"

FILES_${PN} = " \
    ${sysconfdir}/systemd/network/eth0.network \
    ${sysconfdir}/systemd/network/eth0.link \
    ${sysconfdir}/systemd/system/systemd-networkd-wait-online.service.d/systemd-networkd-wait-online.conf \
    ${sysconfdir}/systemd/system/aos-servicemanager.service.d \
    ${systemd_system_unitdir}/dom0-network.service \
"

SYSTEMD_SERVICE_${PN} = "dom0-network.service"

RDEPENDS_${PN} = "systemd"

do_install() {
    install -d ${D}${sysconfdir}/systemd/network/
    install -m 0644 ${S}/eth0.network ${D}${sysconfdir}/systemd/network
    install -m 0644 ${S}/eth0.link ${D}${sysconfdir}/systemd/network

    install -d ${D}${sysconfdir}/systemd/system/systemd-networkd-wait-online.service.d
    install -m 0644 ${S}/systemd-networkd-wait-online.conf \
        ${D}${sysconfdir}/systemd/system/systemd-networkd-wait-online.service.d

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/dom0-network.service ${D}${systemd_system_unitdir}/

    # Override aos-servicemanager.service configuration
    install -d ${D}${sysconfdir}/systemd/system/aos-servicemanager.service.d
    install -m 0644 ${WORKDIR}/aos-servicemanager-depends.conf ${D}${sysconfdir}/systemd/system/aos-servicemanager.service.d
}
