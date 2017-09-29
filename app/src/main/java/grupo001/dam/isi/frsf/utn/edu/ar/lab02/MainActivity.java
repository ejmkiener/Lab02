package grupo001.dam.isi.frsf.utn.edu.ar.lab02;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import modelo.Pedido;
import modelo.Tarjeta;
import modelo.TipoBebida;
import modelo.Utils;

public class MainActivity extends AppCompatActivity {

    DecimalFormat f = new DecimalFormat("##.00");

    private final Integer CODIGO_LLAMADA_ACT2=800;
    boolean confirmado;
    Double montoFinal;

    Pedido datosPedido;
    Tarjeta datosTarjeta;

    Utils utils;

    Spinner horarioSpinner;

    RadioGroup radioGroup;
    RadioButton platoRB;
    ListView list;
    TextView pedido;
    Button agregarBtn,resetBtn,confirmarPedidoBtn;
    private ArrayAdapter<Utils.ElementoMenu> listAdapter1,listAdapter2,listAdapter3;
    ArrayAdapter<String> spinnerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        montoFinal=0d;

        confirmado=false;


        datosPedido=new Pedido();
        datosTarjeta=new Tarjeta();

        utils=new Utils();
        utils.iniciarListas();

        pedido=(TextView)findViewById(R.id.listaPedido);
        pedido.setMovementMethod(new ScrollingMovementMethod());

        list=(ListView) findViewById(R.id.listaOpciones);
        list.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        ArrayList<Utils.ElementoMenu> platos = new ArrayList<Utils.ElementoMenu>();
        platos.addAll(Arrays.asList(utils.listaPlatos));
        listAdapter1 = new ArrayAdapter<Utils.ElementoMenu>(this, android.R.layout.simple_list_item_single_choice,platos);

        ArrayList<Utils.ElementoMenu> bebidas = new ArrayList<Utils.ElementoMenu>();
        bebidas.addAll(Arrays.asList(utils.listaBebidas));
        listAdapter2 = new ArrayAdapter<Utils.ElementoMenu>(this, android.R.layout.simple_list_item_single_choice,bebidas);

        ArrayList<Utils.ElementoMenu> postres = new ArrayList<Utils.ElementoMenu>();
        postres.addAll(Arrays.asList(utils.listaPostre));
        listAdapter3 = new ArrayAdapter<Utils.ElementoMenu>(this, android.R.layout.simple_list_item_single_choice,postres);

        horarioSpinner=(Spinner)findViewById(R.id.spinner);
        final String[] horariosDisponibles = new String[]{"20:00","20:30","21:00","21:30","22:00","22:30"};
        ArrayList<String> horarios=new ArrayList<String>();
        horarios.addAll(Arrays.asList(horariosDisponibles));
        spinnerAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,horarios);
        spinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        horarioSpinner.setAdapter(spinnerAdapter);
        horarioSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                datosPedido.setHoraEntrega(horariosDisponibles[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                datosPedido.setHoraEntrega("");
            }
        });

        /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
*/
        platoRB=(RadioButton) findViewById(R.id.platoRadioBtn);
        //platoRB.setChecked(true);

        radioGroup=(RadioGroup) findViewById(R.id.seleccionRG);
        list.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        //list.setAdapter(listAdapter1);

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
                if(!confirmado) {
                    Utils.ElementoMenu itemValue = (Utils.ElementoMenu) list.getItemAtPosition(list.getCheckedItemPosition());

                    if (itemValue != null) {
                        montoFinal += itemValue.getPrecio();
                        String str = pedido.getText().toString();
                        str += itemValue.toString() + "\n";
                        pedido.setText(str);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Debe seleccionar algo del menú.", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "El Pedido ya fue confirmado.\nReinicie para armar un nuevo pedido.",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        resetBtn=(Button)findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pedido.setText("");
                montoFinal=0d;
                confirmado=false;
            }
        });

        confirmarPedidoBtn=(Button)findViewById(R.id.confirmarPedidoBtn);
        confirmarPedidoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!confirmado) {
                    if(pedido.getText().toString()!="") {
                        Intent intent = new Intent(MainActivity.this, PagoPedido.class);
                        intent.putExtra("datosPedido", datosPedido);
                        startActivityForResult(intent, CODIGO_LLAMADA_ACT2);
                    }else{
                        Toast toast = Toast.makeText(getApplicationContext(), "El Pedido se encuentra vacio.",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "El Pedido ya fue confirmado.\nReinicie para armar un nuevo pedido.",Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("listaPedido", pedido.getText().toString());
        outState.putDouble("montoFinal",montoFinal);

        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pedido.setText(savedInstanceState.getString("listaPedido"));
        montoFinal=savedInstanceState.getDouble("montoFinal");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CODIGO_LLAMADA_ACT2) {
            if (resultCode == RESULT_OK) {
                datosTarjeta=(Tarjeta) data.getSerializableExtra("datosTarjeta");
                confirmado=true;
                Toast toast = Toast.makeText(getApplicationContext(), "Pago Confirmado "+f.format(montoFinal),Toast.LENGTH_SHORT);
                toast.show();

            }

            if (resultCode == RESULT_CANCELED) {
                Toast toast = Toast.makeText(getApplicationContext(), "Pago Cancelado",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
