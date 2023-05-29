# Conference management service backend

This project is a backend service for an IT conference management website. It uses Java 17

---
## Running the service

You can run the project from your IDE of choice or using terminal: <br>
### Linux/MacOS:

`./mvnw clean install`, then `./mvnw spring-boot:run`
### Windows
`mvnw.cmd clean install`, then `mvnw.cmd spring-boot:run`

Postman collection used for testing is included in files.

---
## Notes:

- There can't be two users with the same login and/or email
- While trying to register to lecture, the system checks if users exists. 
If not, then (if possible) the system creates the user in database. 
This way, it is not needed to register first and only then booking the lecture

---
## Endpoints:
**GET** `/api/v1/lecture/schedule` - shows schedule divided into three time blocks <br>
Response:
```json lines
{
  "First block": [
    {
      "id": 3,
      "name": "Basics of microservices",
      "lecturer": "Katarzyna Czyk",
      "startDate": "2023-06-01 08:00:00",
      "endDate": "2023-06-01 09:45:00",
      "topic": "BACKEND",
      "numberOfParticipants": 0
    },
    ...
  ],
  "Second block": [
    ...
  ],
  "Third block": [
    ...
  ]
}
```
<br>

**GET** `/api/v1/lecture/schedule/topics` - shows schedule grouped by topics <br>
Response:
```json lines
{
  "schedule": {
    "FRONTEND": [
      {
        "id": 0,
        "name": "Responsive web design",
        "lecturer": "John Smith",
        "startDate": "2023-06-01 08:00:00",
        "endDate": "2023-06-01 09:45:00",
        "topic": "FRONTEND",
        "numberOfParticipants": 0
      },
      ...
    ],
    "BACKEND": [
      ...
    ],
    "DATABASE": [
      ...
    ]
  }
}
```
<br>

**GET** `/api/v1/lecture/{login}` - shows all lectures for user with given login <br>
Example response for `/api/v1/lecture/jan`:
```json
[
  {
    "id": 1,
    "name": "Introduction to TypeScript",
    "lecturer": "Jan Kowalski",
    "startDate": "2023-06-01 10:00:00",
    "endDate": "2023-06-01 11:45:00",
    "topic": "FRONTEND",
    "numberOfParticipants": 1
  },
  {
    "id": 2,
    "name": "Frontend frameworks - Vue.js, Angular",
    "lecturer": "Anna Nowak",
    "startDate": "2023-06-01 12:00:00",
    "endDate": "2023-06-01 13:45:00",
    "topic": "FRONTEND",
    "numberOfParticipants": 1
  }
]
```
<br>

**GET** `/api/v1/user` - shows all registered users <br>
Example response:
```json
[
  {
    "login": "jan",
    "email": "jan@gmail.com",
    "lecturesNumber": 2
  },
  {
    "login": "adam",
    "email": "adam@gmail.com",
    "lecturesNumber": 0
  }
]
```
<br>

**GET** `/api/v1/stats/topics` - shows percentage of user interest in each topic <br>
Example response:
```json
{
  "Interest in topics": {
    "FRONTEND": 50.0,
    "DATABASE": 50.0
  }
}
```
<br>

**GET** `/api/v1/stats/lectures` - shows percentage of user interest in each lecture <br>
Example response:
```json
{
  "Interest in lectures": {
    "Indexing - what's that?": 0.0,
    "Basics of microservices": 0.0,
    "Understanding Spring annotations": 0.0,
    "Introduction to TypeScript": 50.0,
    "Comparison of .NET and Spring": 0.0,
    "Frontend frameworks - Vue.js, Angular": 50.0,
    "Responsive web design": 0.0,
    "Advanced SQL": 0.0,
    "NoSQL introduction - MongoDB": 50.0
  }
}
``` 
<br>

**POST** `/api/v1/lecture/register` - registers user for lecture <br>
Example request:
```json
{
  "lectureId": 6,
  "login": "adam",
  "email": "adam@gmail.com"
}
``` 
Example response:
```json
{
  "login": "adam",
  "email": "adam@gmail.com",
  "lectures": [
    {
      "id": 6,
      "name": "NoSQL introduction - MongoDB",
      "lecturer": "Adam Nowak",
      "startDate": "2023-06-01 08:00:00",
      "endDate": "2023-06-01 09:45:00",
      "topic": "DATABASE",
      "numberOfParticipants": 1
    }
  ]
}
``` 
<br>

**POST** `/api/v1/user/register` - registers user <br>
Example request:
```json
{
  "login": "adam",
  "email": "adam@gmail.com"
}
``` 
Example response:
```json
{
  "login": "adam",
  "email": "adam@gmail.com",
  "lecturesNumber": 0
}
``` 
<br>

**PUT** `/api/v1/user` - updates the user's email <br>
Example request:
```json
{
  "login": "adam",
  "email": "adam1@gmail.com"
}
``` 
Example response:
```json
{
  "login": "adam",
  "email": "adam1@gmail.com",
  "lecturesNumber": 1
}
``` 
<br>

**DELETE** `/api/v1/user/{login}/lecture/{lectureId}` - cancels reservation for lecture <br>
Example response for `/api/v1/user/jan/lecture/1`:
```json
{
  "login": "jan",
  "email": "jan@gmail.com",
  "lecturesNumber": 1
}
``` 
<br>