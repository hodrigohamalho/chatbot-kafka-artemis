package com.redhat.routeBuilder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ChatControllerRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        
        from("vertx-websocket://echo")
            .log(">>> Message received from WebSocket Client : ${body} - ${headers}")
            //TODO: converter o body p/ model
        .to("direct:send-to-artemis");

        from("direct:send-to-artemis").routeId("send-to-artemis")
            //.marshal().json()   // marshall message to send to artemis            
            .to("jms:topic:chat-bot-raw")
            .log(">>> Message sended to Artemis : ${body} - ${headers}");

        /*from("jms:topic:chat-bot-raw")
        .log(">>> Message received from Artemis : ${body} - ${headers}");*/

            

    }
    
}
