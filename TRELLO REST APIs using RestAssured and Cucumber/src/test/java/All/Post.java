package All;

import io.restassured.response.Response;

public class Post extends CreateTrello {

    public Post(String endpoint) {
        super(endpoint);
    }
    public void addBody(String body)
    {
        request.body(body);
    }

    public static Response send()
    {
        return CreateTrello.request.post();
    }




}
