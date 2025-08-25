# DanceIns Platform - Troubleshooting Guide

## Issue: 401 Unauthorized Errors

The main issue causing 401 errors was a bug in the JWT authentication filter where it was checking for a space character instead of "Bearer " in the Authorization header.

## What Was Fixed

1. **JWT Filter Bug**: Fixed `authHeader.startsWith(" ")` to `authHeader.startsWith("Bearer ")` in `JwtAuthenticationFilter.java`
2. **Frontend Login Function**: Fixed parameter mismatch in the login function
3. **Added Debug Logging**: Added comprehensive logging to help troubleshoot future issues
4. **CORS Configuration**: Enhanced CORS configuration in SecurityConfig

## Steps to Test

### 1. Start the Backend
```bash
cd BACKEND/danceins
./mvnw spring-boot:run
```

### 2. Test Backend Connection
Visit: `http://localhost:8080/api/auth/test`
Should return: "Backend is running!"

### 3. Start the Frontend
```bash
cd FRONTEND/dance-frontend
npm install
npm run dev
```

### 4. Test Authentication Flow
1. Open browser to `http://localhost:5173`
2. Navigate to Register page
3. Create a test account
4. Try logging in
5. Check browser console and backend console for logs

## Debug Information

### Backend Logs
The backend now logs:
- JWT filter processing
- Authentication attempts
- Token generation
- User loading

### Frontend Logs
The frontend now logs:
- API requests
- Token presence
- Authorization headers
- Response errors

## Common Issues and Solutions

### Database Connection
- Ensure MySQL is running on port 3306
- Check database credentials in `application.properties`
- Verify database `dance_app` exists

### CORS Issues
- Backend is configured to allow `http://localhost:5173`
- Frontend makes requests to `http://localhost:8080/api`

### JWT Token Issues
- Tokens expire after 24 hours (86400000 ms)
- Secret key must be at least 32 characters
- Check browser localStorage for token presence

## File Structure
```
BACKEND/danceins/
├── src/main/java/com/example/danceins/
│   ├── security/
│   │   ├── JwtAuthenticationFilter.java (FIXED)
│   │   ├── JwtUtil.java
│   │   └── SecurityConfig.java (ENHANCED)
│   ├── controller/
│   │   └── AuthController.java (ENHANCED)
│   └── config/
│       └── WebConfig.java

FRONTEND/dance-frontend/
├── src/
│   ├── api/
│   │   └── apiClient.js (ENHANCED)
│   ├── context/
│   │   └── AuthContext.jsx
│   └── App.jsx (FIXED)
```

## Next Steps

1. Test the authentication flow
2. Check console logs for any remaining issues
3. Verify protected routes work correctly
4. Test user role-based access

If you still encounter issues, check the console logs for specific error messages and ensure both services are running on the correct ports.
