//-----------------------------------------------------------------------------
package dk.sunepoulsen.clt.cli;

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.api.SubCommand;
import dk.sunepoulsen.clt.api.SubCommandDefinition;
import org.reflections.Reflections;
import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

//-----------------------------------------------------------------------------
/**
 * Registry of all the sub commands that is supported by the main program.
 */
public class SubCommandRegistry {
    public SubCommandRegistry( String programHelpText ) {
        this.programHelpText = programHelpText;
        this.subCommands = new HashMap<>();

        addHelpCommand();
    }

    public void addSubCommands( String packageName ) throws IllegalAccessException, InstantiationException {
        logger.entry();

        try {
            Reflections reflections = new Reflections( packageName );
            addSubCommands( reflections );
        }
        finally {
            logger.exit();
        }
    }

    public void addSubCommands( Reflections reflections ) throws IllegalAccessException, InstantiationException {
        logger.entry();

        try {
            Set<Class<?>> candidates = reflections.getTypesAnnotatedWith( SubCommand.class );
            Iterator<Class<?>> iterator = candidates.iterator();
            while( iterator.hasNext() ) {
                Class<?> clazz = iterator.next();
                if( clazz == HelpDefinition.class ) {
                    continue;
                }

                SubCommand subCommand = clazz.getAnnotation( SubCommand.class );

                SubCommandDefinition subCommandDefinition = checkAndConvertAnnotatedClass( clazz );
                if( subCommandDefinition != null ) {
                    subCommands.put( subCommand.name(), subCommandDefinition );
                }
            }
        }
        finally {
            logger.exit();
        }
    }

    public Set<Map.Entry<String, SubCommandDefinition>> getCommands() {
        return subCommands.entrySet();
    }

    /**
     * Lokkup and finds a subcommand definition by its name.
     *
     * @param subCommandName Name of the subcommand to find.
     *
     * @return The sub command definition.
     */
    public SubCommandDefinition find( String subCommandName ) {
        logger.entry( subCommandName );

        SubCommandDefinition result = null;
        try {
            return result = subCommands.get( subCommandName );
        }
        finally {
            logger.exit( result );
        }
    }

    private SubCommandDefinition checkAndConvertAnnotatedClass( Class<?> clazz ) throws IllegalAccessException, InstantiationException {
        logger.entry();

        SubCommandDefinition result = null;
        try {
            Object obj = clazz.newInstance();
            if( obj instanceof SubCommandDefinition ) {
                return result = (SubCommandDefinition)obj;
            }

            return result = null;
        }
        finally {
            logger.exit( result );
        }
    }

    private void addHelpCommand() {
        logger.entry();

        try {
            HelpDefinition helpDef = new HelpDefinition( programHelpText, this );
            SubCommand subCommand = helpDef.getClass().getAnnotation( SubCommand.class );

            if( subCommand != null ) {
                subCommands.put( subCommand.name(), helpDef );
            }
            else {
                output.warn( "Unable to add 'help' command, because it is not annotated with '@SubCommand'" );
            }
        }
        finally {
            logger.exit();
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( SubCommandRegistry.class );
    private static final XLogger output = XLoggerFactory.getXLogger( CliApplication.OUTPUT_LOGGER_NAME );

    private String programHelpText;

    /**
     * Simple map of sub command names and sub command definitions.
     */
    private Map<String, SubCommandDefinition> subCommands;
}
