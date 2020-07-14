package cn.wahaha.test.javaWebTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Description: DownloadFile
 * @Author: zhangrenwei
 * @Date: 2019/12/2 2:25 下午
 */
@Controller
public class DownloadFile {
    @ResponseBody
    @RequestMapping("/download")
    public String downloadFile(HttpServletRequest request,
                               HttpServletResponse response) throws UnsupportedEncodingException {

//        // 获取指定目录下的第一个文件
//        File scFileDir = new File("E://music_eg");
//        File TrxFiles[] = scFileDir.listFiles();
//        System.out.println(TrxFiles[0]);
//        String fileName = TrxFiles[0].getName(); //下载的文件名
        String fileName = "123.jpg";

        // 如果文件名不为空，则进行下载
//        if (fileName != null) {
        //设置文件下载路径
        String realPath = "https://segmentfault.com/img/remote/1460000015559589";
//            File file = new File(realPath, fileName);
        File file = new File(realPath);

        // 如果文件名存在，则进行下载
//            if (file.exists()) {

        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
//                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));


        // 实现文件下载
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            System.out.println("Download the song successfully!");
        } catch (Exception e) {
            System.out.println("Download the song failed!");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//            }
//        }
        return null;
    }
}
