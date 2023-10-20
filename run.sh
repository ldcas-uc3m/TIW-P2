#!/bin/bash


JAVA_HOME=/usr/lib/jvm/java-17-openjdk-17.0.8.0.7-1.fc38.x86_64
path=$JAVA_HOME/bin
# firewall-cmd --add-port=4000/tcp

PAYARA_PATH=/opt/payara5

# launch payara (admin console in http://localhost:4848/)
sudo $PAYARA_PATH/bin/asadmin start-domain -d

# sudo $PAYARA_PATH/bin/asadmin create-local-instance instance-1
# sudo $PAYARA_PATH/bin/asadmin start-local-instance instance-1

# build EAR file ???
cd lab1/SesionesTIdW
jar -cvf *

# deploy EAR file
sudo $PAYARA_PATH/bin/asadmin deploy lab1/fuentes_de_uso/chatEAR.ear

# undeploy
sudo $PAYARA_PATH/bin/asadmin undeploy chatEAR


# sudo $PAYARA_PATH/bin/asadmin stop-local-instance instance-1