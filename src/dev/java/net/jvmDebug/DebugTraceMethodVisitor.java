package dev.java.net.jvmDebug;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.signature.SignatureReader;
import org.objectweb.asm.util.TraceSignatureVisitor;

public class DebugTraceMethodVisitor extends DebugTraceAbstractVisitor implements
		MethodVisitor {

	public MethodVisitor mv;
	/**
	 * The label names. This map associate String values to Label keys.
	 */
	protected final Map labelNames;

	/**
	 * Tab for labels.
	 */
	protected String ltab = "   ";

	/**
	 * Tab for bytecode instructions.
	 */
	protected String tab2 = "    ";

	public DebugTraceMethodVisitor(MethodVisitor mv) {
		this.labelNames = new HashMap();
		this.mv = mv;
	}

	public AnnotationVisitor visitAnnotationDefault() {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");
		return null;
	}

	public void visitCode() {
		if (mv != null) {
			mv.visitCode();
		}
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
        appendBuf(tab2).appendBuf(OPCODES[opcode]).appendBuf('\n');
        addText();

        if (mv != null) {
            mv.visitInsn(opcode);
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
		appendBuf(ltab);
		appendLabel(label);
		appendBuf('\n');
		addText();

		if (mv != null) {
			mv.visitLabel(label);
		}
	}

	public void visitLdcInsn(Object cst) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitLineNumber(int line, Label start) {
		appendBuf(tab2).appendBuf("LINENUMBER ").appendBuf(line).appendBuf(' ');
		appendLabel(start);
		appendBuf('\n');
		addText();

		if (mv != null) {
			mv.visitLineNumber(line, start);
		}
	}

	public void visitLocalVariable(String name, String desc, String signature,
			Label start, Label end, int index) {
        appendBuf(tab2).appendBuf("LOCALVARIABLE ").appendBuf(name).appendBuf(' ');
        appendDescriptor(FIELD_DESCRIPTOR, desc);
        appendBuf(' ');
        appendLabel(start);
        appendBuf(' ');
        appendLabel(end);
        appendBuf(' ').appendBuf(index).appendBuf('\n');

        if (signature != null) {
            appendBuf(tab2);
            appendDescriptor(FIELD_SIGNATURE, signature);

            DebugTraceSignatureVisitor sv = new DebugTraceSignatureVisitor(0);
            SignatureReader r = new SignatureReader(signature);
            r.acceptType(sv);
            appendBuf(tab2)
                    .appendBuf("// declaration: ")
                    .appendBuf(sv.getDeclaration())
                    .appendBuf('\n');
        }
        addText();

        if (mv != null) {
            mv.visitLocalVariable(name, desc, signature, start, end, index);
        }

	}

	public void visitLookupSwitchInsn(Label dflt, int[] keys, Label[] labels) {
		// TODO Auto-generated method stub
		if (1 == 1)
			throw new RuntimeException("NYI");

	}

	public void visitMaxs(int maxStack, int maxLocals) {
        appendBuf(tab2).appendBuf("MAXSTACK = ").appendBuf(maxStack).appendBuf('\n');
        appendBuf(tab2).appendBuf("MAXLOCALS = ").appendBuf(maxLocals).appendBuf('\n');
        addText();

        if (mv != null) {
            mv.visitMaxs(maxStack, maxLocals);
        }

	}

	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {
        appendBuf(tab2).appendBuf(OPCODES[opcode]).appendBuf(' ');
        appendDescriptor(INTERNAL_NAME, owner);
        appendBuf('.').appendBuf(name).appendBuf(' ');
        appendDescriptor(METHOD_DESCRIPTOR, desc);
        appendBuf('\n');
        addText();

        if (mv != null) {
            mv.visitMethodInsn(opcode, owner, name, desc);
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
        appendBuf(tab2).appendBuf(OPCODES[opcode]).appendBuf(' ');
        appendDescriptor(INTERNAL_NAME, type);
        appendBuf('\n');
        addText();

        if (mv != null) {
            mv.visitTypeInsn(opcode, type);
        }
	}

	public void visitVarInsn(int opcode, int var) {
		appendBuf(tab2).appendBuf(OPCODES[opcode]).appendBuf(' ')
				.appendBuf(var).appendBuf('\n');
		addText();

		if (mv != null) {
			mv.visitVarInsn(opcode, var);
		}
	}

	/**
	 * Appends the name of the given label to {@link #buf buf}. Creates a new
	 * label name if the given label does not yet have one.
	 * 
	 * @param l
	 *            a label.
	 */
	protected void appendLabel(final Label l) {
		String name = (String) labelNames.get(l);
		if (name == null) {
			name = "L" + labelNames.size();
			labelNames.put(l, name);
		}
		appendBuf(name);
	}

}
