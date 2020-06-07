package direct.consumer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

import java.util.concurrent.TimeoutException;

public class Provider {


      public  static  void main(String[] args) throws Exception, TimeoutException {
          Connection connection = ConnectionUtil.getConnetion();
          Channel channel  = connection.createChannel();
          //删除交换机
          channel.exchangeDelete("exchangeTest");
          //定义交换机
          channel.exchangeDeclare("exchangeTest", BuiltinExchangeType.FANOUT);
          //交换机与队列的绑定
          channel.queueBind("testQueue1","exchangeTest","info1");
          channel.queueBind("testQueue2","exchangeTest","info2");
          channel.queueBind("testQueue3","exchangeTest","info3");


//          channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);
          //消息发送到交换机当中  s1 参数是路由键
           //info1 只有队列一能消费消息
          channel.basicPublish("exchangeTest","info1",null,"hello".getBytes());
          channel.close();
          connection.close();
      }

}


