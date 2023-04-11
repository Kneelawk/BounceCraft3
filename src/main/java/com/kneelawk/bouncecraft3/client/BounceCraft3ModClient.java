package com.kneelawk.bouncecraft3.client;

import net.fabricmc.api.ClientModInitializer;

import com.kneelawk.bouncecraft3.client.render.part.BCPartsClient;

public class BounceCraft3ModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BCBlocksClient.init();
        BCPartsClient.init();
    }
}
