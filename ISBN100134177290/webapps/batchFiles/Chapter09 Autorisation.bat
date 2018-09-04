@echo off

cd ../../

set pack=chapter09

set jar=login-ch09.jar
jar cvf %jar% %pack%/AuthTest.class

move /Y %jar% webapps/

jarsigner -keystore "ipi/IPI International.jks" webapps/%jar% "IPI International"

set NLM=^

set NL=^^^%NLM%%NLM%^%NLM%  
echo. %NL%

echo.
set jar=action-ch09.jar
jar cvf %jar% %pack%/SysPropAction.class

move /Y %jar% webapps/

jarsigner -keystore "ipi/IPI International.jks" webapps/%jar% "IPI International"

set NLM=^

set NL=^^^%NLM%%NLM%^%NLM%  
echo. %NL%
echo.
echo. %NL%
pause