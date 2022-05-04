package com.redhat;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ChatControllerRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        from("vertx-websocket://echo")
            .log(">>> Message received from WebSocket Client : ${body} - ${headers}")
            .transform().simple("hello")
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    exchange.getIn();
                }
            })
        .to("vertx-websocket://echo");

    }
    
}
