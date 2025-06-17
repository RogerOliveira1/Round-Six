@echo off
REM Script para executar o Round 6 App no Windows
REM Certifique-se de que o Java está instalado

echo === Round 6 - Gerenciador de Reality Show ===
echo Iniciando aplicacao...

REM Verifica se o Java está instalado
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ERRO: Java nao esta instalado ou nao esta no PATH
    echo Por favor, instale o Java 11 ou superior
    pause
    exit /b 1
)

REM Mostra a versão do Java
echo Versao do Java detectada:
java -version

REM Executa a aplicação
if exist "Round6App.jar" (
    echo Executando a partir do arquivo JAR...
    java -jar Round6App.jar
) else if exist "src" (
    echo Executando a partir do codigo fonte...
    cd src
    java Round6App
) else (
    echo ERRO: Nao foi possivel encontrar o arquivo executavel
    echo Certifique-se de que Round6App.jar ou a pasta src estao presentes
    pause
    exit /b 1
)

pause

