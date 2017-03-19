rem @echo off
COLOR fc


set dirPath=D:\01_study\02_java\01_framework\02_thrift\tcp_monitor5

MKDIR %dirPath%

SETLOCAL ENABLEDELAYEDEXPANSION

rem 局部变量需要用!
for /l %%i in (1,1,50) do (
ping localhost -n 3 > nul 

set currDateTime=!date:~0,4!!date:~5,2!!date:~8,2!!time:~0,2!!time:~3,2!!time:~6,2!
echo !currDateTime!
netstat -nao | find "8081" >> %dirPath%/tcp_monitor!currDateTime!.log
)