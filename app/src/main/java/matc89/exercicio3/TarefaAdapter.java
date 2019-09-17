package matc89.exercicio3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TarefaAdapter extends ArrayAdapter<Tarefa> {
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();

    public TarefaAdapter(Context context, ArrayList<Tarefa> tarefas) {
        super(context, 0, tarefas);
        this.tarefas = tarefas;
    }

    private static class ViewHolder {
        private TextView textDescricao;
        private TextView textPrioridade;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        Tarefa task = getItem(position);
        if (task != null) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(android.R.layout.simple_list_item_2, null);
                ViewHolder holder = new ViewHolder();
                holder.textDescricao = (TextView)view.findViewById(android.R.id.text1);
                holder.textPrioridade = (TextView)view.findViewById(android.R.id.text2);
                view.setTag(holder);
            }
            ViewHolder holder = (ViewHolder)view.getTag();
            holder.textDescricao.setText(task.getDescricao());
            holder.textPrioridade.setText(task.getPrioridade());
        }

        return view;
    }

    @Override
    public int getCount() {return tarefas.size();}

    @Override
    public Tarefa getItem(int position) { return tarefas.get(position); }
}
