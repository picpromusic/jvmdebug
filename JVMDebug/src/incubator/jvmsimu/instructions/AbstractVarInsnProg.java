package incubator.jvmsimu.instructions;

import incubator.jvmsimu.JVMThread.Prog;

public abstract class AbstractVarInsnProg implements Prog{
	
	public String getInsnClassName() {
		return "org/objectweb/asm/tree/VarInsnNode";
	}

	public String getInsnSignatur() {
		return "(II)V";
	}

}
