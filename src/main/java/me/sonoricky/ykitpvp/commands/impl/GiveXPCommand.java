package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GiveXPCommand extends KitPvPCommand {

    public GiveXPCommand() {
        super(KitPvP.getInstance(), "givexp", "kitpvp.command.givexp", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        if (args.size() != 2) {
            player.sendMessage(ChatUtils.getFormattedText("givexp.usage"));
            return;
        }

        String name = args.get(0);
        if (Bukkit.getPlayer(name) == null) {
            player.sendMessage(ChatUtils.getFormattedText("player-offline"));
            return;
        }

        Player target = Bukkit.getPlayer(name);
        if (!NumberUtils.isNumber(args.get(1))) {
            player.sendMessage(ChatUtils.getFormattedText("givexp.usage"));
            return;
        }

        int levels = player.getLevel();
        int amount = Integer.parseInt(args.get(1));

        if (levels < amount) {
            player.sendMessage(ChatUtils.getFormattedText("givexp.not-enough-levels"));
            return;
        }

        player.setLevel(levels - amount);
        target.setLevel(target.getLevel() + amount);

        player.sendMessage(ChatUtils.getFormattedText("givexp.sent")
                .replaceAll("%levels%", String.valueOf(amount))
                .replaceAll("%name%", target.getName()));
    }

}
