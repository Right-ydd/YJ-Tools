package com.yt.tools.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @date: 2018/6/7
 * @author: create by Right_ydd
 * @description: com.yt.tools.utils
 */
public class CharacterUtils {

    /**
     * 已字节为单位切割字符串
     * @param str 需要切割的字符串
     * @param size 切割后每份的长度(单位：字节) 注：最后一份字符串大小一定<=size
     * @return
     */
    public List<StringBuilder> cutStringByByte(String str,int size){
        StringBuilder targetString = new StringBuilder( str );
        List<StringBuilder> stringList = new ArrayList<StringBuilder>();
        try {
            byte[] stringByte = targetString.toString().getBytes("UTF-8");//目标字符串
            byte[] targetByte = new byte[size];//每份字符串
            while(true){
                if((stringByte.length)-size>0){
                    //切割，实际上是复制所定长度字符串到targetByte
                    System.arraycopy(stringByte,0,targetByte,0,size);
                    StringBuilder oldString = new StringBuilder(new String(targetByte));
                    //去除已切割部分
                    targetString.delete(0,oldString.length());
                    stringByte = targetString.toString().getBytes("UTF-8");
                    //存储每份字符串
                    stringList.add(targetString);
                }else{
                    stringList.add(targetString);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringList;
        /*Iterator<Object> iterator = list.iterator();
        StringBuilder b = new StringBuilder(list.size()*3);
        while (iterator.hasNext()){
            StringBuilder a = (StringBuilder) iterator.next();
            b.append(a);
        }
        System.out.println(b);*/
    }
}
