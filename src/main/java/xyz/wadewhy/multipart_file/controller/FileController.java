package xyz.wadewhy.multipart_file.controller;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.wadewhy.multipart_file.utils.AppFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @PACKAGE_NAME: xyz.wadewhy.multipart_file.controller
 * @NAME: FileController
 * @Author: 钟子豪
 * @DATE: 2020/4/5
 * @MONTH_NAME_FULL: 四月
 * @DAY: 05
 * @DAY_NAME_FULL: 星期日
 * @PROJECT_NAME: multipart_file
 **/
@RestController
@Slf4j
@RequestMapping("file")
public class FileController {
    /**
     * 文件上传
     * @param mf
     * @return
     */
    @RequestMapping("uploadFile")
    public Map<String, Object> uploadFile(MultipartFile mf) {
        log.info("mf.getName():-------------->"+mf.getName());
        //1.得到文件名
        String oldName = mf.getOriginalFilename();
        log.info("oldName:-------------------->"+oldName);
        //2.根据文件名生成新的文件名
        String newName = AppFileUtils.createNewFileName(oldName);
        //3.得到当前日期的字符串，命名区分
        String dirName = DateUtil.format(new Date(), "yyyy-MM-dd");
        //4.构建文件夹
        File dirFile = new File(AppFileUtils.UPLOAD_PATH, dirName);
        //5.判断当前文件是否存在,不存在就创建
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        //6.构建文件对象
        File file = new File(dirFile, newName + "_temp");
        //7.把mf里面的图片信息写入file中
        try {
            //保存文件
            mf.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map =new HashMap<>();
        log.info("dirName:------------>"+dirName+"newName:------------------->"+newName);
        //实际情况是提交之后再改名字，现在的file名是以"_temp"结尾，不是图片
        //为了模拟现在这一步改名
        map.put("path",dirName+"/"+newName+"_temp");
//        AppFileUtils.renameFile(dirName+"/"+newName+"_temp");
        //然后再上传
//        map.put("path",dirName+"/"+newName);
        return map;
    }

    /**
     * 图片下载
     * @param path
     * @return
     */
    @RequestMapping("showImagByPath")
    public ResponseEntity<Object> showImagByPath(String path){
        return AppFileUtils.createResponseEntity(path);
    }
}
