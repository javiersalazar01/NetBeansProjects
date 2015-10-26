/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidormanual2;

import java.io.*;
import java.net.*;
import java.util.*;

public final class webserver2 {

    public static void main(String argv[]) throws Exception {
// Set the port number.
        int port = 9999;

// Establish the listen socket.
        ServerSocket welcomeSocket = new ServerSocket(port);

// Process HTTP service requests in an infinite loop.
        while (true) {
// Listen for a TCP connection request.
            Socket connectionSocket = welcomeSocket.accept();

// Construct an object to process the HTTP request message.
            HttpRequest request = new HttpRequest(connectionSocket);

// Create a new thread to process the request.
            Thread thread = new Thread(request);

// Start the thread.
            thread.start();
        }
    }
}
