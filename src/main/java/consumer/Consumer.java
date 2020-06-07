package consumer;

import com.rabbitmq.client.*;
import util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
      public  static  void main(String[] args) throws IOException, TimeoutException {

          Connection connection = ConnectionUtil.getConnetion();
          Channel channel =connection.createChannel();
          //监听 生产者
          DefaultConsumer defaultCallback = new DefaultConsumer(channel){
              @Override
              public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                  System.out.println(new String(body,"UTF-8"));

              }
          };


          channel.basicConsume(ConnectionUtil.QUEUE_NAME,defaultCallback);




         }

}
