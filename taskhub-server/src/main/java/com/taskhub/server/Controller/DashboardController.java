package com.taskhub.server.Controller;

import com.taskhub.server.entity.User;
import com.taskhub.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;


@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        // 之後可以在這裡用 SecurityContextHolder 抓出「是誰登入了」
        model.addAttribute("msg", "歡迎來到 TaskHub 儀表板！");
        return "dashboard";
    }
}