# Sample CRUD Application that will manage a Red Dead Redemption Online Posse

Steps to run:
1. Clone down the application
2. This project was started using the Spring Initializer, so to run via the terminal use: ./mvnw clean spring-boot:run
3. Else, run in your favorite IDE via the PosseUpApplication.class

To hit the application through Postman:

PostMan Collection Link:
https://www.getpostman.com/collections/bd8a57fd7c85d52e8111

GET:
http://YOUR-LOCAL/members/

Will return list of all members (pre-loaded by the PreLoadDatabase.class)

GET:
http://YOUR-LOCAL/members/{id}

Will return member by id

POST:
http://YOUR-LOCAL/members/
{
  "name": "",
  "position": ""
}

Will add a member with the given name and position

PUT:
http://YOUR-LOCAL/members/{id}

Will update existing member with specified Id

DELETE:
http://YOUR-LOCAL/members/{id}

Will delete the member with specified id

Swagger Spec:
1. Go to http://YOUR-LOCAL/swagger-ui.html#/
2. Play with the APIs!



