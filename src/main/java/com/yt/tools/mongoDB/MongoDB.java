package com.yt.tools.mongoDB;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wuyang
 * @date 2018/5/25 15:41
 */
public class MongoDB {

    public static void main(String[] args) {
        try {
            ServerAddress serverAddress = new ServerAddress("120.79.45.25", 27017);
            List<ServerAddress> addressList = new ArrayList<ServerAddress>();
            addressList.add(serverAddress);

            MongoCredential credential = MongoCredential.createScramSha1Credential("blog", "myBlog", "123456".toCharArray());

            List<MongoCredential> mongoCredentials = new ArrayList<MongoCredential>();
            mongoCredentials.add(credential);

            MongoClient mongoClient = new MongoClient(addressList, mongoCredentials);

            MongoDatabase mongoDatabase = mongoClient.getDatabase("blog");
            System.out.println("Connect to database successfully");
        }catch (Exception e){
            e.printStackTrace();
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
