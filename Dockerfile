FROM payara/server-full
ENV DOMAIN domain1
ENV DEPLOY ${PAYARA_PATH}/glassfish/domains/${DOMAIN}/autodeploy/
ENTRYPOINT $PAYARA_PATH/bin/asadmin start-domain --verbose ${DOMAIN}
WORKDIR /opt/payara41/glassfish/bin
COPY target/pos-rest.war ${DEPLOY}