package util;

import model.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserIOUtil {

    // Ghi danh sách users ra file CSV
    public static void writeUsersToFile(List<User> users, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("ID,Username,Email,Role"); // Header
            writer.newLine();
            for (User user : users) {
                writer.write(user.getId() + "," + user.getUsername() + "," +
                        user.getEmail() + "," + user.getRole());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
