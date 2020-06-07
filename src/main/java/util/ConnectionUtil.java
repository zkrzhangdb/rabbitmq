package util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static final  String QUEUE_NAME="testQueue";

    public static final  String EXCHANGE_NAME="exchange";

    public  static Connection getConnetion() throws IOException, TimeoutException {

        ConnectionFactory connectionFactory =new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        return  connectionFactory.newConnection();

    }
}
