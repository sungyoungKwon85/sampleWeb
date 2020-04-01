#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=kkwonsy-sample-web

echo "> Copy build file"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Check pid (running application)"
CURRENT_PID=$(pgrep -fl kkwonsy-sample-web | grep jar | awk 'print $1')

echo "pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> no running application.."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "> Deploy new application"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
  -Dspring.config.location=classpath:/application.yml,classpath:/application-real.yml, \
  /home/ec2-user/app/application-oauth.properties,home/ec2-user/app/application-real-db.yml \
  -Dspring.profiles.active=real \
  $JAR_NAME >$REPOSITORY/nohup.out 2>&1 &
