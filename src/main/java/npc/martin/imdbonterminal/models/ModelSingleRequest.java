package npc.martin.imdbonterminal.models;

import npc.martin.imdbonterminal.views.MoviesResultObject;
import kong.unirest.Unirest;
import npc.martin.imdbonterminal.views.GenerateTables;

public class ModelSingleRequest extends SaveJobs {
    public void makeRequest(String searchQuerry, Boolean getPosters) {
        String getRequestURL = "https://online-movie-database.p.rapidapi.com/auto-complete?q=" + searchQuerry;
        MoviesResultObject response = Unirest.get(getRequestURL)
	    .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
	    .header("X-RapidAPI-Key", "YOUR API KEY HERE")
	    .asObject(MoviesResultObject.class)
            .getBody();
        
        
        new GenerateTables().generateGeneralTable(searchQuerry, response, getPosters);
        
        System.out.println("Initiating search querry save job... ");
        this.saveToFile(response, searchQuerry);
        System.out.println("Exiting search querry save job...");
    }
}
