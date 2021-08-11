package com.pzy.web;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author Nice
 * @Date 2021/7/21 16:35
 */

@RestController
public class UniappUpload {
    SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");


    @PostMapping("/upload")
    public JSONObject upload(MultipartFile uploadFile, HttpServletRequest req){
        //获取文件名
        String fileName = uploadFile.getOriginalFilename();

        //获取后缀名
        String suffixName = fileName.substring(fileName.indexOf("."));

        //重新生成目录
        fileName = UUID.randomUUID()+suffixName;

        //添加日期目录
        String format = sd.format(new Date());
        System.out.println(format);

        //指定本地文件夹存储图片
        String filrPath = "D:/桌面/upload/avatar/"+format+"/";

        File file = new File(filrPath,fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        try{
            //将图片保存到文件夹中
            uploadFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject res = new JSONObject();
        String url = filrPath + fileName;
        System.out.println(url);
        res.put("url",url);

        return res;
    }

}
