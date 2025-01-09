package sk.upjs.paz;

import com.sun.tools.jdeprscan.scan.Scan;

import java.util.Scanner;

public class Magnet implements Comparable<Magnet> {
    private String title;
    private int year;
    private String coundtryCode;
    private boolean fragile;
    private int weight;
    private String giver;
    private String text;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoundtryCode() {
        return coundtryCode;
    }

    public void setCoundtryCode(String coundtryCode) {
        this.coundtryCode = coundtryCode;
    }

    public boolean isFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getGiver() {
        return giver;
    }

    public void setGiver(String giver) {
        this.giver = giver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Magnet(String title, int year, String coundtryCode, boolean fragile, int weight, String giver, String text) {
        this.title = title;
        this.year = year;
        this.coundtryCode = coundtryCode;
        this.fragile = fragile;
        this.weight = weight;
        this.giver = giver;
        this.text = text;
    }

    public Magnet(String title, int year, String coundtryCode, boolean fragile, int weight, String giver) {
        this.title = title;
        this.year = year;
        this.coundtryCode = coundtryCode;
        this.fragile = fragile;
        this.weight = weight;
        this.giver = giver;
    }

    public static Magnet fromString(String input) {
        Scanner scanner = new Scanner(input);
        String title = scanner.next();
        int year = scanner.nextInt();
        String coundtryCode = scanner.next();
        boolean fragile = scanner.nextBoolean();
        int weight = scanner.nextInt();
        String giver = scanner.next();
        if (scanner.hasNext()) {
            String text = scanner.next();
            return new Magnet(title, year, coundtryCode, fragile, weight, giver, text);
        }
        scanner.close();
        return new Magnet(title, year, coundtryCode, fragile, weight, giver);
    }

    @Override
    public String toString() {
        if (text != null) {
            return title + "\t" + year + "\t" + coundtryCode + "\t" + fragile + "\t" + weight + "\t" + giver + "\t" + text;
        }
        return title + "\t" + year + "\t" + coundtryCode + "\t" + fragile + "\t" + weight + "\t" + giver;
    }

    @Override
    public int compareTo(Magnet o) {
        if (this.getYear() == o.getYear()) {
            if (this.getWeight() > o.getWeight()) {
                return -1;
            }
            if (this.getWeight() < o.getWeight()) {
                return 1;
            }
        } else {
            if (this.getYear() < o.getYear()) return -1;
            else return 1;

        }
        return 0;
    }
}
