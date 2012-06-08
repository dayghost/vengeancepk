package server.model.players.packets;

import server.Server;
import server.model.items.UseItem;
import server.model.players.Client;
import server.model.players.PacketType;


public class ItemOnPlayer implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
            
            int itemSlot = c.getInStream().readUnsignedWordBigEndian();
            int itemId = c.getInStream().readUnsignedWord();
            
	}
}