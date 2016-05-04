//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.cli;

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.api.SubCommand;
import dk.sunepoulsen.clt.api.SubCommandDefinition;
import dk.sunepoulsen.clt.api.SubCommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 07/03/16.
 */
@SubCommand( name = "SubCommandDefinitionWithAnnotation" )
public class SubCommandDefinitionWithAnnotation implements SubCommandDefinition {
    public SubCommandDefinitionWithAnnotation() {
    }

    @Override
    public Options createOptions() {
        return null;
    }

    @Override
    public SubCommandExecutor createExecutor( final CommandLine line ) {
        return null;
    }
}
