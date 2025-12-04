package com.user.base.application.adaptors.grpc;

import com.gRPC.Client.DeleteResponse;
import com.gRPC.Client.DeleteUserRequest;
import com.gRPC.Client.VerifiedServiceGrpc;
import com.user.base.core.interactor.UserService;
import io.grpc.stub.StreamObserver;
import io.micronaut.grpc.annotation.GrpcService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@GrpcService
public class UserVerifiedgRPC extends VerifiedServiceGrpc.VerifiedServiceImplBase {
    @Inject
    UserService userService;
    @Override
    public void deleteUser(DeleteUserRequest request, StreamObserver<DeleteResponse> responseObserver) {
        boolean respnse=userService.DeleteUser(request.getUsername(), request.getUsername());
        responseObserver.onNext(DeleteResponse.newBuilder().
                setResponse(respnse)
                .build());
        responseObserver.onCompleted();
    }
}
