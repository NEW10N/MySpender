package com.example.my_spender;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_spender.ui.configuraciones.CantidadttFragment;
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
import androidx.fragment.app.FragmentTransaction;

public class SistemaDifusoCachorro extends AppCompatActivity {

    //Datos para el seek del tamaño
    private TextView textSize;
    private SeekBar skbSize;
    private TextView textSizeValue;
    private String textSizeAnswer;
    private Button sctt;
    //cantidad del seek del tamaño
    private int SizeValue;

    //Datos para el seek del tamaño
    private SeekBar skbWeight;

    //cantidad del seek del tamaño
    private int weightValue;

    //Datos para el seek de la edad
    private SeekBar skbAge;

    //Asignacion a la seekBar Edad


    //cantidad del seek de la edad
    private int AgeValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_confcachorro);

        //El texto, seek y valor del seek del tamaño
        textSize = findViewById(R.id.textSize);
        skbSize = findViewById(R.id.seekBarSize);
        textSizeValue = findViewById(R.id.textSize);

        //El seek del peso
        skbWeight = findViewById(R.id.seekBarWeight);

        //Texto y valor por default del tamaño
        textSizeAnswer = getResources().getString(R.string.Size);
        textSize.setText(textSizeAnswer + ": " + "Miniatura");
        SizeValue = 0;

        //Valor por default del peso
        weightValue = 0;

        //El seek de la edad
        skbWeight = findViewById(R.id.seekBarAge);

        //Valor por default del peso
        AgeValue = 0;

        //El seek de la edad
        skbAge = findViewById(R.id.seekBarAge);
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
        //Mover a fragment
        sctt = (Button) findViewById(R.id.btnsctc);
        sctt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CantidadttFragment fragment = new CantidadttFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.confCachorro, fragment);
                transaction.commit();
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
        skbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                AgeValue = progress;
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
        } else if(AgeValue == 0) {
            Toast.makeText(this, "Falta información de la edad", Toast.LENGTH_SHORT).show();
        } else {
            motorCachorro(SizeValue,weightValue,AgeValue);
        }
    }

    public void motorCachorro(int intTamano, int intPesoIdeal, int intEdad) {

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
        PesoIdeal.addTerm(new Ramp("2KILOGRAMOS", 4, 2));
        PesoIdeal.addTerm(new Triangle("5KILOGRAMOS", 3, 5, 8));
        PesoIdeal.addTerm(new Triangle("10KILOGRAMOS", 7, 10, 14));
        PesoIdeal.addTerm(new Triangle("17KILOGRAMOS", 13, 17, 23));
        PesoIdeal.addTerm(new Triangle("25KILOGRAMOS", 21, 25, 29));
        PesoIdeal.addTerm(new Triangle("32KILOGRAMOS", 28, 32, 37));
        PesoIdeal.addTerm(new Ramp("40KILOGRAMOS", 35, 40));
        motor.addInputVariable(PesoIdeal);

        InputVariable Edad = new InputVariable();
        Edad.setName("Edad");
        Edad.setDescription("Edad de la mascota.");
        Edad.setEnabled(true);
        Edad.setRange(0, 12);
        Edad.setLockValueInRange(false);
        Edad.addTerm(new Ramp("2MESES", 3, 2));
        Edad.addTerm(new Triangle("3MESES", 2, 3, 4));
        Edad.addTerm(new Triangle("4MESES", 3, 4, 5));
        Edad.addTerm(new Triangle("5MESES", 4, 5, 6));
        Edad.addTerm(new Ramp("6MESES", 5, 6));
        motor.addInputVariable(Edad);

        OutputVariable Comida = new OutputVariable();
        Comida.setName("Comida");
        Comida.setDescription("Cantidad de gramos de comida a proporcionar.");
        Comida.setEnabled(true);
        Comida.setRange(50, 530);
        Comida.setLockValueInRange(false);
        Comida.setAggregation(new Maximum());
        Comida.setDefuzzifier(new Centroid(100));
        Comida.setDefaultValue(Double.NaN);
        Comida.setLockPreviousValue(false);
        Comida.addTerm(new Ramp("PARA-MINIATURA-BAJO", 62, 55));
        Comida.addTerm(new Triangle("PARA-MINIATURA-MEDIO", 58, 68, 79));
        Comida.addTerm(new Triangle("PARA-MINIATURA-ALTO", 74, 83, 85));

        Comida.addTerm(new Triangle("PARA-MUY-PEQUENA-BAJO", 65, 81, 97));
        Comida.addTerm(new Triangle("PARA-MUY-PEQUENA-MEDIO", 89, 105, 120));
        Comida.addTerm(new Triangle("PARA-MUY-PEQUENA-ALTO", 113, 129, 145));

        Comida.addTerm(new Triangle("PARA-ALGO-PEQUENA-BAJO", 135, 150, 165));
        Comida.addTerm(new Triangle("PARA-ALGO-PEQUENA-MEDIO", 158, 173, 188));
        Comida.addTerm(new Triangle("PARA-ALGO-PEQUENA-ALTO", 180, 195, 210));

        Comida.addTerm(new Triangle("PARA-MEDIANA-BAJO", 190, 209, 228));
        Comida.addTerm(new Triangle("PARA-MEDIANA-MEDIO", 218, 238, 257));
        Comida.addTerm(new Triangle("PARA-MEDIANA-ALTO", 247, 266, 285));

        Comida.addTerm(new Triangle("PARA-ALGO-GRANDE-BAJO", 265, 286, 307));
        Comida.addTerm(new Triangle("PARA-ALGO-GRANDE-MEDIO", 297, 318, 339));
        Comida.addTerm(new Triangle("PARA-ALGO-GRANDE-ALTO", 328, 350, 370));

        Comida.addTerm(new Triangle("PARA-MEDIO-GRANDE-BAJO", 300, 330, 360));
        Comida.addTerm(new Triangle("PARA-MEDIO-GRANDE-MEDIO", 345, 375, 405));
        Comida.addTerm(new Triangle("PARA-MEDIO-GRANDE-ALTO", 390, 420, 450));

        Comida.addTerm(new Triangle("PARA-MUY-GRANDE-BAJO", 355, 390, 425));
        Comida.addTerm(new Triangle("PARA-MUY-GRANDE-MEDIO", 408, 443, 477));
        Comida.addTerm(new Ramp("PARA-MUY-GRANDE-ALTO", 460, 495));

        motor.addOutputVariable(Comida);

        RuleBlock option = new RuleBlock();
        option.setName("option");
        option.setDescription("Min Max");
        option.setEnabled(true);
        option.setConjunction(new Minimum());
        option.setDisjunction(null);
        option.setImplication(new Minimum());
        option.setActivation(new General());
        option.addRule(Rule.parse("if Tamano is MINIATURA and PesoIdeal is 2KILOGRAMOS and Edad is 2MESES then Comida is PARA-MINIATURA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is MINIATURA and PesoIdeal is 2KILOGRAMOS and Edad is 3MESES then Comida is PARA-MINIATURA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is MINIATURA and PesoIdeal is 2KILOGRAMOS and Edad is 4MESES then Comida is PARA-MINIATURA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is MINIATURA and PesoIdeal is 2KILOGRAMOS and Edad is 5MESES then Comida is PARA-MINIATURA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is MINIATURA and PesoIdeal is 2KILOGRAMOS and Edad is 6MESES then Comida is PARA-MINIATURA-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 5KILOGRAMOS and Edad is 2MESES then Comida is PARA-MUY-PEQUENA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 5KILOGRAMOS and Edad is 3MESES then Comida is PARA-MUY-PEQUENA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 5KILOGRAMOS and Edad is 4MESES then Comida is PARA-MUY-PEQUENA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 5KILOGRAMOS and Edad is 5MESES then Comida is PARA-MUY-PEQUENA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 5KILOGRAMOS and Edad is 6MESES then Comida is PARA-MUY-PEQUENA-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 10KILOGRAMOS and Edad is 2MESES then Comida is PARA-ALGO-PEQUENA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 10KILOGRAMOS and Edad is 3MESES then Comida is PARA-ALGO-PEQUENA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 10KILOGRAMOS and Edad is 4MESES then Comida is PARA-ALGO-PEQUENA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 10KILOGRAMOS and Edad is 5MESES then Comida is PARA-ALGO-PEQUENA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is PEQUENO and PesoIdeal is 10KILOGRAMOS and Edad is 6MESES then Comida is PARA-ALGO-PEQUENA-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is MEDIANO and PesoIdeal is 17KILOGRAMOS and Edad is 2MESES then Comida is PARA-MEDIANA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is MEDIANO and PesoIdeal is 17KILOGRAMOS and Edad is 3MESES then Comida is PARA-MEDIANA-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is MEDIANO and PesoIdeal is 17KILOGRAMOS and Edad is 4MESES then Comida is PARA-MEDIANA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is MEDIANO and PesoIdeal is 17KILOGRAMOS and Edad is 5MESES then Comida is PARA-MEDIANA-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is MEDIANO and PesoIdeal is 17KILOGRAMOS and Edad is 6MESES then Comida is PARA-MEDIANA-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 25KILOGRAMOS and Edad is 2MESES then Comida is PARA-ALGO-GRANDE-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 25KILOGRAMOS and Edad is 3MESES then Comida is PARA-ALGO-GRANDE-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 25KILOGRAMOS and Edad is 4MESES then Comida is PARA-ALGO-GRANDE-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 25KILOGRAMOS and Edad is 5MESES then Comida is PARA-ALGO-GRANDE-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 25KILOGRAMOS and Edad is 6MESES then Comida is PARA-ALGO-GRANDE-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 32KILOGRAMOS and Edad is 2MESES then Comida is PARA-MEDIO-GRANDE-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 32KILOGRAMOS and Edad is 3MESES then Comida is PARA-MEDIO-GRANDE-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 32KILOGRAMOS and Edad is 4MESES then Comida is PARA-MEDIO-GRANDE-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 32KILOGRAMOS and Edad is 5MESES then Comida is PARA-MEDIO-GRANDE-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 32KILOGRAMOS and Edad is 6MESES then Comida is PARA-MEDIO-GRANDE-ALTO", motor));

        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 40KILOGRAMOS and Edad is 2MESES then Comida is PARA-MUY-GRANDE-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 40KILOGRAMOS and Edad is 3MESES then Comida is PARA-MUY-GRANDE-BAJO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 40KILOGRAMOS and Edad is 4MESES then Comida is PARA-MUY-GRANDE-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 40KILOGRAMOS and Edad is 5MESES then Comida is PARA-MUY-GRANDE-MEDIO", motor));
        option.addRule(Rule.parse("if Tamano is GRANDE and PesoIdeal is 40KILOGRAMOS and Edad is 6MESES then Comida is PARA-MUY-GRANDE-ALTO", motor));

        motor.addRuleBlock(option);

        //Uso del sistema
        motor.setInputValue("Tamano", intTamano);
        motor.process();
        motor.setInputValue("PesoIdeal", intPesoIdeal);
        motor.process();
        motor.setInputValue("Edad", intEdad);
        motor.process();
        double solution = motor.getOutputValue("Comida");
        String informacion = "Una mascota con Tamaño de " + intTamano + ", un Peso Ideal de adulto de " + intPesoIdeal + " y una Edad de " + intEdad + " tiene una porción de comida diaria de : "
                + String.valueOf(solution) + " gramos, por lo tanto:";

        Toast.makeText(this, "Calculado", Toast.LENGTH_SHORT).show();

    }
}
