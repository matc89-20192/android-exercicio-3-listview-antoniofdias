package matc89.exercicio3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private TarefaAdapter listAdapter;
    private ArrayList<Tarefa> tarefas = new ArrayList<Tarefa>();
    private EditText editDescricao;
    private EditText editPrioridade;
    private Button addTarefa;
    private Button removeFirstTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listAdapter = new TarefaAdapter(this, tarefas);
        listView.setAdapter(listAdapter);
        editDescricao = (EditText) findViewById(R.id.editDescricao);
        editPrioridade = (EditText) findViewById(R.id.editPrioridade);
        addTarefa = (Button) findViewById(R.id.buttonAdicionar);
        removeFirstTarefa = (Button) findViewById(R.id.buttonRemover);
        removeFirstTarefa.setEnabled(false);
    }

    public void adicionar(View V) {
        String typedDescription = editDescricao.getText().toString();
        int typedPriority = Integer.parseInt(editPrioridade.getText().toString());
        Tarefa newTarefa = checkTarefa(typedDescription, typedPriority);
        if (newTarefa != null) tarefas.add(newTarefa);
        Collections.sort(tarefas);
        listAdapter.notifyDataSetChanged();
        if (!removeFirstTarefa.isEnabled() && tarefas.size() != 0) removeFirstTarefa.setEnabled(true);
    }

    public void remover(View V) {
        tarefas.remove(0);
        if (tarefas.size() == 0) removeFirstTarefa.setEnabled(false);
        listAdapter.notifyDataSetChanged();
    }

    public Tarefa checkTarefa(String description, int priority) {
        boolean avaliability = true;
        Tarefa createdTarefa = null;
        if (priority > 10 || priority < 1) {
            avaliability = false;
            Toast torradin = Toast.makeText(getApplicationContext(),
                    "A prioridade deve estar entre 1 e 10.",
                    Toast.LENGTH_SHORT);
            torradin.show();
        }
        else {
            for (Tarefa t : tarefas) {
                if (t.getDescricao().equals(description)) {
                    avaliability = false;
                    Toast torradin = Toast.makeText(getApplicationContext(),
                            "Tarefa já cadastrada.",
                            Toast.LENGTH_SHORT);
                    torradin.show();
                    break;
                }
            }
        }
        if (avaliability) createdTarefa = new Tarefa(description, priority);
        return createdTarefa;
    }
}
