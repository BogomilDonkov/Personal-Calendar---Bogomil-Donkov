import java.io.*;


public class FileManager {
    private String fileContent;

    //Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public boolean deleteFile(String path){

        File file=new File(path);
       return file.delete() ;
    }

    public String readFile(String path) {
        StringBuilder builder=new StringBuilder();

        try {

            BufferedReader  reader = new BufferedReader(new FileReader(path));
            System.out.println("Successfully  opened "+path);

            String line;
            while((line=reader.readLine())!=null){
                builder.append(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public boolean writeFile(String path,String []content){

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
            for (String s : content)
                bufferedWriter.write(s);

            bufferedWriter.close();
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public void eraseFileContent(Boolean isEmpty){
        isEmpty= fileContent == null;

        fileContent=null;
    }

    //Accessors~~~~~~~~~~~~~~~~~
    public String getFileContent() {
        return fileContent;
    }
}
