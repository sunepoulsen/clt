//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.api;

//-----------------------------------------------------------------------------
/**
 * Annotation to register a subcommand with the main program.
 */
public @interface SubCommand {
    String name = "";
    String description = "";
}
