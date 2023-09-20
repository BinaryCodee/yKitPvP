package me.sonoricky.ykitpvp.commands.impl.subcommands;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.Subcommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.List;

public class SetSpawnCommand extends Subcommand {

    public SetSpawnCommand() {
        super("kitpvp", "setspawn", "kitpvp.command.setspawn", true);
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        Location location = player.getLocation();
        FileConfiguration config = KitPvP.getFileManager().getConfig();

        File file = new File(KitPvP.getInstance().getDataFolder(), "config.yml");

        config.set("spawn-location", location);
        KitPvP.getFileManager().saveFile(config, file);

        sender.sendMessage(ChatUtils.getFormattedText("admin.new-spawn-set"));
    }
}
