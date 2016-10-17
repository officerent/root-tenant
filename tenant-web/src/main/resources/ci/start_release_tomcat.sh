#!/bin/bash
TOMCAT_HOME=/home/work/online/tomcat_8080
LOOP=0


echo "clear tomcat-common/work/Catalina && tomcat-common/temp"

if [ -d $TOMCAT_HOME/work/Catalina ]; then
	rm -rf $TOMCAT_HOME/work/Catalina/*
fi
if [ -d $TOMCAT_HOME/temp ]; then
	rm -rf $TOMCAT_HOME/temp/*
fi

sh $TOMCAT_HOME/bin/start_tomcat.sh &>/dev/null &
echo "after start tomcat"

# wait tomcat start finish
PID_NUM=`ps -ef|grep $TOMCAT_HOME|grep org.apache.catalina.startup.Bootstrap|grep -v grep|wc -l`
while [ $PID_NUM -lt 1 ]&&[ $LOOP -lt 5 ]; do
    echo "----- Waitting tomcat shutdown,sleep 3s"
    sleep 3;
    LOOP=`expr $LOOP + 1`;

    sh $TOMCAT_HOME/bin/start_tomcat.sh &>/dev/null &
    echo "after start tomcat"
    PID_NUM=`ps -ef|grep $TOMCAT_HOME|grep org.apache.catalina.startup.Bootstrap|grep -v grep|wc -l`
    echo "process number: "$PID_NUM
done

 if [ $PID_NUM -lt 1 ]; then
    echo "----- no start tomcat"
    exit 1
fi
echo "after stop"
 
ps -ef| grep $TOMCAT_HOME | exit 0