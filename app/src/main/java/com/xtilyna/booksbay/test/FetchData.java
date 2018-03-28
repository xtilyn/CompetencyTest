package com.xtilyna.booksbay.test;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.xtilyna.booksbay.test.entities.Photo;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FetchData extends AsyncTask<Void, Void, Void>{

    private static final String TAG = "FetchData";

    HashMap<String, String> res = new HashMap<>();
    Photo[] photos;

    String json;

    Context context;

    public FetchData(Context c) {
        Log.d(TAG, "FetchData: ");
        this.context = c;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            InputStream is = context.getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
            Log.d(TAG, "json: " + json);
            JSONObject jo = new JSONObject(json);

            res.put("formatted_address", (String) jo.get("formatted_address"));
            res.put("formatted_phone_number", (String)jo.get("formatted_phone_number"));
            res.put("name", (String) jo.get("name"));
            JSONArray js = (JSONArray) ((JSONObject)jo.get("opening_hours")).get("weekday_text");
            String hours = "";
            for (int i=0; i<js.length(); i++) {
                hours += js.get(i) + ((i==(js.length()-1)) ? "" : ",");
            }
            res.put("opening_hours", hours);

            JSONObject obj;
            JSONArray photosJA =((JSONArray)jo.get("photos"));
            photos = new Photo[photosJA.length()];
            for (int j=0; j<photosJA.length(); j++) {
                obj = (JSONObject)photosJA.get(j);
                photos[j] = new Photo((int)obj.get("photo_id"), (String)obj.get("photo_url"));
            }
            res.put("rating", jo.get("rating").toString());
            res.put("website", (String) jo.get("website"));

        } catch (JSONException e) {
            Log.d(TAG, "doInBackground: " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e2) {
            Log.d(TAG, "doInBackground: " + e2.getMessage());
            e2.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        postEvent(Event.ON_DATA_FETCHED, res, photos);
    }

    private void postEvent(int type, HashMap<String, String> msg, Photo[] photos) {
        Event event = new Event();
        event.setEventType(type);
        event.setMessage(msg);
        event.setPhotos(photos);

        EventBus eventBus = EventBus.getDefault();
        eventBus.post(event);
    }

}
