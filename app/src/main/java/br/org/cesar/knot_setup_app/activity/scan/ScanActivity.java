package br.org.cesar.knot_setup_app.activity.scan;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.org.cesar.knot_setup_app.R;
import br.org.cesar.knot_setup_app.domain.adapter.ScanAdapter;
import br.org.cesar.knot_setup_app.activity.configureDevice.ConfigureDeviceActivity;
import br.org.cesar.knot_setup_app.model.BluetoothDevice;
import br.org.cesar.knot_setup_app.utils.Constants;

public class ScanActivity extends AppCompatActivity implements  ScanContract.ViewModel {

    private ScanAdapter adapter;
    private ScanContract.Presenter mPresenter;
    private List<BluetoothDevice> deviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_for_devices);

        TextView headerTitle = (TextView)findViewById(R.id.list_title);
        headerTitle.setText("Looking for devices...");
        ImageView image = (ImageView) findViewById(R.id.imageView1);
        image.setImageResource(R.drawable.asset_bluetooth_white);


        Boolean operation =  getIntent().getBooleanExtra("operation",false);
        int gatewayID = getIntent().getIntExtra("gatewayID",0);

        mPresenter = new ScanPresenter(this,operation,gatewayID);
        mPresenter.startScan();

        Toast.makeText(getApplicationContext(), "Start configuring your device...", Toast
                .LENGTH_LONG).show();

        setupAdapter();
    }

    /**
     * Setup device list adapter
     *
     */
    private void setupAdapter() {
        //Define list view and adapter
        deviceList = new ArrayList<>();
        adapter = new ScanAdapter(this, R.layout.item_device, deviceList);

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //When device is clicked we must connect to it
                final BluetoothDevice device = deviceList.get(position);
                mPresenter.connectToDevice(device);
            }
        });
    }

    @Override
    public void callbackOnGatewaySelected(int gatewayID, boolean operation){
        this.mPresenter.stopScan();
        Intent intent = new Intent(ScanActivity.this, ConfigureDeviceActivity.class);
        intent.putExtra("gatewayID",gatewayID);
        intent.putExtra("operation",operation);
        startActivity(intent);
        finish();
    }

    @Override
    public void callbackOnThingSelected(boolean operation) {
        this.mPresenter.stopScan();
        Intent intent = new Intent(ScanActivity.this, ConfigureDeviceActivity.class);
        intent.putExtra("operation",operation);
        startActivity(intent);
        finish();
    }

    @Override
    public void callbackOnDeviceFound(List<BluetoothDevice> deviceList){
        this.deviceList.clear();
        this.deviceList.addAll(deviceList);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void callbackOnScanFail(){
        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                Toast.makeText(ScanActivity.this, "Scan for device" +
                        " has failed. Check if your bluetooth is available.", Toast.LENGTH_LONG).show();
            }
        });

        finish();
    }

    @Override
    public void callbackOnBluetoothPermissionRequired() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        this.startActivity(intent);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                Constants.BLUETOOTH_PERMISSION_ID);
        this.finish();
    }

    protected void onDestroy(){
        super.onDestroy();
        mPresenter.stopScan();
    }

}
