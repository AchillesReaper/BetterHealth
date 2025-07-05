chcp 65001
@echo off

cd /D "cd /D "C:\tools\mysql\current\bin""

set year=%date:~-10,4%
set month=%date:~-5,2%
set day=%date:~-2%

set dateStamp=%year%_%month%_%day%

set backupDestination="G:\My Drive\Database Backup\BH_DB_Backup_%dateStamp%.sql"

echo %backupDestination%

mysqldump.exe -u root -p!Q@W3e4r betterhealth > %backupDestination%

echo *****************************************************
echo *** your database is successfully backup in cloud ***
echo *****************************************************

pause
exit 0