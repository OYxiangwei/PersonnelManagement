package com.oy.service;

import java.util.List;
import java.util.Map;

import com.oy.entity.Post;

public interface PostService {
	public List<Post> findPosts(Map<String, Object> map);

	public Integer getCount(Map<String, Object> map);

	public Integer addPost(Post post);

	public Integer deletePost(Integer id);

	public Integer updatePost(Post post);

	public Post findPostById(Integer id);

}
