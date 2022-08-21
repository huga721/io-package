package pl.hubert;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Lokacje implements Map<Integer, Location> {
    private static final Map<Integer, Location> locations = new LinkedHashMap<>();

    // Writing the object to the "locations.dat" file and then reading object from the file and putting values to map and so on
    public static void main(String[] args) throws IOException{
        // Creating and instance of a path
        Path locPath = FileSystems.getDefault().getPath("locations.dat");
        // Opening a ObjectOutputStream, and first we create instance of BufferedOutputStream so object will be buffered
        // Then we pass the path
        try (ObjectOutputStream locFile = new ObjectOutputStream(new BufferedOutputStream(Files.newOutputStream(locPath)))){
            // Looping the map value to write every object
            for (Location loc : locations.values()){
                locFile.writeObject(loc); // writing object to "locations.dat"
            }
        } catch (IOException e) {
            System.out.println("IOException in main");
        }

    }
    static {
        // Instance of locations.dat path
        Path objPath = FileSystems.getDefault().getPath("locations.dat");
        // Creating instance of ObjectInputStream, reading object in BufferedInputStream and passing path of the file that contains object
        try (ObjectInputStream objFile = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(objPath)))){
            boolean eof = false;
            while (!eof){
                try {
                    Location location = (Location)objFile.readObject(); // New instance of Location class, read from the file
                    locations.put(location.getLocationID(), location); // Putting object to the map
                } catch (EOFException e){
                    eof = true;
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        // Creating BufferedReader objects to read data from the files
//        try(BufferedReader locFile = new BufferedReader(new FileReader("locations_big.txt"));
//            BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))){
//
//            String locString;
//            String dirString;
//            while ((locString = locFile.readLine()) != null){ // while the line that has been read is not null
//                String[] split = locString.split(","); // Split the line and put it to the array
//                int id = Integer.parseInt(split[0]);
//                String description = split[1];
//                locations.put(id, new Location(id, description, null));
//            }
//            while ((dirString = dirFile.readLine()) != null){
//                String[] split = dirString.split(",");
//                int id = Integer.parseInt(split[0]);
//                String direction = split[1];
//                int destination = Integer.parseInt(split[2]);
//                locations.get(id).addExit(direction, destination);
//            }
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
