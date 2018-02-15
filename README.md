# kotlin-web-server
a simple http server in kotlin

#### To run
1. compile with
```shell
$ kotlinc main.kt server -include-runtime -d main.jar
```
2. then run
```shell
$ java -jar main.jar
```

> Note: this is very initial implementation, currently it reads the request and then closes/rejects the request in your browser or other client
