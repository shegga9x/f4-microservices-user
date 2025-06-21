package com.f4.user;

import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class GenerateUUIDsOnly {

    public static void main(String[] args) {
        String fileName = "uuid_data.csv";
        int totalRecords = 2_000_000;

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("id\n");
            for (int i = 1; i <= totalRecords; i++) {
                writer.write(UUID.randomUUID().toString() + "\n");

                if (i % 100_000 == 0) {
                    System.out.println("Generated: " + i);
                }
            }

            System.out.println("✅ Finished writing 2,000,000 UUIDs to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
