package top.hjie.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import top.hjie.pojo.Admin;
import top.hjie.service.IAdminService;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Resource
	private IAdminService adminService;
	
	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.setAdminCode("6666");
	}
}
