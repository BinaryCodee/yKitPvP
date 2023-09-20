package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class TrashCommand extends KitPvPCommand {
    public TrashCommand() {
        super(KitPvP.getInstance(), "trash", "kitpvp.command.trash", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        Inventory inv = Bukkit.getServer().createInventory(null, 54, ChatColor.GRAY + "Cestino");

        player.openInventory(inv);
        player.sendMessage(ChatUtils.getFormattedText("trash.success"));
    }
}
