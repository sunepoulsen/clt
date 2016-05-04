//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.api;

//-----------------------------------------------------------------------------
/**
 * Exception to report sub command related errors.
 */
public class CliException extends Exception {
    /**
     * Constructs an exception with a single message.
     *
     * @param message The message.
     */
    public CliException( String message ) {
        super( message );
    }

    /**
     * Constructs an exception from a formated message and a number of arguments.
     * <p>
     *     This constructor is simply a shortcut for
     *     <code>CliException( String.format( format, args ) )</code>
     * </p>
     *
     * @param format A format string.
     * @param args   Arguments to the formatted string.
     */
    public CliException( String format, Object... args ) {
        this( String.format( format, args ) );
    }

    /**
     * Constructs an exception with a message and a caused throwable.
     *
     * @param message Message.
     * @param cause   Cause instance.
     */
    public CliException( String message, Throwable cause ) {
        super( message, cause );
    }
}
