public interface CalendarOperationInterface {


    //Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void book(String date,String startTime,String endTime,String name, String note);
    public void unbook(String date, String startTime, String endTime);
    public void agenda(String date);
    public void change(String date,String startTime,String option,String newValue);
    public void find(String string);
    public void holiday(String date);
    public void busyDays(String fromDay,String toDay);
    public void findSlot(String fromDate,String hours);
    public void findsSlotWith(String fromDate,String hours,String calendar);
    public void merge(PersonalCalendar calendar);
}
