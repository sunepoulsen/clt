//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.cli;

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.api.SubCommandDefinition;
import dk.sunepoulsen.clt.api.SubCommandExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

//-----------------------------------------------------------------------------

/**
 * Created by sunepoulsen on 07/03/16.
 */
public class SubCommandDefinitionWithNoAnnotation implements SubCommandDefinition {
    public SubCommandDefinitionWithNoAnnotation() {
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
