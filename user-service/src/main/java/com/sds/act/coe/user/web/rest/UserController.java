package com.sds.act.coe.user.web.rest;

import com.sds.act.coe.user.repository.entity.UserEntity;
import com.sds.act.coe.user.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static com.sds.act.coe.user.web.rest.ApiConstants.API_V1_BASE_PATH;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = API_V1_BASE_PATH + "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

//    @Autowired
//    private RestTemplate restTemplate;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiOperation(value = "register userEntity")
    public UserEntity register(@RequestBody UserEntity userEntity) {
        return userService.register(userEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "user id", required = true, dataType = "int", paramType = "path")
    })
    public UserEntity get(@PathVariable Integer id) {
        logger.info("galled get(" + id + ")");
        Optional<UserEntity> user = userService.get(id);
        user.ifPresent(customer1 -> customer1.add(linkTo(methodOn(this.getClass()).get(id)).withSelfRel()));
        return user.get();
    }

    @DeleteMapping
    @ApiOperation(value = "Delete user")
    public void delete() {
        userService.deleteAll();
    }

    @GetMapping
    @ApiOperation(value = "get all user")
    public List<UserEntity> search() {
        logger.info("galled getAll");
        return userService.search();
    }

//
//    @GetMapping("/mq")
//    @ApiOperation(value = "get data from order")
//    public String mqTest() {
//        logger.info("get data from order");
//        return restTemplate.getForObject("http://localhost:17001/api/v1/orders/user", String.class);
//    }
}