package com.sitestart.blog.models;

import jakarta.persistence.Lob;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 This class describes an article that can be posted on the site.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    private String title;
    @Lob
    private String anons;
    @Lob
    private String full_text;
    private int views = 0;

    /**
      Constructor for the Post class, which represents the title, blurb, and full text of an article
      @param title the title of an article
      @param anons the blurb of an article
      @param full_text the full text of an article
     */
    public Post(String title, String anons, String full_text) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        views++;
    }

    /**
     Default constructor for Post class
     */
    public Post() {
        views++;
    }

    /**
     Increases the number of views of a post on the site
     */
    public void newView() {
        views++;
    }

    /**
      Gets the post ID.
      @return the post ID
     */
    public Long getId() {
        return id;
    }

    /**
      Sets the post ID.
      @param id the post ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
      Gets the title of the post.
      @return title of the post
     */
    public String getTitle() {
        return title;
    }

    /**
      Sets the title of the post.
      @param title the title of the post
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
      Gets the blurb of the post.
      @return the blurb of the post
     */
    public String getAnons() {
        return anons;
    }

    /**
      Sets the blurb of the post.
      @param anons the blurb of the post
     */
    public void setAnons(String anons) {
        this.anons = anons;
    }

    /**
      Gets the full text of the post.
      @return the full text of the post
     */
    public String getFull_text() {
        return full_text;
    }

    /**
      Sets the full text of the post.
      @param full_text the full text of the post
     */
    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    /**
     Gets amount of views on this post.
     @return amount of views on this post
     */
    public int getViews() {
        return views;
    }

    /**
      Sets amount of views on this post.
      @param views amount of views on this post
     */
    public void setViews(int views) {
        this.views = views;
    }
}
