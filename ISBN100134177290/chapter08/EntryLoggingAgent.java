package chapter08; // bytecodeAnnotations

import java.lang.instrument.*;
import org.objectweb.asm.*;

/**
 * This program installs a class file transformer. The transformer first checks whether the class name 
 * matches the agent argument. If so, it uses the {@link EntryLogger} class to modify the bytecodes. <br />
 * @version 1.10 2016-05-10 
 * @author Cay Horstmann
 */
public class EntryLoggingAgent {
	public static void premain(final String arg, Instrumentation instr) {
		instr.addTransformer((loader, className, cl, pd, data) -> {
			if (!className.equals(arg)) return null;
			ClassReader reader = new ClassReader(data);
			ClassWriter writer = new ClassWriter(
					ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES
					);
			EntryLogger el = new EntryLogger(writer, className);
			reader.accept(el, ClassReader.EXPAND_FRAMES);
			return writer.toByteArray();
		}); 
	}
}
