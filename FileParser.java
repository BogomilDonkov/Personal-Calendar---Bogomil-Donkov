package CommandLineInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public abstract class FileParser {
    public boolean createFileIfNotExist(String path){
        File file=new File(path);
        try {
            return file.createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }
    public boolean deleteFile(String path){
        File file=new File(path);
        return file.delete();
    }
    public abstract ArrayList<Object> readFile(String path)throws Exception;
    public abstract boolean writeFile(String path, ArrayList<Object> content);
}
