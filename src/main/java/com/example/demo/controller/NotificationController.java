package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Notifications;
import com.example.demo.entity.User;
import com.example.demo.form.NotificationForm;
import com.example.demo.service.NotificationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notifications/save")
    public String saveNotifications(
            @ModelAttribute NotificationForm form,
            HttpSession session) {

        User loginUser = (User) session.getAttribute("user");

        notificationService.deleteAllByUser(loginUser);

        if (form.getNotifications() != null) {

            for (Notifications notification : form.getNotifications()) {

                notification.setUser(loginUser);

                notificationService.saveNotification(notification);
            }
        }

        return "redirect:/home";
    }

}