package hb.com.energyclicker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jocelyn on 18/05/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "MonsterClicker";
    private final static int DB_VERSION = 1;

    private final static String CREATE_TABLE_PLAYER =
            "CREATE TABLE player (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "energyWhite INT," +
                    "energyBlue INT," +
                    "energyGreen INT," +
                    "energyRed INT);";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PLAYER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
