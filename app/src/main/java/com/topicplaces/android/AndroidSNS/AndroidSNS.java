package com.topicplaces.android.AndroidSNS;

import com.topicplaces.android.AndroidSNS.Topics.TopicCreator;
import com.topicplaces.android.AndroidSNS.Users.*;

import java.io.IOException;
import java.net.*;

public class AndroidSNS{

    private String ENDPOINT;

    /**
     *
     * Constructor for the SNSController.
     *
     * @param end The endpoint. ex. http://tse.topicplaces.com/api/2/
     */
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


    /**
     *
     * Uses an HttpURLConect to obtain an authentication key from the endpoint.
     *
     * @param username The username you'll be obtaining an authkey for.
     * @param password Password for the given username.
     * @return The authentication key for the endpoint.
     */

    public String acquireKey( String username, String password) {
        ensureConnection();

        RESTLogin logg = new RESTLogin(ENDPOINT);
        return logg.login( username, password );
    }

    /**
     *
     * Creates a new public topic.
     *
     * @param title The title/name of the new Public Topic
     * @param authkey The authentication key. See "acquireKey()"
     * @return The ID code of the newly created topic (in format "t-[id]")
     */

    public String newPublicTopic(String title, String authkey)
    {
        ensureConnection();

        TopicCreator tc = new TopicCreator( ENDPOINT );
        String topicID = tc.createTopic(title, false, authkey);

        if ( topicID.equals( "" ) )
        {
            System.err.println( "Unable to create new topic" );
            return "";
        }

        return topicID;
    }






















}
