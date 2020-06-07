package direct.consumer;

import com.rabbitmq.client.*;
import util.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer2 {
      public  static  void main(String[] args) throws IOException, TimeoutException {




          Connection connection = ConnectionUtil.getConnetion();
          Channel channel =connection.createChannel();
          channel.queueDeclare("testQueue2",true,false,false,null);

          //监听 生产者
          DefaultConsumer defaultCallback = new DefaultConsumer(channel){
              @Override
              //envelope参数，消息的关键信息 消息的路由键 消息的唯一标识等信息
              //收到消息后的一些处理逻辑
              public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                  System.out.println(new String(body,"UTF-8"));


              }
          };
          channel.basicConsume("testQueue2",true,defaultCallback);




         }

}
