#!/usr/bin/env bash
export IMAGE=$1
docker-compose -f /home/deniswork/Desktop/docker-compose.yaml up -d
echo "success"

