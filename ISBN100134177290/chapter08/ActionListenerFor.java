package chapter08;  // runtimeAnnotations

import java.lang.annotation.*;

/**
 * {@code ActionListenerFor} annotated interface Listing 8.11 <br />
 * {@link ButtonFrame3} class extends JFrame Listing 8.10 <br />
 * {@link ActionListenerInstaller} class Listing 8.9 <br />
 * @version 1.00 2004-08-17
 * @author Cay Horstmann
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionListenerFor {
	String source();
}
