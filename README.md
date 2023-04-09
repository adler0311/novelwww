# Web Novel Application API

This document provides a brief overview of the API, including request headers, bodies, responses, and error codes.

## API Documentation

### Create a Novel

- **URL**: `/api/novels`
- **Method**: `POST`
- **Request Header**: N/A
- **Request Body**:

```json
{
    "title": "소설 제목",
    "author": "작가 이름",
    "description": "소설 설명",
    "genre": "소설 장르",
    ...
}
```

#### Success Response

- **Code**: `201 Created`
- **Content**:

```json
{
    "message": "소설이 성공적으로 등록되었습니다.",
    "novelId": 1234
}
```

#### Error Response

- **Code**: `400 Bad Request`
