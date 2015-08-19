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

    private static final String[] mEvents = { "������� ���", "���", "��������",
            "����", "�������", "����� 1�", "����� 2�", "����� 3�", "����� 4�",
            "����� 5�", "����� 6�"};

    Context mContext;

    // �����������
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

    // ���������� ���������� ����������� �������� ������
    public String GetItem(int position) {
        return mEvents[position];
    }
