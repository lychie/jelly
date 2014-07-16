@echo off

	TITLE jelly install

	COLOR 3E
	
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO] Installing jelly, this may take a few minutes, please wait . . .
	echo [INFO] ------------------------------------------------------------------------

	cd %~dp0
	cd ..
	
	call mvn package -Dmaven.test.skip=true
	
	ren target\jelly*.GA-javadoc.jar *.zip
	
	if not exist bin md bin

	xcopy target\jelly*.GA.jar bin /Y
	
	if not exist javadoc md javadoc

	xcopy target\jelly*.GA-javadoc.zip javadoc /Y
	
	md bin\classes

	xcopy target\classes bin\classes /E /Y

	rd /q /s target
	
	md target\classes
	
	xcopy bin\classes target\classes /E /Y

	rd /q /s bin\classes

	echo [INFO] ------------------------------------------------------------------------
	echo [INFO] Finish
	echo [INFO] ------------------------------------------------------------------------

@echo on