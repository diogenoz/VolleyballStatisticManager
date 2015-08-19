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
public class MatchAdapter extends BaseAdapter{

    private static final String DB_NAME = "volleyRegistr.db";
    private static final String TABLE_NAME = "matches";
    private static final int DB_VESION = 2;
    // Для удобства выполнения sql-запросов
    // создадим константы с именами полей таблицы
    // и номерами соответсвующих столбцов
    private static final String KEY_ID = "_id";
    private static final int COLID_ID = 0;
    private static final String KEY_HNAME = "hname";
    private static final int COLID_HNAME = 1;
    private static final String KEY_ANAME = "aname";
    private static final int COLID_ANAME = 2;
    private static final String KEY_DATE = "date";
    private static final int COLID_DATE = 3;
    private static final String KEY_VENUE = "venue";
    private static final int COLID_VENUE = 4;
    private static final String KEY_HSET = "hset";
    private static final int COLID_HSET = 5;
    private static final String KEY_ASET = "aset";
    private static final int COLID_ASET = 6;
    private static final String KEY_NSET = "options_nset";
    private static final int COLID_NSET = 7;
    private static final String KEY_SETLENGTH = "options_set_length";
    private static final int COLID_SETLENGTH = 8;
    private static final String KEY_FINALSETLENGTH = "options_final_set_length";
    private static final int COLID_FINALSETLENGTH = 9;


    private Cursor cursor;
    private SQLiteDatabase database;
    private DbOpenHelper dbOpenHelper;
    private Context context;

    //Далее следуют обязательные к перегрузке методы адаптера

    public MatchAdapter(Context context) {
        super();
        this.context = context;
        this.init();
    }

    @Override
    public long getItemId(int position) {
        Match matchOnPosition = this.getItem(position);
        return matchOnPosition.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parrent) {
        TextView textView;
        if (null == convertView) {
            textView = (TextView) View.inflate(context, R.layout.list_item,
                   null);
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(getItem(position).toString());
        return textView;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Match getItem(int position) {
        if (cursor.moveToPosition(position)) {
            long id = cursor.getLong(COLID_ID);
            String hName=cursor.getString(COLID_HNAME);
            String aName=cursor.getString(COLID_ANAME);
            int hSet=cursor.getInt(COLID_HSET);
            int aSet=cursor.getInt(COLID_ASET);
            int nSet=cursor.getInt(COLID_NSET);
            int setLength=cursor.getInt(COLID_SETLENGTH);
            int lastSetLength=cursor.getInt(COLID_FINALSETLENGTH);
            MatchOptions matchOptions=new MatchOptions(nSet,setLength,lastSetLength);

            Match match=new Match(hName,aName,matchOptions);

            return match;
        } else {
            throw new CursorIndexOutOfBoundsException(
                    "Cant move cursor to postion");
        }
    }

    //Методы для работы с базой данных

    public Cursor getAllEntries() {
        //Список колонок базы, которые следует включить в результат
        String[] columnsToTake = { KEY_ID, };
        // составляем запрос к базе
        return database.query(TABLE_NAME, columnsToTake,
                null, null, null, null, KEY_ID);
    }

    public int addItem(Match match) {
        ContentValues values = new ContentValues();
        values.put(KEY_HNAME, match.gethName());
        values.put(KEY_ANAME, match.getaName());
        values.put(KEY_NSET, match.getMatchOptions().getMaxWinSets());
        values.put(KEY_SETLENGTH, match.getMatchOptions().getSetLength());
        values.put(KEY_FINALSETLENGTH, match.getMatchOptions().getLastSetLength());

        int id = (int) database.insert(TABLE_NAME, null, values);
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
            final String CREATE_MATCH_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + KEY_HNAME + " VARCHAR(30) ,"
                    +KEY_ANAME+" VARCHAR(30), "
                    +KEY_DATE+" INTEGER, "
                    +KEY_VENUE+" VARCHAR(30), "
                    +KEY_HSET+" INTEGER, "
                    +KEY_ASET+" INTEGER, "
                    +KEY_NSET+" INTEGER, "
                    +KEY_SETLENGTH+" INTEGER, "
                    +KEY_FINALSETLENGTH+" INTEGER "



                    +");";
            db.execSQL(CREATE_MATCH_TABLE);
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
