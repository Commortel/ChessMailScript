package fr.home;

import fr.home.entity.Club;
import fr.home.entity.Department;
import java.io.IOException;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Thibaut
 */
public class WintzenheimClubTest {
    
    public WintzenheimClubTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void testDataConstistency() throws IOException {
        Department d = ChessMailScript.getDepartmentWithClub(68, null);
        Club wintzenheim = d.getClubs()
                .stream()
                .filter(c -> c.getName().equals("B68043 - WINTZENHEIM"))
                .collect(Collectors.<Club>toList())
                .get(0);
        
         assertEquals(2, wintzenheim.getLeaders().size());
         assertTrue(wintzenheim.getLeaders().contains("echecs.wintzenheim@free.fr"));
         assertTrue(wintzenheim.getLeaders().contains("m.krick@free.fr"));
     }
}
