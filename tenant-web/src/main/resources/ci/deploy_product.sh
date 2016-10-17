#!/bin/bash

# author : zuoluliang
# date : 2015-10-20

# =====deploy变量=====

# 应用程序的context path，如果是根路径，变量值为 ROOT
app_context_path=cms
# 应用程序端口号
app_port=8085
# tomcat目录名称应该满足如下规范，例如：tomcat-端口号
app_tomcat_path=/home/work/tomcat-cms-8085
# build完成的war包名称
war_name=cms-api-1.0-SNAPSHOT.war
# war包地址
war_url='http://ci.sohochina.com/job/cms-api/com.soho3q.cms$cms-api/lastSuccessfulBuild/artifact/com.soho3q.cms/cms-api/1.0-SNAPSHOT/cms-api-1.0-SNAPSHOT.war'
# =====deploy变量 end=====

# 进入tomcat应用程序目录
cd $app_tomcat_path/webapps
# 备份最近一次的包
mv $app_context_path.war $app_context_path.war.bak
# 下载最后一次build的包，该地址需要根据应用具体地址修改
wget $war_url
# kill tomcat实例
tomcat_pid=`/usr/sbin/lsof -n -P -t -i :$app_port`
[ -n "$tomcat_pid" ] && kill -9 $tomcat_pid
# 删除应用程序目录
rm -fr $app_tomcat_path/webapps/$app_context_path
# 重命名context path
mv $war_name $app_context_path.war
# 重新启动tomcat
export JAVA_HOME=/usr/local/jdk1.7.0_79
cd $app_tomcat_path/bin/
./startup.sh