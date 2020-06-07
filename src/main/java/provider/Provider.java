package provider;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

import java.util.concurrent.TimeoutException;

public class Provider {


      public  static  void main(String[] args) throws Exception, TimeoutException {
          Connection connection = ConnectionUtil.getConnetion();
          Channel channel  = connection.createChannel();
          channel.queueDeclare
                  (ConnectionUtil.QUEUE_NAME,true,false,false,null);
          channel.basicPublish("",ConnectionUtil.QUEUE_NAME,null,"hello".getBytes());
          channel.close();
          connection.close();
      }

}


