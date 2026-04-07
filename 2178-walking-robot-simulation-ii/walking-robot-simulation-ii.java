class Robot {

    int w, h;
    int x, y;
    int dir; // 0=East, 1=North, 2=West, 3=South
    int cycle;
    
    int[][] moves = {
        {1, 0},   // East
        {0, 1},   // North
        {-1, 0},  // West
        {0, -1}   // South
    };
    
    String[] directions = {"East", "North", "West", "South"};

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
        this.x = 0;
        this.y = 0;
        this.dir = 0; // East
        this.cycle = 2 * (width + height) - 4;
    }
    
    public void step(int num) {
        num %= cycle;
        
        // Special case
        if (num == 0) {
            if (x == 0 && y == 0) {
                dir = 3; // South
            }
            return;
        }
        
        while (num > 0) {
            int nx = x + moves[dir][0];
            int ny = y + moves[dir][1];
            
            // Out of bounds → turn
            if (nx < 0 || nx >= w || ny < 0 || ny >= h) {
                dir = (dir + 1) % 4;
                continue;
            }
            
            // Move
            x = nx;
            y = ny;
            num--;
        }
    }
    
    public int[] getPos() {
        return new int[]{x, y};
    }
    
    public String getDir() {
        return directions[dir];
    }
}