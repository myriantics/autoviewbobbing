package net.myriantics.autoviewbobbing;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.myriantics.autoviewbobbing.config.AutoViewBobbingConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoViewBobbing implements ClientModInitializer {
    //public static AutoViewBobbingConfig config = AutoViewBobbingConfig.INSTANCE;
    public static final  String MOD_ID = "autoviewbobbing";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    @Override
    public void onInitializeClient() {
        AutoViewBobbingConfig.load();
        LOGGER.info("AutoViewBobbing Initialized!");
        if (!FabricLoader.getInstance().isDevelopmentEnvironment()
                && isYaclLoaded()) {
            AutoViewBobbing.LOGGER.warn("[AutoViewBobbing] YetAnotherConfigLib is not installed! Please install it in order to access AutoViewBobbing's config!");
        }

    }

    public static boolean isYaclLoaded() {
        return FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");
    }

}
