package net.myriantics.autoviewbobbing.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class AutoViewBobbingConfig {

    // Explosive Enhancement's config was used as an example
    // https://github.com/Superkat32/Explosive-Enhancement
    // Very awesome mod, would highly recommend
    // Makes explosions look awesome :D

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    public static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("autoviewbobbing.json");
    public static File file = PATH.toFile();
    public static AutoViewBobbingConfig INSTANCE = load();

    public boolean modEnabled = true;
    public boolean yLevelThresholdEnabled = false;
    public int yLevelThreshold = 0;
    public int autoFOVValue = 70;
    public ArrayList<String> ServerList;

    public static AutoViewBobbingConfig load() {
        AutoViewBobbingConfig config = null;
        if(file.exists()) {
            try (BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)
            )) {
                config = GSON.fromJson(fileReader, AutoViewBobbingConfig.class);
            } catch (Exception e) {
                throw new RuntimeException("Failed to load AutoViewBobbing Config :(", e);
            }
        }

        if(config == null) {
            config = new AutoViewBobbingConfig();
        }

        config.save();
        return config;

    }

    public void save() {
        try (Writer writer = Files.newBufferedWriter(
                PATH, StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE)) {
            GSON.toJson(this, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
