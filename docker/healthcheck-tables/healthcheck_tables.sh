#!/bin/bash

sqlplus -S sys/171204@oracledb as sysdba @/healthcheck-tables/verify-exist-tables.sql > output.tmp

num_morador=$(grep -o 'T_MORADOR' output.tmp | wc -l)
num_bairro=$(grep -o 'T_BAIRRO' output.tmp | wc -l)
num_motorista=$(grep -o 'T_MOTORISTA' output.tmp | wc -l)
num_caminhao=$(grep -o 'T_CAMINHAO' output.tmp | wc -l)

if [ $num_morador -ge 1 ] && [ $num_bairro -ge 1 ] && [ $num_motorista -ge 1 ] && [ $num_caminhao -ge 1 ]; then
    total_tabelas=$((num_morador + num_bairro + num_motorista + num_caminhao))
    if [ $total_tabelas -eq 4 ]; then
        rm output.tmp
        exit 0
    
    fi
else
    rm output.tmp
    exit 1
fi

