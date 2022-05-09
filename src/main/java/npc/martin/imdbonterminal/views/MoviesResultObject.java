package npc.martin.imdbonterminal.views;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

//the JSON respose is a (self-named) MoviesResultObject
public class MoviesResultObject {
    //this object has a collection of movie matches- which we call MovieMatchObject
    private List<MovieMatches> d = null;

    //and these are the other instance variables of this class
    private String q; //your search querry

    public String getSearchQuerry() {
        return q;
    }

    public List<MovieMatches> getMovieMatches() {
        return d;
    }

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(this);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

//this is the class representing a single match result- a movie match
class MovieMatches {
    //It has an image object- which we refer to by the MoviePoster class
    private MoviePoster i; //the poster for the movie

    //and these are other instance variables of interest that a single match 'may' have
    private String l; //movie label (name) on IMDb servers
    private Integer rank; //movie rank on IMDb
    private String s; //movie starrings
    private Integer y = 0000; //year the movie will be released

    public MoviePoster getMoviePoster() {
        return i;
    }
    
    public String getMovieLabel() {
        return l;
    }
    
    public String getMovieStarrings() {
        return s;
    }
    
    
    public Integer getIMDbMovieRank() {
        return rank;
    }
    
    public Integer getYearOfRelease() {
        return y;
    }
    
    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(this);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

//this is the class representing the MoviePoster embedded inside a single movie match
class MoviePoster {
    //these are the variables making up the image
    private String imageUrl; //URL to view the image

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(this);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}