package dev.java.net.jvmDebug;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

public class CopyMethodVisitor implements MethodVisitor {

	private MethodVisitor mw;

	public CopyMethodVisitor(MethodVisitor methodWriter) {
		this.mw = methodWriter;
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
		if (mw != null) {
			mw.visitCode();
		}
	}

	public void visitEnd() {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

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
			mw.visitInsn(opcode);
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
		if (mw != null) {
			mw.visitLabel(label);
		}
	}

	public void visitLdcInsn(Object cst) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitLineNumber(int line, Label start) {
		if (mw != null) {
			mw.visitLineNumber(line, start);
		}

	}

	public void visitLocalVariable(String name, String desc, String signature,
			Label start, Label end, int index) {
		if (mw != null) {
			mw.visitLocalVariable(name, desc, signature, start, end, index);
		}
	}

	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitMaxs(int maxStack, int maxLocals) {
		if (mw != null) {
			mw.visitMaxs(maxStack, maxLocals);
		}
	}

	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {
		if (mw != null) {
			mw.visitMethodInsn(opcode, owner, name, desc);
		}

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
		if (mw != null) {
			mw.visitTypeInsn(opcode, type);
		}

	}

	public void visitVarInsn(int opcode, int var) {
		if (mw != null) {
			mw.visitVarInsn(opcode, var);
		}
	}

}
