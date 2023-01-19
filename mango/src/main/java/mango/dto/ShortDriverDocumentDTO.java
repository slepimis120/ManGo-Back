package mango.dto;

public class ShortDriverDocumentDTO {
    private Integer id;
    private String name;
    private String documentImage;

    public ShortDriverDocumentDTO(String name, String documentImage) {
        this.name = name;
        this.documentImage = documentImage;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocumentImage(String documentImage) {
        this.documentImage = documentImage;
    }
}
