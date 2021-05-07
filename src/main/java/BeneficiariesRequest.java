import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class BeneficiariesRequest
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            URL url = new URL("https://cdn-api.co-vin.in/api/v2/appointment/beneficiaries");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            System.setProperty("bearer","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiIxYjIzZjZlNS1kMjU0LTRhMjQtOWM4Mi1mYWM1ZDMyYzU5MWQiLCJ1c2VyX2lkIjoiMWIyM2Y2ZTUtZDI1NC00YTI0LTljODItZmFjNWQzMmM1OTFkIiwidXNlcl90eXBlIjoiQkVORUZJQ0lBUlkiLCJtb2JpbGVfbnVtYmVyIjo5NDcwMzcwODMyLCJiZW5lZmljaWFyeV9yZWZlcmVuY2VfaWQiOjI0Mjk0NTU1NTMxNDEwLCJ1YSI6Ik1vemlsbGEvNS4wIChNYWNpbnRvc2g7IEludGVsIE1hYyBPUyBYIDEwXzE1XzcpIEFwcGxlV2ViS2l0LzUzNy4zNiAoS0hUTUwsIGxpa2UgR2Vja28pIENocm9tZS85MC4wLjQ0MzAuOTMgU2FmYXJpLzUzNy4zNiIsImRhdGVfbW9kaWZpZWQiOiIyMDIxLTA1LTA0VDEzOjMxOjAzLjAyNloiLCJpYXQiOjE2MjAxMzUwNjMsImV4cCI6MTYyMDEzNTk2M30.uJHMcaDtwrxqIEC5oqv566Kp0Jw1WKSPNIZfCUxsngM");
            String bearer = System.getProperty("bearer");
            // Now it's "open", we can set the request method, headers etc.
            connection.setRequestProperty("accept", "application/json");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("authorization", bearer);
            InputStream responseStream = connection.getInputStream();

            // Manually converting the response body InputStream to APOD using Jackson
            ObjectMapper mapper = new ObjectMapper();
            HashMap<Object, Object> beneficiaries = mapper.readValue(responseStream, HashMap.class);
            System.out.println(beneficiaries);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
