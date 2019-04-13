package top.hjie.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import top.hjie.pojo.Admin;
import top.hjie.pojo.Article;
import top.hjie.pojo.User;
import top.hjie.service.IArticleService;
import top.hjie.util.Page;

@RequestMapping("/article")
@Controller
public class ArticleController {

	@Resource
	private IArticleService articleService;
	
	/**
	 * @Description 新增文章
	 * @author 何杰
	 * @date 2018年9月14日
	 */
	@RequestMapping("/addArticle")
	public ResponseEntity<?> addArticle(Article article,HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		// 从session域取两个对象判断是前台发帖还是后台发帖
		User user = (User)request.getSession().getAttribute("User");
		Admin admin = (Admin)request.getSession().getAttribute("Admin");
		try {
			if(user == null){
				if(admin == null){
					map.put("success", false);
					map.put("msg", "请先登录！");
					return ResponseEntity.ok(map);
				}else{
					article.setArticleId(UUID.randomUUID().toString());
					article.setReleaseTime(new Date());
					article.setCreateUserId(admin.getAdminId());
					articleService.addArticle(article);
				}
			}else{
				article.setArticleId(UUID.randomUUID().toString());
				article.setReleaseTime(new Date());
				article.setCreateUserId(user.getUserId());
				articleService.addArticle(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "系统错误，文章发布失败！");
			return ResponseEntity.ok(map);
		}
		map.put("success", true);
		map.put("msg", "文章发布成功！");
		return ResponseEntity.ok(map);
	}
	
	/**
	 * @Description 分页查询所有文章
	 * @author 何杰
	 * @date 2018年9月14日
	 */
	@RequestMapping("/getArticleList")
	public ResponseEntity<?> getArticleList(Page info,String searchContent){
		info = articleService.getArticleList(info,searchContent);
		return ResponseEntity.ok(info);
	}
	
	/**
	 * @Description 删除文章
	 * @author 何杰
	 * @date 2018年9月14日
	 */
	@RequestMapping("/delArticle")
	public ResponseEntity<?> delArticle(@RequestBody ArrayList<String> articleIds){
		Map<String, Object> map = new HashMap<>();
		try {
			articleService.delArticle(articleIds);
			map.put("success", true);
			map.put("msg", "删除成功！");
			return ResponseEntity.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "删除失败！");
			return ResponseEntity.ok(map);
		}
	}
	
	/**
	 * @Description 获取一篇文章
	 * @author 何杰
	 * @date 2018年9月15日
	 */
	@RequestMapping("/getArticle")
	public ResponseEntity<?> getArticle(String articleId){
		Map<String, Object> map = new HashMap<>();
		try {
			Article article = articleService.getArticle(articleId);
			if(article != null){
				map.put("data", article);
				map.put("success", true);
				map.put("msg", "");
				return ResponseEntity.ok(map);
			}else{
				throw new RuntimeException();
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("success", false);
			map.put("msg", "系统错误，信息获取失败！");
			return ResponseEntity.ok(map);
		}
	}
	
}
