package com.crypticelement.bazaarcraft.common.util;

import net.minecraft.world.entity.player.Player;

public class PlayerXpWrapper implements IXpHandler {
    private final Player player;

    public PlayerXpWrapper(Player player) {
        this.player = player;
    }

    @Override
    public int getPoints() {
        return player.totalExperience;
    }

    @Override
    public int getLevels() {
        return player.experienceLevel;
    }

    @Override
    public void givePoints(int points) {
        player.giveExperiencePoints(points);
    }

    @Override
    public void giveLevels(int levels) {
        player.giveExperienceLevels(levels);
    }

    @Override
    public void takePoints(int points) {
        player.giveExperiencePoints(-points);
    }

    @Override
    public void takeLevels(int levels) {
        player.giveExperienceLevels(-levels);
    }

    @Override
    public boolean canGivePoints(int points) {
        return true;
    }

    @Override
    public boolean canGiveLevels(int levels) {
        return true;
    }

    @Override
    public boolean canTakePoints(int points) {
        return player.totalExperience >= points;
    }

    @Override
    public boolean canTakeLevels(int levels) {
        return player.experienceLevel >= levels;
    }
}
