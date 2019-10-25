package com.oy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.oy.entity.Post;

@Repository
public interface PostDao {
	public List<Post> findPosts(Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public Integer addPost(Post post);

	public Integer deletePost(Integer id);

	public Integer updatePost(Post post);

	public Post findPostById(Integer id);

}
