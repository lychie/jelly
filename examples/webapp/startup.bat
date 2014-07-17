@echo off

	TITLE jelly webapp demo startup

	COLOR 3E
	
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  
	echo [INFO] Make sure you have installed jell, if not yet, run jell-install.bat
	echo [INFO]  
	echo [INFO] Webapp URL : http://localhost/jelly-webapp/
	echo [INFO]  
	echo [INFO] Press `Ctrl + C` to stop
	echo [INFO]  
	echo [INFO] ------------------------------------------------------------------------
	echo [INFO]  

	cd %~dp0
	
	call mvn jetty:run

@echo on