package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.POP2;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.tree.InsnNode;

public class Pop2Test {

	@Test
	public void popLongElement() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push((long)2);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(POP2),null);
		Assert.assertNull(erg);

		Assert.assertEquals(0, stack.getSize());
	}

	@Test
	public void popTwoSingleElements() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(2);
		stack.push(3);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(POP2),null);
		Assert.assertNull(erg);

		Assert.assertEquals(0, stack.getSize());
	}


	@Test
	public void popSingleDoubleElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push((long)2);
		stack.push(3);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(POP2),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}

	}

	@Test
	public void popSingleElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(3);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(POP2),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}

	}

	@Test
	public void popNoElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(POP2),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}
	}
}
