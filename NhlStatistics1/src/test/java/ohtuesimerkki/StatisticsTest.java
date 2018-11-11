package ohtuesimerkki;

import java.util.List;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    
 
    private Reader readerStub;
 
    Statistics stats;

    @Before
    public void setUp(){
        ArrayList<Player> players = new ArrayList<Player>();
     
        players.add(new Player("Semenko", "EDM", 4, 12));
        players.add(new Player("Lemieux", "PIT", 45, 54));
        players.add(new Player("Kurri",   "EDM", 37, 53));
        players.add(new Player("Yzerman", "DET", 42, 56));
        players.add(new Player("Gretzky", "EDM", 35, 89));
        // luodaan Statistics-olio joka käyttää "stubia"
        
        readerStub = new Reader() {
            public List<Player> getPlayers() {
                return players;
            }
        };
        
        stats = new Statistics(readerStub);
    }  

    @Test
    public void searchLoytaaPelaajan() {
        assertEquals(stats.search("Semenko"), readerStub.getPlayers().get(0));
    }

    @Test
    public void olematonPelaajaEiLoydy() {
        assertEquals(stats.search("Pöö"), null);
    }

    @Test
    public void teamPalauttaaOikein() {
        List<Player> team = stats.team("EDM");

        assertEquals(team.get(0), readerStub.getPlayers().get(0));
        assertEquals(team.get(1), readerStub.getPlayers().get(2));
        assertEquals(team.get(2), readerStub.getPlayers().get(4));
        assertEquals(team.size(), 3);
    }

    @Test
    public void olematonTiimiEiLoydy() {
        ArrayList<Player> players = new ArrayList<Player>();
        assertEquals(stats.team("Pöö"), players);
    }

    @Test
    public void topScorersPalauttaaOikein() {
        List<Player> top = stats.topScorers(2);

        assertEquals(89+35, top.get(0).getPoints());
        assertEquals("Lemieux", top.get(1).getName());
        assertEquals(top.size(), 2);
    }
}