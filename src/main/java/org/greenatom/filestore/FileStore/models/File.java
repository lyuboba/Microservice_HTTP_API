package org.greenatom.filestore.FileStore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name="File")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="file_data")
    @NotNull(message = "Файл должен хранить данные")
    private byte[] fileData;

    @Column(name="title")
    @NotEmpty(message = "Название файла должно быть указано")
    private String title;

    @Column(name="creation_date")
    @NotNull(message = "Дата и время отправки файла должно быть указано")
    private LocalDateTime creationDate;

    @Column(name="description")
    @NotEmpty(message = "Описание файла должно быть указано")
    private String description;

    public File(){}

    public File(byte[] fileData, String title, LocalDateTime creationDate, String description) {
        this.fileData = fileData;
        this.title = title;
        this.creationDate = creationDate;
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
