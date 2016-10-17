#!/bin/bash
#####SET_VER#####
BASEDIR=/home/work/elong
SER_NAME=web-bus
SHUTDOWN_PORT=7001
HTTP_PORT=8081
AJP_PORT=9001
SER_BASE_R='\/home\/work\/elong\web-bus/'
#####NO_NEED_2_SET#####
TOMCAT_NAME=tomcat_$HTTP_PORT
SER_BASE=$BASEDIR/$SER_NAME
SRCFILE=/home/work/tmp/template

#####START#####
echo start
if [ ! -d "$BASEDIR/$TOMCAT_NAME" ]; then
 exit $?
fi
$BASEDIR/$TOMCAT_NAME/bin/stop_tomcat.sh
exit $?
