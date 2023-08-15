package pl.ug.SateliteApp.SateliteApp.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private int id;
    private String name;
    private String password;

    private String email;
    private float[] coords;
    private ArrayList<Integer> favourites;

    public User() {
        this.favourites = new ArrayList<>();
        this.coords = new float[2];
    }

    public User(String name, String password, String email, float[] coords, ArrayList<Integer> favourites) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.coords = coords;
        this.favourites = favourites;
    }

    public User(int id, String name, String password, String email, float[] coords, ArrayList<Integer> favourites) {
        this.id = id;
//        this.id =
        this.name = name;
        this.password = password;
        this.email = email;
        this.coords = coords;
        this.favourites = favourites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String asString() {
        String output = new String("{\n" + "\"ID\": \"" + id + "\",\n\"NAME\": \"" + name + "\",\n\"PASSWORD\": \"" + password
                + "\",\n\"EMAIL\": \"" + email + "\",\n\"COORDINATES\": [\n\""
                + coords[0] + "\",\n\"" + coords[1] + "\"\n],\"FAVOURITES\": [\n\"");

        for (int i = 0; i < favourites.size() - 1; i++)
            output += favourites.get(i) + "\"" + ",\n\"";
        if (favourites.size() > 0)
            output += favourites.get(favourites.size() - 1) + "\"" + "]\n}";
        else
            output += "\"]" + "\n}";
        return output;

    }

    public float[] getCoords() {
        return coords;
    }

    public void setCoords(float[] coords) {
        this.coords = coords;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Integer> getFavourites() {
        return favourites;
    }

    public void setFavourites(ArrayList<Integer> favourites) {
        for (Integer id : favourites) {
            if (!this.favourites.contains(id))
                this.favourites.add(id);
        }
    }
}
