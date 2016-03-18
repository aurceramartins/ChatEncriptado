
import com.sun.security.ntlm.Client;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class ClienteClass extends Thread {

    public static Cliente ventana = null;
    public static SSLSocketFactory SocketFactory;
    public static Socket clientSocket;
    public static InetSocketAddress addr;

    public ClienteClass(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            // indicamos dónde están los almacenes de certificados y certificados de confianza            
            System.setProperty("javax.net.ssl.keyStore", "src//serverKey.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "servpass");
            System.setProperty("javax.net.ssl.trustStore", "src//clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");

            //Creamos sockets uno para leer(5554) y otro para escribir(5555)
            System.out.println("Obteniendo factoria de sockets cliente");
            SocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();

            System.out.println("Creando socket cliente");
            clientSocket = SocketFactory.createSocket();

            System.out.println("Estableciendo la conexion");
            addr = new InetSocketAddress("localhost", 5555);
            clientSocket.connect(addr);

            SSLServerSocketFactory serverClientFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            ServerSocket serverSocket = serverClientFactory.createServerSocket();

            InetSocketAddress addr2 = new InetSocketAddress("localhost", 5554);
            serverSocket.bind(addr2);

            System.out.println("Aceptando conexiones");
            SSLSocket newSocket1 = (SSLSocket) serverSocket.accept();
            System.out.println("Conexi�n recibida");

            //Bucle infinito para que el programa este escuchando siempre las llamadas y asi poder leer varios correos
            while (true) {
                InputStream is = newSocket1.getInputStream();

                byte[] mensaje = new byte[200];
                is.read(mensaje);
                System.out.println("Mensaje recibido: " + new String(mensaje));
                String Area = Cliente.txtAreaCliente.getText();
                Cliente.txtAreaCliente.setText(Area + "\n" + "Cliente > " + new String(mensaje));
                Servidor.txtAreaServidor.setText(Area + "\n" + "Cliente > " + new String(mensaje));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//Metodo para escribir

    public static void escribirMensaje(String mensaje) throws IOException {
        OutputStream os = clientSocket.getOutputStream();

        System.out.println("Enviando mensaje");

        os.write(mensaje.getBytes());
        System.out.println("Mensaje enviado");

    }
//Metodo para cerrar conexiones

    public static void terminarChat() throws IOException {
        System.out.println("Cerrando el socket cliente");
        clientSocket.close();

        System.out.println("Terminado");
    }

}
