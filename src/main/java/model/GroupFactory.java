package model;

import java.util.List;

public class GroupFactory {


    public GroupInterface createGroup (String name, String color, String id, List<Integer> groupInviteCodes) {
        return new Group(name, color, id, groupInviteCodes);
    }
}
