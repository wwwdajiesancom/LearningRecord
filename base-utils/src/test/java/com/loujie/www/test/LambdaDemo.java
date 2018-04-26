package com.loujie.www.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class LambdaDemo {

	@Test
	public void lambda1() throws IOException {

		ServerSocket serverSocket = new ServerSocket(2546);
		System.err.println("----server start");
		Socket client = serverSocket.accept();
		System.err.println(client.getInetAddress());

	}

}
