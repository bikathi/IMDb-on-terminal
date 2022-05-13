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


public class ModelMultipleRequests extends SaveJobs{
    public void makeRequest(List<String> querries, Boolean getPosters) {
        for (String querry : querries) {
            String getRequestURL = "https://online-movie-database.p.rapidapi.com/auto-complete?q=" + querry;
            MoviesResultObject response = Unirest.get(getRequestURL)
                    .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "YOUR API KEY HERE")
                    .asObject(MoviesResultObject.class)
                    .getBody();
            
            new GenerateTables().generateGeneralTable(querry, response, getPosters);
            
            System.out.println("Initiating search querry save job... ");
            this.saveToFile(response, querry);
            System.out.println("Exiting search querry save job...");
        }
    }
}
