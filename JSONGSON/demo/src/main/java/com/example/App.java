package com.example;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {
    public static void main(String[] args) {
        String json1 = "[{\"dorsal\":6, " + "\"name\":\"Iniesta\","
                + "\"demarcation\": [\"Right winger\", \"Midfielder\"],"
                + "\"team\":\"FC Barcelona\"}]";

        JsonParser parser = new JsonParser();

        // Obtain Array
        JsonArray gsonArr = parser.parse(json1).getAsJsonArray();

        // for each element of array
        for (JsonElement obj : gsonArr) {

            // Object of array
            JsonObject gsonObj = obj.getAsJsonObject();

            // Primitives elements of object
            int dorsal = gsonObj.get("dorsal").getAsInt();
            String name = gsonObj.get("name").getAsString();
            String team = gsonObj.get("team").getAsString();

            // List of primitive elements
            JsonArray Demarcations = gsonObj.get("demarcation").getAsJsonArray();
            List demarcations = new ArrayList();
            for (JsonElement demarc : Demarcations) {
                demarcations.add(demarc.getAsString());
            }
            // Object Constructor
            FootballPlayer iniesta = new FootballPlayer(dorsal, name, demarcations, team);
            System.out.println(iniesta);

        }

    }
}
