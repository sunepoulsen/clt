//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.cli;

//-----------------------------------------------------------------------------

import dk.sunepoulsen.clt.api.CliException;
import dk.sunepoulsen.clt.api.SubCommandDefinition;
import dk.sunepoulsen.clt.api.SubCommandExecutor;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.util.Arrays;

//-----------------------------------------------------------------------------
/**
 * Implements an interpreter to parse the entire command line to a single
 * subcommand to be executed.
 */
public class CliInterpreter {
    public CliInterpreter( SubCommandRegistry registry ) {
        this.registry = registry;
    }

    public SubCommandExecutor parseArguments( String[] args ) throws CliException {
        logger.entry();

        try {
            if( args.length == 0 ) {
                return new HelpExecutor( registry );
            }

            SubCommandDefinition definition = registry.find( args[0] );
            if( definition == null ) {
                throw new CliException( "The command '%s' does not exist.", args[0] );
            }

            DefaultParser parser = new DefaultParser();
            return definition.createExecutor( parser.parse( definition.createOptions(), Arrays.copyOfRange( args, 1, args.length ) ) );
        }
        catch( ParseException ex ) {
            throw new CliException( ex.getMessage(), ex );
        }
        finally {
            logger.exit();
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( CliInterpreter.class );

    private SubCommandRegistry registry;
}
