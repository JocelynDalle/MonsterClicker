package hb.com.energyclicker.services;

import java.util.ArrayList;
import java.util.List;

import hb.com.energyclicker.business.Monster;

/**
 * Created by Jocelyn on 13/05/2017.
 */

public class MonsterService {

    public List<Monster> getBuildingsList() {

        List<Monster> monsters = new ArrayList<>();
        //  public Monster(String name, int catchRate, int whitePrice, int bluePrice, int greenPrice, int redPrice, int whiteIncome, int blueIncome, int greenIncome, int redIncome);

        // Monstres basiques
        monsters.add(new Monster("Chromios", 99, 20, 0, 0, 0, 1, 0, 0, 0)); //001
        monsters.add(new Monster("Meneas", 70, 500, 0, 0, 0, 5, 0, 0, 0)); //002
        monsters.add(new Monster("Druax", 50, 7000, 0, 0, 0, 20, 0, 0, 0)); //003


        // Monstres eau
        monsters.add(new Monster("Sylphas", 90, 400, 20, 0, 0, 0, 1, 0, 0)); //004
        monsters.add(new Monster("Nephene", 65, 1200, 500, 0, 0, 3, 4, 0, 0)); //005
        monsters.add(new Monster("Aquaro", 50, 5000, 10000, 0, 0, 6, 9, 0, 0)); //006


        // Monstres Forêt
        monsters.add(new Monster("Grazzy", 80, 400, 2000, 1000, 0, 5, 3, 3, 0)); //007
        monsters.add(new Monster("Tourna", 60, 2000, 5000, 7500, 0, 5, 10, 10, 0)); //008


        // Monstre Plante
        monsters.add(new Monster("Noxis", 90, 6000, 0, 50, 0, 4, 0, 1, 0)); //009
        monsters.add(new Monster("Vrialta", 60, 10000, 0, 1000, 0, 10, 0, 5, 0)); //010
        monsters.add(new Monster("Sylva", 40, 10000, 0, 15000, 0, 15, 0, 8, 1)); //011


        // Monstre Feu
        monsters.add(new Monster("Adamant", 90, 15000, 0, 0, 70, 0, 0, 0, 1)); //012
        monsters.add(new Monster("Ragia", 59, 10000, 0, 0, 2500, 15, 0, 0, 1)); //013
        monsters.add(new Monster("Flambos", 45, 5000, 0, 0, 25000, 30, 0, 0, 2)); //014

        // Monstres Divins
        monsters.add(new Monster("Vilemas", 70, 3000, 500, 300, 20, 30, 0, 0, 0)); //016
        monsters.add(new Monster("Omnimias", 33, 30000, 30000, 30000, 30000, 250, 150, 100, 25)); //015

        return monsters;
    }


    public Monster findMonsterByName(String name, List<Monster> monsters) {
        for (Monster monster : monsters) {
            if (monster.name.equals(name)) {
                return monster;
            }
        }
        return null;
    }

    public int nbMonsterCought(List<Monster> monsters) {
        int nb = 0;
        for (Monster monster : monsters) {
            if (monster.isCought) {
                nb++;
            }
        }
        return nb;
    }

    public int getWhiteIncome(List<Monster> monsters) {
        int whiteIncome = 0;
        for (Monster monster : monsters) {
            if (monster.isCought) {
                whiteIncome += monster.whiteIncome * monster.level;
            }
        }
        return whiteIncome;
    }

    public int getBlueIncome(List<Monster> monsters) {
        int whiteIncome = 0;
        for (Monster monster : monsters) {
            if (monster.isCought) {
                whiteIncome += monster.blueIncome * monster.level;
            }
        }
        return whiteIncome;
    }

    public int getGreenIncome(List<Monster> monsters) {
        int whiteIncome = 0;
        for (Monster monster : monsters) {
            if (monster.isCought) {

                whiteIncome += monster.greenIncome * monster.level;
            }
        }
        return whiteIncome;
    }

    public int getRedIncome(List<Monster> monsters) {
        int whiteIncome = 0;
        for (Monster monster : monsters) {
            if (monster.isCought) {

                whiteIncome += monster.redIncome * monster.level;
            }
        }
        return whiteIncome;
    }
}
