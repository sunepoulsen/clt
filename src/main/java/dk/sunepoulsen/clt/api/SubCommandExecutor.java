//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.api;

//-----------------------------------------------------------------------------
/**
 * Interface for an executor to execute the logic of a parsed sub command.
 * <p>
 *     Each sub command should have its own sub command executor to execute
 *     the sub command.
 * </p>
 * <p>
 *     Implementation classes will hold attributes for each argument or option
 *     that is supported by the sub command on the command line. <code>validateArguments()</code>
 *     is called to validate the values of the arguments. For instance to check if a file exists.
 * </p>
 */
public interface SubCommandExecutor {
    /**
     * Validates the state of arguments to the sub command.
     */
    void validateArguments() throws CliException;

    /**
     * Performs the action of a sub command.
     */
    void performAction() throws CliException;
}
