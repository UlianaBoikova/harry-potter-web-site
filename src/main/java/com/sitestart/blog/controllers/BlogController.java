package com.sitestart.blog.controllers;

import com.sitestart.blog.models.Post;
import com.sitestart.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.attoparser.dom.Text;

import java.util.ArrayList;
import java.util.Optional;

/**
 A controller that navigates between pages of a site and processes some operations on pages.
 */
@Controller
public class BlogController {

    @Autowired
    private PostRepository postRepository;

    /**
      Returns the blog page and showss all posts on it.
      @param model data container
      @return "blog.html"
     */
    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog";
    }

    /**
      Returns the page, where you can add any posts you need.
     * @param model data container
     * @return "blog-add.html"
     */
    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        return "blog-add";
    }

    /**
     Returns the test page,where you can take the test for placement into faculties.
      @param model data container
      @return "test.html"
     */
    @GetMapping("/test")
    public String blogTest(Model model) {
        return "test";
    }

    /**
     Returns the page, where you can complete the Harry Potter game.
      @param model data container
      @return "game.html"
     */
    @GetMapping("/game")
    public String blogGame(Model model) {
        return "game";
    }

    /**
     This method posts your article to the web-site and navigates you to the blog page.
     * @param title the title of an article
     * @param anons the blurb of an article
     * @param full_text the full text of an article
     * @param model data container
     * @return "blog.html" if successful
     */
    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = new Post(title, anons, full_text);
        post.setViews(0);
        postRepository.save(post);
        return "redirect:/blog";
    }

    /**
     Shows a separate page with the selected article.
      @param id an ID of an article
      @param model data container
      @return "blog-details.html"
     */
    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if (!postRepository.existsById(id)) {
            return "redirect:/blog";
        }
        // Finds selected article by ID
        Post exactPost = postRepository.findById(id).orElseThrow();
        // Adds a new view to the selected article and saves it
        exactPost.newView();
        postRepository.save(exactPost);
        // Adds this article to the html page
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "blog-details";
    }

    /**
     Returns the page for editing the selected article.
      @param id an ID of an article
      @param model data container
      @return "blog-edit.html"
     */
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

    /**
     This method allows you to edit the selected article.
      @param id ID of an article
      @param title a new title for an article
      @param anons a new blurb dor an article
      @param full_text a new text for an article
      @param model data container
      @return "blog.html"
     */
    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }

    /**
     This method allows you to delete any post from the blog page.
      @param id an ID of an article
      @param model data container
      @return "blog.html"
     */
    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }
}