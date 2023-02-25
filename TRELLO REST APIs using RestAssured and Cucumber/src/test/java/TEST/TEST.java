package TEST;

import Del.DellBoard;
import Del.DellOrganization;
import CREATE.CreateBoard;
import CREATE.CreateList;
import CREATE.CreateOrganization;
import GET.ArchiveAList;
import GET.GetAllListsOnABoard;
import GET.GetMemberId;
import GET.GetAllBoardsInAnOrganization;
import io.restassured.response.Response;
import All.Delete;
import All.Get;
import All.Post;
import All.CreateTrello;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TEST {
    static Response response;
    public static String member_id;
    public static String organization_Id;
    public static String board_id;
    public static String list_id;

    @Test
    public void test1()
    {
        CreateOrganization createNewOrganization = new CreateOrganization("organizations");
        createNewOrganization.addQueryParameters();
        createNewOrganization.addHeader("Content-Type","application/json");

        createNewOrganization.addBody("{\"displayName\": \"ApiTestFwd\",\"desc\": \"new organization for test Trello Api\"}");
        response = Post.send();
        response.prettyPrint();
        organization_Id = createNewOrganization.getOrganizationId("id");
        System.out.println(" organization id : "+ organization_Id);
        Assert.assertEquals(response.getStatusCode(),200);


    }
    @Test
    public void test2()
    {
        GetMemberId getMemberId = new GetMemberId("members/me");
        getMemberId.addQueryParameters();
        response = Get.send();
        response.prettyPrint();
        member_id = getMemberId.getMemberid("id");
        System.out.println(" member id : " + member_id);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }
    @Test
    public void test3()
    {
        CreateBoard newBoard = new CreateBoard("boards");
        CreateTrello.parameter.put("idOrganization",organization_Id);
        newBoard.addQueryParameters();
        newBoard.addHeader("Content-Type","application/json");
        newBoard.addBody("{\"name\": \"ApiBoard\",\"desc\": \"Create Api board\",\"descData\":\"Create new Board\"}");
        response = newBoard.send();
        response.prettyPrint();
        board_id = newBoard.getBoardId("id");
        System.out.println("board_id : "+ board_id);
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test
    public void test4()
    {
        GetAllBoardsInAnOrganization boards = new GetAllBoardsInAnOrganization("organizations/"+organization_Id+"/boards");
        boards.addQueryParameters();
        response = Get.send();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");


    }
    @Test
    public void test5()
    {
        CreateList list = new CreateList("lists");
        CreateTrello.parameter.put("name","NewList");
        CreateTrello.parameter.put("idBoard",board_id);
        list.addQueryParameters();
        list.addHeader("Content-Type","application/json");
        response = Post.send();
        response.prettyPrint();
        list_id = list.getListId("id");
        System.out.println("list id : " +list_id);
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test
    public void test6()
    {
        GetAllListsOnABoard lists = new GetAllListsOnABoard("boards/"+board_id+"/lists");
        lists.addQueryParameters();
        response = Get.send();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");
    }
    @Test
    public void test7()
    {
        ArchiveAList archiveAList = new ArchiveAList( "lists/"+list_id+"/closed");
        archiveAList.addQueryParameters();
        response = Get.send();
        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");


    }
    @Test
    public void test8()
    {
        DellBoard deleteABoard = new DellBoard("boards/"+board_id);
        deleteABoard.addQueryParameters();
        response = Delete.send();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test
    public void test9()
    {
        DellOrganization deleteAnOrganization = new DellOrganization( "organizations/"+organization_Id);
        deleteAnOrganization.addQueryParameters();
        response = Delete.send();
        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(),200);


    }

}
