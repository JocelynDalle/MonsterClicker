package hb.com.energyclicker.services;

import java.util.ArrayList;
import java.util.List;

import hb.com.energyclicker.business.PokeballUpgrade;

/**
 * Created by Jocelyn on 14/05/2017.
 */

public class UpgradeService {


    public List<PokeballUpgrade> getPokeballUpgradesList() {
        List<PokeballUpgrade> pokeballUpgrades = new ArrayList<>();
        //     public PokeballUpgrade(String name, int whitePrice, int bluePrice, int greenPrice, int redPrice, int tapBonus)
        pokeballUpgrades.add(new PokeballUpgrade("Doubleball", 100, 0, 0, 0, 1));
        pokeballUpgrades.add(new PokeballUpgrade("Tripleball", 500, 25, 0, 0, 2));
        pokeballUpgrades.add(new PokeballUpgrade("Tetraball", 2000, 100, 10, 0, 3));
        pokeballUpgrades.add(new PokeballUpgrade("Pentaball", 8000, 300, 75, 10, 5));
        pokeballUpgrades.add(new PokeballUpgrade("Hexaball", 20000, 1500, 300, 50, 10));
        pokeballUpgrades.add(new PokeballUpgrade("Heptaball", 50000, 8000, 1500, 300, 50));
        pokeballUpgrades.add(new PokeballUpgrade("Octoball", 150000, 25000, 6000, 1000, 100));
        pokeballUpgrades.add(new PokeballUpgrade("Nonaball", 200000, 100000, 25000, 5000, 500));
        pokeballUpgrades.add(new PokeballUpgrade("Decaball", 300000, 200000, 100000, 50000, 1000));


        pokeballUpgrades.add(new PokeballUpgrade("Shiroball α", 10, 0, 0, 0, 1));
        pokeballUpgrades.add(new PokeballUpgrade("Shiroball β", 300, 0, 0, 0, 5));
        pokeballUpgrades.add(new PokeballUpgrade("Shiroball γ", 5000, 0, 0, 0, 10));
        pokeballUpgrades.add(new PokeballUpgrade("Shiroball δ", 15000, 0, 0, 0, 50));
        pokeballUpgrades.add(new PokeballUpgrade("Shiroball ε", 75000, 0, 0, 0, 100));

        pokeballUpgrades.add(new PokeballUpgrade("Aoiball α", 0, 10, 0, 0, 1));
        pokeballUpgrades.add(new PokeballUpgrade("Aoiball β", 0, 300, 0, 0, 5));
        pokeballUpgrades.add(new PokeballUpgrade("Aoiball γ", 0, 5000, 0, 0, 10));
        pokeballUpgrades.add(new PokeballUpgrade("Aoiball δ", 0, 15000, 0, 0, 50));
        pokeballUpgrades.add(new PokeballUpgrade("Aoiball ε", 0, 75000, 0, 0, 100));

        pokeballUpgrades.add(new PokeballUpgrade("Midoriball α", 0, 0, 10, 0, 1));
        pokeballUpgrades.add(new PokeballUpgrade("Midoriball β", 0, 0, 300, 0, 5));
        pokeballUpgrades.add(new PokeballUpgrade("Midoriball γ", 0, 0, 5000, 0, 10));
        pokeballUpgrades.add(new PokeballUpgrade("Midoriball δ", 0, 0, 15000, 0, 50));
        pokeballUpgrades.add(new PokeballUpgrade("Midoriball ε", 0, 0, 75000, 0, 100));

        pokeballUpgrades.add(new PokeballUpgrade("Akaball α", 0, 0, 0, 10, 1));
        pokeballUpgrades.add(new PokeballUpgrade("Akaball β", 0, 0, 0, 300, 5));
        pokeballUpgrades.add(new PokeballUpgrade("Akaball γ", 0, 0, 0, 5000, 10));
        pokeballUpgrades.add(new PokeballUpgrade("Akaball δ", 0, 0, 0, 15000, 50));
        pokeballUpgrades.add(new PokeballUpgrade("Akaball ε", 0, 0, 0, 75000, 100));


        return pokeballUpgrades;
    }


    public int getTapChances(List<PokeballUpgrade> pokeballUpgrades) {
        int tapChances = 1;
        for (PokeballUpgrade upgrade : pokeballUpgrades) {
            if (upgrade.isBought()) {
                tapChances += upgrade.getTapBonus();
            }
        }
        return tapChances;
    }
}
