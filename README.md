# showcase

This projects provides a demo of the changes required to add various interesting java features.
`master` branch currently contains a small 'vanilla' springboot application, which runs on Java17, builds using gradle,
and contains a simple 'hello world' type controller.

There are a number of open pull requests, demonstrating how to add different features. The pull requests for these features
are being kept separate (unmerged) to show the necessary changes to add the given feature without the need to unpick
them from the existing codebase. 

The current PR requests cover the following demos:
- [Http endpoint to download/stream a Zipfile containing PDFs (which themselves are also streamed)](https://github.com/robjwilkins/showcase/pull/7)
- [Scheduling of tasjs to be completed asynchronously using the Quartz framework](https://github.com/robjwilkins/showcase/pull/2)
- [Use of the spock groovy framework for data driven testing](https://github.com/robjwilkins/showcase/pull/1)

## Building

```bash
./gradlew clean build
```

## Running

Either:

within IDE run the `ShowcaseApplication` spring boot app

Or

```bash
./gradle bootRun
```

Assuming you've not modified the port you should then be able to get a response from the 
running server:

`curl "http://localhost:8080/greeting?salutation=good%20morning&name=robin"`


### Docker
The application can be built into a Docker image and run on a docker environment:

```bash
docker build -t robjwilkins/showcase .

docker run -p 8080:8080 robjwilkins/showcase
```

## Contributing

Clone / fork the repo, create a branch, add your feature, and create a PR. Commit messages should be in the format of a single
line summarising the change, followed by a more in-depth message explaining what the feature is and why it is added