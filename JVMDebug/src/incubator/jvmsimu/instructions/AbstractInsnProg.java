package incubator.jvmsimu.instructions;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;

import incubator.jvmsimu.JVMThread;
import incubator.jvmsimu.JVMThread.Prog;

public abstract class AbstractInsnProg implements Prog{

	public String getInsnClassName() {
		return "org/objectweb/asm/tree/InsnNode";
	}

	public String getInsnSignatur() {
		return "(I)V";
	}
	
	public void prepareStackAccess(MethodVisitor mv, AbstractInsnNode instructionNode) {
		mv.visitInsn(Opcodes.ACONST_NULL);
	}
	
	public void decodeStackAccess(MethodVisitor mv, AbstractInsnNode node) {
		mv.visitInsn(Opcodes.POP);
	}
}
