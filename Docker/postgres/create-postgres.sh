#!/bin/bash
set -e


SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

# Load DATABASE_NAME from .env if present (avoid exporting whole file)
if [ -f .env ]; then
  DATABASE_NAME=$(grep -E '^DATABASE_NAME=' .env | cut -d'=' -f2- | tr -d '\r')
fi
# If DATABASE_NAME not found, fall back to 'postgres'
if [ -z "$DATABASE_NAME" ]; then
  DATABASE_NAME=postgres
fi

echo "================ Parando containers ================"
docker compose down || true

# If a container with the same name exists outside of compose, remove it to avoid conflicts
EXISTING_CONTAINER_NAME="postgres-almeidajj"
if docker ps -a --format '{{.Names}}' | grep -q "^${EXISTING_CONTAINER_NAME}$"; then
  echo "Found existing container ${EXISTING_CONTAINER_NAME}, removing it"
  docker rm -f "${EXISTING_CONTAINER_NAME}" || true
fi

echo "================ Removendo volume do Postgres ================"
# Try to remove compose-managed volume; ignore errors
docker volume rm almeida-jj-api_db_data || true

echo "================ Subindo Postgres ================"
docker compose up -d postgres-almeidajj || true

echo "================ Aguardando Postgres ficar pronto ================"
# Use postgres user for readiness check
until docker compose exec -T postgres-almeidajj pg_isready -U "postgres" -h 127.0.0.1; do
  sleep 5
done

echo "================ Garantindo banco de dados ================"
if [ -n "$DATABASE_NAME" ]; then
  docker compose exec -T postgres-almeidajj \
    psql -U postgres -c "CREATE DATABASE \"$DATABASE_NAME\";" || true
else
  echo "DATABASE_NAME not set in .env; skipping explicit CREATE DATABASE"
fi

echo "================ Executando init.sql ================"
docker compose exec -T postgres-almeidajj \
  psql -U postgres -d "$DATABASE_NAME" -f /docker-entrypoint-initdb.d/init.sql

echo "================ Executando populate.sql ================"
docker compose exec -T postgres-almeidajj \
  psql -U postgres -d "$DATABASE_NAME" -f /docker-entrypoint-initdb.d/populate.sql

echo "================ Postgres pronto ================"
