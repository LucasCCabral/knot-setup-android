package br.org.cesar.knot_setup_app.view.gatewayConnected;

import android.net.nsd.NsdServiceInfo;

import java.util.ArrayList;

public interface GatewayConnectedContract {

    interface ViewModel {
        void onGatewaysFound(ArrayList<NsdServiceInfo> deviceList);
        void setSearchingFeedback(int visibility);
    }

    interface Presenter {
        void onFocusLost();
        void onGatewayClicked(NsdServiceInfo serviceInfo);
        void onFocus();
    }
}
