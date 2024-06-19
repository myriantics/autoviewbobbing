package net.myriantics.autoviewbobbing;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import org.apache.logging.log4j.spi.LoggerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialModClient implements ClientModInitializer {
    public static final  String MOD_ID = "autoviewbobbing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {

        LOGGER.info("AutoViewBobbing Initialized!");

    }
}
