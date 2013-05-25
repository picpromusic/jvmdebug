package incubator.jvmutil;

import static org.objectweb.asm.Opcodes.AALOAD;
import static org.objectweb.asm.Opcodes.AASTORE;
import static org.objectweb.asm.Opcodes.ANEWARRAY;
import static org.objectweb.asm.Opcodes.CHECKCAST;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.DUP2_X1;
import static org.objectweb.asm.Opcodes.DUP2_X2;
import static org.objectweb.asm.Opcodes.DUP_X2;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.POP;
import static org.objectweb.asm.Opcodes.POP2;
import static org.objectweb.asm.Opcodes.SWAP;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TypeInsnNode;

public class RescueOperations {

	public static void genAsmForRescueingParameterIntoObjectArray(MethodVisitor mv,
			Type[] parameterTypes) {
		// NOTP(evt 2x),OTP(evt 2x)
		if (parameterTypes.length > 0) {
			// 1: CONVERT_OBJECT --&gt; NOTP(evt 2x),OTP
			genAsmConvertToObject(mv, parameterTypes[parameterTypes.length - 1]);
			// 2: LDC ARRAYSIZE --&gt; NOTP(evt 2x),OTP,ARRAYSIZE
			mv.visitLdcInsn(parameterTypes.length);
			// // 3: ANEWARRAY --&gt; NOTP(evt 2x),OTP,ARRAY
			mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
			for (int i = parameterTypes.length - 1; i >= 0; i--) {
				// 4: LDC ARRAYPOS --> NOTP(evt 2x),OTP,ARRAY,ARRAYPOS
				mv.visitLdcInsn(i);
				// 5: DUP_X2 --> NOTP(evt 2x),ARRAYPOS,OTP,ARRAY,ARRAYPOS
				mv.visitInsn(DUP_X2);
				// 6: POP --> NOTP(evt 2x),ARRAYPOS,OTP,ARRAY
				mv.visitInsn(POP);
				// 7: DUP --> NOTP(evt 2x),ARRAYPOS,OTP,ARRAY,ARRAY
				mv.visitInsn(DUP);
				// 8: DUP2_X2 --> NOTP(evt
				// 2x),ARRAY,ARRAY,ARRAYPOS,OTP,ARRAY,ARRAY
				mv.visitInsn(DUP2_X2);
				// 9: POP2 --> NOTP(evt 2x),ARRAY,ARRAY,ARRAYPOS,OTP
				mv.visitInsn(POP2);
				// 10: AASTORE --> NOTP(evt 2x),ARRAY
				mv.visitInsn(AASTORE);
				// xx: IF 2x (NOT IN ASM CODE, IF IS USED BY CODE GENERATION)
				if (i >= 1) {
					if (parameterTypes[i - 1] == Type.LONG_TYPE
							|| parameterTypes[i - 1] == Type.DOUBLE_TYPE) {
						// 11: DUP_X2 --> ARRAY,NOTP(evt 2x),ARRAY
						mv.visitInsn(DUP_X2);
						// 12: POP --> ARRAY,NOTP(evt 2x)
						mv.visitInsn(POP);
						// XX: ELSE
					} else {
						// 13: SWAP --> ARRAY,NOTP
						mv.visitInsn(SWAP);
						// XX: END-IF
					}
					// 14: CONVERT_OBJECT --&gt; ARRAY,NOTP
					genAsmConvertToObject(mv, parameterTypes[i - 1]);
					// 15: SWAP --> NOTP,ARRAY
					mv.visitInsn(SWAP);
				}
				// NEXT OPERATION SEE LINE 4
			}
		}
	}

	private static void genAsmConvertToObject(MethodVisitor mv, Type type) {
		if (type.equals(Type.BOOLEAN_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Boolean", "valueOf",
					"(Z)Ljava/lang/Boolean;");
		} else if (type.equals(Type.BYTE_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Byte", "valueOf",
					"(B)Ljava/lang/Byte;");
		} else if (type.equals(Type.CHAR_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Character", "valueOf",
					"(C)Ljava/lang/Character;");
		} else if (type.equals(Type.DOUBLE_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Double", "valueOf",
					"(D)Ljava/lang/Double;");
		} else if (type.equals(Type.FLOAT_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Float", "valueOf",
					"(F)Ljava/lang/Float;");
		} else if (type.equals(Type.INT_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf",
					"(I)Ljava/lang/Integer;");
		} else if (type.equals(Type.LONG_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Long", "valueOf",
					"(J)Ljava/lang/Long;");
		} else if (type.equals(Type.SHORT_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Short", "valueOf",
					"(S)Ljava/lang/Short;");
		}
	}

	public static void genAsmForUnrescueingParameter(MethodVisitor mv,
			Type[] parameterTypes) {
		// ARRAY
		for (int i = 0; i < parameterTypes.length; i++) {
			// DUP --&gt; ARRAY,ARRAY
			mv.visitInsn(DUP);
			// ILOAD --&gt; ARRAY,I
			mv.visitLdcInsn(new Integer(i));
			// AALOAD --&gt; ARRAY,OBJ_I
			mv.visitInsn(AALOAD);
			// unrescue Parameter --> ARRAY,VALUE(evt.2x)
			genAsmConvertFromObject(mv, parameterTypes[i]);
			if (parameterTypes[i] == Type.LONG_TYPE
					|| parameterTypes[i] == Type.DOUBLE_TYPE) {
				// DUP2_X1 --&gt; VALUE(2x),ARRAY,VALUE(2x)
				mv.visitInsn(DUP2_X1);
				// POP2 --> VALUE(2x),ARRAY
				mv.visitInsn(POP2);
			} else {
				// SWAP --&gt; VALUE,ARRAY
				mv.visitInsn(SWAP);
			}
		}
		// POP --&gt; OBJ_I,OBJ_I2,...,OBJ_IN
		mv.visitInsn(POP);

	}

	private static void genAsmConvertFromObject(MethodVisitor mv, Type type) {
		if (type.equals(Type.BOOLEAN_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitTypeInsn(CHECKCAST, "java/lang/Boolean");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Boolean",
					"booleanValue", "()Z");
		} else if (type.equals(Type.BYTE_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitTypeInsn(CHECKCAST, "java/lang/Byte");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Byte", "byteValue",
					"()B");
		} else if (type.equals(Type.CHAR_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitTypeInsn(CHECKCAST, "java/lang/Double");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double", "charValue",
					"()C");
		} else if (type.equals(Type.DOUBLE_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitTypeInsn(CHECKCAST, "java/lang/Double");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Double",
					"doubleValue", "()D");
		} else if (type.equals(Type.FLOAT_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitTypeInsn(CHECKCAST, "java/lang/Float");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Float", "floatValue",
					"()F");
		} else if (type.equals(Type.INT_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitTypeInsn(CHECKCAST, "java/lang/Integer");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Integer", "intValue",
					"()I");
		} else if (type.equals(Type.LONG_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitTypeInsn(CHECKCAST, "java/lang/Long");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Long", "longValue",
					"()J");
		} else if (type.equals(Type.SHORT_TYPE)) {
			// INVOKESTATIC java/lang/Integer.valueOf(I)Ljava/lang/Integer;
			mv.visitTypeInsn(CHECKCAST, "java/lang/Short");
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Short", "shortValue",
					"()S");
		} else {
			try {
				mv.visitTypeInsn(CHECKCAST, type.getInternalName());
			} catch (NullPointerException e) {
				throw new RuntimeException(type.toString(), e);
			}
		}
	}

}
