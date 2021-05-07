import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class JavaHttpClientDemo {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        synchronousRequest();
        //asynchronousRequest();
    }


    private static void synchronousRequest() throws IOException, InterruptedException {
        // create a client
        var client = HttpClient.newHttpClient();

        // create a request
        var request = HttpRequest.newBuilder(
            URI.create("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=294&date=03-05-2021")
        ).build();

        // use the client to send the request
        HttpResponse<Supplier<HashMap>> response = client.send(request, new JsonBodyHandler<HashMap>(HashMap.class));


        // the response:
        System.out.println(response);
    }

}