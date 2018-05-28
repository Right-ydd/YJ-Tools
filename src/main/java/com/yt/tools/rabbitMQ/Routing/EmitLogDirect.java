package com.yt.tools.rabbitMQ.Routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * @author wuyang
 * @date 2018/5/4 14:29
 */
public class EmitLogDirect {

    private static final String  EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //创建连接
        Connection connection = factory.newConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明一个交换机
        channel.exchangeDeclare("EXCHANGE_NAME","direct");

        String []flag = new String[]{"error","info","warning"};

        for(int i=0;i<10;i++){
            Random random = new Random();
            int method = random.nextInt(10);
            String message = "我是第"+i+"次中文 "+flag[method % 3];
            channel.basicPublish(EXCHANGE_NAME,flag[method % 3],null,message.getBytes());
            System.out.println("send: "+message);
        }
        channel.close();
        connection.close();
    }
}
