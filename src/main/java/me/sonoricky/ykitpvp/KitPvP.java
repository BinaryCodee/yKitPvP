package me.sonoricky.ykitpvp;

import me.sonoricky.ykitpvp.blocks.BlockListener;
import me.sonoricky.ykitpvp.blocks.BlockTask;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.commands.impl.*;
import me.sonoricky.ykitpvp.commands.impl.gamemodes.AdventureCommand;
import me.sonoricky.ykitpvp.commands.impl.gamemodes.CreativeCommand;
import me.sonoricky.ykitpvp.commands.impl.gamemodes.SpectatorCommand;
import me.sonoricky.ykitpvp.commands.impl.gamemodes.SurvivalCommand;
import me.sonoricky.ykitpvp.commands.impl.teleport.TeleportAllCommand;
import me.sonoricky.ykitpvp.commands.impl.teleport.TeleportCommand;
import me.sonoricky.ykitpvp.commands.impl.teleport.TeleportHereCommand;
import me.sonoricky.ykitpvp.data.PlayerDataManager;
import me.sonoricky.ykitpvp.inventory.InventoryListener;
import me.sonoricky.ykitpvp.listeners.ObsidianBreaker;
import me.sonoricky.ykitpvp.listeners.PlayerListener;
import me.sonoricky.ykitpvp.listeners.PunchListener;
import me.sonoricky.ykitpvp.placeholders.MainPlaceholder;
import me.sonoricky.ykitpvp.tasks.GeneralTask;
import me.sonoricky.ykitpvp.tasks.SaveTask;
import me.sonoricky.ykitpvp.utils.FileManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class KitPvP extends JavaPlugin {
    public static boolean registered;
    public static boolean alreadyLogged;

    public static List<Player> vanishedStaffers;

    @Override
    public void onEnable() {
        instance = this;
        fileManager = new FileManager(instance);
        this.LicenseLoad();

        Arrays.asList(new CreativeCommand(), new AdventureCommand(), new SpectatorCommand(), new SurvivalCommand(), new TeleportAllCommand(), new TeleportCommand(), new TeleportHereCommand(),
        new DiscordCommand(), new DropSettingsCommand(), new FixCommand(), new FlyCommand(), new GiveXPCommand(), new InvseeCommand(), new MainCommand(), new SpawnCommand(),
        new StackCommand(), new StoreCommand(), new TrashCommand(), new WithdrawCommand(), new VanishCommand(), new VoteCommand())
                .forEach(KitPvPCommand::registerExecutor);

        Arrays.asList(new BlockListener(), new PunchListener(), new FixCommand(), new WithdrawCommand(), new PlayerListener(), new InventoryListener(),
                        new ObsidianBreaker(this, 15, Material.GOLD_PICKAXE))
                .forEach(event -> Bukkit.getPluginManager().registerEvents((Listener) event, this));

        dataManager = new PlayerDataManager();
        blockManager = new BlockTask();

        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new MainPlaceholder().register();
        }

        saveManager = new SaveTask();

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, saveManager, 600L, 600L);
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new GeneralTask(), 2L, 2L);

        if (getServer().getPluginManager().isPluginEnabled("Vault")) {
            RegisteredServiceProvider<Economy> service = getServer().getServicesManager().getRegistration(Economy.class);
            if (service != null) {
                economy = service.getProvider();
            }
        }
    }

    private static void LicenseLoad() {
        try {
            URL url = new URL("https://pastebin.com/raw/M5RBbGKW");

            try (Scanner scanner = new Scanner(url.openStream())) {
                while (scanner.hasNext()) {
                    if (!KitPvP.getInstance().getConfig().getString("license").equals(scanner.nextLine().split(":")[0])) {
                        KitPvP.alreadyLogged = true;
                        System.out.println("Licenza non valida");
                        Bukkit.getPluginManager().disablePlugin(KitPvP.getInstance());
                        return;
                    }

                    System.out.println("Licenza valida");
                    KitPvP.registered = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Errore durante il caricamento della licenza: " + e.getMessage());
            Bukkit.getPluginManager().disablePlugin(KitPvP.getInstance());
        }
    }

    public void reloadConfiguration() {
        fileManager = new FileManager(instance);
    }

    @Override
    public void onDisable() {
        if (saveManager == null) return;
        saveManager.run();
    }

    static { KitPvP.vanishedStaffers = new ArrayList<Player>(); }

    private static KitPvP instance;

    public static KitPvP getInstance() {
        return instance;
    }

    private static PlayerDataManager dataManager;

    public static PlayerDataManager getDataManager() {
        return dataManager;
    }

    private static BlockTask blockManager;

    public static BlockTask getBlockManager() {
        return blockManager;
    }

    private static SaveTask saveManager;

    public static SaveTask getSaveManager() {
        return saveManager;
    }

    private static FileManager fileManager;

    public static FileManager getFileManager() {
        return fileManager;
    }

    private static Economy economy;

    public static Economy getEconomy() {
        return economy;
    }

    static {
        KitPvP.alreadyLogged = false;
    }
}
