package org.example;



import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class JsonPlaceholderApiClient {
    static Gson JsonConverter=new Gson();

    private static final String TODOS_ENDPOINT = "/todos";

    private static final String USERS_ENDPOINT = "/users";
    private static final String POSTS_ENDPOINT = "/posts";
    private static final String COMMENTS_ENDPOINT = "/comments";

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    public static User createUser(User user) throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);


        String jsonInputString = JsonConverter.toJson(user);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name())) {
            String responseBody = scanner.useDelimiter("\\A").next();
            return JsonConverter.fromJson(responseBody, User.class);
        }
    }

    public static User updateUser(User user) throws IOException {
        URL url = new URL(BASE_URL + "/" + user.getId());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        String jsonInputString = JsonConverter.toJson(user);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "+conn.getResponseCode());
        }




        try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name())) {
            String responseBody = scanner.useDelimiter("\\A").next();
            return JsonConverter.fromJson(responseBody, User.class);
        }
    }

    public static void deleteUser(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("Accept", "application/json");


        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        conn.disconnect();
    }

    public static User getUserById(int id) throws IOException {
        URL url = new URL(BASE_URL + "/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name())) {
            String responseBody = scanner.useDelimiter("\\A").next();
            return JsonConverter.fromJson(responseBody, User.class);
        }
    }

    public static User getUserByUsername(String username) throws IOException {
        URL url = new URL(BASE_URL + "?username=" + username);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");


        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name())) {
            String responseBody = scanner.useDelimiter("\\A").next();
            User[] users = JsonConverter.fromJson(responseBody, User[].class);
            if (users.length == 0) {
                return null;
            } else {
                return users[0];
            }
        }
    }

    public static User[] getAllUsers() throws IOException {
        URL url = new URL(BASE_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");


        if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8.name())) {
            String responseBody = scanner.useDelimiter("\\A").next();
            return JsonConverter.fromJson(responseBody, User[].class);
        }
    }
    public static void writeCommentsToFile() throws IOException {
        // Get the post with the specified ID for the user
        URL postUrl = new URL("https://jsonplaceholder.typicode.com/users/1/posts");
        HttpURLConnection postConn = (HttpURLConnection) postUrl.openConnection();
        postConn.setRequestMethod("GET");
        postConn.setRequestProperty("Accept", "application/json");

        if (postConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + postConn.getResponseCode());
        }

        try (Scanner postScanner = new Scanner(postConn.getInputStream(), StandardCharsets.UTF_8.name())) {
            String postResponseBody = postScanner.useDelimiter("\\A").next();
            Post[] posts = JsonConverter.fromJson(postResponseBody, Post[].class);

            if (posts.length == 0) {
                throw new RuntimeException("Post not found");
            }

            // Get the comments for the post
            URL commentsUrl = new URL("https://jsonplaceholder.typicode.com/users/1/posts");
            HttpURLConnection commentsConn = (HttpURLConnection) commentsUrl.openConnection();
            commentsConn.setRequestMethod("GET");
            commentsConn.setRequestProperty("Accept", "application/json");

            if (commentsConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + commentsConn.getResponseCode());
            }

            try (Scanner commentsScanner = new Scanner(commentsConn.getInputStream(), StandardCharsets.UTF_8.name());
                 BufferedWriter writer = new BufferedWriter(new FileWriter("user-" + 1 + "-post-" + 10 + "-comments.json"))) {
                String commentsResponseBody = commentsScanner.useDelimiter("\\A").next();
                Comment[] comments = JsonConverter.fromJson(commentsResponseBody, Comment[].class);

                for (Comment comment : comments) {
                    String json = JsonConverter.toJson(comment);
                    writer.write(json);
                    writer.newLine();
                }
            }
        }
    }
    public static List<Todo> getOpenTodosForUser() throws IOException {
        URL todosUrl = new URL("https://jsonplaceholder.typicode.com/users/1/todos");
        HttpURLConnection todosConn = (HttpURLConnection) todosUrl.openConnection();
        todosConn.setRequestMethod("GET");
        todosConn.setRequestProperty("Accept", "application/json");

        if (todosConn.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + todosConn.getResponseCode());
        }

        List<Todo> todos = new ArrayList<>();

        try (Scanner todosScanner = new Scanner(todosConn.getInputStream(), StandardCharsets.UTF_8.name())) {
            String todosResponseBody = todosScanner.useDelimiter("\\A").next();
            Todo[] allTodos = JsonConverter.fromJson(todosResponseBody, Todo[].class);

            for (Todo todo : allTodos) {
                if (!todo.isCompleted() && todo.getUserId() == 1) {
                    todos.add(todo);
                }
            }
        }

        return todos;
    }

    public static void main(String[] args) throws IOException {

        System.out.println(JsonConverter.toJson(getOpenTodosForUser()));
    }
}


 class User {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;
}

class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

}

class Geo {
    private String lat;
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

}

class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

}
 class Post {
    private int userId;
    private int id;
    private String title;
    private String body;


    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
 class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;


    public Comment(int postId, int id, String name, String email, String body) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
 class Todo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}