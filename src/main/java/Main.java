import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Main
{
    public static void main(String[] args) throws Exception
    {
          String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36";

        Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
        int count = 0;
        // Create a neat value object to hold the URL
        String ur = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/calendarByDistrict?district_id=294&date=19-05-2021";
        ur ="https://cdn-api.co-vin.in/api/v2/appointment/sessions/calendarByPin?pincode=370001&date=27-05-2021";//Bhuj
        // ur = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/calendarByPin?pincode=110003&date=05-05-2021";

        URL url = new URL(ur);
        ArrayList<String> arr = new ArrayList<String>();

        arr.add("60157069371430");
      //  arr.add("41488098483320");
     //   arr.add("59636319828470");
     //   arr.add("60816660844910");
        //Rahul  110003
        // arr.add("43637401223180");
        File file = new File("bearer.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        String bearer;
        bearer = br.readLine() ;
        System.out.println(bearer);
        System.setProperty("bearer", bearer);
        // Open a connection(?) on the URL(??) and cast the response(???)
        while (true)
        {
            try
            {
                //System.out.println("New Hit");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // Now it's "open", we can set the request method, headers etc.
                connection.setRequestProperty("accept", "application/json");
                connection.setRequestProperty("User-Agent", USER_AGENT);
                connection.setRequestProperty("authorization", bearer);

                // This line makes the request
                InputStream responseStream = connection.getInputStream();

                // Manually converting the response body InputStream to APOD using Jackson
                ObjectMapper mapper = new ObjectMapper();
                HashMap<Object, Object> slots = mapper.readValue(responseStream, HashMap.class);
                ArrayList entries = (ArrayList) slots.get("centers");
                ArrayList head = new ArrayList();
                ArrayList tail = new ArrayList();
                for (int i = 0; i < entries.size(); i++)
                {
                    if (i < entries.size() / 2)
                        head.add(entries.get(i));
                    else tail.add(entries.get(i));
                }
                new CheckerThread(head, arr).start();
                new CheckerThread(tail, arr).start();
           /* Date dt = new Date();   // given date
            calendar.setTime(dt);   // assigns calendar to given date
            System.out.println("size :"+entries.size()+" Counter->"+count++ + "  Time=>" +calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)); // gets hour in 24h format
            for (int i = 0; i < entries.size(); i++)
            {
                LinkedHashMap session = (LinkedHashMap) entries.get(i);
                ArrayList sessions = (ArrayList) session.get("sessions");
                for (int j = 0; j < sessions.size(); j++)
                {
                    LinkedHashMap slot = (LinkedHashMap) sessions.get(j);
                    try
                    {
                        Integer capacity = (Integer) slot.get("available_capacity");
                        Integer age = (Integer) slot.get("min_age_limit");
                        Integer centreId= (Integer)session.get("center_id");
                        if (age == 18  && capacity!=0 && centreId!=421758)
                        {
                            System.out.println(((LinkedHashMap)entries.get(i)).get("name")+"  age=> "+age+"   capacity=> "+capacity);

                            String sessionId = (String)slot.get("session_id");
                            String date = (String)slot.get("date");
                            BookSchedule.book(BookSchedule.jsonRequest(centreId,sessionId,arr,date,"1"));
                            Thread t =  new AudioPlay();
                           // t.start();
                        }
                    }
                    catch (Exception e)
                    {
                    }
                }

            }*/
            }catch (Exception e){
                System.out.println(e.getMessage());
                Thread.sleep(2500);
            }
            Thread.sleep(2500);
        }

    }
}
