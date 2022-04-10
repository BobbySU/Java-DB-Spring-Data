package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PictureSeedDTO {
    @Expose
    private String path;
    @Expose
    private double size;

    @NotNull
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @NotNull
    @Min(500)
    @Max(60000)
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
