package me.sonoricky.ykitpvp.commands.impl.subcommands;

import me.sonoricky.ykitpvp.commands.api.Subcommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AlertCommand extends Subcommand {

    public AlertCommand() {
        super("kitpvp", "alert", "kitpvp.command.alert", false);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() == 1) {
            sender.sendMessage(ChatUtils.getFormattedText("malformed-command"));
            return;
        }

        String message = String.join("&7[&cAvviso&7]&f ", args.subList(1, args.size()));
        Bukkit.broadcastMessage(ChatUtils.getColoredText(message));
    }
}
