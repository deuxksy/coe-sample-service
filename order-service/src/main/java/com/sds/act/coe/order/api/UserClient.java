package com.sds.act.coe.order.api;

import com.google.common.base.Verify;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.sds.act.coe.api.user.User;
import com.sds.act.coe.api.user.UserSearchRequest;
import com.sds.act.coe.api.user.UserServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserClient {
    private final EurekaClient client;
    private ManagedChannel channel;
    private UserServiceGrpc.UserServiceBlockingStub userClientBlockingStub;

    public UserClient(@Autowired EurekaClient client) {
        this.client = client;
    }

    public List<User> search() {
        final InstanceInfo instanceInfo = client.getNextServerFromEureka("user-service-local", false);
        channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort())
                .usePlaintext()
                .build();

        userClientBlockingStub = UserServiceGrpc.newBlockingStub(channel);
        UserSearchRequest.Builder userSearchRequestBuilder = UserSearchRequest.newBuilder();
        try {
            return userClientBlockingStub.searchUser(userSearchRequestBuilder.build()).getUserList();
        } catch (Exception e) {
            Status status = Status.fromThrowable(e);
            Verify.verify(status.getCode() == Status.Code.INTERNAL);
            Verify.verify(status.getDescription().contains("Eggplant"));
        }
        return null;
    }

    public User get(int id) {
        final InstanceInfo instanceInfo = client.getNextServerFromEureka("user-service-local", false);
        channel = ManagedChannelBuilder.forAddress(instanceInfo.getIPAddr(), instanceInfo.getPort())
                .usePlaintext()
                .build();

        userClientBlockingStub = UserServiceGrpc.newBlockingStub(channel);
        UserSearchRequest.Builder userSearchRequestBuilder = UserSearchRequest.newBuilder();
        userSearchRequestBuilder.setUserId(id);
        return userClientBlockingStub.getOne(userSearchRequestBuilder.build());
    }
}
