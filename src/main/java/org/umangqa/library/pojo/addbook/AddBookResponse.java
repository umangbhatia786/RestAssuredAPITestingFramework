package org.umangqa.library.pojo.addbook;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookResponse {

    @JsonProperty("Msg")
    private String msg;

    @JsonProperty("ID")
    private String id;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }
}
