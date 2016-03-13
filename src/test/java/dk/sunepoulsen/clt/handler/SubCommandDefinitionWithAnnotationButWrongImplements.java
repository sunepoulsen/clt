//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.handler;

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
@SubCommand( name = "SubCommandDefinitionWithAnnotationButWrongImplements" )
public class SubCommandDefinitionWithAnnotationButWrongImplements {
    public SubCommandDefinitionWithAnnotationButWrongImplements() {
    }
}
