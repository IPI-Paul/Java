@echo off

cd ../../

set jar=IPI Paul - Java Methods
set pack=ipi

jar cvfe "%jar%.jar" %pack%.Methods %pack%/Methods*.class sourceFiles/images/Earth.jpg 

move /Y "%jar%.jar" webapps/

jarsigner -keystore "ipi/IPI International.jks" "webapps/%jar%.jar" "IPI International"

set NLM=^

set NL=^^^%NLM%%NLM%^%NLM%  
echo. %NL%

echo.
echo. %NL%

echo.
echo Do you want to run the web start Application? (S) Signed; (N) Neither:
set /p accept=
if %accept% == S goto signed
if %accept% == s goto signed
exit

:signed
"webapps/webstart/signed/%jar%.jnlp"
exit