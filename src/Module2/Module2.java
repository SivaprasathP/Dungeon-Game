package com.dungeonGame.module2;

public class Module2 {
    public static void main(String[] args) {
        int girdRow=5;
        int gridCol=4;

        int[][] grid=new int[girdRow][gridCol];

        int positionXOfAdventurer=5;
        int positionYOfAdventurer=1;

        int positionXOfMonster=3;
        int positionYOfMonster=1;

        int positionXOfGold=4;
        int positionYOfGold=3;

        int stepsForAdventurer=findSteps(grid,positionXOfAdventurer-1,positionYOfAdventurer-1,positionXOfGold-1,positionYOfGold-1,0);
        int stepsForMonster=findSteps(grid,positionXOfMonster-1,positionYOfMonster-1,positionXOfGold-1,positionYOfGold-1,0);

        if(stepsForAdventurer>stepsForMonster){
            System.out.println("No Possible Solution");
        }else{
            System.out.println("Minimum number of steps: "+stepsForAdventurer);
        }
    }

    private static int findSteps(int[][] grid, int x, int y, int gX, int gY,int steps) {
        if(x==gX && y==gY){
            return steps;
        }

        if(grid[x][y]==-1){
            return Integer.MAX_VALUE;
        }

        grid[x][y]=-1;

        int top=Integer.MAX_VALUE;
        if(x>0){
            top=findSteps(grid,x-1,y,gX,gY,steps+1);
        }

        int down=Integer.MAX_VALUE;
        if(x<grid.length-1){
            down=findSteps(grid,x+1,y,gX,gY,steps+1);
        }

        int left=Integer.MAX_VALUE;
        if(y>0){
            left=findSteps(grid,x,y-1,gX,gY,steps+1);
        }

        int right=Integer.MAX_VALUE;
        if(y<grid[0].length-1){
            right=findSteps(grid,x,y+1,gX,gY,steps+1);
        }

        grid[x][y]=0;

        int min=Math.min(Math.min(top,down),Math.min(left,right));

        return min;
    }
}
