package cn.wsd.learn.nio;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;

public class ChatServer {
	// 默认端口
	private static final int DEFAULT_PORT = 8888;
	private static final String QUIT = "quit";

	// 缓冲区大小
	private static final int BUFFER = 1024;

	private ServerSocketChannel server;
	private Selector selector;
	// 读取 buffer
	private ByteBuffer rBuffer = ByteBuffer.allocate(BUFFER);
	// 写入 buffer
	private ByteBuffer wBuffer = ByteBuffer.allocate(BUFFER);
	private Charset charset = StandardCharsets.UTF_8;
	// 自定义端口
	private int port;

	public ChatServer() {
		this(DEFAULT_PORT);
	}

	public ChatServer(int port) {
		this.port = port;
	}

	public void start() {
		try {
			server = ServerSocketChannel.open();
			server.configureBlocking(false);
			server.socket().bind(new InetSocketAddress(port));

			selector = Selector.open();
			server.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("启动服务器，监听端口：" + port + "...");

			while (true) {
				selector.select();
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				for (SelectionKey key : selectionKeys) {
					handles(key);
				}
				selectionKeys.clear();
				sleep(20);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(selector);
		}
	}

	private boolean readyToQuit(String msg) {
		return QUIT.equalsIgnoreCase(msg);
	}

	private synchronized void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String receive(SocketChannel client) throws IOException {
		// 写模式
		rBuffer.clear();
		while ((client.read(rBuffer)) > 0);
		// 读模式
		rBuffer.flip();
		return String.valueOf(charset.decode(rBuffer));
	}

	private void forwardMessage(SocketChannel client, String fwdMsg) throws IOException {
		for (SelectionKey key : selector.keys()) {
			Channel connectedClient = key.channel();
			if (connectedClient instanceof ServerSocketChannel) {
				continue;
			}
			if (key.isValid() && !client.equals(connectedClient)) {
				// 写模式
				wBuffer.clear();
				wBuffer.put(charset.encode(getClientName(client) + ":" + fwdMsg));
				// 读模式
				wBuffer.flip();
				while (wBuffer.hasRemaining()) {
					((SocketChannel) connectedClient).write(wBuffer);
				}
			}
		}
	}

	private String getClientName(SocketChannel client) {
		return "客户端[" + client.socket().getPort() + "]";
	}

	private void handles(SelectionKey key) throws IOException {
		if (key.isAcceptable()) {
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel client = server.accept();
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
			System.out.println(getClientName(client) + "已连接");
		}
		// READ事件 - 客户端发送了消息
		else if (key.isReadable()) {
			SocketChannel client = (SocketChannel) key.channel();
			String fwdMsg = receive(client);
			if (fwdMsg.isEmpty()) {
				// 客户端异常，不再监听这个事件
				key.cancel();
				// 更新监听事件状态
				selector.wakeup();
			} else {
				forwardMessage(client, fwdMsg);

				if (readyToQuit(fwdMsg)) {
					key.cancel();
					selector.wakeup();
					System.out.println(getClientName(client) + "已断开");
				}
			}
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
		ChatServer server = new ChatServer();
		server.start();
	}
}
