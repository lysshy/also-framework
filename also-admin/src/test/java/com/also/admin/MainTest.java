package com.also.admin;

import com.also.admin.utils.ExcelReader;
import com.also.admin.utils.User;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        String filePath = "C:\\MySpace\\paic\\桐乡\\2次违规人员-YY0717165253.xlsx";
        String dirName = "C:\\MySpace\\paic\\桐乡\\2次违规人员-YY0717165253";
        List<User> users = ExcelReader.readExcel(filePath);
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                try {
                    download(dirName, user.getNumber(), user.getUrl());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void download(String dir, String fileName, String urlString) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        String filename = dir + "\\" + fileName + ".jpg";  //下载路径及下载图片名称
        File file = new File(filename);
        try(InputStream is = con.getInputStream();FileOutputStream os = new FileOutputStream(file)) {
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        }
    }
}
