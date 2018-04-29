package cn.qlt.mvc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.qlt.service.ImportService;
import cn.qlt.utils.web.Auth;
import cn.qlt.utils.web.AuthUtil;

@Controller
public class FileUploadController {

	@Autowired
	private ImportService importService;

	@Auth
	@RequestMapping(value = "/console/info/fileUpload", method = { RequestMethod.POST })
	@ResponseBody
	public String upLoadFile(@RequestParam("file") MultipartFile file) throws Exception{
		String name = file.getOriginalFilename();
		String extname = name.substring(name.lastIndexOf("."));
		File save = getSaveFile("files",extname);
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
		return "/"+save.getPath().replace("\\", "/");
	}
	
	@Auth
	@RequestMapping(value = "/console/info/fileUpload2", method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, String> upLoadFile2(@RequestParam("file") MultipartFile file) throws Exception{
		String name = file.getOriginalFilename();
		String extname = name.substring(name.lastIndexOf("."));
		File save = getSaveFile("files",extname);
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
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("path", "/"+save.getPath().replace("\\", "/"));
		map.put("label", name);
		return map;
	}
	
	
	@Auth(role=AuthUtil.SYSTEM)
	@RequestMapping(value = "/console/import/users", method = { RequestMethod.POST })
	@ResponseBody
	public boolean importUsers(@RequestParam("file")MultipartFile file, String grade, String years, String classes, String specialty) throws Exception{
		importService.importUsers(grade, years, classes, specialty, file.getInputStream());
		return true;
	}
	

	private File getSaveFile(String base,String extname) {
		Date date = new Date();
		String path = String.format("/%1$tC%1$ty/%1$tm/%1$td", date);
		File dir = new File(base+path);
		if (!(dir.exists() && dir.isDirectory())) {
			dir.mkdirs();
		}
		String filename = date.getTime() + extname;
		File save = new File(dir, filename);
		return save;
	}

}
