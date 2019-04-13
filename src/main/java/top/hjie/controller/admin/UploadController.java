package top.hjie.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import top.hjie.util.CreateMD5Util;

/**
 * 
 * @author 上传文件
 *
 */
@RequestMapping("/upload")
@Controller
public class UploadController {

	
	/**
	 * @Description 统一处理上传文件，回传URL
	 * @author 何杰
	 * @date 2018年9月13日
	 */
	public String uploadFile(MultipartFile file,HttpServletRequest request){
		// 获取上传的文件名
		String fileName = file.getOriginalFilename();
		// 获取当前文件扩展名
		String extName = fileName.substring(fileName.lastIndexOf("."));
		String realPath = request.getSession().getServletContext().getRealPath("/").
				substring(0, request.getSession().getServletContext().getRealPath("/").
						lastIndexOf(request.getContextPath().replace("/", ""))) + "/UploadFile/";
		try {
			// 判断文件夹是否存在
			if(!new File(realPath).isDirectory()){
				// 不存在创建文件夹
				new File(realPath).mkdir();
			}
			// 得到字节数组进行md5加密
			byte[] bytes = file.getBytes();
			String bytesMd5 = CreateMD5Util.getMD5ByBytes(bytes);
			File filePath = new File(realPath + bytesMd5 + extName);
			// 存储到硬盘
			file.transferTo(filePath);
			// 返回文件名
			return bytesMd5 + extName;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/**
	 * @Description 富文本的文件上传  
	 * @author 何杰
	 * @date 2018年9月13日
	 */
	@RequestMapping("/editorUploadPicture")
	public ResponseEntity<?> editorUploadPicture(MultipartFile file,HttpServletRequest request){
		// 存放结果
		Map<String, Object> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		String uploadFile = uploadFile(file,request);
		list.add("/UploadFile/" + uploadFile);
		map.put("errno", 0);
		map.put("data", list);
		return ResponseEntity.ok(map);
	}
	
}
