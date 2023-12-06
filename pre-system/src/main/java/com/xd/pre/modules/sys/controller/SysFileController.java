package com.xd.pre.modules.sys.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.xd.pre.common.utils.R;
import com.xd.pre.modules.sys.domain.SysTools;
import com.xd.pre.modules.sys.service.impl.SysToolsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/*
文件上传相应的控制类
 */
@RestController
@RequestMapping("/file")
public class SysFileController {
    @Value("${file.upload.path}")
    private String fileUploadPath;
    @Autowired
    SysToolsServiceImpl sysToolsService;
    /*
       工器具上传图片照片
     */
    @PostMapping("/upload")
    public R upload(@RequestParam("multipartFile") MultipartFile multipartFile,@RequestParam("id") Integer id) throws IOException {
        String OrginName=multipartFile.getOriginalFilename();
        String type= FileUtil.extName(OrginName);
        long size=multipartFile.getSize();
        File uploadParentFile =new File(fileUploadPath);
        if(!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }
        //定义文件唯一的标识为
        String uuid=IdUtil.fastSimpleUUID();
        String FileUuid =uuid+ StrUtil.DOT+type;
        File uploadFile = new File(fileUploadPath + uuid+ StrUtil.DOT+type);
        String notTrueUrl="http://localhost:8081/file/"+FileUuid;
        String url= fileUploadPath+FileUuid;
        // 获取文件的md5
        //String md5= SecureUtil.md5(uploadFile);
        //存磁盘
        SysTools sysTools= sysToolsService.getById(id);
        if(sysTools!=null) {
            if (sysTools.getToolPhoto() != null && sysTools.getToolPhoto() != "") {
                String path = sysTools.getToolPhoto().substring(27);
                String realpath = fileUploadPath + path;
                boolean a = FileSystemUtils.deleteRecursively(new File(realpath));
                System.out.println(a);
            }
        }
        multipartFile.transferTo(uploadFile);
        //存数据库

        return  R.ok(notTrueUrl);
    }
    /*
    需求管理里面的现场勘察照片的上传
     */
    @PostMapping("/uploadRequirePhoto")
    public R uploadRequirePhoto(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        String OrginName=multipartFile.getOriginalFilename();
        String type= FileUtil.extName(OrginName);
        long size=multipartFile.getSize();
        File uploadParentFile =new File(fileUploadPath);
        if(!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }
        //定义文件唯一的标识为
        String uuid=IdUtil.fastSimpleUUID();
        String FileUuid =uuid+ StrUtil.DOT+type;
        File uploadFile = new File(fileUploadPath + uuid+ StrUtil.DOT+type);
        String notTrueUrl="http://localhost:8081/file/"+FileUuid;
        String url= fileUploadPath+FileUuid;
        // 获取文件的md5
        //String md5= SecureUtil.md5(uploadFile);
        //存磁盘
        multipartFile.transferTo(uploadFile);
        //存数据库

        return  R.ok(notTrueUrl);
    }
    /*
     用户上传头像
     */
    @PostMapping("/upload/avator")
    public R uploadAvator(@RequestParam("multipartFile") MultipartFile multipartFile) throws IOException {
        String OrginName=multipartFile.getOriginalFilename();
        String type= FileUtil.extName(OrginName);
        long size=multipartFile.getSize();
        File uploadParentFile =new File(fileUploadPath);
        if(!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }
        //定义文件唯一的标识为
        String uuid=IdUtil.fastSimpleUUID();
        String FileUuid =uuid+ StrUtil.DOT+type;
        File uploadFile = new File(fileUploadPath + uuid+ StrUtil.DOT+type);
        String notTrueUrl="http://localhost:8081/file/"+FileUuid;
        String url= fileUploadPath+FileUuid;
        // 获取文件的md5
        //String md5= SecureUtil.md5(uploadFile);
        //存磁盘
        multipartFile.transferTo(uploadFile);
        //存数据库

        return  R.ok(notTrueUrl);
    }
    /*
    文件下载
     */
    @GetMapping("/{FileUuid}")
    public  void  download(@PathVariable String FileUuid, HttpServletResponse response) throws IOException{
        File uploadFile= new File(fileUploadPath+FileUuid);
        ServletOutputStream os=response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(FileUuid, "UTF-8"));
        response.setContentType("application/octet-stream");
        //读取文件字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
}
