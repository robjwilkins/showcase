# showcase

provides demo of some nifty java stuff

## Building

`./gradlew clean build`

## Running

Either:

within IDE run the `ShowcaseApplication` spring boot app

Or

`./gradle bootRun`

Assuming you've not modified the port you should then be able to get a response from the 
running server:

`curl "http://localhost:8080/greeting?salutation=good%20morning&name=robin"`

### Docker
The application can be built into a Docker image and run on a docker environment:

```bash
docker build -t robjwilkins/showcase .

docker run -p 8080:8080 robjwilkins/showcase
```

### Add a demo of quartz

Quartz is a java based framework which provides scheduling functionality. This allows jobs to be
created which will be run at a later point-in-time.

The job details are persisted in a database. This demo uses an in-memory h2 database. A flyway migration
is also added to create the schema for this database
