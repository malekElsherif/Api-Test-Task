package GET;

import All.Get;


public class GetMemberId extends Get {

    public GetMemberId(String endpoint) {
        super(endpoint);
    }

    public String getMemberid(String value)
    {
        return send().jsonPath().getString(value);
    }

}
