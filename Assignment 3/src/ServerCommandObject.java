import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommandObject {

    private ServerSocket server;
    private Socket socket;

    public ServerCommandObject() throws IOException {
        server = new ServerSocket(5000);
        System.out.println("Waiting for client");

        socket = server.accept();

        if(socket.isConnected()){
            System.out.println("Found client");
        }
    }

    public void ServerReceive() throws IOException, ClassNotFoundException, NumberFormatException {

        try {
            ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream());
            Object command = inStream.readObject();
            System.out.println("Received Command: " + command);

            String[] commandInfo = command.toString().split(" ");


            // Process Command
            switch (commandInfo[0]) {
                case "getFirstName":
                    commandInfo[(commandInfo.length)-1] = commandInfo[1];
                    String returnObject = commandInfo[0] + " " + commandInfo[1] + " " + commandInfo[2] + " " + commandInfo[3];
                    Send(returnObject);
                    break;
                case "getLastName":
                    commandInfo[(commandInfo.length)-1] = commandInfo[2];
                    returnObject = commandInfo[0] + " " + commandInfo[1] + " " + commandInfo[2] + " " + commandInfo[3];
                    Send(returnObject);
                    break;
                case "getUpperCase": //returns first name
                    commandInfo[(commandInfo.length)-1] = commandInfo[1].toUpperCase();
                    returnObject = commandInfo[0] + " " + commandInfo[1] + " " + commandInfo[2] + " " + commandInfo[3];
                    Send(returnObject);
                    break;
                case "getFirstLetterCase":
                    String[] field = commandInfo[1].split(commandInfo[1].substring(1));
                    commandInfo[(commandInfo.length)-1] = field[0];
                    returnObject = commandInfo[0] + " " + commandInfo[1] + " " + commandInfo[2] + " " + commandInfo[3];
                    Send(returnObject);
                    break;
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void Send(Object data)
    {
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            outStream.writeObject(data);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            ServerCommandObject server = new ServerCommandObject();

            server.ServerReceive();
        }catch(Exception e){}
    }


}
