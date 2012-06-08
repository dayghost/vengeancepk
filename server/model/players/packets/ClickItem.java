package server.model.players.packets;

import server.model.players.Client;
import server.model.players.PacketType;


/**
 * Clicking an item, bury bone, eat food etc
 **/
public class ClickItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		int junk = c.getInStream().readSignedWordBigEndianA();
		int itemSlot = c.getInStream().readUnsignedWordA();
		int itemId = c.getInStream().readUnsignedWordBigEndian();
		if (itemId != c.playerItems[itemSlot] - 1) {
			return;
			
		}
                if (itemId == 13227) {
                    c.getItems().deleteItem(13227, c.getItems().getItemSlot(13227), 1);
                    c.isDonator = 1;
                    c.sendMessage("You rubbed the Mysterious Lamp A.K.A Donator lamp...");
                    c.sendMessage("You are now a donator! Congratulations! :)");
                }
                if(itemId == 8007) {
                   c.getItems().deleteItem(8007,c.getItems().getItemSlot(8007),1);
                   c.getPA().teleTabTeleport(3213, 3423, 0, "teleTab");
                }
                if(itemId == 8008) {
                   c.getItems().deleteItem(8008,c.getItems().getItemSlot(8008),1);
                   c.getPA().teleTabTeleport(3224, 3218, 0, "teleTab");
                }
              if(itemId == 8009) {
                   c.getItems().deleteItem(8009,c.getItems().getItemSlot(8009),1);
                   c.getPA().teleTabTeleport(2965, 3383, 0, "teleTab");
                }
            if(itemId == 8010) {
                   c.getItems().deleteItem(8010,c.getItems().getItemSlot(8010),1);
                   c.getPA().teleTabTeleport(2757, 3477, 0, "teleTab");
                }
          if(itemId == 8011) {
                   c.getItems().deleteItem(8011,c.getItems().getItemSlot(8011),1);
                   c.getPA().teleTabTeleport(2653, 3283, 0, "teleTab");
                }
          if(itemId == 8012) {
                   c.getItems().deleteItem(8012,c.getItems().getItemSlot(8012),1);
                   c.getPA().teleTabTeleport(2546, 3112, 0, "teleTab");
                }
          if(itemId == 8013) {
                   c.getItems().deleteItem(8013,c.getItems().getItemSlot(8013),1);
                   c.getPA().teleTabTeleport(3086, 3499, 0, "teleTab");
                }
		if(itemId == 4447) {
						for (int i = 0; i < 5; i++) {
					c.playerLevel[0] = 99;
					c.playerLevel[1] = 99;
					c.playerLevel[2] = 99;
					c.playerLevel[3] = 99;
					c.playerLevel[4] = 99;
					c.playerLevel[6] = 99;
					c.playerXP[i] = c.getPA().getXPForLevel(100);
					c.playerXP[6] = c.getPA().getXPForLevel(100);
					c.getPA().refreshSkill(i);
					c.getPA().refreshSkill(6);	
					c.getItems().deleteItem(4447, 1);
					c.logout();
				}
				c.getPA().requestUpdates();
			}
			
		if(itemId == 6796) {
			c.playerLevel[0] = 99;
			c.playerLevel[2] = 99;
			c.playerLevel[3] = 99;
			c.playerLevel[4] = 99;
			c.playerLevel[6] = 99;
			c.playerXP[0] = c.getPA().getXPForLevel(100);
			c.playerXP[2] = c.getPA().getXPForLevel(100);
			c.playerXP[3] = c.getPA().getXPForLevel(100);
			c.playerXP[4] = c.getPA().getXPForLevel(100);
			c.playerXP[6] = c.getPA().getXPForLevel(100);
			c.getPA().refreshSkill(0);
			c.getPA().refreshSkill(2);
			c.getPA().refreshSkill(3);
			c.getPA().refreshSkill(4);
			c.getPA().refreshSkill(6);
			c.getItems().deleteItem(6796, 1);
			c.logout();
			}
			
		if (itemId == 15272) {
		if (System.currentTimeMillis() - c.foodDelay >= 1500 && c.playerLevel[3] > 0) {
			c.getCombat().resetPlayerAttack();
			c.attackTimer += 2;
			c.startAnimation(829);
			c.getItems().deleteItem(15272, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[3])) {
				c.playerLevel[3] += 23;
				if (c.playerLevel[3] > c.getLevelForXP(c.playerXP[3]))
					c.playerLevel[3] = c.getLevelForXP(c.playerXP[3]);
			}
			c.foodDelay = System.currentTimeMillis();
			c.getPA().refreshSkill(3);
			c.sendMessage("You eat the Rocktail.");
		}
 		//c.playerLevel[3] += 10;
		if (c.playerLevel[3] > (c.getLevelForXP(c.playerXP[3])*1.11 + 1)) {
			c.playerLevel[3] = (int)(c.getLevelForXP(c.playerXP[3])*1.11);
		}
		c.getPA().refreshSkill(3);
			return;
		}
		if (itemId == 2528) {
		c.getItems().deleteItem(2528,1);
		c.getPA().showInterface(2808);
		}
		//Begin artifacts by Hirukos
		if (itemId >= 14876 && itemId <= 14892) {
			int a = itemId;
			String YEYAF = "<col=1532693>You Exchanged Your Artifact For</col> ";
			if (a == 14876){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000000);
			c.sendMessage(YEYAF + "<col=1532693>10 million Coins!</col>");
			}
			if (a == 14877){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,2000000);
			c.sendMessage(YEYAF + "<col=1532693>2 million Coins!</col>");
			}
			if (a == 14878){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1500000);
			c.sendMessage(YEYAF + "<col=1532693>1.5 million Coins!</col>");
			}
			if (a == 14879){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,1000000);
			c.sendMessage(YEYAF + "<col=1532693>1 million Coins!</col>");
			}
			if (a == 14880){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,800000);
			c.sendMessage(YEYAF + "<col=1532693>800,000 Coins!</col>");
			}
			if (a == 14881){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,600000);
			c.sendMessage(YEYAF + "<col=1532693>600,000 Coins!</col>");
			}
			if (a == 14882){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,540000);
			c.sendMessage(YEYAF + "<col=1532693>540,000 Coins!</col>");
			}
			if (a == 14883){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,400000);
			c.sendMessage(YEYAF + "<col=1532693>400,000 Coins!</col>");
			}
			if (a == 14884){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,300000);
			c.sendMessage(YEYAF + "<col=1532693>300,000 Coins!</col>");
			}
			if (a == 14885){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,200000);
			c.sendMessage(YEYAF + "<col=1532693>200,000 Coins!</col>");
			}
			if (a == 14886){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,150000);
			c.sendMessage(YEYAF + "<col=1532693>150,000 Coins!</col>");
			}
			if (a == 14887){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,100000);
			c.sendMessage(YEYAF + "<col=1532693>100,000 Coins!</col>");
			}
			if (a == 14888){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,80000);
			c.sendMessage(YEYAF + "<col=1532693>80,000 Coins!</col>");
			}
			if (a == 14889){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,60000);
			c.sendMessage(YEYAF + "<col=1532693>60,000 Coins!</col>");
			}
			if (a == 14890){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,40000);
			c.sendMessage(YEYAF + "<col=1532693>40,000 Coins!</col>");
			}
			if (a == 14891){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,20000);
			c.sendMessage(YEYAF + "<col=1532693>20,000 Coins!</col>");
			} 
			if (a == 14892){
			c.getItems().deleteItem(a,1);
			c.getItems().addItem(995,10000);
			c.sendMessage(YEYAF + "<col=1532693>10,000 Coins!</col>");
			}
			
		}
		//End of artifacts By Hirukos
		
		
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			int a = itemId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().fillPouch(pouch);
			return;
		}
		if (c.getHerblore().isUnidHerb(itemId))
			c.getHerblore().handleHerbClick(itemId);
		if (c.getFood().isFood(itemId))
			c.getFood().eat(itemId,itemSlot);
		//ScriptManager.callFunc("itemClick_"+itemId, c, itemId, itemSlot);
		if (c.getPotions().isPotion(itemId))
			c.getPotions().handlePotion(itemId,itemSlot);
		if (c.getPrayer().isBone(itemId))
			c.getPrayer().buryBone(itemId, itemSlot);
		if (itemId == 952) {
			if(c.inArea(3553, 3301, 3561, 3294)) {
				c.teleTimer = 3;
				c.newLocation = 1;
			} else if(c.inArea(3550, 3287, 3557, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 2;
			} else if(c.inArea(3561, 3292, 3568, 3285)) {
				c.teleTimer = 3;
				c.newLocation = 3;
			} else if(c.inArea(3570, 3302, 3579, 3293)) {
				c.teleTimer = 3;
				c.newLocation = 4;
			} else if(c.inArea(3571, 3285, 3582, 3278)) {
				c.teleTimer = 3;
				c.newLocation = 5;
			} else if(c.inArea(3562, 3279, 3569, 3273)) {
				c.teleTimer = 3;
				c.newLocation = 6;
			} else if(c.inArea(2986, 3370, 3013, 3388)) {
				c.teleTimer = 3;
				c.newLocation = 7;
			}
		}
	}

}
