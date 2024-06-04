#!/bin/bash

if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <new_version>"
    exit 1
fi


docker build --build-context src=../src -t javawhlsub:$1 .  --platform linux/amd64
docker tag javawhlsub:$1 bcacr2023.azurecr.io/kab-whl/javawhlsub:$1
docker push bcacr2023.azurecr.io/kab-whl/javawhlsub:$1
