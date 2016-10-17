#!/bin/bash

# author : qiuliang
# date : 2015-10-15

# ===== 入参 =====
# ${1} war包下载地址路径
publish_root_url=${1}
# ${2} war包名称
war_file_name=${2}
# ${3} tomcat path
app_tomcat_path=${3}
# ===== 入参 end =====

# =====deploy变量=====
java_home=/home/work/jdk1.7.0_80
# 应用程序的context path，如果是根路径，变量值为 ROOT
app_context_path=cms
# 发布的war包下载地址
publish_url=$publish_root_url/$war_file_name
# =====deploy变量 end=====

# 进入tomcat应用程序目录
cd $app_tomcat_path/webapps
# 备份最近一次的包
mv $app_context_path.war $app_context_path.war.bak

cd $app_tomcat_path
wget $publish_url

# stop tomcat
export JAVA_HOME=$java_home
./bin/catalina.sh stop 0 -force

# 删除应用程序目录
cd $app_tomcat_path/webapps
rm -fr $app_tomcat_path/webapps/$app_context_path
# 重命名context path
mv ../$war_file_name $app_context_path.war
# 重新启动tomcat

cd $app_tomcat_path/bin/
./startup.sh