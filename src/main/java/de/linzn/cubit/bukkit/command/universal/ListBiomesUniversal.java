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

package de.linzn.cubit.bukkit.command.universal;

import de.linzn.cubit.bukkit.command.ICommand;
import de.linzn.cubit.bukkit.plugin.CubitBukkitPlugin;
import de.linzn.cubit.internal.cubitRegion.CubitType;
import org.bukkit.Registry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.OldEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListBiomesUniversal implements ICommand {

    private CubitBukkitPlugin plugin;
    private String permNode;

    public ListBiomesUniversal(CubitBukkitPlugin plugin, String permNode, CubitType type) {
        this.plugin = plugin;
        this.permNode = permNode;
    }

    @Override
    public boolean runCmd(final Command cmd, final CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            /* This is not possible from the server console */
            sender.sendMessage(plugin.getYamlManager().getLanguage().noConsoleMode);
            return true;
        }

        /* Build and get all variables */
        Player player = (Player) sender;

        /* Permission Check */
        if (!player.hasPermission(this.permNode)) {
            sender.sendMessage(plugin.getYamlManager().getLanguage().errorNoPermission);
            return true;
        }

        List<String> biomeList = Registry.BIOME.stream().filter(biome -> !biome.name().contains("nether") && !biome.name().contains("end")).map(OldEnum::name).collect(Collectors.toList()); //Registry.BIOME.stream().filter(biome -> biome.getKeyOrNull().toString().contains("nether")).map(biome -> biome.getKeyOrNull().getNamespace()).collect(Collectors.toList());

        sender.sendMessage(plugin.getYamlManager().getLanguage().landBiomeListHeader);
        sender.sendMessage(biomeList.toString().replace("[", " ").replace("]", " "));

        return true;
    }

}
