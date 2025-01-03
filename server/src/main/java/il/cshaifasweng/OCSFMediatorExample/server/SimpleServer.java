package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.*;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.SubscribedClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;


public class SimpleServer extends AbstractServer {
	private static ArrayList<SubscribedClient> SubscribersList = new ArrayList<>();
	private MenuItemsController menuItemsController = new MenuItemsController();

	public SimpleServer(int port) {
		super(port);}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msgString.startsWith("add client")) {
			SubscribedClient connection = new SubscribedClient(client);
			SubscribersList.add(connection);
			try {
				client.sendToClient("client added successfully");
				System.out.println("Client added successfully");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}
		//receives display menu msg from client and returns a map with "menu sent" string and a list of the menu items
		else if (msgString.startsWith("#display menu"))
		{
			System.out.println("Displaying menu");
			List<MenuItem> menuItems=menuItemsController.getMenuItems();//currently returns null :(
			System.out.println("try displaying menu1111");
			Map<String, Object> response = new HashMap<>();
			response.put("message", "menu sent");
			response.put("data",menuItems);
			try {
				client.sendToClient(response);//sent the menu
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//receives update msg from client and returns a map with "updated dish" string and MenuItem
		else if (msgString.startsWith("#update,"))//ASSUMING msg="#update,itemID(int),price(int)"
		{
			String[] parts = msgString.split(",");
			int ItemId = Integer.parseInt(parts[1]);
			int price = Integer.parseInt(parts[2]);
			updateDish(ItemId, price);
			Map<String, Object> response = new HashMap<>();
			response.put("message", "updated dish");
			response.put("data",menuItemsController.getMenuItems().get(ItemId));
			sendToAllClients("updated dish");
			System.out.println("updated Item");
        }
	}
	public void sendToAllClients(String message) {
		try {
			for (SubscribedClient subscribedClient : SubscribersList) {
				subscribedClient.getClient().sendToClient(message);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	private void updateDish(int ItemId, int price) {
		System.out.println("in updateDish");
	}
}
