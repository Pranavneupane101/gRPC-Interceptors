package com.user.base.application.adaptors.grpc;

import com.user.base.core.interactor.UserService;

import io.grpc.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.inject.Inject;


import java.security.Key;

public class UserInterceptors implements ServerInterceptor {



    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        Metadata.Key<String> key = Metadata.Key.of("Authentication", Metadata.ASCII_STRING_MARSHALLER);
        String token = headers.get(key);

        String method =call.getMethodDescriptor().getFullMethodName(); ;
        if(!method.equals("com.gRPC.Client.VerifiedService/DeleteUser")) {
            return next.startCall(call, headers);
        }

        byte[] keyBytes = Decoders.BASE64.decode("l8GcKkB7fIhPZz9B+0lM9whk2hJTePf8iU7kNW9qV0o=");
        Key secrateKey = Keys.hmacShaKeyFor(keyBytes);


        Metadata Hello = new Metadata();
        if (token == null) {


            Metadata.Key<String> Error_Message = Metadata.Key.of("UNAUTHORIZED", Metadata.ASCII_STRING_MARSHALLER);
            Hello.put(Error_Message, "Token is null");
            call.close(Status.UNAUTHENTICATED, Hello);
            return new ServerCall.Listener<ReqT>() {
                @Override
                public void onMessage(ReqT message) {
                    System.out.println("Token is null");
                }
            };

        } else {
            try {

                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(secrateKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
                Context context = Context.current();

                return Contexts.interceptCall(context, call, headers, next);


            } catch (Exception e) {

                Metadata.Key<String> Error_Message = Metadata.Key.of("UNAUTHORIZED", Metadata.ASCII_STRING_MARSHALLER);
                Hello.put(Error_Message, "Token is invalid");
                call.close(Status.UNAUTHENTICATED, Hello);
                return new ServerCall.Listener<ReqT>() {
                };
            }
        }




    }
}
