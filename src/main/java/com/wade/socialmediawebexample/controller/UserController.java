package com.wade.socialmediawebexample.controller;

import com.wade.socialmediawebexample.dto.Result;
import com.wade.socialmediawebexample.model.Member;
import com.wade.socialmediawebexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;


    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Member> register(@RequestBody Member member) {

        String hashedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(hashedPassword);
        userService.add(member);

        Member result = userService.getUserByUsername(member.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
