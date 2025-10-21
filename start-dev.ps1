Write-Host "Starting Adventure Club Project in development mode..." -ForegroundColor Green

# Копируем .env пример если нет .env
if (!(Test-Path .env)) {
    Write-Host "Copying .env.example to .env" -ForegroundColor Yellow
    Copy-Item .env.example .env
}

# Запускаем базу данных и API
docker-compose -f docker-compose.dev.yml up --build

Write-Host "Project is running!" -ForegroundColor Green
Write-Host "API: http://localhost:5000" -ForegroundColor Cyan
Write-Host "PostgreSQL: localhost:5432" -ForegroundColor Cyan
Write-Host "PgAdmin: http://localhost:8080 (admin@adventureclub.com / admin123)" -ForegroundColor Cyan