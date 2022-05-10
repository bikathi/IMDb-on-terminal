package npc.martin.imdbonterminal.models;

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
        }
    }
}
