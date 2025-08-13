//package main.java;

import java.io.*;
import java.net.*;

public class IA_Translator {
  public static void main(String[] args) throws IOException {
    int port = 8080; // porta su cui il server ascolta
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("Server avviato sulla porta " + port);

    while (true) {
      try (Socket clientSocket = serverSocket.accept()) {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

        // Leggi la prima linea della richiesta (es. "GET / HTTP/1.1")
        String requestLine = in.readLine();
        System.out.println("Richiesta ricevuta: " + requestLine);

        // Scarta le altre linee della richiesta HTTP
        while (in.ready()) {
          in.readLine();
        }

        // Costruisci risposta HTTP con pagina HTML semplice
        String htmlResponse = "<html><body><h1>Ciao dal server Java!</h1><p>Questa è una pagina HTML semplice.</p></body></html>";

        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-Type: text/html\r\n");
        out.write("Content-Length: " + htmlResponse.length() + "\r\n");
        out.write("\r\n");
        out.write(htmlResponse);
        out.flush();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

