package dev.java.net.jvmDebug;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.signature.SignatureVisitor;

public class DebugTraceSignatureVisitor implements SignatureVisitor{

    private final StringBuffer declaration;

    private boolean isInterface;

	public DebugTraceSignatureVisitor(int access) {
        isInterface = (access & Opcodes.ACC_INTERFACE) != 0;
        this.declaration = new StringBuffer();
	}

	public SignatureVisitor visitArrayType() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public void visitBaseType(char descriptor) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public SignatureVisitor visitClassBound() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public void visitClassType(String name) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public void visitEnd() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public SignatureVisitor visitExceptionType() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public void visitFormalTypeParameter(String name) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public void visitInnerClassType(String name) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public SignatureVisitor visitInterface() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public SignatureVisitor visitInterfaceBound() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public SignatureVisitor visitParameterType() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public SignatureVisitor visitReturnType() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public SignatureVisitor visitSuperclass() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public void visitTypeArgument() {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public SignatureVisitor visitTypeArgument(char wildcard) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		return null;
	}

	public void visitTypeVariable(String name) {
		// TODO Auto-generated method stub
		if (1==1) throw new RuntimeException("NYI");
		
	}

	public String getDeclaration() {
        return declaration.toString();
    }

}
