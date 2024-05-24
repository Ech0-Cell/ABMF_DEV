
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaMessageConsumer {

    private KafkaConsumer<String, String> consumer;

    public KafkaMessageConsumer(String topic, Properties kafkaProperties) {
        this.consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(Collections.singletonList(topic));
    }

    public String pollMessage() {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        for (ConsumerRecord<String, String> record : records) {
            return record.value();  // Assuming you process one message at a time
        }
        return null;
    }

    public void close() {
        consumer.close();
    }
}
