package sk.upjs.paz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Fridge {

    private ArrayList<Magnet> myFridge;

    public Fridge() {
        myFridge = new ArrayList<>();
    }

    public void addMagnet(Magnet m) {
        myFridge.add(m);
    }

    public static Fridge loadMagnets(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        Fridge fridge = new Fridge();
        while (scanner.hasNextLine() && scanner.hasNext()) {
            fridge.addMagnet(Magnet.fromString(scanner.nextLine()));
        }
        return fridge;
    }

    public void saveMagnets(String filename) throws FileNotFoundException {
        File file = new File(filename);
        PrintWriter printWriter = new PrintWriter(file);
        ArrayList<Magnet> triedim = new ArrayList<>();
        for (Magnet magnet : myFridge) {
            triedim.add(magnet);
        }
        Collections.sort(triedim);
        for (Magnet magnet : triedim) {
            printWriter.println(magnet.toString());
        }
        printWriter.close();

    }

    @Override
    public String toString() {
        return myFridge.toString();
    }


    public List<Magnet> getMagnetsByCountry(String countryCode) {
        List<Magnet> list = new ArrayList<>();
        for (Magnet magnet : myFridge) {
            if (countryCode.equals(magnet.getCoundtryCode()))
                list.add(magnet);

        }
        return list;
    }

    public double totalWeight() {
        double vaha = 0;
        for (Magnet magnet : myFridge) {
            vaha = vaha + magnet.getWeight();
        }
        return Math.round(vaha / 100) / 10;
    }

    public List<String> getCountryCodes() {
        Set<String> mnozina = new HashSet<String>();
        for (Magnet magnet : myFridge) {
            mnozina.add(magnet.getCoundtryCode());
        }
        List<String> list = new ArrayList<>();
        for (String s : mnozina) {
            list.add(s);
        }
        return list;
    }

    public boolean isOriginalNamer() {
        boolean originalNames = true;
        Map<String, Integer> mapa = new HashMap<>();
        for (Magnet magnet : myFridge) {
            if (mapa.containsKey(magnet.getText())) {
                mapa.put(magnet.getText(), mapa.get(magnet.getText()) + 1);
            } else {
                mapa.put(magnet.getText(), 1);
            }
        }

        for (String s : mapa.keySet()) {
            if (mapa.get(s) > 1) {
                originalNames = false;
            }
        }
        return originalNames;
    }

    public String firstMagnetGiver() {
        int year = Integer.MAX_VALUE;
        int weight = Integer.MIN_VALUE;
        String giver = "";
        for (Magnet magnet : myFridge) {
            if (year == magnet.getYear()) {
                if (magnet.getWeight() > weight) {
                    giver = magnet.getGiver();
                }
            }
            if (magnet.getYear() < year) {
                year = magnet.getYear();
                weight = magnet.getWeight();
                giver = magnet.getGiver();
            }
        }
        return giver;
    }

    public List<Magnet> searchMagnetsByText(String searchText) {
        List<Magnet> vratim = new ArrayList<>();
        String hladanyText = searchText.toLowerCase();
        for (Magnet magnet : myFridge) {
            if (magnet.getText().toLowerCase().contains(hladanyText) || magnet.getTitle().toLowerCase().contains(hladanyText)) {
                vratim.add(magnet);
            }
        }
        return vratim;
    }

    public List<String> getCountriesWithMultipleMagnets() {
        List<String> vratim = new ArrayList<>();
        Map<String, Integer> mapa = new HashMap<>();
        for (Magnet magnet : myFridge) {
            if (mapa.containsKey(magnet.getCoundtryCode())) {
                mapa.put(magnet.getCoundtryCode(), mapa.get(magnet.getCoundtryCode()) + 1);
            } else {
                mapa.put(magnet.getCoundtryCode(), 1);
            }
        }
        for (String s : mapa.keySet()) {
            if (mapa.get(s) > 1) {
                vratim.add(s);
            }
        }
        Collections.sort(vratim);
        return vratim;
    }

    public Map<String, Integer> getMagnetCounts(int year) {
        Map<String, Integer> mapa = new HashMap<>();
        for (Magnet magnet : myFridge) {
            if (mapa.containsKey(magnet.getGiver())) {
                mapa.put(magnet.getGiver(), mapa.get(magnet.getGiver()) + 1);
            } else {
                mapa.put(magnet.getGiver(), 1);
            }
        }
        return mapa;
    }

    public boolean hasIdenticalMagnets() {

        for (Magnet magnet : myFridge) {
            int counter = 0;
            for (Magnet magnet1 : myFridge) {
                if (magnet1.getTitle().equals(magnet.getTitle()) && magnet1.getCoundtryCode().equals(magnet.getCoundtryCode()) && (magnet1.isFragile() == magnet.isFragile()) && magnet1.getText().equals(magnet.getText()) && (Math.abs(magnet.getWeight() - magnet1.getWeight()) < 3)) {
                    counter++;
                }
                if (counter == 2) {
                    return true;
                }

            }
        }


        return false;
    }


    public int calculateMovingBoxes() {
        int boxcounter = 0;
        int krehkeMale = 0;
        int krehkeVelke = 0;
        int nekrehke = 0;

        for (Magnet magnet : myFridge) {
            if (magnet.isFragile()) {
                if (magnet.getWeight() > 50) {
                    krehkeVelke++;
                } else {
                    krehkeMale++;
                }
            } else {
                nekrehke++;
            }
        }
        boxcounter += krehkeVelke;
        if (krehkeMale % 2 != 0) {
            boxcounter += krehkeMale / 2;
            boxcounter++;
        } else {
            boxcounter += krehkeMale / 2;
        }

        if (nekrehke % 30 != 0) {
            boxcounter += nekrehke / 30;
            boxcounter++;
        } else {
            boxcounter += nekrehke / 30;
        }

        return boxcounter;
    }

    public int removeByCountry(String countryCode) {
        int counter = 0;
        List<Magnet> toKeep = new ArrayList<>();
        for (Magnet magnet : myFridge) {
            if (!magnet.getCoundtryCode().equals(countryCode)) {
                toKeep.add(magnet);
            } else {
                if (magnet.isFragile()) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean isGoodRelative(String name, Map<Integer, Set<String>> visitedCountries) {
        int counterMagnetovNaChladnicke = 0;
        int counterKolkoIchMalDoniest = 0;
        for (Magnet magnet : myFridge) {
            if (magnet.getGiver().equals(name)) {
                counterMagnetovNaChladnicke++;
            }
        }
        for (Set<String> value : visitedCountries.values()) {
            counterKolkoIchMalDoniest += value.size();
        }

        if (counterKolkoIchMalDoniest == counterMagnetovNaChladnicke) {
            return true;
        }
        return false;
    }

    public int firstMagnetOfCountry(Map<String, Integer> countryEstablished) {
        int rok = Integer.MAX_VALUE;

        for (Magnet magnet : myFridge) {
            for (String s : countryEstablished.keySet()) {
                if (magnet.getCoundtryCode().equals(s)) {
                    if (magnet.getYear() < countryEstablished.get(s)) {
                        throw new InvalidYearException("zly rok kamo:" + magnet.getYear());
                    }
                    if (rok > (magnet.getYear() - countryEstablished.get(s))) {
                        rok = magnet.getYear() - countryEstablished.get(s);
                    }
                }
            }
        }
        return rok;
    }

    public static Set<String> dajRovnakeMagnetky(Fridge a, Fridge b) {
        Set<Magnet> znackyVA = new HashSet<>();
        Set<Magnet> znackyVB = new HashSet<>();
        Set<String> spolocne = new HashSet<>();
        for (Magnet ma : a.myFridge) {
            znackyVA.add(ma);
        }
        for (Magnet mb : b.myFridge) {
            znackyVB.add(mb);
        }

        for (Magnet magnet : znackyVA) {
            for (Magnet magnet1 : znackyVB) {
                if (magnet.getCoundtryCode().equals(magnet1)) {
                    spolocne.add(magnet.getCoundtryCode());
                }
            }
        }
        return spolocne;
    }


}

