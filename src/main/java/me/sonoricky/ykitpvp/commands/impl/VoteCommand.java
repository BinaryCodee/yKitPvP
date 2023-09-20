package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class VoteCommand extends KitPvPCommand {
    public VoteCommand() {
        super(KitPvP.getInstance(), "vote", "kitpvp.command.vote", false);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        sender.sendMessage(ChatUtils.getFormattedText("vote"));
    }
}
