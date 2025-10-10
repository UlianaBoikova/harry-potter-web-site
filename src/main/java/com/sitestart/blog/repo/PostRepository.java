package com.sitestart.blog.repo;

import com.sitestart.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

/**
 This class provides methods (save or update post, find by ID, get all users, delete by ID)
 for working with the database from Post
 */
public interface PostRepository extends CrudRepository<Post, Long> {
}
