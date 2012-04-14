package incubator.jvmsimu;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JVMStackTest {

	private JVMStack stack;

	@Before
	public void setup() {
		stack = new JVMStack();
	}

	@Test
	public void pushIncreasesStackCount() throws Exception {
		Assert.assertEquals(0, stack.getSize());
		stack.push("Huch");
		Assert.assertEquals(1, stack.getSize());

	}

	@Test
	public void pushedElementsCanBePoped() throws Exception {
		Assert.assertEquals(0, stack.getSize());
		stack.push("Huch");
		Assert.assertEquals(1, stack.getSize());
		String s = stack.pop(String.class);
		Assert.assertEquals("Huch", s);
	}

	@Test
	public void pushedElementsCanReceived() throws Exception {
		Assert.assertEquals(0, stack.getSize());
		stack.push("Huch");
		Assert.assertEquals(1, stack.getSize());
		String s = stack.getElement(0,String.class);
		Assert.assertEquals("Huch", s);
	}

	@Test
	public void pushedElementsCanAskForType() throws Exception {
		Assert.assertEquals(0, stack.getSize());
		stack.push("Huch");
		Assert.assertEquals(1, stack.getSize());
		Class c = stack.getType(0);
		Assert.assertEquals(String.class, c);
	}
}
