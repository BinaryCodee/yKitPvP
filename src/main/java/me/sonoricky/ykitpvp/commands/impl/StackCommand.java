package me.sonoricky.ykitpvp.commands.impl;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.commands.api.KitPvPCommand;
import me.sonoricky.ykitpvp.utils.ChatUtils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StackCommand extends KitPvPCommand {
    public StackCommand() {
        super(KitPvP.getInstance(), "stack", "kitpvp.command.stack", true);

        setNoPermissionMessage(ChatUtils.getFormattedText("no-permission"));
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        Player player = (Player) sender;
        Map<ItemStack, Integer> potionMap = new LinkedHashMap<ItemStack, Integer>();
        for (int i = 0; i < player.getInventory().getSize(); ++i) {
            ItemStack item = player.getInventory().getItem(i);
            if (item != null && item.getType() == Material.POTION && !Potion.fromItemStack(item).isSplash() && item.getDurability() != 0) {
                ItemStack contains = null;
                for (ItemStack stack : potionMap.keySet()) {
                    if (stack.getDurability() == item.getDurability() && stack.getItemMeta().equals(item.getItemMeta())) {
                        contains = stack;
                        break;
                    }
                }

                if (contains != null) {
                    potionMap.put(contains, potionMap.get(contains) + item.getAmount());
                } else {
                    potionMap.put(item, item.getAmount());
                }
            }
        }

        if (potionMap.isEmpty()) {
            return;
        }

        ItemStack[] items = player.getInventory().getContents();
        for (int j = 0; j < items.length; ++j) {
            if (items[j] != null && items[j].getType() == Material.POTION && !Potion.fromItemStack(items[j]).isSplash() && items[j].getDurability() != 0) {
                player.getInventory().clear(j);
            }
        }

        for (Map.Entry<ItemStack, Integer> entry : potionMap.entrySet()) {
            ItemStack stack = entry.getKey();
            stack.setAmount((int) entry.getValue());
            player.getInventory().addItem(new ItemStack[]{stack});
        }

        player.updateInventory();
    }
}