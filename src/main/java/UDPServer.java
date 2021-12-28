import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static int buffsize = 512;
    public static void server(Integer port ) {
        DatagramSocket socket;
        DatagramPacket receive, send;
        try {
            socket = new DatagramSocket(port);
            receive = new DatagramPacket(new byte[buffsize], buffsize);
            while(true) {
                String tmp = new String(receive.getData(), 0 , receive.getLength()).trim().replaceAll(" ", "%20");
                System.out.println("Server received: " + tmp + " from " +
                        receive.getAddress().getHostAddress() + " at port " +
                        socket.getLocalPort());
                if(tmp.equals("bye")) {
                    System.out.println("Server socket closed");
                    socket.close();
                    break;
                }
                String data="test";
                send = new DatagramPacket(data.getBytes(), data.getBytes().length,
                        receive.getAddress(), receive.getPort());
                System.out.println("Server sent back " + data + " to client");
                socket.send(send);
            }
        } catch (IOException e) { System.err.println(e);}
    }
}
