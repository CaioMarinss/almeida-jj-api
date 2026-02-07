#!/bin/bash
set -e

echo "================ Subindo Backend ================"

# builda a imagem (caso tenha mudado código)
docker compose build backend

# sobe somente o backend
docker compose up -d backend

echo "================ Backend rodando ================"
docker compose ps
