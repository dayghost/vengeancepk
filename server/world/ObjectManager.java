package server.world;

import java.util.ArrayList;

import server.model.objects.Object;
import server.util.Misc;
import server.model.players.Client;
import server.Server;

/**
 * @author Sanity
 */

public class ObjectManager {

	public ArrayList<Object> objects = new ArrayList<Object>();
	private ArrayList<Object> toRemove = new ArrayList<Object>();
	public void process() {
		for (Object o : objects) {
			if (o.tick > 0)
				o.tick--;
			else {
				updateObject(o);
				toRemove.add(o);
			}		
		}
		for (Object o : toRemove) {
			if (isObelisk(o.newId)) {
				int index = getObeliskIndex(o.newId);
				if (activated[index]) {
					activated[index] = false;
					teleportObelisk(index);
				}
			}
			objects.remove(o);	
		}
		toRemove.clear();
	}
	
	public void removeObject(int x, int y) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(-1, x, y, 0, 10);			
			}	
		}	
	}
	
	public void updateObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				c.getPA().object(o.newId, o.objectX, o.objectY, o.face, o.type);			
			}	
		}	
	}
	
	public void placeObject(Object o) {
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				if (c.distanceToPoint(o.objectX, o.objectY) <= 60)
					c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
			}	
		}
	}
	
	public Object getObject(int x, int y, int height) {
		for (Object o : objects) {
			if (o.objectX == x && o.objectY == y && o.height == height)
				return o;
		}	
		return null;
	}
	
	public void loadObjects(Client c) {
		if (c == null)
			return;
		for (Object o : objects) {
			if (loadForPlayer(o,c))
				c.getPA().object(o.objectId, o.objectX, o.objectY, o.face, o.type);
		}
		loadCustomSpawns(c);
		if (c.distanceToPoint(2813, 3463) <= 60) {
			c.getFarming().updateHerbPatch();
		}
	}
	
	private int[][] customObjects = {{}};
	public void loadCustomSpawns(Client c) {
//	c.getPA().checkObjectSpawn(3192, 3084, 3488, 0, 10); //hs board
	c.getPA().checkObjectSpawn(4151, 2605, 3153, 1, 10); //portal home FunPk
		c.getPA().checkObjectSpawn(2619, 2602, 3156, 1, 10); //barrel FunPk
		c.getPA().checkObjectSpawn(1032, 2605, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2603, 3156, 2, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3155, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(1032, 2602, 3153, 1, 10); //warning sign FunPk
		c.getPA().checkObjectSpawn(-1, 3077, 3491, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3496, 1, 10);
		c.getPA().checkObjectSpawn(-1, 3077, 3491, 1, 10);
				c.getPA().checkObjectSpawn(-1, 3080, 3501, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2599, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2598, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4780, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4779, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4778, 1, 10);	
		c.getPA().checkObjectSpawn(1, 2597, 4777, 1, 10);
		c.getPA().checkObjectSpawn(1, 2598, 4777, 1, 10);
		c.getPA().checkObjectSpawn(2286, 2598, 4778, 1, 10);
		c.getPA().checkObjectSpawn(12356, 3094, 3487, 1, 10);
				c.getPA().checkObjectSpawn(2403, 3095, 3487, 2, 10);
		c.getPA().checkObjectSpawn(2996, 3077, 3491, 1, 10);//al key chest
	
		c.getPA().checkObjectSpawn(14859, 2839, 3439, 0, 10);//runite ore skilling.
	
		c.getPA().checkObjectSpawn(13617, 2044, 4521, 1, 10); //Barrelportal donor	
		
		c.getPA().checkObjectSpawn(411, 3243, 3215, 2, 10); // Curse Prayers
		c.getPA().checkObjectSpawn(411, 3093, 3506, 0, 10); // Curse Prayers
		c.getPA().checkObjectSpawn(410, 3098, 3504, 0, 10);

		c.getPA().checkObjectSpawn(13615, 2036, 4518, 0, 10);
		c.getPA().checkObjectSpawn(13620, 2041, 4518, 0, 10);
		c.getPA().checkObjectSpawn(13619, 2031, 4518, 0, 10);

		c.getPA().checkObjectSpawn(6163, 2029, 4527, 1, 10);
		c.getPA().checkObjectSpawn(6165, 2029, 4529, 1, 10);
		c.getPA().checkObjectSpawn(6166, 2029, 4531, 1, 10);

		c.getPA().checkObjectSpawn(410, 2694, 9564, 0, 10); 

		c.getPA().checkObjectSpawn(4874, 3084, 3483, 1, 10);
		c.getPA().checkObjectSpawn(4875, 3085, 3483, 1, 10);
		c.getPA().checkObjectSpawn(4876, 3086, 3483, 0, 10);
		c.getPA().checkObjectSpawn(4877, 3087, 3483, 0, 10);
		c.getPA().checkObjectSpawn(4878, 3088, 3483, 0, 10);

		c.getPA().checkObjectSpawn(1596, 3008, 3850, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3008, 3849, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10307, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3040, 10308, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10311, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3022, 10312, 1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10341, -1, 0);
		c.getPA().checkObjectSpawn(1596, 3044, 10342, 1, 0);
		c.getPA().checkObjectSpawn(6552, 3232, 2900, 2, 10); //ancient prayers
		c.getPA().checkObjectSpawn(6552, 3096, 3500, 0, 10); //ancient prayers
		c.getPA().checkObjectSpawn(409, 3095, 3506, 2, 10);
		c.getPA().checkObjectSpawn(2213, 3047, 9779, 1, 10);
		c.getPA().checkObjectSpawn(2213, 3080, 9502, 1, 10);
		c.getPA().checkObjectSpawn(1530, 3097, 3487, 1, 10);

                                          //X     Y     ID -> ID X Y
		c.getPA().checkObjectSpawn(2213, 2855, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2090, 2839, 3440, -1, 10);
		c.getPA().checkObjectSpawn(2094, 2839, 3441, -1, 10);
		c.getPA().checkObjectSpawn(2092, 2839, 3442, -1, 10);
		c.getPA().checkObjectSpawn(2096, 2839, 3443, -1, 10);
		c.getPA().checkObjectSpawn(2102, 2839, 3444, -1, 10);
		c.getPA().checkObjectSpawn(2105, 2839, 3445, 0, 10);
		c.getPA().checkObjectSpawn(1276, 2843, 3442, 0, 10);
		c.getPA().checkObjectSpawn(1281, 2844, 3499, 0, 10);
		c.getPA().checkObjectSpawn(4156, 3083, 3440, 0, 10);
		c.getPA().checkObjectSpawn(1308, 2846, 3436, 0, 10);
		c.getPA().checkObjectSpawn(1309, 2846, 3439, -1, 10);
		c.getPA().checkObjectSpawn(1306, 2850, 3439, -1, 10);
		c.getPA().checkObjectSpawn(2783, 2841, 3436, 0, 10);
		c.getPA().checkObjectSpawn(2728, 2861, 3429, 0, 10);
		c.getPA().checkObjectSpawn(3044, 2857, 3427, -1, 10);
		c.getPA().checkObjectSpawn(320, 3048, 10342, 0, 10);
				c.getPA().checkObjectSpawn(104, 2032, 4526, 1, 10); //Donatorchest
		c.getPA().checkObjectSpawn(-1, 2844, 3440, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2846, 3437, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2840, 3439, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2841, 3443, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2851, 3438, -1, 10);

		c.getPA().checkObjectSpawn(-1, 2009, 4752, -1, 10);//staffzone
		c.getPA().checkObjectSpawn(-1, 2010, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2011, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2012, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2013, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2014, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2015, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2016, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2009, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2010, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2011, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2012, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2013, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2014, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2015, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2016, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2009, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2010, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2011, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2012, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2013, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2014, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2015, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2016, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2009, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2010, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2011, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2012, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2013, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2014, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2015, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2016, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2009, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2010, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2011, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2012, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2013, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2014, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2015, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2016, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2009, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2010, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2011, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2012, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2013, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2014, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2015, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2016, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2009, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2010, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2011, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2012, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2013, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2014, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2015, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2016, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2008, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2007, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2006, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2005, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2004, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2003, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2008, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2007, 4752, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2006, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2005, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2004, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2003, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2008, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2007, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2006, 4753, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2005, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2004, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2003, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2008, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2007, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2006, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2005, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2004, 4754, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2003, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2008, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2007, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2006, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2005, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2004, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2003, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2008, 4755, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2007, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2006, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2005, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2004, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2003, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2008, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2007, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2006, 4756, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2005, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2004, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2003, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2008, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2007, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2006, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2005, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2004, 4757, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2003, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2008, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2007, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2006, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2005, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2004, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2003, 4758, -1, 10);
		c.getPA().checkObjectSpawn(-1, 2016, 4758, -1, 10);

		c.getPA().checkObjectSpawn(2213, 2010, 4748, 0, 10);//addeditems
		c.getPA().checkObjectSpawn(2213, 2009, 4748, 0, 10);
		c.getPA().checkObjectSpawn(2864, 2005, 4755, -1, 10);
		c.getPA().checkObjectSpawn(2864, 2014, 4755, -1, 10);
		


		










	 if (c.heightLevel == 0) {
			c.getPA().checkObjectSpawn(2492, 2911, 3614, 1, 10);
		 }else{
			c.getPA().checkObjectSpawn(-1, 2911, 3614, 1, 10);
	}
	}
	
	public final int IN_USE_ID = 14825;
	public boolean isObelisk(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return true;
		}
		return false;
	}
	public int[] obeliskIds = {14829,14830,14827,14828,14826,14831};
	public int[][] obeliskCoords = {{3154,3618},{3225,3665},{3033,3730},{3104,3792},{2978,3864},{3305,3914}};
	public boolean[] activated = {false,false,false,false,false,false};
	
	public void startObelisk(int obeliskId) {
		int index = getObeliskIndex(obeliskId);
		if (index >= 0) {
			if (!activated[index]) {
				activated[index] = true;
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1], 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0], obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
				addObject(new Object(14825, obeliskCoords[index][0] + 4, obeliskCoords[index][1] + 4, 0, -1, 10, obeliskId,16));
			}
		}	
	}
	
	public int getObeliskIndex(int id) {
		for (int j = 0; j < obeliskIds.length; j++) {
			if (obeliskIds[j] == id)
				return j;
		}
		return -1;
	}
	
	public void teleportObelisk(int port) {
		int random = Misc.random(5);
		while (random == port) {
			random = Misc.random(5);
		}
		for (int j = 0; j < Server.playerHandler.players.length; j++) {
			if (Server.playerHandler.players[j] != null) {
				Client c = (Client)Server.playerHandler.players[j];
				int xOffset = c.absX - obeliskCoords[port][0];
				int yOffset = c.absY - obeliskCoords[port][1];
				if (c.goodDistance(c.getX(), c.getY(), obeliskCoords[port][0] + 2, obeliskCoords[port][1] + 2, 1)) {
					c.getPA().startTeleport2(obeliskCoords[random][0] + xOffset, obeliskCoords[random][1] + yOffset, 0);
				}
			}		
		}
	}
	
	public boolean loadForPlayer(Object o, Client c) {
		if (o == null || c == null)
			return false;
		return c.distanceToPoint(o.objectX, o.objectY) <= 60 && c.heightLevel == o.height;
	}
	
	public void addObject(Object o) {
		if (getObject(o.objectX, o.objectY, o.height) == null) {
			objects.add(o);
			placeObject(o);
		}	
	}




}