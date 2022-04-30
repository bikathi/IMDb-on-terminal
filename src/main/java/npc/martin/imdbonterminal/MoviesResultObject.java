package npc.martin.imdbonterminal;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

//the JSON respose is a (self-named) MoviesResultObject
public class MoviesResultObject {
    //this object has a collection of movie matches- which we call MovieMatchObject
    protected List<MovieMatchObject> d = null;

    //and these are the other instance variables of this class
    private String q;
    protected Integer v;

    public String getQ() {
        return q;
    }

    public List<MovieMatchObject> getMovieMatchObject() {
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
class MovieMatchObject {
    //It has an image object- which we refer to by the ImageObject class
    ImageObject i;

    //and these are the other instance variables that a single match 'may' have
    private String id;
    String l;
    String q;
    Integer rank;
    String s;

    //Inside a single match result is embedded a list of videos related to that match
    //This list is therefore of the type VideoObject
    List<VideoObject> v;

    public String getIMovieMatchObject() {
        return id;
    }

    public List<VideoObject> getV() {
        return v;
    }

    public ImageObject getImageObject() {
        return i;
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

//this is the class representing the ImageObject embedded inside a single movie match
class ImageObject {
    //these are the variables making up one image
    Integer height;
    private String imageUrl;
    Integer width;

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

//and this is the class representing the VideoObject embedded inside a single movie match object
class VideoObject {
    //the Video has it's associated images- say a thumbnail-like
    //we get it's properties from the ImageObject class because they are the same
    ImageObject i;

    //and these are the other variables forming one VideoObject
    String id;
    private String l;
    String s;

    public ImageObject getImageObject() {
        return i;
    }

    public String getL() {
        return l;
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
