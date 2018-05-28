package com.yt.tools.rabbitMQ.publishSubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * @author wuyang
 * @date 2018/5/3 16:23
 */
public class ReceiveLogs1 {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        //产生一个随机的队列名称
        String queueName = channel.queueDeclare().getQueue();
        //对队列进行绑定
        channel.queueBind(queueName, EXCHANGE_NAME, "");

        System.out.println("ReceiveLogs1 Waiting for messages");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws UnsupportedEncodingException {
                String message = new String(body,"UTF-8");
                System.out.println("ReceiveLogs1 Received : " + message);
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制 (队列会自动删除)
        channel.basicConsume(queueName, true, consumer);
    }
}
