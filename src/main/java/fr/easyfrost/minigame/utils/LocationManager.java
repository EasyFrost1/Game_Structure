package fr.easyfrost.minigame.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.json.JSONObject;

import java.util.List;

public class LocationManager {

    private enum locEnum {
        world,
        x,
        y,
        z,
        pitch,
        yaw
    }

    /**
     * Transform a location to Json Object
     *
     * @param location
     * @return
     */
    public static JSONObject locationToJson(Location location) {
        if (location == null) return null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(locEnum.world.toString(), location.getWorld().getName());
        jsonObject.put(locEnum.x.toString(), Math.ceil(location.getX()));
        jsonObject.put(locEnum.y.toString(), Math.ceil(location.getY()));
        jsonObject.put(locEnum.z.toString(), Math.ceil(location.getZ()));
        jsonObject.put(locEnum.pitch.toString(), Math.ceil(location.getPitch()));
        jsonObject.put(locEnum.yaw.toString(), Math.ceil(location.getYaw()));

        return jsonObject;
    }

    /**
     * Transform a jsonObject to location
     *
     * @param jsonObject
     * @return
     */
    public static Location jsonToLocation(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.isEmpty()) return null;
        World world = Bukkit.getWorld(jsonObject.getString(locEnum.world.toString()));
        int x = jsonObject.getInt(locEnum.x.toString());
        int y = jsonObject.getInt(locEnum.y.toString());
        int z = jsonObject.getInt(locEnum.z.toString());

        int pitch = jsonObject.getInt(locEnum.pitch.toString());
        int yaw = jsonObject.getInt(locEnum.yaw.toString());

        if (pitch < 0) pitch = 0;
        if (yaw < 0) yaw = 0;

        return new Location(world, x, y, z, pitch, yaw);
    }

    /**
     * Find the location in the list and return it, null if not find
     *
     * @param listLoc
     * @param location
     * @return
     */
    public static Location getLocation(List<Location> listLoc, Location location) {
        for (Location element : listLoc) {
            if (element.distance(location) < 1) return element;
            if (element.distance(location.clone().add(0, -1, 0)) < 1) return element;
            if (element.distance(location.clone().add(0, 1, 0)) < 1) return element;
        }
        return null;
    }

    /**
     * If location is find remove it, if not adding it to list
     *
     * @param listLoc
     * @param location
     * @param used
     * @return true if added, false if remove
     */
    public static boolean editLocationList(List<Location> listLoc, Location location, boolean used) {
        Location loc = LocationManager.getLocation(listLoc, location);
        if (loc == null && !used) {
            listLoc.add(location);
            return true;
        } else {
            listLoc.remove(loc);
            return false;
        }
    }
}
