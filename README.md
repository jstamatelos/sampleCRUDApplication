# Sample CRUD Application that will manage a Red Dead Redemption Online Posse

Steps to run:
1. Clone down the application
2. This project was started using the Spring Initializer, so to run via the terminal use: ./mvnw clean spring-boot:run
3. Else, run in your favorite IDE via the PosseUpApplication.class

To hit the application through Postman:
GET:
http://localhost:8080/members/

Will return list of all members (pre-loaded by the PreLoadDatabase.class)

GET:
http://localhost:8080/members/{id}

Will return member by id

POST:
http://localhost:8080/members/
{
  "name": "",
  "position": ""
}

Will add a member with the given name and position

PUT:
http://localhost:8080/members/{id}

Will update existing member with specified Id

DELETE:
http://localhost:8080/members/{id}

Will delete the member with specified id


