package hb.com.energyclicker.services;

import android.content.Context;

import java.util.List;

import hb.com.energyclicker.DatabaseHelper;
import hb.com.energyclicker.business.EnergySet;
import hb.com.energyclicker.business.Monster;
import hb.com.energyclicker.business.PokeballUpgrade;

/**
 * Created by Jocelyn on 18/05/2017.
 */

public class DatabaseService {

    Context ctx;


    public void saveDatas(EnergySet energy, List<Monster> monsters, List<PokeballUpgrade> pokeballUpgrades, Context ctx) {
        saveEnergy(energy);
        saveMonsters(monsters);
        savePokeballs(pokeballUpgrades);
        this.ctx = ctx;
    }

    private void savePokeballs(List<PokeballUpgrade> pokeballUpgrades) {

    }

    private void saveMonsters(List<Monster> monsters) {

    }

    private void saveEnergy(EnergySet energy) {

        DatabaseHelper dbh = new DatabaseHelper(ctx);


    }
}
