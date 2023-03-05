package PersonalCalendar;

import CommandLineInterface.FileParser;
import PersonalCalendar.Exceptions.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


public class CalendarXMLParser extends FileParser  {
    //Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public ArrayList<Object> readFile(String path) throws IOException, CalendarDateException, CalendarTimeException {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        ArrayList<Object> calendarEvents= new ArrayList<>();
        try {
            DocumentBuilder builder= factory.newDocumentBuilder();

            Document document=builder.parse(path);
            document.getDocumentElement().normalize();

            NodeList eventList= document.getElementsByTagName("event");

            for(int i=0;i<eventList.getLength();i++){
                Node event = eventList.item(i);
                if(event.getNodeType()==Node.ELEMENT_NODE){
                    Element eventElement=(Element)event;

                    String name= eventElement.getAttribute("name");
                    String date= eventElement.getAttribute("date");
                    String startTime= eventElement.getAttribute("startTime");
                    String endTime= eventElement.getAttribute("endTime");
                    String note=eventElement.getAttribute("note");

                    Object content= new CalendarEvent(name,date,startTime,endTime,note);

                    calendarEvents.add(content);
                }
            }
            return calendarEvents;

        } catch (ParserConfigurationException | SAXException e) {
            throw new IOException("File is unreachable.");
        }
    }

    @Override
    public boolean writeFile(String path, ArrayList<Object> content) {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder= factory.newDocumentBuilder();

            Document document=builder.parse(path);
            document.getDocumentElement().normalize();

            for (Object value : content) {
                Element element=document.createElement("event");

                CalendarEvent calendarEvent= (CalendarEvent) value;
                String name = calendarEvent.getName();
                String date = String.valueOf(calendarEvent.getDate());
                String startTime = String.valueOf(calendarEvent.getStartTime());
                String endTime = String.valueOf(calendarEvent.getEndTime());
                String note = calendarEvent.getNote();

                element.setAttribute("name", name);
                element.setAttribute("date", date);
                element.setAttribute("startTime", startTime);
                element.setAttribute("endTime", endTime);
                element.setAttribute("note", note);
            }

            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            return false;
        }
    }

}
