package npc.martin.imdbonterminal.models;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import npc.martin.imdbonterminal.views.MoviesResultObject;
import java.util.List;
import kong.unirest.Unirest;
import npc.martin.imdbonterminal.views.GenerateTables;


public class ModelMultipleRequests {
    public void makeRequest(List<String> querries, Boolean getPosters) {
        for (String querry : querries) {
            String getRequestURL = "https://online-movie-database.p.rapidapi.com/auto-complete?q=" + querry;
            MoviesResultObject response = Unirest.get(getRequestURL)
                    .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "663ca5a111msh7c4791d9dff1181p132acdjsn58c849b9b499")
                    .asObject(MoviesResultObject.class)
                    .getBody();
            
            new GenerateTables().generateGeneralTable(querry, response, getPosters);
            
            System.out.println("Initiating search querry save job... ");
            this.saveToFile(response, querry);
            System.out.println("Exiting search querry save job...");
        }
    }
    
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
