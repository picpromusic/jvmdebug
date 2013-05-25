package incubator.jvmsimu.instructions;

import incubator.jvmsimu.JVM;
import incubator.jvmsimu.JVMStack;
import incubator.jvmsimu.JVMThread.Prog;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;

public class ICONSTProg extends AbstractInsnProg  {

	public Object[] process(AbstractInsnNode insnNode, JVM vm, JVMStack stack,Object[] realStack) {
		int opcode = insnNode.getOpcode();
		int value = opcode - Opcodes.ICONST_0;
		if (value < -1 || value > 5)
			throw new IllegalArgumentException(insnNode + "(" + opcode
					+ ") is not a valid ICONST_x instruction");
		stack.push(value);
		return null;
	}

}
