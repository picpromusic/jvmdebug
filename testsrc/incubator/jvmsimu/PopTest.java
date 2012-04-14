package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.POP;
import static org.objectweb.asm.Opcodes.ICONST_1;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.tree.InsnNode;

public class PopTest {

	@Test
	public void popSingleElement() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push("1");

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(POP),null);
		Assert.assertNull(erg);

		Assert.assertEquals(0, stack.getSize());
	}

	@Test
	public void popSingleIntElement() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(1);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(POP),null);
		Assert.assertNull(erg);

		Assert.assertEquals(0, stack.getSize());
	}

	@Test
	public void popDoubleElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push((long) 3);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(POP),null);
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
			Object[] erg = jvmThread.process(new InsnNode(POP),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}

	}
}
