package com.example.projetoasynctask;

import br.edu.ifpb.conection.MinhaAsyncTask;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button enviar = (Button) findViewById(R.id.buttonEnviar);
		enviar.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText editTextNome = (EditText) findViewById(R.id.editTextNome);
				String nome = editTextNome.getText().toString();
				
				EditText editSenha = (EditText) findViewById(R.id.editSenha);
				String senha = editTextNome.getText().toString();
				
				MinhaAsyncTask minhaAsynctask = new MinhaAsyncTask(v.getContext());
				String[] valores = {nome,senha};
				
				MinhaAsyncTask.execute(valores);				
				
			}
		});	
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	
}
