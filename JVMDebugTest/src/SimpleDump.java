import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SimpleDump implements Opcodes {

	public static void main(String[] args) throws Exception {
		FileOutputStream fout = new FileOutputStream("asm2/Simple2.class");
		fout.write(dump());
		fout.close();
	}

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_6, ACC_PUBLIC + ACC_SUPER, "Simple2", null,
				"java/lang/Object", null);

		cw.visitSource("Simple.asm", null);

		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(8, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>",
					"()V");
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "LSimple;", null, l0, l1, 0);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "get", "()I", null,
					null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(20, l0);
			mv.visitTypeInsn(NEW, "incubator/jvmsimu/JVMThread");
			mv.visitInsn(DUP);
			mv.visitTypeInsn(NEW, "incubator/jvmsimu/JVM");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "incubator/jvmsimu/JVM",
					"<init>", "()V");
			mv.visitTypeInsn(NEW, "incubator/jvmsimu/JVMStack");
			mv.visitInsn(DUP);
			mv.visitMethodInsn(INVOKESPECIAL, "incubator/jvmsimu/JVMStack",
					"<init>", "()V");
			mv.visitMethodInsn(INVOKESPECIAL, "incubator/jvmsimu/JVMThread",
					"<init>",
					"(Lincubator/jvmsimu/JVM;Lincubator/jvmsimu/JVMStack;)V");
			mv.visitVarInsn(ASTORE, 2);
			mv.visitLdcInsn(new Integer(21));
			mv.visitVarInsn(ISTORE, 0);
			mv.visitLdcInsn("L0");
			mv.visitVarInsn(ASTORE, 1);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLineNumber(21, l1);
			mv.visitLdcInsn(new Integer(22));
			mv.visitVarInsn(ISTORE, 0);
			mv.visitLdcInsn("LINENUMBER 11 L0");
			mv.visitVarInsn(ASTORE, 1);
			Label l2 = new Label();
			mv.visitLabel(l2);
			mv.visitLineNumber(22, l2);
			mv.visitLdcInsn(new Integer(23));
			mv.visitVarInsn(ISTORE, 0);
			mv.visitLdcInsn("ICONST_5");
			mv.visitVarInsn(ASTORE, 1);
			Label l3 = new Label();
			mv.visitLabel(l3);
			mv.visitLineNumber(23, l3);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitTypeInsn(NEW, "org/objectweb/asm/tree/InsnNode");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(new Integer(8));
			mv.visitMethodInsn(INVOKESPECIAL,
					"org/objectweb/asm/tree/InsnNode", "<init>", "(I)V");
			mv.visitInsn(ACONST_NULL);
			mv
					.visitMethodInsn(
							INVOKEVIRTUAL,
							"incubator/jvmsimu/JVMThread",
							"process",
							"(Lorg/objectweb/asm/tree/AbstractInsnNode;[Ljava/lang/Object;)[Ljava/lang/Object;");
			mv.visitInsn(POP);
			mv.visitLdcInsn(new Integer(24));
			mv.visitVarInsn(ISTORE, 0);
			mv.visitLdcInsn("ISTORE");
			mv.visitVarInsn(ASTORE, 1);
			Label l4 = new Label();
			mv.visitLabel(l4);
			mv.visitLineNumber(24, l4);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitTypeInsn(NEW, "org/objectweb/asm/tree/InsnNode");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(new Integer(54));
			mv.visitLdcInsn(new Integer(3));
			mv.visitMethodInsn(INVOKESPECIAL,
					"org/objectweb/asm/tree/VarInsnNode", "<init>", "(II)V");
			mv.visitInsn(ACONST_NULL);
			mv
					.visitMethodInsn(
							INVOKEVIRTUAL,
							"incubator/jvmsimu/JVMThread",
							"process",
							"(Lorg/objectweb/asm/tree/AbstractInsnNode;[Ljava/lang/Object;)[Ljava/lang/Object;");
			mv.visitInsn(ICONST_0);
			mv.visitInsn(AALOAD);
			mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue",
					"()I");
			mv.visitVarInsn(ISTORE, 3);
			mv.visitLdcInsn(new Integer(25));
			mv.visitVarInsn(ISTORE, 0);
			mv.visitLdcInsn("L1");
			mv.visitVarInsn(ASTORE, 1);
			Label l5 = new Label();
			mv.visitLabel(l5);
			mv.visitLineNumber(25, l5);
			mv.visitLdcInsn(new Integer(26));
			mv.visitVarInsn(ISTORE, 0);
			mv.visitLdcInsn("LINENUMBER 12 L1");
			mv.visitVarInsn(ASTORE, 1);
			Label l6 = new Label();
			mv.visitLabel(l6);
			mv.visitLineNumber(26, l6);
			mv.visitLdcInsn(new Integer(27));
			mv.visitVarInsn(ISTORE, 0);
			mv.visitLdcInsn("ILOAD");
			mv.visitVarInsn(ASTORE, 1);
			Label l7 = new Label();
			mv.visitLabel(l7);
			mv.visitLineNumber(27, l7);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitTypeInsn(NEW, "org/objectweb/asm/tree/InsnNode");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(new Integer(21));
			mv.visitLdcInsn(new Integer(3));
			mv.visitMethodInsn(INVOKESPECIAL,
					"org/objectweb/asm/tree/VarInsnNode", "<init>", "(II)V");
			mv.visitInsn(ICONST_1);
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
			mv.visitInsn(ICONST_0);
			mv.visitVarInsn(ILOAD, 3);
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf",
					"(I)Ljava/lang/Integer;");
			mv.visitInsn(AASTORE);
			mv
					.visitMethodInsn(
							INVOKEVIRTUAL,
							"incubator/jvmsimu/JVMThread",
							"process",
							"(Lorg/objectweb/asm/tree/AbstractInsnNode;[Ljava/lang/Object;)[Ljava/lang/Object;");
			mv.visitInsn(POP);
			mv.visitLdcInsn(new Integer(28));
			mv.visitVarInsn(ISTORE, 0);
			mv.visitLdcInsn("IRETURN");
			mv.visitVarInsn(ASTORE, 1);
			Label l8 = new Label();
			mv.visitLabel(l8);
			mv.visitLineNumber(28, l8);
			mv.visitVarInsn(ALOAD, 2);
			mv.visitTypeInsn(NEW, "org/objectweb/asm/tree/InsnNode");
			mv.visitInsn(DUP);
			mv.visitLdcInsn(new Integer(172));
			mv.visitMethodInsn(INVOKESPECIAL,
					"org/objectweb/asm/tree/InsnNode", "<init>", "(I)V");
			mv.visitInsn(ACONST_NULL);
			mv
					.visitMethodInsn(
							INVOKEVIRTUAL,
							"incubator/jvmsimu/JVMThread",
							"process",
							"(Lorg/objectweb/asm/tree/AbstractInsnNode;[Ljava/lang/Object;)[Ljava/lang/Object;");
			mv.visitInsn(ICONST_0);
			mv.visitInsn(AALOAD);
			mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue",
					"()I");
			mv.visitInsn(IRETURN);
			mv.visitLdcInsn(new Integer(29));
			mv.visitVarInsn(ISTORE, 0);
			mv.visitLdcInsn("L2");
			mv.visitVarInsn(ASTORE, 1);
			Label l9 = new Label();
			mv.visitLabel(l9);
			mv.visitLocalVariable("lineNumber", "I", null, l0, l9, 0);
			mv.visitLocalVariable("$_content_$", "Ljava/lang/String;", null,
					l0, l9, 1);
			mv.visitLocalVariable("$_context_$",
					"Lincubator/jvmsimu/JVMThread;", null, l0, l9, 2);
			mv.visitLocalVariable("temp", "I", null, l0, l9, 3);
			mv.visitMaxs(11, 4);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}
