package hb.com.energyclicker.business;

/**
 * Created by Jocelyn on 14/05/2017.
 */

public abstract class Upgrades {

    protected String name;
    // Attributs de prix / price attributes
    protected int whitePrice;
    protected int bluePrice;
    protected int greenPrice;
    protected int redPrice;
    protected boolean isBought;

    public Upgrades(String name, int whitePrice, int bluePrice, int greenPrice, int redPrice) {
        this.name = name;
        this.whitePrice = whitePrice;
        this.bluePrice = bluePrice;
        this.greenPrice = greenPrice;
        this.redPrice = redPrice;
        this.isBought = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWhitePrice() {
        return whitePrice;
    }

    public void setWhitePrice(int whitePrice) {
        this.whitePrice = whitePrice;
    }

    public int getBluePrice() {
        return bluePrice;
    }

    public void setBluePrice(int bluePrice) {
        this.bluePrice = bluePrice;
    }

    public int getGreenPrice() {
        return greenPrice;
    }

    public void setGreenPrice(int greenPrice) {
        this.greenPrice = greenPrice;
    }

    public int getRedPrice() {
        return redPrice;
    }

    public void setRedPrice(int redPrice) {
        this.redPrice = redPrice;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }
}
