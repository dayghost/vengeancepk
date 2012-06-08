package server.model.players.packets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlayerClip
{
	public static void addClip(String Name) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("./ClippedSpots.txt", true));
		    try {
				out.write(Name);
				out.newLine();
		    } finally {
				out.close();
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}