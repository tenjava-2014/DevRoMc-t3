package com.tenjava.entries.DevRoMc.t3.utils;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class EventUtils {

    public static Location getRandomLocation(World world) {
        double x = world.getSpawnLocation().getX() + MathUtil.r(100) * 2 - 100;
        double y = world.getSpawnLocation().getY() + MathUtil.r(100) * 2 - 100;
        double z = world.getSpawnLocation().getZ() + MathUtil.r(100) * 2 - 100;

        Location location = new Location(world, x, y,z);

        location.setY(world.getHighestBlockYAt(location));

        return location;
    }

    /**
     *
     * Made by sk89q, edited by bobacadodl
     */
    public static void createSphere(Location loc, Material type, double radius) {
        for (Location location : getSphere(loc, radius, radius, radius, true)) {
            location.getBlock().setType(type);
        }

        for (Location location : getSphere(loc, MathUtil.r(radius), MathUtil.r(radius), MathUtil.r(radius), true)) {
            location.getBlock().setType(Material.LAVA);
        }
    }

    public static void createSpace(Location location) {
        Location end = location.add(MathUtil.r(75), 0, MathUtil.r(75));
        for (Location l : drawLine(location, end, MathUtil.r(6), false)) {
            int i = l.getWorld().getHighestBlockYAt(l);
            for (int y = i; y > 0; y --) {
                l.subtract(0, 1, 0).getBlock().setType(Material.AIR);
            }
        }
    }

    public static void makeWildfire(Location location, double radius) {
        for (Location l : getSphere(location, radius, radius, radius, false)) {
            l.getBlock().getRelative(BlockFace.UP).setType(Material.FIRE);
        }
    }

    private static void setBlock(World world, Vector vector, Material m, int x, int y, int z){
        Vector ve = vector.clone().add(new Vector(x, y, z));
        new Location(world, vector.getX(), ve.getY(), ve.getZ()).getBlock().setType(m);
    }

    private static final double lengthSq(double x, double y, double z) {
        return (x * x) + (y * y) + (z * z);
    }

    private static final double lengthSq(double x, double z) {
        return (x * x) + (z * z);
    }

    private static List<Location> drawLine(Location start, Location end, double width, boolean filled){
        Vector pos1 = start.toVector();
        Vector pos2 = end.toVector();
        if(!start.getWorld().equals(end.getWorld())) return new ArrayList<Location>();
        World world = start.getWorld();
        Set<Location> vset = new HashSet<Location>();

        boolean notdrawn = true;

        int x1 = pos1.getBlockX(), y1 = pos1.getBlockY(), z1 = pos1.getBlockZ();
        int x2 = pos2.getBlockX(), y2 = pos2.getBlockY(), z2 = pos2.getBlockZ();
        int tipx = x1, tipy = y1, tipz = z1;
        int dx = Math.abs(x2 - x1), dy = Math.abs(y2 - y1), dz = Math.abs(z2 - z1);

        if (dx + dy + dz == 0) {
            vset.add(new Location(world,tipx, tipy, tipz));
            notdrawn = false;
        }

        if (Math.max(Math.max(dx, dy), dz) == dx && notdrawn) {
            for (int domstep = 0; domstep <= dx; domstep++) {
                tipx = x1 + domstep * (x2 - x1 > 0 ? 1 : -1);
                tipy = (int) Math.round(y1 + domstep * ((double) dy) / ((double) dx) * (y2 - y1 > 0 ? 1 : -1));
                tipz = (int) Math.round(z1 + domstep * ((double) dz) / ((double) dx) * (z2 - z1 > 0 ? 1 : -1));

                vset.add(new Location(world,tipx, tipy, tipz));
            }
            notdrawn = false;
        }

        if (Math.max(Math.max(dx, dy), dz) == dy && notdrawn) {
            for (int domstep = 0; domstep <= dy; domstep++) {
                tipy = y1 + domstep * (y2 - y1 > 0 ? 1 : -1);
                tipx = (int) Math.round(x1 + domstep * ((double) dx) / ((double) dy) * (x2 - x1 > 0 ? 1 : -1));
                tipz = (int) Math.round(z1 + domstep * ((double) dz) / ((double) dy) * (z2 - z1 > 0 ? 1 : -1));

                vset.add(new Location(world,tipx, tipy, tipz));
            }
            notdrawn = false;
        }

        if (Math.max(Math.max(dx, dy), dz) == dz && notdrawn) {
            for (int domstep = 0; domstep <= dz; domstep++) {
                tipz = z1 + domstep * (z2 - z1 > 0 ? 1 : -1);
                tipy = (int) Math.round(y1 + domstep * ((double) dy) / ((double) dz) * (y2-y1>0 ? 1 : -1));
                tipx = (int) Math.round(x1 + domstep * ((double) dx) / ((double) dz) * (x2-x1>0 ? 1 : -1));

                vset.add(new Location(world,tipx, tipy, tipz));
            }
            notdrawn = false;
        }

        vset = getBallooned(vset, width);
        if (!filled) {
            vset = getHollowed(vset);
        }
        return new ArrayList<Location>(vset);
    }

    public static List<Location> getCylinder(Location center, double radiusX, double radiusZ, int height, boolean filled) {
        Vector pos = center.toVector();
        World world = center.getWorld();
        List<Location> blocks = new ArrayList<Location>();
        radiusX += 0.5;
        radiusZ += 0.5;

        if (height == 0) {
            return blocks;
        } else if (height < 0) {
            height = -height;
            pos = pos.subtract(new Vector(0, height, 0));
        }

        if (pos.getBlockY() < 0) {
            pos = pos.setY(0);
        } else if (pos.getBlockY() + height - 1 > world.getMaxHeight()) {
            height = world.getMaxHeight() - pos.getBlockY() + 1;
        }

        final double invRadiusX = 1 / radiusX;
        final double invRadiusZ = 1 / radiusZ;

        final int ceilRadiusX = (int) Math.ceil(radiusX);
        final int ceilRadiusZ = (int) Math.ceil(radiusZ);

        double nextXn = 0;
        forX: for (int x = 0; x <= ceilRadiusX; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextZn = 0;
            forZ: for (int z = 0; z <= ceilRadiusZ; ++z) {
                final double zn = nextZn;
                nextZn = (z + 1) * invRadiusZ;

                double distanceSq = lengthSq(xn, zn);
                if (distanceSq > 1) {
                    if (z == 0) {
                        break forX;
                    }
                    break forZ;
                }

                if (!filled) {
                    if (lengthSq(nextXn, zn) <= 1 && lengthSq(xn, nextZn) <= 1) {
                        continue;
                    }
                }

                for (int y = 0; y < height; ++y) {

                    blocks.add(pos.add(new Vector(x,y,z)).toLocation(world));
                    blocks.add(pos.add(new Vector(-x, y, z)).toLocation(world));
                    blocks.add(pos.add(new Vector(x,y,-z)).toLocation(world));
                    blocks.add(pos.add(new Vector(-x,y,-z)).toLocation(world));
                }
            }
        }

        return blocks;
    }

    public static List<Location> getSphere(Location center, double radiusX, double radiusY, double radiusZ, boolean filled){
        Vector pos = center.toVector();
        World world = center.getWorld();
        List<Location> blocks = new ArrayList<Location>();

        radiusX += 0.5;
        radiusY += 0.5;
        radiusZ += 0.5;

        final double invRadiusX = 1 / radiusX;
        final double invRadiusY = 1 / radiusY;
        final double invRadiusZ = 1 / radiusZ;

        final int ceilRadiusX = (int) Math.ceil(radiusX);
        final int ceilRadiusY = (int) Math.ceil(radiusY);
        final int ceilRadiusZ = (int) Math.ceil(radiusZ);

        double nextXn = 0;
        forX: for (int x = 0; x <= ceilRadiusX; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0;
            forY: for (int y = 0; y <= ceilRadiusY; ++y) {
                final double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0;
                forZ: for (int z = 0; z <= ceilRadiusZ; ++z) {
                    final double zn = nextZn;
                    nextZn = (z + 1) * invRadiusZ;

                    double distanceSq = lengthSq(xn, yn, zn);
                    if (distanceSq > 1) {
                        if (z == 0) {
                            if (y == 0) {
                                break forX;
                            }
                            break forY;
                        }
                        break forZ;
                    }
                    if (!filled) {
                        if (lengthSq(nextXn, yn, zn) <= 1 && lengthSq(xn, nextYn, zn) <= 1 && lengthSq(xn, yn, nextZn) <= 1) {
                            continue;
                        }
                    }
                    blocks.add(pos.add(new Vector(x,y,z)).toLocation(world));
                    blocks.add(pos.add(new Vector(-x,y,z)).toLocation(world));
                    blocks.add(pos.add(new Vector(x,-y,z)).toLocation(world));
                    blocks.add(pos.add(new Vector(x,y,-z)).toLocation(world));
                    blocks.add(pos.add(new Vector(-x,-y,z)).toLocation(world));
                    blocks.add(pos.add(new Vector(x,-y,-z)).toLocation(world));
                    blocks.add(pos.add(new Vector(-x,y,-z)).toLocation(world));
                    blocks.add(pos.add(new Vector(-x,-y,-z)).toLocation(world));
                }
            }
        }

        return blocks;
    }

    private static Set<Location> getBallooned(Set<Location> vset, double radius) {
        Set<Location> returnset = new HashSet<Location>();
        int ceilrad = (int) Math.ceil(radius);

        for (Location v : vset) {
            int tipx = v.getBlockX(), tipy = v.getBlockY(), tipz = v.getBlockZ();

            for (int loopx = tipx - ceilrad; loopx <= tipx + ceilrad; loopx++) {
                for (int loopy = tipy - ceilrad; loopy <= tipy + ceilrad; loopy++) {
                    for (int loopz = tipz - ceilrad; loopz <= tipz + ceilrad; loopz++) {
                        if (hypot(loopx - tipx, loopy - tipy, loopz - tipz) <= radius) {
                            returnset.add(new Location(v.getWorld(),loopx, loopy, loopz));
                        }
                    }
                }
            }
        }
        return returnset;
    }

    private static Set<Location> getHollowed(Set<Location> vset) {
        Set<Location> returnset = new HashSet<Location>();
        for (Location v : vset) {
            double x = v.getX(), y = v.getY(), z = v.getZ();
            if (!(vset.contains(new Location(v.getWorld(),x + 1, y, z)) &&
                    vset.contains(new Location(v.getWorld(),x - 1, y, z)) &&
                    vset.contains(new Location(v.getWorld(),x, y + 1, z)) &&
                    vset.contains(new Location(v.getWorld(),x, y - 1, z)) &&
                    vset.contains(new Location(v.getWorld(),x, y, z + 1)) &&
                    vset.contains(new Location(v.getWorld(),x, y, z - 1)))) {
                returnset.add(v);
            }
        }
        return returnset;
    }

    private static double hypot(double... pars) {
        double sum = 0;
        for (double d : pars) {
            sum += Math.pow(d, 2);
        }
        return Math.sqrt(sum);
    }
}
