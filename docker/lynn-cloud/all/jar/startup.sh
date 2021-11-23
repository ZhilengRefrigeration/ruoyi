#!/usr/bin/env bash

nohup java -jar lynn-gateway.jar > /home/lynn/logs/lynn-gateway.log &
nohup java -jar lynn-auth.jar > /home/lynn/logs/lynn-auth.log &
nohup java -jar lynn-modules-system.jar > /home/lynn/logs/lynn-system.log &
nohup java -jar lynn-magic-api.jar > /home/lynn/logs/lynn-magic-api.log &
tail -f /home/lynn/logs/lynn-gateway.log