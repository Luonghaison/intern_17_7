package com.example.intern_14_7.model;


import javax.persistence.*;


@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "discription")
    private String discription;

    public Role() {
    }

    public Role(String id, String name, String discription) {
        this.id = id;
        this.name = name;
        this.discription = discription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }
}
