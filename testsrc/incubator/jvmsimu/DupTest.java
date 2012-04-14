package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.DUP2;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.tree.InsnNode;

public class DupTest {

	@Test
	public void dupSingleElement() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push("1");

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(DUP),null);
		Assert.assertNull(erg);

		Assert.assertEquals(2, stack.getSize());
		Assert.assertEquals(String.class, stack.getType(0));
		Assert.assertEquals(String.class, stack.getType(1));
		Assert.assertEquals("1", stack.getElement(0, Object.class));
		Assert.assertEquals(stack.getElement(0, Object.class), stack
				.getElement(1, Object.class));
		Assert.assertSame(stack.getElement(0, Object.class), stack.getElement(
				1, Object.class));
	}

	@Test
	public void dupSingleIntElement() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(1);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(DUP),null);
		Assert.assertNull(erg);

		Assert.assertEquals(2, stack.getSize());
		Assert.assertEquals(int.class, stack.getType(0));
		Assert.assertEquals(int.class, stack.getType(1));
		Assert.assertEquals(1, stack.getElement(0, int.class));
		Assert.assertEquals(stack.getElement(0, int.class), stack.getElement(1,
				int.class));
	}

	@Test
	public void dupSingleByteElement() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push((byte) 1);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(DUP),null);
		Assert.assertNull(erg);

		Assert.assertEquals(2, stack.getSize());
		Assert.assertEquals(byte.class, stack.getType(0));
		Assert.assertEquals(byte.class, stack.getType(1));
		Assert.assertEquals(1, stack.getElement(0, byte.class));
		Assert.assertEquals(stack.getElement(0, byte.class), stack.getElement(
				1, byte.class));
	}

	@Test
	public void dupDoubleElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push((long) 3);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(DUP),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}

	}

	@Test
	public void dupNoElementShouldFail() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(DUP),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}
	}
}
