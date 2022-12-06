import java.util.HashSet;
import java.util.Set;

/* Both Player and Ghost inherit Mover.  Has generic functions relevant to both*/
class Mover {
    /* Framecount is used to count animation frames*/
    int frameCount = 0;

    /* State contains the game map */
    boolean[][] state;

    /* gridSize is the size of one square in the game.
       max is the height/width of the game.
       increment is the speed at which the object moves,
       1 increment per move() call */
    int gridSize;
    int max;
    int increment;
    /* Direction ghost is heading */
    char direction;
    /* Current ghost location */
    int x;
    int y;
    /* Last ghost location*/
    int lastX;
    int lastY;
    /* The pellet the ghost is on top of */
    int pelletX;
    int pelletY;

    /* Generic constructor */
    public Mover(int x, int y) {
        gridSize = 20;
        increment = 4;
        max = 400;
        state = new boolean[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                state[i][j] = false;
            }
        }
        direction = 'L';
        this.x = x;
        this.y = y;
        this.lastX = x;
        this.lastY = y;
        pelletX = x / gridSize - 1;
        pelletY = y / gridSize - 1;
    }

    /* Updates the state information */
    public void updateState(boolean[][] state) {
        for (int i = 0; i < 20; i++) {
            System.arraycopy(state[i], 0, this.state[i], 0, 20);
        }
    }

    /* Determines if a set of coordinates is a valid destination.*/
    public boolean isValidDest(int x, int y) {
    /* The first statements check that the x and y are inbounds.  The last statement checks the map to
       see if it's a valid location */
        return (((x) % 20 == 0) || ((y) % 20) == 0) && 20 <= x && x < 400 && 20 <= y && y < 400 && state[x / 20 - 1][y / 20 - 1];
    }

    /* Calculate newDirection for Demo and Ghost */
    public char newDirection() {
        int random;
        char backwards = 'U';
        int newX = x, newY = y;
        int lookX = x, lookY = y;
        Set<Character> set = new HashSet<Character>();
        switch (direction) {
            case 'L':
                backwards = 'R';
                break;
            case 'R':
                backwards = 'L';
                break;
            case 'U':
                backwards = 'D';
                break;
            case 'D':
                backwards = 'U';
                break;
        }
        char newDirection = backwards;
        while (newDirection == backwards || !isValidDest(lookX, lookY)) {
            if (set.size() == 3) {
                newDirection = backwards;
                break;
            }
            newX = x;
            newY = y;
            lookX = x;
            lookY = y;
            random = (int) (Math.random() * 4) + 1;
            if (random == 1) {
                newDirection = 'L';
                newX -= increment;
                lookX -= increment;
            } else if (random == 2) {
                newDirection = 'R';
                newX += increment;
                lookX += gridSize;
            } else if (random == 3) {
                newDirection = 'U';
                newY -= increment;
                lookY -= increment;
            } else if (random == 4) {
                newDirection = 'D';
                newY += increment;
                lookY += gridSize;
            }
            if (newDirection != backwards) {
                set.add(new Character(newDirection));
            }
        }
        return newDirection;
    }
}
