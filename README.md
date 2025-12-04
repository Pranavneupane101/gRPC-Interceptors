# gRPC-Interceptors
Performed the JWT authentication with the help of the gRPC interceptors.

Interceptors:

ServerCall-> Connection between the client and the Server for one RPC call. Used for sending response , close the call, set status code, access call informations. 
SercerCall obj.close(Status,"message")
ServerCall.Listner<>() to communicate with the Client

Context.interruptCall()-> Attaching the new context to the call before sending to the actual servicecall.



