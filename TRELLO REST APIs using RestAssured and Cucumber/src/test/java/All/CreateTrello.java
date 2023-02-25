package All;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class CreateTrello {
    public static String baseUrl = "https://api.trello.com/1/";
    public static String endPoint;
    public static RequestSpecification request;
    public static Map<String, String> parameter;

    static {
        parameter = new HashMap<>();
        parameter.put("key", "f28e107bb9af418de664e029170aa92c");
        parameter.put("token", "ATTA51b4bdcec5dc3c2f34e62b40265d8f4eb242d13681d6039643107b812cfe06ae96E71E79");
    }
    public CreateTrello(String endpoint) {
        endPoint = endpoint;
        request = RestAssured.
                given()
                .baseUri(baseUrl)
                .basePath(endPoint);

    }
    public void addHeader(String header ,String value)
    {
        request.header(header,value);
    }
    public void addQueryParameters()
    {
        request.queryParams(parameter);
    }


}
