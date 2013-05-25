package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.DUP2;
import static org.objectweb.asm.Opcodes.ICONST_M1;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.ICONST_2;
import static org.objectweb.asm.Opcodes.ICONST_3;
import static org.objectweb.asm.Opcodes.ICONST_4;
import static org.objectweb.asm.Opcodes.ICONST_5;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.POP;
import static org.objectweb.asm.Opcodes.POP2;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.AbstractInsnNode;

public class JVMThread {

	public interface Prog {

		public Object[] process(AbstractInsnNode insnNode, JVM vm,
				JVMStack stack, Object[] realStack);

		public String getInsnClassName();

		public String getInsnSignatur();

		public void prepareStackAccess(MethodVisitor mv,
				AbstractInsnNode instructionNode);

		public void decodeStackAccess(MethodVisitor mv,
				AbstractInsnNode node);

	}

	private JVMStack stack;
	private JVM vm;

	public JVMThread(JVM vm, JVMStack stack) {
		this.vm = vm;
		this.stack = stack;
	}

	public Object[] process(AbstractInsnNode insnNode, Object[] realStack) {
		Prog prog = ProgDefs.get(insnNode.getOpcode());
		return prog.process(insnNode, vm, stack, realStack);
	}

	public JVMStack getStack() {
		return stack;
	}

	@Override
	public String toString() {
		return this.stack.toString();
	}
}
