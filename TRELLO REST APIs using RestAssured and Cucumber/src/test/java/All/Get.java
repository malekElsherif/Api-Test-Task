package All;
import io.restassured.response.Response;

public class Get extends CreateTrello {

    public Get(String endpoint) {
        super(endpoint);
    }
    public static Response send()
    {
       return CreateTrello.request.get();
    }



}
