package com.mango.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class ShortDriverDocumentDTO {

    @NotNull
    @Length(max = 100)
    private String name;

    @NotNull
    private String documentImage;

    public ShortDriverDocumentDTO(String name, String documentImage) {
        this.name = name;
        this.documentImage = documentImage;
    }

    public String getName() {
        return name;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocumentImage(String documentImage) {
        this.documentImage = documentImage;
    }
}
