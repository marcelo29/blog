package br.com.cursoandroid.cadastroaluno;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import br.com.android.R;

public class ListaAlunosActivity extends Activity {

	//Definicao de constantes
	private String TAG = "CADASTRO_ALUNO";
	private String ALUNOS_KEY = "LISTA";
	
	//Atributos de tela
	private EditText edNome;
	private Button botao;
	private ListView lvListagem;

	private List<String> listaAlunos;

	private ArrayAdapter<String> adapter;
	private int adapterLayout = android.R.layout.simple_list_item_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listaalunoslayout);

		edNome = (EditText) findViewById(R.id.edNomeListagem);
		botao = (Button) findViewById(R.id.btAddListagem);
		lvListagem = (ListView) findViewById(R.id.lvListagem);

		//criando a lista de alunos
		listaAlunos = new ArrayList<String>();

		// o objeto ArrayAdapter sabe converter lista ou vetores em View
		adapter = new ArrayAdapter<String>(this, adapterLayout, listaAlunos);

		lvListagem.setAdapter(adapter);

		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				listaAlunos.add(edNome.getText().toString());

				edNome.setText("");

				adapter.notifyDataSetChanged();
			}
		});

		// Metodo q escuta o evento de click simples
		lvListagem.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
				Toast.makeText(ListaAlunosActivity.this, "Aluno: " + listaAlunos.get(position), Toast.LENGTH_SHORT)
						.show();
			}

		});

		// Metodo q escuta o click longo
		lvListagem.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
				Toast.makeText(ListaAlunosActivity.this, "Aluno: " + listaAlunos.get(position) + " [click longo]",
						Toast.LENGTH_LONG).show();

				return true;
			}

		});

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// Inclusao da lista de alunos ao objeto Bundle.Map
		outState.putStringArrayList(ALUNOS_KEY, (ArrayList<String>) listaAlunos);
		//Persistencia do objeto Bundle
		super.onSaveInstanceState(outState);
		//Lancamento de msg de log
		Log.i(TAG, "onSaveInstanceState(): " + listaAlunos);
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		//Recuperao o estado do objeto Bundle		
		super.onRestoreInstanceState(savedInstanceState);
		//Carrega lista de alunos do Bundle.map
		listaAlunos = savedInstanceState.getStringArrayList(ALUNOS_KEY);
		//Lancamento de msg de log
		Log.i(TAG, "onSaveRestoreState(): " + listaAlunos);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//definicao do objeto inflater
		MenuInflater inflater = this.getMenuInflater();
		
		//inflar um xml em um menu vazio
		inflater.inflate(R.menu.menu_principal, menu);
		
		
		//exibir o menu 
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//verifica o item do menu selecionado
		switch (item.getItemId()) {
		//verifica se foi selecionado o item novo
		case R.id.menu_novo:
			Toast.makeText(ListaAlunosActivity.this, "Voce clicou em novo", Toast.LENGTH_LONG).show();
			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
}