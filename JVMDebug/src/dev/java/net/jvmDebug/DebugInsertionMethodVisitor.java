package dev.java.net.jvmDebug;

import static org.objectweb.asm.Opcodes.*;

import incubator.jvmsimu.ProgDefs;
import incubator.jvmsimu.JVMThread.Prog;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.objectweb.asm.util.AbstractVisitor;

public class DebugInsertionMethodVisitor implements MethodVisitor {

	private static final boolean TRACE_INSN = false;
	private MethodVisitor mw;
	private LineNumberator ln;
	/**
	 * The label names. This map associate String values to Label keys.
	 */
	private final Map<Label, String> labelNames;
	private boolean additionalVarsDefined;
	private Label firstLabel;
	private Label lastLabel;
	private boolean nonStatic;

	private static class LineNumberEntry {
		public int ln;
		public Label label;
	}

	public DebugInsertionMethodVisitor(MethodVisitor methodWriter,
			LineNumberator ln, boolean staticMethod) {
		this.mw = methodWriter;
		this.ln = ln;
		labelNames = new HashMap<Label, String>();
		nonStatic = !staticMethod;
	}

	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");
		return null;
	}

	public AnnotationVisitor visitAnnotationDefault() {
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

	public void visitCode() {
		if (this.mw != null) {
			mw.visitCode();
			firstLabel = newLabel();
			mw.visitLabel(firstLabel);
			prepareDebug(mw);

			mw.visitLineNumber(ln.getCurLine(), firstLabel);
		}
	}

	private Label newLabel() {
		this.lastLabel = new Label();
		return lastLabel;
	}

	public void visitEnd() {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");
		// writeAdditionalVars();
	}

	public void visitFieldInsn(int opcode, String owner, String name,
			String desc) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitFrame(int type, int local, Object[] local2, int stack,
			Object[] stack2) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitIincInsn(int var, int increment) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitInsn(int opcode) {
		if (mw != null) {
			lastLabel = fillDebugVars(mw, ln.getCurLine(), opcode);
			mw.visitVarInsn(ALOAD, 2);
			createInsn(mw, opcode, null);
			prepareStackAccess(mw, new InsnNode(opcode));
			mw
					.visitMethodInsn(
							INVOKEVIRTUAL,
							"incubator/jvmsimu/JVMThread",
							"process",
							"(Lorg/objectweb/asm/tree/AbstractInsnNode;[Ljava/lang/Object;)[Ljava/lang/Object;");
			decodeStackAccess(mw, new InsnNode(opcode));
			// mw.visitInsn(opcode);

		}
	}

	public void visitIntInsn(int opcode, int operand) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitJumpInsn(int opcode, Label label) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitLabel(Label label) {
		// if (mw != null) {
		// fillDebugVars(mw, ln.getCurLine(), label);
		// }

	}

	public void visitLdcInsn(Object cst) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitLineNumber(int line, Label start) {
		// if (mw != null) {
		// String labelName = labelNames.get(start);
		// if (labelName == null) {
		// labelName = "L" + labelNames.size();
		// labelNames.put(start, labelName);
		// }
		// fillDebugVars(mw, ln.getCurLine(), "LINENUMBER " + line + " "
		// + labelName);
		// }

	}

	public void visitLocalVariable(String name, String desc, String signature,
			Label start, Label end, int index) {
		writeAdditionalVars();
		mw.visitLocalVariable(name, desc, signature, firstLabel, lastLabel,
				calcVarNum(index));
	}

	private int calcVarNum(int index) {
		return index == 0 && nonStatic ? 0 : index + 3;
	}

	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitMaxs(int maxStack, int maxLocals) {
		int calcVarNum = calcVarNum(maxLocals);
		if (mw != null) {
			mw.visitMaxs(maxStack + 10, calcVarNum);
			writeAdditionalVars();
		}

	}

	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitMultiANewArrayInsn(String desc, int dims) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public AnnotationVisitor visitParameterAnnotation(int parameter,
			String desc, boolean visible) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");
		return null;
	}

	public void visitTableSwitchInsn(int min, int max, Label dflt,
			Label[] labels) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitTryCatchBlock(Label start, Label end, Label handler,
			String type) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitTypeInsn(int opcode, String type) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitVarInsn(int opcode, int var) {
		if (mw != null) {
			lastLabel = fillDebugVars(mw, ln.getCurLine(), opcode);
			mw.visitVarInsn(ALOAD, 2);
			createInsn(mw, opcode, new Object[] { calcVarNum(var) });
			prepareStackAccess(mw, new VarInsnNode(opcode, calcVarNum(var)));
			mw
					.visitMethodInsn(
							INVOKEVIRTUAL,
							"incubator/jvmsimu/JVMThread",
							"process",
							"(Lorg/objectweb/asm/tree/AbstractInsnNode;[Ljava/lang/Object;)[Ljava/lang/Object;");
			decodeStackAccess(mw, new VarInsnNode(opcode, calcVarNum(var)));
			// mw.visitInsn(opcode);

		}

	}

	public static void prepareDebug(MethodVisitor mv) {
		prepareObject(mv, "incubator/jvmsimu/JVMThread");
		newObject(mv, "incubator/jvmsimu/JVM", "()V");
		newObject(mv, "incubator/jvmsimu/JVMStack", "()V");
		finishObject(mv, "incubator/jvmsimu/JVMThread",
				"(Lincubator/jvmsimu/JVM;Lincubator/jvmsimu/JVMStack;)V");
		mv.visitVarInsn(ASTORE, 2);
	}

	public static void newObject(MethodVisitor mv, String className,
			String constrDesc) {
		prepareObject(mv, className);
		finishObject(mv, className, constrDesc);
	}

	public static void finishObject(MethodVisitor mv, String className,
			String constrDesc) {
		mv.visitMethodInsn(INVOKESPECIAL, className, "<init>", constrDesc);
	}

	public static void prepareObject(MethodVisitor mv, String className) {
		mv.visitTypeInsn(NEW, className);
		mv.visitInsn(DUP);
	}

	public static Label fillDebugVars(MethodVisitor mv, int lineNumber,
			int nextOpcode) {
		mv.visitLdcInsn(lineNumber);
		mv.visitVarInsn(ISTORE, 0);
		mv.visitLdcInsn(AbstractVisitor.OPCODES[nextOpcode]);
		if (TRACE_INSN) {
			mv.visitInsn(DUP);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err",
					"Ljava/io/PrintStream;");
			mv.visitInsn(SWAP);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
					"(Ljava/lang/Object;)V");
		}
		mv.visitVarInsn(ASTORE, 1);
		Label label = new Label();
		mv.visitLabel(label);
		mv.visitLineNumber(lineNumber, label);
		return label;
	}

	public void fillDebugVars(MethodVisitor mv, int lineNumber,
			Label originalLabel) {
		String labelName = labelNames.get(originalLabel);
		if (labelName == null) {
			labelName = "L" + labelNames.size();
			labelNames.put(originalLabel, labelName);
		}

		mv.visitLdcInsn(lineNumber);
		mv.visitVarInsn(ISTORE, 0);
		mv.visitLdcInsn(labelName);
		if (TRACE_INSN) {
			mv.visitInsn(DUP);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err",
					"Ljava/io/PrintStream;");
			mv.visitInsn(SWAP);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
					"(Ljava/lang/Object;)V");
		}
		mv.visitVarInsn(ASTORE, 1);
		Label label = newLabel();
		mv.visitLabel(label);
		mv.visitLineNumber(lineNumber, label);

	}

	private void fillDebugVars(MethodVisitor mv, int lineNumber, String content) {

		mv.visitLdcInsn(lineNumber);
		mv.visitVarInsn(ISTORE, 0);
		mv.visitLdcInsn(content);
		if (TRACE_INSN) {
			mv.visitInsn(DUP);
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "err",
					"Ljava/io/PrintStream;");
			mv.visitInsn(SWAP);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println",
					"(Ljava/lang/Object;)V");
		}
		mv.visitVarInsn(ASTORE, 1);
		Label label = newLabel();
		mv.visitLabel(label);
		mv.visitLineNumber(lineNumber, label);
	}

	public static void prepareDebugVars(MethodVisitor mv, Label startLabel,
			Label endLabel) {
		System.out.println("prepareDebugVars");
		mv.visitLocalVariable("lineNumber", "I", null, startLabel, endLabel, 0);
		mv.visitLocalVariable("$_content_$", "Ljava/lang/String;", null,
				startLabel, endLabel, 1);
		mv.visitLocalVariable("$_context_$", "Lincubator/jvmsimu/JVMThread;",
				null, startLabel, endLabel, 2);
	}

	private void writeAdditionalVars() {
		if (!additionalVarsDefined) {
			additionalVarsDefined = true;
			mw.visitLabel(lastLabel);
			prepareDebugVars(mw, firstLabel, lastLabel);
		}
	}

	private void prepareStackAccess(MethodVisitor mv, AbstractInsnNode node) {
		Prog prog = ProgDefs.get(node.getOpcode());
		prog.prepareStackAccess(mv, node);
	}

	private void decodeStackAccess(MethodVisitor mv, AbstractInsnNode node) {
		Prog prog = ProgDefs.get(node.getOpcode());
		prog.decodeStackAccess(mv, node);
	}

	public static void createInsn(MethodVisitor mv, int opcode, Object[] params) {
		String[] insnMethodSelection = getInsnMethod(opcode);
		DebugInsertionMethodVisitor.prepareObject(mv, insnMethodSelection[0]);
		mv.visitLdcInsn(opcode);
		if (params != null) {
			for (Object param : params) {
				mv.visitLdcInsn(param);
			}
		}
		DebugInsertionMethodVisitor.finishObject(mv, insnMethodSelection[0],
				insnMethodSelection[1]);
	}

	public static String[] getInsnMethod(int opcode) {
		Prog prog = ProgDefs.get(opcode);
		return new String[] { prog.getInsnClassName(), prog.getInsnSignatur() };
	}

}
