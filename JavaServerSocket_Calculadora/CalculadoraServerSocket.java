import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServerSocket {

	public static void main(String[] args) {
	    
        ServerSocket sock;
		DataOutputStream socketOutput;     	
	    DataInputStream socketInput;
	    BufferedReader socketEntrada;
	    Calculadora calc = new Calculadora();

        try {
        String result="";
        sock = new ServerSocket(9090);
        Socket connectionSocket = sock.accept(); 
	    socketEntrada = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        String operacao = socketEntrada.readLine();
        String oper1 = socketEntrada.readLine();
        String oper2 = socketEntrada.readLine();

            if (operacao == "1") {
                result = ""+ calc.soma(Double.parseDouble(oper1),Double.parseDouble(oper2));
            }else if (operacao == "2") {
                result = ""+ calc.subtrair(Double.parseDouble(oper1),Double.parseDouble(oper2));
            }else if (operacao == "3"){
                result = ""+ calc.multiplicar(Double.parseDouble(oper1),Double.parseDouble(oper2));
            }else if (operacao == "4") {
                result = ""+ calc.dividir(Double.parseDouble(oper1),Double.parseDouble(oper2));
            }

            socketOutput= new DataOutputStream(connectionSocket.getOutputStream());     	
	        socketOutput.writeBytes(result+ '\n');
	        System.out.println (result);	           
	        socketOutput.flush();
	        socketOutput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


	}

}
