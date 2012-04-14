package dev.java.net.jvmDebug;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

public class DebugClassTransformer implements ClassFileTransformer {

	private Set<String> onlyTransform;
	private File dir;
	private File dir2;

	public DebugClassTransformer(String[] onlyTransform) {
		this.onlyTransform = new HashSet<String>();
		for (String clazz : onlyTransform) {
			this.onlyTransform.add(clazz);
		}
		dir = new File("asm");
		dir2 = new File("asm2");
		dir.mkdirs();
		dir2.mkdirs();
	}

	public byte[] transform(ClassLoader loader, String className,
			Class<?> classBeingRedefined, ProtectionDomain protectionDomain,
			byte[] classfileBuffer) throws IllegalClassFormatException {
		if (className.startsWith("net/java/dev/jvmDebug/")) {
			return classfileBuffer;
		}
		if (this.onlyTransform == null
				|| this.onlyTransform.contains(className)) {
			// System.out.println(className);
			try {
				return transform(classfileBuffer, className);
			} catch (Throwable e) {
				e.printStackTrace();
				return new byte[0];

			}
		}
		return classfileBuffer;
	}

	private byte[] transform(byte[] classfileBuffer, String className)
			throws IOException {
		ClassReader classReader = new ClassReader(classfileBuffer);
		DebugTraceClassVisitor debugClassVisitor = new DebugTraceClassVisitor(
				dir);
		classReader.accept(debugClassVisitor, ClassReader.EXPAND_FRAMES);
		byte[] result = debugClassVisitor.getResult();
		printAsm(result, new File(dir2, className
				+ ".txt"));
		print(result, new File(dir2,className+".class"));
		return debugClassVisitor.getResult();
	}

	private static void printAsm(byte[] transformed, File outp)
			throws FileNotFoundException {
		ClassReader classReader = new ClassReader(transformed);
		PrintWriter pw;
		pw = new PrintWriter(new FileOutputStream(outp));
		TraceClassVisitor traceClassVisitor = new TraceClassVisitor(pw);
		classReader.accept(traceClassVisitor, ClassReader.EXPAND_FRAMES);
		pw.close();

	}

	private static void print(byte[] transformed, File outp)
			throws IOException {
		FileOutputStream fout;
		fout = new FileOutputStream(outp);
		fout.write(transformed);
		fout.close();

	}

}
