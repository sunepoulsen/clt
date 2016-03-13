//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.api;

//-----------------------------------------------------------------------------
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//-----------------------------------------------------------------------------
/**
 * Annotation to register a subcommand with the main program.
 */
@Retention( value = RetentionPolicy.RUNTIME )
public @interface SubCommand {
    String name();
    String description() default "";
}
