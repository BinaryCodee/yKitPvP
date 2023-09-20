package me.sonoricky.ykitpvp.commands.impl.teleport;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TeleportAllCommand extends KitPvPCommand {
    public TeleportAllCommand() {
        super(KitPvP.getInstance(), "tpall", "kitpvp.command.tpall", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Location senderLocation = ((Player) sender).getLocation();

        Bukkit.getScheduler().runTaskAsynchronously(KitPvP.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.teleport(senderLocation);
            }
        });
        sender.sendMessage(ChatUtils.getFormattedText("tpall.success"));
    }
}