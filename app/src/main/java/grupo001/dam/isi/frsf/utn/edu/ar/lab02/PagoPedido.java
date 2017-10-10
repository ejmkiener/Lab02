package grupo001.dam.isi.frsf.utn.edu.ar.lab02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import modelo.Pedido;
import modelo.Tarjeta;

public class PagoPedido extends AppCompatActivity {
    Pedido datosPedido;

    EditText nombre,mail,mes,anio,tarjeta;

    Button confirmarPagoBtn,cancelarPagoBtn;

    Tarjeta datosTarjeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_pedido);

        datosTarjeta=new Tarjeta();

        nombre=(EditText)findViewById(R.id.ingresoNombrePP);
        mail=(EditText)findViewById(R.id.ingresoCorreoPP);
        tarjeta=(EditText)findViewById(R.id.ingresoTarjetaPP);
        mes=(EditText)findViewById(R.id.mesVencimientoPP);
        anio=(EditText)findViewById(R.id.anioVencimientoPP);

        confirmarPagoBtn=(Button)findViewById(R.id.confirmarPagoBtn);
        cancelarPagoBtn=(Button)findViewById(R.id.cancelarPagoBtn);

        final Intent intent=getIntent();
        datosPedido=(Pedido)intent.getSerializableExtra("datosPedido");
        confirmarPagoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!nombre.getText().toString().trim().equals("")&&!mail.getText().toString().trim().equals("")&& !tarjeta.getText().toString().trim().equals("")&& !mes.getText().toString().trim().equals("")&& !anio.getText().toString().trim().equals("")) {
                    datosTarjeta.setNombre(nombre.getText().toString());
                    intent.putExtra("datosTarjeta",datosTarjeta);

                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Complete todos los campos!",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        cancelarPagoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED,intent);
                finish();
            }
        });
    }
}
