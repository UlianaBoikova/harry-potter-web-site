package com.sitestart.blog.controllers;

import com.sitestart.blog.models.MessageFromTo;
import com.sitestart.blog.models.User;
import com.sitestart.blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class BlogController {

    private User currentUser;
    @Autowired
    private UserRepository userRepository;

    public BlogController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/enter")
    public String blogEnter(Model model) {
        return "enter";
    }

    @GetMapping("/register")
    public String blogRegister(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String blogPostAdd(@RequestParam String firstName, @RequestParam String secondName, @RequestParam String password, Model model) {
        User user = new User(firstName, secondName, password);
        userRepository.save(user);
        return "redirect:/enter";
    }

    @PostMapping("/enter")
    public String enter(@RequestParam String firstName, @RequestParam String password, Model model) {
        User user = userRepository.findByFirstName(firstName);

        // Проверяем, существует ли пользователь и совпадает ли пароль
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            currentUser = userRepository.findByFirstName(firstName);;
            return "user-profile"; // Перенаправление на страницу с данными пользователя
        } else {
            model.addAttribute("error", "Неверное имя или пароль!");
            return "enter"; // Если ошибка, возвращаем на страницу входа
        }
    }

    @GetMapping("/user-profile")
    public String userProfile() {
        return "user-profile"; // Страница для отображения информации о пользователе
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        User user = currentUser;
        model.addAttribute("user", user);
        HashMap<MessageFromTo, ArrayList<String>> users = user.getMessengers();
        model.addAttribute("users", users);
        return "chat"; // Страница для отображения информации о пользователе
    }

    @PostMapping("/chat")
    public String chat(@RequestParam String firstName, @RequestParam String message, Model model) {
        User personWhoWeSendFor = userRepository.findByFirstName(firstName);
        User sender = currentUser;
        // Проверяем, существует ли пользователь и совпадает ли пароль
        if (personWhoWeSendFor != null) {
            model.addAttribute("user", sender);
            MessageFromTo messageFromTo = new MessageFromTo(sender.getFirstName(), personWhoWeSendFor.getFirstName());
            if (!sender.getMessengers().containsKey(messageFromTo)) {
                ArrayList<String> allMessages = new ArrayList<>();
                allMessages.add(message);
                sender.getMessengers().put(messageFromTo, allMessages);
                userRepository.save(sender);
                personWhoWeSendFor.getMessengers().put(messageFromTo, allMessages);
                userRepository.save(personWhoWeSendFor);
            } else {
                sender.refactor(messageFromTo, message);
                userRepository.save(sender);
                personWhoWeSendFor.refactor(messageFromTo, message);
                userRepository.save(personWhoWeSendFor);
            }
            HashMap<MessageFromTo, ArrayList<String>> users = sender.getMessengers();

            model.addAttribute("users", users);
            return "chat"; // Перенаправление на страницу с данными пользователя
        } else {
            model.addAttribute("error", "Неверное имя или пароль!");
            return "enter"; // Если ошибка, возвращаем на страницу входа
        }
    }
/*
    @PostMapping("/chat")
    public String chat(@RequestParam String firstName, @RequestParam String message, Model model) {
        User user = userRepository.findByFirstName(firstName);

        // Проверяем, существует ли пользователь и совпадает ли пароль
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("user", user);
            return "user-profile"; // Перенаправление на страницу с данными пользователя
        } else {
            model.addAttribute("error", "Неверное имя или пароль!");
            return "enter"; // Если ошибка, возвращаем на страницу входа
        }
    }

 */


/*
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
             return "redirect:/blog";
        }
        Post exactPost = postRepository.findById(id).orElseThrow();
        System.out.println("Watching new post " + exactPost.getViews() + " " + exactPost.getTitle());
        exactPost.newView();
        postRepository.save(exactPost);
        System.out.println("Watching new post " + exactPost.getViews() + " " + exactPost.getTitle());
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }



    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }
     */
}