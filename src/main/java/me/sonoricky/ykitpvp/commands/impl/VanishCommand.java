package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class VanishCommand extends KitPvPCommand {
    public VanishCommand() {
        super(KitPvP.getInstance(), "vanish", "kitpvp.command.vanish", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        if (args.size() == 0) {

            if (KitPvP.vanishedStaffers.contains(player)) {
                KitPvP.vanishedStaffers.remove(player);
                player.sendMessage(ChatUtils.getFormattedText("vanish.disabled"));
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.showPlayer(player);
                    player.setAllowFlight(false);
                    return;
                }

            } else {
                KitPvP.vanishedStaffers.add(player);
                player.sendMessage(ChatUtils.getFormattedText("vanish.enabled"));
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.hidePlayer(player);
                    player.setAllowFlight(true);
                    return;
                }
            }
        }

        if (args.size() == 1) {
            String name = args.get(0);
            Player target = Bukkit.getPlayerExact(name);

            if (target == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return;
            }

            if (target == player) {
                if (KitPvP.vanishedStaffers.contains(player)) {
                    KitPvP.vanishedStaffers.remove(player);
                    player.sendMessage(ChatUtils.getFormattedText("vanish.disabled"));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.showPlayer(player);
                        player.setAllowFlight(false);
                        return;
                    }

                } else {
                    KitPvP.vanishedStaffers.add(player);
                    player.sendMessage(ChatUtils.getFormattedText("vanish.enabled"));
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.hidePlayer(player);
                        player.setAllowFlight(true);
                        return;
                    }
                }
            }

            if (KitPvP.vanishedStaffers.contains(target)) {
                KitPvP.vanishedStaffers.remove(target);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.showPlayer(target);
                    target.setAllowFlight(false);
                    player.sendMessage(ChatUtils.getFormattedText("vanish.disabled-for-player")
                            .replaceAll("%name%", target.getName()));
                    target.sendMessage(ChatUtils.getFormattedText("vanish.disabled-by-staff")
                            .replaceAll("%name%", player.getName()));
                    return;
                }

            } else {
                KitPvP.vanishedStaffers.add(target);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.hidePlayer(target);
                    target.setAllowFlight(true);
                    player.sendMessage(ChatUtils.getFormattedText("vanish.enabled-for-player")
                            .replaceAll("%name%", target.getName()));
                    target.sendMessage(ChatUtils.getFormattedText("vanish.enabled-by-staff")
                            .replaceAll("%name%", player.getName()));
                    return;
                }
            }
        }
        return;
    }
}
