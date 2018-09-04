@echo off

dir /w

set NLM=^

set NL=^^^%NLM%%NLM%^%NLM%  
echo. %NL%

echo.
echo. %NL%

echo.
:sign
set /p jar=Which file do you want to sign (without extentsion '.jar'):

jarsigner -keystore "../ipi/IPI International.jks" %jar%.jar "IPI International"
echo. %NL%
set /p another=Do you want to sign another jar (Y/N):

if "%another%" == "Y" goto sign
if "%another%" == "y" goto sign
exit