import java.util.*;

//Actual generation and printing of word search
public class WordSearch {

    private char[][] grid;
    private boolean[][] solution;
    private String[] userWords;

    //takes each inputted word and uses placeWord method to input them into a generated grid
    public void generate(String[] w) {

        for(int i = 0 ; i < w.length ; i++){
            w[i] = w[i].toLowerCase();
        }
        this.userWords = w;
        char[][] wordChars = setupGrid();
        for(int i = 0 ; i < wordChars.length ; i++){
            placeWord(wordChars, i);
        }
        fillGrid();
    }//end of generate method

    public String toString() {

        String result = "";
        for(int i = 0 ; i < grid.length ; i++){
            for(int x = 0 ; x < grid[i].length ; x++){
                result += " " + grid[i][x] + " ";
            }
            result += "\n";
        }
        return result;
    }//end of toString method

    //generates solution grid
    public String toSolution() {

        String result = "";
        for(int i = 0 ; i < grid.length ; i++){
            for(int x = 0 ; x < grid[i].length ; x++){
                if(solution[i][x]){
                    result += " " + grid[i][x] + " ";
                }
                else{
                    result += " X ";
                }
            }
            result += "\n";
        }
        return result;
    }//end of toSolution method

    //randomly places words in grid both vertically, horizontally, and diagonally
    private void placeWord(char[][] wordChars, int iter) {

        Random rand = new Random();
        int direction = rand.nextInt(3);
        int[] pos = {0,0};
        if(direction == 0){
            boolean placed = false;
            int attempts = 0;
            while(!placed && attempts < 100){
                pos[0] = rand.nextInt((grid.length-1) - wordChars[iter].length);
                pos[1] = rand.nextInt((grid.length-1) - wordChars[iter].length);
                placed = true;
                for(int u = 0 ; u < wordChars[iter].length ; u++){
                    if(grid[pos[0] + u][pos[1]] != '\u0000' && grid[pos[0] + u]
                            [pos[1]] != wordChars[iter][u]){
                        placed = false;
                        break;
                    }
                }
                attempts++;
            }
            if(placed){
                for(int x = 0 ; x < wordChars[iter].length ; x++){
                    grid[pos[0]][pos[1]] = wordChars[iter][x];
                    solution[pos[0]][pos[1]] = true;
                    pos[0]++;
                }
            }
        }
        else if(direction == 1) {
            boolean placed = false;
            int attempts = 0;
            while(!placed && attempts < 100){
                pos[0] = rand.nextInt((grid.length-1) - wordChars[iter].length);
                pos[1] = rand.nextInt((grid.length-1) - wordChars[iter].length);
                placed = true;
                for(int u = 0 ; u < wordChars[iter].length ; u++){
                    if(grid[pos[0]][pos[1] + u] != '\u0000' && grid[pos[0]][pos[1] +
                            u] != wordChars[iter][u]){
                        placed = false;
                        break;
                    }
                }
                attempts++;
            }
            if(placed){
                for(int x = 0 ; x < wordChars[iter].length ; x++){
                    grid[pos[0]][pos[1]] = wordChars[iter][x];
                    solution[pos[0]][pos[1]] = true;
                    pos[1]++;
                }
            }
        }
        else if(direction == 2) {
            boolean placed = false;
            int attempts = 0;
            while(!placed && attempts < 100){
                pos[0] = rand.nextInt((grid.length-1) - wordChars[iter].length);
                pos[1] = rand.nextInt((grid.length-1) - wordChars[iter].length);
                placed = true;
                for(int u = 0 ; u < wordChars[iter].length ; u++){
                    if(grid[pos[0] + u][pos[1] + u] != '\u0000' && grid[pos[0] + u]
                            [pos[1] + u] != wordChars[iter][u]){
                        placed = false;
                        break;
                    }
                }
                attempts++;
            }
            if(placed) {
                for(int x = 0 ; x < wordChars[iter].length ; x++){
                    grid[pos[0]][pos[1]] = wordChars[iter][x];
                    solution[pos[0]][pos[1]] = true;
                    pos[1]++;
                    pos[0]++;
                }
            }
        }
    }//end of placeWord method

    //Creates the grid of characters
    private char[][] setupGrid() {

        char[][] wordChars = new char[userWords.length][];
        int longest = 8;
        for(int i = 0 ; i < userWords.length ; i++){
            wordChars[i] = userWords[i].toCharArray();
            if(wordChars[i].length > longest){
                longest = wordChars[i].length;
            }
        }
        if(userWords.length > longest){
            longest = userWords.length;
        }
        this.grid = new char[longest + 4][longest + 4];
        this.solution = new boolean[longest + 4][longest + 4];
        return wordChars;
    }//end of setupGrid method

    //adds in random chars to unused spaces
    private void fillGrid() {

        for(int i = 0 ; i < grid.length ; i++){
            for(int x = 0 ; x < grid[i].length ; x++){
                Random rand = new Random();
                if(grid[i][x] == '\u0000'){ //unicode char for null
                    grid[i][x] = (char)(rand.nextInt(26)+97); //random alphabet char
                }
            }
        }
    }//end of fillGrid method

}//end of WordSearch class