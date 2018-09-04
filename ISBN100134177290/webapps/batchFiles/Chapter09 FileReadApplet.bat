@echo off

cd ../../

set jar=FileReadApplet-ch09.jar
set pack=chapter09

jar cvf %jar% %pack%/FileRead*.class sourceFiles/permissions/*applet*ch09.*

move /Y %jar% webapps/

jarsigner -keystore "ipi/IPI International.jks" webapps/%jar% "IPI International"

set NLM=^

set NL=^^^%NLM%%NLM%^%NLM%  
echo. %NL%

echo.
echo. %NL%
pause