package com.example.my_spender;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fuzzylite.*;
import com.fuzzylite.activation.*;
import com.fuzzylite.defuzzifier.*;
import com.fuzzylite.factory.*;
import com.fuzzylite.hedge.*;
import com.fuzzylite.imex.*;
import com.fuzzylite.norm.*;
import com.fuzzylite.norm.s.*;
import com.fuzzylite.norm.t.*;
import com.fuzzylite.rule.*;
import com.fuzzylite.term.*;
import com.fuzzylite.variable.*;
import java.util.Scanner;

import androidx.appcompat.app.AppCompatActivity;

public class SistemaDifusoAdulto extends AppCompatActivity {

    //Datos para el seek del tamaño
    private TextView textSize;
    private SeekBar skbSize;
    private String size;
    private TextView optionSizeArray[] = new TextView[4];
    //cantidad del seek del tamaño
    private int sizeValue;

    //Datos para el seek del peso
    private TextView textWeight;
    private SeekBar skbWeight;
    private String weight;
    //cantidad del seek del peso
    private int weightValue;


    //Datos para el seek de la actividad fisica
    private TextView textActivity;
    private SeekBar skbActivity;
    private String activity;
    private TextView optionActivityArray[] = new TextView[3];
    //cantidad del seek de la actividad fisica
    private int activityValue;

    //CONDICIONES CLAVES
    private int minWeight;
    private int maxWeight;
    //FIN DE CONDICIONES CLAVES

    //FUNCIÓN BOOLEANA CLAVE
    public boolean esCorrectoONo(int numero){
        boolean result = (minWeight <= numero && numero <= maxWeight);
        return result;
    }
    //FIN DE LA FUNCIÓN BOOLEANA CLAVE



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_confadulto);

        //El texto, seek y valor del seek del tamaño
        textSize = findViewById(R.id.textSizeAdulto);
        skbSize = findViewById(R.id.seekBarSizeAdulto);
        optionSizeArray[0] = findViewById(R.id.optionSizeAdulto1);
        optionSizeArray[1] = findViewById(R.id.optionSizeAdulto2);
        optionSizeArray[2] = findViewById(R.id.optionSizeAdulto3);
        optionSizeArray[3] = findViewById(R.id.optionSizeAdulto4);
        //Texto y valor por default del tamaño
        size = getResources().getString(R.string.Size);
        textSize.setText(size + ": " + "0cm");
        textSize.setTextColor(getColor(R.color.colorRed));
        sizeValue = 0;

        //El texto y seek del peso
        textWeight = findViewById(R.id.textWeightAdulto);
        skbWeight = findViewById(R.id.seekBarWeightAdulto);
        //Valor por default del peso
        weight = getResources().getString(R.string.Weight);
        textWeight.setText(weight + ": " + "0kg");
        textWeight.setTextColor(getColor(R.color.colorRed));
        weightValue = 0;
        minWeight = 0;
        maxWeight = 0;

        //El seek de la actividad fisica
        textActivity = findViewById(R.id.textActivityAdulto);
        skbActivity = findViewById(R.id.seekBarActivityAdulto);
        optionActivityArray[0] = findViewById(R.id.optionActivityBaja);
        optionActivityArray[1] = findViewById(R.id.optionActivityNormal);
        optionActivityArray[2] = findViewById(R.id.optionActivityAlta);
        //Valor por default de la actividad fisica
        activity = getResources().getString(R.string.PhysicalActivity);
        textActivity.setText(activity + ": 0");
        textActivity.setTextColor(getColor(R.color.colorRed));
        activityValue = 0;

        //Metodos del seek del tamaño
        skbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSize.setText(size + ": " +progress + "cm");
                /*
                * Miniatura: 0 37
                * Pequeño: 20 57
                * Mediano 45 a 75
                * Grande: 65 a 100
                * */
                if(progress == 0) {
                    textSize.setTextColor(getColor(R.color.colorRed));
                    optionSizeArray[0].setBackgroundColor(getColor(R.color.colorPantalla));
                }else{
                    textSize.setTextColor(getColor(R.color.colorBlack));
                    if(progress <= 30){
                        minWeight = 1;
                        maxWeight = 5;
                        optionSizeArray[0].setBackgroundColor(getColor(R.color.colorAccent));
                        optionSizeArray[1].setBackgroundColor(getColor(R.color.colorPantalla));
                        if(progress > 25){
                            maxWeight = 10;
                            optionSizeArray[1].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                    }else if(progress <= 50){
                        minWeight = 5;
                        maxWeight = 10;
                        optionSizeArray[0].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionSizeArray[1].setBackgroundColor(getColor(R.color.colorAccent));
                        optionSizeArray[2].setBackgroundColor(getColor(R.color.colorPantalla));
                        if (progress <= 35) {
                            minWeight = 2;
                            optionSizeArray[0].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                        if (progress > 45){
                            maxWeight = 15;
                            optionSizeArray[2].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                    }else if(progress <= 70){
                        minWeight = 10;
                        maxWeight = 15;
                        optionSizeArray[1].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionSizeArray[2].setBackgroundColor(getColor(R.color.colorAccent));
                        optionSizeArray[3].setBackgroundColor(getColor(R.color.colorPantalla));
                        if (progress <= 55){
                            minWeight = 5;
                            optionSizeArray[1].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                        if (progress >= 65) {
                            maxWeight = 40;
                            optionSizeArray[3].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                    }else {
                        minWeight = 15;
                        optionSizeArray[2].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionSizeArray[3].setBackgroundColor(getColor(R.color.colorAccent));
                        if (progress < 75) {
                            minWeight = 10;
                            optionSizeArray[2].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                    }
                }
                if(esCorrectoONo(weightValue)){
                    textWeight.setTextColor(getColor(R.color.colorBlack));
                }else {
                    textWeight.setTextColor(getColor(R.color.colorRed));
                }
                sizeValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Metodos del seek del peso
        skbWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textWeight.setText(weight + ": " + progress + "kg");
                if(progress == 0) {
                    textWeight.setTextColor(getColor(R.color.colorRed));
                }else{
                    textWeight.setTextColor(getColor(R.color.colorBlack));
                }
                if(esCorrectoONo(progress)){
                    textWeight.setTextColor(getColor(R.color.colorBlack));
                }else {
                    textWeight.setTextColor(getColor(R.color.colorRed));
                }
                weightValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Metodos del seek de la actividad fisica
        skbActivity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textActivity.setText(activity + ": " + progress);
                if(progress == 0) {
                    textActivity.setTextColor(getColor(R.color.colorRed));
                    optionActivityArray[0].setBackgroundColor(getColor(R.color.colorPantalla));
                }else{
                    textActivity.setTextColor(getColor(R.color.colorBlack));
                }
                if(progress != 0 && progress <= 3){
                    optionActivityArray[0].setBackgroundColor(getColor(R.color.colorAccent));
                    optionActivityArray[1].setBackgroundColor(getColor(R.color.colorPantalla));
                    if(progress >= 2){
                        optionActivityArray[1].setBackgroundColor(getColor(R.color.colorOnAccent));
                    }
                }else if(progress <= 6){
                    if(progress == 4){
                        optionActivityArray[0].setBackgroundColor(getColor(R.color.colorOnAccent));
                        optionActivityArray[1].setBackgroundColor(getColor(R.color.colorAccent));
                    }
                    if(progress == 5) {
                        optionActivityArray[0].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionActivityArray[1].setBackgroundColor(getColor(R.color.colorAccent));
                        optionActivityArray[2].setBackgroundColor(getColor(R.color.colorPantalla));
                    }
                    if(progress == 6) {
                        optionActivityArray[1].setBackgroundColor(getColor(R.color.colorAccent));
                        optionActivityArray[2].setBackgroundColor(getColor(R.color.colorOnAccent));
                    }
                }else if(progress <= 10){
                    optionActivityArray[1].setBackgroundColor(getColor(R.color.colorOnAccent));
                    optionActivityArray[2].setBackgroundColor(getColor(R.color.colorAccent));
                    if(progress >= 8){
                        optionActivityArray[1].setBackgroundColor(getColor(R.color.colorPantalla));
                    }
                }
                activityValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void ActivateFuzzySystem(View v) {
        if(sizeValue == 0){
            Toast.makeText(this, "Falta información del tamaño", Toast.LENGTH_SHORT).show();
        }else if(weightValue == 0) {
            Toast.makeText(this, "Falta información del peso", Toast.LENGTH_SHORT).show();
        } else if(activityValue == 0) {
            Toast.makeText(this, "Falta información de la actividad fisica", Toast.LENGTH_SHORT).show();
        } else {
            motorAdulto(sizeValue,weightValue,activityValue);
        }
    }

    public void motorAdulto(int intTamano, int intPesoIdeal, int intActividad) {

        Engine motor = new Engine();
        motor.setName("Comida");
        motor.setDescription("Porción de comida a proporcionar");

        InputVariable Tamano = new InputVariable();
        Tamano.setName("Tamano");
        Tamano.setDescription("Tamano de la raza de la mascota.");
        Tamano.setEnabled(true);
        Tamano.setRange(0, 100);
        Tamano.setLockValueInRange(false);
        Tamano.addTerm(new Ramp("MINIATURA", 35, 20));
        Tamano.addTerm(new Triangle("PEQUENO", 25, 40, 55));
        Tamano.addTerm(new Triangle("MEDIANO", 45, 60, 75));
        Tamano.addTerm(new Ramp("GRANDE", 65, 80));
        motor.addInputVariable(Tamano);

        InputVariable PesoIdeal = new InputVariable();
        PesoIdeal.setName("PesoIdeal");
        PesoIdeal.setDescription("Peso ideal de la mascota.");
        PesoIdeal.setEnabled(true);
        PesoIdeal.setRange(0, 40);
        PesoIdeal.setLockValueInRange(false);
        PesoIdeal.addTerm(new Ramp("2A5KILOGRAMOS", 6, 3));
        PesoIdeal.addTerm(new Triangle("5A10KILOGRAMOS", 4, 8, 11));
        PesoIdeal.addTerm(new Triangle("10A15KILOGRAMOS", 9, 13, 16));
        PesoIdeal.addTerm(new Triangle("15A25KILOGRAMOS", 14, 21, 28));
        PesoIdeal.addTerm(new Ramp("25A40KILOGRAMOS", 23, 32));
        motor.addInputVariable(PesoIdeal);

        InputVariable Actividad = new InputVariable();
        Actividad.setName("Actividad");
        Actividad.setDescription("Actividad Física de las Mascotas.");
        Actividad.setEnabled(true);
        Actividad.setRange(0, 10);
        Actividad.setLockValueInRange(false);
        Actividad.addTerm(new Ramp("BAJA", 4, 3));
        Actividad.addTerm(new Triangle("NORMAL", 2, 5, 7));
        Actividad.addTerm(new Ramp("ALTA", 6, 8));
        motor.addInputVariable(Actividad);

        OutputVariable Comida = new OutputVariable();
        Comida.setName("Comida");
        Comida.setDescription("Cantidad de gramos de comida a proporcionar.");
        Comida.setEnabled(true);
        Comida.setRange(45, 535);
        Comida.setLockValueInRange(false);
        Comida.setAggregation(new Maximum());
        Comida.setDefuzzifier(new Centroid(100));
        Comida.setDefaultValue(Double.NaN);
        Comida.setLockPreviousValue(false);
        Comida.addTerm(new Ramp("PARA-MINIATURA-BAJO", 85, 45));
        Comida.addTerm(new Triangle("PARA-MINIATURA-MEDIO", 55, 78, 100));
        Comida.addTerm(new Triangle("PARA-MINIATURA-ALTO", 60, 88, 115));

        Comida.addTerm(new Triangle("PARA-PEQUENA-BAJO", 85, 115, 145));
        Comida.addTerm(new Triangle("PARA-PEQUENA-MEDIO", 100, 135, 170));
        Comida.addTerm(new Triangle("PARA-PEQUENA-ALTO", 115, 153, 190));

        Comida.addTerm(new Triangle("PARA-MEDIANA-BAJO", 145, 170, 195));
        Comida.addTerm(new Triangle("PARA-MEDIANA-MEDIO", 170, 198, 225));
        Comida.addTerm(new Triangle("PARA-MEDIANA-ALTO", 190, 223, 255));

        Comida.addTerm(new Triangle("PARA-ALGO-GRANDE-BAJO", 195, 240, 285));
        Comida.addTerm(new Triangle("PARA-ALGO-GRANDE-MEDIO", 225, 278, 330));
        Comida.addTerm(new Triangle("PARA-ALGO-GRANDE-ALTO", 255, 318, 380));

        Comida.addTerm(new Triangle("PARA-MEDIO-GRANDE-BAJO", 285, 350, 410));
        Comida.addTerm(new Triangle("PARA-MEDIO-GRANDE-MEDIO", 330, 403, 475));
        Comida.addTerm(new Ramp("PARA-MEDIO-GRANDE-ALTO", 280, 350));

        motor.addOutputVariable(Comida);

        RuleBlock option = new RuleBlock();
        option.setName("option");
        option.setDescription("Min Max");
        option.setEnabled(true);
        option.setConjunction(new Minimum());
        option.setDisjunction(null);
        option.setImplication(new Minimum());
        option.setActivation(new General());
        option.addRule(Rule.parse("if Tamano is MINIATURA and PesoIdeal is 2A5KILOGRAMOS and Actividad is BAJA then Comida is PARA-MINIATURA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is MINIATURA and PesoIdeal is 2A5KILOGRAMOS and Actividad is NORMAL then Comida is PARA-MINIATURA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is MINIATURA and PesoIdeal is 2A5KILOGRAMOS and Actividad is ALTA then Comida is PARA-MINIATURA-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 5A10KILOGRAMOS and Actividad is BAJA then Comida is PARA-PEQUENA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 5A10KILOGRAMOS and Actividad is NORMAL then Comida is PARA-PEQUENA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 5A10KILOGRAMOS and Actividad is ALTA then Comida is PARA-PEQUENA-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is MEDIANO and PesoIdeal is 10A15KILOGRAMOS and Actividad is BAJA then Comida is PARA-MEDIANA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is MEDIANO and PesoIdeal is 10A15KILOGRAMOS and Actividad is NORMAL then Comida is PARA-MEDIANA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is MEDIANO and PesoIdeal is 10A15KILOGRAMOS and Actividad is ALTA then Comida is PARA-MEDIANA-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 15A25KILOGRAMOS and Actividad is BAJA then Comida is PARA-ALGO-GRANDE-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 15A25KILOGRAMOS and Actividad is NORMAL then Comida is PARA-ALGO-GRANDE-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 15A25KILOGRAMOS and Actividad is ALTA then Comida is PARA-ALGO-GRANDE-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 25A40KILOGRAMOS and Actividad is BAJA then Comida is PARA-MEDIO-GRANDE-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 25A40KILOGRAMOS and Actividad is NORMAL then Comida is PARA-MEDIO-GRANDE-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 25A40KILOGRAMOS and Actividad is ALTA then Comida is PARA-MEDIO-GRANDE-ALTO", motor));

        motor.addRuleBlock(option);

        //Uso del sistema
        motor.setInputValue("Tamano", intTamano);
        motor.process();
        motor.setInputValue("PesoIdeal", intPesoIdeal);
        motor.process();
        motor.setInputValue("Actividad", intActividad);
        motor.process();
        double solution = motor.getOutputValue("Comida");
        String informacion = "Una mascota con Tamaño de " + intTamano + ", un Peso Ideal de adulto de " + intPesoIdeal + " y una Actividad de " + intActividad
                + " tiene una porción de comida diaria de : " + String.valueOf(solution) + " gramos, por lo tanto:";

        int cantidad = (int) solution;
        String mensajeFinal;
        if(cantidad == 0) {
            mensajeFinal = "Error con el peso";
        }else {
            mensajeFinal = "Cantidad: " + cantidad + " gramos";
        }
        Toast.makeText(this, mensajeFinal, Toast.LENGTH_SHORT).show();

    }
}
