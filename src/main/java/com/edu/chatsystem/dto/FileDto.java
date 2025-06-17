package com.edu.chatsystem.dto;

import java.util.Set;

public class FileDto {
    private Long id;
    private String name;
    private String type;
    private byte[] data;
    private Long messageId;

    public FileDto() {}
    public FileDto(Long id, String name, String type, byte[] data, Long messageId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data =data;
        this.messageId = messageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMessageId() {
        return messageId;
    }
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

}
