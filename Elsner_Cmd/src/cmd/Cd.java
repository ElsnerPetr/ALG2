package cmd;

import java.io.File;

/**
 *
 * @author CryHeroCZ
 */
public class Cd extends Command{  
//mění adresář, jedna možnost : execute místo aby to vracelo jen string, tak aby to i měnilo no
    //druhá -- execute nebude vracet string ale objekt kterýbude vracet "file" a "text", bude mít gettere kde bude result text nebo lol wtf pokuř mi
//druhej úkol udělat obrázek diagram a nahrát na git (jak to je propojené)

@Override
    public Location execute(File actualDir) {
        File file;
        if (params.length == 1) {
            return new Location(actualDir, "Zadejte parametr\n");
        }
        if (params.length > 2) {
            return new Location(actualDir, "Nevalidni prikaz\n");
        }
        if(params[1].equals("..")){
            file = new File(actualDir.getParent());
        } else{
            try{
            file = new File(actualDir.getAbsolutePath() + "\\" + params[1]);
            } catch(Exception e){
                throw new RuntimeException("Slozka neexistuje");
            }
        }
        
        return new Location(file,"");
    }
}
