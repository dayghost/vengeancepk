/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Devin
 */
public class LoadAnnouncements {
    
    private final String URL = "http://vengeancepk.cu.cc/server/announcements/load.php";
    
    private Scanner sc;
    
    
    private ArrayList<String> author = new ArrayList<>();
    private ArrayList<String> announce = new ArrayList<>();
    private ArrayList<Integer> ids = new ArrayList<>();
    
    
    public LoadAnnouncements() {
        try {
            InputStreamReader in = new InputStreamReader(new URL(URL).openStream());
            BufferedReader read = new BufferedReader(in);
            sc = new Scanner(read);
        } catch (FileNotFoundException ex) { System.out.println("File Not Found");
        } catch (Exception x) { System.out.println("File Not Found"); }
    }
    
    public void reload() {
        if (sc == null) {
            System.out.println("Scanner is null");
            return;
        }
        ArrayList<String> array = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (s.equals("[END]"))
                break;
            array.add(s);
            
        }
        
        array = trimArray(array);
        
        for (int i = 0; i < array.size(); i++) {
            String[] s = array.get(i).split(" - ");
            ids.add(Integer.parseInt(s[0]));
            author.add(s[1]);
            announce.add(s[2]);
            
        }
    }
    
    private ArrayList<String> trimArray(ArrayList<String> array) {
        ArrayList<String> a = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            String ln = array.get(i);
            ln = ln.replace("\n", "");
            a.add(ln);
        }
        return a;
    }
    
    public ArrayList<String> getAuthor() {
        return author;
    }

    public ArrayList<Integer> getIds() {
        return ids;
    }

    public ArrayList<String> getAnnounce() {
        return announce;
    }
    
}
