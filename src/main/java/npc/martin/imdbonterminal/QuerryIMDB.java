package npc.martin.imdbonterminal;

import java.util.ArrayList;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import npc.martin.imdbonterminal.models.*;

@Command(name = "", description = "Start the IMDB querry utility.", mixinStandardHelpOptions = true, 
        version = "QuerryIMBb-1.0 BETA")
public class QuerryIMDB implements Runnable {
    @Option(names = { "-q", "--querry" }, paramLabel = "SEARCH QUERRY", 
            description = "Provide the movie/ show phrase to querry.")
    protected String searchQuerry;
    
    @Option(names = { "-s", "--save" }, description = "Save your search querry and your results as JSON.")
    protected Boolean save;
    
    @Option(names ={ "-m", "--multiple" }, description = "Use this flag to provide more than one search querries to the util.")
    protected Boolean mult;
    
    @Option(names = {"-l", "--list"}, description = "List of querries you are searching for.", 
            paramLabel = "QUERRY LIST", arity = "1...5")
    protected ArrayList<String> searchQuerries = new ArrayList<String>(3);
    
    @Override
    public void run() {
        new ModelSingleRequest().makeRequest(searchQuerry);
    }
    
    public static void main( String[] args ) {
        //for testing
        new CommandLine(new QuerryIMDB()).execute("-q", "transformers", "-g");
        
        
        //for deployment
//        int exitCode = new CommandLine(new QuerryIMDB()).execute(args);
//        System.exit(exitCode);
    }
}
