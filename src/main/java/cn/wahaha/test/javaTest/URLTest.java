package cn.wahaha.test.javaTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: zhangrenwei
 * @Date: 2019-02-20 14:42
 */

public class URLTest {
    public static void main(String[] args) throws IOException {
        long begintime = System.currentTimeMillis();

        URL url = new URL("http://www.yhfund.com.cn");
        HttpURLConnection urlcon = (HttpURLConnection)url.openConnection();
        urlcon.connect();         //获取连接
        InputStream is = urlcon.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
        StringBuffer bs = new StringBuffer();
        String l = null;
        while((l=buffer.readLine())!=null){
            bs.append(l).append("/n");
        }
        System.out.println(bs.toString());

        //System.out.println(" content-encode："+urlcon.getContentEncoding());
        //System.out.println(" content-length："+urlcon.getContentLength());
        //System.out.println(" content-type："+urlcon.getContentType());
        //System.out.println(" date："+urlcon.getDate());

        System.out.println("总共执行时间为："+(System.currentTimeMillis()-begintime)+"毫秒");

}
}
