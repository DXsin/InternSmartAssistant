package com.example.terence.internsmartassistant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Terence on 9/23/2017.
 */

public class JournalModel {
     String message;
     String in;
     String out;
     String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public JournalModel() {
    }

    public JournalModel(String message, String in, String out) {
        this.message = message;
        this.in = in;
        this.out = out;
      //  this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIn() {
        return in;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "" + "" + message;
    }

    public boolean equals(Object obj) {
        boolean check = false;
        if (obj instanceof JournalModel) {
            JournalModel temp = (JournalModel) obj;
            if (this.getMessage().equals(temp.getMessage()))
                check = true;
        }
        return check;
    }
    public Map<String,Object> toMap(){
        HashMap<String,Object>result = new HashMap<>();
        result.put("message",message);
        result.put("in",in);
        result.put("out",out);
        result.put("key",key);

        return result;
    }
}
