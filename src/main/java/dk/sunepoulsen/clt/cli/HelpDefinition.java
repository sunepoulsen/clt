//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.cli;

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.api.CliException;
import dk.sunepoulsen.clt.api.SubCommand;
import dk.sunepoulsen.clt.api.SubCommandDefinition;
import dk.sunepoulsen.clt.api.SubCommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 17/04/16.
 */
@SubCommand(
    name = "help",
    usage = "[command name]",
    description = "Show help for all or a single command."
)
public class HelpDefinition implements SubCommandDefinition {
    public HelpDefinition( String programHelpText, SubCommandRegistry registry ) {
        this.programHelpText = programHelpText;
        this.registry = registry;
    }

    @Override
    public Options createOptions() {
        return new Options();
    }

    @Override
    public SubCommandExecutor createExecutor( final CommandLine line ) throws CliException {
        logger.entry();

        try {
            if( line.getArgList().isEmpty() ) {
                return new HelpExecutor( programHelpText, registry );
            }

            if( line.getArgList().size() == 1 ) {
                return new HelpExecutor( programHelpText, registry, line.getArgList().get( 0 ) );
            }

            throw new CliException( "The subcommand 'help' only accepts one argument" );
        }
        finally {
            logger.exit();
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( HelpDefinition.class );

    private String programHelpText;
    private SubCommandRegistry registry;
}
