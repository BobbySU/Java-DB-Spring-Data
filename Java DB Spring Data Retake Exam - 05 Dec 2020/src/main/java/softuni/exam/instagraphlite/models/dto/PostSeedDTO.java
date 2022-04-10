package softuni.exam.instagraphlite.models.dto;

import softuni.exam.instagraphlite.models.Picture;
import softuni.exam.instagraphlite.models.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedDTO {

    private String caption;
    private UserUserNameDTO user;
    private PicturePathDTO picture;

    @Size(min = 21)
    @NotNull
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @NotNull
    public UserUserNameDTO getUser() {
        return user;
    }

    public void setUser(UserUserNameDTO user) {
        this.user = user;
    }

    @NotNull
    public PicturePathDTO getPicture() {
        return picture;
    }

    public void setPicture(PicturePathDTO picture) {
        this.picture = picture;
    }
}
