package npc.martin.imdbonterminal.views;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GenerateTables {
    protected Character[] borderStyle = AsciiTable.FANCY_ASCII;
    
    public void generateGeneralTable(String q, MoviesResultObject result, Boolean getPosters) {
        
        Integer numberOfSuccessfullMatches = result.getMovieMatches().size();
        
        //print the search querry:
        System.out.println("Your search querry: " + q);
        
        //print the number of found matches
        System.out.println("Number of related results: " + numberOfSuccessfullMatches);
        
        //general overview of results
        System.out.println("Results overview: ");
        
        System.out.println(AsciiTable.getTable(borderStyle, result.getMovieMatches(), Arrays.asList(
                new Column().header("Movie Name").dataAlign(HorizontalAlign.LEFT).maxColumnWidth(30).with(movie -> movie.getMovieLabel()),
                new Column().header("Rank On IMDb").dataAlign(HorizontalAlign.CENTER).with(movie -> Integer.toString(movie.getIMDbMovieRank())),
                new Column().header("Starring Actors").dataAlign(HorizontalAlign.LEFT).with(movie -> movie.getMovieStarrings()),
                new Column().header("Movie Poster").dataAlign(HorizontalAlign.LEFT).maxColumnWidth(40).with(movie -> movie.getMoviePoster().getImageUrl()),
                new Column().header("Year of Release").dataAlign(HorizontalAlign.LEFT).with(movie -> Integer.toString(movie.getYearOfRelease()))
        )));
        
        
        if(getPosters == true) {
            List<MovieMatches> movieMatches = result.getMovieMatches();
            this.downloadMoviePosters(movieMatches);
            System.out.println("Done... Posters should be downloaded into /User/Downloads/MoviePosters.");
        } else {
            System.out.println("Exiting without auto-downloading of posters... ");
        }
        
  
    }
    
    public void downloadMoviePosters(List<MovieMatches> sourceList) {
        Iterator it = sourceList.iterator();
        
        Integer numberOfPosters = sourceList.size();
        
        String hostOS = System.getProperty("os.name");
        String userHome = System.getProperty("user.home");
        
        //first make the folder to store the posters in
        this.makeDownloadsFolder(hostOS, userHome);
        
        //then download the posters
        while(it.hasNext()) {
            MovieMatches m = (MovieMatches) it.next();
            
            System.out.println("Processing result for: " + m.getMovieLabel() + " ...");
            
            //get the image URL
            String imageUrl = m.getMoviePoster().getImageUrl();
            //create a file name
            String fileName = m.getMovieLabel() + " poster";
            //get/ the storage location
            String storageLocation = userHome + File.separator + "Downloads" + File.separator + "Movie Posters";
                
            try {
                System.out.println("Downloading poster ...");
                numberOfPosters--;
                
                this.getImageFromNet(imageUrl, fileName, storageLocation);
            } catch (IOException ex) {
                ex.printStackTrace();
            } 
        }
    }
    
    public void makeDownloadsFolder(String os, String home) {
        if(os.equals("Linux") || os.equals("MacOS") || os.equals("Windows")) {
            //navigate to the downloads dir
            File file = new File(home + File.separator + "Downloads" + File.separator + "Movie Posters");
            
            //make the Movie Posters folder there
            file.mkdir();
        } else {
            System.out.println("The movie poster downloads feature is not supported on this OS.");
        }
    }
    
    public void getImageFromNet(String imageUrl, String fileName, String location) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(location + File.separator + fileName);
        
        byte[] b = new byte[2040];
        int length;
        
        while((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }
        
        is.close();
        os.close();
    }
}
