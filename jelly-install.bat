@echo off

	TITLE jelly install

	COLOR 3E
	
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO] Installing jelly, this may take a few minutes, please wait . . .
	echo [INFO] ------------------------------------------------------------------------

	cd %~dp0
	
	call mvn clean install -Dmaven.test.skip=true

	echo [INFO] ------------------------------------------------------------------------
	echo [INFO] Finish
	echo [INFO] ------------------------------------------------------------------------

pause