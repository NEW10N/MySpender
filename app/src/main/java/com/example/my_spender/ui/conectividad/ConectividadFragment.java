package com.example.my_spender.ui.conectividad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.my_spender.R;
import com.example.my_spender.home;
import com.example.my_spender.progress;

public class ConectividadFragment extends Fragment {

    private ProgressBar miProgress;
    Button btncon;
    //Estado fragment = new HoraFragment();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_conectividad, container, false);

        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btncon = (Button) view.findViewById(R.id.btnConexion);
        miProgress = (ProgressBar) view.findViewById(R.id.circularProgress);
        //Instanciamos el animador
        //Construye y devuelve un ObjectAnimator que anima.

        //Ejecuación de la cargar de la barra de progreso
        //Se utiliza un hilo para que cuando termine de cargar por completo pueda hacer una acción final
        //Un hilo se encarga de incrementar la barra y al final ejecuta ejecutar el metodo dentro de runOnUiThread que se encuentra abajo
        //Otro hilo se encarga poner invisible el boton, poner visible la barra de progeso y asignarle un tamaño final de 100
        btncon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final ConectividadEstadoFragment fragment = new ConectividadEstadoFragment();
                                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.contConectividad, fragment);
                                transaction.commit();
                            }
                        });
                    }
                }).start();

                btncon.setVisibility(View.GONE);
                miProgress.setVisibility(View.VISIBLE);
                miProgress.setMax(100);

            }

            //el metodo se encarga de alargar el tiempo de esperar en la animación de carga

                private void tareaLarga() {
                        try {
                            Thread.sleep(80);
                        } catch (InterruptedException e) {
                    }
                }
            });
        }
    }