package TEST;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class AutomationTesting {
    public static String baseUrl = "https://api.trello.com/1/";
    public static String organization_Id;
    public static String member_id;
    public static String board_id;
    public static String list_id;
    public static Map<String, String> parameter;
    static {
        parameter = new HashMap<>();
        parameter.put("key", "f28e107bb9af418de664e029170aa92c");
        parameter.put("token", "ATTA51b4bdcec5dc3c2f34e62b40265d8f4eb242d13681d6039643107b812cfe06ae96E71E79");
    }

    public static void main(String[] args) throws InterruptedException {
        createOrganization();
        Thread.sleep(1000);
        getMemberId();
        Thread.sleep(1000);
        createBoard();
        Thread.sleep(1000);
        getBoardsInAnOrganization();
        Thread.sleep(1000);
        createList();
        Thread.sleep(1000);
        getAllListsOnAbBoard();
        Thread.sleep(1000);
        ArchiveAList();
        Thread.sleep(1000);
        deleteAboard();
        Thread.sleep(1000);
        deleteANOrganization();
    }

    public static void  createOrganization()
    {
        RestAssured.baseURI = baseUrl +"organizations";
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        request.header("Content-Type","application/json");
        request.body("{\"displayName\": \"ApiTesting\",\"desc\": \"create new organization for test Trello Api\"}");
        Response response = request.post();
        response.prettyPrint();
        JsonPath path = response.jsonPath();
        organization_Id = path.getString("id");
        Assert.assertEquals(response.getStatusCode(),200);

    }
    public static void getMemberId()
    {
        RestAssured.baseURI = baseUrl + "members/me";
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.get();
        response.prettyPrint();
        JsonPath path = response.jsonPath();
        member_id = path.getString("id");
        System.out.println(member_id);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

    }
    public static void createBoard()
    {
        RestAssured.baseURI = baseUrl +"boards";
        RequestSpecification request = RestAssured.given();
        parameter.put("idOrganization",organization_Id);
        request.queryParams(parameter);
        request.header("Content-Type","application/json");
        request.body("{\"name\": \"Board\",\"desc\": \"Create new board\",\"descData\":\"Create new Board\"}");
        Response response = request.post();
        response.prettyPrint();
        JsonPath path = response.jsonPath();
        board_id = path.getString("id");
        Assert.assertEquals(response.getStatusCode(),200);

    }

    public static void  getBoardsInAnOrganization()
    {
        RestAssured.baseURI = baseUrl + "organizations/"+organization_Id+"/boards";
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.get();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

    }
    public static void createList()
    {
        RestAssured.baseURI = baseUrl +"lists";
        RequestSpecification request = RestAssured.given();
        parameter.put("name","NewList");
        parameter.put("idBoard",board_id);
        request.queryParams(parameter);
        request.header("Content-Type","application/json");

        Response response = request.post();
        response.prettyPrint();
        JsonPath path = response.jsonPath();
        list_id = path.getString("id");

        Assert.assertEquals(response.getStatusCode(),200);

    }

    public static void getAllListsOnAbBoard()
    {
        RestAssured.baseURI = baseUrl + "boards/"+board_id+"/lists";
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.get();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

    }

    public static void ArchiveAList()
    {
        RestAssured.baseURI = baseUrl + "lists/"+list_id+"/closed";
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.get();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");

    }

    public static void deleteAboard()
    {
        RestAssured.baseURI = baseUrl + "boards/"+board_id;
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.delete();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);

    }

    public static void  deleteANOrganization()
    {
        RestAssured.baseURI = baseUrl + "organizations/"+organization_Id;
        RequestSpecification request = RestAssured.given();
        request.queryParams(parameter);
        Response response = request.delete();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);

    }

}
