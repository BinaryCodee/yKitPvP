package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class WithdrawCommand extends KitPvPCommand implements Listener {

    public WithdrawCommand() {
        super(KitPvP.getInstance(), "withdraw", "kitpvp.command.withdraw", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;

        if (args.size() != 1) {
            player.sendMessage(ChatUtils.getFormattedText("withdraw.usage"));
            return;
        }

        int amount;

        try {
            amount = Integer.parseInt(args.get(0));
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatUtils.getFormattedText("withdraw.invalid-number"));
            return;
        }

        if (amount <= 0) {
            sender.sendMessage(ChatUtils.getFormattedText("withdraw.invalid-number"));
            return;
        }

        if (!KitPvP.getEconomy().has(player, amount)) {
            sender.sendMessage(ChatUtils.getFormattedText("withdraw.not-enought-money"));
            return;
        }

        KitPvP.getEconomy().withdrawPlayer(player, amount);

        ItemStack paper = createValuePaper(amount);

        player.getInventory().addItem(paper);
        player.sendMessage(ChatUtils.getFormattedText("withdraw.success")
                .replaceAll("%value%", String.valueOf(amount)));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.PAPER) {
            ItemMeta meta = item.getItemMeta();

            if (meta != null && meta.hasDisplayName()) {
                String displayName = meta.getDisplayName();

                if (displayName.startsWith(ChatUtils.getFormattedText("withdraw.title-startwith"))) {
                    int value = Integer.parseInt(displayName.replace(ChatUtils.getFormattedText("withdraw.title-startwith"), ""));
                    KitPvP.getEconomy().depositPlayer(player, value);
                    player.getInventory().remove(item);
                    player.sendMessage(ChatUtils.getFormattedText("withdraw.claimed")
                            .replaceAll("%value%", String.valueOf(value)));
                }
            }
        }
    }


    private ItemStack createValuePaper(int value) {
        ItemStack paper = new ItemStack(Material.PAPER);
        ItemMeta meta = paper.getItemMeta();

        meta.setDisplayName(ChatUtils.getFormattedText("withdraw.title")
                        .replaceAll("%value%", String.valueOf(value)));
        paper.setItemMeta(meta);

        return paper;
    }
}