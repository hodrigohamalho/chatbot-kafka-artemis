package com.redhat;

import java.net.URI;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

// import javax.websocket.ClientEndpointConfig;
// import javax.websocket.ContainerProvider;
// import javax.websocket.Endpoint;
// import javax.websocket.EndpointConfig;
// import javax.websocket.MessageHandler;
// import javax.websocket.Session;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

// import io.quarkus.it.websocket.WebSocketOpenEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.restassured.RestAssured;

@QuarkusTest
public class GreetingResourceTest {


    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    // @Test
    // public void websocketTest() throws Exception {

    //     LinkedBlockingDeque<String> message = new LinkedBlockingDeque<>();
    //     Session session = ContainerProvider.getWebSocketContainer().connectToServer(new Endpoint() {
    //         @Override
    //         public void onOpen(Session session, EndpointConfig endpointConfig) {
    //             session.addMessageHandler(new MessageHandler.Whole<String>() {
    //                 @Override
    //                 public void onMessage(String s) {
    //                     message.add(s);
    //                 }
    //             });
    //             session.getAsyncRemote().sendText("hello");
    //         }
    //     }, ClientEndpointConfig.Builder.create().build(), echoUri);

    //     try {
    //         Assertions.assertEquals("hello", message.poll(20, TimeUnit.SECONDS));
    //     } finally {
    //         session.close();
    //     }
    // }

}