# Valet Scheduling Service

Schedule Valet Pickup times.

!! currently not working

## API

### Schedule a valet

```
http post localhost:8080/api/v1/schedule_valet id=1 time=yyyy-MM-ddTHH:mm:ss.SSSZZ
```

## Technologies

* Scala
* [http4s](http://http4s.org/) 


## Running the service


run `sbt run`  in project root


## Tests

run `sbt test` in project root


## ToDos

* implement json decoding/encoding for models
* get API running
* dont require ID for schedule_pickup API
* proper error handling