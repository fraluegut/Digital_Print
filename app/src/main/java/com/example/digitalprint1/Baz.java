
package com.example.digitalprint1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Baz {

    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Baz() {
    }

    /**
     * 
     * @param type
     */
    public Baz(String type) {
        super();
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
