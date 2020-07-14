//package cn.wahaha.test.javaWebTest;
//
//import java.io.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//
///**
// * @Description: Java_wget
// * @Author: zhangrenwei
// * @Date: 2019/12/2 11:23 上午
// */
//
//public class Java_wget {
//    public static void main(String[] args) {
//        String url = "https://segmentfault.com/img/remote/1460000015559589";
//
//        URL u;
//        InputStream is = null;
//        DataInputStream dis;
//        String s;
//
//        try
//        {
//            u = new URL(url);
//            is = u.openStream();
//            dis = new DataInputStream(new BufferedInputStream(is));
//
//            dis.read()
//            while ((s = dis.readLine()) != null)
//            {
//                FileOutputStream fileOutputStream = new FileOutputStream("/Users/zhangrenwei/Desktop/test/");
//
//
//
//                System.out.println(s);
//            }
//        }
//        catch (MalformedURLException mue)
//        {///Users/zhangrenwei/Desktop/test
//            System.err.println("Ouch - a MalformedURLException happened.");
//            mue.printStackTrace();
//            System.exit(2);
//        }
//        catch (IOException ioe)
//        {
//            System.err.println("Oops- an IOException happened.");
//            ioe.printStackTrace();
//            System.exit(3);
//        }
//        finally
//        {
//            try
//            {
//                is.close();
//            }
//            catch (IOException ioe)
//            {
//            }
//        }
//    }
//}
