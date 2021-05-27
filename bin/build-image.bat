@echo off
echo.
echo [信息] 创建镜像
echo.


cd %~dp0
cd ..

set version=1.0.0

echo [信息] 创建[ruoyi-auth:%version%]镜像
cd ./ruoyi-auth
call docker build -t ruoyi-auth:%version% .

echo [信息] 创建[ruoyi-gateway:%version%]镜像
cd ../ruoyi-gateway
call docker build -t ruoyi-gateway:%version% .

echo [信息] 创建[ruoyi-monitor:%version%]镜像
cd ../ruoyi-visual/ruoyi-monitor
call docker build -t ruoyi-monitor:%version% .

cd ../../ruoyi-modules

echo [信息] 创建[ruoyi-file:%version%]镜像
cd ./ruoyi-file
call docker build -t ruoyi-file:%version% .

echo [信息] 创建[ruoyi-gen:%version%]镜像
cd ../ruoyi-gen
call docker build -t ruoyi-gen:%version% .

echo [信息] 创建[ruoyi-job:%version%]镜像
cd ../ruoyi-job
call docker build -t ruoyi-job:%version% .

echo [信息] 创建[ruoyi-system:%version%]镜像
cd ../ruoyi-system
call docker build -t ruoyi-system:%version% .

pause