package incubator.jvmsimu.instructions;

import org.objectweb.asm.tree.AbstractInsnNode;

import incubator.jvmsimu.InvalidStackException;
import incubator.jvmsimu.JVM;
import incubator.jvmsimu.JVMStack;
import incubator.jvmsimu.JVMThread.Prog;

public class DUPProg extends AbstractInsnProg  {

	public Object[] process(AbstractInsnNode insnNode, JVM vm, JVMStack stack,Object[] realStack) {
		int pos = stack.getSize()-1;
		if (pos < 0) {
			throw new InvalidStackException("DUP needs at least one stack element");
			
		}
		Class type = stack.getType(pos);
		if (type == long.class || type == double.class) {
			throw new InvalidStackException("DUP invalidates stack on double-stack-element");
		}
		Object pop = stack.pop();
		stack.push(pop,type);
		stack.push(pop,type);
		return null;
	}

}
