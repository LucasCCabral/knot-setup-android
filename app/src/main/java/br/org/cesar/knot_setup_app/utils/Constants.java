package br.org.cesar.knot_setup_app.utils;

import java.util.UUID;

public class Constants{
    public static final UUID OT_SETTINGS_SERVICE = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d30");
    public static final UUID CHANNEL_CHARACTERISTIC = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d31");
    public static final UUID NET_NAME_CHARACTERISTIC = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d32");
    public static final UUID PAN_ID_CHARACTERISTIC = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d33"); //Int
    public static final UUID XPANID_CHARACTERISTIC = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d34");
    public static final UUID MASTER_KEY_CHARACTERISTIC = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d35");
    public static final UUID IPV6_SERVICE = UUID.fromString("49601183-5db4-498b-b35a-e6ddbe1c1470");
    public static final UUID IPV6_CHARACTERISTIC = UUID.fromString("49601183-5db4-498b-b35a-e6ddbe1c1471");

    public static final UUID OT_SETTINGS_SERVICE_GATEWAY = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900e30");
    public static final UUID CHANNEL_CHARACTERISTIC_GATEWAY = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d31");
    public static final UUID NET_NAME_CHARACTERISTIC_GATEWAY = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d32");
    public static final UUID PAN_ID_CHARACTERISTIC_GATEWAY = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d33"); //Int
    public static final UUID XPANID_CHARACTERISTIC_GATEWAY = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d34");
    public static final UUID MASTER_KEY_CHARACTERISTIC_GATEWAY = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d35");
    public static final UUID IPV6_CHARACTERISTIC_GATEWAY = UUID.fromString("a8a9e49c-aa9a-d441-9bec-817bb4900d36");

    public static final String BASE_URL = "http://localhost:8080";
    public static final String Oauth = "";
}
