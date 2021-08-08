@echo off
@REM 后续命令使用的是：UTF-8编码
chcp 65001
echo.
echo [信息] 打包Web工程，生成dist文件。
echo.

%~d0
cd %~dp0

cd ..
npm run build:prod

pause
