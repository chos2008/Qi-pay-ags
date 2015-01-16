@echo off
echo ----------------------------------
set _cd=%cd%
echo Current dir: %_cd%

cd ..
set _temp=%cd%\dist\Qi-pg-game.war
echo Uploading war: %_temp%

rem scp -P 36000 -i C:\home\admin\git\glsg-server\server\scripts\secureCRT_Identity ./dist/qi_pay.sql root@180.153.225.177:/mnt/pay
scp -P 36000 -i C:\home\admin\git\glsg-server\server\scripts\secureCRT_Identity ./dist/Qi-pg-game.war root@180.153.225.177:/mnt/pay

cd %_cd%