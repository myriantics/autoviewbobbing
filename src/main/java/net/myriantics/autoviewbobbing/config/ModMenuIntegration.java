package net.myriantics.autoviewbobbing.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.myriantics.autoviewbobbing.AutoViewBobbing;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if(AutoViewBobbing.isYaclLoaded()) {
            return YACLIntegration::makeScreen;
        }
        return parent -> null;
    }

}
