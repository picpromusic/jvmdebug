package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.IADD;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class IAddTest {

	@Test
	public void addTwoElements() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(18);
		stack.push(42);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(IADD),null);
		Assert.assertNull(erg);
		Assert.assertEquals(1, stack.getSize());
		Assert.assertEquals(60, stack.getElement(0, int.class));
	}

	@Test
	public void addTwoDoubleElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push((long) 3);
		stack.push((long) 5);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(IADD),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}

	}

	@Test
	public void addOneElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(18);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(IADD),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}
	}
}
