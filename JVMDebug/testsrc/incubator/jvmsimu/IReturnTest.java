package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.ICONST_0;
import static org.objectweb.asm.Opcodes.ICONST_1;
import static org.objectweb.asm.Opcodes.ICONST_5;

import org.junit.Assert;
import org.junit.Test;
import org.objectweb.asm.tree.InsnNode;

public class IReturnTest {

	@Test
	public void OneIntegerWorks() throws Exception {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(2);

		JVMThread jvmThread = new JVMThread(vm, stack);
		Object[] erg = jvmThread.process(new InsnNode(IRETURN),null);
		Assert.assertEquals(1, erg.length);
		Assert.assertEquals(2, erg[0]);

		Assert.assertEquals(0, stack.getSize());
	}


	
	
	@Test
	public void EmptyStackDoesNotWork() {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(IRETURN),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {

		}
	}

	@Test
	public void StringDoesNotWork() {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(new String());
		
		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(IRETURN),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {

		}
	}

	@Test
	public void OneDoubleDoesNotWork() {
		JVM vm = new JVM();
		JVMStack stack = new JVMStack();
		stack.push(2d);

		JVMThread jvmThread = new JVMThread(vm, stack);
		try {
			Object[] erg = jvmThread.process(new InsnNode(IRETURN),null);
			Assert.fail("Exception expected");
		} catch (InvalidStackException e) {

		}
	}

}
