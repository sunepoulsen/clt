//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.cli;

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.api.SubCommand;
import org.junit.Test;
import org.reflections.Reflections;

//-----------------------------------------------------------------------------
public class SubCommandRegistryTest {
    @Test
    public void testRegisterFromPackageClazz() throws Exception {
        Reflections reflections = new Reflections( getClass().getPackage().getName() );

        SubCommandRegistry instance = new SubCommandRegistry();
        instance.addSubCommands( reflections );

        String subCommandName = SubCommandDefinitionWithAnnotation.class.getAnnotation( SubCommand.class ).name();
        assert instance.find( subCommandName ) != null

        subCommandName = SubCommandDefinitionWithAnnotationButWrongImplements.class.getAnnotation( SubCommand.class ).name();
        assert instance.find( subCommandName ) == null

        assert instance.find( "SubCommandDefinitionWithNoAnnotation" ) == null
    }

    @Test
    public void testRegisterFromPackageName() throws Exception {
        SubCommandRegistry instance = new SubCommandRegistry();
        instance.addSubCommands( getClass().getPackage().getName() );

        String subCommandName = SubCommandDefinitionWithAnnotation.class.getAnnotation( SubCommand.class ).name();
        assert instance.find( subCommandName ) != null

        subCommandName = SubCommandDefinitionWithAnnotationButWrongImplements.class.getAnnotation( SubCommand.class ).name();
        assert instance.find( subCommandName ) == null

        assert instance.find( "SubCommandDefinitionWithNoAnnotation" ) == null
    }
}
