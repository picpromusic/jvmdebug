package incubator.jvmsimu.instructions;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import incubator.jvmsimu.InvalidStackException;
import incubator.jvmsimu.JVM;
import incubator.jvmsimu.JVMStack;
import incubator.jvmsimu.JVMThread;
import incubator.jvmsimu.JVMThread.Prog;

public class ISTOREProg extends AbstractVarInsnProg {

	public void prepareStackAccess(MethodVisitor mv, AbstractInsnNode node) {
		mv.visitInsn(ACONST_NULL);
	}
	
	public void decodeStackAccess(MethodVisitor mv, AbstractInsnNode node) {
		VarInsnNode vNode = (VarInsnNode) node;
		mv.visitInsn(ICONST_0);
		mv.visitInsn(AALOAD);
		mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue",
				"()I");
		mv.visitVarInsn(vNode.getOpcode(),vNode.var);
	}

	public Object[] process(AbstractInsnNode insnNode, JVM vm, JVMStack stack,Object[] realStack) {
		int stackSize = stack.getSize();
		if (stackSize < 1 || !stack.getType(stackSize-1).equals(int.class)) {
			throw new InvalidStackException(
					"There should be a int as topmost stack-element");
		}
		return new Object[]{stack.pop(int.class)};
	}

}
