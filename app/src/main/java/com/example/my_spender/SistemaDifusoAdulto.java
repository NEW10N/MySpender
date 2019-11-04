package com.example.my_spender;

import android.os.Bundle;
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
    private TextView textSizeValue;
    private String textSizeAnswer;

    //cantidad del seek del tamaño
    private int SizeValue;

    //Datos para el seek del tamaño
    private SeekBar skbWeight;

    //cantidad del seek del tamaño
    private int weightValue;

    //Datos para el seek de la actividad fisica
    private SeekBar skbActivity;

    //cantidad del seek de la actividad fisica
    private int ActivityValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_confadulto);

        //El texto, seek y valor del seek del tamaño
        textSize = findViewById(R.id.texttamañodogo);
        skbSize = findViewById(R.id.seekBartadogo);
        textSizeValue = findViewById(R.id.texttamañodogo);

        //El seek del peso
        skbWeight = findViewById(R.id.seekBarWeight);

        //Texto y valor por default del tamaño
        textSizeAnswer = getResources().getString(R.string.Size);
        textSize.setText(textSizeAnswer + ": " + "Miniatura");
        SizeValue = 0;

        //Valor por default del peso
        weightValue = 0;

        //El seek de la edad
        skbWeight = findViewById(R.id.seekBarpesodogo);

        //Valor por default del peso
        ActivityValue = 0;

        //El seek de la actividad
        skbActivity = findViewById(R.id.seekBarActividad);

        //Metodos del seek del tamaño
        skbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSizeValue.setText(progress + "cm");
                if(progress <= 8){
                    textSize.setText(textSizeAnswer + ": " + "Miniatura");
                }else if(progress <= 20){
                    textSize.setText(textSizeAnswer + ": " + "Pequeño");
                }else if(progress <= 30){
                    textSize.setText(textSizeAnswer + ": " + "Mediano");
                }else {
                    textSize.setText(textSizeAnswer + ": " + "Grande");
                }
                SizeValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Metodos del seek del tamaño
        skbWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                weightValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Metodos del seek del tamaño
        skbActivity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ActivityValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void ActivateFuzzySystem() {
        if(SizeValue == 0){
            Toast.makeText(this, "Falta información del tamaño", Toast.LENGTH_SHORT).show();
        }else if(weightValue == 0) {
            Toast.makeText(this, "Falta información del peso", Toast.LENGTH_SHORT).show();
        } else if(ActivityValue == 0) {
            Toast.makeText(this, "Falta información de la actividad fisica", Toast.LENGTH_SHORT).show();
        } else {
            motorAdulto(SizeValue,weightValue,ActivityValue);
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

        Toast.makeText(this, "Calculado", Toast.LENGTH_SHORT).show();

    }
}
