package incubator.jvmsimu;

import static org.objectweb.asm.Opcodes.*;
import incubator.jvmsimu.JVMThread.Prog;
import incubator.jvmsimu.instructions.DUP2Prog;
import incubator.jvmsimu.instructions.DUPProg;
import incubator.jvmsimu.instructions.IADDProg;
import incubator.jvmsimu.instructions.ICONSTProg;
import incubator.jvmsimu.instructions.ILOADProg;
import incubator.jvmsimu.instructions.IRETURNProg;
import incubator.jvmsimu.instructions.ISTOREProg;
import incubator.jvmsimu.instructions.POP2Prog;
import incubator.jvmsimu.instructions.POPProg;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.AbstractVisitor;

public class ProgDefs {

	private Map<Integer, Prog> map;

	private static ProgDefs singleton;
	
	
	private ProgDefs() {
		this.map = new HashMap<Integer, Prog>();
		this.map.put(DUP, new DUPProg());
		this.map.put(DUP2, new DUP2Prog());
		this.map.put(POP, new POPProg());
		this.map.put(POP2, new POP2Prog());
		ICONSTProg iconst = new ICONSTProg();
		this.map.put(ICONST_M1, iconst);
		this.map.put(ICONST_0, iconst);
		this.map.put(ICONST_1, iconst);
		this.map.put(ICONST_2, iconst);
		this.map.put(ICONST_3, iconst);
		this.map.put(ICONST_4, iconst);
		this.map.put(ICONST_5, iconst);
		this.map.put(IRETURN, new IRETURNProg());
		this.map.put(ISTORE, new ISTOREProg());
		this.map.put(ILOAD, new ILOADProg());
		this.map.put(IADD, new IADDProg());
	}

	public static Prog get(int opcode) {
		Prog prog = get().map.get(opcode);
		if (prog == null) {
			throw new RuntimeException("No Prog defined for:"+AbstractVisitor.OPCODES[opcode]);
		}
		return prog;
	}
	
	public static ProgDefs get() {
		if (singleton == null) {
			singleton = new ProgDefs();
		}
		return singleton;
	}
}
