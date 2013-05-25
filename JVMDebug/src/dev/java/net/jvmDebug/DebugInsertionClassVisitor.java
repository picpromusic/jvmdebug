package dev.java.net.jvmDebug;

import java.io.File;
import java.lang.reflect.Modifier;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

public class DebugInsertionClassVisitor implements ClassVisitor {

	private File traceSource;
	private ClassWriter cw;
	private LineNumberator ln;
	private String className;

	public DebugInsertionClassVisitor(ClassWriter classWriter,LineNumberator ln) {
		this.cw = classWriter;
		this.ln = ln;
	}

	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		this.className = name;
		cw.visit(version, access, name, signature, superName, interfaces);
	}

	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public void visitAttribute(Attribute attr) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public void visitEnd() {
		if (cw != null) {
			cw.visitEnd();
		}
	}

	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public void visitInnerClass(String name, String outerName,
			String innerName, int access) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		MethodVisitor methodWriter = cw.visitMethod(access, name, desc, signature, exceptions);
		if (name.equals("get")) {
			return new DebugInsertionMethodVisitor(methodWriter,ln,Modifier.isStatic(access));
		} else {
			return new CopyMethodVisitor(methodWriter);
		}
	}

	public void visitOuterClass(String owner, String name, String desc) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public void visitSource(String source, String debug) {
		cw.visitSource(className+".asm", null);
	}

	public void setTraceSource(File traceSource) {
		this.traceSource = traceSource;
	}

}
