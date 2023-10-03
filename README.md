# 웹 소설 서비스 API

### 테이블 설계

### 동시성 이슈 해결
- 에피소드 구매가 여러 번 발생하는 경우를 방지하기 위해 에피소드 구매 히스토리 테이블의 unique constraint를 통해 특정 유저가 특정 소설의 특정 에피소드를 중복 구매할 수 있는 이슈를 방지함.
- 유저는 포인트 정보를 가지고 있는데 낙관적 락을 사용하여 포인트 차감 혹은 충전 시 동시성 이슈 발생을 방지함

### 성능 개선
- 베스트 셀러 소설 조회는 요청이 많은 API일 수 있기 때문에 레디스 캐시를 사용하며 일정 주기마다 내부 스케줄러를 통해 갱신함. 실제 서비스에서는 API 서버 별이 아닌 별도의 스케줄러를 통해 실행. 
  그리고 실제 API 요청에 대한 응답은 레디스에 저장된 데이터를 반환함. 

## API 문서

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