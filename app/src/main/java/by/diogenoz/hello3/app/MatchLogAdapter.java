package by.diogenoz.hello3.app;

/**
 * Created by diogen on 06.07.15.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MatchLogAdapter extends ArrayAdapter<String> {

    private static final String[] mEvents = { "Спорный мяч", "Эйс", "Неподача",
            "Блок", "Неатака", "Атака 1з", "Атака 2з", "Атака 3з", "Атака 4з",
            "Атака 5з", "Атака 6з"};

    Context mContext;

    // Конструктор
    public MatchLogAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId, mEvents);

        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        TextView label = (TextView) convertView;

        if (convertView == null) {
            convertView = new TextView(mContext);
            label = (TextView) convertView;
        }
        label.setText(mEvents[position]);
        return (convertView);
    }

    // возвращает содержимое выделенного элемента списка
    public String GetItem(int position) {
        return mEvents[position];
    }
