package CREATE;

import All.Post;

public class CreateBoard extends Post {
    public CreateBoard(String endpoint) {
        super(endpoint);
    }

    public String getBoardId(String value)
    {
        return send().jsonPath().getString(value);
    }


}