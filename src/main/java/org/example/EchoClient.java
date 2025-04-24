package org.example;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        try {
            NioSocketConnector connector = new NioSocketConnector();
            connector.getFilterChain().addLast("logger", new LoggingFilter());
            connector.getFilterChain().addLast("codec",
                    new ProtocolCodecFilter(new TextLineCodecFactory(StandardCharsets.UTF_8)));

            connector.setHandler(new IoHandlerAdapter() {
                @Override
                public void messageReceived(IoSession session, Object message) {
                    System.out.println("Server: " + message);
                }

                @Override
                public void exceptionCaught(IoSession session, Throwable cause) {
                    System.err.println("Error: " + cause.getMessage());
                    cause.printStackTrace();
                }
            });

            ConnectFuture future = connector.connect(new InetSocketAddress("localhost", 9123));
            future.awaitUninterruptibly();

            if (!future.isConnected()) {
                System.err.println("Failed to connect to server.");
                return;
            }

            IoSession session = future.getSession();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Connected. Type messages to send. Type 'bye' to exit.");

            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();
                session.write(input);

                if ("bye".equalsIgnoreCase(input.trim())) {
                    session.closeNow();
                    connector.dispose();
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("Unexpected error:");
            e.printStackTrace();
        }
    }
}
