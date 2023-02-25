package CREATE;

import All.Post;

public class CreateList extends Post {
    public CreateList(String endpoint) {
        super(endpoint);
    }

    public String getListId(String value)
    {
        return send().jsonPath().getString(value);
    }


}