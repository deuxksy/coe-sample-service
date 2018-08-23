package com.sds.act.coe.user.web.grpc;

import com.google.common.base.Preconditions;
import com.sds.act.coe.api.user.User;
import com.sds.act.coe.api.user.UserSearchRequest;
import com.sds.act.coe.api.user.UserSearchResponse;
import com.sds.act.coe.api.user.UserServiceGrpc;
import com.sds.act.coe.user.repository.entity.UserEntity;
import com.sds.act.coe.user.service.UserService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@GRpcService
public class UserGrpcImpl extends UserServiceGrpc.UserServiceImplBase {

    private UserService userService;

    @Autowired
    public UserGrpcImpl(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void searchUser(UserSearchRequest request, StreamObserver<UserSearchResponse> responseObserver) {
        List<UserEntity> userEntities = userService.search();
        responseObserver.onNext(convertToDTO(userEntities));
        responseObserver.onCompleted();
    }

    @Override
    public void getOne(UserSearchRequest request, StreamObserver<User> responseObserver) {

        int userId = request.getUserId();
        Optional<UserEntity> userEntity = userService.get(userId);

        User.Builder userResponse = User.newBuilder();

        userEntity.ifPresent(user -> userResponse
                .setUserId(user.getUserId())
                .setName(user.getName())
                .setEmail(user.getEmail()));

        responseObserver.onNext(userResponse.build());
        responseObserver.onCompleted();
    }

    private UserSearchResponse convertToDTO(List users) {
        UserSearchResponse userSearchResponse = UserSearchResponse.newBuilder().build();
        modelMapper().map(users, UserSearchResponse.class);
        return userSearchResponse;
    }
}
