FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://misc.conf \
"

FILES_${PN} += " \
    ${sysconfdir}/tmpfiles.d/misc.conf \
"

do_install_append() {
        install -d ${D}${sysconfdir}/tmpfiles.d
        install -m 0644 ${WORKDIR}/misc.conf ${D}${sysconfdir}/tmpfiles.d
        echo "dhcp-host=08:00:27:ff:cb:cb,dom0,192.168.0.8,infinite" >> ${D}${sysconfdir}/dnsmasq.conf
}
