@echo off
@REM 后续命令使用的是：UTF-8编码
chcp 65001
echo.
echo [信息] 运行modules-system工程。
echo.

cd %~dp0
cd ../ruoyi-modules/ruoyi-system/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 -jar %JAVA_OPTS% ruoyi-modules-system.jar

cd bin
pause