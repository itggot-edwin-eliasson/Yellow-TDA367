package Model;

import Model.Group;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void generateInviteCode(){
        Group g = new Group("Test");
        List<Integer> l = new ArrayList<>();
        g.generateInviteCode(l);
        assertTrue(g.getInviteCode() > 999 || g.getInviteCode() < 10000);
    }

    @Test
    public void generateUniqueInviteCode(){
        Group g = new Group("Test");
        List<Integer> l = new ArrayList<>();
        g.generateInviteCode(l);
        int tmp = g.getInviteCode();
        g.generateInviteCode(l);
        assertTrue(tmp != g.getInviteCode());
    }
}