package com.edu.chatsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "file")
@Data
@Getter
@Setter
public class FileEntity extends BaseEntity{
    private String name;
    private String type;
    @Lob
    private byte[] data;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private MessageEntity message;

    public FileEntity(){}
    public FileEntity(String name, String type, byte[] data, MessageEntity message){
        this.name = name;
        this.type = type;
        this.data = data;
        this.message = message;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }

    public MessageEntity getMessage() {
        return message;
    }
    public void setMessage(MessageEntity data) {
        this.message = message;
    }
}
