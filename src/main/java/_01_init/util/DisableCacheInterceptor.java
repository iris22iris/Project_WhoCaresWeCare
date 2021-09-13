package _01_init.util;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DisableCacheInterceptor implements HandlerInterceptor {

	private static Logger log = LoggerFactory.getLogger(DisableCacheInterceptor.class);
	
	List<String> list = Arrays.asList("gif", "jpeg", "jpg", "css", "js", "png");
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 通知瀏覽器必須先以 Last-Modified or ETag送出請求來詢問Server，本網頁是否有較新的版本，
		// 如果Server回應沒有，才可以使用快取區內的網頁，否則必須再次送出請求取得更新的版本
		String servletPath = request.getServletPath();
		String ext = servletPath.substring(servletPath.lastIndexOf(".") + 1).toLowerCase();
		if (list.contains(ext)) {
			log.info("不必取消快取,  request.getServletPath()=" + request.getServletPath());
			return;
		}
		response.setHeader("Cache-Control", "no-cache");
		// 通知瀏覽器絕對不要將本網頁儲存在快取區內
		response.setHeader("Cache-Control", "no-store");
		// Causes the proxy cache to see the page as "stale",
		// 0 表示該網頁的有效期限為  1970/01/01 00:00:00 GMT，若現在時間超過它，就不能再使用
		// 快取出內的網頁
		response.setDateHeader("Expires", 0);
		// 為了與 HTTP 1.0 相容，加入此回應標頭 
		response.setHeader("Pragma", "no-cache");
		log.info("已經取消快取，request.getServletPath()=" + request.getServletPath());
	}

}
