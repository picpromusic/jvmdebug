package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.DUP2;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.tree.InsnNode;

public class Dup2Test {

	@Test
	public void dupDoubleElement() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push((long) 7);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(DUP2),null);
		Assert.assertNull(erg);

		Assert.assertEquals(2, stack.getSize());
		Assert.assertEquals(long.class, stack.getType(0));
		Assert.assertEquals(long.class, stack.getType(1));
		Assert.assertEquals((long) 7, stack.getElement(0, Object.class));
		Assert.assertEquals(stack.getElement(0, long.class), stack.getElement(
				1, long.class));
		Assert.assertSame(stack.getElement(0, long.class), stack.getElement(1,
				long.class));
	}

	@Test
	public void dupTwoSingleElements() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(7);
		stack.push(8);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(DUP2),null);
		Assert.assertNull(erg);

		Assert.assertEquals(4, stack.getSize());
		Assert.assertEquals(int.class, stack.getType(0));
		Assert.assertEquals(int.class, stack.getType(1));
		Assert.assertEquals(int.class, stack.getType(2));
		Assert.assertEquals(int.class, stack.getType(3));
		Assert.assertEquals(7, stack.getElement(0, int.class));
		Assert.assertEquals(8, stack.getElement(1, int.class));
		Assert.assertEquals(stack.getElement(0, int.class), stack.getElement(2,
				int.class));
		Assert.assertSame(stack.getElement(1, int.class), stack.getElement(3,
				int.class));
	}

	@Test
	public void dupSingleElementsDoubleElementShouldFail() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push((long)7);
		stack.push(8);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(DUP2),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}
	}

	@Test
	public void dupSingleElementShouldFail() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(8);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(DUP2),null);
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
			Object[] erg = jvmThread.process(new InsnNode(DUP2),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}
	}

}
