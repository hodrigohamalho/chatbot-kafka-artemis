// package com.redhat;

// import io.quarkus.test.junit.QuarkusTest;
// import org.junit.jupiter.api.Test;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.concurrent.CountDownLatch;
// import java.util.concurrent.TimeUnit;

// import com.ning.http.client.AsyncHttpClient;
// import com.ning.http.client.websocket.WebSocket;
// import com.ning.http.client.websocket.WebSocketTextListener;
// import com.ning.http.client.websocket.WebSocketUpgradeHandler;

// import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.is;

// @QuarkusTest
// public class GreetingResourceTest {

//     private static List<String> received = new ArrayList<String>();
//     private static CountDownLatch latch = new CountDownLatch(1);

//     @Test
//     public void testHelloEndpoint() {
//         given()
//           .when().get("/hello")
//           .then()
//              .statusCode(200)
//              .body(is("Hello RESTEasy"));
//     }

//     @Test
//     public void testeWebSocket(){

//         AsyncHttpClient c = new AsyncHttpClient();

//         WebSocket websocket = c.prepareGet("ws://127.0.0.1:9292/echo").execute(
//             new WebSocketUpgradeHandler.Builder()
//                 .addWebSocketListener(new WebSocketTextListener() {
//                     @Override
//                     public void onMessage(String message) {
//                         received.add(message);
//                         log.info("received --> " + message);
//                         latch.countDown();
//                     }

//                     @Override
//                     public void onFragment(String fragment, boolean last) {
//                     }

//                     @Override
//                     public void onOpen(WebSocket websocket) {
//                     }

//                     @Override
//                     public void onClose(WebSocket websocket) {
//                     }

//                     @Override
//                     public void onError(Throwable t) {
//                         t.printStackTrace();
//                     }
//                 }).build()).get();

//         websocket.sendTextMessage("Beer");
//         assertTrue(latch.await(10, TimeUnit.SECONDS));

//         assertEquals(1, received.size());
//         assertEquals("BeerBeer", received.get(0));

//         websocket.close();
//         c.close();

//     }

// }