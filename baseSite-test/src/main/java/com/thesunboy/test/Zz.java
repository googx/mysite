package com.thesunboy.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Zz
{

	public static void main(String[] args) throws Exception
	{
		final Zz ssss = new Zz();
		ssss.server();
	}

	private void client2() throws Exception
	{

		Socket socket = new Socket("localhost", 8888);
		OutputStream outputStream = socket.getOutputStream();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
		File file = new File("F:/Project_Management/work/eye4/安徽省厅城居保/01后台/mbr-bs-web/src/main/java/com/aeye/mbr/bs/action/aop/CustomerValidatorAOP.java");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		String tempString;
		StringBuilder builder = new StringBuilder();
		while ((tempString = reader.readLine()) != null)
		{
			builder.append(tempString);
		}
		System.out.println(builder.toString());
		writer.write(builder.toString());
		writer.flush();
		writer.close();
	}

	private void client() throws Exception
	{
		File file = new File("F:/Project_Management/work/eye4/安徽省厅城居保/01后台/mbr-bs-web/src/main/java/com/aeye/mbr/bs/action/aop/CustomerValidatorAOP.java");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

		String tempString;
		StringBuilder builder = new StringBuilder();
		while ((tempString = reader.readLine()) != null)
		{
			builder.append(tempString);
		}
		System.out.println(builder.toString());

	}

	private void server() throws Exception
	{
		ServerSocket serverSocket = new ServerSocket(8888);

		while (!Thread.currentThread().isInterrupted())
		{
			System.out.println("listen at 8888");
			Socket clientSocket = serverSocket.accept();
			System.out.println("accept : " + clientSocket.getInetAddress().toString() + " port: " + clientSocket.getPort());
			BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String tempString;
			StringBuilder builder = new StringBuilder();
			while ((tempString = reader.readLine()) != null)
			{
				builder.append(tempString);
			}
			System.out.println("server:==> " + builder.toString());
			File file = new File("./testcode/");
			Random random = new Random();
			if(file.exists() == false)
			{
				file.mkdirs();
			}
			if(file.canWrite())
			{
				File writeFile = new File(file, random.nextInt(1000) + "");
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeFile)));
				writer.write(builder.toString().toCharArray());
				writer.flush();
				writer.close();
			}
			clientSocket.close();
		}

		serverSocket.close();

	}

}
