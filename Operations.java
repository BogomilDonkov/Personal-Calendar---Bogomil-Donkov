package CommandLineInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Operations<T extends FileParser> {
    //Members~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private ArrayList<Object> fileContent;
    private String currentlyFilePath=null;
    private T fileParser;

    //Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Operations(T fileParser) {
        this.fileParser = fileParser;
    }

    //Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public boolean open(String fileDirectory){
        if(currentlyFilePath !=null)
           System.out.println("There is currently opened file:"+currentlyFilePath);

        try {
            fileContent = fileParser.readFile(fileDirectory);
            System.out.println("File successfully opened:"+fileDirectory);

            if(fileContent==null)
                System.out.println("File is empty");

            currentlyFilePath=fileDirectory;
            return true;

        }
        catch (IOException e) {
            System.out.println("File not found.\nCreating new file "+fileDirectory);
            fileParser.createFileIfNotExist(fileDirectory);
            return open(fileDirectory);
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean close(){
        if(currentlyFilePath==null) {
            System.out.println("There is no currently opened file at the moment.");
            return false;
        }

        fileContent =null;
        System.out.println("File successfully closed "+currentlyFilePath);
        currentlyFilePath=null;
        return true;
    }

    public boolean save(){
        if(currentlyFilePath==null) {
            System.out.println("There is no currently opened file at the moment.");
            return false;
        }

        if(!fileParser.writeFile(currentlyFilePath, fileContent))
        {
            System.out.println("File cannot be saved "+currentlyFilePath);
            return false;
        }

        System.out.println("File successfully saved "+currentlyFilePath);
        return true;
    }

    public boolean saveAs(String newFileDirectory){
        if(currentlyFilePath==null) {
            System.out.println("There is no currently opened file at the moment.");
            return false;
        }

        //FileCalendarParser xmlCalendarParser=new FileCalendarParser();

        if(!fileParser.writeFile(newFileDirectory, fileContent)||!fileParser.deleteFile(currentlyFilePath))
        {
            System.out.println("File cannot be saved as "+newFileDirectory);
            return false;
        }

        return true;
    }

    public String help(){
        return """             
                \tDefault commands:                                     Description:\s
                \t\t\topen <file directory>                                 opens <file>\s
                \t\t\tclose                                                 closes currently opened file\s
                \t\t\tsave                                                  saves the currently open file\s
                \t\t\tsaveas <file directory>                               saves the currently open file in <file>
                \t\t\thelp                                                  prints this information\s
                \t\t\texit                                                  exists the program\s
                """;
    }

    public void exit(){
        System.exit(0);
    }
}
