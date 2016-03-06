//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.api;

//-----------------------------------------------------------------------------

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

/**
 * Defines a sub command to be supported by the main program.
 *
 * <h3>Implementations</h3>
 *
 * Implementation classes will have to be annotated with the <code>SubCommand</code>
 * annotation to register the sub command to the main program.
 */
public interface SubCommandDefinition {
    /**
     * Creates the options that this sub command accepts at the command line.
     *
     * @return The options and arguments.
     */
    Options createOptions();

    /**
     * Creates a sub command executor to execute the sub command based on the parsed arguments
     * and options from the command line.
     *
     * @param line The parsed arguments and options specified on the command line.
     *
     * @return A sub command executor to execute the sub command.
     */
    SubCommandExecutor createExecutor( CommandLine line );
}
