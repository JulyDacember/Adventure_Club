#!/bin/bash

echo "Stopping Adventure Club Project..."

docker-compose -f docker-compose.dev.yml down

echo "Project stopped!"