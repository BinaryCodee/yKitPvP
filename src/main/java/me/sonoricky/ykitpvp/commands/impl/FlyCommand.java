package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class FlyCommand extends KitPvPCommand {
    public FlyCommand() {
        super(KitPvP.getInstance(), "fly", "kitpvp.command.fly", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        if (args.size() == 0) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.sendMessage(ChatUtils.getFormattedText("fly.disabled"));
                return;
            } else {
                player.setAllowFlight(true);
                player.sendMessage(ChatUtils.getFormattedText("fly.enabled"));
                return;
            }
        }

        String name = args.get(0);
        Player target = Bukkit.getPlayer(name);

        if (target == null) {
            player.sendMessage(ChatUtils.getFormattedText("player-offline"));
            return;

        } else {
            if (target.getAllowFlight()) {
                target.setAllowFlight(false);
                player.sendMessage(ChatUtils.getFormattedText("fly.disabled-for-player"));
                return;
            } else {
                target.setAllowFlight(true);
                player.sendMessage(ChatUtils.getFormattedText("fly.enabled-for-player"));
                return;
            }
        }
    }
}
