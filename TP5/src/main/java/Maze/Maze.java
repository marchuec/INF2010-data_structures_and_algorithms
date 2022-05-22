package Maze;

import java.util.*;
import java.util.stream.Collectors;

public class Maze {
    /**
     * Returns the distance of the shortest path within the maze
     * @param maze 2D table representing the maze
     * @return Distance of the shortest path within the maze, null if not solvable
     */
    public static Integer findShortestPath(ArrayList<ArrayList<Tile>> maze) {
        if (maze.isEmpty())
            return null;

        int ROWS = maze.size();
        int COLS = maze.get(0).size();

        // Trouve l'entrée et la sortie
        ArrayList<Coord> extremities = findExtremities(maze, ROWS, COLS);
        if (extremities.size() != 2)    // Si il n'y a pas 1 entrée et 1 sortie
            return null;
        Coord start = extremities.get(0);

        boolean[][] visited = new boolean[ROWS][COLS];  // Array des noeuds visités
        visited[start.row][start.col] = true;

        Queue<queueNode> q = new LinkedList<>();    // Queue contenant les noeuds actuels à traiter
        q.add(new queueNode(start, 0));

        // On analyse le labyrinthe tant qu'il reste des noeuds voisins à visiter ou qu'on ait pas
        // atteint la sortie
        while (!q.isEmpty()) {
            queueNode current = q.poll();
            Coord coordCurrent = current.coord;

            // On visite les 4 voisins
            for (int i = 0; i < 4; i++) {
                Coord neighbour = coordCurrent.direction(i);
                int row = neighbour.row;
                int col = neighbour.col;

                // Si la cellule est dans le labyrinthe, n'est pas un mur et n'a pas déjà été visitée
                if (row >= 0 && row < ROWS && col >= 0 && col < COLS &&
                        maze.get(row).get(col) != Tile.Wall && !visited[row][col]) {
                    // Si on a atteint la sortie
                    if (maze.get(row).get(col) == Tile.Exit) {
                        return current.dist + 1;
                    }

                    // On marque la cellule comme visitée et on l'ajoute à la queue
                    visited[row][col] = true;
                    q.add(new queueNode(neighbour, current.dist + 1));
                }
            }
        }

        return null;    // Si on ne trouve pas de solution
    }

    private static class Coord {
        final int row;
        final int col;

        /**
         * Constructeur de Coord
         * @param row Coordonnée ligne
         * @param col Coordonnée colonne
         */
        Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * Retourne la coordonnée voisine dans une des 4 directions
         * @param i La direction, un entier entre 0 et 3
         * @return La nouvelle coordonnée
         */
        Coord direction(int i) {
            switch (i) {
                case 0: return new Coord(row + 1, col);
                case 1: return new Coord(row - 1, col);
                case 2: return new Coord(row, col + 1);
                case 3: return new Coord(row, col - 1);
                default: return this;
            }
        }
    }

    private static class queueNode {
        final Coord coord;
        final int dist;

        /**
         * Constructeur de queueNode
         * @param coord Coordonnée du point
         * @param dist Distance entre l'entrée et le point sur le trajet courant
         */
        queueNode(Coord coord, int dist)
        {
            this.coord = coord;
            this.dist = dist;
        }
    }

    /**
     * Trouve toutes les sorties du labyrinthe
     * @return extremities Les sorties
     */
    private static ArrayList<Coord> findExtremities(ArrayList<ArrayList<Tile>> maze, int ROWS, int COLS) {
        ArrayList<Coord> extremities = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (maze.get(i).get(j) == Tile.Exit)
                    extremities.add(new Coord(i, j));
            }
        }
        return extremities;
    }

    /**
     * Print the maze line by line
     * @param maze The maze to print
     */
    public static void printMaze(ArrayList<ArrayList<Tile>> maze) {
        for (ArrayList<Tile> row : maze) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }
}

