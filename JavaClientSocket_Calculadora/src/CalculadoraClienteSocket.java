import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.Inet4Address;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class CalculadoraClientSocket {

	public static void main(String[] args) {
		
		double oper1=10,oper2=20;
		int operacao=1, subtrair=2, multiplicar=3,dividir=4 ; 
		String result="";
		Socket clientSocket = null;
		DataOutputStream socketSaidaServer = null;
		BufferedReader mensagem = null;
        try {

            clientSocket = new Socket(Inet4Address.getLocalHost().getHostAddress(), 9090);
            socketSaidaServer = new DataOutputStream(clientSocket.getOutputStream());
            messagem = new BufferedReader
            (new InputStreamReader(clientSocket.getInputStream()));
            
            //Enviando os dados
            socketSaidaServer.writeBytes(operacao+"\n");
            socketSaidaServer.writeBytes(oper1+ "\n");
            socketSaidaServer.writeBytes( oper2+ "\n");
            socketSaidaServer.flush();

            //Recebendo a resposta
            BufferedReader messageFromServer = new BufferedReader
                    (new InputStreamReader(clientSocket.getInputStream()));
            result=messageFromServer.readLine();
            
            System.out.println("resultado="+result);
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


	}

}
