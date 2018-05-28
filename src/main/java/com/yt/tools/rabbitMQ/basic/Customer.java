package com.yt.tools.rabbitMQ.basic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * 创建消费者
 * @author wuyang
 * @date 2018/5/3 15:15
 */
public class Customer {

    private final static String QUEUE_NAME = "rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
       /*factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(15672);*/
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Customer Waiting Received messages");

        /**
         *  DefaultConsumer类实现了Consumer接口，通过传入一个频道，
         * 告诉服务器我们需要哪个频道的消息，如果频道中有消息，就会
         * 执行回调函数handleDelivery
         */

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws UnsupportedEncodingException {
                String message = new String(body,"UTF-8");
                System.out.println("Customer Received '" + message + "'");

            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制 (队列会自动删除)
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
