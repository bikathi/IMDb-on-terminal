package npc.martin.imdbonterminal.views;

import npc.martin.imdbonterminal.QuerryIMDB;
import npc.martin.imdbonterminal.models.*;
import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import java.util.Arrays;
import java.util.List;


public class GenerateTables extends QuerryIMDB {
    protected Character[] borderStyle = AsciiTable.FANCY_ASCII;
    
    public void generateGeneralTable(MoviesResultObject result) {
        
        Integer numberOfSuccessfullMatches = result.getMovieMatches().size();
        
        //print the search querry:
        System.out.println("Your search querry: " + searchQuerry);
        
        //print the number of found matches
        System.out.println("Number of related results: " + numberOfSuccessfullMatches);
        
        
        
        //print out the general table
        System.out.println(AsciiTable.getTable(borderStyle, result.getMovieMatches(), Arrays.asList(
                new Column().header("Movie Name").with(),
                new Column().header("Rank On IMDb").with(),
                new Column().header("Starring Actors").with()
        )));
        
        //then call the method that generates the detailed table
        this.generateDetailedTable(result);
    }
    
    public void generateDetailedTable(MoviesResultObject result) { 
        System.out.println(AsciiTable.getTable(borderStyle, result.getMovieMatches(), Arrays.asList(
                new Column().header("Movie Name").with(),
                new Column().header("IMDb Rank").with(),
                new Column().header("Starring Actors").with(),
                new Column().header("Movie Poster").with(),
                new Column().header("Year of Release").with()
        )));
    }
}
