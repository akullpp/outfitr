# outfitr

## Requirements

[Docker](https://www.docker.com/) (stable:latest)

An environment variable `OMW_API_KEY` has to be set which should be a valid API Key for OpenWeatherMap.

If you use an HTTP(S) proxy, ensure that the correct environment variables, i.e. `http_proxy` and `https_proxy` are set.

Don't forget to adapt your IDE's run configurations as well!

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

If you need a proxy, you set it like usual with `-Dhttp(s).proxyHost=<host> -Dhttp(s).proxyPort=<port>` depending if it's a HTTP or HTTPS proxy.

## Development

```
./mvwn spring-boot:run
```

The backend will be available at port `11000`.

## Build

```
./mvwn clean install
```
