package cn.qlt.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.qlt.utils.AvatarsUtil;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.AuthUtil;

@Controller
public class AvatarsController {
	
	@Value("${avatars.base}")
	private String basePath;

	@GetMapping("/avatars/{path1}/{path2}/{filename}.jpg")
	public void loadAvatars(@PathVariable String path1, @PathVariable String path2, @PathVariable String filename, HttpServletResponse response) throws IOException{
		response.addHeader("Content-Type", "image/jpeg");
		File file  = new File(basePath, String.format("%s/%s/%s.jpg", path1, path2, filename));
		if(!file.exists()){
			file = new File(basePath, "empty.jpg");
		}
		FileInputStream in = new FileInputStream(file);
		byte [] b = new byte[4096];
		int length = -1;
		ServletOutputStream out = response.getOutputStream();
		while((length = in.read(b))>0){
			out.write(b,0,length);
		}
		out.flush();
		out.close();
	}
	
	@Auth
	@RequestMapping(value = "/avatars/{id}", method = { RequestMethod.POST })
	@ResponseBody
	public boolean upLoadFile(@RequestParam("file") MultipartFile file, @PathVariable String id) throws Exception{
		String name = file.getOriginalFilename();
		String extname = name.substring(name.lastIndexOf("."));
		if(!".jpg".equals(extname.toLowerCase())){
			throw new Exception("上传文件类型错误，要求jpg类型");
		}
		File save = new File(basePath, AvatarsUtil.getSavePath(id));
		File parentFile = save.getParentFile();
		if(!parentFile.exists() || !parentFile.isDirectory()){
			parentFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(save);
		byte[] b = new byte[4096];
		int length = 0;
		InputStream in = file.getInputStream();
		while((length = in.read(b))>0){
			out.write(b, 0, length);
		}
		out.flush();
		out.close();
		in.close();
		return true;
	}
}
