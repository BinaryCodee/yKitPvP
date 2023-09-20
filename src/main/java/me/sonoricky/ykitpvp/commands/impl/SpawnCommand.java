package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.data.PlayerData;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SpawnCommand extends KitPvPCommand {
    public SpawnCommand() {
        super(KitPvP.getInstance(), "spawn", "kitpvp.command.spawn", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        Location spawn = (Location) KitPvP.getFileManager().getConfig().get("spawn-location");

        if (spawn == null) {
            player.sendMessage(ChatUtils.getFormattedText("spawn.no-spawn-found"));
            return;
        }

        PlayerData data = KitPvP.getDataManager().getPlayerData(player.getUniqueId());
        data.atSpawn = true;

        KitPvP.getDataManager().updateData(data);
        player.teleport(spawn);
    }
}
