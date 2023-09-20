package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.CommandHandler;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.commands.impl.subcommands.*;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

public class MainCommand extends KitPvPCommand {

    public MainCommand() {
        super(KitPvP.getInstance(), "kitpvp", "kitpvp.command.admin", false);

        setNoSubCommandFoundMessage(ChatUtils.getFormattedText("admin.no-sub-command-found"));
        setNoPermissionMessage(ChatUtils.getColoredText("&7Running &bzKitPvP &7version &a1.0 &7by &cSonoRicky &7And &cBinaryCodee&7. "));

        CommandHandler.addSubCommand(this, new AlertCommand());
        CommandHandler.addSubCommand(this, new BuildCommand());
        CommandHandler.addSubCommand(this, new ReloadCommand());
        CommandHandler.addSubCommand(this, new SetBountyCommand());
        CommandHandler.addSubCommand(this, new SetSpawnCommand());
        CommandHandler.addSubCommand(this, new SetStatsCommand());
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if (args.size() != 0) return;

        KitPvP.getFileManager().getMessages().getStringList("admin.help-command")
                .forEach(msg -> sender.sendMessage(ChatUtils.getColoredText(msg)));
    }

}
