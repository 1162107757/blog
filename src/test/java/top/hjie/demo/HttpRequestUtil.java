package top.hjie.demo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HttpRequestUtil {

	public static String getJsonData(String jsonParam, String urls) {
		StringBuffer sb = new StringBuffer();
		try {
			// 创建url资源
			URL url = new URL(urls);
			// 建立http连接
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置允许输出
			conn.setDoOutput(true);
			// 设置允许输入
			conn.setDoInput(true);
			// 设置不用缓存
			conn.setUseCaches(false);
			// 设置传递方式
			conn.setRequestMethod("POST");
			// 设置维持长连接
			conn.setRequestProperty("Connection", "Keep-Alive");
			// 设置文件字符集:
			conn.setRequestProperty("Charset", "UTF-8");
			// 转换为字节数组
			byte[] data = (jsonParam.toString()).getBytes();
			// 设置文件长度
			conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			// 设置文件类型:
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Cookie", "_ga=GA1.2.730200280.1540520999; _gid=GA1.2.1202328595.1541053886; order=null; jsid=95af5172-4ceb-4f06-8cf0-c56ef258dd2b");
			// 开始连接请求
			conn.connect();
			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// 写入请求的字符串
			out.write((jsonParam.toString()).getBytes());
			out.flush();
			out.close();

			// 请求返回的状态
			if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
				// 请求返回的数据
				InputStream in = conn.getInputStream();
				try {
					String readLine = new String();
					BufferedReader responseReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
					while ((readLine = responseReader.readLine()) != null) {
						sb.append(readLine).append("\n");
					}
					responseReader.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else {
				System.err.println("请求状态异常: " + conn.getResponseCode());
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		// 设置参数
		List<Map<String, Object>> params = new ArrayList<>();
		Map<String, Object> param = new HashMap<>();
		param.put("productID", 61);
		param.put("itemCount", 3);
		param.put("itemMoney", 84);
		params.add(param);
		String json = "[{\"productID\":61,\"itemCount\":3,\"itemMoney\":84},{\"productID\":98,\"itemCount\":13,\"itemMoney\":117},{\"productID\":46742,\"itemCount\":1,\"itemMoney\":999}]";
		String url = "http://localhost.ak1ak1.com/AKGW-web/v1/order/test";
		String data = HttpRequestUtil.getJsonData(json, url);
		System.out.println(data);
	}

}
