package grupo001.dam.isi.frsf.utn.edu.ar.lab02;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    Utils utils;

    RadioGroup radioGroup;
    ListView list;
    TextView pedido;
    Button agregarBtn;
    private ArrayAdapter<Utils.ElementoMenu> listAdapter1,listAdapter2,listAdapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils=new Utils();
        utils.iniciarListas();



        pedido=(TextView)findViewById(R.id.listaPedido);
        pedido.setMovementMethod(new ScrollingMovementMethod());

        list=(ListView) findViewById(R.id.listaOpciones);

        ArrayList<Utils.ElementoMenu> platos = new ArrayList<Utils.ElementoMenu>();
        platos.addAll(Arrays.asList(utils.listaPlatos));
        listAdapter1 = new ArrayAdapter<Utils.ElementoMenu>(this, android.R.layout.simple_list_item_1,platos);

        ArrayList<Utils.ElementoMenu> bebidas = new ArrayList<Utils.ElementoMenu>();
        bebidas.addAll(Arrays.asList(utils.listaBebidas));
        listAdapter2 = new ArrayAdapter<Utils.ElementoMenu>(this, android.R.layout.simple_list_item_1,bebidas);

        ArrayList<Utils.ElementoMenu> postres = new ArrayList<Utils.ElementoMenu>();
        postres.addAll(Arrays.asList(utils.listaPostre));
        listAdapter3 = new ArrayAdapter<Utils.ElementoMenu>(this, android.R.layout.simple_list_item_1,postres);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        radioGroup=(RadioGroup) findViewById(R.id.seleccionRG);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case -1:
                        break;
                    case R.id.platoRadioBtn:
                        list.setAdapter(listAdapter1);
                        break;
                    case R.id.bebidaRadioBtn:
                        list.setAdapter(listAdapter2);
                        break;
                    case R.id.postreRadioBtn:
                        list.setAdapter(listAdapter3);
                        break;
                }
            }
        });

        agregarBtn=(Button)findViewById(R.id.agregarBtn);
        agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.ElementoMenu itemValue = (Utils.ElementoMenu) list.getSelectedItem();
                String str=pedido.getText().toString();
                str+="\n"+itemValue.toString();
                pedido.setText(str);
            }
        });
    }
}
