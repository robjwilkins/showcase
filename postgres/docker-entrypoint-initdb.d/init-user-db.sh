#!/usr/bin/env bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE USER showcase WITH PASSWORD 'password';
	CREATE DATABASE showcase;
	GRANT ALL PRIVILEGES ON DATABASE showcase TO showcase;
EOSQL
