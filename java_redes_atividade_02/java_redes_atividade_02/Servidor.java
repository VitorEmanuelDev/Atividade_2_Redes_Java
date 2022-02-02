package java_redes_atividade_02;


import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    
    private static Socket socket;
    private static ServerSocket server;
    private static ObjectInputStream entrada;
    
    private static String resposta;
    
    public static void main(String[] args) {
   
        try {
            
            server = new ServerSocket(50000);
            
            socket = server.accept();
            
            entrada = new ObjectInputStream(socket.getInputStream());
            
            Pessoa pessoa = (Pessoa) entrada.readObject(); 
            
            System.out.println("Nome: " + pessoa.getNome() + " Idade: " + pessoa.getIdade());
            
            Servidor.getResposta();
            
            socket.close();
            
        } catch(Exception e) {
            
        }
        
    }

	public static String getResposta() {
		return resposta;
	}

	public static void setResposta(String resposta) {
		Servidor.resposta = resposta;
	}
    
}
