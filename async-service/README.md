# Async Serivce

#### Controller @ResponseBody Return Type

~~~ 
T
Mono<T>
Mono<void>
~~~

~~~
Flux<T>
Flux<ServerSideEvent> : HTTP STREAMING으로 리턴하고 싶을 경우
~~~

~~~
void
~~~

#### 대표적인 블록킹 I/O
 - 데이터 엑세스 레포지토리
 - Http API 호출
 - 기타 네트워크를 이용하는 서비스
     
#### ReactiveCRUDRepository
 - MongoDB
 - Redis
 - Cassandra
     

####  Dependencies

#### [projectreactor](https://projectreactor.io)

_'Reactor is a fully non-blocking reactive programming foundation for the JVM, with efficient demand management
 (in the form of managing "backpressure"). It integrates directly with the Java 8 functional APIs, 
 **notably CompletableFuture, Stream, and Duration**. It offers composable asynchronous sequence APIs **Flux (for [N] elements) and Mono (for [0|1] elements)**,
  extensively implementing the Reactive Extensions specification. Reactor also supports non-blocking inter-process communication (IPC) with the reactor-ipc components. Suited for Microservices Architecture, Reactor IPC offers backpressure-ready network engines for HTTP (including Websockets), TCP, and UDP. Reactive Encoding and Decoding are fully supported.'_
