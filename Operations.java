public class Operations implements CalendarOperationInterface {

    //Overrides~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void book(String date, String startTime, String endTime, String name, String note) {

    }

    @Override
    public void unbook(String date, String startTime, String endTime) {

    }

    @Override
    public void agenda(String date) {

    }

    @Override
    public void change(String date, String startTime, String option, String newValue) {

    }

    @Override
    public void find(String string) {

    }

    @Override
    public void holiday(String date) {

    }

    @Override
    public void busyDays(String fromDay, String toDay) {

    }

    @Override
    public void findSlot(String fromDate, String hours) {

    }

    @Override
    public void findsSlotWith(String fromDate, String hours, String calendar) {

    }

    @Override
    public void merge(PersonalCalendar calendar) {

    }
    //Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public String open(String fileDirectory){
        FileManager fileManager=new FileManager();
        return fileManager.readFile(fileDirectory);
    }

    public String help(){
        return  "\t\t\topen <file directory>       opens <file> " +
                "\t\t\tclose                       closes currently opened file " +
                "\t\t\tsave                        saves the currently open file " +
                "\t\t\tsaveas <file directory>     saves the currently open file in <file>" +
                "\t\t\thelp                        prints this information " +
                "\t\t\texit                        exists the program ";
    }

    public void close(Boolean isEmpty){
           FileManager fileManager=new FileManager();
           fileManager.eraseFileContent(isEmpty);
    }

    public void exit(int status){
        System.exit(status);
    }

    public boolean save(String fileDirectory,String []content){
        FileManager fileManager=new FileManager();
        return  fileManager.writeFile(fileDirectory,content);
    }

    public boolean saveAs(String oldFileDirectory,String newFileDirectory,String []content) {
        FileManager fileManager = new FileManager();

        if(fileManager.deleteFile(oldFileDirectory))
            return false;

        fileManager.writeFile(newFileDirectory, content);

        return true;
    }



    //Accessors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

}
