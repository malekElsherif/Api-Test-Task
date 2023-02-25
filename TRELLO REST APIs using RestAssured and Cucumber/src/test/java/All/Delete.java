package All;

import io.restassured.response.Response;

public class Delete extends CreateTrello {

    public Delete(String endpoint) {
        super(endpoint);
    }
    public static Response send()
    {
        return CreateTrello.request.delete();
    }


}

