package net.myriantics.autoviewbobbing;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoViewBobbing implements ClientModInitializer {
    public static final  String MOD_ID = "autoviewbobbing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {

        LOGGER.info("AutoViewBobbing Initialized!");

    }
}
