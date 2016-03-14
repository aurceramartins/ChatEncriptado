
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
            System.setProperty("javax.net.ssl.keyStore", "C://Program Files//Java//jre1.8.0_71//bin//serverKey.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "servpass");
            System.setProperty("javax.net.ssl.trustStore", "C://Program Files//Java//jre1.8.0_71//bin//clientTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");

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

            while (true) {
                InputStream is = newSocket1.getInputStream();

                byte[] mensaje = new byte[200];
                is.read(mensaje);
                System.out.println("Mensaje recibido: " + new String(mensaje));
                String Area = Cliente.txtAreaCliente.getText();
                Cliente.txtAreaCliente.setText(Area + "\n" + new String(mensaje));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void escribirMensaje(String mensaje) throws IOException {
        OutputStream os = clientSocket.getOutputStream();

        System.out.println("Enviando mensaje");

        os.write(mensaje.getBytes());
        System.out.println("Mensaje enviado");

    }

    public static void terminarChat() throws IOException {
        System.out.println("Cerrando el socket cliente");
        clientSocket.close();

        System.out.println("Terminado");
    }

    public static void main(String[] args) throws IOException {
        Cliente ventana = new Cliente();
        ventana.setVisible(true);
    }
}
