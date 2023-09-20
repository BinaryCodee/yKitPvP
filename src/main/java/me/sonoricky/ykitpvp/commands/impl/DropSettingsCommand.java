package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.data.PlayerData;
import me.sonoricky.ykitpvp.inventory.InventoryMaker;
import me.sonoricky.ykitpvp.items.ItemMaker;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DropSettingsCommand extends KitPvPCommand {

    private final List<String> lines = KitPvP.getFileManager().getConfig().getStringList("drop-settings.lore");

    public DropSettingsCommand() {
        super(KitPvP.getInstance(), "dropsettings", "kitpvp.command.dropsettings", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (!(sender instanceof Player)) return;

        Player player = (Player) sender;
        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId());

        Inventory inventory = new InventoryMaker(27, "Impostazioni raccolta oggetti")
                .addItemStack(12, getGoldenApple(data))
                .addAction(12, (event) -> {
                    Inventory clickedInventory = event.getClickedInventory();
                    PlayerData clickerData = KitPvP.getDataManager().getPlayerData(event.getWhoClicked().getUniqueId());

                    clickerData.pickupGoldenApple = !clickerData.pickupGoldenApple;

                    event.setCancelled(true);
                    KitPvP.getDataManager().updateData(clickerData);

                    clickedInventory.setItem(12, getGoldenApple(clickerData));
                    clickedInventory.setItem(14, getArrow(clickerData));
                })
                .addItemStack(14, getArrow(data))
                .addAction(14, (event) -> {
                    Inventory clickedInventory = event.getClickedInventory();
                    PlayerData clickerData = KitPvP.getDataManager().getPlayerData(event.getWhoClicked().getUniqueId());

                    clickerData.pickupArrows = !clickerData.pickupArrows;

                    event.setCancelled(true);
                    KitPvP.getDataManager().updateData(clickerData);

                    clickedInventory.setItem(12, getGoldenApple(clickerData));
                    clickedInventory.setItem(14, getArrow(clickerData));
                })
                .get();

        player.openInventory(inventory);
    }

    private ItemStack getArrow(PlayerData data) {
        ItemMaker arrow = new ItemMaker(Material.ARROW);
        lines.forEach(lore -> arrow
                .addLoreLine(ChatUtils.getColoredText(lore
                        .replaceAll("%enabled%", KitPvP.getFileManager().getConfig().getString("drop-settings." + (data.pickupArrows ? "enabled" : "disabled") + "-text"))
                )));

        return arrow.get();
    }

    private ItemStack getGoldenApple(PlayerData data) {
        ItemMaker apple = new ItemMaker(Material.GOLDEN_APPLE);
        lines.forEach(lore -> apple
                .addLoreLine(ChatUtils.getColoredText(lore
                        .replaceAll("%enabled%", KitPvP.getFileManager().getConfig().getString("drop-settings." + (data.pickupGoldenApple ? "enabled" : "disabled") + "-text"))
                )));

        return apple.get();
    }

}
