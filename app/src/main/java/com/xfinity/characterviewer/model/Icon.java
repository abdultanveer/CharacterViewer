
package com.xfinity.characterviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Icon contains the image information about a character
 */
public class Icon implements Serializable{


    @SerializedName("URL")
    @Expose
    private String uRL;

    public String getURL() {
        return uRL;
    }


    public void setuRL(String uRL) {
        this.uRL = uRL;
    }
}
