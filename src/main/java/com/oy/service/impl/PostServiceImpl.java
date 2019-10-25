package com.oy.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oy.dao.PostDao;
import com.oy.entity.Post;
import com.oy.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService {
	@Autowired
	private PostDao postDao;

	public List<Post> findPosts(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return postDao.findPosts(map);
	}

	public Integer getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return postDao.getCount(map);
	}

	public Integer addPost(Post post) {
		// TODO Auto-generated method stub
		return postDao.addPost(post);
	}

	public Integer deletePost(Integer id) {
		// TODO Auto-generated method stub
		return postDao.deletePost(id);
	}

	public Integer updatePost(Post post) {
		// TODO Auto-generated method stub
		return postDao.updatePost(post);
	}

	public Post findPostById(Integer id) {
		// TODO Auto-generated method stub
		return postDao.findPostById(id);
	}

}
