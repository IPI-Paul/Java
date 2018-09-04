@echo off

cd ../../

set pack=chapter09

set jar=JAASTest-login-ch09.jar
jar cvf %jar% %pack%/JAAS*.class %pack%/Simple*.class

move /Y %jar% webapps/

jarsigner -keystore "ipi/IPI International.jks" webapps/%jar% "IPI International"

set NLM=^

set NL=^^^%NLM%%NLM%^%NLM%  
echo. %NL%

echo.
set jar=JAASTest-action-ch09.jar
jar cvf %jar% %pack%/SysPropAction.class

move /Y %jar% webapps/

jarsigner -keystore "ipi/IPI International.jks" webapps/%jar% "IPI International"

set NLM=^

set NL=^^^%NLM%%NLM%^%NLM%  
echo. %NL%
echo.
echo. %NL%
pause