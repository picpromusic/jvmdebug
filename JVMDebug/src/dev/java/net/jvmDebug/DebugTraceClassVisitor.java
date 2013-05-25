package dev.java.net.jvmDebug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.util.TraceAbstractVisitor;
import org.objectweb.asm.util.TraceMethodVisitor;
import org.objectweb.asm.util.TraceSignatureVisitor;

public class DebugTraceClassVisitor extends DebugTraceAbstractVisitor implements
		ClassVisitor {

	private PrintWriter pw;
	private File dir;

	/**
	 * The {@link ClassVisitor} to which this visitor delegates calls. May be
	 * <tt>null</tt>.
	 */
	protected final DebugInsertionClassVisitor cv;
	private File outpFile;
	private ClassWriter classWriter;
	public DebugTraceClassVisitor(File dir) {
		this.dir = dir;
		classWriter = new ClassWriter(0);
		cv = new DebugInsertionClassVisitor(classWriter,this);
	}

	public byte[] getResult() {
		return classWriter.toByteArray();
	}

	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		outpFile = new File(dir, name + ".asm");
		if (cv != null) {
			cv.setTraceSource(outpFile);
		}
		try {
			pw = new PrintWriter(outpFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		int major = version & 0xFFFF;
		int minor = version >>> 16;
		appendBuf("// class version ").appendBuf(major).appendBuf('.')
				.appendBuf(minor).appendBuf(" (").appendBuf(version).appendBuf(
						")\n");
		if ((access & Opcodes.ACC_DEPRECATED) != 0) {
			appendBuf("// DEPRECATED\n");
		}
		appendBuf("// access flags ").appendBuf(access).appendBuf('\n');

		appendDescriptor(CLASS_SIGNATURE, signature);
		if (signature != null) {
			TraceSignatureVisitor sv = new TraceSignatureVisitor(access);
			SignatureReader r = new SignatureReader(signature);
			r.accept(sv);
			appendBuf("// declaration: ").appendBuf(name).appendBuf(
					sv.getDeclaration()).appendBuf('\n');
		}

		appendAccess(access & ~Opcodes.ACC_SUPER);
		if ((access & Opcodes.ACC_ANNOTATION) != 0) {
			appendBuf("@interface ");
		} else if ((access & Opcodes.ACC_INTERFACE) != 0) {
			appendBuf("interface ");
		} else if ((access & Opcodes.ACC_ENUM) == 0) {
			appendBuf("class ");
		}
		appendDescriptor(INTERNAL_NAME, name);

		if (superName != null && !"java/lang/Object".equals(superName)) {
			appendBuf(" extends ");
			appendDescriptor(INTERNAL_NAME, superName);
			appendBuf(' ');
		}
		if (interfaces != null && interfaces.length > 0) {
			appendBuf(" implements ");
			for (int i = 0; i < interfaces.length; ++i) {
				appendDescriptor(INTERNAL_NAME, interfaces[i]);
				appendBuf(' ');
			}
		}
		appendBuf(" {\n\n");

		addText();

		if (cv != null) {
			cv.visit(version, access, name, signature, superName, interfaces);
		}

	}

	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");
		return null;
	}

	public void visitAttribute(Attribute attr) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitEnd() {
		appendBuf("}\n");
		addText();

		print(pw);
		pw.flush();

		if (cv != null) {
			cv.visitEnd();
		}
	}

	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");
		return null;
	}

	public void visitInnerClass(String name, String outerName,
			String innerName, int access) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		appendBuf('\n');
		if ((access & Opcodes.ACC_DEPRECATED) != 0) {
			appendBuf(tab).appendBuf("// DEPRECATED\n");
		}
		appendBuf(tab).appendBuf("// access flags ").appendBuf(access)
				.appendBuf('\n');

		if (signature != null) {
			appendBuf(tab);
			appendDescriptor(METHOD_SIGNATURE, signature);

			TraceSignatureVisitor v = new TraceSignatureVisitor(0);
			SignatureReader r = new SignatureReader(signature);
			r.accept(v);
			String genericDecl = v.getDeclaration();
			String genericReturn = v.getReturnType();
			String genericExceptions = v.getExceptions();

			appendBuf(tab).appendBuf("// declaration: ").appendBuf(
					genericReturn).appendBuf(' ').appendBuf(name).appendBuf(
					genericDecl);
			if (genericExceptions != null) {
				appendBuf(" throws ").appendBuf(genericExceptions);
			}
			appendBuf('\n');
		}

		appendBuf(tab);
		appendAccess(access);
		if ((access & Opcodes.ACC_NATIVE) != 0) {
			appendBuf("native ");
		}
		if ((access & Opcodes.ACC_VARARGS) != 0) {
			appendBuf("varargs ");
		}
		if ((access & Opcodes.ACC_BRIDGE) != 0) {
			appendBuf("bridge ");
		}

		appendBuf(name);
		appendDescriptor(METHOD_DESCRIPTOR, desc);
		if (exceptions != null && exceptions.length > 0) {
			appendBuf(" throws ");
			for (int i = 0; i < exceptions.length; ++i) {
				appendDescriptor(INTERNAL_NAME, exceptions[i]);
				appendBuf(' ');
			}
		}

		appendBuf('\n');
		addText();

		MethodVisitor temp = null; 
		if (cv != null) {
			temp = cv.visitMethod(access, name, desc, signature, exceptions);
		}

		DebugTraceMethodVisitor tcv = new DebugTraceMethodVisitor(temp);
		text.add(tcv.getText());
		lines.add(tcv);

		return tcv;
	}

	public void visitOuterClass(String owner, String name, String desc) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitSource(String source, String debug) {
		if (source != null) {
			appendBuf(tab).appendBuf("// compiled from: ").appendBuf(source)
					.appendBuf('\n');
		}
		if (debug != null) {
			appendBuf(tab).appendBuf("// debug info: ").appendBuf(debug)
					.appendBuf('\n');
		}

		addText();

		if (cv != null) {
			cv.visitSource(source, debug);
		}
	}

	/**
	 * Appends a string representation of the given access modifiers to
	 * {@link #buf buf}.
	 * 
	 * @param access
	 *            some access modifiers.
	 */
	private void appendAccess(final int access) {
		if ((access & Opcodes.ACC_PUBLIC) != 0) {
			appendBuf("public ");
		}
		if ((access & Opcodes.ACC_PRIVATE) != 0) {
			appendBuf("private ");
		}
		if ((access & Opcodes.ACC_PROTECTED) != 0) {
			appendBuf("protected ");
		}
		if ((access & Opcodes.ACC_FINAL) != 0) {
			appendBuf("final ");
		}
		if ((access & Opcodes.ACC_STATIC) != 0) {
			appendBuf("static ");
		}
		if ((access & Opcodes.ACC_SYNCHRONIZED) != 0) {
			appendBuf("synchronized ");
		}
		if ((access & Opcodes.ACC_VOLATILE) != 0) {
			appendBuf("volatile ");
		}
		if ((access & Opcodes.ACC_TRANSIENT) != 0) {
			appendBuf("transient ");
		}
		if ((access & Opcodes.ACC_ABSTRACT) != 0) {
			appendBuf("abstract ");
		}
		if ((access & Opcodes.ACC_STRICT) != 0) {
			appendBuf("strictfp ");
		}
		if ((access & Opcodes.ACC_ENUM) != 0) {
			appendBuf("enum ");
		}
	}

}
