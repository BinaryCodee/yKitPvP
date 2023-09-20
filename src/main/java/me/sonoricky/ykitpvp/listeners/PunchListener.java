package me.sonoricky.ykitpvp.listeners;

import me.sonoricky.ykitpvp.KitPvP;
import org.bukkit.plugin.*;
import org.bukkit.enchantments.*;
import org.bukkit.entity.*;
import org.bukkit.projectiles.*;
import org.bukkit.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.event.entity.*;
import org.bukkit.util.*;
import java.util.HashMap;
import java.util.Map;

public class PunchListener implements Listener
{
    Map<Arrow, Vector> arrows;

    public PunchListener() {
        this.arrows = new HashMap<Arrow, Vector>();
        Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)KitPvP.getInstance(), () -> this.arrows.entrySet().removeIf(set -> !set.getKey().isValid()), 10L, 0L);
    }

    @EventHandler
    public void onArrowShoot(final EntityShootBowEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Entity proj = event.getProjectile();
        if (!(proj instanceof Arrow)) {
            return;
        }
        final Arrow arrow = (Arrow)proj;
        final ProjectileSource shooter = arrow.getShooter();
        if (!(shooter instanceof Player)) {
            return;
        }
        final Player player = (Player)shooter;
        final double boost = arrow.getVelocity().length();
        final int level = player.getItemInHand().getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK);
        final Location location = player.getLocation();
        final Vector direction = location.getDirection();
        boolean straight = KitPvP.getFileManager().getConfig().getBoolean("punch.straight");
        if (KitPvP.getFileManager().getConfig().contains("punch.levels." + level)) {
            straight = KitPvP.getFileManager().getConfig().getBoolean("punch.levels." + level + ".straight");
        }
        if (!straight) {
            return;
        }
        arrow.setVelocity(direction.multiply(boost));
        this.arrows.put(arrow, this.getFixedDirection(location, level));
    }

    @EventHandler
    public void onPlayerVelocity(final PlayerVelocityEvent event) {
        if (event.isCancelled()) {
            return;
        }
        final Player player = event.getPlayer();
        final EntityDamageEvent cause = player.getLastDamageCause();
        if (cause == null || cause.isCancelled() || !(cause instanceof EntityDamageByEntityEvent)) {
            return;
        }
        final EntityDamageByEntityEvent event2 = (EntityDamageByEntityEvent)cause;
        final Entity damager = event2.getDamager();
        if (!(damager instanceof Arrow)) {
            return;
        }
        final Arrow arrow = (Arrow)damager;
        if (!this.arrows.containsKey(arrow)) {
            return;
        }
        final ProjectileSource source = arrow.getShooter();
        if (source != player) {
            return;
        }
        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin) KitPvP.getInstance(), () -> player.setNoDamageTicks(0), 2L);
        event.setVelocity((Vector)this.arrows.get(arrow));
    }

    public Vector getFixedDirection(final Location location, final int level) {
        final double rotX = Math.toRadians(location.getYaw());
        final Vector vector = new Vector(-Math.sin(rotX), 0.0, Math.cos(rotX));
        double boost = KitPvP.getFileManager().getConfig().getDouble("punch.horizontal");
        double vertical = KitPvP.getFileManager().getConfig().getDouble("punch.vertical");
        if (KitPvP.getFileManager().getConfig().contains("punch.levels." + level)) {
            boost = KitPvP.getFileManager().getConfig().getDouble("punch.levels." + level + ".horizontal");
            vertical = KitPvP.getFileManager().getConfig().getDouble("punch.levels." + level + ".vertical");
        }
        final double factor = Math.max(1, level) * boost;
        return vector.multiply(factor).setY(vertical);
    }
}
