package com.crypticelement.bazaarcraft.common.util;

public interface IXpHandler {
    int getPoints();

    int getLevels();

    void givePoints(int points);

    void giveLevels(int levels);

    void takePoints(int points);

    void takeLevels(int levels);

    boolean canGivePoints(int points);

    boolean canGiveLevels(int levels);

    boolean canTakePoints(int points);

    boolean canTakeLevels(int levels);
}
