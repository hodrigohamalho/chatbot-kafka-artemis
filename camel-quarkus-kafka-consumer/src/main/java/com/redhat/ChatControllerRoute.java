package com.redhat;

import org.apache.camel.builder.RouteBuilder;

public class ChatControllerRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        from("vertx-websocket://echo")
            .log(">>> Message received from WebSocket Client : ${body}");
        //     .transform().simple("yuhu!")
        // .to("vertx-websocket://echo");

    }
    
}
