package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.ISTORE;
import static org.objectweb.asm.Opcodes.ICONST_1;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class ISTORETest {

	@Test
	public void storeSingleElement() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(18);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new VarInsnNode(ISTORE,0),null);
		Assert.assertNotNull(erg);
		Assert.assertEquals(18, (Integer)erg[0]);
		
		Assert.assertEquals(0, stack.getSize());
	}

	@Test
	public void storeDoubleElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push((long) 3);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(ISTORE),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}

	}

	@Test
	public void storeNoElementFails() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(ISTORE),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {
			// Everything is fine
		}
	}
}
