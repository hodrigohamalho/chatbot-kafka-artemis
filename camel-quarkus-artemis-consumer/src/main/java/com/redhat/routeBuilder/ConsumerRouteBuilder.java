package com.redhat.routeBuilder;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class ConsumerRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        
        from("jms:topic:chat-bot-raw")
        .log(">>> Message received from Artemis : ${body} - ${headers}");           

    }
    
}
