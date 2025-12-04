package com.user.base.application.adaptors.grpc;

import com.gRPC.User.FindRequest;
import com.gRPC.User.Response;

import com.gRPC.User.UserServicesGrpc;
import com.gRPC.User.UserTokenResponse;
import com.user.base.application.mapper.UserAndUsergRPCMapper;
import com.user.base.core.interactor.UserService;
import com.user.base.core.model.User;
import io.grpc.stub.StreamObserver;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.micronaut.context.annotation.Value;
import io.micronaut.grpc.annotation.GrpcService;
import jakarta.inject.Inject;


import java.security.Key;
import java.util.Date;

@GrpcService
public class UsergRPC extends  UserServicesGrpc.UserServicesImplBase  {
    private final String secret;
    @Inject
    UserService userService;

    public UsergRPC(@Value("${micronaut.security.token.jwt.signatures.secret.generator.secret}")String secret) {
        this.secret = secret;
    }

    @Override
    public void adduser(com.gRPC.User.UsergRPC request, StreamObserver<Response> responseObserver) {
         boolean status =userService.AddUser(
                 UserAndUsergRPCMapper.toUser(request)

         );


         responseObserver.onNext(
                 Response.newBuilder().setResponse(status).build()
         );
         responseObserver.onCompleted();
    }

    @Override
    public void verifyUser(FindRequest request, StreamObserver<UserTokenResponse> responseObserver) {

        User user =userService.findUser(request.getUsername(), request.getPassword());

        byte[] keyBytes = Decoders.BASE64.decode("l8GcKkB7fIhPZz9B+0lM9whk2hJTePf8iU7kNW9qV0o=");
        Key key = Keys.hmacShaKeyFor(keyBytes);



        String token =Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+3000000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        responseObserver.onNext(
                UserTokenResponse.newBuilder()
                        .setToken(token)
                        .build()
        );
        responseObserver.onCompleted();

    }

    @Override
    public void updateUser(com.gRPC.User.UsergRPC request, StreamObserver<com.gRPC.User.UsergRPC> responseObserver) {

        User user =userService.UpdateUser(UserAndUsergRPCMapper.toUser(request));
        responseObserver.onNext(UserAndUsergRPCMapper.toUsergRPC(user));
        responseObserver.onCompleted();
    }

}
