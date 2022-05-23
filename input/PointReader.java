package input;

import dom.Point;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PointReader {
    public List<Point> readPoints(String filepath) throws IOException {
        List<Point> points = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filepath)).useDelimiter("\\s*\\n");
        scanner.useLocale(Locale.US);
        while (scanner.hasNextLine()){
            double x = Double.parseDouble(scanner.nextLine());
            double y = Double.parseDouble(scanner.nextLine());

            points.add(new Point(x,y));
        }

        return points;
    }
}
