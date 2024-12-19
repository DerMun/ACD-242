package TP2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader {

    public static DigraphList readAsList(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int numVertices = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith("c")) {
                continue;
            }

            if (line.startsWith("p sp")) {
                String[] parts = line.split(" ");
                numVertices = Integer.parseInt(parts[2]);
            }

            if (line.startsWith("a")) {
                break;
            }
        }

        DigraphList graph = new DigraphList(numVertices);

        do {
            String line = scanner.nextLine();
            if (line.startsWith("a")) {
                String[] parts = line.split(" ");
                int v = Integer.parseInt(parts[1]) - 1; //vértices começam do 1 no arquivo
                int w = Integer.parseInt(parts[2]) - 1;
                double weight = Double.parseDouble(parts[3]);

                graph.addEdge(v, w, weight);
            }
        } while (scanner.hasNextLine());

        scanner.close();
        return graph;
    }

    public static DigraphMatrix readAsMatrix(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int numVertices = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith("c")) {
                continue;
            }

            if (line.startsWith("p sp")) {
                String[] parts = line.split(" ");
                numVertices = Integer.parseInt(parts[2]);
            }

            if (line.startsWith("a")) {
                break;
            }
        }

        DigraphMatrix graph = new DigraphMatrix(numVertices);

        do {
            String line = scanner.nextLine();
            if (line.startsWith("a")) {
                String[] parts = line.split(" ");
                int v = Integer.parseInt(parts[1]) - 1;
                int w = Integer.parseInt(parts[2]) - 1;
                double weight = Double.parseDouble(parts[3]);

                graph.addEdge(v, w, weight);
            }
        } while (scanner.hasNextLine());

        scanner.close();
        return graph;
    }
}
