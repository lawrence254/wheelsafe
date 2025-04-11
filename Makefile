# Variables
DEV_COMPOSE=docker-compose -f docker-compose.dev.yml
PROD_COMPOSE=docker-compose -f docker-compose.prod.yml
JAR_NAME=app.jar

.PHONY: help dev-up dev-down dev-build prod-up prod-down prod-build clean logs

help:
	@echo ""
	@echo "Spring Boot Docker Makefile Commands:"
	@echo "-------------------------------------"
	@echo " make dev-up       - Start dev environment"
	@echo " make dev-down     - Stop dev environment"
	@echo " make dev-build    - Build app image for dev"
	@echo " make prod-up      - Start production environment"
	@echo " make prod-down    - Stop production environment"
	@echo " make prod-build   - Build app image for production"
	@echo " make clean        - Remove all Docker images and volumes"
	@echo " make logs         - Tail logs for the app (dev)"
	@echo ""

dev-up:
	$(DEV_COMPOSE) up -d

dev-down:
	$(DEV_COMPOSE) down

dev-build:
	$(DEV_COMPOSE) build

prod-up:
	$(PROD_COMPOSE) up -d

prod-down:
	$(PROD_COMPOSE) down

prod-build:
	$(PROD_COMPOSE) build

clean:
	docker system prune -af --volumes

logs:
	$(DEV_COMPOSE) logs -f app
