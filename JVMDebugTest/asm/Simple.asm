// class version 50.0 (50)
// access flags 33
public class Simple {

  // compiled from: Simple.java

  // access flags 1
  public <init>()V
   L0
    LINENUMBER 10 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
    RETURN
   L1
    LOCALVARIABLE this LSimple; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 9
  public static get()I
   L0
    LINENUMBER 13 L0
   FRAME FULL [] []
    ICONST_1
    ISTORE 0
   L1
    LINENUMBER 14 L1
    ICONST_0
    ISTORE 1
   L2
    GOTO L3
   L4
    LINENUMBER 15 L4
   FRAME FULL [I I] []
    ILOAD 0
    ILOAD 0
    IADD
    ISTORE 0
   L5
    LINENUMBER 14 L5
    IINC 1 1
   L3
   FRAME FULL [I I] []
    ICONST_5
    ILOAD 1
    IF_ICMPGE L4
   L6
    LINENUMBER 18 L6
    ILOAD 0
    INVOKESTATIC Simple.get (I)I
    IRETURN
   L7
    LOCALVARIABLE temp I L1 L7 0
    LOCALVARIABLE i I L2 L6 1
    MAXSTACK = 2
    MAXLOCALS = 2

  // access flags 10
  private static get(I)I
   L0
    LINENUMBER 22 L0
    ILOAD 0
    ICONST_M1
    IADD
    IRETURN
   L1
    LOCALVARIABLE temp I L0 L1 0
    MAXSTACK = 2
    MAXLOCALS = 1
}
