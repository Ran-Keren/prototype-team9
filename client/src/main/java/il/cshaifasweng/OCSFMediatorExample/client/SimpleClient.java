package il.cshaifasweng.OCSFMediatorExample.client;

import java.util.*;
import il.cshaifasweng.OCSFMediatorExample.entities.MenuItem;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

import javax.swing.event.MenuEvent;
import java.io.IOException;


public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	private SimpleClient(String host, int port) {

		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {

		if (msg.getClass().equals(Warning.class)) {
			String message = msg.toString();
			System.out.println(message);
			EventBus.getDefault().post(new WarningEvent((Warning) msg));
		}
		else if(msg instanceof Map)
		{
			Map message = (Map) msg;
			if(message.containsKey("menu sent"))
			{
				System.out.println("menu received");
			}
			else if (message.containsKey("updated dish"))
			{
				System.out.println("dish updated");
			}
		}

	}
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}
	public void updateMenu(String ItemId,String price) throws IOException {
		client.sendToServer("#update,"+ItemId+","+price);
		System.out.println("sent update to server");
	}
	public void displayMenu() throws IOException {
		client.sendToServer("#display menu");
	}

}
