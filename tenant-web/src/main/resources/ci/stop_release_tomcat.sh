#!/bin/bash
TOMCAT_HOME=/home/work/online/tomcat_8080
PID=`ps -ef|grep $TOMCAT_HOME|grep org.apache.catalina.startup.Bootstrap|grep -v grep|awk '{print $2}'`
LOOP=0
 
# stop tomcat
sh $TOMCAT_HOME/bin/stop_tomcat.sh  &>/dev/null &

# wait tomcat shutdown finish
PID_NUM=`ps -ef|grep $TOMCAT_HOME|grep org.apache.catalina.startup.Bootstrap|grep -v grep|wc -l`
while [ $PID_NUM -ge 1 ]&&[ $LOOP -lt 5 ]; do
    echo "----- Waitting tomcat shutdown,sleep 5s"
    sleep 5;
    LOOP=`expr $LOOP + 1`;
    PID_NUM=`ps -ef|grep $TOMCAT_HOME|grep org.apache.catalina.startup.Bootstrap|grep -v grep|wc -l`
    echo "process number: "$PID_NUM
done
if [ $LOOP -ge 5 ]; then
    echo "----- Force kill Tomcat"
    kill -9 $PID;
fi
echo "after stop"
ps -ef|grep $TOMCAT_HOME|grep org.apache.catalina.startup.Bootstrap|grep -v grep ||exit 0
