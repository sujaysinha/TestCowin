import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class BookSchedule
{
    private static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36";
    public static void main(String[] args) throws Exception
    {
        ArrayList<String> arr= new ArrayList<String>();
        arr.add("91151377379280");
        book(jsonRequest(418264,"24e647d2-b906-4aef-9eb8-01d28ad5583b",arr,"04:00PM-05:00PM","1","captcha"));
    }
    public static  void book(String request)
    {
        try
        {
            URL url = new URL("https://cdn-api.co-vin.in/api/v2/appointment/schedule");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String bearer = System.getProperty("bearer");
            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("authorization", bearer);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
          /*  try(OutputStream os = connection.getOutputStream()) {
                byte[] input = request.getBytes("utf-8");
                os.write(input, 0, input.length);
            }*/
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(request);
            wr.flush();
            wr.close();


            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            HashMap<Object, Object> result = mapper.readValue(responseStream, HashMap.class);
            System.out.println(result);
            int responseCode = connection.getResponseCode();
            if(responseCode==200)
                System.exit(0);


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    public static String jsonRequest(Integer centerId, String sessionId, ArrayList<String> benefeciaries, String slot, String dose, String captcha) throws Exception
    {
        JSONObject json = new JSONObject();
        String benefic="[";
        int counter = 0;
        for(String benef:benefeciaries)
        {
            counter++;
            benefic += "\"" + benef+"\"";
            if(counter!=benefeciaries.size() && benefeciaries.size()>1 )
                benefic +=",";
        }
        benefic+="]";
        json.put("center_id", centerId);
        json.put("beneficiaries", benefeciaries);
        json.put("session_id", sessionId);
        json.put("slot",slot);
        json.put("dose",Integer.parseInt(dose));
        json.put("captcha",captcha);
        System.out.println("Request-->"+json);
        return json.toString();
    }

    public static String request(String centerId, String sessionId, ArrayList<String> benefeciaries, String slot, String dose)
    {
        String benefic="";
        int counter = 0;
        for(String benef:benefeciaries)
        {
            counter++;
            benefic += "\"" + benef+"\"";
            if(counter!=benefeciaries.size() && benefeciaries.size()>1 )
                benefic +=",";
        }
        String request = "{center_id\":"+centerId+",\"session_id\":\""+sessionId+"\",\"beneficiaries\":["+benefic+"],\"slot\":\""+slot+"\",\"dose\":"+dose+"}";
        return request;
    }
}
