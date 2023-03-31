package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

    public class JsonPlaceholderClient {

        private static final String BASE_URL = "https://jsonplaceholder.typicode.com";


        public static void getCommentsForUserAndPost(int userId, String username) throws JSONException {

            JSONObject user = getJSONObjectFromAPI("/users?username=" + username).getJSONObject("1");


            JSONArray posts = getJSONArrayFromAPI("/users/" + user.getInt("id") + "/posts");


            JSONObject lastPost = posts.getJSONObject(posts.length() - 1);


            JSONArray comments = getJSONArrayFromAPI("/posts/" + lastPost.getInt("id") + "/comments");


            String fileName = "user-" + userId + "-post-" + lastPost.getInt("id") + "-comments.json";
            try (FileWriter fileWriter = new FileWriter(fileName)) {
                fileWriter.write(comments.toString(2));
            } catch (IOException e) {
                e.printStackTrace();
            }

            
            System.out.println("Comments for User " + userId + " and Post " + lastPost.getInt("id") + ":");
            for (int i = 0; i < comments.length(); i++) {
                JSONObject comment = comments.getJSONObject(i);
                System.out.println(comment.getString("name") + " (" + comment.getString("email") + "): " + comment.getString("body"));
            }
        }


        public static JSONObject getJSONObjectFromAPI(String path) {
            try {
                URL url = new URL(BASE_URL + path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");

                if (connection.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
                }

                InputStream inputStream = connection.getInputStream();
                byte[] bytes = inputStream.readAllBytes();
                String jsonString = new String(bytes, StandardCharsets.UTF_8);

                return new JSONObject(jsonString);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }


        public static JSONArray getJSONArrayFromAPI(String path) {
            try {
                URL url = new URL(BASE_URL + path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();




                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");


                if (connection.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + connection.getResponseCode());
                }

                InputStream inputStream = connection.getInputStream();
                byte[] bytes = inputStream.readAllBytes();
                String jsonString = new String(bytes, StandardCharsets.UTF_8);

                return new JSONArray(jsonString);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        public static void getOpenTasksForUser(int userId) throws JSONException {
            JSONArray todos = getJSONArrayFromAPI("/users/" + userId + "/todos");
            System.out.println("Open tasks for User " + userId + ":");
            for (int i = 0; i < todos.length(); i++) {
                JSONObject todo = todos.getJSONObject(i);
                if (!todo.getBoolean("completed")) {
                    System.out.println(todo.getString("title"));
                }
            }
        }
        public static void main(String[] args) throws JSONException {

            getCommentsForUserAndPost(1, "Bret");
            int userId = 1;
            getOpenTasksForUser(userId);
        }
}
