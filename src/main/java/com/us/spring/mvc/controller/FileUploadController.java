package com.us.spring.mvc.controller;

import com.us.common.config.Global;
import com.us.common.utils.Message;
import com.us.common.utils.StringUtil;
import com.us.spring.mvc.utils.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jansing on 16-11-22.
 */
@Controller
public class FileUploadController extends BaseController {

    /**
     * 单文件ajax上传
     *
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadAjax", method = RequestMethod.POST)
    public Message uploadAjax(@RequestParam CommonsMultipartFile file, HttpServletRequest req) throws IOException {
        Message message = FileUploadUtil.upload(file, req, Global.getConfig("uploadPath"));
        //html的id，用以回显
        String id = req.getParameter("id");
        if (StringUtil.isNotBlank(id)) {
            message.getExtra().put("id", id);
        }
        StringUtil.split("fdsa", "fdsa");
        return message;
    }

}
