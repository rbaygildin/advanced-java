package org.rbaygildin;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;


public class SimpleProducerConsumer {
    private static final String QNAME = "hello";
    private static final Logger LOG = Logger.getLogger(SimpleProducerConsumer.class.getName());

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            try(Connection connection = factory.newConnection()){
                try(Channel channel = connection.createChannel()) {
                    channel.queueDeclare(QNAME, false, false, false, null);
                    String message = "Hello, moose";
                    channel.basicPublish("", QNAME, null, message.getBytes());
                    LOG.info("Message was sent");
                }
            }
            catch (IOException | TimeoutException e){
                throw new RuntimeException(e);
            }
        });
        producer.setUncaughtExceptionHandler((t, e) -> {
            LOG.info("Handled exception from producer: \n" + e.getMessage());
        });
        Thread consumer = new Thread(() -> {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            try(Connection connection = factory.newConnection()){
                try(Channel channel = connection.createChannel()) {
                    channel.queueDeclare(QNAME, false, false, false, null);
                    Consumer consumerHandler = new DefaultConsumer(channel){
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            System.out.println("[x] received message from message queue: " + new String(body, "UTF-8"));
                        }
                    };
                    System.out.println("Start to consume");
                    channel.basicConsume(QNAME, true, consumerHandler);
                }
            }
            catch (IOException | TimeoutException e){
                throw new RuntimeException(e);
            }
        });
        consumer.setUncaughtExceptionHandler((t, e) -> {
            LOG.info("Handled exception from consumer: \n" + e.getMessage());
        });
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }
}
