# Spring Boot Signup REST API

A Spring Boot REST API that provides user signup functionality with robust password strength validation and confirmation password matching.

## Features

- ✅ User registration endpoint
- ✅ Password strength validation
  - Minimum 8 characters
  - At least 1 uppercase letter
  - At least 1 lowercase letter
  - At least 1 digit
  - At least 1 special character
- ✅ Password and confirmation password matching
- ✅ Email validation
- ✅ Comprehensive error handling
- ✅ RESTful API design

## Requirements

- Java 17+
- Maven 3.6+
- Spring Boot 3.3.3

## Setup

1. Clone the repository
```bash
git clone https://github.com/Arunkathir27/spring-boot-signup-api.git
cd spring-boot-signup-api
```

2. Build the project
```bash
mvn clean install
```

3. Run the application
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

### POST /api/signup

Register a new user.

**Request Body:**
```json
{
  "fullName": "John Doe",
  "email": "john@example.com",
  "password": "StrongPass1!",
  "confirmPassword": "StrongPass1!"
}
```

**Success Response (201 Created):**
```json
{
  "message": "Signup successful",
  "email": "john@example.com"
}
```

**Validation Error Response (400 Bad Request):**
```json
{
  "success": false,
  "errors": {
    "password": "Password must be at least 8 characters long, include uppercase, lowercase, number, and special character.",
    "passwordMatching": "Password and confirm password do not match"
  }
}
```

## Password Requirements

Passwords must meet the following criteria:

1. **Minimum 8 characters** long
2. **At least 1 uppercase letter** (A-Z)
3. **At least 1 lowercase letter** (a-z)
4. **At least 1 digit** (0-9)
5. **At least 1 special character** (!@#$%^&* etc.)

### Examples

✅ **Valid Passwords:**
- `StrongPass1!`
- `MyPass@123`
- `Secure#Password2`

❌ **Invalid Passwords:**
- `password` - no uppercase, no digit, no special char
- `Pass123` - no special character
- `Pass@` - less than 8 characters
- `UPPERCASE1!` - no lowercase

## Project Structure

```
src/main/java/com/example/signupapi/
├── SignupApiApplication.java          # Main Spring Boot application
├── controller/
│   └── AuthController.java             # REST endpoints
├── service/
│   └── SignupService.java              # Business logic
├── dto/
│   └── SignupRequest.java              # Request DTO with validation
├── validation/
│   ├── ValidPassword.java              # Custom password validation annotation
│   └── PasswordStrengthValidator.java   # Password strength validation logic
└── exception/
    └── GlobalExceptionHandler.java     # Global exception handling
```

## Testing with curl

**Valid signup:**
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

**Invalid password (too weak):**
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

**Passwords don't match:**
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

## Dependencies

- **spring-boot-starter-web** - Spring Web MVC framework
- **spring-boot-starter-validation** - Jakarta Validation API
- **lombok** - Boilerplate code reduction (optional)
- **spring-boot-starter-test** - Testing framework

## Future Enhancements

- Database integration (JPA/Hibernate)
- Password hashing with BCrypt
- Email verification
- User login endpoint
- JWT authentication
- Rate limiting
- CORS configuration

## License

This project is open source and available under the MIT License.

## Author

Arunkathir27
