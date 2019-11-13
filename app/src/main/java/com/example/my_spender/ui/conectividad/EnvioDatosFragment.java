package com.example.my_spender.ui.conectividad;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_spender.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class EnvioDatosFragment extends Fragment {

    Button idEncender_Aux, idApagar, btndispensar;
    TextView estado;

    Handler bluetoothIn;
    final int handlerState = 0;
    private BluetoothAdapter btAdapter = null;
    private BluetoothSocket btSocket = null;
    private StringBuilder DataStringIN = new StringBuilder();
    private ConnectedThread MyConexionBT;
    // Identificador unico de servicio - SPP UUID
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la dirección MAC
    private static String address = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_envio_datos, container, false);
        idEncender_Aux = root.findViewById(R.id.btnEnviarD);
        idApagar = root.findViewById(R.id.btnRecibirD);
        btndispensar = root.findViewById(R.id.btnDispensarAhora);
        estado = root.findViewById(R.id.estadoD);

        if(getArguments() != null){
            address = getArguments().getString("address");
        }

        bluetoothIn = new Handler() {
            public void handleMessage(Message msg) {
                if(msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if(endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        String [] datos = dataInPrint.split(",");

                        estado.setText("Dispositivo: " + datos[0]);
                        Toast.makeText(getContext(), "Nivel de agua: " + datos[1],Toast.LENGTH_SHORT).show();
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();

        // Configuración onClick listeners para los botones
        // para indicar que se realizara cuando se detecte
        // el evento de Click

        idEncender_Aux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("1");
            }
        });

        btndispensar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("2");
            }
        });

        idApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyConexionBT.write("0");
            }
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException
    {
        //crea un conexion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }


    @Override
    public void onResume() {
        super.onResume();
        //Consigue la dirección MAC desde el anterior fragment
        if(getArguments() != null){
            address = getArguments().getString("address");
        }
        //Setea la dirección MAC
        BluetoothDevice device = btAdapter.getRemoteDevice(address);

        try{
            btSocket = createBluetoothSocket(device);
        } catch (IOException e){
            Toast.makeText(getContext(), "La creación del Socket fallo", Toast.LENGTH_LONG).show();
        }
        //Establece la conexion con el socket Bluetooth
        try {
            btSocket.connect();
        } catch (IOException e){
            try {
                btSocket.close();
            } catch (IOException e2) {}
        }
        MyConexionBT = new ConnectedThread(btSocket);
        MyConexionBT.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            btSocket.close();
        } catch (IOException e){}
    }

    //Comprueba que el dispositivo Bluetooth está disponible y solicita que se active si está desactivado
    private void VerificarEstadoBT() {
        if(btAdapter==null){
            Toast.makeText(getContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if(btAdapter.isEnabled()){
            }else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    //Crea la clase que permite crear el evento de conexión
    private class ConnectedThread extends Thread
    {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;

        public ConnectedThread(BluetoothSocket socket){
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
            try{
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {}
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e){
                    break;
                }
            }
        }

        //Envio de trama
        public void write(String input){
            try{
                mmOutStream.write(input.getBytes());
            }catch (IOException e){
                //si no es posible enviar datos se cierra la conexión
                Toast.makeText(getContext(), "La conexión fallo", Toast.LENGTH_LONG).show();
            }
        }

    }
}
