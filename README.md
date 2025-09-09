# showcase

This project provides a demo of the changes required to add various interesting Java features.  
The `main` branch currently contains a small 'vanilla' Spring Boot application, which runs on Java 22, builds using Gradle, and includes a simple 'hello world' type controller.

There are several open pull requests demonstrating how to add different features. These pull requests are kept separate (unmerged) to clearly show the necessary changes for each feature without modifying the existing codebase.

### Current Pull Requests

The following demos are available as pull requests:
- [HTTP endpoint to download/stream a ZIP file containing PDFs (which themselves are also streamed)](https://github.com/robjwilkins/showcase/pull/7)
- [Scheduling of tasks to be completed asynchronously using the Quartz framework](https://github.com/robjwilkins/showcase/pull/2)
- [Use of the Spock Groovy framework for data-driven testing](https://github.com/robjwilkins/showcase/pull/1)

## Building the Project

To build the project, run the following command:

```bash
./gradlew clean build
```

## Running the Application

You can run the application in one of the following ways:

1. **Within an IDE**: Run the `ShowcaseApplication` Spring Boot app.

2. **Using the command line**:
   ```bash
   ./gradlew bootRun
   ```

Once the application is running, you can test it by sending a request to the following endpoint (assuming the default port is unchanged):

```bash
curl "http://localhost:8080/greeting?salutation=good%20morning&name=robin"
```

## Docker Support

The application can be built into a Docker image and run in a Docker environment:

1. Build the Docker image:
   ```bash
   docker build -t robjwilkins/showcase .
   ```

2. Run the Docker container:
   ```bash
   docker run -p 8080:8080 robjwilkins/showcase
   ```

## Contributing

To contribute to this project:

1. Clone or fork the repository.
2. Create a new branch for your feature.
3. Add your feature and commit your changes.
4. Create a pull request.

Commit messages should follow this format:
- A single line summarizing the change.
- A more detailed message explaining what the feature is and why it was added.