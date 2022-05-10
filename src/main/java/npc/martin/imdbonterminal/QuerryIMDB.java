package npc.martin.imdbonterminal;

import java.util.ArrayList;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import npc.martin.imdbonterminal.models.*;

@Command(name = "", description = "Searching for movies from IMDb.", mixinStandardHelpOptions = true, 
        version = "QuerryIMBb-1.0 BETA")
public class QuerryIMDB implements Runnable {
    @Option(names = { "-q", "--querry" }, paramLabel = "SEARCH TERM", description = "The movie term you're searching for.")
    protected String searchQuerry;
    
    @Option(names = { "-qs", "--querries" }, paramLabel = "SEARCH TERMS", arity = "1...3",
            description = "Upto three movie terms to search for.")
    protected ArrayList<String> searchQuerries = new ArrayList<String>(3);
    
    @Option(names = { "-g", "--get-posters" }, description = "Authorize auto-downloading of available movie posters.")
    protected Boolean getPosters = false; //by default is false to save on bandwidth
    
    long start, end, executionTime; double timeInSeconds;
    
    //save for search querries happens by default, and cannot be dissabled- saved as JSON.
    //the location to save the movie posters is in the user Downloads directories.
    
    @Override
    public void run() {
        if(searchQuerry != null) {
            //1. You can't use the '-q' and '-qs' flags together
            if(!searchQuerries.isEmpty()) {
                System.out.println("Can't use the -q and -qs flags together. Use the -h flag for help.");
            } else {
                start = System.nanoTime();
                System.out.println("Starting job...");
                new ModelSingleRequest().makeRequest(searchQuerry, getPosters);
                end = System.nanoTime();
                timeInSeconds = (long) ((end - start) / 1000000000);
                System.out.println("Job ended. Took " + timeInSeconds + " seconds.");
            }
        } else if(searchQuerry == null) {
            //2. Either the -q or the -qs flags should be used but not both at once
            if(searchQuerries.isEmpty()) {
                System.out.println("Use either -q or -qs flags. Use the -h flag for help.");
            } else if(!searchQuerries.isEmpty()) {
                //3. The -qs flag cannot take more than three parameters or none
                if(searchQuerries.size() > 3) {
                    System.out.println("The -qs flag takes a maximum of three arguments. Use -h for help.");
                } else if(searchQuerries.size() == 0) {
                    System.out.println("The -qs flag needs at least one argument. Use -h for help.");
                } else {
                    start = System.nanoTime();
                    System.out.println("Starting jobs...");
                    new ModelMultipleRequests().makeRequest(searchQuerries, getPosters);
                    end = System.nanoTime();
                    timeInSeconds = (long) ((end - start) / 1000000000);
                    System.out.println("Jobs ended. Took " + timeInSeconds + " seconds.");
                }
            }
        }
    }
    
    public static void main( String[] args ) {
        //for testing
        //new CommandLine(new QuerryIMDB()).execute("-qs", "fast and furious", "God of war");
        
        
        //for deployment
        int exitCode = new CommandLine(new QuerryIMDB()).execute(args);
        System.exit(exitCode);
    }
}
