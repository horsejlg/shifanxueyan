package cn.qlt.utils;

public class AvatarsUtil {

	
	public static String getPath(String id){
		int hashCode = id.hashCode();
		return String.format("/avatars/%d/%d/%s.jpg", hashCode%100, hashCode/100,id);
	}
	
	public static String getSavePath(String id){
		int hashCode = id.hashCode();
		return String.format("/%d/%d/%s.jpg", hashCode%100, hashCode/100,id);
	}
}
