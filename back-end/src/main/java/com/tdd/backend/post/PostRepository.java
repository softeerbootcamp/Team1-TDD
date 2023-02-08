package com.tdd.backend.post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tdd.backend.post.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
}
