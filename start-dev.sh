#!/bin/bash
set -e

echo "================ Ambiente DEV - Almeida JJ ================"

./Docker/postgres/create-postgres.sh

./Docker/backend/create-backend.sh

echo "================ Ambiente DEV pronto! ================"
