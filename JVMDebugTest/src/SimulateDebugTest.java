import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.NEW;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_5;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.AbstractVisitor;

import dev.java.net.jvmDebug.DebugInsertionMethodVisitor;
import dev.java.net.jvmDebug.DebugTraceMethodVisitor;

public class SimulateDebugTest {
	public static void main(String[] args) throws IOException {
		DebugInsertionMethodVisitor debugInsertionMethodVisitor;
		ClassWriter cw = new ClassWriter(0);
		cw.visit(V1_5, ACC_PUBLIC + ACC_SUPER, "Simple2", null,
				"java/lang/Object", null);
		cw.visitSource("asm.txt", null);

		{
			MethodVisitor mv;
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			debugInsertionMethodVisitor = new DebugInsertionMethodVisitor(mv,null,false,"Simple2","<init>","()V");
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(2, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Objject", "<init>",
					"()V");
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "LSimple;", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}

		{
			MethodVisitor mv;
			mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "get", "()I", null,
					null);
			debugInsertionMethodVisitor = new DebugInsertionMethodVisitor(mv,null,false,"Simple2","get","()I");
			mv.visitCode();
			Label startLabel = new Label();
			mv.visitLabel(startLabel);
			debugInsertionMethodVisitor.prepareDebug(mv);

			mv.visitLineNumber(20, startLabel);

			debugInsertionMethodVisitor.fillDebugVars(mv, 23, ICONST_1); 
			mv.visitInsn(ICONST_1);
			mv.visitVarInsn(ALOAD, 2);
			DebugInsertionMethodVisitor.createInsn(mv, 4, null);
			mv.visitMethodInsn(INVOKEVIRTUAL, "incubator/jvmsimu/JVMThread",
					"process", "(Lorg/objectweb/asm/tree/AbstractInsnNode;)[Ljava/lang/Object;");

			debugInsertionMethodVisitor.fillDebugVars(mv, 24, IRETURN); // IRETURN
			mv.visitInsn(IRETURN);
			Label endLabel = new Label();
			mv.visitLabel(endLabel);
			debugInsertionMethodVisitor.prepareDebugVars(mv, startLabel, endLabel);
			
			mv.visitMaxs(5, 3);
			mv.visitEnd();
		}

		cw.visitEnd();

		byte[] byteArray = cw.toByteArray();
		FileOutputStream fout = new FileOutputStream("bin2/Simple2.class");
		fout.write(byteArray);
		fout.close();
	}

	public int schwupp() {
		Stack s = new Stack();
		int lineNumber = 5;
		s.push(1000000);
		return (Integer) s.pop();
		// public static get() : int
		// L0 (0)
		// LINENUMBER 5 L0
		// LDC 1000000
		// IRETURN
		// MAXSTACK = 1
		// MAXLOCALS = 0

	}

}
