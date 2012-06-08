package server.model.players.packets;

import server.Config;
import server.Server;
import server.model.players.Client;
import server.model.players.PacketType;
import server.util.Misc;

/**
 * Drop Item
 **/
public class DropItem implements PacketType {
    
    public int cracker = 962; //christmas cracker
    public int[] partyhats = {1038, 1040, 1042, 1044, 1046, 1048 }; //array of party hats
    
    @Override
    public void processPacket(Client c, int packetType, int packetSize) {
        int itemId = c.getInStream().readUnsignedWordA();
        c.getInStream().readUnsignedByte();
        c.getInStream().readUnsignedByte();
        int slot = c.getInStream().readUnsignedWordA();
        if (itemId == 13227) {
            c.sendMessage("I don't think you want to do that...");
            return;
        }
        if (c.inTrade) {
            c.sendMessage("You cannot drop items in the trade screen.");
            return;
        }
        if(c.arenas()) {
            c.sendMessage("You can't drop items inside the arena!");
            return;
        }
        if(!c.getItems().playerHasItem(itemId,1,slot)) {
            return;
        }
        if (System.currentTimeMillis() - c.alchDelay < 1800)
            return;
        boolean droppable = true;
        for (int i : Config.UNDROPPABLE_ITEMS) {
            if (i == itemId) {
                droppable = false;
                break;
            }
        }
        if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
            if(droppable) {
                if (c.underAttackBy > 0) {
                    if (c.getShops().getItemShopValue(itemId) > 1000) {
                        c.sendMessage("You may not drop items worth more than 1,000gp while in combat.");
                        return;
                    }
                }
                if (itemId == cracker) {
                    int size = partyhats.length;
                    int ran = Misc.random(size);
                    c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
                    c.sendMessage("You drop the cracker and it opens!");
                    Server.itemHandler.createGroundItem(c, partyhats[ran], c.getX(), c.getY(), c.playerItemsN[slot], c.getId());
                    return;
                }
                
                Server.itemHandler.createGroundItem(c, itemId, c.getX(), c.getY(), c.playerItemsN[slot], c.getId());
                c.getItems().deleteItem(itemId, slot, c.playerItemsN[slot]);
            } else {
                c.sendMessage("This item cannot be dropped.");
            }
        }
        if(c.playerItemsN[slot] != 0 && itemId != -1 && c.playerItems[slot] == itemId + 1) {
            if(!c.getItems().playerHasItem(itemId,1,slot)) {
                c.sendMessage("Stop cheating!");
                return;
            }
        }
    }
}
