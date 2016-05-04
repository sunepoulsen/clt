//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.cli;

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.api.CliException;
import dk.sunepoulsen.clt.api.SubCommand;
import dk.sunepoulsen.clt.api.SubCommandDefinition;
import dk.sunepoulsen.clt.api.SubCommandExecutor;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 17/04/16.
 */
public class HelpExecutor implements SubCommandExecutor {
    public HelpExecutor( SubCommandRegistry registry ) {
        this( registry, null );
    }

    public HelpExecutor( SubCommandRegistry registry, String subCommandName ) {
        this.registry = registry;
        this.subCommandName = subCommandName;
    }

    @Override
    public void validateArguments() throws CliException {
        if( registry == null ) {
            Exception ex = new IllegalStateException( "Registry can not be (null)." );
            throw new CliException( ex.getMessage(), ex );
        }
    }

    @Override
    public void performAction() throws CliException {
        logger.entry();

        try {
            if( subCommandName == null ) {
                printHelp();
            }
            else {
                printCommandHelp();
            }
        }
        finally {
            logger.exit();
        }
    }

    private void printHelp() {
        logger.entry();

        try {
            logger.info( "Help to program..." );
            logger.info( "" );
            logger.info( "Commands:" );
            logger.info( "" );

            for( Map.Entry<String, SubCommandDefinition> entry : registry.getCommands() ) {
                SubCommand subCommand = entry.getValue().getClass().getAnnotation( SubCommand.class );

                if( subCommand != null ) {
                    logger.info( "{} - {}", entry.getKey(), subCommand.description() );
                }
            }
        }
        finally {
            logger.exit();
        }
    }

    private void printCommandHelp() throws CliException {
        logger.entry();

        try {
            throw new CliException( "Not implementated yet!" );
        }
        finally {
            logger.exit();
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( HelpExecutor.class );

    private SubCommandRegistry registry;
    private String subCommandName;
}
