package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.ILOAD;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ILOADTest {

	@Test
	public void loadSingleElement() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new VarInsnNode(ILOAD, 0),
				new Object[] { 2 });
		Assert.assertNull(erg);

		Assert.assertEquals(1, stack.getSize());
		Assert.assertEquals(int.class, stack.getLastType());
		Assert.assertEquals(2, stack.pop());
	}

	@Test
	public void loadDoubleElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new VarInsnNode(ILOAD, 0),
					new Object[] { 2.0d });
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}

	}

	@Test
	public void loadNoElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new VarInsnNode(ILOAD,0),new Object[0]);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}
	}
}
