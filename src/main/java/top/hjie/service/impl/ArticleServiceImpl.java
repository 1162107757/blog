package top.hjie.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import top.hjie.mapper.ArticleMapper;
import top.hjie.pojo.Article;
import top.hjie.service.IArticleService;
import top.hjie.util.Page;

@Service
@Transactional
public class ArticleServiceImpl implements IArticleService {

	@Resource
	private ArticleMapper articleMapper;
	
	@Override
	public void addArticle(Article article) {
		articleMapper.insertSelective(article);
	}

	@Override
	public Page getArticleList(Page info, String searchContent) {
		PageHelper.startPage(info.getPage(), info.getLimit());
		List<Article> articles = articleMapper.getArticleList(searchContent);
		PageInfo<Article> pageInfo  = new PageInfo<>(articles);
		
		info.setData(pageInfo.getList());
		info.setTotal((int)pageInfo.getTotal());
		info.setTotalPage(info.getTotalPage());
		info.setPrevPage(info.getPrevPage());
		info.setNextPage(info.getNextPage());
		return info;
	}

	@Override
	public void delArticle(ArrayList<String> articleIds) {
		try {
			articleMapper.delArticle(articleIds);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public Article getArticle(String articleId) {
		return articleMapper.getArticle(articleId);
	}

}
