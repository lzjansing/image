package com.us.image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by jansing on 16-11-8.
 */
public class Foo {
    public static void main(String[] args) throws IOException {
        String dao = "src/main/java/com/us/image/dao/";
        String service = "src/main/java/com/us/image/service/";
        String controller = "src/main/java/com/us/image/controller";
        String suffix = ".java";
        String s = null;
        Scanner scan = new Scanner(System.in);
        while ((s = scan.nextLine()) != null) {
            create(controller, s, suffix);
        }


    }

    public static void create(String prefix, String s, String suffix) throws IOException {
        File f = new File(prefix + s + "Controller" + suffix);
        BufferedWriter bfw = new BufferedWriter(new FileWriter(f));
        bfw.write("package com.us.image.controller;\n" +
                "\n" +
                "import com.us.common.persistence.Page;\n" +
                "import com.us.common.utils.StringUtil;\n" +
                "import com.us.image.entities.User;\n" +
                "import com.us.image.service.UserService;\n" +
                "import com.us.spring.mvc.controller.BaseController;\n" +
                "import org.springframework.ui.Model;\n" +
                "import org.springframework.stereotype.Controller;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.ModelAttribute;\n" +
                "import org.springframework.web.bind.annotation.RequestParam;\n" +
                "import org.springframework.web.servlet.mvc.support.RedirectAttributes;\n" +
                "\n" +
                "import javax.servlet.http.HttpServletRequest;\n" +
                "import javax.servlet.http.HttpServletResponse;\n" +
                "/**\n" +
                " * Created by jansing on 16-11-8.\n" +
                " */\n" +
                "@Controller\n" +
                "@RequestMapping(value = \"admin/user\")\n" +
                "public class UserController extends BaseController {\n" +
                "    @Autowired\n" +
                "    private UserService userService;\n" +
                "    \n" +
                "    @ModelAttribute\n" +
                "    public User get(@RequestParam(required = false) String id){\n" +
                "        User user = null;\n" +
                "        if(StringUtil.isNotBlank(id)){\n" +
                "            return userService.get(id);\n" +
                "        }\n" +
                "        return new User();\n" +
                "    }\n" +
                "    \n" +
                "    @RequestMapping(value={\"list\",\"\"})\n" +
                "    public String list(User user, HttpServletRequest req, HttpServletResponse resp, Model model){\n" +
                "        Page<User> page = userService.findPage(new Page<User>(req,resp), user);\n" +
                "        model.addAttribute(\"page\",page);\n" +
                "        return \"user/list\";\n" +
                "    }\n" +
                "    \n" +
                "    @RequestMapping(value = \"form\")\n" +
                "    public String form(User user, Model model){\n" +
                "        model.addAttribute(\"user\", user);\n" +
                "        return \"user/form\";\n" +
                "    }\n" +
                "    \n" +
                "    @RequestMapping(value = \"save\")\n" +
                "    public String save(User user, Model model, RedirectAttributes redirectAttributes){\n" +
                "        if(!beanValidator(model, user)){\n" +
                "            return form(user, model);\n" +
                "        }\n" +
                "        userService.save(user);\n" +
                "        addMessage(redirectAttributes, \"保存用户成功\");\n" +
                "        return \"redirect:admin/user/?repage\";\n" +
                "    }\n" +
                "    \n" +
                "    @RequestMapping(value = \"delete\")\n" +
                "    public String delete(User user, RedirectAttributes redirectAttributes){\n" +
                "        userService.delete(user);\n" +
                "        addMessage(redirectAttributes, \"删除用户成功\");\n" +
                "        return \"redirect:admin/user/?repage\";\n" +
                "    }\n" +
                "}");
        bfw.close();
    }

    public static void createDao(String prefix, String s, String suffix) {

    }
}
