package server.model.players.packets;

import server.model.players.Player;
import server.model.players.PlayerHandler;
import server.model.players.Client;
import server.model.players.PacketType;
import server.Connection;
import server.Config;
import server.util.Misc;

/**
 * Chat
 **/
public class Chat implements PacketType {

        public static boolean newMessage = false;
        public static String lastMessage = null;
        public static String lastUser = null;

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.setChatTextEffects(c.getInStream().readUnsignedByteS());
		c.setChatTextColor(c.getInStream().readUnsignedByteS());
        c.setChatTextSize((byte)(c.packetSize - 2));
        c.inStream.readBytes_reverseA(c.getChatText(), c.getChatTextSize(), 0);
		if (!Connection.isMuted(c)){
                    lastUser = c.playerName;
                    lastMessage = Misc.textUnpack(c.getChatText(), c.getChatTextSize());
                    newMessage = true;
			c.setChatTextUpdateRequired(true);
            }
	}	
}