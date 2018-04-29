package test.cn.qlt;

import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.util.Assert;

public class TestDyCompile{

	public static void main(String[] args) throws Exception{
		// 动态类模版
		StringBuilder classTemplate = new StringBuilder();
		classTemplate.append("package test.cn.qlt;\r\n");
		classTemplate.append("%s\r\n");
		classTemplate.append("public class %s implements DyClassInterface{\r\n");
		classTemplate.append("%s");
		classTemplate.append("public String test(String arg) throws Exception{")
			.append("String result = null;\r\n")
			.append("%s\r\n")
			.append("return result;\r\n}\r\n}");
		
		StringWriter writer = new StringWriter();
		//类名称
		String name = "testdyclassimpl";
		//主要过程代码
		StringBuilder code = new StringBuilder();
		//可以把代码拆分成若干个独立分段根据需求添加
		code.append("result = arg;\r\n")
			.append("System.out.println(\"调用动态创建类\");\r\n");
		String imports = "";
		String methodCodes = "";
		writer.write(String.format(classTemplate.toString(), imports,name,methodCodes,code));
		
		//类的字节码输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Writer byteWriter = new OutputStreamWriter(out);
		
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
        JavaFileObject fileObject = new StringJavaFileObject(name, writer.toString());
        List<String> options = Arrays.asList("-d","./bin");
		CompilationTask task = javaCompiler.getTask(byteWriter, fileManager, null, options, null, Arrays.asList(fileObject));
        boolean success = task.call();
        if (!success) {
            System.out.println("编译失败");
            return;
        }
        fileManager.flush();
        fileManager.close();
        byteWriter.flush();
       // byteWriter.close();
        out.flush();
        ByteArrayClassLoader classloader = new ByteArrayClassLoader(TestDyCompile.class.getClassLoader());
        byte[] codes = out.toByteArray();
        System.out.println(codes.length);
		Class<DyClassInterface> classl = (Class<DyClassInterface>) classloader.loadClass("test.cn.qlt.testdyclassimpl", codes);
        /*URL[] urls = new URL[]{new URL("file:/" + "./bin/")};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class<DyClassInterface> classl = (Class<DyClassInterface>) classLoader.loadClass("test.cn.qlt.testdyclassimpl");*/
		
        DyClassInterface dy = classl.newInstance();
        
       System.out.println(dy.test("测试"));
	}
	
	static class StringJavaFileObject extends SimpleJavaFileObject {

        private final String sourceCode;

        public StringJavaFileObject(String className, String sourceCode) {
            super(URI.create("string:///" + className.replace('.', '/')
                + Kind.SOURCE.extension), Kind.SOURCE);
            this.sourceCode = sourceCode;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return sourceCode;
        }

    }
	
	static class ByteArrayClassLoader extends ClassLoader {

		public ByteArrayClassLoader(ClassLoader parent) {
			super(parent);
		}

		/**
		 * Tries to load a class given {@code byte[]}.
		 * 
		 * @param name must not be {@literal null}
		 * @param bytes must not be {@literal null}
		 * @return
		 */
		public Class<?> loadClass(String name, byte[] bytes) {

			Assert.notNull(name, "name must not be null");
			Assert.notNull(bytes, "bytes must not be null");

			try {
				Class<?> clazz = findClass(name);
				if (clazz != null) {
					return clazz;
				}
			} catch (ClassNotFoundException ignore) {}

			return defineClass(name, bytes, 0, bytes.length);
		}
	}

}
