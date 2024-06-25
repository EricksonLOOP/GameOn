package com.br.gameon.api.GameOn.Entitys;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import  java.util.Date;

import java.util.UUID;
@Entity
@Table(name = "publishs")
public class PublishEntity extends RepresentationModel<PublishEntity> implements Serializable {
    public static final long serialVersionUID= 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPublish;
    private String userName;
    private Boolean authorized;
    private UUID idUser;
    private String gameName;



    private String type;
    private int value;
    private String comment;
    private Date data;

    public Boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }



    public UUID getIdPublish() {
        return idPublish;
    }

    public void setIdPublish(UUID idPublish) {
        this.idPublish = idPublish;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UUID getIdUser() {
        return idUser;
    }

    public void setIdUser(UUID idUser) {
        this.idUser = idUser;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


}
