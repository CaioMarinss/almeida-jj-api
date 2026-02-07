#!/bin/bash
set -e

# ================= Load env =================
set -a
source ./.env
set +a

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

echo "================ Parando containers ================"
docker compose down

echo "================ Removendo volume do MySQL ================"
docker volume rm almeida-jj-api_db_data || true

echo "================ Subindo MySQL ================"
docker compose up -d mysql-almeidajj

echo "================ Aguardando MySQL ficar pronto ================"
until docker compose exec -T mysql-almeidajj \
  mysqladmin ping -h 127.0.0.1 -u root -p"$MYSQL_ROOT_PASSWORD" --silent; do
  sleep 2
done

echo "================ Garantindo banco de dados ================"
docker compose exec -T mysql-almeidajj \
  mysql -u root -p"$MYSQL_ROOT_PASSWORD" \
  -e "CREATE DATABASE IF NOT EXISTS $MYSQL_DATABASE;"

echo "================ Executando init.sql ================"
docker compose exec -T mysql-almeidajj \
  mysql -u root -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" \
  < "$SCRIPT_DIR/init.sql"

echo "================ Executando populate.sql ================"
docker compose exec -T mysql-almeidajj \
  mysql -u root -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" \
  < "$SCRIPT_DIR/populate.sql"

echo "================ MySQL pronto ================"
