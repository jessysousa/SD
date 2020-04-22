import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
//import das bibliotecas

public class CalculadoraClientHTTP {

	public static void main(String[] args) {
		
		//apenas comentar as linhas de código como foi pedido.
		
	String result=""; 
    try {

       URL url = new URL("https://double-nirvana-273602.appspot.com/?hl=pt-BR"); //cria url absoluta
       HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setReadTimeout(10000); //timeout de leitura
        conn.setConnectTimeout(15000); //timeout para conectar
        conn.setRequestMethod("POST"); //metodo post para alterar os dados
        conn.setDoInput(true);
        conn.setDoOutput(true) ; //envio de solicitação do post

        //ENVIO DOS PARAMETROS
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write("oper1=15&oper15=2&operacao=1"); //1-somar 2-subtrair 3-multiplicar 4-dividir
        writer.flush();
        writer.close();
        os.close();

        int responseCode=conn.getResponseCode(); // salva o código de resposta a requisição em responseCode
        if (responseCode == HttpsURLConnection.HTTP_OK) { //verifica se a requisição foi realizada com sucesso

            //RECBIMENTO DOS PARAMETROS


            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine = null; //cria uma string vazia
            while ((responseLine = br.readLine()) != null) { //loop até a leitura de br retornar null
                response.append(responseLine.trim()); ;//acrescenta a responseLine sem espaços a response
            }
            result = response.toString(); //converte o response para string e salva em result
            System.out.println("Resposta do Servidor PHP="+result); //exibe a resposta do servidor
        }
    } catch (IOException e) { //excecao
        e.printStackTrace(); //exibe a exceção
    }
	}
}
