package incubator.jvmsimu.instructions;

import static org.objectweb.asm.Opcodes.AALOAD;
import static org.objectweb.asm.Opcodes.CHECKCAST;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.AbstractInsnNode;

import incubator.jvmsimu.InvalidStackException;
import incubator.jvmsimu.JVM;
import incubator.jvmsimu.JVMStack;
import incubator.jvmsimu.JVMThread.Prog;

public class IRETURNProg extends AbstractInsnProg  {

	public Object[] process(AbstractInsnNode insnNode, JVM vm, JVMStack stack,Object[] realStack) {
		if (stack.getSize() < 1 || !stack.getLastType().equals(int.class)) {
			throw new InvalidStackException(
					"There should be a int as topmost stack-element");
		}
		Object[] ret = new Object[1];
		ret[0] = stack.pop();
		return ret;
	}

	@Override
	public void decodeStackAccess(MethodVisitor mv,AbstractInsnNode node) {
		mv.visitInsn(ICONST_0);
		mv.visitInsn(AALOAD);
		mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
		mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue",
				"()I");
		mv.visitInsn(node.getOpcode());
	}

}
