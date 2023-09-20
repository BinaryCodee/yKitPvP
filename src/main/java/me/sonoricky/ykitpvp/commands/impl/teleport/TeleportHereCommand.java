package me.sonoricky.ykitpvp.commands.impl.teleport;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportHereCommand extends KitPvPCommand {
    public TeleportHereCommand() {
        super(KitPvP.getInstance(), "tphere", "kitpvp.command.tphere", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        if (args.size() == 0) {
            player.sendMessage(ChatUtils.getFormattedText("tphere.usage"));
            return;
        }

        if (args.size() == 1) {
            String name = args.get(0);

            if (Bukkit.getPlayerExact(name) == player) {
                player.sendMessage(ChatUtils.getFormattedText("tphere.tp-yourself-error"));
                return;
            }

            if (Bukkit.getPlayerExact(name) == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return;
            }

            Player target = Bukkit.getPlayerExact(name);

            target.teleport(player.getLocation());
            player.sendMessage(ChatUtils.getFormattedText("tphere.success")
                    .replaceAll("%name%", target.getName()));
        }
    }
}
