package com.example.my_spender;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

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

public class SistemaDifusoCachorro extends AppCompatActivity {

    //Datos para el seek del tamaño
    private SeekBar skbSize;
    private TextView optionSizeArray[] = new TextView[4];
    //cantidad del seek del tamaño
    private int SizeValue;

    //Datos para el seek del peso
    private SeekBar skbWeight;
    private TextView optionWeightArray[] = new TextView[4];
    //cantidad del seek del peso
    private int weightValue;

    //Datos para el seek de la edad
    private SeekBar skbAge;
    //cantidad del seek de la edad
    private int AgeValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_confcachorro);

        //El seek y las opciones del seek del tamaño
        skbSize = findViewById(R.id.seekBarSizeCachorro);
        optionSizeArray[0] = findViewById(R.id.optionSizeCachorro1);
        optionSizeArray[1] = findViewById(R.id.optionSizeCachorro2);
        optionSizeArray[2] = findViewById(R.id.optionSizeCachorro3);
        optionSizeArray[3] = findViewById(R.id.optionSizeCachorro4);
        //Valor y color por default del tamaño
        SizeValue = 0;
        optionSizeArray[0].setBackgroundColor(getColor(R.color.colorAccent));

        //El seek y las opciones del peso
        skbWeight = findViewById(R.id.seekBarWeightCachorro);
        optionWeightArray[0] = findViewById(R.id.optionWeightCachorro1);
        optionWeightArray[1] = findViewById(R.id.optionWeightCachorro2);
        optionWeightArray[2] = findViewById(R.id.optionWeightCachorro3);
        optionWeightArray[3] = findViewById(R.id.optionWeightCachorro4);
        //Valor y color por default del peso
        weightValue = 0;
        optionWeightArray[0].setBackgroundColor(getColor(R.color.colorAccent));

        //El seek, las opciones y el valor de la edad
        skbAge = findViewById(R.id.seekBarAgeCachorro);
        //Valor por default de la edad
        AgeValue = 0;


        //Metodos del seek del tamaño
        skbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress <= 8){
                    optionSizeArray[0].setBackgroundColor(getColor(R.color.colorAccent));
                    optionSizeArray[1].setBackgroundColor(getColor(R.color.colorWhite));
                }else if(progress <= 20){
                    optionSizeArray[0].setBackgroundColor(getColor(R.color.colorWhite));
                    optionSizeArray[1].setBackgroundColor(getColor(R.color.colorAccent));
                    optionSizeArray[2].setBackgroundColor(getColor(R.color.colorWhite));
                }else if(progress <= 30){
                    optionSizeArray[1].setBackgroundColor(getColor(R.color.colorWhite));
                    optionSizeArray[2].setBackgroundColor(getColor(R.color.colorAccent));
                    optionSizeArray[3].setBackgroundColor(getColor(R.color.colorWhite));
                }else {
                    optionSizeArray[2].setBackgroundColor(getColor(R.color.colorWhite));
                    optionSizeArray[3].setBackgroundColor(getColor(R.color.colorAccent));
                }
                SizeValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String ValorTamaño = "Tamaño: " + SizeValue + "cm";
                Toast.makeText(getApplicationContext(), ValorTamaño, Toast.LENGTH_SHORT).show();
            }
        });

        //Metodos del seek del tamaño
        skbWeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress <= 15){
                    optionWeightArray[0].setBackgroundColor(getColor(R.color.colorAccent));
                    optionWeightArray[1].setBackgroundColor(getColor(R.color.colorWhite));
                }else if(progress <= 30){
                    optionWeightArray[0].setBackgroundColor(getColor(R.color.colorWhite));
                    optionWeightArray[1].setBackgroundColor(getColor(R.color.colorAccent));
                    optionWeightArray[2].setBackgroundColor(getColor(R.color.colorWhite));
                }else if(progress <= 60){
                    optionWeightArray[1].setBackgroundColor(getColor(R.color.colorWhite));
                    optionWeightArray[2].setBackgroundColor(getColor(R.color.colorAccent));
                    optionWeightArray[3].setBackgroundColor(getColor(R.color.colorWhite));
                }else {
                    optionWeightArray[2].setBackgroundColor(getColor(R.color.colorWhite));
                    optionWeightArray[3].setBackgroundColor(getColor(R.color.colorAccent));
                }
                weightValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String ValorPeso = "Peso: " + weightValue + "kg";
                Toast.makeText(getApplicationContext(), ValorPeso, Toast.LENGTH_SHORT).show();
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
                String ValorTamaño = "Edad: " + AgeValue + " meses";
                Toast.makeText(getApplicationContext(), ValorTamaño, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ActivateFuzzySystem(View v) {
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
