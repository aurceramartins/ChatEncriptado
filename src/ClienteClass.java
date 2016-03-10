
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
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
            

            
            while (true) {
                InputStream is = clientSocket.getInputStream();

                byte[] mensaje = new byte[200];
                is.read(mensaje);
                System.out.println("Mensaje recibido: " + new String(mensaje));
                Cliente.txtAreaCliente.setText(new String(mensaje));
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

    public static void leerMensajes() throws IOException {
        InputStream is = clientSocket.getInputStream();

        byte[] mensaje = new byte[200];
        is.read(mensaje);
        System.out.println("Mensaje recibido: " + new String(mensaje));
        Cliente.txtAreaCliente.setText(new String(mensaje));

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
