package server.model.players.packets;

import server.Config;
import server.model.players.Client;
import server.model.players.PacketType;

/**
 * Trading
 */
public class Trade implements PacketType {
	public boolean inTrade;
	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int tradeId = c.getInStream().readSignedWordBigEndian();
		c.getPA().resetFollow();
		
		if (c.inTrade) {
		c.sendMessage("You cannot walk while in a trade.");
		return;
		}
		if(c.arenas()) {
			c.sendMessage("You can't trade inside the arena!");
			return;
		}

		if(c.inWild()) {
			c.sendMessage("You can't trade in wild!");
			return;
		}
		
		if(c.playerRights == 2 && !Config.ADMIN_CAN_TRADE) {
			c.sendMessage("Trading as an admin has been disabled.");
			return;
		}
		if(c.playerName.equalsIgnoreCase("k 2") || c.playerName.equalsIgnoreCase("x x fbi x x") || c.playerName.equalsIgnoreCase("hold alt f4")) {
			c.sendMessage("Your trading has been disabled.");
			return;
		}
		if (tradeId != c.playerId)
			c.getTradeAndDuel().requestTrade(tradeId);
	}
		
}
