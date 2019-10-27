import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientCommandObject {

    private Socket socket = null;

    public ClientCommandObject() throws IOException {
        System.out.println("Client Started");
        socket = new Socket("127.0.0.1", 5000);
    }

    public void SendCommandObject(Object cmd) throws IOException{
        ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
        outStream.writeObject(cmd);
    }

    public void ClientReceive() throws IOException, ClassNotFoundException {
        ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
        Object serverResult =  inStream.readObject();
        System.out.println("Message Received: " + serverResult);
    }


    public static void main(String[] args){
        try {
            ClientCommandObject client = new ClientCommandObject();
            //client.SendCommandObject("getFirstName John Smith :result");
            //client.SendCommandObject("getLastName John Smith :result");
            //client.SendCommandObject("getUpperCase John Smith :result");
            client.SendCommandObject("getFirstLetterCase John Smith :result");
            client.ClientReceive();

        } catch(Exception e){e.printStackTrace();}
    }


}
