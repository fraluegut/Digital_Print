
package com.example.digitalprint1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Foo {

    @SerializedName("type")
    @Expose
    private String type;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Foo() {
    }

    /**
     * 
     * @param type
     */
    public Foo(String type) {
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
