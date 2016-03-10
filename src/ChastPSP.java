
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import static javafx.scene.input.KeyCode.O;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class ChastPSP extends Thread {

    public static Servidor ventana = null;
    public static SSLSocket newSocket;
    public static SSLServerSocketFactory serverSocketFactory;
    public static ServerSocket serverSocket;
    public static boolean cerrar = false;
    public static boolean infinito = true;

    public ChastPSP(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            System.setProperty("javax.net.ssl.keyStore", "C://Program Files//Java//jre1.8.0_71//bin//serverKey.jks");
            System.setProperty("javax.net.ssl.keyStorePassword", "servpass");
            System.setProperty("javax.net.ssl.trustStore", "C://Program Files//Java//jre1.8.0_71//bin//serverTrustedCerts.jks");
            System.setProperty("javax.net.ssl.trustStorePassword", "clientpass");

            System.out.println("Obteniendo factoria de sockets servidor");
            SSLServerSocketFactory serverSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

            System.out.println("Creando socket servidor");
            ServerSocket serverSocket = serverSocketFactory.createServerSocket();

            System.out.println("Realizando el bind");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            serverSocket.bind(addr);

            System.out.println("Aceptando conexiones");
            SSLSocket newSocket = (SSLSocket) serverSocket.accept();
            System.out.println("Conexi�n recibida");

            while (infinito == true) {
                InputStream is = newSocket.getInputStream();

                byte[] mensaje = new byte[200];
                is.read(mensaje);
                System.out.println("Mensaje recibido: " + new String(mensaje));
                Servidor.txtAreaServidor.setText(new String(mensaje));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void escribirMensajes(String mensaje) throws IOException {
        OutputStream os = newSocket.getOutputStream();

        System.out.println("Enviando mensaje");
        os.write(mensaje.getBytes());
        System.out.println("Mensaje enviado");

    }

    public static void terminarChat() throws IOException {
        System.out.println("Cerrando el nuevo Socket");

        newSocket.close();

        System.out.println("Cerando el socket servidor");

        serverSocket.close();

        System.out.println("Terminado");
    }

    public static void main(String[] args) {
        Servidor ventana = new Servidor();
        ventana.setVisible(true);

    }
}
