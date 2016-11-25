package com.us.spring.mvc.utils;

import com.us.common.utils.Message;
import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by jansing on 16-10-30.
 */
public class FileUploadUtil {
    public static final String PATH_KEY = "path";

    public static Message upload(CommonsMultipartFile file, HttpServletRequest req, String uploadPath) throws IOException {
        String relativePath = uploadPath + File.separator + file.getOriginalFilename();
        String realPath = getRealPath(req) + relativePath;
        File tmp = new File(realPath);
        FileUtils.copyInputStreamToFile(file.getInputStream(), tmp);
        Message message = new Message();
        message.setCode(Message.SUCCESS);
        message.setMessage("上传成功");
        message.getExtra().put(PATH_KEY, relativePath);
        return message;
    }

    public static void download(HttpServletRequest req, HttpServletResponse resp, String filePath) throws FileNotFoundException {
        File file = new File(getRealPath(req, filePath));
        if (!file.exists()) {
            throw new FileNotFoundException(filePath);
        }
        try {
            resp.setHeader("Content-disposition", "attachment; filename="
                    + URLEncoder.encode(file.getName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        InputStream bis = null;
        OutputStream bos = null;
        try {
            int bytesRead;
            byte[] buff = new byte[1024 * 8];
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(resp.getOutputStream());
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getRealPath(HttpServletRequest req) {
        return getRealPath(req, null);
    }

    private static String getRealPath(HttpServletRequest req, String path) {
        return req.getSession().getServletContext().getRealPath("/"+(path!=null?path:""));
    }
}
