<<<<<<< HEAD
## Micronaut 4.10.3 Documentation

- [User Guide](https://docs.micronaut.io/4.10.3/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.10.3/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.10.3/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)
- [Shadow Gradle Plugin](https://gradleup.com/shadow/)
## Feature serialization-jackson documentation

- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


## Feature micronaut-aot documentation

- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)

=======
# gRPC-Interceptors
Performed the JWT authentication with the help of the gRPC interceptors.

Interceptors:

ServerCall-> Connection between the client and the Server for one RPC call. Used for sending response , close the call, set status code, access call informations. 
SercerCall obj.close(Status,"message")
ServerCall.Listner<>() to communicate with the Client

Context.interruptCall()-> Attaching the new context to the call before sending to the actual servicecall.


>>>>>>> 37f80a1a28fcf065d0a2a321b3f16226597e0ea2

