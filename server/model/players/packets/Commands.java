package server.model.players.packets;

import server.Config;
import server.Connection;
import server.Server;
import server.model.players.PlayerSave;
import server.model.players.Player;
import server.model.players.Client;
import server.model.players.PacketType;
import server.model.players.PlayerHandler;
import server.util.Misc;
import server.util.CheckDonation;


import java.io.*;

/**
 * Commands
 **/
public class Commands implements PacketType
{
    
    private void give1(Client c, int item) {
        c.getItems().addItem(item, 1);
    }
    private void give100(Client c, int item) {
        c.getItems().addItem(item, 100);
    }
    
    @Override
    public void processPacket(Client c, int packetType, int packetSize)
    {
        String playerCommand = c.getInStream().readString();
        if (!playerCommand.startsWith("/"))
        {
            c.getPA().writeCommandLog(playerCommand);
        }
        if (playerCommand.equalsIgnoreCase("reloadannouncements")) {
            Server.loadAnnouncements();
            
        }
        /*if (playerCommand.startsWith("pure") && c.puremaster == 0) {
         * int i = 0;
         * c.getPA().addSkillXP((15000000), 0);
         * c.getPA().addSkillXP((15000000), 2);
         * c.getPA().addSkillXP((15000000), 3);
         * c.getPA().addSkillXP((15000000), 4);
         * c.getPA().addSkillXP((15000000), 6);
         * c.playerXP[3] = c.getPA().getXPForLevel(99)+5;
         * c.playerLevel[3] = c.getPA().getLevelForXP(c.playerXP[3]);
         * c.getPA().refreshSkill(3);
         * c.puremaster = 1;
         * }*/
        
        if (playerCommand.equalsIgnoreCase("receivedonation")) {
            CheckDonation donate = new CheckDonation(c.playerName);
            if (!donate.isReceived()) {
                int itemReceive = donate.receiveItem();
                boolean given = false;
                switch (itemReceive) {
                    case 1: //Torva
                        give1(c, 13362);
                        give1(c, 13358);
                        give1(c, 13360);
                        given = true;
                        break;
                    case 2: //Pernix
                        give1(c, 13355);
                        give1(c, 13354);
                        give1(c, 13352);
                        given = true;
                        break;
                    case 3: //Virtus
                        give1(c, 13350);
                        give1(c, 13348);
                        give1(c, 13346);
                        given = true;
                        break;
                    case 4: //Vesta w/ spear
                        give1(c, 13887);
                        give1(c, 13893);
                        give1(c, 13905);
                        given = true;
                        break;
                    case 5: //Vesta w/ sword
                        give1(c, 13887);
                        give1(c, 13893);
                        give1(c, 13899);
                        given = true;
                        break;
                    case 6: //Statius
                        give1(c, 13884);
                        give1(c, 13890);
                        give1(c, 13896);
                        give1(c, 13902);
                        given = true;
                        break;
                    case 7: //morrigan w/ javelin
                        give1(c, 13870);
                        give1(c, 13873);
                        give1(c, 13876);
                        give100(c, 13879);
                        given = true;
                        break;
                    case 8: //morrigan w/ axes
                        give1(c, 13870);
                        give1(c, 13873);
                        give1(c, 13876);
                        give100(c, 13883);
                        given = true;
                        break;
                    case 9: //chaotic set
                        give1(c, 18349);
                        give1(c, 18351);
                        give1(c, 18353);
                        give1(c, 18355);
                        give1(c, 18357);
                        give1(c, 18359);
                        given = true;
                        break;
                    default:
                        give1(c, itemReceive);
                        given = true;
                }
                if (given && !donate.getItemName().equalsIgnoreCase("received")) {
                    c.sendMessage("Congratulations! You just received " + donate.getItemName());
                } else {
                    c.sendMessage("We're sorry, but there was a problem!");
                    c.sendMessage("please contact Dayghost, the creator of the automated donation system!");
                }
            }
        }
        if (playerCommand.startsWith("report") && playerCommand.length() > 7) {
            try {
                BufferedWriter report = new BufferedWriter(new FileWriter("./Data/bans/Reports.txt", true));
                String Report = playerCommand.substring(7);
                try {
                    report.newLine();
                    report.write(c.playerName + ": " + Report);
                    c.sendMessage("You have successfully submitted your report.");
                } finally {
                    report.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        if (playerCommand.equals("dice") && (c.playerRights > 0)) {
            c.forcedChat("I have rolled a "+ Misc.random(100) +" on the dice!");
            c.foodDelay = System.currentTimeMillis();
            c.gfx0(2074);
            c.startAnimation(11900);
        }
        
        
        if (playerCommand.equals("trade") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(2012, 4751, 0, "modern");
            c.sendMessage("Welcome to the Shops!");
        }
        
        if (playerCommand.equals("corp") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(3304, 9375, 0, "modern");
            c.sendMessage("Use a sigil with a blessed spirit shield!");
        }
        
        if (playerCommand.equals("home") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(3086, 3494, 0, "modern");
            c.sendMessage("You teleport to Home");
        }
        if (playerCommand.equals("slayertower") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(3428, 3538, 0, "modern");
            c.sendMessage("Welcome to the slayer tower!");
        }
        if (playerCommand.equals("summon") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(3503, 3492, 0, "modern");
            c.sendMessage("You can train Summoning here! Talk to Pikkupstix.");
        }
        if (playerCommand.equals("adminzone") && (c.playerRights >= 2)) {
            c.getPA().startTeleport(3156, 4820, 0, "modern");
            c.sendMessage("Welcome to the administration HQ!");
        }
        if (playerCommand.equals("funpk") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(2605, 3153, 0, "modern");
            c.sendMessage("Welcome to the FunPK arena!");
        }
        if (playerCommand.equals("modzone") && (c.playerRights >= 1)) {
            c.getPA().startTeleport(2393, 9894, 0, "modern");
            c.sendMessage("Welcome to the administration HQ!");
        }
        if (playerCommand.equals("mining") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(3040, 9802, 0, "modern");
            c.sendMessage("Welcome to the Mining zone");
        }
        if (playerCommand.equals("monkeyhome") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(2758, 2782, 0, "modern");
            c.sendMessage("Welcome to the Jungle..lol Ape Atoll UNDER CONSTRUCTION!");
        }
        if (playerCommand.equals("ancient") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(3233, 2916, 0, "modern");
            c.sendMessage("Climb up the Pyramid and the alter is at the top!");
        }
        if (playerCommand.equals("lunar") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(2713, 9564, 0, "modern");
            c.sendMessage("Go down the stairs till you see the Lunar Alter!");
        }
        if (playerCommand.equals("highpk") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(3286, 3881, 0, "modern");
            c.sendMessage("welcome to level 47 wildy, this is Multi area...Good Luck!");
        }
        if (playerCommand.equals("chill") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(2149, 5096, 0, "modern");
            c.sendMessage("welcome to Chill zone, simply relax and do what ever you want!");
        }
        if (playerCommand.equals("trippy") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(3683, 9889, 0, "modern");
            c.sendMessage("woah look at the ground!");
        }
        if (playerCommand.equals("train2") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(2911, 3614, 0, "modern");
            c.sendMessage("Welcome to the 2nd training are! summoning NPC's will help you in battle");
        }
        if (playerCommand.equals("curses") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(3217, 3219, 0, "modern");
            c.sendMessage("Head on over to Lumbridge Chapel to switch to Ancient Prayers!");
        }
        if (playerCommand.equals("train") && (c.playerRights >= 0)) {
            c.getPA().startTeleport(2683, 3725, 0, "modern");
            c.sendMessage("Welcome to the classic rock crab training area!");
        }
        
        if (playerCommand.startsWith("kdr")) {
            double KDR = ((double)c.KC)/((double)c.DC);
            c.forcedChat("My Kill/Death ratio is "+c.KC+"/"+c.DC+"; "+KDR+".");
        }
        
        if (playerCommand.startsWith("/") && playerCommand.length() > 1) {
            if (c.clanId >= 0) {
                System.out.println(playerCommand);
                playerCommand = playerCommand.substring(1);
                Server.clanChat.playerMessageToClan(c.playerId, playerCommand, c.clanId);
            } else {
                if (c.clanId != -1)
                    c.clanId = -1;
                c.sendMessage("You are not in a clan.");
            }
            return;
        }
        if (Config.SERVER_DEBUG)
            Misc.println(c.playerName+" playerCommand: "+playerCommand);
        
        if (c.playerRights >= 0)
            playerCommands(c, playerCommand);
        if (c.playerRights == 1 || c.playerRights == 2 || c.playerRights == 3)
            moderatorCommands(c, playerCommand);
        if (c.playerRights == 2 || c.playerRights == 3)
            administratorCommands(c, playerCommand);
        if (c.playerRights == 3)
            ownerCommands(c, playerCommand);
        if (c.playerRights == 4)
            DonatorCommands(c, playerCommand);
        
    }
    
    
    public void playerCommands(Client c, String playerCommand)
    {
        if (playerCommand.startsWith("resettask")) {
            c.taskAmount = -1;
            c.slayerTask = 0;
        }
        if (playerCommand.startsWith("resetdef")) {
            if (c.inWild())
                return;
            for (int j = 0; j < c.playerEquipment.length; j++) {
                if (c.playerEquipment[j] > 0) {
                    c.sendMessage("Please take all your armour and weapons off before using this command.");
                    return;
                }
            }
            try {
                int skill = 1;
                int level = 1;
                c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
                c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                c.getPA().refreshSkill(skill);
            } catch (Exception e){}
        }
        
        if (playerCommand.startsWith("resethp")) {
            if (c.inWild())
                return;
            for (int j = 0; j < c.playerEquipment.length; j++) {
                if (c.playerEquipment[j] > 0) {
                    c.sendMessage("Please take all your armour and weapons off before using this command.");
                    return;
                }
            }
            try {
                int skill = 3;
                int level = 1;
                c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
                c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                c.getPA().refreshSkill(skill);
            } catch (Exception e){}
        }
        if (playerCommand.startsWith("resetatt")) {
            if (c.inWild())
                return;
            for (int j = 0; j < c.playerEquipment.length; j++) {
                if (c.playerEquipment[j] > 0) {
                    c.sendMessage("Please take all your armour and weapons off before using this command.");
                    return;
                }
            }
            try {
                int skill = 0;
                int level = 1;
                c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
                c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                c.getPA().refreshSkill(skill);
            } catch (Exception e){}
        }
        if (playerCommand.startsWith("resetstr")) {
            if (c.inWild())
                return;
            for (int j = 0; j < c.playerEquipment.length; j++) {
                if (c.playerEquipment[j] > 0) {
                    c.sendMessage("Please take all your armour and weapons off before using this command.");
                    return;
                }
            }
            try {
                int skill = 2;
                int level = 1;
                c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
                c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                c.getPA().refreshSkill(skill);
            } catch (Exception e){}
        }
        if (playerCommand.startsWith("resetrange")) {
            if (c.inWild())
                return;
            for (int j = 0; j < c.playerEquipment.length; j++) {
                if (c.playerEquipment[j] > 0) {
                    c.sendMessage("Please take all your armour and weapons off before using this command.");
                    return;
                }
            }
            try {
                int skill = 4;
                int level = 1;
                c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
                c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                c.getPA().refreshSkill(skill);
            } catch (Exception e){}
        }
        if (playerCommand.startsWith("resetpray")) {
            if (c.inWild())
                return;
            for (int j = 0; j < c.playerEquipment.length; j++) {
                if (c.playerEquipment[j] > 0) {
                    c.sendMessage("Please take all your armour and weapons off before using this command.");
                    return;
                }
            }
            try {
                int skill = 5;
                int level = 1;
                c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
                c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                c.getPA().refreshSkill(skill);
            } catch (Exception e){}
        }
        if (playerCommand.startsWith("resetmagic")) {
            if (c.inWild())
                return;
            for (int j = 0; j < c.playerEquipment.length; j++) {
                if (c.playerEquipment[j] > 0) {
                    c.sendMessage("Please take all your armour and weapons off before using this command.");
                    return;
                }
            }
            try {
                int skill = 6;
                int level = 1;
                c.playerXP[skill] = c.getPA().getXPForLevel(level)+5;
                c.playerLevel[skill] = c.getPA().getLevelForXP(c.playerXP[skill]);
                c.getPA().refreshSkill(skill);
            } catch (Exception e){}
        }
        if (playerCommand.startsWith("reward")) {
            if(c.checkVotes(c.playerName)) {
                c.getItems().addItem(995, 5000000);
                c.sendMessage("Thanks for voting!");
            } else {
                c.sendMessage("You have not yet voted, type ::vote to do so");
            }
        }
        if (playerCommand.startsWith("rules")) {
            c.getPA().showInterface(8134);
            c.flushOutStream();
            c.getPA().sendFrame126("Rules of Vengeance-Pk!", 8144);
            c.getPA().sendFrame126("No pkpoint farming. (jail or ban)", 8147);
            c.getPA().sendFrame126("Do not use offensive langauge, mild language is aloud.", 8148);
            c.getPA().sendFrame126("Do not scam items and or passwords (ipban)", 8149);
            c.getPA().sendFrame126("Auto clicking is against the rules!( we will catch you!)", 8150);
            c.getPA().sendFrame126("auto types enabled(5+sec) NO ADVERTING", 8151);
            c.getPA().sendFrame126("Luring is aloud, however do not complain to staff!", 8152);
            c.getPA().sendFrame126("Respect all staff members, and fellow players.", 8153);
            c.getPA().sendFrame126("NO advertisments aloud (automaticly banned -> Ipban)", 8154);
            c.getPA().sendFrame126("if you see someone flaming really badly contact a admin", 8155);
            c.getPA().sendFrame126("Do NOT ask staf for any stuff (mute -> ban)!", 8156);
        }
        if (playerCommand.equalsIgnoreCase("modcommands")) {
            c.sendMessage("-Moderator commands are-");
            c.sendMessage("::jail ::unjail ::mute ::unmute ::ipmute ::unipmute");
            c.sendMessage("And of course @blu@ALL @bla@regular commands");
        }
        
        if (playerCommand.equalsIgnoreCase("giveitem")) {
            String canGive[] = { "Admin", "Owner", "Mod" }; //List of people who can give items.
            for (String x : canGive) {
                if (!c.playerName.equals(x) || c.playerRights != 3)
                    return;
            }
            
            try {
                String[] args = playerCommand.split(" ");
                String playerToGive = args[1];
                int itemToGive = Integer.parseInt(args[2]);
                int amountToGive = Integer.parseInt(args[3]);
                
                for (int p = 0; p < Server.playerHandler.players.length; p++) {
                    if (Server.playerHandler.players[p] != null) {
                        if(Server.playerHandler.players[p].playerName.equalsIgnoreCase(playerToGive)) {
                            Client player = (Client)Server.playerHandler.players[p];
                            
                            player.getItems().addItem(itemToGive, amountToGive);
                            c.sendMessage("You have successfully given " + player.playerName + " the item, " + c.getItems().getItemName(itemToGive) + ".");
                            player.sendMessage(c.playerName + " has given you the item, " + c.getItems().getItemName(itemToGive) + ".");
                        }
                    }
                }
            } catch (Exception e){
                System.out.println("Error in GiveItem Command!");
            }
        }
        if (playerCommand.equalsIgnoreCase("commands")) {
            c.getPA().showInterface(8134);
            c.getPA().sendFrame126("@red@~ Vengeance-Pk Commands ~",8144);
            c.getPA().sendFrame126("@cya@All commands have a :: infront of it",8145);
            c.getPA().sendFrame126("@red@yell ",8146);
            c.getPA().sendFrame126("@gre@All Reset Commands",8147);
            c.getPA().sendFrame126("@gre@::changepassword",8148);
            c.getPA().sendFrame126("@gre@::kdr",8149);
            c.getPA().sendFrame126("@gre@::rest",8150);
            c.getPA().sendFrame126("@gre@::fpp",8151);
            c.getPA().sendFrame126("@gre@::rules",8152);
            c.getPA().sendFrame126("@gre@::report",8153);
            c.getPA().sendFrame126("@gre@::corp",8154);
            c.getPA().sendFrame126("@gre@::train",8155);
            c.getPA().sendFrame126("@gre@::home",8155);
            c.getPA().sendFrame126("@gre@::slayertower",8156);
            c.getPA().sendFrame126("@gre@::news",8157);
            c.getPA().sendFrame126("@gre@::monkeyhome",8158);
            c.getPA().sendFrame126("@gre@::mining",8159);
            c.getPA().sendFrame126("@gre@::summon",8160);
            c.getPA().sendFrame126("@gre@::train2",8161);
            c.getPA().sendFrame126("@gre@::funpk",8162);
            c.getPA().sendFrame126("@gre@::curses",8163);
            c.getPA().sendFrame126("@gre@::ancient",8164);
            c.getPA().sendFrame126("@gre@::lunar",8165);
            c.getPA().sendFrame126("@gre@::highpk",8166);
            c.getPA().sendFrame126("@gre@::chill",8167);
            c.getPA().sendFrame126("@gre@::trippy",8168);
        }
        if (playerCommand.equalsIgnoreCase("news")) {
            c.getPA().showInterface(8134);
            c.getPA().sendFrame126("@red@~ Vengeance-Pk Updates List ~",8144);
            c.getPA().sendFrame126("@cya@Summoning has now been perfected 100%",8145);
            c.getPA().sendFrame126("@red@Increased switched speed for hybriders",8146);
            c.getPA().sendFrame126("@gre@Added shortcut commands! do ::commands",8147);
            c.getPA().sendFrame126("@gre@korasi special has been boosted up!",8148);
            c.getPA().sendFrame126("@gre@We've added Frost Dragons! (kbd area)",8149);
            c.getPA().sendFrame126("@gre@::rest is 100% workin with 0 bugs!",8150);
            c.getPA().sendFrame126("@gre@Hunter+agility is perfected!",8151);
            c.getPA().sendFrame126("@gre@Curses all are 100% working + effects 100%",8152);
            c.getPA().sendFrame126("@gre@Peity,smite,chivalry now has effects",8153);
            c.getPA().sendFrame126("@gre@Added gamblings NPC to Donator Zone!!",8154);
            c.getPA().sendFrame126("@gre@Lunar tab now has brand new 562 looks!",8155);
            c.getPA().sendFrame126("@gre@Pak-yak can now store your items",8155);
            c.getPA().sendFrame126("@gre@Added 100% working FunPK! (::funpk)",8155);
            c.getPA().sendFrame126("@gre@ancient alter, ancient curses alter moved",8155);
            c.getPA().sendFrame126("@gre@In ANY multi areas, your familiar will help you out",8155);
        }
        if(playerCommand.startsWith("stafflist")) {
            c.getPA().sendFrame126(" ", 13610);
            c.getPA().sendFrame126(" ", 13611);
            c.getPA().sendFrame126(" ", 13612);
            c.getPA().sendFrame126(" ", 13613);
            c.getPA().sendFrame126(" ", 13614);
            c.getPA().sendFrame126(" ", 13615);
            c.getPA().sendFrame126(" ", 13616);
            c.getPA().sendFrame126(" ", 13617);
            c.getPA().sendFrame126(" ", 13618);
            c.getPA().sendFrame126(" ", 13619);
            c.getPA().sendFrame126(" ", 13620);
            c.getPA().sendFrame126(" ", 13621);
            c.getPA().sendFrame126(" ", 13622);
            c.getPA().sendFrame126(" ", 13623);
            c.getPA().showInterface(13585);
            c.getPA().sendFrame126("@blu@Vengeance-Pk's Official Staff List:", 13589);
            c.getPA().sendFrame126("Owner: Aleyasu", 13591);
            c.getPA().sendFrame126("Co-Owner: Berserker ", 13592);
            c.getPA().sendFrame126("Administrators: Mark", 13593);
            c.getPA().sendFrame126("Head - Moderator: NONE", 13594);
            c.getPA().sendFrame126("Moderator - Brad", 13595);
            c.getPA().sendFrame126("Server Helpers/ Donators- Brad, Thetank ", 13596);
            c.getPA().sendFrame126("Super Donators - NONE", 13597);
            c.getPA().sendFrame126("", 13598);
            c.getPA().sendFrame126("PM any of these players above if you need help!", 13599);
            c.getPA().sendFrame126("( Unless they are busy! )", 13600);
            c.getPA().sendFrame126("", 13601);
            c.getPA().sendFrame126("", 13602);
            c.getPA().sendFrame126("", 13602);
            c.getPA().sendFrame126("", 13603);
            c.getPA().sendFrame126("", 13604);
            c.getPA().sendFrame126("", 13605);
            c.getPA().sendFrame126("", 13606);
            c.getPA().sendFrame126("", 13607);
            c.getPA().sendFrame126("", 13608);
            c.getPA().sendFrame126("", 13609);
            
        }
        
        if (playerCommand.startsWith("rest")) {
            c.startAnimation(5713);
        }
        if (playerCommand.startsWith("tpk") || playerCommand.startsWith("epp") || playerCommand.startsWith("Epp")) {
            c.sendMessage("You have <col=1532693>" + c.pkPoints + "</col> EPP.");
        }
        if (playerCommand.equalsIgnoreCase("voted")) {
            if(c.checkVotes(c.playerName)) {
                c.getItems().addItem(995, 5000000);
                c.sendMessage("<col=1532693>Thanks for voting!</col>");
            } else {
                c.sendMessage("<col=1532693>You have voted already!</col>");
            }
        }
        if (playerCommand.equals("vote")) {
            c.getPA().sendFrame126("http://http://www.runelocus.com/toplist/?action=details&id=23217", 12000);
            c.sendMessage("Please vote every day for us!");
        }
        if (playerCommand.equals("forums")) {
            c.getPA().sendFrame126("http://vengeancepk.tk", 12000);
            c.sendMessage("Welcome! Please register!!");
        }
        if (playerCommand.equalsIgnoreCase("players")) {
            c.getPA().showInterface(8134);
            c.getPA().sendFrame126("players online", 8144);
            c.getPA().sendFrame126("Online players(" + PlayerHandler.getPlayerCount() + "):", 8145);
            int line = 8147;
            for (int i = 0; i < Config.MAX_PLAYERS; i++)  {
                if (Server.playerHandler.players[i] != null) {
                    Client d = c.getClient(Server.playerHandler.players[i].playerName);
                    if (d.playerName != null){
                        c.getPA().sendFrame126(d.playerName, line);
                        line++;
                    } else if (d.playerName == null) {
                        c.getPA().sendFrame126("", line);
                    }
                }
            }
            c.flushOutStream();
        }
        
        if (playerCommand.equalsIgnoreCase("players")) {
            c.sendMessage("There are currently "+PlayerHandler.getPlayerCount()+ " players online.");
            c.getPA().sendFrame126(Config.SERVER_NAME+" - Online Players", 8144);
            c.getPA().sendFrame126("@dbl@Online players(" + PlayerHandler.getPlayerCount()+ "):", 8145);
            int line = 8147;
            for (int i = 1; i < Config.MAX_PLAYERS; i++) {
                Client p = c.getClient(i);
                if (!c.validClient(i))
                    continue;
                if (p.playerName != null) {
                    String title = "";
                    if (p.playerRights == 1) {
                        title = "Mod, ";
                    } else if (p.playerRights == 2) {
                        title = "Admin, ";
                    }
                    title += "level-" + p.combatLevel;
                    String extra = "";
                    if (c.playerRights > 0) {
                        extra = "(" + p.playerId + ") ";
                    }
                    c.getPA().sendFrame126("@dre@" + extra + p.playerName + "@dbl@ ("+ title + ") is at " + p.absX + ", "+ p.absY, line);
                    line++;
                }
            }
            c.getPA().showInterface(8134);
            c.flushOutStream();
        }
        if (playerCommand.startsWith("changepassword") && playerCommand.length() > 15) {
            c.playerPass = playerCommand.substring(15);
            c.sendMessage("Your password is now: " + c.playerPass);
        }
        
        if (playerCommand.startsWith("ep") || playerCommand.startsWith("Ep") || playerCommand.startsWith("EP") || playerCommand.startsWith("eP")) {
            c.sendMessage("EP: "+ c.earningPotential+"");
        }
        if (playerCommand.startsWith("yell")) {
            if (Connection.isMuted(c)) {
                c.sendMessage("You are muted and cannot yell.");
                return;
            }
            if (c.playerRights == 0){
                c.sendMessage("You must be a donator to use this command!");
            }
            /*
             *This is the sensor for the yell command
             */
            /*String text = playerCommand.substring(5);
             * String[] bad = {"chalreq", "duelreq", "tradereq", ". com", "c0m", "com",
             * "org", "net", "biz", ". net", ". org", ". biz",
             * ". no-ip", "- ip", ".no-ip.biz", "no-ip.org", "servegame",
             * ".com", ".net", ".org", "no-ip", "****", "is gay", "****",
             * "crap", "rubbish", ". com", ". serve", ". no-ip", ". net", ". biz"};
             * for(int i = 0; i < bad.length; i++){
             * if(text.indexOf(bad[i]) >= 0){
             * return;
             * }
             * }*/
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client)Server.playerHandler.players[j];
                    
                    
                    
                    if (c.playerName.equalsIgnoreCase("Teakyfdhydfh")) {
                        c2.sendMessage("<shad=9440238>[Global Mod]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("Stujrujsu")) {
                        c2.sendMessage("<shad=9440238>[Friend]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("Alexargfyarzar")) {
                        c2.sendMessage("<shad=9440238>[Loves Bitch]</col>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("xsysteazdadzatzatx")) {
                        c2.sendMessage("<shad=9440238>[Mountain Dew]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("s6zsdzjz")) {
                        c2.sendMessage("<shad=9440238>[Founder]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("Dayghost")) {
                        c2.sendMessage("<col=255><shad=9440238>[Web Developer]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("Mod Ssthstfhsthhadow")) {
                        c2.sendMessage("<shad=9440238>[Sexy Mod]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.isDonator == 4) {
                        c2.sendMessage("<shad=10906535>[Forum Mod]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("Some Str Lt")) {
                        c2.sendMessage("<col=255>[VIP]</col>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("Jossthsthsthh")) {
                        c2.sendMessage("<col=40000><shad=15695415>[System Coder]</col><img=2>"+ Misc.optimizeText(c.playerName) +":<shad=9440238></col> "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("Shadowtotftghsthston")) {
                        c2.sendMessage("<shad=15695415>[Global Mod]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("Berserker")) {
                        c2.sendMessage("<shad=15695415>[Co-Owner]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerName.equalsIgnoreCase("Lovdhatathe")) {
                        c2.sendMessage("<shad=6081134>[Co-Owner]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerRights == 1){
                        c2.sendMessage("<col=40000><shad=6081134>[Moderator]</col><img=1>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerRights == 2){
                        c2.sendMessage("<col=255><shad=15695415>[Administrator]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.playerRights == 3){
                        c2.sendMessage("<col=255><shad=15695415>[Owner]</col><img=2>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.isDonator == 1){
                        c2.sendMessage("<shad=6081134>[Donator]</col><img=0>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.isDonator == 2) {
                        c2.sendMessage("<shad=65535>[Super Donator]</col><img=0>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                    }else if (c.isDonator == 3) {
                        c2.sendMessage("<shad=14981889>[Extreme Donator]</col><img=0>"+ Misc.optimizeText(c.playerName) +": "
                                + Misc.optimizeText(playerCommand.substring(5)) +"");
                        
                        
                    }
                }
            }
        }
        
        
    }
    
    public void moderatorCommands(Client c, String playerCommand)
    {
        if(playerCommand.startsWith("jail")) {
            try {
                String playerToBan = playerCommand.substring(5);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.teleportToX = 3102;
                            c2.teleportToY = 9516;
                            c2.Jail = true;
                            c2.sendMessage("You have been jailed by "+c.playerName+"");
                            c.sendMessage("Successfully Jailed "+c2.playerName+".");
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.startsWith("mute")) {
            try {
                String playerToBan = playerCommand.substring(5);
                Connection.addNameToMuteList(playerToBan);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.sendMessage("You have been muted by: " + c.playerName);
                            c.sendMessage("You have muted: " + c2.playerName);
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
        if (playerCommand.startsWith("unmute")) {
            try {
                String playerToBan = playerCommand.substring(7);
                Connection.unMuteUser(playerToBan);
                c.sendMessage("Unmuted.");
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
        if (playerCommand.startsWith("checkbank")) {
            String[] args = playerCommand.split(" ");
            for(int i = 0; i < Config.MAX_PLAYERS; i++)
            {
                Client o = (Client) Server.playerHandler.players[i];
                if(Server.playerHandler.players[i] != null)
                {
                    if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1]))
                    {
                        c.getPA().otherBank(c, o);
                        break;
                    }
                }
            }
        }
        if (playerCommand.startsWith("kick") && playerCommand.charAt(4) == ' ') {
            try {
                String playerToBan = playerCommand.substring(5);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Server.playerHandler.players[i].disconnected = true;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.startsWith("ban") && playerCommand.charAt(3) == ' ') {
            try {
                String playerToBan = playerCommand.substring(4);
                Connection.addNameToBanList(playerToBan);
                Connection.addNameToFile(playerToBan);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Server.playerHandler.players[i].disconnected = true;
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.sendMessage(" " +c2.playerName+ " Got Banned By " + c.playerName+ ".");
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.startsWith("unban")) {
            try {
                String playerToBan = playerCommand.substring(6);
                Connection.removeNameFromBanList(playerToBan);
                c.sendMessage(playerToBan + " has been unbanned.");
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if(playerCommand.startsWith("unjail")) {
            try {
                String playerToBan = playerCommand.substring(7);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.teleportToX = 3086;
                            c2.teleportToY = 3493;
                            c2.monkeyk0ed = 0;
                            c2.Jail = false;
                            c2.sendMessage("You have been unjailed by "+c.playerName+".");
                            c.sendMessage("Successfully unjailed "+c2.playerName+".");
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
    }
    
    public void administratorCommands(Client c, String playerCommand)
    {
        if (playerCommand.startsWith("alert")) {
            String msg = playerCommand.substring(6);
            for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                if (Server.playerHandler.players[i] != null) {
                    Client c2 = (Client)Server.playerHandler.players[i];
                    c2.sendMessage("Alert##Notification##" + msg + "##By: " + c.playerName);
                    
                }
            }
        }
        if (playerCommand.startsWith("ipmute")) {
            try {
                String playerToBan = playerCommand.substring(7);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Connection.addIpToMuteList(Server.playerHandler.players[i].connectedFrom);
                            c.sendMessage("You have IP Muted the user: "+Server.playerHandler.players[i].playerName);
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.sendMessage("You have been muted by: " + c.playerName);
                            c2.sendMessage(" " +c2.playerName+ " Got IpMuted By " + c.playerName+ ".");
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
        
        
        if (playerCommand.startsWith("object")) {
            String[] args = playerCommand.split(" ");
            c.getPA().object(Integer.parseInt(args[1]), c.absX, c.absY, 0, 10);
        }
        
        if (playerCommand.equalsIgnoreCase("mypos")) {
            c.sendMessage("X: "+c.absX+" Y: "+c.absY+" H: "+c.heightLevel);
        }
        
        if (playerCommand.startsWith("interface")) {
            String[] args = playerCommand.split(" ");
            c.getPA().showInterface(Integer.parseInt(args[1]));
        }
        
        if (playerCommand.startsWith("gfx")) {
            String[] args = playerCommand.split(" ");
            c.gfx0(Integer.parseInt(args[1]));
        }
        if (playerCommand.startsWith("tele")) {
            String[] arg = playerCommand.split(" ");
            if (arg.length > 3)
                c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),Integer.parseInt(arg[3]));
            else if (arg.length == 3)
                c.getPA().movePlayer(Integer.parseInt(arg[1]),Integer.parseInt(arg[2]),c.heightLevel);
        }
        
        if (playerCommand.startsWith("xteletome")) {
            try {
                String playerToTele = playerCommand.substring(10);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToTele)) {
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.sendMessage("You have been teleported to " + c.playerName);
                            c2.getPA().movePlayer(c.getX(), c.getY(), c.heightLevel);
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
        
        if (playerCommand.startsWith("xteleto")) {
            String name = playerCommand.substring(8);
            for (int i = 0; i < Config.MAX_PLAYERS; i++) {
                if (Server.playerHandler.players[i] != null) {
                    if (Server.playerHandler.players[i].playerName.equalsIgnoreCase(name)) {
                        c.getPA().movePlayer(Server.playerHandler.players[i].getX(), Server.playerHandler.players[i].getY(), Server.playerHandler.players[i].heightLevel);
                    }
                }
            }
        }
        if (playerCommand.equalsIgnoreCase("bank")) {
            c.getPA().openUpBank();
        }
        if (playerCommand.startsWith("unipmute")) {
            try {
                String playerToBan = playerCommand.substring(9);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Connection.unIPMuteUser(Server.playerHandler.players[i].connectedFrom);
                            c.sendMessage("You have Un Ip-Muted the user: "+Server.playerHandler.players[i].playerName);
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
        
        
        if (playerCommand.startsWith("ipban")) {
            try {
                String playerToBan = playerCommand.substring(6);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToBan)) {
                            Connection.addIpToBanList(Server.playerHandler.players[i].connectedFrom);
                            Connection.addIpToFile(Server.playerHandler.players[i].connectedFrom);
                            c.sendMessage("You have IP banned the user: "+Server.playerHandler.players[i].playerName+" with the host: "+Server.playerHandler.players[i].connectedFrom);
                            Client c2 = (Client)Server.playerHandler.players[i];
                            Server.playerHandler.players[i].disconnected = true;
                            c2.sendMessage(" " +c2.playerName+ " Got IpBanned By " + c.playerName+ ".");
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
        
    }
    
    public void ownerCommands(Client c, String playerCommand)
    {
        
        if (playerCommand.startsWith("update") && c.playerName.equalsIgnoreCase("Aleyasu")) {
            String[] args = playerCommand.split(" ");
            int a = Integer.parseInt(args[1]);
            PlayerHandler.updateSeconds = a;
            PlayerHandler.updateAnnounced = false;
            PlayerHandler.updateRunning = true;
            PlayerHandler.updateStartTime = System.currentTimeMillis();
        }
        
        
        if(playerCommand.startsWith("npc")) {
            try {
                int newNPC = Integer.parseInt(playerCommand.substring(4));
                if(newNPC > 0) {
                    Server.npcHandler.spawnNpc(c, newNPC, c.absX, c.absY, 0, 0, 120, 7, 70, 70, false, false);
                    c.sendMessage("You spawn a Npc.");
                } else {
                    c.sendMessage("No such NPC.");
                }
            } catch(Exception e) {
                
            }
        }
        if (playerCommand.startsWith("item")) {
            try {
                String[] args = playerCommand.split(" ");
                if (args.length == 3) {
                    int newItemID = Integer.parseInt(args[1]);
                    int newItemAmount = Integer.parseInt(args[2]);
                    if ((newItemID <= 20500) && (newItemID >= 0)) {
                        c.getItems().addItem(newItemID, newItemAmount);
                    } else {
                        c.sendMessage("That item ID does not exist.");
                    }
                } else {
                    c.sendMessage("Wrong usage: (Ex:(::pickup_ID_Amount)(::item 995 1))");
                }
            } catch(Exception e) {
                
            } // HERE?
        } // HERE?
        if (playerCommand.startsWith("hail")) {
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                    Client p = (Client)Server.playerHandler.players[j];
                    p.forcedChat("REMEMBER TO SIGN UP ON OUR FORUMS AND VOTE DAILY!");
                    p.startAnimation(1651);
                }
            }
        }
        if (playerCommand.startsWith("hail2")) {
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                    Client p = (Client)Server.playerHandler.players[j];
                    p.forcedChat("RESPECT ALL STAFF AND PLAYERS, ENJOY!");
                    p.startAnimation(1651);
                }
            }
        }
        if (playerCommand.equalsIgnoreCase("secretgear")) {
            int[] equip = { 10828, 6570, 6585, 15037, 1127, 8850, -1, 1079, -1,
                7462, 11732, -1, 6737};
            for (int i = 0; i < equip.length; i++) {
                c.playerEquipment[i] = equip[i];
                c.playerEquipmentN[i] = 1;
                c.getItems().setEquipment(equip[i], 1, i);
            }
            
            c.getItems().addItem(15004, 1);
            c.getItems().addItem(15019, 1);
            c.getItems().addItem(2436, 1);
            c.getItems().addItem(2440, 1);
            c.getItems().addItem(15005, 1);
            c.getItems().addItem(5698, 1);
            c.getItems().addItem(6685, 1);
            c.getItems().addItem(3024, 1);
            c.getItems().addItem(391, 1);
            c.getItems().addItem(391, 1);
            c.getItems().addItem(391, 1);
            c.getItems().addItem(3024, 1);
            c.getItems().addItem(391, 13);
            c.getItems().addItem(560, 500);
            c.getItems().addItem(9075, 500);
            c.getItems().addItem(557, 500);
            c.playerMagicBook = 2;
            c.getItems().resetItems(3214);
            c.getItems().resetBonus();
            c.getItems().getBonus();
            c.getItems().writeBonus();
        }
        if (playerCommand.startsWith("givesuper")) {
            try {
                String playerToMod = playerCommand.substring(10);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.sendMessage("You have been given donator status by " + c.playerName);
                            c2.isDonator = 2;
                            c2.logout();
                            
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.equalsIgnoreCase("staffmeeting")) {
            int random = Misc.random(2);
            
            switch (random) {
                case 0:
                    c.getPA().startTeleport(2384, 4420, 0, "modern");
                    break;
                case 1:
                    c.getPA().startTeleport(2388, 4425, 0, "modern");
                    break;
            }
        }
        if (playerCommand.equalsIgnoreCase("redskull")) {
            c.getPA().requestUpdates();
            c.playerLevel[0] = 120;
            c.getPA().refreshSkill(0);
            c.playerLevel[1] = 120;
            c.getPA().refreshSkill(1);
            c.playerLevel[2] = 120;
            c.getPA().refreshSkill(2);
            c.playerLevel[4] = 126;
            c.getPA().refreshSkill(4);
            c.playerLevel[5] = 1337;
            c.getPA().refreshSkill(5);
            c.playerLevel[6] = 126;
            c.getPA().refreshSkill(6);
            c.isSkulled = false;
            c.skullTimer = Config.SKULL_TIMER;
            c.headIconPk = 1;
            c.sendMessage("You are now L33tz0rs like g wishart & judge dread!!");
            
        }
        
        if (playerCommand.equalsIgnoreCase("lord")) {
            c.getItems().addItem(15073, 1);
            c.getItems().addItem(15074, 1);
            c.sendMessage("Have fun Owning!!");
        }
        if (playerCommand.equalsIgnoreCase("dcape")) {
            c.getItems().addItem(15070, 1);
            c.getItems().addItem(15071, 1);
            c.sendMessage("Have fun Owning!!");
        }
        if (playerCommand.equalsIgnoreCase("prome")) {
            c.getItems().addItem(15080, 1);
            c.getItems().addItem(15081, 1);
            c.getItems().addItem(15082, 1);
            c.getItems().addItem(15083, 1);
            c.getItems().addItem(15084, 1);
            c.getItems().addItem(15085, 1);
            c.sendMessage("Have fun Owning!!");
        }                        /*if (playerCommand.startsWith("minigame")) {
         * c.getPA().movePlayer(2911, 3605, 0);
         * }*/
        
        if (playerCommand.equalsIgnoreCase("sets")) {
            if (c.getItems().freeSlots() > 27) {
                c.getItems().addItem(16015, 1);
                c.getItems().addItem(16016, 1);
                c.getItems().addItem(16017, 1);
                c.getItems().addItem(16018, 1);
                c.getItems().addItem(16019, 1);
                c.getItems().addItem(16020, 1);
                c.getItems().addItem(16021, 1);
                c.getItems().addItem(16022, 1);
                c.getItems().addItem(16023, 1);
                c.getItems().addItem(16024, 1);
                c.getItems().addItem(16025, 1);
                c.getItems().addItem(16026, 1);
                c.getItems().addItem(16027, 1);
                c.getItems().addItem(16028, 1);
                c.getItems().addItem(16029, 1);
                c.getItems().addItem(16030, 1);
                c.getItems().addItem(16031, 1);
                c.getItems().addItem(16032, 1);
                c.getItems().addItem(16033, 1);
                c.getItems().addItem(16034, 1);
                c.getItems().addItem(16035, 1);
                c.sendMessage("Have fun Owning!!");
            } else {
                c.sendMessage("You need 10 free slots to open this set!");
            }
        }
        if (playerCommand.equalsIgnoreCase("party")) {
            int random = Misc.random(5);
            
            switch (random) {
                case 0:
                    c.getPA().startTeleport(2736, 3475, 0, "modern");
                    break;
                case 1:
                    c.getPA().startTeleport(2743, 3472, 0, "modern");
                    break;
                case 2:
                    c.getPA().startTeleport(2740, 3469, 0, "modern");
                    break;
                case 3:
                    c.getPA().startTeleport(2734, 3467, 0, "modern");
                    break;
                case 4:
                    c.getPA().startTeleport(2737, 3463, 0, "modern");
                    break;
            }
        }
        if (playerCommand.startsWith("barrage") && c.playerRights >=3) {
            if (c.inWild())
                return;
            if(c.duelStatus == 5)
                return;
            c.sendMessage("you now recieve barrage runes!");
            c.getItems().addItem(560, 200);
            c.getItems().addItem(565, 100);
            c.getItems().addItem(555, 300);
        }
        if (playerCommand.startsWith("anim")) {
            String[] args = playerCommand.split(" ");
            c.startAnimation(Integer.parseInt(args[1]));
            c.getPA().requestUpdates();
        }
        
        if (playerCommand.startsWith("spec")) {
            c.specAmount = 500.0;
        }
        
        if (playerCommand.startsWith("giveadmin") && c.playerName.equalsIgnoreCase("Aleyasu")) {
            try {
                String playerToAdmin = playerCommand.substring(10);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToAdmin)) {
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.sendMessage("You have been given admin status by " + c.playerName);
                            c2.playerRights = 2;
                            c2.logout();
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
        if (playerCommand.startsWith("givemod") && c.playerName.equalsIgnoreCase("Aleyasu")) {
            try {
                String playerToMod = playerCommand.substring(8);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.sendMessage("You have been given mod status by " + c.playerName);
                            c2.playerRights = 1;
                            c2.logout();
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
        if (playerCommand.startsWith("pnpc"))
        {
            try {
                int newNPC = Integer.parseInt(playerCommand.substring(5));
                if (newNPC <= 200000 && newNPC >= 0) {
                    c.npcId2 = newNPC;
                    c.isNpc = true;
                    c.updateRequired = true;
                    c.setAppearanceUpdateRequired(true);
                }
                else {
                    c.sendMessage("No such P-NPC.");
                }
            } catch(Exception e) {
                c.sendMessage("Wrong Syntax! Use as ::pnpc #");
            }
        }
        
        
        if (playerCommand.startsWith("givedonor") && c.playerName.equalsIgnoreCase("Aleyasu")) {
            try {
                String playerToMod = playerCommand.substring(10);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToMod)) {
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.sendMessage("You have been given donator status by " + c.playerName);
                            c2.playerRights = 4;
                            c2.isDonator = 1;
                            c2.logout();
                            
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        
        
        if (playerCommand.startsWith("demote") && c.playerName.equalsIgnoreCase("Aleyasu")) {
            try {
                String playerToDemote = playerCommand.substring(7);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(playerToDemote)) {
                            Client c2 = (Client)Server.playerHandler.players[i];
                            c2.sendMessage("You have been demoted by " + c.playerName);
                            c2.playerRights = 0;
                            c2.logout();
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.startsWith("reloadspawns")) {
            Server.npcHandler = null;
            Server.npcHandler = new server.model.npcs.NPCHandler();
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client)Server.playerHandler.players[j];
                    c2.sendMessage("<shad=15695415>[" + c.playerName + "] " + "NPC Spawns have been reloaded.</col>");
                }
            }
            
        }
        if(playerCommand.startsWith("restart") && (c.playerName.equalsIgnoreCase("Aleyasu"))) {
            for(Player p : PlayerHandler.players) {
                if(p == null)
                    continue;
                PlayerSave.saveGame((Client)p);
            }
            System.exit(0);
        }
        
        if (playerCommand.startsWith("item")) {
            try {
                String[] args = playerCommand.split(" ");
                if (args.length == 3) {
                    int newItemID = Integer.parseInt(args[1]);
                    int newItemAmount = Integer.parseInt(args[2]);
                    if ((newItemID <= 20500) && (newItemID >= 0)) {
                        c.getItems().addItem(newItemID, newItemAmount);
                    } else {
                        c.sendMessage("That item ID does not exist.");
                    }
                } else {
                    c.sendMessage("Wrong usage: (Ex:(::pickup_ID_Amount)(::item 995 1))");
                }
            } catch(Exception e) {
                
            } // HERE?
        } // HERE?
        if (playerCommand.equalsIgnoreCase("switch")) {
            for (int i = 0; i < 8 ; i++){
                c.getItems().wearItem(c.playerItems[i]-1,i);
            }
            c.sendMessage("Switching Armor");
        }
        if (playerCommand.equalsIgnoreCase("brid")) {
            c.getItems().deleteAllItems();
            int itemsToAdd[] = { 4151, 6585, 10551, 20072, 11732, 11726, 15220, 7462,
                2440, 2436, 3024};
            for (int i = 0; i < itemsToAdd.length; i++) {
                c.getItems().addItem(itemsToAdd[i], 1);
            }
            int[] equip = { 10828, 2414, 18335, 15486, 4712, 6889, -1, 4714, -1,
                6922, -1, 6920, 15018};
            for (int i = 0; i < equip.length; i++) {
                c.playerEquipment[i] = equip[i];
                c.playerEquipmentN[i] = 1;
                c.getItems().setEquipment(equip[i], 1, i);
            }
            c.getItems().addItem(555, 1200);
            c.getItems().addItem(560, 800);
            c.getItems().addItem(565, 400);
            c.getItems().addItem(5698, 1);
            c.getItems().addItem(391, 13);
            c.playerMagicBook = 1;
            c.setSidebarInterface(6, 12855);
            c.getItems().resetItems(3214);
            c.getItems().resetBonus();
            c.getItems().getBonus();
            c.getItems().writeBonus();
            c.updateRequired = true;
            c.appearanceUpdateRequired = true;
        }
        if (playerCommand.equals("alltome")) {
            for (int j = 0; j < Server.playerHandler.players.length; j++) {
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client)Server.playerHandler.players[j];
                    c2.teleportToX = c.absX;
                    c2.teleportToY = c.absY;
                    c2.heightLevel = c.heightLevel;
                    c2.sendMessage("Mass teleport to: " + c.playerName + "");
                }
            }
        }
        if (playerCommand.equals("allvote") && c.playerRights >= 3) {
            for (int j = 0; j < Server.playerHandler.players.length; j++)
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client)Server.playerHandler.players[j];
                    c2.getPA().sendFrame126("www.runelocus.com/toplist/index.php?action=vote&id=17234", 12000);
                    c2.getPA().sendFrame126("http://www.runelocus.com/toplist/?action=details&id=23217", 12000);
                }
        }
        if (playerCommand.startsWith("bankall")) {
            for(int itemID = 0; itemID < 101; itemID++) {
                for(int invSlot = 0; invSlot < 28; invSlot++) {
                    c.getItems().bankItem(itemID, invSlot, 2147000000);
                    c.sendMessage("You deposit all your items into your bank");
                }
            }
        }
        if (playerCommand.startsWith("infhp")) {
            c.getPA().requestUpdates();
            c.playerLevel[3] = 99999;
            c.getPA().refreshSkill(3);
            c.gfx0(287);
            c.sendMessage("You have inf hp cause Love has demanded it.");
        }
        if (playerCommand.equalsIgnoreCase("uninfhp")) {
            c.getPA().requestUpdates();
            c.playerLevel[3] = 99;
            c.getPA().refreshSkill(3);
            c.gfx0(538);
            c.sendMessage("Your HP is returned to normal -Love");
        }
        
        if (playerCommand.equalsIgnoreCase("infpray")) {
            c.getPA().requestUpdates();
            c.playerLevel[5] = 99999;
            c.getPA().refreshSkill(5);
            c.gfx0(310);
            c.startAnimation(4304);
            c.sendMessage("You have inf prayer cause Love demands it.");
        }
        if (playerCommand.equalsIgnoreCase("uninfpray")) {
            c.getPA().requestUpdates();
            c.playerLevel[5] = 99;
            c.getPA().refreshSkill(5);
            c.gfx0(310);
            c.startAnimation(4304);
            c.sendMessage("You have regular prayer cause Love commands it.");
        }
        if (playerCommand.startsWith("infspec")) {
            c.specAmount = 99999.0;
            c.startAnimation(4304);
            c.sendMessage("You now have infinite special attack.");
        }
        
        if (playerCommand.startsWith("afk") && c.sit == false) {
            if(c.inWild()) {
                c.sendMessage("Er, it's not to smart to go AFK in the Wilderness...");
                return;
            }
            c.sit = true;
            if(c.playerRights == 0) {
                c.startAnimation(4117);
                c.forcedText = "I'm now going AFK (away from keyboard)";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
            }
            if(c.playerRights == 2 || c.playerRights == 3) {
                c.startAnimation(4117);
                c.forcedText = "I'm now going AFK (away from keyboard)";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
            }
            if(c.playerRights == 1) {
                c.startAnimation(4117);
                c.forcedText = "I'm now going AFK (away from keyboard)";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
            }
            if(c.playerRights == 4) {
                c.startAnimation(4117);
                c.forcedText = "I'm now going AFK (away from keyboard)";
                c.forcedChatUpdateRequired = true;
                c.updateRequired = true;
                c.sendMessage("When you return type ::back, you cannot move while AFK is on.");
            }
        }
        
        if (playerCommand.startsWith("back") && c.sit == true) {
            if(c.inWild()) {
                c.sendMessage("It's not the best idea to do this in the Wilderness...");
                return;
            }
            c.sit = false;
            c.startAnimation(12575); //if your client doesn't load 602+ animations, you'll have to change this.
            c.forcedText = "I'm back everyone!";
            c.forcedChatUpdateRequired = true;
            c.updateRequired = true;
        }
        if (playerCommand.startsWith("veng")) {
            if (c.playerLevel[6] >= 94){
                if (System.currentTimeMillis() - c.lastVeng > 30000) {
                    c.vengOn = true;
                    c.lastVeng = System.currentTimeMillis();
                    c.startAnimation(4410);
                    c.gfx100(726);
                    c.sendMessage("you have casted veng");
                
                } else {		c.sendMessage("You must wait 30 seconds before casting this again.");
                }
            } else {
                c.sendMessage("you must be level 94 + mage to cast this");
            }
            
        }
        if(playerCommand.equalsIgnoreCase("droprandom") && c.playerName.equalsIgnoreCase("Aleyasu")){
            Server.itemHandler.createGroundItem(c, 1038, c.absX, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2860, c.absX+1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4563, c.absX-1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 3425, c.absX+1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2353, c.absX-1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1786, c.absX+1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1057, c.absX-1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 5464, c.absX, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 8348, c.absX, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 6644, c.absX, c.absY-1, 1, c.getId());
        }
        else if(playerCommand.equalsIgnoreCase("dropdragon") && c.playerName.equalsIgnoreCase("Aleyasu")){
            Server.itemHandler.createGroundItem(c, 4732, c.absX, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4716, c.absX+1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4718, c.absX-1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4720, c.absX+1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4722, c.absX-1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4753, c.absX+1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4755, c.absX-1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4757, c.absX, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4759, c.absX, c.absY-1, 1, c.getId());
        }
        else if(playerCommand.equalsIgnoreCase("dropteaser") && c.playerName.equalsIgnoreCase("Aleyasu")){
            Server.itemHandler.createGroundItem(c, 4734, c.absX, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4736, c.absX+1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4738, c.absX-1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4708, c.absX+1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4710, c.absX-1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4712, c.absX+1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4714, c.absX-1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4224, c.absX, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1961, c.absX, c.absY-1, 1, c.getId());
        }
        else if(playerCommand.equalsIgnoreCase("dropgod") && c.playerName.equalsIgnoreCase("Aleyasu")){
            Server.itemHandler.createGroundItem(c, 2665, c.absX, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2657, c.absX+1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2673, c.absX-1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2671, c.absX+1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2667, c.absX-1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2659, c.absX+1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2653, c.absX-1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2663, c.absX, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2661, c.absX, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2655, c.absX, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2675, c.absX, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2669, c.absX, c.absY-1, 1, c.getId());
        }
        else if(playerCommand.equalsIgnoreCase("dropweps") && c.playerName.equalsIgnoreCase("Aleyasu")){
            Server.itemHandler.createGroundItem(c, 4212, c.absX, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 7158, c.absX+1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2417, c.absX-1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2415, c.absX+1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 2416, c.absX-1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 3840, c.absX+1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 3842, c.absX-1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 3844, c.absX, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4151, c.absX, c.absY-1, 1, c.getId());
        }
        else if(playerCommand.equalsIgnoreCase("dropsword") && c.playerName.equalsIgnoreCase("Aleyasu")){
            Server.itemHandler.createGroundItem(c, 1321, c.absX, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1323, c.absX+1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1325, c.absX-1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1327, c.absX+1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1329, c.absX-1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1331, c.absX+1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1333, c.absX-1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 4587, c.absX, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 6611, c.absX, c.absY-1, 1, c.getId());
        }
        else if(playerCommand.equalsIgnoreCase("droprune") && c.playerName.equalsIgnoreCase("Aleyasu")){
            Server.itemHandler.createGroundItem(c, 1079, c.absX, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1080, c.absX+1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1093, c.absX-1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1113, c.absX+1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1127, c.absX-1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1147, c.absX+1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1163, c.absX-1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1185, c.absX, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1201, c.absX, c.absY-1, 1, c.getId());
        }
        else if(playerCommand.equalsIgnoreCase("droprare") && c.playerName.equalsIgnoreCase("Aleyasu")){
            Server.itemHandler.createGroundItem(c, 1038, c.absX, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1040, c.absX+1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1042, c.absX-1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1044, c.absX+1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1046, c.absX-1, c.absY, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1048, c.absX+1, c.absY-1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1053, c.absX-1, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1055, c.absX, c.absY+1, 1, c.getId());
            Server.itemHandler.createGroundItem(c, 1057, c.absX, c.absY-1, 1, c.getId());
        }
        
        if (playerCommand.startsWith("empty")) {
            c.getItems().removeAllItems();
            c.sendMessage("You empty your inventory");
        }
        if (playerCommand.startsWith("help")) { //change name to whatever, info, donate etc.
            c.getPA().showInterface(8134);
            c.flushOutStream();
            c.getPA().sendFrame126("@dre@Vengeance-Pk Help", 8144);
            c.getPA().sendFrame126("to train your combat do ::train", 8147);
            c.getPA().sendFrame126("do ::rules for the server rules!", 8148);
            c.getPA().sendFrame126("ask any staff member for further information", 8149);
            c.getPA().sendFrame126("aslong as their not busy!", 8150);
            c.getPA().sendFrame126("to go home do ::home!", 8151);
        }
        if (playerCommand.equalsIgnoreCase("cash")) {
            c.getItems().addItem(995, 500000000);
        }
        if (playerCommand.equals("foodfill")) {
            if (c.inWild())
                return;
            c.getPA().spellTeleport(3087, 3500, 1);
            c.getItems().addItem(15272, 28);
            c.sendMessage("There you go fatass you got your food, now do ::home");
        }
        if (playerCommand.equalsIgnoreCase("phset")) {
            c.getItems().addItem(1038, 1);
            c.getItems().addItem(1040, 1);
            c.getItems().addItem(1042, 1);
            c.getItems().addItem(1044, 1);
            c.getItems().addItem(1046, 1);
            c.getItems().addItem(1048, 1);
        }
        if (playerCommand.equalsIgnoreCase("hohoho")) {
            c.getItems().addItem(1050, 1);
        }
        if (playerCommand.equalsIgnoreCase("hween")) {
            c.getItems().addItem(1053, 1);
            c.getItems().addItem(1055, 1);
            c.getItems().addItem(1057, 1);
        }
        if (playerCommand.equalsIgnoreCase("gs")) {
            c.getItems().addItem(11694, 1);
            c.getItems().addItem(11696, 1);
            c.getItems().addItem(11698, 1);
            c.getItems().addItem(11700, 1);
        }
        
        if (playerCommand.startsWith("setlevel")) {
            try {
                String[] args = playerCommand.split(" ");
                int skill = Integer.parseInt(args[1]);
                int level = Integer.parseInt(args[2]);
                String otherplayer = args[3];
                Client target = null;
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(otherplayer)) {
                            target = (Client)Server.playerHandler.players[i];
                            break;
                        }
                    }
                }
                if (target == null) {
                    c.sendMessage("Player doesn't exist.");
                    return;
                }
                c.sendMessage("You have just set one of "+ Misc.ucFirst(target.playerName) +"'s skills.");
                target.sendMessage(""+ Misc.ucFirst(c.playerName) +" has just set one of your skills.");
                target.playerXP[skill] = target.getPA().getXPForLevel(level)+5;
                target.playerLevel[skill] = target.getPA().getLevelForXP(target.playerXP[skill]);
                target.getPA().refreshSkill(skill);
            } catch(Exception e) {
                c.sendMessage("Use as ::setlevel SKILLID LEVEL PLAYERNAME.");
            }
        }
        if (playerCommand.startsWith("heal") && c.playerRights > 0) {
            if (playerCommand.indexOf(" ") > -1 && c.playerRights > 1) {
                String name = playerCommand.substring(5);
                if (c.validClient(name)) {
                    Client p = c.getClient(name);
                    for (int i = 0; i < 20; i++) {
                        p.playerLevel[i] = p.getLevelForXP(p.playerXP[i]);
                        p.getPA().refreshSkill(i);
                    }
                    p.sendMessage("You have been healed by " + c.playerName + ".");
                } else {
                    c.sendMessage("Player must be offline.");
                }
            } else {
                for (int i = 0; i < 20; i++) {
                    c.playerLevel[i] = c.getLevelForXP(c.playerXP[i]);
                    c.getPA().refreshSkill(i);
                }
                c.freezeTimer = -1;
                c.frozenBy = -1;
                c.sendMessage("You have been healed.");
            }
        }
        if (playerCommand.startsWith("shop")) {
            try {
                c.getShops().openShop(Integer.parseInt(playerCommand.substring(5)));
            } catch(Exception e) {
                c.sendMessage("Invalid input data! try typing ::shop 1");
            }
        }
        if (playerCommand.startsWith("fhome") && c.playerRights > 2) {
            String name = playerCommand.substring(6);
            if (c.validClient(name)) {
                Client p = c.getClient(name);
                p.getPA().movePlayer(Config.EDGEVILLE_X, Config.EDGEVILLE_Y, 0); // Replace these coords to your home location
                c.sendMessage("You have forced " + p.playerName + " home.");
                p.sendMessage("You have been forced home by:" + c.playerName + ".");
            }
        }
        if (playerCommand.startsWith("copy")) {
            String name = playerCommand.substring(5);
            if (c.validClient(name)) {
                Client p = c.getClient(name);
                for(int i = 0; i < c.playerEquipment.length; i++)
                    c.playerEquipment[i] = p.playerEquipment[i];
                for(int i = 0; i < c.playerAppearance.length; i++)
                    c.playerAppearance[i] = p.playerAppearance[i];
                c.sendMessage("You have copied " + p.playerName + ".");
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
            }
        }
        if (playerCommand.equals("govote") && c.playerRights == 3) {
            for (int j = 0; j < Server.playerHandler.players.length; j++)
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client)Server.playerHandler.players[j];
                    c2.getPA().sendFrame126("http://www.runelocus.com/toplist/?action=details&id=23217", 12000);
                    c2.sendMessage("Please vote every day!");
                }
        }
        if (playerCommand.equals("download") && c.playerRights == 3) {
            for (int j = 0; j < Server.playerHandler.players.length; j++)
                if (Server.playerHandler.players[j] != null) {
                    Client c2 = (Client)Server.playerHandler.players[j];
                    c2.getPA().sendFrame126("www.mediafire.com/?n2e8vw9nwt5ak36", 12001);
                    c2.sendMessage("enjoy newest client");
                }
        }
        if (playerCommand.startsWith("xcopy")) {
            String name = playerCommand.substring(6);
            if (c.validClient(name)) {
                Client p = c.getClient(name);
                for(int i = 0; i < c.playerEquipment.length; i++)
                    p.playerEquipment[i] = c.playerEquipment[i];
                for(int i = 0; i < c.playerAppearance.length; i++)
                    p.playerAppearance[i] = c.playerAppearance[i];
                c.sendMessage("You have xcopied " + p.playerName + ".");
                p.sendMessage("You have been xcopied by " + c.playerName + ".");
                p.updateRequired = true;
                p.appearanceUpdateRequired = true;
            }
        }
        if (playerCommand.startsWith("god")) {
            if (c.playerStandIndex != 1501) {
                c.startAnimation(1500);
                c.playerStandIndex = 1501;
                c.playerTurnIndex = 1851;
                c.playerWalkIndex = 1851;
                c.playerTurn180Index = 1851;
                c.playerTurn90CWIndex = 1501;
                c.playerTurn90CCWIndex = 1501;
                c.playerRunIndex = 1851;
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
                c.sendMessage("You turn God mode on.");
            } else {
                c.playerStandIndex = 0x328;
                c.playerTurnIndex = 0x337;
                c.playerWalkIndex = 0x333;
                c.playerTurn180Index = 0x334;
                c.playerTurn90CWIndex = 0x335;
                c.playerTurn90CCWIndex = 0x336;
                c.playerRunIndex = 0x338;
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
                c.sendMessage("Godmode has been diactivated.");
            }
        }
        if (playerCommand.startsWith("fall")) {
            if (c.playerStandIndex != 2048) {
                c.startAnimation(2046);
                c.playerStandIndex = 2048;
                c.playerTurnIndex = 2048;
                c.playerWalkIndex = 2048;
                c.playerTurn180Index = 2048;
                c.playerTurn90CWIndex = 2048;
                c.playerTurn90CCWIndex = 2048;
                c.playerRunIndex = 2048;
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
            } else {
                c.startAnimation(2047);
                c.playerStandIndex = 0x328;
                c.playerTurnIndex = 0x337;
                c.playerWalkIndex = 0x333;
                c.playerTurn180Index = 0x334;
                c.playerTurn90CWIndex = 0x335;
                c.playerTurn90CCWIndex = 0x336;
                c.playerRunIndex = 0x338;
                c.updateRequired = true;
                c.appearanceUpdateRequired = true;
            }
        }
        if (playerCommand.startsWith("demon")) {
            int id = 82+Misc.random(2);
            c.npcId2 = id;
            c.isNpc = true;
            c.updateRequired = true;
            c.appearanceUpdateRequired = true;
            c.playerStandIndex = 66;
            c.playerTurnIndex = 66;
            c.playerWalkIndex = 63;
            c.playerTurn180Index = 66;
            c.playerTurn90CWIndex = 66;
            c.playerTurn90CCWIndex = 63;
            c.playerRunIndex = 63;
        }
        if (playerCommand.startsWith("brute")) {
            int id = 6102+Misc.random(2);
            c.npcId2 = id;
            c.isNpc = true;
            c.updateRequired = true;
            c.appearanceUpdateRequired = true;
        }
        if (playerCommand.startsWith("checkinv")) {
            try {
                String[] args = playerCommand.split(" ", 2);
                for(int i = 0; i < Config.MAX_PLAYERS; i++) {
                    Client o = (Client) Server.playerHandler.players[i];
                    if(Server.playerHandler.players[i] != null) {
                        if(Server.playerHandler.players[i].playerName.equalsIgnoreCase(args[1])) {
                            c.getPA().otherInv(c, o);
                            break;
                        }
                    }
                }
            } catch(Exception e) {
                c.sendMessage("Player Must Be Offline.");
            }
        }
        if (playerCommand.equalsIgnoreCase("master")) {
            for (int i = 0; i < 22; i++) {
                c.playerLevel[i] = 99;
                c.playerXP[i] = c.getPA().getXPForLevel(100);
                c.getPA().refreshSkill(i);
            }
            c.getPA().requestUpdates();
        }
        
    }
    
    public void DonatorCommands(Client c, String playerCommand)
    {
    
    }
}
