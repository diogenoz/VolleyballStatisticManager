package by.diogenoz.hello3.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by diogen on 20.06.15.
 */
public class RoundAdapter extends BaseAdapter{

    private static final String DB_NAME = "volleyRegistr.db";
    private static final String TABLE_NAME = "rounds";
    private static final int DB_VESION = 6;
    // Для удобства выполнения sql-запросов
    // создадим константы с именами полей таблицы
    // и номерами соответсвующих столбцов
    private static final String KEY_ID = "_id";
    private static final int COLID_ID = 0;
    private static final String KEY_HSCORE = "hscore";
    private static final int COLID_HSCORE = 1;
    private static final String KEY_ASCORE = "ascore";
    private static final int COLID_ASCORE = 2;
    private static final String KEY_MATCHID = "match_id";
    private static final int COLID_MATCHID = 3;
    private static final String KEY_CURRENTSET = "current_set";
    private static final int COLID_CURRENTSET = 4;
    private static final String KEY_CURRENTROUND = "current_round";
    private static final int COLID_CURRENTROUND = 5;
    private static final String KEY_WINSIDE = "win_side";
    private static final int COLID_WINSIDE = 6;
    private static final String KEY_MATCHEVENT = "match_event";
    private static final int COLID_MATCHEVENT = 7;


    private Cursor cursor;
    private SQLiteDatabase database;
    private DbOpenHelper dbOpenHelper;
    private Context context;

    //Далее следуют обязательные к перегрузке методы адаптера

    public RoundAdapter(Context context) {
        super();
        this.context = context;
        this.init();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parrent) {

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        Round RoundOnPosition = this.getItem(position);
        return RoundOnPosition.getId();

    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Round getItem(int position){
        Round round=new Round(0,0,0);
        return round;
    }
    public int getSummary(int matchId,int currentSet,int winSide,int matchEvent) {
        String[] columnsToTake = {"COUNT("+KEY_MATCHEVENT+")" };
        String selectionString = KEY_MATCHID+"=? AND "+KEY_CURRENTSET+"=? AND "+KEY_WINSIDE+"=? AND "+KEY_MATCHEVENT+"=? ";
        String[] selectionArgs = new String[]{
                Integer.toString(matchId),
                Integer.toString(currentSet),
                Integer.toString(winSide),
                Integer.toString(matchEvent),

        };
        Cursor cursor1= database.query(TABLE_NAME,columnsToTake,selectionString,selectionArgs,null,null,KEY_ID);

        int summary=0;
        if (!cursor1.isAfterLast()) {
            cursor1.moveToFirst();
            summary = cursor1.getInt(0);
        }
        cursor1.close();

        return summary;

    }
    public Round getRound(int matchId,int currentSet,int currentRound) {
        String[] columnsToTake = { KEY_ID, KEY_HSCORE,KEY_ASCORE,KEY_MATCHID,KEY_CURRENTSET,KEY_CURRENTROUND,KEY_WINSIDE,KEY_MATCHEVENT };
        String selectionString = KEY_MATCHID+"=? AND "+KEY_CURRENTSET+"=? AND "+KEY_CURRENTROUND+"=? ";
        String[] selectionArgs = new String[]{
                Integer.toString(matchId),
                Integer.toString(currentSet),
                Integer.toString(currentRound)
        };
        Cursor cursor1= database.query(TABLE_NAME,columnsToTake,selectionString,selectionArgs,null,null,KEY_ID);

        int hScore=0;
        int aScore=0;
        int winSide=0;
        int matchEvent=0;
        if (!cursor1.isAfterLast()) {
                cursor1.moveToFirst();
                hScore = cursor1.getInt(COLID_HSCORE);
                aScore = cursor1.getInt(COLID_ASCORE);
                winSide = cursor1.getInt(COLID_WINSIDE);
                matchEvent = cursor1.getInt(COLID_MATCHEVENT);
            }
            cursor1.close();

            Round round=new Round(matchId,currentSet,currentRound);
            round.addHScore(hScore);
            round.addAScore(aScore);
            round.setWinSide(winSide);
            round.setMatchEvent(matchEvent);


            return round;

    }

    //Методы для работы с базой данных

    public Cursor getAllEntries() {
        //Список колонок базы, которые следует включить в результат
        String[] columnsToTake = { KEY_ID, KEY_HSCORE,KEY_ASCORE,KEY_MATCHID,KEY_CURRENTSET,KEY_CURRENTROUND,KEY_WINSIDE,KEY_MATCHEVENT };
        // составляем запрос к базе
        return database.query(TABLE_NAME, columnsToTake,
                null, null, null, null, KEY_ID);
    }

    public long addItem(Round round) {
        ContentValues values = new ContentValues();
        values.put(KEY_HSCORE, round.getHScore());
        values.put(KEY_ASCORE, round.getAScore());
        values.put(KEY_MATCHID, round.getMatchId());
        values.put(KEY_CURRENTSET, round.getCurrentSet());
        values.put(KEY_CURRENTROUND, round.getCurrentRound());
        values.put(KEY_WINSIDE, round.getWinSide());
        values.put(KEY_MATCHEVENT, round.getMatchEvent());


        long id = database.insert(TABLE_NAME, null, values);

        refresh();
        return id;
    }

    //Прочие служебные методывфзеук

    public void onDestroy() {
        cursor.close();
        dbOpenHelper.close();
    }

    //Вызывает обновление вида
    private void refresh() {
        cursor = getAllEntries();
        notifyDataSetChanged();
    }

    // Инициализация адаптера: открываем базу и создаем курсор
    private void init() {
        dbOpenHelper = new DbOpenHelper(context, DB_NAME, null, DB_VESION);
        try {
            database = dbOpenHelper.getWritableDatabase();
        } catch (SQLException e) {
            // /Если база не открылась, то дальше нам дороги нет
            // но это особый случай
            Log.e(this.toString(), "Error while getting database");
            throw new Error("The end");
        }
        cursor = getAllEntries();
    }

    // Класс-помошник отвечающий за создание/отктрытие
    // базы и осуществляющий контроль ее версий
    private static class DbOpenHelper extends SQLiteOpenHelper {
        public DbOpenHelper(Context context, String name,
                            CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        // Вызывается при создании базы на устройстве
        public void onCreate(SQLiteDatabase db) {
            // Посроим стандартный sql-запрос для создания таблицы
            final String CREATE_ROUNDS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                    +KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    +KEY_HSCORE + " INTEGER ,"
                    +KEY_ASCORE+" INTEGER ,"
                    +KEY_MATCHID+" INTEGER ,"
                    +KEY_CURRENTSET+" INTEGER ,"
                    +KEY_CURRENTROUND+" INTEGER ,"
                    +KEY_WINSIDE+" INTEGER ,"
                    +KEY_MATCHEVENT+" INTEGER "
                    +");";
            db.execSQL(CREATE_ROUNDS_TABLE);

        }

        @Override
        // Метод будет вызван, если изменится версия базы
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Тут можно организовать миграцию данных из старой базы в новую
            // или просто "выбросить" таблицу и создать заново
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
