package npc.martin.imdbonterminal.models;

import kong.unirest.Unirest;
import npc.martin.imdbonterminal.views.GenerateTables;

public class ModelSingleRequest {
    public void makeRequest(String searchQuerry) {
        String getRequestURL = "https://online-movie-database.p.rapidapi.com/auto-complete?q=" + searchQuerry;
        MoviesResultObject response = Unirest.get(getRequestURL)
	    .header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
	    .header("X-RapidAPI-Key", "663ca5a111msh7c4791d9dff1181p132acdjsn58c849b9b499")
	    .asObject(MoviesResultObject.class)
            .getBody();
        
        
        new GenerateTables().generateGeneralTable(response);
    }
}
