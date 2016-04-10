package com.polaris.chat;
import java.io.IOException;

import org.lwjgl.glfw.GLFW;

import com.polaris.engine.Application;
import com.polaris.engine.network.Packet;
import com.polaris.engine.render.Window;

public class Server extends Application
{
	
	static
	{
		Packet.addPacket(PacketContent.class);
		Packet.sortPackets();
	}

	public static void main(String[] args) throws IOException
	{
		Server chat = new Server(8888, 4096);
		chat.run();
		System.exit(0);
	}

	public Server(int port, int encryption) throws IOException
	{
		super(port, encryption);
	}
	
	@Override
	protected void init() 
	{
		this.setGui(new ServerGui(this));
	}

	@Override
	public long createWindow()
	{
		Window.setHint(GLFW.GLFW_RESIZABLE, true);
		Window.setHint(GLFW.GLFW_DECORATED, false);
		return Window.createAndCenter(1280, 720, "Chat System", 0);
	}

}
