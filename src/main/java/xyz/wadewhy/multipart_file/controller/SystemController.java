package xyz.wadewhy.multipart_file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @PACKAGE_NAME: xyz.wadewhy.multipart_file.controller
 * @NAME: SystemController
 * @Author: 钟子豪
 * @DATE: 2020/4/5
 * @MONTH_NAME_FULL: 四月
 * @DAY: 05
 * @DAY_NAME_FULL: 星期日
 * @PROJECT_NAME: multipart_file
 **/
@Controller
@RequestMapping("sys")
public class SystemController {
    @RequestMapping("/upload")
    public String toUpload(){
        return "upload1";
    }
}
