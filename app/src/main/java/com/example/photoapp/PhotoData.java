package com.example.photoapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PhotoData {
    //public static ArrayList<Photo> generatePhotoData()
    //{
    //    ArrayList<Photo> photos = new ArrayList<>();
    //    photos.add(new Photo(0,"https://images.unsplash.com/photo-1522383225653-ed111181a951?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=876&q=80","Cherry Blossom","A cherry blossom, also known as Japanese cherry or sakura, is a flower of many trees of genus Prunus or Prunus subg. Cerasus. They are common species in East Asia, including China, Korea and especially in Japan. They generally refer to ornamental cherry trees, not to be confused with cherry trees that produce fruit for eating. It is considered the national flower of Japan."));
    //    photos.add(new Photo(1,"https://unsplash.com/fr/photos/PflhlrORvx0", "Daisy", ""));
    //    photos.add(new Photo(2,"https://unsplash.com/fr/photos/90srujjpqP4", "Rose", ""));
    //    photos.add(new Photo(3,"https://images.unsplash.com/photo-1518701005037-d53b1f67bb1c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8dHVsaXB8ZW58MHx8MHx8&auto=format&fit=crop&w=800&q=60", "Tulip", "" ));
    //    photos.add(new Photo(4,"https://images.unsplash.com/photo-1599369553272-29f89a9d4696?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8c3VuJTIwZmxvd2VyfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=800&q=60", "Sun Flower",""));

    //    return photos;
    //}

    //public static Photo getPhotoFromId(int id)
    //{
    //    ArrayList<Photo> phs = generatePhotoData();
    //    for (int i = 0; i < phs.size(); i++)
    //        if (phs.get(i).getId() == id)
    //            return phs.get(i);
    //    return  null;
    //}

    private static Context context;

    public static void init(Context context) {
        PhotoData.context = context;
    }

    public static ArrayList<Photo> getPhotos() {
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            String jsonString = loadJSONFromAsset("photo.json");
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt("id");
                String source = jsonObject.getString("source_photo");
                String title = jsonObject.getString("title_photo");
                String description = jsonObject.getString("description_photo");
                photos.add(new Photo(id, source, title, description));
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photos;
    }

    private static String loadJSONFromAsset(String fileName) throws IOException {
        String jsonString;
        InputStream inputStream = context.getAssets().open(fileName);
        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();
        jsonString = new String(buffer, "UTF-8");
        return jsonString;
    }
    public static Photo getPhotoFromId(int id) {
        Photo photo = null;

        try {
            String jsonString = loadJSONFromAsset("photo.json"); // Assuming the JSON file is named photos.json and is located in the assets folder
            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int photoId = jsonObject.getInt("id");

                if (photoId == id) {
                    String source = jsonObject.getString("source_photo");
                    String title = jsonObject.getString("title_photo");
                    String description = jsonObject.getString("description_photo");
                    photo = new Photo(id, source, title, description);
                    break;
                }
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return photo;
    }
}
