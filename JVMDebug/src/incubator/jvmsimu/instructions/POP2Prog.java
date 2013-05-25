package incubator.jvmsimu.instructions;

import org.objectweb.asm.tree.AbstractInsnNode;

import incubator.jvmsimu.InvalidStackException;
import incubator.jvmsimu.JVM;
import incubator.jvmsimu.JVMStack;
import incubator.jvmsimu.JVMThread.Prog;

public class POP2Prog extends AbstractInsnProg  {

	public Object[] process(AbstractInsnNode insnNode, JVM vm, JVMStack stack,Object[] realStack) {
		int pos = stack.getSize() - 1;
		if (pos < 0) {
			throw new InvalidStackException(
					"POP2 needs at least one stack-element");

		}
		Class type = stack.getType(pos);
		if (type == long.class || type == double.class) {
			stack.pop();
		} else if (pos < 1) {
			throw new InvalidStackException(
					"POP2 invalidates stack on one single-stack-element");
		} else {
			Class type2 = stack.getType(pos - 1);
			if (type2 == long.class || type2 == double.class) {
				throw new InvalidStackException(
						"POP invalidates stack on combination single-stack-element, double-stack-element");
			} else {
				stack.pop();
				stack.pop();
			}
		}
		return null;
	}

}
