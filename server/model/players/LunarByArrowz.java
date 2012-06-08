package server.model.players.Content;

import server.Config;
import server.Server;
import server.event.EventManager;
import server.event.Event;
import server.event.EventContainer;
import server.util.Misc;
import server.model.players.Client;

/**
* Skill Guides class. Handles the ingame skill menus.
* @author Arrowzftw aka arrowzflame
*/

//9075 = astral
//554 = fire
//555 = water
//556 = air
//557 = earth
//558 = mind
//559 = body
//560 = death 
//561 = nats
//562 = chaos
//563 = law
//564 = cosmic
//565 = blood
//566 = soul


public class LunarByArrowz {

	public Client Client;

	public LunarByArrowz(Client c) {
		c = c;
	}

public int astral = 9075;
public int fire = 554;
public int water = 555;
public int air = 556;
public int earth = 557;
public int mind = 558;
public int body = 559;
public int death = 560;
public int nats = 561;
public int chaos = 562;
public int law = 563;
public int cosmic = 564;
public int blood = 565;
public int soul = 566;
public int timer;

//c.getItems().playerHasItem(fire, 5)

public void magicOnItems(Client c, int slot, int itemId, int spellId) {
	switch(spellId) {


case 30017:
timer = 6;
if (c.playerLevel[6] > 65) {
if(c.getItems().playerHasItem(astral, 1) && c.getItems().playerHasItem(fire, 5) && c.getItems().playerHasItem(water, 4) && itemId == 2317 || itemId == 2319 || itemId == 2321) {
switch(itemId) {

case 2317:
timer = 6;
c.startAnimation(4413);
c.gfx100(746);
c.getItems().deleteItem(itemId, 1);
c.getItems().deleteItem(astral, 1);
c.getItems().deleteItem(fire, 5);
c.getItems().deleteItem(water, 4);
c.getItems().addItem(2323, 1);
c.getPA().addSkillXP(1024, 6);
c.sendMessage("You bake the pie");
break;

case 2319:
timer = 6;
c.startAnimation(4413);
c.gfx100(746);
c.getItems().deleteItem(itemId, 1);
c.getItems().deleteItem(astral, 1);
c.getItems().deleteItem(fire, 5);
c.getItems().deleteItem(water, 4);
c.getItems().addItem(2327, 1);
c.getPA().addSkillXP(1024, 6);
c.sendMessage("You bake the pie");
break;




case 2321:
timer = 6;
c.startAnimation(4413);
c.gfx100(746);
c.getItems().deleteItem(itemId, 1);
c.getItems().deleteItem(astral, 1);
c.getItems().deleteItem(fire, 5);
c.getItems().deleteItem(water, 4);
c.getItems().addItem(2325, 1);
c.getPA().addSkillXP(1024, 6);
c.sendMessage("You bake the pie");
}
} else {
timer = 6;
c.sendMessage("You do not have the required runes to cast this spell");
c.sendMessage("Or you are casting this spell on the wrong item");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;

case 30154:
if (c.playerLevel[6] > 77) {
c.req = 77;
timer = 6;
if(c.getItems().playerHasItem(astral, 2) && c.getItems().playerHasItem(fire, 6) && c.getItems().playerHasItem(air, 10))
{
if(itemId == 1783)
{
timer = 6;
c.startAnimation(4412);
c.gfx0(729);
c.getItems().deleteItem(itemId, 1);
c.getItems().deleteItem(astral, 2);
c.getItems().deleteItem(fire, 6);
c.getItems().deleteItem(air, 10);
c.getItems().addItem(1775, 1);
c.getPA().addSkillXP(1600, 6);
} else {
timer = 6;
c.sendMessage("This spell only works with a bucket of sand");
}
} else {
c.sendMessage("You do not have the required runes to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;






} // closes switch
} // closes magiconitems

//START OF CLICK BUTTON LUNAR



public void Button(Client c, int actionButtonId) {
		
		//int actionButtonId = c.getInStream().readShort();

			switch (actionButtonId){
case 117104:
timer = 6;
if(c.playerLevel[6] > 68)
{
if(c.getItems().playerHasItem(astral, 1) && c.getItems().playerHasItem(fire, 1) && c.getItems().playerHasItem(water, 3))
{
if(c.getItems().playerHasItem(229, 1))
{
timer = 6;
c.startAnimation(6294);
c.gfx0(1061);
c.getItems().deleteItem(astral, 1);
c.getItems().deleteItem(fire, 1);
c.getItems().deleteItem(water, 3);
c.getItems().deleteItem(229, 1);
c.getItems().addItem(227, 1);
c.getPA().addSkillXP(200, 6);
} else {
timer = 6;
c.sendMessage("You have ran out of empty vials");
}
} else {
timer = 6;
c.sendMessage("You do not have the runes required to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;

case 117147:

if(c.playerLevel[6] > 71)
{
timer = 6;
if(c.getItems().playerHasItem(astral, 2) && c.getItems().playerHasItem(earth, 2))
{
c.startAnimation(6303);
c.gfx0(1074);
c.getItems().deleteItem(astral, 1);
c.getItems().deleteItem(earth, 1);
c.getItems().addItem(11159, 1);
c.sendMessage("You Get a hunter kit");
c.getPA().addSkillXP(700, 6);
} else {
c.sendMessage("You do not have the runes required to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}

break;



case 117170:
if(c.playerLevel[6] > 74)
{
timer = 6;

if(c.getItems().playerHasItem(astral, 2) && c.getItems().playerHasItem(cosmic, 2) && c.getItems().playerHasItem(law, 2))
{
c.getItems().deleteItem(astral, 2);
c.getItems().deleteItem(cosmic, 2);
c.getItems().deleteItem(law, 2);
c.cureAll();
} else {
c.sendMessage("You do not have the runes required to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;


case 118106:
if(c.playerLevel[6] > 95)
{
timer = 6;

if(c.getItems().playerHasItem(astral, 4) && c.getItems().playerHasItem(blood, 3) && c.getItems().playerHasItem(law, 6))
{
c.getItems().deleteItem(9075, 4);
c.getItems().deleteItem(565, 2);
c.getItems().deleteItem(563, 6);
c.HealAll();
} else {
c.sendMessage("You do not have the runes required to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;

case 117139:
if(c.playerLevel[6] > 71)
{
timer = 6;
if(c.getItems().playerHasItem(astral, 2) && c.getItems().playerHasItem(cosmic, 2) && c.getItems().playerHasItem(law, 1))
{
c.getItems().deleteItem(astral, 2);
c.getItems().deleteItem(cosmic, 2);
c.getItems().deleteItem(law, 1);
c.poisonDamage = -1;
c.startAnimation(4411);
c.gfx0(748);
c.getPA().addSkillXP(500, 6);
} else {
c.sendMessage("You do not have the runes required to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break; 

case 118034:
if(c.playerLevel[6] > 86)
{
timer = 6;
if(c.getItems().playerHasItem(astral, 3) && c.getItems().playerHasItem(earth, 15) && c.getItems().playerHasItem(nats, 1))
{
c.getItems().deleteItem(astral, 3);
c.getItems().deleteItem(earth, 15);
c.getItems().deleteItem(nats, 1);
c.getItems().addItem(960, 1);
c.startAnimation(6298);
c.gfx0(1063);
c.getPA().addSkillXP(400, 6);
} else {
c.sendMessage("You do not have the runes required to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;

case 118010:
timer = 6;
c.sendMessage("Coming soon");
break;

case 117242:
timer = 6;

if(c.playerLevel[6] > 81)
{
if(c.getItems().playerHasItem(astral, 2) && c.getItems().playerHasItem(earth, 10) && c.getItems().playerHasItem(water, 10))
{
if(c.getItems().playerHasItem(3024, 1))
{
c.getItems().deleteItem(astral, 2);
c.getItems().deleteItem(earth, 10);
c.getItems().deleteItem(water, 10);
c.getItems().deleteItem(3024, 1);
c.getItems().addItem(229, 1);
c.getPA().addSkillXP(3000, 6);
c.statrestore();
} else {
c.sendMessage("You Need a Super Restore potion to cast this spell");
}
} else {
c.sendMessage("You do not have enough runes to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}

break;





case 118114:
timer = 6;
if(c.playerLevel[6] > 96)
{
if(c.getItems().playerHasItem(astral, 3) && c.getItems().playerHasItem(cosmic, 2) && c.getItems().playerHasItem(law, 1)) {
if(c.playerMagicBook == 0)
{
c.startAnimation(6299);
c.gfx0(1062);
c.getItems().deleteItem(astral, 3);
c.getItems().deleteItem(cosmic, 2);
c.getItems().deleteItem(law, 1);
c.getPA().addSkillXP(1000, 6);
c.setSidebarInterface(6, 16640);
c.playerMagicBook = 2;
c.sendMessage("Your mind becomes stirred with thoughs of dreams.");
}

if(c.playerMagicBook == 2)
{
c.startAnimation(6299);
c.gfx0(1062);
c.getItems().deleteItem(astral, 3);
c.getItems().deleteItem(cosmic, 2);
c.getItems().deleteItem(law, 1);
c.getPA().addSkillXP(1000, 6);
c.setSidebarInterface(6, 12855);
c.sendMessage("An ancient wisdomin fills your mind.");
c.playerMagicBook = 1;
}
if(c.playerMagicBook == 1)
{
c.startAnimation(6299);
c.gfx0(1062);
c.getItems().deleteItem(astral, 3);
c.getItems().deleteItem(cosmic, 2);
c.getItems().deleteItem(law, 1);
c.getPA().addSkillXP(1000, 6);
c.setSidebarInterface(6, 1151); //modern
c.sendMessage("You feel a drain on your memory.");
c.playerMagicBook = 0;
}


} else {
c.sendMessage("You do not have enough runes to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;

case 117226:
timer = 6;
c.req = 79;
if(c.getItems().playerHasItem(astral, 2) && c.getItems().playerHasItem(cosmic, 1) && c.getItems().playerHasItem(body, 5)) {
if(System.currentTimeMillis() - c.logoutDelay > 10000)
{

c.getItems().deleteItem(astral, 2);
c.getItems().deleteItem(cosmic, 1);
c.getItems().deleteItem(body, 5);
c.getPA().addSkillXP(1000, 6);
c.getPA().resetFollow();
c.Dream();
c.dream = 5;
}


}

if(c.indream == true)
{
c.sendMessage("You already in deep sleep");
} else {
c.sendMessage("You do not have enough runes to cast this spell");
}
break;


			}

}



public void CastingLunarOnPlayer(Client c, int castingSpellId) {

Client castOnPlayer = (Client) Server.playerHandler.players[c.playerIndex];


switch(castingSpellId) {


case 30130:
timer = 6;
if(c.playerLevel[6] > 75)
{
if(c.getItems().playerHasItem(astral, 2) && c.getItems().playerHasItem(cosmic, 2) && c.getItems().playerHasItem(body, 5)) {
c.getItems().deleteItem(astral, 2);
c.getItems().deleteItem(cosmic, 2);
c.getItems().deleteItem(body, 5);
c.startAnimation(6293);
c.gfx0(1060);
c.getPA().sendFrame126(""+castOnPlayer.playerName+"'s Attack Level: "+castOnPlayer.playerLevel[0]+"/"+castOnPlayer.getLevelForXP(castOnPlayer.playerXP[0])+"", 8147);
c.getPA().sendFrame126(""+castOnPlayer.playerName+"'s Strength Level: "+castOnPlayer.playerLevel[2]+"/"+castOnPlayer.getLevelForXP(castOnPlayer.playerXP[2])+"", 8148);
c.getPA().sendFrame126(""+castOnPlayer.playerName+"'s Defence Level: "+castOnPlayer.playerLevel[1]+"/"+castOnPlayer.getLevelForXP(castOnPlayer.playerXP[1])+"", 8149);
c.getPA().sendFrame126(""+castOnPlayer.playerName+"'s Hitpoints Level: "+castOnPlayer.playerLevel[3]+"/"+castOnPlayer.getLevelForXP(castOnPlayer.playerXP[3])+"", 8150);
c.getPA().sendFrame126(""+castOnPlayer.playerName+"'s Range Level: "+castOnPlayer.playerLevel[4]+"/"+castOnPlayer.getLevelForXP(castOnPlayer.playerXP[4])+"", 8151);
c.getPA().sendFrame126(""+castOnPlayer.playerName+"'s Prayer Level: "+castOnPlayer.playerLevel[5]+"/"+castOnPlayer.getLevelForXP(castOnPlayer.playerXP[5])+"", 8152);
c.getPA().sendFrame126(""+castOnPlayer.playerName+"'s Magic Level: "+castOnPlayer.playerLevel[6]+"/"+castOnPlayer.getLevelForXP(castOnPlayer.playerXP[6])+"", 8153);
c.getPA().showInterface(8134);
castOnPlayer.gfx0(736);
} else {
c.sendMessage("You do not have the required runes to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}

break;



case 30298:
timer = 6;
if(c.playerLevel[6] > 93)
{
if (System.currentTimeMillis() - castOnPlayer.lastVeng > 30000 || System.currentTimeMillis() - c.lastVeng > 30000) {
if (c.getItems().playerHasItem(557,10) && c.getItems().playerHasItem(9075,3) && c.getItems().playerHasItem(560,2)) {
c.getItems().deleteItem(astral, 3);
c.getItems().deleteItem(death, 2);
c.getItems().deleteItem(earth, 10);
				castOnPlayer.vengOn = true;
c.lastVeng = System.currentTimeMillis();
				castOnPlayer.lastVeng = System.currentTimeMillis();
				castOnPlayer.gfx100(725);
				c.startAnimation(4411);
} else {
c.sendMessage("You do not have the required runes to cast this spell");
}
} else {
c.sendMessage("Please wait 30 seconds before casting Vengance other");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;


case 30048:
timer = 6;
if(c.playerLevel[6] > 93)
{
if (c.getItems().playerHasItem(earth,10) && c.getItems().playerHasItem(astral,1) && c.getItems().playerHasItem(law,1)) {
c.getItems().deleteItem(astral, 1);
c.getItems().deleteItem(law, 1);
c.getItems().deleteItem(earth, 10);
castOnPlayer.poisonDamage = -1;
c.sendMessage("You have been cured by "+c.playerName+".");
c.startAnimation(4411);
castOnPlayer.gfx100(745);
} else {
c.sendMessage("You do not have the required runes to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;

case 30290: //need to fix heal other
timer = 6;
if(c.playerLevel[6] > 92)
{
if (c.getItems().playerHasItem(law,3) && c.getItems().playerHasItem(astral,1) && c.getItems().playerHasItem(blood,1)) {
c.getItems().deleteItem(astral, 1);
c.getItems().deleteItem(law, 3);
c.getItems().deleteItem(blood, 1);
castOnPlayer.playerLevel[3] += 5;
c.sendMessage("You have been Healed by "+c.playerName+".");
c.startAnimation(4411);
castOnPlayer.gfx100(745);
} else {
c.sendMessage("You do not have the required runes to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;


case 30282: //need to fix heal other
timer = 6;
if(c.playerLevel[6] > 91)
{
if(c.specAmount >= .5){
if (c.getItems().playerHasItem(law,2) && c.getItems().playerHasItem(astral,3) && c.getItems().playerHasItem(nats,1)) {
c.getItems().deleteItem(astral, 3);
c.getItems().deleteItem(law, 2);
c.getItems().deleteItem(nats, 1);
c.specAmount -= .5;
castOnPlayer.specAmount += .5;
c.sendMessage("You have been Given 25% special attack by "+c.playerName+".");
c.startAnimation(4411);
castOnPlayer.gfx100(734);
} else {
c.sendMessage("You do not have the required runes to cast this spell");
}
} else {
c.sendMessage("You need atleast 25% special attack to cast this spell");
}
} else {
c.sendMessage("You need a higher magic level to cast this spell");
}
break;


}
}
}
