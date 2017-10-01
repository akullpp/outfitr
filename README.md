# outfitr

## Requirements

[Docker](https://www.docker.com/) (stable:latest)

An environment variable `OMW_API_KEY` has to be set which should be a valid API Key for OpenWeatherMap.

## Run

```
docker-compose up
```

or a clean slate

```
docker-compose up --build
```

The server can be accessed under port `8080`.

The Swagger API can be used under the `http://<host>:<port>/swagger-ui.html` URL.

## Development

```
./mvwn spring-boot:run
```

The backend will be available at port `11000`.

## Build

```
./mvwn clean install
```
