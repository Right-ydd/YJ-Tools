package com.yt.tools.rabbitMQ.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 * @author wuyang
 * @date 2018/5/3 15:04
 */
public class Producer {

    public final static String QUEUE_NAME="rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ相关信息
        factory.setHost("localhost");
        factory.setUsername("guest");
       /* factory.setPassword("guest");
        factory.setPort(15672);*/

        //创建一个新的连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();
        /**
         * 声明一个队列
         * 1、队列名称
         * 2、是否持久化（true表示队列将在服务器重启时生存）
         * 3、是否独占队列（创建者可以使用的私有队列，断开后自动删除）
         * 4、当所有消费者客户端连接断开时是否自动删除队列
         * 5、其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false ,null);
        String message = "hello RabbitMQ";
        /**
         * 发送消息到队列中.
         * 1、交换机名称
         * 2、队列映射的路由key
         * 3、消息的其他属性
         * 4、参数发送消息的主体
         */
        channel.basicPublish("",QUEUE_NAME,null, message.getBytes("UTF-8"));
        System.out.println("Producer Send +'" + message + "'");
        channel.close();
        connection.close();
    }

}
