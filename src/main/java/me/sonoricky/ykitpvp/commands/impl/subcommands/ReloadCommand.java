package me.sonoricky.ykitpvp.commands.impl.subcommands;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.Subcommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ReloadCommand extends Subcommand {

    public ReloadCommand() {
        super("kitpvp", "reload", "kitpvp.command.reload", false);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        KitPvP.getInstance().reloadConfiguration();
        sender.sendMessage(ChatUtils.getFormattedText("admin.plugin-reloaded"));
    }
}