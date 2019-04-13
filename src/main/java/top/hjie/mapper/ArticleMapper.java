package top.hjie.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;
import top.hjie.pojo.Article;

@Repository
public interface ArticleMapper extends Mapper<Article> {

	List<Article> getArticleList(@Param("searchContent")String searchContent);

	void delArticle(@Param("articleIds")ArrayList<String> articleIds);

	Article getArticle(@Param("articleId")String articleId);

}
