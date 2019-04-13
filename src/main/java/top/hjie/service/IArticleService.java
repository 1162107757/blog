package top.hjie.service;

import java.util.ArrayList;

import top.hjie.pojo.Article;
import top.hjie.util.Page;

public interface IArticleService {

	void addArticle(Article article);

	Page getArticleList(Page info, String searchContent);

	void delArticle(ArrayList<String> articleIds);

	Article getArticle(String articleId);

}
