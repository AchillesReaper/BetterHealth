@echo off

cd /D "C:\Program Files\MySQL\MySQL Server 8.0\bin"

set year=%date:~-4%
set month=%date:~-7,2%
set day=%date:~-10,2%

set dateStamp=%year%_%month%_%day%

set backupDestination="G:\My Drive\Database Backup\BH_DB_Backup_%dateStamp%.sql"


mysqldump.exe -u root -p!Q@W3e4r betterhealth > %backupDestination%

echo *****************************************************
echo *** your database is successfully backup in cloud ***
echo *****************************************************

pause
exit 0