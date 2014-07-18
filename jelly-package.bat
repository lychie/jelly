@echo off

	TITLE jelly package

	COLOR 3E
	
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  
	echo [INFO] packaging jelly, this may take a few minutes, please wait . . .
	echo [INFO]  
	echo [INFO] ------------------------------------------------------------------------

	cd %~dp0
	
	if exist core\target rd /q /s core\target
	
	if exist examples\webapp\target rd /q /s examples\webapp\target
	
	call mvn package -Dmaven.test.skip=true

	echo [INFO]  
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  
	echo [INFO] Finish! Jelly Cases have been to skipped.
	echo [INFO]  
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  

pause