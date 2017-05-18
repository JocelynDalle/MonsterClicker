package hb.com.energyclicker.business;

import java.util.Random;

/**
 * Created by Jocelyn on 13/05/2017.
 */

public class Monster {

    public String name;
    public int level;
    public int catchRate;

    // Attributs de prix / price attributes
    public int whitePrice;
    public int bluePrice;
    public int greenPrice;
    public int redPrice;

    // Atributs de revenu/seconde / Attributes of incomes/second
    public int whiteIncome;
    public int blueIncome;
    public int greenIncome;
    public int redIncome;
    public boolean isVisible;
    public boolean isCought;

    public Monster(String name, int catchRate, int whitePrice, int bluePrice, int greenPrice, int redPrice, int whiteIncome, int blueIncome, int greenIncome, int redIncome) {
        this.name = name;
        this.level = 1;
        this.catchRate = catchRate;
        this.whitePrice = whitePrice;
        this.bluePrice = bluePrice;
        this.greenPrice = greenPrice;
        this.redPrice = redPrice;
        this.whiteIncome = whiteIncome;
        this.blueIncome = blueIncome;
        this.greenIncome = greenIncome;
        this.redIncome = redIncome;
        this.isCought = false;
        this.isVisible = false;
    }


    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", whitePrice=" + whitePrice +
                ", bluePrice=" + bluePrice +
                ", greenPrice=" + greenPrice +
                ", redPrice=" + redPrice +
                ", whiteIncome=" + whiteIncome +
                ", blueIncome=" + blueIncome +
                ", greenIncome=" + greenIncome +
                ", redIncome=" + redIncome +
                '}';
    }

    public void levelUp() {
        this.level++;
        if (this.whitePrice > 0) {
            this.whitePrice += this.whitePrice * 0.06;
        }

        if (this.bluePrice > 0) {
            this.bluePrice += this.bluePrice * 0.09;
        }

        if (this.greenPrice > 0) {
            this.greenPrice += this.greenPrice * 0.12;
        }

        if (this.redIncome > 0) {
            this.redPrice += this.redPrice * 0.15;
        }
    }

    public boolean tryToCatch() {

        Random rng = new Random();
        if (rng.nextInt(100) <= catchRate) {
            this.isCought = true;
            this.isVisible = false;
            return true;
        } else {
            return false;

        }

    }

    public void makeVisible() {
        this.isVisible = true;
    }
}
