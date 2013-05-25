package incubator.jvmsimu;

import java.util.Iterator;
import java.util.Stack;

public class JVMStack {

	private int size = 0;
	private Stack<Object> stack = new Stack<Object>();
	private Stack<Class> type = new Stack<Class>();

	public void push(long value) {
		type.push(long.class);
		stack.push(value);
	}

	public void push(int value) {
		type.push(int.class);
		stack.push(value);
	}

	public void push(byte value) {
		type.push(byte.class);
		stack.push(value);
	}

	public void push(Object object) {
		type.push(object.getClass());
		stack.push(object);
	}

	public int getSize() {
		return stack.size();
	}

	public Class getType(int i) {
		return type.get(i);
	}

	public <T> T getElement(int i, Class<T> class1) {
		return (T) stack.get(i);
	}

	public <T> T pop(Class<T>  requestedType) {
		if (requestedType.isAssignableFrom(getLastType())) {
			type.pop();
			return (T) stack.pop();
		}
		throw new RuntimeException("Types are not compatible. Stack: "
				+ getLastType() + " Requested :"+ requestedType);
	}

	public Class getLastType() {
		return getType(getSize()-1);
	}

	public void push(Object element, Class type) {
		this.stack.push(element);
		this.type.push(type);
	}

	public Object pop() {
		this.type.pop();
		return stack.pop();
	}

	public String toString() {
		if (this.stack.size() != 0) {
			
			Iterator<Object> stackIt = this.stack.iterator();
			Iterator<Class> typeIt = this.type.iterator();
			StringBuilder ret = new StringBuilder();
			while (stackIt.hasNext() && typeIt.hasNext()) {
				ret.append(stackIt.next());
				ret.append("\t(");
				ret.append(typeIt.next());
				ret.append(")\n");
			}
			return ret.toString();
		}else {
			return "empty stack";
		}
	}
}
