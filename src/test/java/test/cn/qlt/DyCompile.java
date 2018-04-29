package test.cn.qlt;

import java.io.IOException;

import org.springframework.util.Assert;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.ConstPool;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;

public class DyCompile{
	

	public static void main(String[] args) throws Exception {  
		String src = "public String test(String name){System.out.println(\"name:\"+name);return name;}";
		String src1 = "public String test(String name){User user = new User();System.out.println(user);System.out.println(\"id:\"+name);return name+\"_类型2\";}";
        byte[] bytecode = markClass(src);
        ByteArrayClassLoader cl = new ByteArrayClassLoader(DyCompile.class.getClassLoader());
        Class<DyClassInterface> clz = (Class<DyClassInterface>) cl.loadClass("bean.Test", bytecode);
        DyClassInterface impl = clz.newInstance();
        System.out.println(impl.test("测试"));
        System.out.println("+++++++++++++++++");
        cl = new ByteArrayClassLoader(DyCompile.class.getClassLoader());
        clz = (Class<DyClassInterface>) cl.loadClass("bean.Test", markClass(src1));
        impl = clz.newInstance();
        System.out.println(impl.test("测试2"));
        System.out.println("================");
        //cc.writeFile("E:/workspace/TestCompiler/src");  
    }

	private static byte[] markClass(String src) throws NotFoundException, CannotCompileException, IOException {
		ClassPool pool = new ClassPool(ClassPool.getDefault()){
			@Override
			public CtClass makeClass(String classname) throws RuntimeException {
				return super.makeClass(classname);
			}
		};

        pool.importPackage("cn.qlt.domain.User");
        CtClass cc = pool.makeClass("bean.Test");
        CtClass ctif = pool.get("test.cn.qlt.DyClassInterface");
        
        cc.addInterface(ctif);
          
        //创建属性  
        CtField field01 = CtField.make("private int id;",cc);  
        CtField field02 = CtField.make("private String name;", cc);
        ConstPool cp = cc.getClassFile().getConstPool();
		Annotation annotation = new Annotation("javax.persistence.JoinColumn", cp);
        annotation.addMemberValue("name", new StringMemberValue("id",cp));
        AnnotationsAttribute attr = new AnnotationsAttribute(cp, AnnotationsAttribute.visibleTag);
        attr.addAnnotation(annotation);
        field01.getFieldInfo().addAttribute(attr);
        cc.addField(field01);  
        cc.addField(field02); 
        //创建方法  
        CtMethod method01 = CtMethod.make("public String getName(){return name;}", cc);  
        CtMethod method02 = CtMethod.make("public void setName(String name){this.name = name;}", cc);  
        cc.addMethod(method01);  
        cc.addMethod(method02);
		CtMethod method = CtMethod.make(src, cc);
        cc.addMethod(method);
        //添加有参构造器  
        CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType,pool.get("java.lang.String")},cc);  
        constructor.setBody("{this.id=id;this.name=name;}");  
        cc.addConstructor(constructor);  
        //无参构造器  
        CtConstructor cons = new CtConstructor(null,cc);  
        cons.setBody("{}");  
        cc.addConstructor(cons);  
        
        byte[] bytecode = cc.toBytecode();
		return bytecode;
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
