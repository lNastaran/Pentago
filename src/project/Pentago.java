package project;

	import javafx.application.*;
	import javafx.scene.paint.*;

	public class Pentago {

	    private static Thread thread;
	    private Grid[] grids = new Grid[4];
	    private boolean side = false; // false for Orange True For Blue
	    private boolean moveOrRotate = false; // false for Move True for Rotate
	    private boolean gameOnGoing = false;

	    public Pentago() {
	        for(int i = 0; i < 4; i++) {
	            grids[i] = new Grid();
	        }
	    }

	    public void startGame(GUI gui) {
	        thread = Thread.currentThread();
	        synchronized(thread) {
	            try {
	                gameOnGoing = true;
	                while(!(grids[0].isFull() && grids[1].isFull() && grids[2].isFull() && grids[3].isFull())) {
	                    updateBoard(gui);
	                    thread.wait();
	                    moveOrRotate = true;
	                    updateBoard(gui);
	                    if(winner() != 0) break;
	                    thread.wait();
	                    moveOrRotate = false;
	                    updateBoard(gui);
	                    if(winner() != 0) break;
	                    side = !side;
	                }
	                switch(winner()) {
	                    case 0:
	                        System.out.println("The game is a draw");
	                        break;
	                    case 1:
	                        System.out.println("Orange Wins");
	                        break;
	                    case 2:
	                        System.out.println("Blue Wins");
	                        break;
	                }
	                gameOnGoing = false;

	            }
	            catch(Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    public void updateBoard(GUI g) {
	        for(int i = 0; i < 6; i++) {
	            for(int j = 0; j < 6; j++) {
	                int finalJ = j;
	                int finalI = i;
	                Platform.runLater(()->{
	                    switch(grids[(finalJ / 3) + (finalI > 2 ? 2 : 0)].table[finalI % 3][finalJ % 3]) {
	                        case 0:
	                            g.cells[finalI][finalJ].setFill(Color.GRAY);
	                            break;
	                        case 1:
	                            g.cells[finalI][finalJ].setFill(Color.ORANGE);
	                            break;
	                        case 2:
	                            g.cells[finalI][finalJ].setFill(Color.BLUE);
	                            break;
	                    }
	                });
	            }
	        }
	    }

	    public int winner() {
	        int rval = 0;
	        if(winner(1)) {
	            rval = 1;
	        }
	        else if(winner(2)) {
	            rval = 2;
	        }
	        return rval;
	    }

	    public boolean winner(int color) {
	        return ((grids[0].table[0][0] == color && grids[0].table[0][1] == color && grids[0].table[0][2] == color &&
	                 grids[1].table[0][0] == color && grids[1].table[0][1] == color) ||
	                (grids[0].table[0][1] == color && grids[0].table[0][2] == color && grids[1].table[0][0] == color &&
	                 grids[1].table[0][1] == color && grids[1].table[0][2] == color) ||
	                (grids[0].table[1][0] == color && grids[0].table[1][1] == color && grids[0].table[1][2] == color &&
	                 grids[1].table[1][0] == color && grids[1].table[1][1] == color) ||
	                (grids[0].table[1][1] == color && grids[0].table[1][2] == color && grids[1].table[1][0] == color &&
	                 grids[1].table[1][1] == color && grids[1].table[1][2] == color) ||
	                (grids[0].table[2][0] == color && grids[0].table[2][1] == color && grids[0].table[2][2] == color &&
	                 grids[1].table[2][0] == color && grids[1].table[2][1] == color) ||
	                (grids[0].table[2][1] == color && grids[0].table[2][2] == color && grids[1].table[2][0] == color &&
	                 grids[1].table[2][1] == color && grids[1].table[2][2] == color) ||
	                (grids[2].table[0][0] == color && grids[2].table[0][1] == color && grids[2].table[0][2] == color &&
	                 grids[3].table[0][0] == color && grids[3].table[0][1] == color) ||
	                (grids[2].table[0][1] == color && grids[2].table[0][2] == color && grids[3].table[0][0] == color &&
	                 grids[3].table[0][1] == color && grids[3].table[0][2] == color) ||
	                (grids[2].table[1][0] == color && grids[2].table[1][1] == color && grids[2].table[1][2] == color &&
	                 grids[3].table[1][0] == color && grids[3].table[1][1] == color) ||
	                (grids[2].table[1][1] == color && grids[2].table[1][2] == color && grids[3].table[1][0] == color &&
	                 grids[3].table[1][1] == color && grids[3].table[1][2] == color) ||
	                (grids[2].table[2][0] == color && grids[2].table[2][1] == color && grids[2].table[2][2] == color &&
	                 grids[3].table[2][0] == color && grids[3].table[2][1] == color) ||
	                (grids[2].table[2][1] == color && grids[2].table[2][2] == color && grids[3].table[2][0] == color &&
	                 grids[3].table[2][1] == color && grids[3].table[2][2] == color) ||
	                (grids[0].table[0][0] == color && grids[0].table[1][0] == color && grids[0].table[2][0] == color &&
	                 grids[2].table[0][0] == color && grids[2].table[1][0] == color) ||
	                (grids[0].table[0][1] == color && grids[0].table[1][1] == color && grids[0].table[2][1] == color &&
	                 grids[2].table[0][1] == color && grids[2].table[1][1] == color) ||
	                (grids[0].table[0][2] == color && grids[0].table[1][2] == color && grids[0].table[2][2] == color &&
	                 grids[2].table[0][2] == color && grids[2].table[1][2] == color) ||
	                (grids[1].table[0][0] == color && grids[1].table[1][0] == color && grids[1].table[2][0] == color &&
	                 grids[3].table[0][0] == color && grids[3].table[1][0] == color) ||
	                (grids[1].table[0][1] == color && grids[1].table[1][1] == color && grids[1].table[2][1] == color &&
	                 grids[3].table[0][1] == color && grids[3].table[1][1] == color) ||
	                (grids[1].table[0][2] == color && grids[1].table[1][2] == color && grids[1].table[2][2] == color &&
	                 grids[3].table[0][2] == color && grids[3].table[1][2] == color) ||
	                (grids[0].table[1][0] == color && grids[0].table[2][0] == color && grids[2].table[0][0] == color &&
	                 grids[2].table[1][0] == color && grids[2].table[2][0] == color) ||
	                (grids[0].table[1][1] == color && grids[0].table[2][1] == color && grids[2].table[0][1] == color &&
	                 grids[2].table[1][1] == color && grids[2].table[2][1] == color) ||
	                (grids[0].table[1][2] == color && grids[0].table[2][2] == color && grids[2].table[0][2] == color &&
	                 grids[2].table[1][2] == color && grids[2].table[2][2] == color) ||
	                (grids[1].table[1][0] == color && grids[1].table[2][0] == color && grids[3].table[0][0] == color &&
	                 grids[3].table[1][0] == color && grids[3].table[2][0] == color) ||
	                (grids[1].table[1][1] == color && grids[1].table[2][1] == color && grids[3].table[0][1] == color &&
	                 grids[3].table[1][1] == color && grids[3].table[2][1] == color) ||
	                (grids[1].table[1][2] == color && grids[1].table[2][2] == color && grids[3].table[0][2] == color &&
	                 grids[3].table[1][2] == color && grids[3].table[2][2] == color) ||
	                (grids[0].table[0][0] == color && grids[0].table[1][1] == color && grids[0].table[2][2] == color &&
	                 grids[3].table[0][0] == color && grids[3].table[1][1] == color) ||
	                (grids[0].table[0][1] == color && grids[0].table[1][2] == color && grids[1].table[2][0] == color &&
	                 grids[3].table[0][1] == color && grids[3].table[1][2] == color) ||
	                (grids[0].table[1][0] == color && grids[0].table[2][1] == color && grids[2].table[0][2] == color &&
	                 grids[3].table[1][0] == color && grids[3].table[2][1] == color) ||
	                (grids[0].table[1][1] == color && grids[0].table[2][2] == color && grids[3].table[0][0] == color &&
	                 grids[3].table[1][1] == color && grids[3].table[2][2] == color) ||
	                (grids[1].table[0][1] == color && grids[1].table[1][0] == color && grids[0].table[2][2] == color &&
	                 grids[2].table[0][1] == color && grids[2].table[1][0] == color) ||
	                (grids[1].table[0][2] == color && grids[1].table[1][1] == color && grids[1].table[2][0] == color &&
	                 grids[2].table[0][2] == color && grids[2].table[1][1] == color) ||
	                (grids[1].table[1][1] == color && grids[1].table[2][0] == color && grids[2].table[0][2] == color &&
	                 grids[2].table[1][1] == color && grids[2].table[2][0] == color) ||
	                (grids[1].table[1][2] == color && grids[1].table[2][1] == color && grids[3].table[0][0] == color &&
	                 grids[2].table[1][2] == color && grids[2].table[2][1] == color));
	    }

	    public void promptRotate(int i ,boolean wise) {
	        if(!gameOnGoing) {
	            System.out.println("End");
	            return;
	        }
	        if(!moveOrRotate) {
	            System.out.println("Time To Move");
	            return;
	        }
	        grids[i].rotate(wise);
	        synchronized(thread) {thread.notify();}
	    }

	    public void promptMove(int i ,int j) {
	        if(!gameOnGoing) {
	            System.out.println("End");
	            return;
	        }
	        if(moveOrRotate) {
	            System.out.println("Time To Rotation");
	            return;
	        }
	        if(!grids[(j / 3) + (i > 2 ? 2 : 0)].play(j % 3 ,i % 3 ,side)) {
	            System.out.println("Invalid Move");
	        }
	        else synchronized(thread) {
	            thread.notify();
	        }
	    }

	    class Grid {

	        private int[][] table = new int[3][3];


	        public boolean play(int x ,int y ,boolean side) {
	            if(table[y][x] == 0) {
	                table[y][x] = side ? 1 : 2;
	                return true;
	            }
	            return false;
	        }


	        public void rotate(boolean clockwise) {
	            if(clockwise) {
	                int temp = table[0][2];
	                table[0][2] = table[0][0];
	                table[0][0] = table[2][0];
	                table[2][0] = table[2][2];
	                table[2][2] = temp;
	                temp = table[1][2];
	                table[1][2] = table[0][1];
	                table[0][1] = table[1][0];
	                table[1][0] = table[2][1];
	                table[2][1] = temp;
	            }
	            else {
	                int temp = table[0][0];
	                table[0][0] = table[0][2];
	                table[0][2] = table[2][2];
	                table[2][2] = table[2][0];
	                table[2][0] = temp;
	                temp = table[0][1];
	                table[0][1] = table[1][2];
	                table[1][2] = table[2][1];
	                table[2][1] = table[1][0];
	                table[1][0] = temp;
	            }
	        }

	        public boolean isFull() {
	            return (table[0][0] != 0 && table[0][1] != 0 && table[0][2] != 0 && table[1][0] != 0 && table[1][1] != 0 &&
	                    table[1][2] != 0 && table[2][0] != 0 && table[2][1] != 0 && table[2][2] != 0);
	        }
	    }
	}
}
