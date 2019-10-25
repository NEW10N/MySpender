package com.example.my_spender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class progress extends AppCompatActivity {

    private ProgressBar miProgress;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        btn = findViewById(R.id.button);
        //instanciamos el progressbar
        miProgress = (ProgressBar) findViewById(R.id.circularProgress);
        //Instanciamos el animador
        //Construye y devuelve un ObjectAnimator que anima.

        //Ejecuación de la cargar de la barra de progreso
        //Se utiliza un hilo para que cuando termine de cargar por completo pueda hacer una acción final
        //Un hilo se encarga de incrementar la barra y al final ejecuta ejecutar el metodo dentro de runOnUiThread que se encuentra abajo
        //Otro hilo se encarga poner invisible el boton, poner visible la barra de progeso y asignarle un tamaño final de 100
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Se inicia la barra de progreso en cero
                miProgress.post(new Runnable() {
                    @Override
                    public void run() {
                        miProgress.setProgress(0);
                    }
                });

                for (int i = 1; i <= 50; i++) {
                    tareaLarga();
                    miProgress.post(new Runnable() {
                        @Override
                        public void run() {
                            //Incrementa la barra cada 2 unidades, al repetirlo 50 veces completa el tamaño final de 100
                            miProgress.incrementProgressBy(2);
                        }
                    });
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(progress.this, home.class);
                        startActivity(intent);
                    }
                });
            }
        }).start();
        btn.setVisibility(View.GONE);
        miProgress.setVisibility(View.VISIBLE);
        miProgress.setMax(100);
    }

    //el metodo se encarga de alargar el tiempo de esperar en la animación de carga

    private void tareaLarga()
    {
        try {
            Thread.sleep(80);
        }catch (InterruptedException e){}
    }

/*
    //Al seleccionar el boton llama a este metodo
    public void mostrarProgress(View view) {


    }

 */
}
