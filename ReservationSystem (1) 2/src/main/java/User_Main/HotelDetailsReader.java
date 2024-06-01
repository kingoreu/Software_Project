package User_Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HotelDetailsReader {

    public static List<String[]> readHotelDetails(String filename) {
        List<String[]> hotelDetails = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    String hotelName = parts[2];
                    String hotelRegion = parts[3];
                    hotelDetails.add(new String[]{hotelName, hotelRegion});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hotelDetails;
    }
}
