package me.sonoricky.ykitpvp.commands.impl.gamemodes;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;
import org.bukkit.*;

public class AdventureCommand extends KitPvPCommand {

    public AdventureCommand() {
        super(KitPvP.getInstance(), "gma", "kitpvp.command.gma", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        if (args.size() == 0) {
            player.sendMessage(ChatUtils.getFormattedText("gamemodes.adventure"));
            player.setGameMode(GameMode.ADVENTURE);
        }

        else if (args.size() == 1) {
            String name = args.get(0);
            Player target = Bukkit.getPlayerExact(name);

            if (target == null) {
                player.sendMessage(ChatUtils.getFormattedText("player-offline"));
                return;
            }

            if (target == player) {
                player.sendMessage(ChatUtils.getFormattedText("gamemodes.adventure"));
                player.setGameMode(GameMode.ADVENTURE);
                return;
            }

            target.setGameMode(GameMode.ADVENTURE);
            player.sendMessage(ChatUtils.getFormattedText("gamemodes.adventure-for-player")
                    .replaceAll("%name%", target.getName()));

            target.sendMessage(ChatUtils.getFormattedText("gamemodes.adventure-by-staff")
                    .replaceAll("%name%", player.getName()));
        }
    }
}