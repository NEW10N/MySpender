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
    private String size;
    private TextView optionSizeArray[] = new TextView[4];
    private Button sctt;
    //cantidad del seek del tamaño
    private int sizeValue;

    //Datos para el seek del peso
    private TextView textWeight;
    private SeekBar skbWeight;
    private String weight;
    //cantidad del seek del peso
    private int weightValue;

    //Datos para el seek de la edad
    private TextView textAge;
    private SeekBar skbAge;
    private String age;
    //cantidad del seek de la edad
    private int ageValue;

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
        setContentView(R.layout.fragment_confcachorro);

        //El seek y las opciones del seek del tamaño
        textSize = findViewById(R.id.textSizeCachorro);
        skbSize = findViewById(R.id.seekBarSizeCachorro);
        optionSizeArray[0] = findViewById(R.id.optionSizeCachorro1);
        optionSizeArray[1] = findViewById(R.id.optionSizeCachorro2);
        optionSizeArray[2] = findViewById(R.id.optionSizeCachorro3);
        optionSizeArray[3] = findViewById(R.id.optionSizeCachorro4);
        //Valor y color por default del tamaño
        size = getResources().getString(R.string.Size);
        textSize.setText(size + ": 0cm");
        textSize.setTextColor(getColor(R.color.colorRed));
        sizeValue = 0;

        //El seek y las opciones del peso
        textWeight = findViewById(R.id.textWeightCachorro);
        skbWeight = findViewById(R.id.seekBarWeightCachorro);
        //Valor y color por default del peso
        weight = getResources().getString(R.string.Weight);
        textWeight.setText(weight + ": 0kg");
        textWeight.setTextColor(getColor(R.color.colorRed));
        weightValue = 0;

        //El seek, las opciones y el valor de la edad
        textAge = findViewById(R.id.textAgeCachorro);
        skbAge = findViewById(R.id.seekBarAgeCachorro);
        //Valor por default de la edad
        age = getResources().getString(R.string.Age);
        textAge.setText(age + ": 0 meses");
        textAge.setTextColor(getColor(R.color.colorRed));
        ageValue = 0;


        //Metodos del seek del tamaño
        skbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textSize.setText(size + ": " + progress + "cm");
                /*
                 * Miniatura: 0 35
                 * Pequeño: 25 55
                 * Mediano 45 a 75
                 * Grande: 65 a 80
                 * */
                if(progress == 0) {
                    textSize.setTextColor(getColor(R.color.colorRed));
                    optionSizeArray[0].setBackgroundColor(getColor(R.color.colorPantalla));
                    optionSizeArray[0].setTextColor(getColor(R.color.colorBlack));
                }else{
                    textSize.setTextColor(getColor(R.color.colorBlack));
                    if(progress <= 30){
                        minWeight = 1;
                        maxWeight = 3;
                        optionSizeArray[0].setBackgroundColor(getColor(R.color.colorAccent));
                        optionSizeArray[0].setTextColor(getColor(R.color.colorWhite));
                        optionSizeArray[1].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionSizeArray[1].setTextColor(getColor(R.color.colorBlack));
                        if(progress > 25){
                            maxWeight = 13;
                            optionSizeArray[1].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                    }else if(progress <= 50){
                        minWeight = 4;
                        maxWeight = 13;
                        optionSizeArray[0].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionSizeArray[0].setTextColor(getColor(R.color.colorBlack));
                        optionSizeArray[1].setBackgroundColor(getColor(R.color.colorAccent));
                        optionSizeArray[1].setTextColor(getColor(R.color.colorWhite));
                        optionSizeArray[2].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionSizeArray[2].setTextColor(getColor(R.color.colorBlack));
                        if (progress < 35) {
                            minWeight = 1;
                            optionSizeArray[0].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                        if (progress > 45){
                            maxWeight = 22;
                            optionSizeArray[2].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                    }else if(progress <= 70){
                        minWeight = 14;
                        maxWeight = 22;
                        optionSizeArray[1].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionSizeArray[1].setTextColor(getColor(R.color.colorBlack));
                        optionSizeArray[2].setBackgroundColor(getColor(R.color.colorAccent));
                        optionSizeArray[2].setTextColor(getColor(R.color.colorWhite));
                        optionSizeArray[3].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionSizeArray[3].setTextColor(getColor(R.color.colorBlack));
                        if (progress < 55){
                            minWeight = 4;
                            optionSizeArray[1].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                        if (progress > 65) {
                            maxWeight = 40;
                            optionSizeArray[3].setBackgroundColor(getColor(R.color.colorOnAccent));
                        }
                    }else {
                        minWeight = 22;
                        optionSizeArray[2].setBackgroundColor(getColor(R.color.colorPantalla));
                        optionSizeArray[2].setTextColor(getColor(R.color.colorBlack));
                        optionSizeArray[3].setBackgroundColor(getColor(R.color.colorAccent));
                        optionSizeArray[3].setTextColor(getColor(R.color.colorWhite));
                        if (progress <= 74) {
                            minWeight = 14;
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
                //String ValorTamaño = "Tamaño: " + SizeValue + "cm";
                //Toast.makeText(getApplicationContext(), ValorTamaño, Toast.LENGTH_SHORT).show();
            }
        });
        //Mover a fragment
        /*
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
         */
        //Metodos del seek del tamaño
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
                //String ValorPeso = "Peso: " + weightValue + "kg";
                //Toast.makeText(getApplicationContext(), ValorPeso, Toast.LENGTH_SHORT).show();
            }
        });

        //Metodos del seek del tamaño
        skbAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 1){
                    textAge.setText(age + ": " + progress + " mes");
                }else{
                    textAge.setText(age + ": " + progress + " meses");
                }
                if(progress == 0) {
                    textAge.setTextColor(getColor(R.color.colorRed));
                }else{
                    textAge.setTextColor(getColor(R.color.colorBlack));
                }
                ageValue = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //String ValorTamaño = "Edad: " + AgeValue + " meses";
                //Toast.makeText(getApplicationContext(), ValorTamaño, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void ActivateFuzzySystem(View v) {
        if(sizeValue == 0){
            Toast.makeText(this, "Falta información del tamaño", Toast.LENGTH_SHORT).show();
        }else if(weightValue == 0) {
            Toast.makeText(this, "Falta información del peso", Toast.LENGTH_SHORT).show();
        } else if(ageValue == 0) {
            Toast.makeText(this, "Falta información de la edad", Toast.LENGTH_SHORT).show();
        } else {
            motorCachorro(sizeValue,weightValue,ageValue);
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

        int cantidad = (int) solution;
        String mensajeFinal;
        if(cantidad == 0) {
            mensajeFinal = "Error con el peso";
            Toast.makeText(this, mensajeFinal, Toast.LENGTH_SHORT).show();
        }else {
            CantidadttFragment fragment = new CantidadttFragment();

            //Se crea un paquete donde se almacena el dato de croquetas
            Bundle cantidadCroquetas = new Bundle();
            cantidadCroquetas.putInt("Croquetas", cantidad);
            cantidadCroquetas.putInt("Tamaño", sizeValue);
            cantidadCroquetas.putInt("Peso", weightValue);
            cantidadCroquetas.putInt("Edad", ageValue);
            //Se envia el paquete a la siguiente fragment
            fragment.setArguments(cantidadCroquetas);

            //Se traslada el fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.confCachorro, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }
}
