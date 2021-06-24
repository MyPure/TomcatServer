package room;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class Room {
    private String themeName;
    private float price;
    private float evaluation;

    @JSONField(defaultValue = " ")
    private String imgSrc;

    public Room(){ }

    public Room(String themeName, float price) {
        this.themeName = themeName;
        this.price = price;
        this.evaluation = 0f;
        this.imgSrc = "";
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(float evaluation) {
        this.evaluation = evaluation;
    }


    @Override
    public String toString() {
        return "Room{" +
                "themeName='" + themeName + '\'' +
                ", price=" + price +
                ", evaluation=" + evaluation +
                ", imgSrc='" + imgSrc + '\'' +
                '}';
    }

    public String toJson(){
        return JSON.toJSONString(this);
    }
}
