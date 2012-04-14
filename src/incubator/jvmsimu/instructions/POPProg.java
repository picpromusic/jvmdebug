package incubator.jvmsimu.instructions;

import org.objectweb.asm.tree.AbstractInsnNode;

import incubator.jvmsimu.InvalidStackException;
import incubator.jvmsimu.JVM;
import incubator.jvmsimu.JVMStack;
import incubator.jvmsimu.JVMThread.Prog;

public class POPProg extends AbstractInsnProg  {

	public Object[] process(AbstractInsnNode insnNode, JVM vm, JVMStack stack,Object[] realStack) {
		int pos = stack.getSize();
		if (pos == 0) {
			throw new InvalidStackException("POP invalidates stack on no-element");
		}
		Class type = stack.getType(pos-1);
		if (type == long.class | type == double.class) {
			throw new InvalidStackException("POP invalidates stack on double-stack-element");
		}
		stack.pop();
		return null;
	}

}
