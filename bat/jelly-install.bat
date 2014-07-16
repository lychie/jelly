@echo off

	TITLE jelly install

	COLOR 3E
	
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO] Installing jelly, this may take a few minutes, please wait . . .
	echo [INFO] ------------------------------------------------------------------------

	cd %~dp0
	cd ..
	
	call mvn clean package -Dmaven.test.skip=true
	
	ren target\jelly*.GA-javadoc.jar *.zip

	xcopy target\jelly*.GA-javadoc.zip javadocs /Y

	xcopy target\jelly*.GA.jar bin /Y

	rd /q /s target

	echo [INFO] ------------------------------------------------------------------------
	echo [INFO] Finish
	echo [INFO] ------------------------------------------------------------------------

@echo on