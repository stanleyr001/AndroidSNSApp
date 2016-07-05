package com.topicplaces.android.androidsnsapp.AndroidSNS;

import com.topicplaces.android.androidsnsapp.AndroidSNS.Users.*;

import java.io.IOException;
import java.net.*;

public class AndroidSNS{

    private String ENDPOINT;

    public AndroidSNS(String end){
        ENDPOINT = end;
    }
    //ENDPOINT will be set to http://tse.topicplaces.com/api/2/

    /**
     *
     * Checks that a connection can be made with the endpoint.
     * If not, program stalls until a connection can be reestablished.
     *
     */
    private void ensureConnection() {

        boolean connected = false;

        while ( !connected ) {
            try {
                URLConnection connection = new URL( ENDPOINT ).openConnection();
                connection.connect();

                connected = true;
                //System.out.println("Connection to " + ENDPOINT + " established.");
            } catch ( MalformedURLException e ) {
                throw new IllegalStateException("Bad URL: " + ENDPOINT, e);
            } catch ( IOException e ) {

                System.out.println( "Connection to " + ENDPOINT + " lost, attempting to reconnect..." );

                try {
                    Thread.sleep( 15000 );
                } catch ( InterruptedException s ) {
                    throw new IllegalStateException( "Sleep interrupted.");
                }
            }
        }
    }


    public String acquireKey( String username, String password) {
        ensureConnection();

        RESTLogin logg = new RESTLogin(ENDPOINT);
        return logg.login( username, password );
    }

}
