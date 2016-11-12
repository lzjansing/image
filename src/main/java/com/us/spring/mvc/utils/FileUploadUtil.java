package com.us.spring.mvc.utils;

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

    public static String upload(CommonsMultipartFile file, HttpServletRequest req, String uploadPath) throws IOException {
        String realpath = getRealPath(req, uploadPath);
        File tmp = new File(realpath + File.separator + file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), tmp);
        return uploadPath + File.separator + file.getOriginalFilename();
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

    private static String getRealPath(HttpServletRequest req, String path) {
        return req.getSession().getServletContext().getRealPath("/WEB-INF/" + path);
    }
}
