package dev.java.net.jvmDebug;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.util.TraceAbstractVisitor;

public class DebugTraceAbstractVisitor extends TraceAbstractVisitor implements
		LineNumberator {

	protected int curLine;
	private Object buf; // hide buf of superClass
	protected List<LineNumberator> lines;

	public DebugTraceAbstractVisitor() {
		curLine = 0;
		lines = new LinkedList<LineNumberator>();
	}

	protected DebugTraceAbstractVisitor appendBuf(Object content) {
		curLine += countLines(content.toString());
		super.buf.append(content);
		return this;
	}

	private static int countLines(String content) {
		int lines = 0;
		int pos = content.indexOf("\n");
		while (pos >= 0) {
			lines++;
			pos = content.indexOf("\n", pos + 1);
		}
		return lines;
	}

	protected void addText() {
		if (super.buf.length() > 0) {
			String addContent = super.buf.toString();
			super.text.add(addContent);
			super.buf.setLength(0);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("curLine=").append(getCurLine()).append("\n");
		for (Object element : super.text) {
			if (element instanceof Collection) {
				element = recListToString((Collection) element);
			}
			sb.append(element);
		}
		sb.append(super.buf);
		return sb.toString();
	}

	private String recListToString(Collection elements) {
		StringBuilder sb = new StringBuilder();
		for (Object object : elements) {
			if (object instanceof Collection) {
				object = recListToString((Collection) object);
			}
			sb.append(object);
		}
		return sb.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dev.java.net.jvmDebug.LineNumberator#getCurLine()
	 */
	public int getCurLine() {
		int line = curLine;
		for (LineNumberator ln : lines) {
			line += ln.getCurLine();
		}
		return line;
	}
}
