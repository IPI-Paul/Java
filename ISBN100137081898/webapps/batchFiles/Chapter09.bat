@echo off

cd ../../

set jar=Chapter09
set pack=chapter09

jar cvfe %jar%.jar %pack%.%jar% %pack%/*.class ipi/*.class sourceFiles/images/blue-ball.gif sourceFiles/images/red-ball.gif sourceFiles/images/yellow-ball.gif sourceFiles/images/palette.gif sourceFiles/images/nine-of-hearts.gif sourceFiles/images/ten-of-hearts.gif sourceFiles/images/jack-of-hearts.gif sourceFiles/images/queen-of-hearts.gif sourceFiles/images/king-of-hearts.gif sourceFiles/images/ace-of-hearts.gif sourceFiles/images/copy.gif sourceFiles/images/cut.gif sourceFiles/images/paste.gif sourceFiles/images/crosses.gif

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