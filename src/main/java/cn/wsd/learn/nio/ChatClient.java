package cn.wsd.learn.nio;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class ChatClient {

	private static final String DEFAULT_SERVER_HOST = "127.0.0.1";
	private static final int DEFAULT_SERVER_PORT = 8888;
	public static final int BUFFER = 1024;
	public static final String QUIT = "quit";

	private String host;
	private int port;
	private SocketChannel client;
	private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);
	private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
	private Selector selector;
	private Charset charset = StandardCharsets.UTF_8;

	public ChatClient() {
		this(DEFAULT_SERVER_HOST, DEFAULT_SERVER_PORT);
	}

	public ChatClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public boolean readyToQuit(String msg) {
		return QUIT.equalsIgnoreCase(msg);
	}

	public void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		try {
			client = SocketChannel.open();
			client.configureBlocking(false);

			selector = Selector.open();
			client.register(selector, SelectionKey.OP_CONNECT);
			client.connect(new InetSocketAddress(host, port));

			while (true) {
				selector.select();

				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				for (SelectionKey key : selectionKeys) {
					handles(key);
				}
				selectionKeys.clear();	// 这一步很关键
				sleep(50);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(selector);
		}
	}

	private void handles(SelectionKey key) throws IOException {
		if (key.isConnectable()) {
			SocketChannel client = (SocketChannel) key.channel();
			if (client.isConnectionPending()) {
				client.finishConnect();
				new Thread(new UserInputHandler(this)).start();
			}
			client.register(selector, SelectionKey.OP_READ);
		}
		else if (key.isReadable()) {
			SocketChannel client = (SocketChannel) key.channel();
			String msg = receive(client);
			if (msg.isEmpty()) {
				close(selector);
			} else {
				System.out.println(msg);
			}
		}
	}

	private String receive(SocketChannel client) throws IOException {
		rBuffer.clear();
		while (client.read(rBuffer) > 0);
		rBuffer.flip();
		return String.valueOf(charset.decode(rBuffer));
	}

	public void send(String msg) throws IOException {
		if (msg.isEmpty()) {
			return;
		}
		wBuffer.clear();
		wBuffer.put(charset.encode(msg));

		wBuffer.flip();
		while (wBuffer.hasRemaining()) {
			client.write(wBuffer);
		}

		if (readyToQuit(msg)) {
			close(selector);
		}
	}

	private void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ChatClient client = new ChatClient();
		client.start();
	}
}
