//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.cli;

//-----------------------------------------------------------------------------

import dk.sunepoulsen.clt.api.CliException;
import dk.sunepoulsen.clt.api.SubCommandExecutor;
import org.reflections.Reflections;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

/**
 * Created by sunepoulsen on 17/04/16.
 */
public class CliApplication {
    public CliApplication( String programHelpText, String packageName ) throws IllegalAccessException, InstantiationException {
        this( programHelpText, new Reflections( packageName ) );
    }

    public CliApplication( String programHelpText, Reflections reflections ) throws InstantiationException, IllegalAccessException {
        registry = new SubCommandRegistry( programHelpText );
        registry.addSubCommands( reflections );

        interpreter = new CliInterpreter( programHelpText, registry );
    }

    public void main( String[] args ) {
        logger.entry( args );

        try {
            SubCommandExecutor executor = interpreter.parseArguments( args );
            executor.validateArguments();
            executor.performAction();

            System.exit( 0 );
        }
        catch( CliException ex ) {
            output.error( ex.getMessage() );
            logger.catching( XLogger.Level.DEBUG, ex );

            System.exit( 1 );
        }
        finally {
            logger.exit();
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    public static final String OUTPUT_LOGGER_NAME = "Cli.Output.Logger";

    private static final XLogger logger = XLoggerFactory.getXLogger( CliApplication.class );
    private static final XLogger output = XLoggerFactory.getXLogger( OUTPUT_LOGGER_NAME );

    private SubCommandRegistry registry;
    private CliInterpreter interpreter;
}
