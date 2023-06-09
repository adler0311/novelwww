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
    "genre": "소설 장르"
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


### Purchase Episode

- **URL**: `/api/novels/{novelId}/episodes/{episodeId}/purchase`
- **Method**: `POST`
- **Request Header**: N/A
- **Request Body**:

```json
{
  "userId": "f85a97c9-d862-11ed-bbee-e86a642fe192"
}
```

#### Success Response

- **Code**: `200 OK`
- **Content**:

```json
{
    "message": "단편을 성공적으로 구매하였습니다.",
    "episodePurchaseId": 1234
}
```

#### Error Response

- **Code**: `409 Conflict`


### Get Best Seller Novels
- **URL**: `/api/novels/best-sellers`
- **Method**: `GET`
- **Request Header**: N/A
- **Request Body**:

#### Success Response

- **Code**: `200 OK`
- **Content**:

```json
{
  "items": [
    {
      "id": 1,
      "title": "Example Novel Title 1",
      "author": "Example Author",
      "description": "Example novel description",
      "genre": "Example Genre",
      "purchaseCount": 600
    },     {
      "id": 2,
      "title": "Example Novel Title 2",
      "author": "Example Author",
      "description": "Example novel description",
      "genre": "Example Genre",
      "purchaseCount": 600
    },     {
      "id": 3,
      "title": "Example Novel Title 3",
      "author": "Example Author",
      "description": "Example novel description",
      "genre": "Example Genre",
      "purchaseCount": 600
    }
  ]
}
```