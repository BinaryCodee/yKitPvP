package me.sonoricky.ykitpvp.commands.impl.gamemodes;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;
import org.bukkit.*;

public class SurvivalCommand extends KitPvPCommand {

    public SurvivalCommand() {
        super(KitPvP.getInstance(), "gms", "kitpvp.command.gms", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        if (args.size() == 0) {
            player.sendMessage(ChatUtils.getFormattedText("gamemodes.survival"));
            player.setGameMode(GameMode.SURVIVAL);
        }

        else if (args.size() == 1) {
            String name = args.get(0);
            if (Bukkit.getPlayer(name) == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return;
            }

            if (Bukkit.getPlayer(name) == player) {
                player.sendMessage(ChatUtils.getFormattedText("gamemodes.survival"));
                player.setGameMode(GameMode.SURVIVAL);
                return;
            }

            Player target = Bukkit.getPlayer(name);

            target.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatUtils.getFormattedText("gamemodes.survival-for-player")
                    .replaceAll("%name%", target.getName()));

            target.sendMessage(ChatUtils.getFormattedText("gamemodes.survival-by-staff")
                    .replaceAll("%name%", player.getName()));
        }
    }
}