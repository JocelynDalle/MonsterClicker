package hb.com.energyclicker.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import hb.com.energyclicker.DatabaseHelper;
import hb.com.energyclicker.business.EnergySet;

/**
 * Created by Jocelyn on 18/05/2017.
 */

public class PlayerDao {

    SQLiteDatabase db;

    public PlayerDao(DatabaseHelper helper) {
        this.db = helper.getWritableDatabase();
    }

    public boolean saveEnergy(EnergySet energies) {
        ContentValues content = new ContentValues();
        content.put("energyWhite", energies.white);
        content.put("energyBlue", energies.blue);
        content.put("energyGreen", energies.green);
        content.put("energyRed", energies.red);

        db.update("player", content, "_id = ?", new String[]{"1"});

        return true;
    }

    public EnergySet loadEnergy() {

        Cursor cursor = db.rawQuery("SELECT * FROM player WHERE _id = 1", null);
        if (cursor.moveToFirst()) {
            return new EnergySet(cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
        } else {
            ContentValues content = new ContentValues();
            content.put("energyWhite", 0);
            content.put("energyBlue", 0);
            content.put("energyGreen", 0);
            content.put("energyRed", 0);
            db.insert("player", null, content);
            return new EnergySet(0,0,0,0);
        }
    }
}
