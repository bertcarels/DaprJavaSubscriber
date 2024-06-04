#!/bin/sh
cd /usr/src/app
if [ {{ .Values.applicationinsights.enabled }} == true ]
then 
   java -javaagent:"/usr/src/app/applicationinsights-agent.jar" -jar app.jar
else
   java -jar app.jar
fi      