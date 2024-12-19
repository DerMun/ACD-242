import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Engine {
    private String name;
    private String hp;
    private String torque;
    private String manufacturer;
    private int numberOfCylinders;
    private int valvesPerCylinder;
    private String productionYears;

    public Engine(String name, String hp, String torque, String manufacturer, int numberOfCylinders, int valvesPerCylinder, String productionYears) {
        this.name = name;
        this.hp = hp;
        this.torque = torque;
        this.manufacturer = manufacturer;
        this.numberOfCylinders = numberOfCylinders;
        this.valvesPerCylinder = valvesPerCylinder;
        this.productionYears = productionYears;
    }

    public String getName() {
        return name;
    }

    public String printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Engine Name: ").append(name).append("\n");
        sb.append("Horse Power: ").append(hp).append(" HP\n");
        sb.append("Torque: ").append(torque).append(" lb-ft\n");
        sb.append("Manufacturer: ").append(manufacturer).append("\n");
        sb.append("Number of Cylinders: ").append(numberOfCylinders).append("\n");
        sb.append("Valves per Cylinder: ").append(valvesPerCylinder).append("\n");
        sb.append("Production Years: ").append(productionYears);
        return sb.toString();
    }


    public static List<Engine> readEnginesFromFile(String filename) {
        List<Engine> engines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 7) {
                    String name = data[0].trim();
                    String hp = data[1].trim();
                    String torque = data[2].trim();
                    String manufacturer = data[3].trim();
                    int numberOfCylinders = Integer.parseInt(data[4].trim());
                    int valvesPerCylinder = Integer.parseInt(data[5].trim());
                    String productionYears = data[6].trim();

                    Engine engine = new Engine(name, hp, torque, manufacturer, numberOfCylinders, valvesPerCylinder, productionYears);
                    engines.add(engine);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return engines;
    }

}