package incubator.jvmsimu.instructions;

import incubator.jvmsimu.InvalidStackException;
import incubator.jvmsimu.JVM;
import incubator.jvmsimu.JVMStack;

import org.objectweb.asm.tree.AbstractInsnNode;

public class IADDProg extends AbstractInsnProg {

	public Object[] process(AbstractInsnNode insnNode, JVM vm, JVMStack stack,
			Object[] realStack) {
		int pos = stack.getSize()-2;
		if (pos < 0) {
			throw new InvalidStackException("IADD needs at least two int stack element");
			
		}
		Class type = stack.getType(pos);
		Class type2 = stack.getType(pos+1);
		if (type == long.class || type == double.class || type2 == long.class || type2 == double.class) {
			throw new InvalidStackException("IADD invalidates stack on double-stack-element");
		}
		int pop = stack.pop(int.class);
		int pop2 = stack.pop(int.class);
		stack.push(pop+pop2,int.class);
		return null;
	}


}
