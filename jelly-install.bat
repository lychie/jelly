@echo off

	TITLE jelly install

	COLOR 3E
	
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  
	echo [INFO] Installing jelly, this may take a few minutes, please wait . . .
	echo [INFO]  
	echo [INFO] ------------------------------------------------------------------------

	cd %~dp0
	
	call mvn install -Dmaven.test.skip=true
	
	rd /q /s bin
	
	md bin
	
	xcopy core\target\jelly*.GA.jar bin /Y
	
	rd /q /s javadoc
	
	md javadoc
	
	xcopy core\target\jelly*.GA-javadoc.jar javadoc /Y
	
	ren javadoc\jelly*.GA-javadoc.jar *.zip

	echo [INFO]  
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  
	echo [INFO] Finish! See javadoc directory use api, and jar is in the bin directory.
	echo [INFO]  
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  

pause