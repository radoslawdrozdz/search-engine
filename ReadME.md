##To run app execute:
- mvn spring-boot:run

##To add some documents
- curl -d 'the brown fox jumped over the brown dog' -H "Content-Type: application/json" -X POST http://localhost:8080/documents
- curl -d 'the lazy brown dog sat in the corner' -H "Content-Type: application/json" -X POST http://localhost:8080/documents
- curl -d 'the red fox bit the lazy dog' -H "Content-Type: application/json" -X POST http://localhost:8080/documents

##To search term use
- curl http://localhost:8080/documents/search?term=brown
- curl http://localhost:8080/documents/search?term=fox

##To get all documents
- curl http://localhost:8080/documents?term=fox