package ru.geekbrains.spring.security.controllers;

import ru.geekbrains.spring.security.entities.User;
import ru.geekbrains.spring.security.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DemoController {
    private final UserService userService;

    @GetMapping("/")
    public String homePage() {
        return "Simple guest page";
    }

    @GetMapping("/git_pull")
    public String gitPull() {
        return "make pull request success!";
    }

    @GetMapping("/git_push")
    public String gitPush() {
        return "make push success!";
    }

    @GetMapping("/git_commit")
    public String gitCommit() {
        return "make commit success!";
    }

    @GetMapping("/manage_tasks")
    public String manageTasks() {
        return "You can manage company tasks!";
    }

    @GetMapping("/manage_balance")
    public String manageBalance() {
        return "You can manage company balance!";
    }

    @GetMapping("/manage_schedule")
    public String manageSchedule() {
        return "You can manage company schedule!";
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail();
    }
}
