package io.codeforall.javatars;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Curl {
    public static void main(String[] args) {

        //Getting our website URL in a String and checking if it's absolute (if it starts with the protocol - ex.: "http://...")
        String websiteURLText = checkAbsolute();

        try {
            // Creating our URL variable using URI
            URL websiteURL = new URI(websiteURLText).toURL();

            // If our website has content and if we get an InputStream from it, reads it and prints to console all its content
            if (websiteURL.openConnection().getContentType() != null && websiteURL.getContent() instanceof InputStream) {
                BufferedInputStream in = new BufferedInputStream((InputStream) websiteURL.getContent());
                System.out.println(new String(in.readAllBytes()));
            }

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static String getMessage() {

        Scanner reader = new Scanner(System.in);
        System.out.println("Website to curl: ");
        return reader.nextLine();
    }

    private static String checkAbsolute(){
        String websiteURL = getMessage();

        try {
            if (!new URI(websiteURL).isAbsolute()){
                System.out.println("Not absolute. Write again.");
                websiteURL = checkAbsolute();
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return websiteURL;
    }
}
