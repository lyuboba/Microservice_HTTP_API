package org.greenatom.filestore.FileStore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class FileDTO {

    @NotEmpty(message = "Файл должен хранить данные")
    private byte[] fileData;

    @NotEmpty(message = "Название файла должно быть указано")
    private String title;

    @NotNull(message = "Дата и время отправки файла должно быть указано")
    private LocalDateTime creationDate;

    @NotEmpty(message = "Описание файла должно быть указано")
    private String description;


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
