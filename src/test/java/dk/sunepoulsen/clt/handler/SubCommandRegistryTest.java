//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.handler;

import dk.sunepoulsen.clt.api.SubCommand;
import org.junit.Test;
import org.reflections.Reflections;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

//-----------------------------------------------------------------------------
public class SubCommandRegistryTest {
    @Test
    public void testRegisterFromPackage() throws Exception {
        Reflections reflections = new Reflections( getClass().getPackage().getName() );

        SubCommandRegistry instance = new SubCommandRegistry();
        instance.addSubCommands( reflections );

        String subCommandName = SubCommandDefinitionWithAnnotation.class.getAnnotation( SubCommand.class ).name();
        assertThat( instance.find( subCommandName ), notNullValue() );

        subCommandName = SubCommandDefinitionWithAnnotationButWrongImplements.class.getAnnotation( SubCommand.class ).name();
        assertThat( instance.find( subCommandName ), nullValue() );

        assertThat( instance.find( "SubCommandDefinitionWithNoAnnotation" ), nullValue() );
    }
}
