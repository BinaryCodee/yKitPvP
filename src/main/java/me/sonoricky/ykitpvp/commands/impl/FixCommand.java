package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FixCommand extends KitPvPCommand {

    public FixCommand() {
        super(KitPvP.getInstance(), "fix", "kitpvp.command.fix", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        openAnvilGUI(player);
    }
    private void openAnvilGUI(Player player) {
        Inventory anvilGUI = Bukkit.createInventory(null, 9, "Anvil");

        ItemStack anvilItem = new ItemStack(Material.ANVIL);

        anvilGUI.setItem(4, anvilItem);

        player.openInventory(anvilGUI);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory().getTitle().equals("Anvil")) {
            if (event.getSlot() == 4) {
                event.setCancelled(true);
                event.getWhoClicked().sendMessage("Hello!");
                return;
            }
            return;
        }
    }
}
