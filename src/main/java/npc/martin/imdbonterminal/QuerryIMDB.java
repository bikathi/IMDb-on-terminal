package npc.martin.imdbonterminal;


import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

@Command(name = "", description = "Start the IMDB querry utility.", mixinStandardHelpOptions = true, version = "QuerryIMBb-1.0 BETA")
public class QuerryIMDB implements Runnable {
    @Option(names = { "-q", "--querry" }, paramLabel = "SEARCH QUERRY", description = "Give the movie/ show phrase to querry.")
    protected String searchQuerry;
    
    @Option(names = { "-g", "--general" }, description = "Give a general overview of search querry results.")
    protected Boolean general;
    
    @Option(names = { "-d", "--detailed" }, description = "Give a detailed overview of each querry result.")
    protected Boolean detailed;
    
    @Option(names = { "-s", "--save" }, description = "Save your search querry and your results as JSON.")
    protected Boolean save;
    
    
    @Override
    public void run() {
        
    }
    
    public static void main( String[] args ) {
        //for testing
        new CommandLine(new QuerryIMDB()).execute("-h");
        
        
        //for deployment
//        int exitCode = new CommandLine(new QuerryIMDB()).execute(args);
//        System.exit(exitCode);
    }
}
