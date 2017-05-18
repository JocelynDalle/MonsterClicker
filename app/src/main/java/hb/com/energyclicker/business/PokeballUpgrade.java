package hb.com.energyclicker.business;

/**
 * Created by Jocelyn on 14/05/2017.
 */

public class PokeballUpgrade extends Upgrades {

    protected int tapBonus;

    public int getTapBonus() {
        return tapBonus;
    }

    public void setTapBonus(int tapBonus) {
        this.tapBonus = tapBonus;
    }

    public PokeballUpgrade(String name, int whitePrice, int bluePrice, int greenPrice, int redPrice, int tapBonus) {
        super(name, whitePrice, bluePrice, greenPrice, redPrice);
        this.tapBonus = tapBonus;
    }

    public void buy() {
        this.isBought = true;
    }
}
