package com.kneelawk.bouncecraft3.client;

import net.fabricmc.api.ClientModInitializer;

public class BounceCraft3ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BCBlocksClient.init();
    }
}
