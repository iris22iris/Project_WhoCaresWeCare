package com.web.store.service;

import com.web.store.model._02_customerService.CommentBean;

public interface CommentService {

	//	使用評價編號尋找評價
	CommentBean findCommentById(Integer commentId);

	//	新增評價
	void addComment(CommentBean commentBean);

	//	更新評價
	void updateComment(CommentBean commentBean);

}