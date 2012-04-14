package incubator.jvmsimu.instructions;

import static org.objectweb.asm.Opcodes.*;
import incubator.jvmsimu.InvalidStackException;
import incubator.jvmsimu.JVM;
import incubator.jvmsimu.JVMStack;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ILOADProg extends AbstractVarInsnProg {

	public void prepareStackAccess(MethodVisitor mv, AbstractInsnNode node) {
		VarInsnNode vNode = (VarInsnNode) node;
		mv.visitInsn(ICONST_1);
		mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
		mv.visitInsn(DUP);
		mv.visitInsn(ICONST_0);
		mv.visitVarInsn(vNode.getOpcode(), vNode.var);
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;");
		mv.visitInsn(AASTORE);
	}
	
	public void decodeStackAccess(MethodVisitor mv, AbstractInsnNode node) {
		mv.visitInsn(POP);
	}

	public Object[] process(AbstractInsnNode insnNode, JVM vm, JVMStack stack,
			Object[] realStack) {
		int stackSize = realStack.length;
		
		if (stackSize >= 1) {
			if (realStack[stackSize-1] instanceof Integer) {
				stack.push(realStack[stackSize-1],int.class);
				return null;
			}
		}
		throw new InvalidStackException(
				"There should be a int as topmost stack-element");
	}

}
