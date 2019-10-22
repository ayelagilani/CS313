import java.io.IOException;
import java.net.InetAddress;
import java.net.*;

public class Client2 {

    String hostname = "127.0.0.1";
    int port = 5000;

    private DatagramSocket socket = null;
    private InetAddress IPAddress = null;
    private byte[] buffer = null;



    public Client2() throws IOException, SocketTimeoutException, InterruptedException {
        System.out.println("Client Started");
        IPAddress = InetAddress.getByName(hostname);
        socket = new DatagramSocket();
        buffer = new byte[512];
    }

    public void SendPacket() throws IOException {

        String Second = "220";
        DatagramPacket request2 = new DatagramPacket( Second.getBytes(), Second.getBytes().length, IPAddress, port);
        socket.send(request2);

    }

    public void ClientReceive() throws IOException, ClassNotFoundException {
        DatagramPacket response2 = new DatagramPacket(buffer, buffer.length);
        socket.receive(response2);
        String messageTwo = new String(buffer, 0, response2.getLength());

        System.out.println("Message Received: " + messageTwo);
    }


    public static void main(String[] args){
        try {
            Client2 client = new Client2();
            client.SendPacket();
            client.ClientReceive();
        }catch(Exception e){e.printStackTrace();}
    }


}