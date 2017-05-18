package hb.com.energyclicker;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import hb.com.energyclicker.business.EnergySet;
import hb.com.energyclicker.business.Monster;
import hb.com.energyclicker.business.PokeballUpgrade;
import hb.com.energyclicker.dao.PlayerDao;
import hb.com.energyclicker.services.DatabaseService;
import hb.com.energyclicker.services.MonsterService;
import hb.com.energyclicker.services.UpgradeService;

public class MainActivity extends AppCompatActivity {

    EnergySet energy;

    Random rng;
    Handler handler;
    List<Monster> monsters;
    List<PokeballUpgrade> pokeballUpgrades;

    MonsterService bs;
    UpgradeService us;

    DatabaseHelper dbh;
    PlayerDao pDao;
    /*
    TODO:

    Refactoring :
    - Upgrades -> Shop & Evolution

    Création de Triggers :
    PokeballUpgrades

    Persistance des données
    enregistrer nv des monstres + boolean isCought
    enregistrer les upgrades isBought


    Au chargement :
    Recalculer les niveaux des monstres
    Reaffecter true au monstres et upgrades achetées


    CATCH SYSTEM
    XX

    TRAINING
    XX

    POKEBALL UPGRADES
    XX

    EVOLUTION
    -> Débloqué à un niveau pokemon
    -> Dépense d'une somme d'énergie
    -> Augmente les revenus du pokémon
    -> nv 100 => Mega-Pokemon
    -> nv 1000 => Ultra-Pokémon

    ENERGY CONVERT
    -> convertion d'énergie système incrémental 10++ => 1 (W -> B -> G -> R)



    GAME MECHANICS
    -> Equilibrage (beaucoup de tps de jeu pour débloquer les upgrades vertes et rouges)
    -> Stats
    -> Achievements
    -> Events (Frenzy etc...)
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bs = new MonsterService();
        us = new UpgradeService();
        rng = new Random();
        handler = new Handler();

        dbh = new DatabaseHelper(this);
        pDao = new PlayerDao(dbh);
        energy = pDao.loadEnergy();

        monsters = bs.getBuildingsList();
        pokeballUpgrades = us.getPokeballUpgradesList();

        displayEnergyCounters();
        displayMonsterTrainableList();
        displayPokeballUpgradesList();
        displayMonsterCoughtableList();


        updateData.run();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pDao.saveEnergy(energy);
    }

    public void tap(View view) {

        for (int i = 0; i < us.getTapChances(pokeballUpgrades); i++) {

            int newEnergy = rng.nextInt(10000);

            if (newEnergy <= 3) {
                energy.red++;
            } else if (newEnergy <= 50) {
                energy.green++;
            } else if (newEnergy <= 750) {
                energy.blue++;
            } else if (newEnergy <= 9500) {
                energy.white++;
            }
        }

        displayEnergyCounters();

    }


    private Runnable updateData = new Runnable() {
        public void run() {
            addIncome();
            triggerMonsterList();
            triggerUpdateList();
            handler.postDelayed(updateData, 1000);
        }
    };


    private void addIncome() {
        energy.white += bs.getWhiteIncome(monsters);
        energy.blue += bs.getBlueIncome(monsters);
        energy.green += bs.getGreenIncome(monsters);
        energy.red += bs.getRedIncome(monsters);
        displayEnergyCounters();
    }

    private void displayAll() {
        displayMonsterCoughtableList();
        displayMonsterTrainableList();
        displayPokeballUpgradesList();
        displayEnergyCounters();
    }

    private void displayEnergyCounters() {

        // Display energy counters

        TextView viewWhite = (TextView) findViewById(R.id.energyWhite);
        TextView viewBlue = (TextView) findViewById(R.id.energyBlue);
        TextView viewGreen = (TextView) findViewById(R.id.energyGreen);
        TextView viewRed = (TextView) findViewById(R.id.energyRed);


        viewWhite.setText(energy.white + "");
        viewBlue.setText(energy.blue + "");
        viewGreen.setText(energy.green + "");
        viewRed.setText(energy.red + "");

        // Display incomes


        TextView viewIncomeWhite = (TextView) findViewById(R.id.incomeWhite);
        TextView viewIncomeBlue = (TextView) findViewById(R.id.incomeBlue);
        TextView viewIncomeGreen = (TextView) findViewById(R.id.incomeGreen);
        TextView viewIncomeRed = (TextView) findViewById(R.id.incomeRed);

        viewIncomeWhite.setText(bs.getWhiteIncome(monsters) + "/s");
        viewIncomeBlue.setText(bs.getBlueIncome(monsters) + "/s");
        viewIncomeGreen.setText(bs.getGreenIncome(monsters) + "/s");
        viewIncomeRed.setText(bs.getRedIncome(monsters) + "/s");


    }

    private void displayMonsterTrainableList() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.trainingList);
        ll.removeAllViews();

        for (final Monster monster : monsters) {
            if (monster.isCought) {
                View view = getLayoutInflater().inflate(R.layout.training_template, null);

                TextView bName = (TextView) view.findViewById(R.id.buildingName);
                TextView bLevel = (TextView) view.findViewById(R.id.buildingLevel);

                TextView bPriceWhite = (TextView) view.findViewById(R.id.whitePrice);
                TextView bPriceBlue = (TextView) view.findViewById(R.id.bluePrice);
                TextView bPriceGreen = (TextView) view.findViewById(R.id.greenPrice);
                TextView bPriceRed = (TextView) view.findViewById(R.id.redPrice);

                TextView bIncomeWhite = (TextView) view.findViewById(R.id.whiteIncome);
                TextView bIncomeBlue = (TextView) view.findViewById(R.id.blueIncome);
                TextView bIncomeGreen = (TextView) view.findViewById(R.id.greenIncome);
                TextView bIncomeRed = (TextView) view.findViewById(R.id.redIncome);

                System.out.print("BUILDING : " + monster);

                bName.setText(monster.name);
                bLevel.setText("nv " + monster.level);

                bPriceWhite.setText(monster.whitePrice + "");
                bPriceBlue.setText(monster.bluePrice + "");
                bPriceGreen.setText(monster.greenPrice + "");
                bPriceRed.setText(monster.redPrice + "");

                bIncomeWhite.setText(monster.whiteIncome * monster.level + "");
                bIncomeBlue.setText(monster.blueIncome * monster.level + "");
                bIncomeGreen.setText(monster.greenIncome * monster.level + "");
                bIncomeRed.setText(monster.redIncome * monster.level + "");


                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (energy.white >= monster.whitePrice &&
                                energy.blue >= monster.bluePrice &&
                                energy.green >= monster.greenPrice &&
                                energy.red >= monster.redPrice) {

                            energy.white -= monster.whitePrice;
                            energy.blue -= monster.bluePrice;
                            energy.green -= monster.greenPrice;
                            energy.red -= monster.redPrice;

                            monster.levelUp();

                            displayAll();

                        } else {
                            Toast.makeText(getBaseContext(), "Pas assez d'énergie", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                ll.addView(view);
            }
        }
    }

    private void displayPokeballUpgradesList() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.upgradeList);
        ll.removeAllViews();

        for (final PokeballUpgrade pokeballUpgrade : pokeballUpgrades) {
            if (!pokeballUpgrade.isBought()) {
                View view = getLayoutInflater().inflate(R.layout.upgrade_template, null);

                TextView bName = (TextView) view.findViewById(R.id.upgradeName);

                TextView bPriceWhite = (TextView) view.findViewById(R.id.whitePrice);
                TextView bPriceBlue = (TextView) view.findViewById(R.id.bluePrice);
                TextView bPriceGreen = (TextView) view.findViewById(R.id.greenPrice);
                TextView bPriceRed = (TextView) view.findViewById(R.id.redPrice);


                bName.setText(pokeballUpgrade.getName());

                bPriceWhite.setText(pokeballUpgrade.getWhitePrice() + "");
                bPriceBlue.setText(pokeballUpgrade.getBluePrice() + "");
                bPriceGreen.setText(pokeballUpgrade.getGreenPrice() + "");
                bPriceRed.setText(pokeballUpgrade.getRedPrice() + "");

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (energy.white >= pokeballUpgrade.getWhitePrice() &&
                                energy.blue >= pokeballUpgrade.getBluePrice() &&
                                energy.green >= pokeballUpgrade.getGreenPrice() &&
                                energy.red >= pokeballUpgrade.getRedPrice()) {
                            energy.white -= pokeballUpgrade.getWhitePrice();
                            energy.blue -= pokeballUpgrade.getBluePrice();
                            energy.green -= pokeballUpgrade.getGreenPrice();
                            energy.red -= pokeballUpgrade.getRedPrice();
                            pokeballUpgrade.buy();

                            displayAll();

                        } else {
                            Toast.makeText(getBaseContext(), "NOT ENOUGTH ENERGY", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                ll.addView(view);
            }
        }
    }

    private void displayMonsterCoughtableList() {
        LinearLayout ll = (LinearLayout) findViewById(R.id.huntList);
        ll.removeAllViews();

        for (final Monster monster : monsters) {
            if (!monster.isCought && monster.isVisible) {
                View view = getLayoutInflater().inflate(R.layout.hunt_template, null);

                TextView mName = (TextView) view.findViewById(R.id.monsterName);

                TextView mPriceWhite = (TextView) view.findViewById(R.id.whitePrice);
                TextView mPriceBlue = (TextView) view.findViewById(R.id.bluePrice);
                TextView mPriceGreen = (TextView) view.findViewById(R.id.greenPrice);
                TextView mPriceRed = (TextView) view.findViewById(R.id.redPrice);


                mName.setText(monster.name);

                mPriceWhite.setText(monster.whitePrice + "");
                mPriceBlue.setText(monster.bluePrice + "");
                mPriceGreen.setText(monster.greenPrice + "");
                mPriceRed.setText(monster.redPrice + "");

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (energy.white >= monster.whitePrice &&
                                energy.blue >= monster.bluePrice &&
                                energy.green >= monster.greenPrice &&
                                energy.red >= monster.redPrice) {

                            energy.white -= monster.whitePrice;
                            energy.blue -= monster.bluePrice;
                            energy.green -= monster.greenPrice;
                            energy.red -= monster.redPrice;

                            if (monster.tryToCatch()) {
                                Toast.makeText(getBaseContext(), monster.name + " est capturé !", Toast.LENGTH_SHORT).show();

                                displayAll();

                            } else {
                                Toast.makeText(getBaseContext(), "Capture échouée", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getBaseContext(), "NOT ENOUGTH ENERGY", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                ll.addView(view);
            }
        }
    }

    private void triggerMonsterList() {
        // Activer Chromios
        if (!bs.findMonsterByName("Chromios", monsters).isVisible && !bs.findMonsterByName("Chromios", monsters).isCought && energy.white >= 10) {
            bs.findMonsterByName("Chromios", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Meneas
        if (!bs.findMonsterByName("Meneas", monsters).isVisible && !bs.findMonsterByName("Meneas", monsters).isCought && energy.white >= 100) {
            bs.findMonsterByName("Meneas", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Druax
        if (!bs.findMonsterByName("Druax", monsters).isVisible && !bs.findMonsterByName("Druax", monsters).isCought && bs.findMonsterByName("Meneas", monsters).isCought) {
            bs.findMonsterByName("Druax", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Sylphas
        if (!bs.findMonsterByName("Sylphas", monsters).isVisible && !bs.findMonsterByName("Sylphas", monsters).isCought && bs.findMonsterByName("Meneas", monsters).level >= 15) {
            bs.findMonsterByName("Sylphas", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Nephene
        if (!bs.findMonsterByName("Nephene", monsters).isVisible && !bs.findMonsterByName("Nephene", monsters).isCought && energy.blue >= 1000) {
            bs.findMonsterByName("Nephene", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Aquaro
        if (!bs.findMonsterByName("Aquaro", monsters).isVisible && !bs.findMonsterByName("Aquaro", monsters).isCought && bs.findMonsterByName("Sylphas", monsters).isCought && bs.findMonsterByName("Nephene", monsters).isCought) {
            bs.findMonsterByName("Aquaro", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Grazzy
        if (!bs.findMonsterByName("Grazzy", monsters).isVisible && !bs.findMonsterByName("Grazzy", monsters).isCought && bs.getBlueIncome(monsters) >= 50) {
            bs.findMonsterByName("Grazzy", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Tourna
        if (!bs.findMonsterByName("Tourna", monsters).isVisible && !bs.findMonsterByName("Tourna", monsters).isCought && bs.getWhiteIncome(monsters) >= 500) {
            bs.findMonsterByName("Tourna", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Noxis
        if (!bs.findMonsterByName("Noxis", monsters).isVisible && !bs.findMonsterByName("Noxis", monsters).isCought && bs.nbMonsterCought(monsters) >= 5) {
            bs.findMonsterByName("Noxis", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Vrialta
        if (!bs.findMonsterByName("Vrialta", monsters).isVisible && !bs.findMonsterByName("Vrialta", monsters).isCought && bs.findMonsterByName("Grazzy", monsters).level >= 10) {
            bs.findMonsterByName("Vrialta", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Sylva
        if (!bs.findMonsterByName("Sylva", monsters).isVisible && !bs.findMonsterByName("Sylva", monsters).isCought && energy.green >= 10000) {
            bs.findMonsterByName("Sylva", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Adamant
        if (!bs.findMonsterByName("Adamant", monsters).isVisible && !bs.findMonsterByName("Adamant", monsters).isCought && energy.red >= 15) {
            bs.findMonsterByName("Adamant", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Ragia
        if (!bs.findMonsterByName("Ragia", monsters).isVisible && !bs.findMonsterByName("Ragia", monsters).isCought && bs.findMonsterByName("Adamant", monsters).isCought) {
            bs.findMonsterByName("Ragia", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Flambos
        if (!bs.findMonsterByName("Flambos", monsters).isVisible && !bs.findMonsterByName("Flambos", monsters).isCought && bs.findMonsterByName("Adamant", monsters).level >= 25) {
            bs.findMonsterByName("Flambos", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Vilemas
        if (!bs.findMonsterByName("Vilemas", monsters).isVisible && !bs.findMonsterByName("Vilemas", monsters).isCought && (energy.white + energy.blue + energy.green + energy.red) >= 300000) {
            bs.findMonsterByName("Vilemas", monsters).makeVisible();
            displayMonsterCoughtableList();
        }

        // Activer Omnimias
        if (!bs.findMonsterByName("Omnimias", monsters).isVisible && !bs.findMonsterByName("Omnimias", monsters).isCought && bs.nbMonsterCought(monsters) >= 15) {
            bs.findMonsterByName("Omnimias", monsters).makeVisible();
            displayMonsterCoughtableList();
        }
    }

    private void triggerUpdateList() {
    }
}
