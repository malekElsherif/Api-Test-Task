package CREATE;

import All.Post;

public class CreateOrganization extends Post {
    public CreateOrganization(String endpoint) {
        super(endpoint);
    }

    public String getOrganizationId(String value)
    {
        return send().jsonPath().getString(value);
    }


}
