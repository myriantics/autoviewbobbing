package net.myriantics.autoviewbobbing.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.gui.controllers.BooleanController;
import dev.isxander.yacl3.gui.controllers.slider.IntegerSliderController;
import dev.isxander.yacl3.gui.controllers.string.number.IntegerFieldController;
import dev.isxander.yacl3.impl.YetAnotherConfigLibImpl;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.myriantics.autoviewbobbing.AutoViewBobbing;

import java.util.ArrayList;

public class YACLIntegration {
    public static Screen makeScreen(Screen parent) {
        AutoViewBobbingConfig config = AutoViewBobbingConfig.INSTANCE;
        AutoViewBobbingConfig defaults = new AutoViewBobbingConfig();
        var yacl = YetAnotherConfigLib.createBuilder()
                .title(Text.translatable("autoviewbobbing.title"));

        var generalCategoryBuilder = ConfigCategory.createBuilder()
                .name(Text.translatable("autoviewbobbing.general.category"));

        // very important settings (the rest are still important but this category is for basically just turning the mod on and off [pretty important])
        var importantSettingsGroup = OptionGroup.createBuilder()
                .name(Text.translatable("autoviewbobbing.group.important_settings"))
                .description(OptionDescription.createBuilder()
                        .text(Text.translatable("autoviewbobbing.group.important_settings.tooltip"))
                        .build());

        var modEnabled = Option.<Boolean>createBuilder()
                .name(Text.translatable("autoviewbobbing.important_settings.mod_enabled"))
                .description(OptionDescription.createBuilder()
                        .text(Text.translatable("autoviewbobbing.important_settings.mod_enabled.tooltip"))
                        .build())
                .binding(
                        defaults.modEnabled,
                        () -> config.modEnabled,
                        val -> config.modEnabled = val
                )
                .customController(booleanOption -> new BooleanController(booleanOption, true))
                .build();

        importantSettingsGroup.option(modEnabled);
        generalCategoryBuilder.group(importantSettingsGroup.build());


        var generalSettingsGroup = OptionGroup.createBuilder()
                .name(Text.translatable("autoviewbobbing.group.general"))
                .description(OptionDescription.createBuilder()
                        .text(Text.translatable("autoviewbobbing.group.general.tooltip"))
                        .build());

        var yLevelThresholdEnabled = Option.<Boolean>createBuilder()
                .name(Text.translatable("autoviewbobbing.general.y_threshold_toggle"))
                .description(OptionDescription.createBuilder()
                        .text(Text.translatable("autoviewbobbing.general.y_threshold_toggle.tooltip"))
                        .build())
                .binding(
                        defaults.yLevelThresholdEnabled,
                        () -> config.yLevelThresholdEnabled,
                        val -> config.yLevelThresholdEnabled = val
                )
                .customController(booleanOption -> new BooleanController(booleanOption, true))
                .build();

        var yLevelThreshold = Option.<Integer>createBuilder()
                .name(Text.translatable("autoviewbobbing.general.y_threshold"))
                .description(OptionDescription.createBuilder()
                        .text(Text.translatable("autoviewbobbing.general.y_threshold.tooltip"))
                        .build())
                .binding(
                        defaults.yLevelThreshold,
                        () -> config.yLevelThreshold,
                        val -> config.yLevelThreshold = val
                )
                .customController(integerOption -> new <Number>IntegerFieldController(integerOption, -64, 320 ))
                .build();

        var autoFOVValue = Option.<Integer>createBuilder()
                .name(Text.translatable("autoviewbobbing.general.auto_fov_value"))
                .description(OptionDescription.createBuilder()
                        .text(Text.translatable("autoviewbobbing.general.auto_fov_value.tooltip"))
                        .build())
                .binding(
                        defaults.autoFOVValue,
                        () -> config.autoFOVValue,
                        val -> config.autoFOVValue = val
                )
                .customController(integerOption -> new <Number>IntegerSliderController(integerOption, 30, 110, 1))
                .build();

        generalSettingsGroup.option(yLevelThresholdEnabled);
        generalSettingsGroup.option(yLevelThreshold);
        generalSettingsGroup.option(autoFOVValue);
        generalCategoryBuilder.group(generalSettingsGroup.build());

        yacl.category(generalCategoryBuilder.build());

        return yacl.build().generateScreen(parent);
    }

}