package com.yt.tools.rabbitMQ.Routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;


/**
 * @author wuyang
 * @date 2018/5/4 14:38
 */
public class ReceiveLogs1 {

    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //创建连接
        Connection connection = factory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //创建随机队列名称
        String queueName = channel.queueDeclare().getQueue();
        //绑定队列
        channel.queueBind(queueName,EXCHANGE_NAME,"error",null);
        Consumer consumer = new DefaultConsumer(channel){
          @Override
          public void handleDelivery(String consumerTag,
                                     Envelope envelope,
                                     AMQP.BasicProperties properties,
                                     byte[] body) throws IOException {
              String message = new String(body,"UTF-8");
              System.out.println("[1] receive: "+message);
              channel.basicAck(envelope.getDeliveryTag(),false);
          }
        };
        channel.basicConsume(queueName,false,consumer);
    }
}
