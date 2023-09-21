#!/bin/bash

container_id=$(docker ps -qa)

if [ -z ${container_id} ]
then
        echo "현재 실행중인 컨테이너 X"
else
        echo "현재 실행중인 컨테이너: ${container_id}"
        echo "컨테이너 중지"
        sudo docker stop ${container_id}
        echo "컨테이너 삭제"
        sudo docker rm -f ${container_id}

        sleep 3
fi

echo "이미지 파일 Pull"
sudo docker pull adultkim/jpapractice:latest
sudo docker pull adultkim/jpa-nginx:latest

echo "컨테이너 재 시작"
docker-compose -f /home/ubuntu/app/docker/zip/docker-compose.yaml up -d
