package br.org.cesar.knot_setup_app.activity.thingList;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.org.cesar.knot_setup_app.activity.thingList.ThingContract.ViewModel;
import br.org.cesar.knot_setup_app.activity.thingList.ThingContract.Presenter;
import br.org.cesar.knot_setup_app.data.DataManager;
import br.org.cesar.knot_setup_app.model.Gateway;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class  ThingPresenter implements Presenter{
    private ViewModel mViewModel;
    private int gatewayID;
    private static DataManager dataManager;
    private static String token;

    ThingPresenter(ViewModel viewModel,int gatewayID) {
        mViewModel = viewModel;
        this.gatewayID = gatewayID;
    }


    public void getDeviceList(){
        dataManager.getInstance().getService().getDevices(token)
                .timeout(30, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleDeviceSuccess,
                        this::onErrorHandler);
    }

    private void handleDeviceSuccess(List<Gateway> gat){
        if(!gat.isEmpty()) {
            ArrayList<String> deviceList = new ArrayList<String>();

            Log.d("DEV-LOG", "Entered");
            for (Gateway gt : gat) {
                deviceList.add(gt.getName());
            }
            this.mViewModel.callbackOnDeviceList(deviceList);
        }
        else{
            Log.d("DEV-LOG","There are no devices avaiable.");
        }
    }

    private void onErrorHandler(Throwable throwable){
        Log.d("DEV-LOG", "onErrorHandler: " + throwable.getMessage());
    }

}