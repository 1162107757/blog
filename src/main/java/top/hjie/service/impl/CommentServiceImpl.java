package top.hjie.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.hjie.mapper.CommentMapper;
import top.hjie.service.ICommentService;

@Service
public class CommentServiceImpl implements ICommentService {

	@Resource
	private CommentMapper commentMapper;
	
	
	
}
