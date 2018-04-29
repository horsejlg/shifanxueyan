package test.cn.qlt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;


public class reload {
 public static void main(String[] args) throws Exception {
	FileInputStream in = new FileInputStream("E:/play/RPGVXAce/ゲーム/save/file1.rpgsave");
	byte[] b = new byte[in.available()];
	//BASE64Decoder decoder = new BASE64Decoder();
	//ByteBuffer buffer = decoder.decodeBufferToByteBuffer(in);
	//System.out.println(new String(buffer.array()));
}
}
