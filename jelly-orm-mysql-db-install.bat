@echo off

	TITLE Install  jelly  orm  demo  db  for  mysql

	COLOR 3E
	
	echo.
	
	SET /p MYSQL_USERNAME=[TYPE] mysql username :  
	
	echo.
	
	SET /p MYSQL_PASSWORD=[TYPE] mysql password :  
	
	echo.
	
	call mysql -u%MYSQL_USERNAME% -p%MYSQL_PASSWORD% --default-character-set=utf8 < examples\resources\jelly_orm_demo_db
	
	echo.
	
	if ERRORLEVEL 1 GOTO ERROR
	
	:FINISH
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  
	echo [INFO] Finished to create database `jelly_orm_demo_db`
	echo [INFO]  
	echo [INFO] Twenty four queries executed successfully, good-bye
	echo [INFO]  
	echo [INFO] ------------------------------------------------------------------------
	GOTO END
	
	:ERROR
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  
	echo [INFO] Fail£¡The username or password is incorrect, please recheck.
	echo [INFO]  
	echo [INFO] ------------------------------------------------------------------------
	
	:END
	
	echo.
	
pause
