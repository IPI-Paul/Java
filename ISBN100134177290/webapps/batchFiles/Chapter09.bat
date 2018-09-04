@echo off

cd ../../

set jar=Chapter09
set pack=chapter09

jar cvfe %jar%.jar %pack%.%jar% %pack%/*.class %pack%/*.html ipi/*.class sourceFiles/permissions/*ch09.* sourceFiles/settings/*ch09.* webapps/*ch09.jar sourceFiles/text/*ch09.* sourceFiles/keys/*ch09.*

move /Y %jar%.jar webapps/

jarsigner -keystore "ipi/IPI International.jks" webapps/%jar%.jar "IPI International"

set NLM=^

set NL=^^^%NLM%%NLM%^%NLM%  
echo. %NL%

echo.
echo. %NL%

echo.
echo Do you want to run the web start Application? (S) Signed; (U) Unsigned; (N) Neither:
set /p accept=
if %accept% == S goto signed
if %accept% == s goto signed
if %accept% == U goto unsigned
if %accept% == u goto unsigned
exit

:signed
"webapps/webstart/signed/%jar% Signed.jnlp"
exit

:unsigned
"webapps/webstart/unsigned/%jar%.jnlp"
exit