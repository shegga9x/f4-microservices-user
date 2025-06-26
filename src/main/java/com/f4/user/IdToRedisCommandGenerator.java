package com.f4.user;

public class IdToRedisCommandGenerator {
    // public static void generateRedisCommands() {
    //     String inputCsv = "../data/user_ids.csv";
    //     String outputTxt = "redis_commands.txt";

    //     try (
    //             BufferedReader reader = new BufferedReader(new FileReader(inputCsv));
    //             BufferedWriter writer = new BufferedWriter(new FileWriter(outputTxt))) {
    //         String line = reader.readLine(); // skip header

    //         while ((line = reader.readLine()) != null) {
    //             String id = line.trim();
    //             if (id.isEmpty())
    //                 continue;

    //             String shortId = id.substring(0, 8);
    //             String name = "User-" + shortId;
    //             String avatar = "https://avatar.io/" + shortId + ".png";

    //             String json = String.format("{\"id\":\"%s\",\"name\":\"%s\",\"avatar\":\"%s\"}", id, name, avatar);
    //             String escapedJson = json.replace("\"", "\\\"");
    //             String redisCommand = String.format("SET user:%s \"%s\"", id, escapedJson);

    //             writer.write(redisCommand);
    //             writer.newLine();
    //         }

    //         System.out.println("✅ redis_commands.txt generated successfully.");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static void generateUserIds() {
    //     String outputCsv = "../data/user_ids.csv";
    //     int total = 1000000;

    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputCsv))) {
    //         writer.write("id\n"); // header

    //         for (int i = 0; i < total; i++) {
    //             UUID uuid = UUID.randomUUID();
    //             writer.write(uuid.toString());
    //             writer.newLine();
    //         }

    //         System.out.println("✅ Generated " + total + " UUIDs into " + outputCsv);
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    // public static void main(String[] args) {
    //     generateUserIds();
    //     // generateRedisCommands();

    // }
}
