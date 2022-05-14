# IMDb ON TERMINAL
## WHAT THIS IS
This is simple utility helps you easily get movies and show data related to a keyword or phrase you type in. You can also get access to posters on the suggested movies if they are available.  
 
## INSTALLATION-SPECIFICALLY FOR LINUX & MAC
On Windows, you can simply skip to the **USING THE UTILITY** section and use the command `java -jar IMDbOnTerminal-1.0-STABLE.jar` in place of `gevie`.
### PREREQUISITES
- **A JDK or a JVM be installed on the target machine**.
- Basic knowledge of terminal commands.
- A RAPID API key, and a subscription to the IMBb API. To get one, click [here](https://rapidapi.com/apidojo/api/online-movie-database/) 
- Some Java (or any programming language) knowledge.
- Any Linux OS. This installation is specifically for Linux.
>Note that if you download the `IMDbOnTerminal-1.0-STABLE.jar` file in this repository, **you will be capped to translate only 500 searches per month**.
>**You will also be using my API key to search for movies** which may bill me. Also, the multiple people that have downloaded the same will be fast depleting the 500 max cap. So consider using the first procedure described below for maximum comfortability when using this utility. Otherwise, proceed direct to procedure A step 9.

### PROCEDURE A
1. Download the download the source code zip file from this repository.
2. Extract the zip file an you should get a `cli-google-translate-master` folder inside.
3. In the folder, navigate to `src/main/java/npc/martin/imdbonterminal/`. In there, there should be a number of Java source files. We will deal with only two.
4. Open the `models/ModelSingleRequest.java` file in any text editor and navigate to the lines with _YOUR API KEY HERE_ with your RAPID API KEY like this:
```
.header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
    .header("X-RapidAPI-Key", "PASTE YOUR API KEY HERE")
    .asObject(MoviesResultObject.class)
    .getBody()
```
5. Save this file an close. Then open the `models/ModelMultipleRequests.java` file and edit the same String on line 32 like this:
```
.header("X-RapidAPI-Host", "online-movie-database.p.rapidapi.com")
    .header("X-RapidAPI-Key", "PASTE YOUR API KEY HERE")
    .asObject(MoviesResultObject.class)
    .getBody()
```
6. Save the file an exit then navigate back to the parent folder `IMDbOnTerminal-source-code`. Rename the folder as IMDbOnTerminal`.
7. Go into the renamed folder and run `mvn clean package` to compile and package the package the project.
8. You should now see a `target` folder. Navigate into it an there should be two jar files.  Copy the `IMDbOnTerminal-1.0-SNAPSHOT.jar` file somewhere else an rename it to `IMDbOnTerminal-1.0-STABLE.jar`. This is the full package you need to use the utility.
9. To test the utility jar file, navigate to the folder where the download was placed and run the command `java -jar IMDbOnTerminal-1.0-STABLE.jar`. It should display a simple help message like this:
```
Usage: gevie [-ghV] [-q=SEARCH TERM] [-qs=SEARCH TERMS...]...
Searching for movies from IMDb.
  -g, --get-posters          Authorize auto-downloading of available movie
                               posters.
  -h, --help                 Show this help message and exit.
  -q, --querry=SEARCH TERM   The movie term you're searching for.
      -qs, --querries=SEARCH TERMS...
                             Upto three movie terms to search for.
  -V, --version              Print version information and exit.
```
10. Now that we have confirmation it works, let's "install" it in a more use-case-friendly way. Proceed to procedure B.

### PROCEDURE B
For those who downloaded the `IMDbOnTerminal-1.0-STABLE.jar`, as well as those who have come from the end of procedure A, proceed from here.
1. Copy the renamed (or downloaded if you downloaded it) jar file into your `/home/{user}` directory, or in the directory where you store you custom scripts. For me, I have a folder called `.custom_scripts` in my `/home/{user}` directory. We are going to edit the `.bashrc file with this information.`
2. Open the `.bashrc` file in your favorite text editor, I will use nano. It is located in the same `/home/{user}` loacation. So if your using the terminal, the command would be `nano ~/.bashrc` or if you're using gedit or kate or any other text editor it would be `{text_editor_name} ~/.bashrc` and hit enter.
3. One the file is opened, navigate to the very bottom an add the following alias:
```
#ALIAS
alias gevie="java -jar ~/{path to where you copied the jar file}/IMDbOnTerminal-1.0-STABLE.jar"
```
4. Save the file and exit. To effect the changes we have to recompile the `.bashrc` file, so either close an re-open your terminal, or run `source ~/.bashrc` and hit enter. Both ways work. So now the install is complete. So now let's see how to use the utility.

## USING THE UTILITY
### EXAMPLE USE CASES
1. Using the single-querry search option.
Type `gevie -q "Monsters"` and hit enter. Result
![Sample Single Querry Search](https://github.com/LunarkX/IMDb-on-terminal/blob/master/gallery/single-search-example.png)
2. Using the multi-querry search option.
Type `gevie -qs "Monsters" "Cars"` and hit enter. Result
![Sample Multi-Querry Search p1](https://github.com/LunarkX/IMDb-on-terminal/blob/master/gallery/search-multiple-p1.png)
![Sample Multi-Querry Search p2](https://github.com/LunarkX/IMDb-on-terminal/blob/master/gallery/multiple-search-p2.png)
3. Using the poster-auto download feature. Also works with the multi-querry option.
Type `gevie -qs "Maleficent" -g`. 
![Download Flag Example](https://github.com/LunarkX/IMDb-on-terminal/blob/master/gallery/image-download-example.png)
Once the job exits, open your `/Downloads` folder. There should be a `Movie Posters` folder with the posters that were available for download.
![Image of Download Poster with Movie Posters Folder Shown](https://github.com/LunarkX/IMDb-on-terminal/blob/master/gallery/image-of-target-folder.png)
Continuation of the above ↑ image
![Image of Malificent Posters](https://github.com/LunarkX/IMDb-on-terminal/blob/master/gallery/maleficent-posters-example.png)

## SAVING SEARCH QUERRIES
By default (cannot be changed unless you edit the code yourself), each of your search querries along with the results is saved in a hidden folder named `.IMDbOnTerminal` in `/home/.IMDbOnTerminal`, as a file with the name of your search querry and the results being inside the file.

## KNOWN ISSUES
1. If the terminal window you're viewing this from is too small, the resulting table can be distorted.
2. If your internet speed is slow, a single search querry can take a long time to complete.
3. If the resulting movie suggestion has no poster, a default image will be downloaded.
