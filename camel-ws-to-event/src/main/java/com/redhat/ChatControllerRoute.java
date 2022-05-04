// camel-k: language=java dependency=mvn:org.apache.camel.quarkus:camel-quarkus-kafka
package com.redhat;

import org.apache.camel.builder.RouteBuilder;

public class ChatControllerRoute extends RouteBuilder{

    protected String KAFKA_TOPIC_RAW = "{{kafka.topic.raw}}";
    protected String KAFKA_TOPIC_PROCESSED = "{{kafka.topic.raw}}";
    protected String KAFKA_BOOTSTRAP_SERVERS = "{{kafka.bootstrap.servers}}";
    protected String KAFKA_GROUP_ID = "{{kafka.group.id}}";

    @Override
    public void configure() throws Exception {
        
        from("vertx-websocket://echo")
            .log(">>> Message received from WebSocket Client : ${body} - ${headers}")
            .wireTap("direct:to-broker")
        .to("direct:send-to-kafka");

        from("direct:send-to-kafka").routeId("send-to-kafka")
            // .marshal().json()   // marshall message to send to kafka
            .setHeader("kafka.KEY", constant("Camel")) // Key of the message
        .to("kafka:"+KAFKA_TOPIC_RAW+"?brokers="+KAFKA_BOOTSTRAP_SERVERS);

        from("direct:to-broker")
            .log("sending message to message broker");
            // .to("amqp:topic:chat-bot-raw");

        //Route that consumes message to kafka topic
        // from("kafka:"+KAFKA_TOPIC_RAW+"?brokers="+KAFKA_BOOTSTRAP_SERVERS+"&groupId=KAFKA_GROUP_ID")
        //     .routeId("kafka-consumer-raw")
        //     .unmarshal(new JacksonDataFormat(Message.class))
        //     .log("Message received from Kafka Topic raw : ${body}");

    }
    
}
