package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.ICONST_M1;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.ICONST_5;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.tree.InsnNode;

public class IConstTest {

	@Test
	public void One() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(ICONST_1),null);
		Assert.assertNull(erg);

		Assert.assertEquals(1, stack.getSize());
		Assert.assertEquals(1, stack.pop());
	}

	@Test
	public void Minus1() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(ICONST_M1),null);
		Assert.assertNull(erg);

		Assert.assertEquals(1, stack.getSize());
		Assert.assertEquals(-1, stack.pop());
	}

	@Test
	public void Zero() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(ICONST_0),null);
		Assert.assertNull(erg);

		Assert.assertEquals(1, stack.getSize());
		Assert.assertEquals(0, stack.pop());
	}

	@Test
	public void Five() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(ICONST_5),null);
		Assert.assertNull(erg);

		Assert.assertEquals(1, stack.getSize());
		Assert.assertEquals(5, stack.pop());
	}

}
