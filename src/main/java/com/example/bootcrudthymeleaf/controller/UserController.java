package com.example.bootcrudthymeleaf.controller;

import com.example.bootcrudthymeleaf.ds.User;
import com.example.bootcrudthymeleaf.repo.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/signup")
    public String showSignupForm(@ModelAttribute User user, Model model){
        return "add-user";
    }
    @PostMapping("/add-user")
    public String addUser(@ModelAttribute @Valid User user, BindingResult result, Model model){
        if (result.hasErrors()){
            return "add-user";
        }
        userRepo.save(user);
        return "redirect:/index";
    }
    @GetMapping("/index")
    public String showUserList(Model model){
        model.addAttribute("users",userRepo.findAll());
        return "index";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable long id, Model model){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("INVALID USER ID" + id));
        model.addAttribute("user",user);
        return "update-user";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable long id,@Valid User user,BindingResult result,Model model){
        if (result.hasErrors()){
            user.setId(id);
            return "update-user";
        }
        userRepo.save(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id,Model model){
        User user = userRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("INVALID USER ID :" + id));
        userRepo.delete(user);
        return "redirect:/index";
    }
    @ModelAttribute("user")
    public User user(){
        return new User();
    }
}
