
package com.example.digitalprint1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bar {

    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Bar() {
    }

    /**
     * 
     * @param type
     */
    public Bar(String type) {
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
