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

package de.linzn.cubit.internal.blockEdit.normal.block;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

public class ChunkBorder implements Runnable {

    private static final Set<Material> illegalBlockSet = getIllegalBlockList();
    private static final Set<Material> allowedOverrideBlockSet = getAllowedOverrideBlocks();

    private Plugin plugin;
    private Chunk chunk;
    private Material borderMaterial;
    private Worker work;

    public ChunkBorder(Plugin plugin, Chunk chunk, Material borderMaterial) {
        this.plugin = plugin;
        this.chunk = chunk;
        this.borderMaterial = borderMaterial;
        work = new Worker();
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, this);
    }
/*
    @Override
    public void run() {
        if (work.isTaskComplete()) {
            return;
        }

        for (int count = 0; count < 4 && !work.isTaskComplete(); work.i++, count++) {
            Location corner_1 = chunk.getBlock(work.i, 0, 0).getLocation();
            corner_1.setY(getValidBlockHeight(chunk.getWorld(), corner_1.getBlockX(), corner_1.getBlockZ()));

            Location corner_2 = chunk.getBlock(15, 0, work.i).getLocation();
            corner_2.setY(getValidBlockHeight(chunk.getWorld(), corner_2.getBlockX(), corner_2.getBlockZ()));

            Location corner_3 = chunk.getBlock((15 - work.i), 0, 15).getLocation();
            corner_3.setY(getValidBlockHeight(chunk.getWorld(), corner_3.getBlockX(), corner_3.getBlockZ()));

            Location corner_4 = chunk.getBlock(0, 0, (15 - work.i)).getLocation();
            corner_4.setY(getValidBlockHeight(chunk.getWorld(), corner_4.getBlockX(), corner_4.getBlockZ()));

            setBlockSync(torchMaterial, corner_1, corner_2, corner_3, corner_4);
        }
        if (!work.isTaskComplete()) {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, this);
        }
    }


    public int getValidBlockHeight(World world, int x, int z) {
        int y;
        for (y = world.getMaxHeight(); y > world.getMinHeight(); y--) {
            if ((world.getBlockAt(x, y, z).getType() != Material.AIR)
                    && (world.getBlockAt(x, y, z).getType() != Material.END_PORTAL_FRAME)
                    && (world.getBlockAt(x, y, z).getType() != Material.BEDROCK)) {
                if ((world.getBlockAt(x, y, z).getType() != Material.TORCH)
                        && (world.getBlockAt(x, y, z).getType() != Material.REDSTONE_TORCH)
                        && (world.getBlockAt(x, y, z).getType() != Material.DEAD_BUSH)
                        && (world.getBlockAt(x, y, z).getType() != Material.SNOW)) {
                    break;
                }
                y--;
                break;
            }
        }
        return y + 1;
    } */


    @Override
    public void run() {
        while (!work.isTaskComplete()) {
            for (int count = 0; count < 4 && !work.isTaskComplete(); count++) {
                Location corner_1 = getValidBlockLocation(chunk.getWorld(), chunk.getBlock(work.i, 0, 0).getLocation());
                Location corner_2 = getValidBlockLocation(chunk.getWorld(), chunk.getBlock(15, 0, work.i).getLocation());
                Location corner_3 = getValidBlockLocation(chunk.getWorld(), chunk.getBlock((15 - work.i), 0, 15).getLocation());
                Location corner_4 = getValidBlockLocation(chunk.getWorld(), chunk.getBlock(0, 0, (15 - work.i)).getLocation());

                updateBlock(borderMaterial, corner_1, corner_2, corner_3, corner_4);
                work.i++;
            }
        }
    }


    public Location getValidBlockLocation(World world, Location chunkLocation) {
        int x = chunkLocation.getBlockX();
        int z = chunkLocation.getBlockZ();
        Location validLocation = null;
        Block block = world.getHighestBlockAt(x, z, HeightMap.WORLD_SURFACE);
        /* Check if its ok to set a block on top for offset block */
        if (!illegalBlockSet.contains(block.getType())) {
            /* Check if the current block is allowed to override */
            if (allowedOverrideBlockSet.contains(block.getType())) {
                validLocation = block.getLocation();
            } else {
                Location location = new Location(world, x, block.getLocation().getY(), z);
                Block offsetBlock = world.getBlockAt(location.getBlockX(), location.getBlockY() + 1, location.getBlockZ());
                /* Check if the block in offset is allowed to override*/
                if (allowedOverrideBlockSet.contains(offsetBlock.getType())) {
                    validLocation = offsetBlock.getLocation();
                }
            }
        }
        return validLocation;
    }


    private void updateBlock(final Material material, final Location corner_1, final Location corner_2,
                             final Location corner_3, final Location corner_4) {
        plugin.getServer().getScheduler().runTask(plugin, () -> {
            if (corner_1 != null) {
                chunk.getWorld().getBlockAt(corner_1).setType(material);
            }
            if (corner_2 != null) {
                chunk.getWorld().getBlockAt(corner_2).setType(material);
            }
            if (corner_3 != null) {
                chunk.getWorld().getBlockAt(corner_3).setType(material);
            }
            if (corner_4 != null) {
                chunk.getWorld().getBlockAt(corner_4).setType(material);
            }
        });
    }

    public static class Worker {
        public int i = 0;

        public boolean isTaskComplete() {
            return i == 15;
        }
    }


    public static Set<Material> getAllowedOverrideBlocks() {
        Set<Material> set = new HashSet<>();
        set.add(Material.AIR);
        set.add(Material.TORCH);
        set.add(Material.WALL_TORCH);
        set.add(Material.REDSTONE_TORCH);
        set.add(Material.REDSTONE_WALL_TORCH);
        return set;
    }

    public static Set<Material> getIllegalBlockList() {
        Set<Material> set = new HashSet<>();
        set.add(Material.END_PORTAL_FRAME);
        set.add(Material.BEDROCK);
        set.add(Material.SOUL_TORCH);
        set.add(Material.SOUL_WALL_TORCH);
        set.add(Material.DEAD_BUSH);
        set.add(Material.SNOW);
        set.add(Material.WATER);
        set.add(Material.LAVA);
        set.add(Material.ACACIA_LEAVES);
        set.add(Material.AZALEA_LEAVES);
        set.add(Material.BIRCH_LEAVES);
        set.add(Material.DARK_OAK_LEAVES);
        set.add(Material.FLOWERING_AZALEA_LEAVES);
        set.add(Material.JUNGLE_LEAVES);
        set.add(Material.OAK_LEAVES);
        set.add(Material.SPRUCE_LEAVES);
        set.add(Material.CAKE);
        set.add(Material.SHORT_GRASS);
        set.add(Material.TALL_GRASS);
        set.add(Material.CAMPFIRE);
        set.add(Material.CANDLE);
        set.add(Material.DANDELION);
        set.add(Material.POPPY);
        set.add(Material.BLUE_ORCHID);
        set.add(Material.ALLIUM);
        set.add(Material.AZURE_BLUET);
        set.add(Material.RED_TULIP);
        set.add(Material.ORANGE_TULIP);
        set.add(Material.WHITE_TULIP);
        set.add(Material.PINK_TULIP);
        set.add(Material.OXEYE_DAISY);
        set.add(Material.CORNFLOWER);
        set.add(Material.LILY_OF_THE_VALLEY);
        set.add(Material.WITHER_ROSE);
        set.add(Material.SUNFLOWER);
        set.add(Material.LILAC);
        set.add(Material.ROSE_BUSH);
        set.add(Material.PEONY);
        return set;
    }
}
