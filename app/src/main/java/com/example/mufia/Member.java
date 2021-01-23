package com.example.mufia;

public class Member {
    String name;
    String role;
    Boolean cured;      // heal
    Boolean sectarian;  // sectant
    Boolean can_vote;   // golos
    Boolean poisoned;   // otravlen

    public Member(String _name){
        name = _name;
    }

    public Member(String _name, String _role){
        name = _name;
        role = _role;


        cured = false;
        sectarian = false;
        can_vote  = true;
        poisoned = false;

    }
}

