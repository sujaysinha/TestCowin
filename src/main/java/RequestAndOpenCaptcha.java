import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class RequestAndOpenCaptcha {
    private static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.93 Safari/537.36";
    public static void main(String[] args) throws Exception{
        String test ="Test";
        createCaptcha(test);
        openCaptcha();
    }

    public static void createCaptcha(String captcha) throws Exception
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter("captcha.svg"));
        writer.write(captcha);
        writer.flush();
        writer.close();
    }

    public static String generateCaptcha()
    {
        String captcha="";
        try
        {
            URL url = new URL("https://cdn-api.co-vin.in/api/v2/auth/getRecaptcha");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String bearer = System.getProperty("bearer");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestProperty("authorization", bearer);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes("");
            wr.flush();
            wr.close();
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            HashMap<Object, Object> result = mapper.readValue(responseStream, HashMap.class);
            System.out.println(result);
            captcha=(String)result.get("captcha");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return captcha;
    }

    public static void openCaptcha() throws  Exception
    {
        Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome file:///C:/Users/Sujay/IdeaProjects/TestCowin/captcha.svg"});
    }

    public static String readCaptcha() throws Exception
    {
        String captcha;
        File file = new File("captcha.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        captcha = br.readLine() ;
        return captcha;
    }
}
