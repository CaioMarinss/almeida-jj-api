#!/bin/bash
set -e

echo "================ Ambiente DEV - Almeida JJ ================"

./Docker/mysql/create-mysql.sh

./Docker/backend/create-backend.sh

echo "================ Ambiente DEV pronto! ================"
