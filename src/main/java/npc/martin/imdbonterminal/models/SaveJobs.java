package npc.martin.imdbonterminal.models;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import npc.martin.imdbonterminal.views.MoviesResultObject;

public abstract class SaveJobs {
    public void saveToFile(MoviesResultObject response, String filePrefix) {
        String outputString = response.toString();
        String fileNamePrefix = filePrefix;
        
        //get the user home
        String home = System.getProperty("user.home");
        
        //make the storage directory in a hidden folder in /user/home
        File file = new File(home + File.separator + ".IMDbforTerminal");
        file.mkdir();
        
        //get path to the storage 
        Path path = Paths.get(home + File.separator + ".IMDbforTerminal" + File.separator + fileNamePrefix + ".txt");
        
        try {
            Files.writeString(path, outputString, StandardCharsets.UTF_8);
        } catch(IOException ex) {
            System.out.println("Invalid Path");
        }
    }
}
