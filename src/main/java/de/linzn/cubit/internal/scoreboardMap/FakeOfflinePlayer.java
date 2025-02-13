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

package de.linzn.cubit.internal.scoreboardMap;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.profile.PlayerProfile;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class FakeOfflinePlayer implements OfflinePlayer {
    String name;

    public FakeOfflinePlayer(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return null;
    }

    public boolean hasPlayedBefore() {
        return false;
    }

    public String getName() {
        return name;
    }

    public UUID getUniqueId() {
        return null;
    }

    @Override
    public PlayerProfile getPlayerProfile() {
        return null;
    }

    public long getFirstPlayed() {
        return 0;
    }

    public boolean isBanned() {
        return false;
    }

    @Override
    public BanEntry<PlayerProfile> ban(String s, Date date, String s1) {
        return null;
    }

    @Override
    public BanEntry<PlayerProfile> ban(String s, Instant instant, String s1) {
        return null;
    }

    @Override
    public BanEntry<PlayerProfile> ban(String s, Duration duration, String s1) {
        return null;
    }

    @Deprecated
    public void setBanned(boolean b) {
        return;
    }

    public Map<String, Object> serialize() {
        return null;
    }

    public boolean isWhitelisted() {
        return true;
    }

    public void setWhitelisted(boolean b) {
        return;
    }

    public Location getBedSpawnLocation() {
        return null;
    }

    @Override
    public Location getRespawnLocation() {
        return null;
    }

    @Override
    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, int i) throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(Statistic statistic, int i) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(Statistic statistic) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {

    }

    @Override
    public void setStatistic(Statistic statistic, Material material, int i) throws IllegalArgumentException {

    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {

    }

    @Override
    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException {
        return 0;
    }

    @Override
    public void incrementStatistic(Statistic statistic, EntityType entityType, int i) throws IllegalArgumentException {

    }

    @Override
    public void decrementStatistic(Statistic statistic, EntityType entityType, int i) {

    }

    @Override
    public void setStatistic(Statistic statistic, EntityType entityType, int i) {

    }

    @Override
    public Location getLastDeathLocation() {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    public boolean isOnline() {
        return false;
    }

    public long getLastPlayed() {
        return 0;
    }

    public boolean isOp() {
        return false;
    }

    public void setOp(boolean b) {
        return;
    }
}
