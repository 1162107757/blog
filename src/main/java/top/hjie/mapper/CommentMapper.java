package top.hjie.mapper;

import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import top.hjie.pojo.Comment;

@Repository
public interface CommentMapper extends Mapper<Comment> {

}
