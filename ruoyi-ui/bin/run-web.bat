@echo off
@REM 后续命令使用的是：UTF-8编码
chcp 65001
echo.
echo [信息] 使用 Vue 运行 Web 工程。
echo.

%~d0
cd %~dp0

cd ..
npm run dev

pause
