package com.edu.chatsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "files")
@Data
public class FileEntity extends BaseEntity{
    private String name;
    private String type;
    @Lob
    private byte[] data;
}
