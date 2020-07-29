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

## lombok

The GreetingController and Greeting pojo provide a demo of some basic lombok code

The project uses a lombok gradle plugin to enable the lombok magic when the project builds

In Idea `Annotation Processing` must be enabled in your preferences

### Greeting class

this class:
- is annotated with `@Value` making the class immutable
- is annotated with `@With` adding withers for the fields

### GreetingController

this class:
- provides an example of how the Greeting class can be used
- demonstrates the lombok `@Slf4j` annotation for creating a logger