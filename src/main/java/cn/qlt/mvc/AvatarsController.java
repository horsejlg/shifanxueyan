package cn.qlt.mvc;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AvatarsController {
	
	@Value("${avatars.base}")
	private String basePath;

	@GetMapping("/avatars/{path1}/{path2}/{filename}.jpg")
	public void loadAvatars(String path1,String path2, String filename, HttpServletResponse response){
		response.addHeader("Content-Type", "image/jpeg");
		
	}
}
