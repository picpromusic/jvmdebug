package dev.java.net.jvmDebug;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

	public class Premain {
		public static void premain(String agentArgs, Instrumentation inst) {
			ClassFileTransformer classFileTransformer;
			if (agentArgs != null) {
				String[] ignore = agentArgs.split(",");
				classFileTransformer = new DebugClassTransformer(ignore);
			} else {
				classFileTransformer = new DebugClassTransformer(new String[0]);
			}
			inst.addTransformer(classFileTransformer);
		}
	}
