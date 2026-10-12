# Docker Deployment Guide for Spring Boot Signup API

## Prerequisites

- Docker installed ([Download Docker](https://www.docker.com/products/docker-desktop))
- Docker Compose installed (included with Docker Desktop)

## Quick Start with Docker Compose

### 1. Clone the repository
```bash
git clone https://github.com/Arunkathir27/spring-boot-signup-api.git
cd spring-boot-signup-api
```

### 2. Build and run with Docker Compose
```bash
docker-compose up --build
```

The API will be available at `http://localhost:8080`

### 3. Stop the container
```bash
docker-compose down
```

---

## Alternative: Manual Docker Commands

### 1. Build the Docker image
```bash
docker build -t signup-api .
```

### 2. Run the container
```bash
docker run -p 8080:8080 --name signup-api signup-api
```

### 3. Stop the container
```bash
docker stop signup-api
```

### 4. Remove the container
```bash
docker rm signup-api
```

---

## Testing the API

### Valid Signup Request
```bash
curl -X POST http://localhost:8080/api/signup \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "John Doe",
    "email": "john@example.com",
    "password": "StrongPass1!",
    "confirmPassword": "StrongPass1!"
  }'
```

Expected Response (201 Created):
```json
{
  "message": "Signup successful",
  "email": "john@example.com"
}
```

### Test Invalid Password
```bash
curl -X POST http://localhost:8080/api/signup \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "John Doe",
    "email": "john@example.com",
    "password": "weak",
    "confirmPassword": "weak"
  }'
```

Expected Response (400 Bad Request):
```json
{
  "success": false,
  "errors": {
    "password": "Password must be at least 8 characters long, include uppercase, lowercase, number, and special character."
  }
}
```

### Test Password Mismatch
```bash
curl -X POST http://localhost:8080/api/signup \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "John Doe",
    "email": "john@example.com",
    "password": "StrongPass1!",
    "confirmPassword": "DifferentPass1!"
  }'
```

Expected Response (400 Bad Request):
```json
{
  "success": false,
  "errors": {
    "passwordMatching": "Password and confirm password do not match"
  }
}
```

---

## Docker Commands Reference

| Command | Description |
|---------|-------------|
| `docker-compose up --build` | Build and start the container |
| `docker-compose up -d` | Start in detached mode (background) |
| `docker-compose down` | Stop and remove the container |
| `docker-compose logs` | View container logs |
| `docker-compose logs -f` | Follow logs in real-time |
| `docker ps` | List running containers |
| `docker images` | List available images |

---

## Environment Variables

You can customize the deployment by editing `docker-compose.yml`:

```yaml
environment:
  - SPRING_APPLICATION_NAME=signup-api
  - SERVER_PORT=8080
```

---

## Image Details

- **Builder Stage**: Maven 3.9 with Eclipse Temurin 17
- **Runtime Stage**: Eclipse Temurin 17 JRE (lightweight)
- **Image Size**: Approximately 300-400 MB
- **Port**: 8080 (configurable)

---

## Troubleshooting

### Container won't start
```bash
docker-compose logs signup-api
```

### Port already in use
Change the port mapping in `docker-compose.yml`:
```yaml
ports:
  - "9090:8080"  # Map external port 9090 to container port 8080
```

### Clear everything and start fresh
```bash
docker-compose down -v  # -v removes volumes
docker system prune -a   # Remove unused images
docker-compose up --build
```

---

## Production Deployment Tips

1. Use specific image tags instead of `latest`
2. Set resource limits in `docker-compose.yml`:
   ```yaml
   deploy:
     resources:
       limits:
         cpus: '1'
         memory: 512M
   ```
3. Use environment files for sensitive data
4. Configure logging for production
5. Set up proper health checks (already included)

