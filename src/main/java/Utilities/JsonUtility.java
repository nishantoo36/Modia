package Utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class JsonUtility {
    static JSONParser parser = new JSONParser();
    public static final String defaultPath =System.getProperty("user.dir");
    public static final String commonConfigFilePath = defaultPath + "/src/main/resources/DefaultData.json";

    public static void writeJson(JSONObject jsonObject, String fileName) {
            try (FileWriter file = new FileWriter(fileName)) {
                file.write(jsonObject.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public static void updateJson(JSONObject jsonObject, String fileName,String jsonPathToUpdate) {
        try  (FileReader reader = new FileReader(fileName)) {
            JSONParser jsonParser = new JSONParser();
            JSONObject data;
            JSONObject path;
            JSONObject finalData = new JSONObject();
            data = (JSONObject) jsonParser.parse(reader);
            path= getJsonObjectUSingJsonPath(jsonPathToUpdate,fileName);
            for (Object map :jsonObject.keySet()){
                path.put(map,jsonObject.get(map));
            }
            JSONObject val = (JSONObject) data.get(0);
            for (Object d: path.keySet()){
                val.putIfAbsent(d,path.get(d));
            }
            writeJson(finalData,fileName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    public static void appendJson(JSONObject jsonObject, String fileName){
        try  (FileReader reader = new FileReader(fileName)) {
            JSONParser jsonParser = new JSONParser();
            JSONObject data;
            data = (JSONObject) jsonParser.parse(reader);
            for (Object map :jsonObject.keySet()){
                data.put(map,jsonObject.get(map));
            }
            writeJson(data,fileName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void appendJsonWithKey(JSONObject jsonObject, String key,String fileName){
        try  (FileReader reader = new FileReader(fileName)) {
            JSONParser jsonParser = new JSONParser();
            JSONObject data;
            data = (JSONObject) jsonParser.parse(reader);
            JSONArray d = (JSONArray) data.get(key);
            d.add(1,jsonObject);
            data.put(key,d);
            writeJson(data,fileName);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getJsonDataWithJsonPath(String path, String file) {
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(file));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] pathData = path.split("-");
        int len = pathData.length;
        if (len == 1) {
            return (String) jsonObject.get(pathData[0]);
        } else {
            JSONObject jsonObj = (JSONObject) jsonObject.get(pathData[0]);
            for (int i = 1; i < len - 1; i++) {
                jsonObj = (JSONObject) jsonObj.get(pathData[i]);
            }
            return String.valueOf(jsonObj.get(pathData[len - 1]));
        }
    }

    public static JSONObject getJsonObjectUSingJsonPath(String path, String file) {
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(file));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] pathData = path.split("-");
        int len = pathData.length;
        if (len == 1) {
            return (JSONObject) jsonObject.get(pathData[0]);
        } else {
            JSONObject jsonObj = (JSONObject) jsonObject.get(pathData[0]);
            for (int i = 1; i < len - 1; i++) {
                jsonObj = (JSONObject) jsonObj.get(pathData[i]);
            }
            return (JSONObject) jsonObj.get(pathData[len - 1]);
        }
    }

    public static JSONArray getJsonArrayUsingJsonPath(String path, String file) {
        JSONObject jsonObject = null;
        try {
            Object obj = parser.parse(new FileReader(file));
            jsonObject = (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] pathData = path.split("-");
        int len = pathData.length;
        if (len == 1) {
            return (JSONArray) jsonObject.get(pathData[0]);
        } else {
            JSONObject jsonObj = (JSONObject) jsonObject.get(pathData[0]);
            for (int i = 1; i < len - 1; i++) {
                jsonObj = (JSONObject) jsonObj.get(pathData[i]);
            }
            return (JSONArray) jsonObj.get(pathData[len - 1]);
        }
    }



    public static void clearDataFromJson(String fileName){
        try (FileWriter file = new FileWriter(fileName)) {
                file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
