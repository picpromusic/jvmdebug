import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

public class DumpASM {
	public static void main(String[] args) throws IOException {
		dump("bin/Simple.class", "asm.txt");
		dump("asm2/Simple.class", "asm2.txt");
		dump("asm2/Simple2.class", "asm3.txt");
		dump("bin/TestEnum.class", "TestEnum.txt");
		
	}
	private static void dump(String source, String dest) throws FileNotFoundException, IOException {
		File file = new File(source);
		FileInputStream fin = new FileInputStream(file);
		byte[] in = new byte[(int) file.length()];
		fin.read(in);
		fin.close();
		FileOutputStream fout = new FileOutputStream(dest);
		printAsm(in,fout);
	}
	public static void printAsm(byte[] transformed, OutputStream outp) {
		ClassReader classReader = new ClassReader(transformed);
		PrintWriter pw;
		pw = new PrintWriter(outp);
		TraceClassVisitor traceClassVisitor = new TraceClassVisitor(pw);
		classReader.accept(traceClassVisitor, ClassReader.EXPAND_FRAMES);
		pw.close();

	}

}
