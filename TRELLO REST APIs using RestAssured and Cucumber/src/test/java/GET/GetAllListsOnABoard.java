package GET;

import All.Get;

public class GetAllListsOnABoard extends Get {

    public GetAllListsOnABoard(String endpoint) {
        super(endpoint);
    }

    public String getMemberid(String value)
    {
        return send().jsonPath().getString(value);
    }

}