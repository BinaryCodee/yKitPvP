package me.sonoricky.ykitpvp.tasks;

import me.sonoricky.ykitpvp.KitPvP;
import me.sonoricky.ykitpvp.data.PlayerData;

public class GeneralTask implements Runnable {

    public static final double combatTimer = KitPvP.getFileManager().getConfig().getDouble("combat.timer");
    public static final double enderTimer = KitPvP.getFileManager().getConfig().getDouble("ender-pearl.timer");

    @Override
    public void run() {
        if (KitPvP.getDataManager().getAllData().size() == 0) return;

        for (int i = 0; i < KitPvP.getDataManager().getAllData().size(); i++) {
            PlayerData data = KitPvP.getDataManager().getAllData().get(i);
            if (data != null) {
                long current = System.currentTimeMillis();

                long combatTimestamp = data.endCombatTimestamp;
                long combatDiff = combatTimestamp - current;

                long enderTimestamp = data.endEnderTimestamp;
                long enderDiff = enderTimestamp - current;

                data.inCombat = combatDiff > 0;
                data.inEnderCooldown = enderDiff > 0;

                KitPvP.getDataManager().updateData(data);
            }
        }
    }

}
