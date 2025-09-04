package com.bootdo.common.utils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SendRTXMessage {
     /**
     * RTX发送消息提醒
     * @param receivers String 接收人(多个接收人以逗号分隔)
     * @param title String 消息标题
     * @param msg String 消息内容
     * @param type String 0:普通消息 1:紧急消息
     * @param delayTime String 显示停留时间(毫秒) 0:为永久停留(用户关闭时才关闭)
     * @return int 0:操作成功 非0:操作不成功
     */
    public int SendRTXNotify(String receivers,String title,String msg,String type,String delayTime)throws Exception{

    	int iRet= -1;
        //String rtxHost = "10.6.100.1";
        String rtxHost = "216.16.16.142";
        String result = null;
        String s;
 
        StringBuffer strURL = new StringBuffer("http://" + rtxHost + ":8012/sendnotify.cgi");
        //?msg=hello&receiver=admin";
        try
        {
            strURL.append("?msg=").append(URLEncoder.encode(msg,"GB2312"))
            .append("&receiver=").append(URLEncoder.encode(receivers,"GB2312"))
            .append("&title=").append(URLEncoder.encode(title,"GB2312"));
            java.net.URL url = new URL(strURL.toString());
            HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
 
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(),"GB2312"));  
            while ((s=reader.readLine())!=null){
                result += s;
            }
            if (result.indexOf("操作成功")>-1) iRet = 0;
        }
        catch(Exception e)
        {
            System.out.println("系统出错"+e);
        }
        System.out.println("RTX服务器返回："+result.toString());
        return iRet;
    }
}