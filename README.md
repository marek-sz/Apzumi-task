## Apzumi - Recruitment task
_____________________
### Description
_____________________
Application fetches data from external web service <br>
https://jsonplaceholder.typicode.com/posts 

Data is fetched once every day at 12 p.m. for Europe/Warsaw time zone.

By default application starts at port 8080

User can force fetch at anytime using "/fetch" endpoint (PUT method). More about available endpoints in the API Documentation.

Application uses H2-in-memory database.
<br>
<br>

### API Documentation
_____________________
Full API Documentation with enlisted endpoints is available at:<br>
<https://localhost:8080/swagger-ui.html>
<br>
<br>

### Run
_____________________
Clone this project by typing in the command line:<br>
`git clone "https://github.com/marek-sz/Apzumi-task.git"`

And then type:<br>
`mvn install spring-boot:run` 
<br>
<br>

##### Note
_____________________
Before accessing data make sure to fetch them first ;)
Enjoy!