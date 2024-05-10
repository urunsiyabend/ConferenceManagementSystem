package com.urunsiyabend.conferencemanagementsystem.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    public enum Role{
        ORGANIZER,
        REVIEWER,
        PRESENTER,
        PARTICIPATOR
    }

    private int id;

    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private Collection<Paper> assignedPapers;
    private int maxNumberOfPapers;

    public void assignPaper(Paper paper){
        if(!canAssignPaper()){
            throw new IllegalStateException("Maximum number of papers reached");
        }

        else {
            assignedPapers.add(paper);
        }
    }

    public boolean canAssignPaper(){
        return assignedPapers.size() < maxNumberOfPapers;
    }
}
