package com.topicplaces.android.androidsnsapp.AndroidSNS.Users;

public class RESTLogin {

    private String ENDPOINT;

    public RESTLogin(String end) {
        ENDPOINT = end;
    }

    public String login(String users, String passwords){
        return users + passwords;
    }

}
