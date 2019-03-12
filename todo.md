# ToDo

## Java
1) Change groupId to `stef.projects.console` so you can (in the future) group your console applications under this group id
2) Change base-package to `stef.projects.console` as well
3) Create the appropriate structure of packages under your base package. It is recommended (and common) to use a Model-view-Controller (i.e. MVC) architecture for the application. That is because it separates our classes/files in such a way that we can easily add/remove/replace layers.
    
    1) **view**<br/>
    Contains all the classes necessary for showing stuff to the application user (through a console in this specific case). 
    In a web application, this package would not exist and we would have `.html` files for the ui under the resources folder.
    
    2) **model**<br/>
    Contains all the classes necessary for the objects we will be showing to the application user (e.g. UserForm (for registration), QuizForm (for showing a quiz) etc..).
    
    2) **domain**<br/>    
    Contains all the *Entities* of the application(e.g. User, Question, Quiz etc..). 
    Most classes in this package (usually) have an 1-1 relationship with the tables in the database of the application, but it's not mandatory.
    
    3) **controller**<br/>
    This package acts as an intermediate layer between the UI (i.e. `view` package) and the business layer (i.e. `service` package).
    
    4) **service**<br/>
    Contains all the business logic of the application and acts as an intermediate layer between the `controller` package and the `repository` package (e.g. Database, Rest Api etc...).
    
    5) **repository**<br/>
    Contain all the classes necessary for communicating with the data provider. In this case the data provider is a database and specifically Postgres.
    
Obviously other packages & classes will be used but this is the base structure we should start with.

## Postgres
1) Create a folder `migration` (in the base directory of the project)
2) For each TABLE in the database create a corresponding `table-name.sql` file that contains its `CREATE TABLE` statement (e.g. `user.sql`, `question.sql` etc..)
