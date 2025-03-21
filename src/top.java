import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;
import org.json.*;
/*
 * 
 *   {
    "id": 1,
    "user_id": "18",
    "age": "33",
    "user_weight": "91.88",
    "name": "Pasta Carbonara",
    "price": 10.39,
    "weight": 630,
    "calories": 383,
    "fat": 10.3,
    "carbs": 5.95,
    "protein": 12.67,
    "time_consumed": "11:58",
    "date_consumed": "2022-09-25",
    "type": "lunch",
    "favorite": "false",
    "procedence": "purchased"
  }

  3) Find the food with the highest amount of proteins that was consumed on 1st of November 2022.
Output: name of the meal

4) Calculate how many users have passed 30k calories for each month.
Output: Lines in format "<month in format YYYY-MM>: <number of users meeting criteria>"

5) Find 3 cheapest foods and their consumers.
Output: Lines in format "<name of the meal>, <lowest price>, <consumer ID>"

 */
class Diet{
    int id;
    String user_id;
    String age;
    String user_weight;
    String name;
    double price;
    int weight;
    int calories;
    double fat;
    double carbs;
    double protein;
    String time_consumed;
    String date_consumed;
    String type;
    String favorite;
    String procedence;

    public String toString(){
        return "id: " + id + ", user_id: " + user_id + ", age: " + age;
        //", user_weight: " + user_weight + ", name: " + name + ", price: " + price + ", weight: " + weight + ", calories: " + calories + ", fat: " + fat + ", carbs: " + carbs + ", protein: " + protein + ", time_consumed: " + time_consumed + ", date_consumed: " + date_consumed + ", type: " + type + ", favorite: " + favorite + ", procedence: " + procedence;
    }
}


public class top {


    public static void main(String[] args) {
        String url = "https://git.toptal.com/screeners/calories-json/-/raw/main/calories.json";
        List<Diet> diets = getJsonData(url);
        //System.out.println("Hello, World!");
    }

    public static List<Diet> getJsonData(String url_json){
        List<Diet> dietList = new ArrayList<>();
        try {
            URL url = new URL(url_json);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
           // JSONObject jsonObject = new JSONObject("{'id': 1, 'user_id': '18', 'age': '33', 'user_weight': '91.88', 'name': 'Pasta Carbonara', 'price': 10.39, 'weight': 630, 'calories': 383, 'fat': 10.3, 'carbs': 5.95, 'protein': 12.67, 'time_consumed': '11:58', 'date_consumed': '2022-09-25', 'type': 'lunch', 'favorite': 'false', 'procedence': 'purchased'}");
           // System.out.println(jsonObject);
           // System.out.println(jsonObject.getInt("id"));
            StringBuilder all_lines = new StringBuilder();
            while ((output = br.readLine()) != null) {
                all_lines.append(output);
            }
            String lines = all_lines.toString();
            lines = lines.replace("\n","");
            /* using Gson from Google
             *       JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
             */
            //all_lines = all_lines.toString()
            //.replace("\n","");
            while (lines.contains("{")) {
                String element = lines.substring(lines.indexOf("{"), lines.indexOf("}") + 1);
                lines = lines.replace(element, "");
                //System.out.println(element);
                Diet diet = new Diet();
                JSONObject jsonObject = new JSONObject(element);
                diet.id = jsonObject.getInt("id");
                diet.user_id = jsonObject.getString("user_id");
                diet.age = jsonObject.getString("age");
                diet.user_weight = jsonObject.getString("user_weight");
                diet.name = jsonObject.getString("name");
                diet.price = jsonObject.getDouble("price");
                diet.weight = jsonObject.getInt("weight");
                diet.calories = jsonObject.getInt("calories");
                diet.fat = jsonObject.getDouble("fat");
                diet.carbs = jsonObject.getDouble("carbs");
                diet.protein = jsonObject.getDouble("protein");
                diet.time_consumed = jsonObject.getString("time_consumed");
                diet.date_consumed = jsonObject.getString("date_consumed");
                diet.type = jsonObject.getString("type");
                diet.favorite = jsonObject.getString("favorite");
                diet.procedence = jsonObject.getString("procedence");
                dietList.add(diet);
            }
            //System.out.println(dietList.get(0));
           // System.out.println(dietList);
            //Fin d the food with the highest amount of proteins that was consumed on 1st of November 2022.
            //Output: name of the meal
            double maxProtein = Double.MIN_VALUE;
            String maxProteinMeal = "";
            for (Diet diet : dietList) {
                if (diet.date_consumed.equals("2022-11-01")) {
                    //System.out.println("Dish:" + diet.name + ", Protein:" + diet.protein);
                    //proteinsPerDish.put(diet.name, proteinsPerDish.getOrDefault(diet.name, 0.0) + diet.protein);
                    //you need this to save the name come on
                    if (diet.protein > maxProtein) {
                        maxProtein = diet.protein;
                        maxProteinMeal = diet.name;
                    }
                }
            }
            System.out.println("Max protein: " + maxProtein);
            System.out.println("Max protein meal: " + maxProteinMeal);
            /* Calculate how many users have passed 30k calories for each month.
                Output: Lines in format "<month in format YYYY-MM>: <number of users meeting criteria>"
            /* */
            Map<String, Map<String, Integer>> monthUserCalories = new HashMap<>();
            for (Diet diet : dietList) {
                String date_diet = diet.date_consumed;
                String[] date_diet_split = date_diet.split("-");
                String month = date_diet_split[0] + "-" + date_diet_split[1];
                String userId = diet.user_id;

                monthUserCalories.putIfAbsent(month, new HashMap<>());
                Map<String, Integer> userCalories = monthUserCalories.get(month);
                userCalories.put(userId, userCalories.getOrDefault(userId, 0) + diet.calories);
            }
            System.out.println("Month user calories: " + monthUserCalories);
            List<String> usersCal = new ArrayList<>();
            int cal = 30000;

            for (Map.Entry<String, Map<String, Integer>> entry : monthUserCalories.entrySet()) {
                String month = entry.getKey();
                Map<String, Integer> userCalories = entry.getValue();
                long count = userCalories.values().stream().filter(totalCalories -> totalCalories > cal).count();
                usersCal.add(month + ": " + count);
            }

            System.out.println("Users with more than 30k calories: " + usersCal);
           /*  List<String> usersCal = new ArrayList<>();
            Map<String, Set<String>> usersCalMonthMap = new HashMap<>();
            int cal = 30000;
            System.out.println("Month users: " + monthUsers);
            for (Map.Entry<String, List<Diet>> entry : monthUsers.entrySet()) {
                String month = entry.getKey();
                List<Diet> diets = entry.getValue();
                System.out.println("Month: " + month + ", diets: " + diets.size());
                int totalCalories = 0;
                for (Diet diet : diets) {
                    totalCalories += diet.calories;
                }
                if (totalCalories > cal) {
                    System.out.println("Diet: " + diet.name + ", userID: " + diet.user_id);
                    //usersCal.add(month + ": " + totalCalories);
                }
            }
            System.out.println("Users with more than 30k calories: " + usersCal);*/
            //Find 3 cheapest foods and their consumers.
            //Output: Lines in format "<name of the meal>, <lowest price>, <consumer ID>"
            dietList.sort(Comparator.comparingDouble(d -> d.price));
            List<Diet> cheapestFoods = dietList.stream().limit(3).collect(Collectors.toList());

            System.out.println("3 Cheapest foods and their consumers:");
            for (Diet diet : cheapestFoods) {
                System.out.println(diet.name + ", " + diet.price + ", " + diet.user_id);
            }
            
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dietList;
    }
 
}
