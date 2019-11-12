package com.example.my_spender.ui.conectividad;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.my_spender.R;
import com.example.my_spender.home;

import java.util.Set;

public class ConectividadEstadoFragment extends Fragment {

    private static final String TAG = "DispositivosBT";

    ListView IdLista;

    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    private BluetoothAdapter mBtAdapter;
    private ArrayAdapter<String> mPairedDevicesArrayAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_conectividadestado, container, false);
        IdLista = root.findViewById(R.id.ListViewDispositivos);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        VerificarEstadoBI();

        // Inicializa la array que contendra la lista de los dispositivos bluetooth vinculados
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.nombre_dispositivos);
        // Presenta los dispositivos vinculados en el ListView
        IdLista.setAdapter(mPairedDevicesArrayAdapter);
        IdLista.setOnItemClickListener(mDeviceClickListener);
        // Obtiene el adaptador locak Bluetooth adapter
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        // Obtiene un conjunto de dispositivos actualmente emparejados y agrega a 'paired'
        Set<BluetoothDevice> pairedDevices = mBtAdapter.getBondedDevices();
        // Adiciona un dispositivo previo emparejado al array
        if (pairedDevices.size() > 0){
            for(BluetoothDevice device : pairedDevices){
                mPairedDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    }

    // Configura un (on-click) para la lista
    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // Obtener la dirección MAC del dispositivo, que son los ultimos 17 caracteres en la vista
            String info = ((TextView) view).getText().toString();
            String address = info.substring(info.length() - 17);

            // Realiza un intentn para iniciar la siguiente actividad
            // mientras toma un EXTRA_DEVICE_ADDRESS QUE ES LA DIRECCIÓN MAC
            final EnvioDatosFragment fragment = new EnvioDatosFragment();

            //Se crea un paquete donde se almacena el address
            Bundle cantidadCroquetas = new Bundle();
            cantidadCroquetas.putString("address", address);
            //Se envia el paquete a la siguiente fragment
            fragment.setArguments(cantidadCroquetas);

            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.contConectividadD, fragment);
            transaction.commit();
        }
    };


    private void VerificarEstadoBI(){
        //Comprueba que el dispositivo tiene Bluetoot y que está encendido.
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBtAdapter == null){
            Toast.makeText(getContext(), "El dispositivo no soporta Bluetooth", Toast.LENGTH_SHORT).show();
        } else {
            if (mBtAdapter.isEnabled()){
                Log.d(TAG, "... Bluetooth Activado...");
            } else {
                //Solicita al usuario que active Bluetooth
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

}