package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class FreezeCommand extends KitPvPCommand {
    public FreezeCommand() {
        super(KitPvP.getInstance(), "freeze", "kitpvp.command.freeze", false);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        if (args.size() == 0) {
            player.sendMessage(ChatUtils.getFormattedText("freeze.usage"));
            return;
        }
    }
}
