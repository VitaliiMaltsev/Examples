package ru.gs.test.wstest;

import ru.gs.test.SimpleWebService;
import ru.gs.test.SimpleWebServiceService;
import com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe;

public class SimpleWebServiceClient {

    public static void main(String[] args) {
        com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump = true;
//        System.setProperty("javax.xml.accessExternalSchema", "all");

    SimpleWebServiceService service = new SimpleWebServiceService();
    SimpleWebService port = service.getSimpleWebServicePort();
    String str = port.sayHello("World!");
    System.out.println("returned: " + str);


    }
}
