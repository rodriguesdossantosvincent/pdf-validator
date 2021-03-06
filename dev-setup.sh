#!/usr/bin/env bash 

source ./helper-scripts.conf

EXTENSION='.tar.gz'
TOMCAT_FILENAME=apache-tomcat-${TOMCAT_VERSION}
TOMCAT_URL=http://www.eu.apache.org/dist/tomcat/tomcat-7/v${TOMCAT_VERSION}/bin/${TOMCAT_FILENAME}${EXTENSION}

echo "Current Tomcat version is: ${TOMCAT_VERSION}"
echo "Download URL is:  ${TOMCAT_URL}"

# Clean before download 
echo 'Cleaning up tomcats'
rm -fr ${TOMCAT_FILENAME}* 

echo 'Downloding new version of Tomcat'
http ${TOMCAT_URL} > ${TOMCAT_FILENAME}${EXTENSION}
tar xf ${TOMCAT_FILENAME}${EXTENSION} 

echo 'Creating directories for tomcat configuration'
mkdir -p ${PWD}/${TOMCAT_FILENAME}/conf/Catalina/localhost

WEBAPP_PATH="$PWD/pdf-validator-parent/pdf-validator-webapp/target/pdf-validator-webapp-${PDF_VALIDATOR_VERSION}"
MATCH='{webapp_path}'
WEBAPP_CONFIG_PATH=${PWD}/apache-tomcat-${TOMCAT_VERSION}/conf/Catalina/localhost
TEMPLATE_PATH=${PWD}/helpers/templates

cp ${TEMPLATE_PATH}/pdf-validator-webapp.xml ${WEBAPP_CONFIG_PATH}/pdf-validator-webapp.xml
cp ${TEMPLATE_PATH}/setenv.sh $PWD/apache-tomcat-${TOMCAT_VERSION}/bin/setenv.sh

ls -al ${WEBAPP_CONFIG_PATH}
sed -i.bak "s#$MATCH#$WEBAPP_PATH#g" ${WEBAPP_CONFIG_PATH}/pdf-validator-webapp.xml
