package main.is.ru.honn.Entity;

/**
 * This class only holds the VideoType of a particular Video
 */

public class VideoType {
    private String type;

    public VideoType() {

    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAsVHS() {
        this.type = "VHS";
    }

    public void setAsBETA() {
        this.type = "BETAMAX";
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        return "Type : " + this.type;
    }
}
