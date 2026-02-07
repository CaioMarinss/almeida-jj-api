#!/bin/bash
set -e

echo "🚀 Ambiente DEV - Almeida JJ"

echo "🐬 Subindo MySQL..."
./Docker/mysql/create-mysql.sh

echo "☕ Subindo Backend..."
./Docker/backend/create-backend.sh

echo "✅ Ambiente DEV pronto!"
