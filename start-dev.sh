#!/bin/bash

echo "Starting Adventure Club Project in development mode..."

# Копируем .env пример если нет .env
if [ ! -f .env ]; then
    echo "Copying .env.example to .env"
    cp .env.example .env
fi

# Запускаем базу данных и API
docker-compose -f docker-compose.dev.yml up --build

echo "Project is running!"
echo "API: http://localhost:5000"
echo "PostgreSQL: localhost:5432"
echo "PgAdmin: http://localhost:8080 (admin@adventureclub.com / admin123)"