package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class InvseeCommand extends KitPvPCommand {
    public InvseeCommand() {
        super(KitPvP.getInstance(), "invsee", "kitpvp.command.invsee", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        if (args.size() == 0) {
            player.sendMessage(ChatUtils.getFormattedText("invsee.usage"));
            return;
        }

        if (args.size() == 1) {
            String name = args.get(0);
            Player target = Bukkit.getPlayerExact(name);

            if (target == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return;
            }

            player.openInventory(target.getInventory());
            player.sendMessage(ChatUtils.getFormattedText("invsee.success")
                    .replaceAll("%name%", target.getName()));
        }
    }
}