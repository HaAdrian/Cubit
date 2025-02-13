/*
 * Copyright (C) 2021. Kekshaus - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 * You should have received a copy of the LGPLv3 license with
 * this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.cubit.internal.particle;

import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.particle.border.ParticleSender;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;


public class ParticleManager {

    private CubitBukkitPlugin plugin;

    public ParticleManager(CubitBukkitPlugin plugin) {
        plugin.getLogger().info("[Setup] ParticleManager");
        plugin.getLogger().info("Using BukkitAPI as provider");
        this.plugin = plugin;
    }

    public boolean sendBuy(final Player player, final Location loc) {
        if (CubitBukkitPlugin.inst().getYamlManager().getSettings().particleUse) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> new ParticleSender(player, loc, Particle.HAPPY_VILLAGER, Particle.FIREWORK));
        }
        return true;
    }

    public boolean sendSell(final Player player, final Location loc) {
        if (CubitBukkitPlugin.inst().getYamlManager().getSettings().particleUse) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> new ParticleSender(player, loc, Particle.SOUL_FIRE_FLAME, Particle.FIREWORK));
        }
        return true;
    }

    public boolean sendInfo(final Player player, final Location loc) {
        if (CubitBukkitPlugin.inst().getYamlManager().getSettings().particleUse) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> new ParticleSender(player, loc, Particle.REVERSE_PORTAL, Particle.FIREWORK));
        }
        return true;
    }

    public boolean changeFlag(final Player player, final Location loc) {
        if (CubitBukkitPlugin.inst().getYamlManager().getSettings().particleUse) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> new ParticleSender(player, loc, Particle.FLAME, Particle.FIREWORK));
        }
        return true;
    }

    public boolean addMember(final Player player, final Location loc) {
        if (CubitBukkitPlugin.inst().getYamlManager().getSettings().particleUse) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> new ParticleSender(player, loc, Particle.HEART, Particle.FIREWORK));
        }
        return true;
    }

    public boolean removeMember(final Player player, final Location loc) {
        if (CubitBukkitPlugin.inst().getYamlManager().getSettings().particleUse) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> new ParticleSender(player, loc, Particle.ANGRY_VILLAGER, Particle.FIREWORK));
        }
        return true;
    }

    public boolean changeBiome(final Player player, final Location loc) {
        if (CubitBukkitPlugin.inst().getYamlManager().getSettings().particleUse) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> new ParticleSender(player, loc, Particle.PORTAL, Particle.FIREWORK));

        }
        return true;
    }

}
