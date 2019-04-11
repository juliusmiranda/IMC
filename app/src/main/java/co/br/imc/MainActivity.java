package co.br.imc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText peso, altura;
    Button salvar, carregar, calcular;
    IMC imc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peso = (EditText)findViewById(R.id.peso);
        altura = (EditText)findViewById(R.id.altura);
        salvar = (Button)findViewById(R.id.salvar);
        carregar = (Button)findViewById(R.id.carregar);
        calcular = (Button)findViewById(R.id.calcular);
    }

    public void calcular(View v){
        Float p, a;
        p = Float.parseFloat(String.valueOf(peso.getText()));
        a = Float.parseFloat((String.valueOf(altura.getText())));
        String resultado = String.format("%.2f",(p/(a*a)));
        Toast.makeText(getApplicationContext(),"Seu IMC é: "+resultado,Toast.LENGTH_LONG).show();
    }

    public void salvar(View v){
        imc = new IMC();
        imc.setPeso(peso.getText().toString());
        imc.setAltura(altura.getText().toString());

        IMCRepo repo = new IMCRepo(getApplicationContext());
        repo.salvar(imc);
        Toast.makeText(getApplicationContext(),"Salvo com sucesso!", Toast.LENGTH_LONG).show();
    }

    public void carregar(View v){
        IMCRepo repo = new IMCRepo(getApplicationContext());
        ArrayList<IMC> meuIMC;
        meuIMC = repo.carregar();
        peso.setText(meuIMC.get(meuIMC.size()-1).getPeso());
        altura.setText(meuIMC.get(meuIMC.size()-1).getAltura());
        Toast.makeText(getApplicationContext(),"Dados carregados com sucesso!", Toast.LENGTH_LONG).show();
    }

    public void limpar(View v){
        peso.setText("");
        altura.setText("");
        Toast.makeText(getApplicationContext(),"Limpo!", Toast.LENGTH_LONG).show();
    }
}
