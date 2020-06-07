package direct.consumer;

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
              //envelope参数，消息的关键信息 消息的路由键 消息的唯一标识等信息
              //收到消息后的一些处理逻辑
              public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                  System.out.println(new String(body,"UTF-8"));

//                  System.out.println(1/0);

                  System.out.println("消费成功。。。。");
                  // 设置消息确认   参数一 消息的唯一标识 参数2 是否进行批量的确认

                  channel.basicAck(envelope.getDeliveryTag(),false);

              }
          };
          channel.basicConsume(ConnectionUtil.QUEUE_NAME,false,defaultCallback);




         }

}
