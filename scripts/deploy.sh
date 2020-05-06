#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=spring_aws

echo "> Copy build files"
cp $REPOSITORY/zip/*.jar $REPOSIOTY/

echo "> check project application pid"
CURRENT_PID=$(pgrep -fl spring_aws | grep jar | awk'{print $1}')
echo "> Application PID: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> no application..."
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> deploy new Application..."
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
echo "> JAR name: $JAR_NAME"

echo "> Grant +x "
chmod +x $JAR_NAME
echo "> excute $JAR_NAME"

nohup java -jar -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties -Dspring.profiles.active=real $REPOSITORY/nohup.out 2>&1 &
