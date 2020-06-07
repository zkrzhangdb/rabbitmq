package provider;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

import java.util.concurrent.TimeoutException;

public class Provider {


      public  static  void main(String[] args) throws Exception, TimeoutException {
          Connection connection = ConnectionUtil.getConnetion();
          Channel channel  = connection.createChannel();
          //定义交换机
          channel.exchangeDeclare("exchangeTest", BuiltinExchangeType.FANOUT);
          //交换机与队列的绑定
          channel.queueBind("testQueue1","exchangeTest","");
          channel.queueBind("testQueue2","exchangeTest","");


//          channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
          //消息发送到交换机当中  s1 参数是路由键
          channel.basicPublish("exchangeTest","",null,"hello".getBytes());
          channel.close();
          connection.close();
      }

}


