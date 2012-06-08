package server.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * To let you know, the main method is used for testing. you can simply erase it, or leave it, it doesn't matter.. :p
 * ALSO the toString() method is only used for testing also. just use receiveItem() to get the item ID
 * MAKE SURE when you set up the command or whatever you are gonna use, because to add the constructor shit in it all.
 * it is used to set the username to check the user actually has an item to receive...
 * 
 * After 100+ lines of Java, and 80+ lines of PHP, there you go! ;)
 * 
 * ALSO, the item_names is hooked with the name of the items in the store on the forums!
 * 
 * 
 * Making Automated Donations a hell of a lot easier since June 6, 2012 and around 2 AM ! :)
 * I dedicated a lot of time into this process. Tell me what you think!
 * @author Dayghost
 */
public class CheckDonation {
    private final String URL_BASE = "http://vengeancepk.cu.cc/automated_donation/receiveitem.php?user=";
    private String username;
    
    //Make sure the item name, and the id corresponding to the name matches indexes!
    private String[] item_names = {"Donator Lamp", "Korasis Sword", "Dragon Claws", "Armadyl Godsword", "spirit shield", "Blessed spirit shield",
                                "arcane spirit shield", "divine spirit shield", "elysian spirit shield", "spectral spirit shield", "ring of vigour",
                                "Chaotic Rapier", "Chaotic Longsword", "Chaotic Maul", "Chaotic Staff", "Chaotic Crossbow",
                                "Chaotic Kiteshield", "Christmas Cracker", "Red H'ween Mask", "Blue H'ween Mask", "Green H'ween Mask", "Sled"};
    private int[] item_ids = {13227, 19780, 14484, 11694, 13734, 13736, 13738, 13740, 13742, 13744, 19669,
                            18349, 18351, 18353, 18355, 18357, 18359, 962, 1057, 1055, 1053, 4083};
    
    private ArrayList<String> testline = new ArrayList<>();
    
    private boolean received = false;
    
    private String itemName = null;
    

    public String getItemName() {
        return itemName;
    }
    
    public boolean isReceived() {
        return received;
    }
    public CheckDonation(String name) {
        username = name;
        
    }
    
    /**
     * 
     * @return the item to receive OR received
     */    
    private String getStatus() {
        try {
            URL url = new URL(URL_BASE + username);
            InputStreamReader in = new InputStreamReader(url.openStream());
            BufferedReader read = new BufferedReader(in);
            Scanner sc = new Scanner(read);
            if (sc != null) {
                    String s = sc.nextLine();
                    if (s.contains("received"))
                        received = true;
                    testline.add(s);
                    return s;
            } else
                return "ERROR";
        } catch (IOException e) {
            return "ERROR";
        }
    }
    
    private String[] getParts(String line) {
        String[] array = line.split("<br />");
        return array;
    }
    /**
     * Special IDs:<br />
     * <br />
     * 1 = torva set<br />
     * 2 = pernix set<br />
     * 3 = virtus set<br />
     * 4 = Vesta set w/ Spear<br />
     * 5 = Vesta set w/ Sword<br />
     * 6 = Statius set<br />
     * 7 = Morrigan set w/ Javelins<br />
     * 8 = Morrigan set w/ Thrown Axes<br />
     * 9 = Chaotic set
     * @return the ID of the item UNLESS special
     */
    public int receiveItem() {
        String line = getStatus();
        String[] custom = getParts(line);
        String item = custom[0];
        String field = "";
        //System.out.println(Arrays.toString(custom));
        if (custom.length > 1) {
            field = custom[1];
            item = field;
        }
        item = item.replaceAll("&#39;", "'");
        
        System.out.println(item);
        itemName = item;
        int itemID = 0;
        if (item.equalsIgnoreCase("received") || 
                item.equalsIgnoreCase("No username!") || 
                item.equalsIgnoreCase("ERROR")) {
            itemID = -1;
        }
        else {
            for (int i = 0; i < item_names.length; i++) {
                if (item_names[i].equalsIgnoreCase(item)) {
                    itemID = item_ids[i];
                }
            }
            
        }
        String item2 = item.toLowerCase();
        System.out.println(item2);
        if (item2.contains("set")) {
            System.out.println("SET");
            if (item2.contains("torva")) {
                itemID = 1;
            } else if (item2.contains("pernix")) {
                itemID = 2;
            } else if (item2.contains("virtus")) {
                itemID = 3;
            } else if (item2.contains("vesta")) {
                if (item2.contains("spear")) {
                    itemID = 4;
                } else if (item2.contains("sword")) {
                    itemID = 5;
                }
            } else if (item2.contains("statius")) {
                System.out.println("Statius");
                itemID = 6;
            } else if (item2.contains("morrigans")) {
                if (item2.contains("javelin")) {
                    itemID = 7;
                } else if (item2.contains("axes")) {
                    itemID = 8;
                }
            } else if (item2.contains("chaotic")) {
                itemID = 9;
            }
        }
        return itemID;
    }
    
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append(receiveItem()).append(": Item ID Going to ").append(username);
        string.append("\n");
        string.append("String status = ").append(testline.toString());
        string.append("\n");
        string.append("URL Check = ").append(URL_BASE).append(username);
        
        return string.toString();
    }
    
    
    public static void main1(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a username to test with >>> ");
        String username = sc.nextLine();
        
        CheckDonation donate = new CheckDonation(username);
        System.out.println(donate);
    }
}
