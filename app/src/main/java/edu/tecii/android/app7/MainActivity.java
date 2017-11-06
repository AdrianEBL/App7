package edu.tecii.android.app7;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Button btn1, btn2;
    CheckBox chkbx1;
    EditText edtxt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        chkbx1 = (CheckBox)findViewById(R.id.checkBox);
        edtxt1 = (EditText)findViewById(R.id.editText);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (chkbx1.isChecked()){
                        guardarEnMemoriaExterna();
                    } else {
                        guardarEnMemoriaInterna();
                    }
                } catch (Exception e) {}
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (chkbx1.isChecked()){
                        cargarDesdeSD();
                    } else {
                        cargarEnMemoriaInterna();
                    }
                } catch (Exception e){}
            }
        });
    }

    public boolean existeMemoria() {
        String estado = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(estado)){
            return true;
        }
        return false;
    }

    public void guardarEnMemoriaExterna(){
        try {
            if (existeMemoria()){
                File archivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "archivo.txt");
                FileOutputStream stream = new FileOutputStream(archivo, false);
                OutputStreamWriter escritor = new OutputStreamWriter(stream);
                escritor.write(edtxt1.getText().toString());
                escritor.close();
            }
        } catch (Exception e) {

        }
    }

    public void cargarEnMemoriaInterna(){
        try {
            File archivo = new File(getApplicationContext().getFilesDir(), "archivo.txt");
            FileReader lector = new FileReader(archivo);
            String linea = "", mensaje = "";
            BufferedReader contenido = new BufferedReader(lector);
            while ((linea = contenido.readLine()) != null){
                mensaje += linea;
            }
            lector.close();
            edtxt1.setText(mensaje);
        } catch (Exception e) {

        }
    }

    public void guardarEnMemoriaInterna() {
        try {
            File archivo = new File(getApplication().getFilesDir(), "archivo.txt");
            FileWriter escritor = new FileWriter(archivo, false);
            escritor.write(edtxt1.getText().toString());
            escritor.close();
        } catch (Exception e) {

        }
    }

    public void cargarDesdeSD() {
        try {
            if (existeMemoria()){
                File archivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "archivo.txt");
                FileInputStream stream = new FileInputStream(archivo);
                InputStreamReader lector = new InputStreamReader(stream);
                BufferedReader contenido = new BufferedReader(lector);
                String linea, mensaje = "";
                while ((linea = contenido.readLine()) != null){
                    mensaje += linea;
                }
                lector.close();
                edtxt1.setText(mensaje);
            }
        } catch (Exception e) {

        }
    }
}
