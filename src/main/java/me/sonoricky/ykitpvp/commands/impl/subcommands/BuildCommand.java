package me.sonoricky.ykitpvp.commands.impl.subcommands;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.Subcommand;
import me.sonoricky.ykitpvp.data.PlayerData;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BuildCommand extends Subcommand {

    public BuildCommand() {
        super("kitpvp", "build", "kitpvp.command.build", true);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId());
        data.isBuilder = !data.isBuilder;

        sender.sendMessage(ChatUtils.getFormattedText("anti-build.build-mode-" + (data.isBuilder ? "on" : "off")));

        KitPvP.getDataManager().updateData(data);
    }
}
