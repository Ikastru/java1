package ru.progwards.java2.lessons.graph;

import java.util.Arrays;
import java.util.BitSet;

public class Dijkstra {
    private int[][] dataDijkstra;

    public static int[][] getGraphMatrixInt(int size) {
        int[][] matrix = new int[size][size];
        matrix[0][1] = 6;
        matrix[0][2] = 2;
        matrix[2][1] = 3;
        matrix[1][3] = 1;
        matrix[2][3] = 5;
        printMatrix(matrix);
        return matrix;
    }

    public Dijkstra(int[][] graph) {
        int count = 0;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                count++;
            }
        }
        int sizel = graph.length;
        int sizew = count / graph.length;
        dataDijkstra = new int[sizel][sizew];
        for (int i = 0; i < graph.length; i++)
            for (int j = 0; j < graph[i].length; j++)
                dataDijkstra[i][j] = graph[i][j];
    }

    // Для поиска с нулевого узла
    public void dijkstra() {
        Integer[] costs = new Integer[dataDijkstra.length];    // Стоимость перехода
        Integer[] parents = new Integer[dataDijkstra.length];  // Родительский узел
        BitSet visited = new BitSet(dataDijkstra.length);      // "Ферма" маркеров посещённости

        int w = 0;
        do {
            System.out.println("-> Рассматриваем вершину: " + w);
            int min = 0;
            for (int i = 0; i < dataDijkstra.length; i++) {    // Обрабатываем каждую дугу
                if (dataDijkstra[w][i] == 0) continue;      // Дуги нет - идём дальше
                if (min == 0 || (!visited.get(i) && dataDijkstra[w][min] > dataDijkstra[w][i])) {
                    min = i;
                }
                if (costs[i] == null || costs[i] > costs[w] + dataDijkstra[w][i]) {
                    System.out.print("Меням вес с " + costs[i]);
                    costs[i] = (costs[w] != null ? costs[w] : 0) + dataDijkstra[w][i];
                    System.out.println(" на " + costs[i] + " для вершины " + i);
                    parents[i] = w;
                }
            }
            System.out.println("Вершина с минимальным весом: " + min);
            visited.set(w);
            w = min;
        } while (w != 0);

        System.out.println(Arrays.toString(costs));
        printPath(parents, 3);
    }

    public void printPath(Integer[] parents, int target) {
        Integer parent = target;
        do {
            System.out.print(parent + " <- ");
            parent = parents[parent];
        } while (parent != null);
        System.out.println();
    }

    // Для поиска с узла n
    public int[][] find(int n) {
        Integer[] costs = new Integer[dataDijkstra.length];    // Стоимость перехода
        Integer[] parents = new Integer[dataDijkstra.length];  // Родительский узел
        BitSet visited = new BitSet(dataDijkstra.length);      // "Ферма" маркеров посещённости

        int w = n;
        do {
            System.out.println("-> Рассматриваем вершину: " + w);
            int min = 0;
            for (int i = 0; i < dataDijkstra.length; i++) {    // Обрабатываем каждую дугу
                if (dataDijkstra[w][i] == 0) continue;      // Дуги нет - идём дальше
                if (min == 0 || (!visited.get(i) && dataDijkstra[w][min] > dataDijkstra[w][i])) {
                    min = i;
                }
                if (costs[i] == null || costs[i] > costs[w] + dataDijkstra[w][i]) {
                    System.out.print("Меням вес с " + costs[i]);
                    costs[i] = (costs[w] != null ? costs[w] : 0) + dataDijkstra[w][i];
                    System.out.println(" на " + costs[i] + " для вершины " + i);
                    parents[i] = w;
                }
            }
            System.out.println("Вершина с минимальным весом: " + min);
            visited.set(w);
            w = min;
        } while (w != 0);

        System.out.println(Arrays.toString(costs));
        printPath(parents, 3);
        return dataDijkstra;
    }

    public static void printMatrix(int[][] matr) {
        for (int[] a : matr) {
            for (int i : a) {
                System.out.print(i + "\t");
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra(getGraphMatrixInt(4));
        dijkstra.dijkstra();
        printMatrix(dijkstra.find(0));
    }
}
