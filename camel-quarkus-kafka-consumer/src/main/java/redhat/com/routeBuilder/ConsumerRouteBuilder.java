package redhat.com.routeBuilder;




import com.mongodb.client.model.Filters;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.model.rest.RestBindingMode;
import redhat.com.models.Message;
import org.apache.camel.LoggingLevel;
import org.bson.types.ObjectId;


public class ConsumerRouteBuilder extends RouteBuilder{
    protected String KAFKA_TOPIC_RAW = "{{kafka.topic.raw}}";
    protected String KAFKA_TOPIC_PROCESSED = "{{kafka.topic.raw}}";
    protected String KAFKA_BOOTSTRAP_SERVERS = "{{kafka.bootstrap.servers}}";
    protected String KAFKA_GROUP_ID = "{{kafka.group.id}}";
    @Override
    public void configure() throws Exception {
        
               
        //Route that consumes message to kafka topic
        from("kafka:"+ KAFKA_TOPIC_RAW + "?brokers=" + KAFKA_BOOTSTRAP_SERVERS + "&groupId=" + KAFKA_GROUP_ID)
        .routeId("kafkaConsumerRaw")
        .unmarshal(new JacksonDataFormat(Message.class))
        .log("Message received from Kafka Topic raw : ${body}")
        .to("direct:insertProcessedTopic")
        ;

        //Route insert object on Processed Topic
        from("direct:insertProcessedTopic").routeId("insertProcessedTopic")        
        .to("kafka:"+ KAFKA_TOPIC_PROCESSED + "?brokers=" + KAFKA_BOOTSTRAP_SERVERS + "&groupId=" + KAFKA_GROUP_ID)
        .log("Message send from Kafka Topic processed : ${body}")
        ;
    }
}