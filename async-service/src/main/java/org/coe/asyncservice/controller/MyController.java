package org.coe.asyncservice.controller;

import org.coe.asyncservice.vo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RestController
public class MyController {

    @RequestMapping("/hello/{name}")
    Mono<ServerResponse> hello(ServerRequest req) {
        return ok().body(fromObject(req.pathVariable("name")));
    }

    @RequestMapping("/hello2/{name}")
    Mono<ServerResponse> hello(@PathVariable String name) {
        return ok().body(fromObject(name));
    }

    @RequestMapping("/hello")
    Mono<String> hello(@RequestBody Mono<User> user) {
        // Mono의 연산자를 사용해서 로직을 수행하고 Mono로 리턴
        return user.map(u -> "Hello" + u.getName());
    }

    @RequestMapping("/hello2")
    Flux<String> hello2(@RequestBody Flux<User> users) {
        // Flux의 연산자를 사용해서 로직을 수행하고 Flux로 리턴 Flux는 복수개
        return users.map(u -> "Hello" + u.getName());
    }
}
