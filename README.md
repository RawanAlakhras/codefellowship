# codefellowship

# installation

1. open your terminal

2. clone my repo by command

    > git clone git@github.com:RawanAlakhras/codefellowship.git

3. cd into it .

4. Run this command to start the server

    > pg_ctl -D /home/linuxbrew/.linuxbrew/var/postgres start

5. Validate that postgres clients can connect
    > psql

6. Create your "user" database

    > CREATE DATABASE databaseName
    > \c databaseName

7. go to codefellowship/src/main/resources/application.properties then :

    * change **spring.datasource.username** to your user name.
    * change **spring.datasource.password** to your password .
    * change spring.datasource.url=jdbc:postgresql://localhost:5432/**databaseName**

8. run the application by IDE then go to your browser and write <http://localhost:8080/>.
9. now you are in the home page from navbar click on signup .
10. write all your information then submit.
11. you can login using your username and pass.
12. the profile page now dispaly in navbar click in it to see your info.
13. from profile page you can add new post and you can see your previous post in it.

* you can see user’s username on every page ( in the navbar) after login.
* The site have a non-whitelabel error handling page.

# API

## GET requests

* / </br>
to show the home page
* /signup </br>
shows the signup page
* /login </br>
show the login page
* /profile </br>
shows the profile of the logged user and your post .
* <http://localhost:8080/users/{id>} </br>
which allows viewing the data about a single ApplicationUser by write their id .

## POST requests

* /signup </br>
Gets the user credientials and signs up his data to the data base and authorize the user.
* /addpost </br>
get the body of the post from form and store it in database.
