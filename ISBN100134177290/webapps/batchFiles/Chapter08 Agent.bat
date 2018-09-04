@echo off

cd ../../

set jar=EntryLoggingAgent
set pack=chapter08

jar cvfm %jar%.jar webapps/manifests/%jar%-ch08.mf %pack%/Entry*.class

move /Y %jar%.jar webapps/

jarsigner -keystore "ipi/IPI International.jks" webapps/%jar%.jar "IPI International"

set NLM=^

set NL=^^^%NLM%%NLM%^%NLM%  
echo. %NL%

echo.
echo. %NL%
pause