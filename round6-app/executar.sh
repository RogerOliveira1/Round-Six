#!/bin/bash

# Script para executar o Round 6 App
# Certifique-se de que o Java está instalado

echo "=== Round 6 - Gerenciador de Reality Show ==="
echo "Iniciando aplicação..."

# Verifica se o Java está instalado
if ! command -v java &> /dev/null; then
    echo "ERRO: Java não está instalado ou não está no PATH"
    echo "Por favor, instale o Java 11 ou superior"
    exit 1
fi

# Verifica a versão do Java
java_version=$(java -version 2>&1 | head -n 1 | cut -d'"' -f2)
echo "Versão do Java detectada: $java_version"

# Executa a aplicação
if [ -f "Round6App.jar" ]; then
    echo "Executando a partir do arquivo JAR..."
    java -jar Round6App.jar
elif [ -d "src" ]; then
    echo "Executando a partir do código fonte..."
    cd src
    java Round6App
else
    echo "ERRO: Não foi possível encontrar o arquivo executável"
    echo "Certifique-se de que Round6App.jar ou a pasta src estão presentes"
    exit 1
fi

