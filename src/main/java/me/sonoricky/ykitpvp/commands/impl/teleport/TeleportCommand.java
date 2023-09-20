package me.sonoricky.ykitpvp.commands.impl.teleport;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportCommand extends KitPvPCommand {
    public TeleportCommand() {
        super(KitPvP.getInstance(), "tp", "kitpvp.command.tp", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        String name = args.get(0);
        Player target = Bukkit.getPlayerExact(name);

        if (args.size() == 0) {
            player.sendMessage(ChatUtils.getFormattedText("teleport.usage"));
            return;

        } else if (args.size() == 1) {
            if (Bukkit.getPlayerExact(name) == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return;
            }

            if (player == target) {
                sender.sendMessage(ChatUtils.getFormattedText("teleport.tp-yourself-error"));
                return;
            }

            player.teleport(target);
            player.sendMessage(ChatUtils.getFormattedText("teleport.success")
                    .replaceAll("%name%", target.getName()));
        }

        if (args.size() == 2) {
            String name2 = args.get(1);
            Player target2 = Bukkit.getPlayerExact(name2);

            if (Bukkit.getPlayerExact(name2) == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return;
            }

            if (target == target2) {
                sender.sendMessage(ChatUtils.getFormattedText("teleport.tp-other-self"));
                return;
            }

            target.teleport(target2);
            player.sendMessage(ChatUtils.getFormattedText("teleport.success-different")
                    .replaceAll("%name%", target.getName())
                    .replaceAll("%target%", target2.getName()));
        }
    }
}
