import java.util.*;

public class CheckerThread extends Thread
{
    ArrayList entries;
    ArrayList<String> arr;
    static String COVISHIELD= "COVISHIELD";

    public CheckerThread(ArrayList entries,ArrayList arr)
    {
        this.entries=entries;
        this.arr=arr;
    }

    public void run()
    {
       // ArrayList<String> arr= new ArrayList<String>();
        Calendar calendar = GregorianCalendar.getInstance();
        Date dt = new Date();   // given date
        calendar.setTime(dt);   // assigns calendar to given date
        System.out.println("size :"+entries.size()+ "  Time=>" +calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND)); // gets hour in 24h format
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
                    String vaccine = (String) slot.get("vaccine");
                    Integer dose1 = (Integer) slot.get("available_capacity_dose1");
                    Integer dose2= (Integer) slot.get("available_capacity_dose2");
                    Integer centreId= (Integer)session.get("center_id");
                    if( !vaccine.equalsIgnoreCase(COVISHIELD) && dose1!=0)
                        System.out.println(((LinkedHashMap)entries.get(i)).get("name")+" Covaxin  Dose1 "+dose1);
                    if (age == 18  && capacity != 0 && dose2 != 0 && !vaccine.equalsIgnoreCase(COVISHIELD))
                    {
                        System.out.println(((LinkedHashMap)entries.get(i)).get("name")+"  age=> "+age+"   capacity=> "+capacity);

                        String sessionId = (String)slot.get("session_id");
                       // String date = (String)slot.get("date");
                        ArrayList<String> slots=  (ArrayList<String>)slot.get("slots");
                        String date = slots.get(slots.size()-1);
                        Thread t =  new AudioPlay();
                         t.start();
                       /* RequestAndOpenCaptcha.createCaptcha( RequestAndOpenCaptcha.generateCaptcha());
                        RequestAndOpenCaptcha.openCaptcha();
                        String captcha = RequestAndOpenCaptcha.readCaptcha();
                        BookSchedule.book(BookSchedule.jsonRequest(centreId,sessionId,arr,date,"1",captcha));*/

                    }
                }
                catch (Exception e)
                {
                }
            }

        }
    }
}
