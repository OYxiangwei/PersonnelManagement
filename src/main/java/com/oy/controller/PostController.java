package com.oy.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oy.entity.Admin;
import com.oy.entity.Post;
import com.oy.service.PostService;
import com.oy.utils.DateUtil;
import com.oy.utils.JsonDateValueProcessor;
import com.oy.utils.ResponseUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;

	@RequestMapping("/list")
	public String list(Post post, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (post.getTitle() != null && !"".equals(post.getTitle().trim())) {
			map.put("title", "%" + post.getTitle() + "%");
		}
		List<Post> postList = postService.findPosts(map);
		Integer total = postService.getCount(map);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(postList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/save")
	public String save(Post post, HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		Admin admin = (Admin) session.getAttribute("currentAdmin");
		post.setAdmin(admin);
		post.setDate(DateUtil.getDate());
		int resultTotal = 0;
		if (post.getId() == null) {
			resultTotal = postService.addPost(post);
		} else {
			resultTotal = postService.updatePost(post);
		}
		JSONObject result = new JSONObject();
		if (resultTotal > 0) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam(value = "ids") String ids, HttpServletResponse response, HttpSession session)
			throws Exception {
		JSONObject result = new JSONObject();
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			postService.deletePost(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;

	}

	@RequestMapping("/getById")
	public String getById(@RequestParam(value = "id") Integer id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Post post = postService.findPostById(id);
		request.setAttribute("postContent", post.getContent());
		return "content";

	}
}
