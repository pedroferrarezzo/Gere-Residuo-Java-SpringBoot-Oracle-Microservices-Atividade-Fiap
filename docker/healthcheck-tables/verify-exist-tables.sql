SET ECHO OFF
SET FEEDBACK OFF
SET HEADING OFF
SET PAGESIZE 0
SET VERIFY OFF
SET LINESIZE 1000
SET TRIMSPOOL ON

SELECT table_name FROM all_tables WHERE table_name = upper('T_BAIRRO');
SELECT table_name FROM all_tables WHERE table_name = upper('T_MORADOR');
SELECT table_name FROM all_tables WHERE table_name = upper('T_MOTORISTA');
SELECT table_name FROM all_tables WHERE table_name = upper('T_CAMINHAO');
exit;
