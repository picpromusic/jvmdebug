import incubator.jvmsimu.JVM;
import incubator.jvmsimu.JVMStack;
import incubator.jvmsimu.JVMThread;

import static org.objectweb.asm.Opcodes.*;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;

public class Simple {

	public static int get() {
		int temp = 1;
		for (int i = 0; 5 >= i; i++){
			temp = temp + temp;
		}
//		return temp;
   	    return get(temp);
	}

	private static int get(int temp) {
		return temp+(-1);
	}

//	private static int test(int temp) {
//		return Integer.valueOf(temp).intValue();
//	}
//	public static int get() {
//		int temp = 5;
//		int temp2 = 1;
//		int temp3 = 2;
//		int temp4 = 3;
//		int temp5 = 5;
//		int temp6 = 1;
//		int temp7 = -1;
//		if (temp + temp2 < 5) {
//			return 1;
//		} else {
//			return -1;
//		}
//	}

//	public static int get() {
//		int temp = 5;
//		int temp2 = -1;
//		int temp3 = 2;
//		int temp4 = 3;
//		int temp5 = 5;
//		int temp6 = 1;
//		int temp7 = -1;
//		if (temp + temp2 < 5) {
//			return 1;
//		} else {
//			return -1;
//		}
//	}
	
//	public static int get() {
//		int temp = 4;
//		return temp;
//	}

//	public static int get() {
//		 return 5;
//	}

//	 public static int test() {
//		 LabelNode ln = new LabelNode();
//		 JumpInsnNode ji = new JumpInsnNode(IF_ICMPGE,ln);
//		 System.out.println(TestEnum.A.getName());
//	 int linenumber = 0;
//	 String content[] = new String[1];
//	 JVMThread jvmThread = new JVMThread(new JVM(), new JVMStack());
//	 Object[] r = new Object[1];
//	 r[0] = linenumber;
//	 // Object[] ret = jvmThread.process(new InsnNode(ICONST_2));
//	 // int i = (Integer) ret[0];
//	 // System.out.println(i);
//			
//	 // int i = jvmThread.getStack().pop(int.class);
//	 return 2;
//	 }

}
