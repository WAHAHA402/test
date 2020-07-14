package cn.wahaha.test.javaWebTest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

/**
 * @Description: DownloadStream
 * @Author: zhangrenwei
 * @Date: 2019/12/2 2:39 下午
 */
@RestController
public class DownloadStream {
    @RequestMapping("/download1")
    public File downloadFile(HttpServletRequest request,
                             HttpServletResponse response) throws UnsupportedEncodingException {


        String url = "https://segmentfault.com/img/remote/1460000015559589";

        URL u;
        InputStream is = null;
        DataInputStream dis;
        String s;
        File file = new File("/Users/zhangrenwei/Desktop/test", "123.jpg");
        OutputStream outputStream = null;

        try {
            u = new URL(url);
            URLConnection connection = u.openConnection();
            is = u.openStream();
            dis = new DataInputStream(new BufferedInputStream(is));

            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int i = dis.read(buffer);
            while (i != -1) {
                outputStream.write(buffer, 0, i);
                i = dis.read(buffer);
//                System.out.println(s);
            }
            System.out.println("读取流成功" + " 文件类型：" + connection.getContentType());
        } catch (MalformedURLException mue) {
            System.err.println("Ouch - a MalformedURLException happened.");
            mue.printStackTrace();
            System.exit(2);
        } catch (IOException ioe) {
            System.err.println("Oops- an IOException happened.");
            ioe.printStackTrace();
            System.exit(3);
        } finally {
            try {
                is.close();
                outputStream.close();
            } catch (IOException ioe) {
            }
        }


        return file;
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt());
    }
}
