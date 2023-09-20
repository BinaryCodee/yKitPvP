package me.sonoricky.ykitpvp.listeners;

import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

public class ObsidianBreaker implements Listener
{

    private final JavaPlugin plugin;
    private final int cooldown;
    private final Material requiredTool;

    public ObsidianBreaker(JavaPlugin plugin, int cooldown, Material requiredTool) {
        this.plugin = plugin;
        this.cooldown = cooldown;
        this.requiredTool = requiredTool;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (event.getClickedBlock().getType() != Material.OBSIDIAN) {
            return;
        }

        Player player = event.getPlayer();
        ItemStack tool = player.getInventory().getItemInHand();

        if (tool.getType() != requiredTool) {
            return;
        }

        if (player.hasMetadata("obsidian_break_cooldown")) {
            long lastBreakTime = player.getMetadata("obsidian_break_cooldown").get(0).asLong();
            long currentTime = System.currentTimeMillis();
            long remainingCooldown = lastBreakTime + cooldown * 1000 - currentTime;
            if (remainingCooldown > 0) {
                player.sendMessage(ChatUtils.getFormattedText("obsidianbreaker.time")
                                .replaceAll("%time%", String.valueOf((remainingCooldown / 1000))));
                event.setCancelled(true);
                return;
            }
        }

        event.setCancelled(true);
        event.getClickedBlock().breakNaturally(tool);
        player.sendMessage(ChatUtils.getFormattedText("obsidianbreaker.success"));
        player.setMetadata("obsidian_break_cooldown", new FixedMetadataValue(plugin, System.currentTimeMillis()));
    }
}