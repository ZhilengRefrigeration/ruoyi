@echo off
@REM 后续命令使用的是：UTF-8编码
chcp 65001
echo.
echo [信息] 清理生成路径。
echo.

%~d0
cd %~dp0

cd ..
call mvn clean

pause