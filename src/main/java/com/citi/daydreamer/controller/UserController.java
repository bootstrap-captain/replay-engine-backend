package com.citi.daydreamer.controller;

import com.citi.daydreamer.entity.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {

    List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setUsername(UUID.randomUUID().toString().substring(0, 5));
            user.setType("Admin");
            user.setUserId(UUID.randomUUID().toString());
            users.add(user);
        }

        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setUsername(UUID.randomUUID().toString().substring(0, 5));
            user.setType("Checker");
            user.setUserId(UUID.randomUUID().toString());
            users.add(user);
        }


        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setUsername(UUID.randomUUID().toString().substring(0, 5));
            user.setType("Maker");
            user.setUserId(UUID.randomUUID().toString());
            users.add(user);
        }

        for (int i = 0; i < 3; i++) {
            User user = new User();
            user.setUsername(UUID.randomUUID().toString().substring(0, 5));
            user.setType("Maker_Checker");
            user.setUserId(UUID.randomUUID().toString());
            users.add(user);
        }
    }

    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setType(user.getType());
        user.setUsername(user.getUsername());
        users.add(user);
        log.info("created-new-user={}", user);
    }

    @GetMapping("/delete")
    public void deleteUser(@RequestParam(name = "userId") String userId) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User element = iterator.next();
            if (element.getUserId().equals(userId)) {
                iterator.remove();
            }
        }
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody User user) {
        for (int i = 0; i < users.size(); i++) {
            User element = users.get(i);

            if (element.getUserId().equals(user.getUserId())) {
                element.setType(user.getType());
            }
        }
    }

    @PostMapping("/query")
    public List<User> queryByType(@RequestBody User user) {
        log.info("query,{}", user);
        if (Objects.isNull(user.getType()) ||
                user.getType().equalsIgnoreCase("all")) {
            return users;
        }

        List<User> result = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getType().equalsIgnoreCase(user.getType())) {
                result.add(users.get(i));
            }
        }
        return result;
    }
}
