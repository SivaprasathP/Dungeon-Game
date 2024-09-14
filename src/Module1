package com.dungeonGame.module1;

public class Module1 {
    public static void main(String[] args) {
        int girdRow=5;
        int gridCol=4;

        int[][] grid=new int[girdRow][gridCol];

        int positionXOfAdventurer=5;
        int positionYOfAdventurer=1;

        int positionXOfGold=1;
        int positionYOfGold=4;

        int minSteps=findSteps(grid,positionXOfAdventurer-1,positionYOfAdventurer-1,positionXOfGold-1,positionYOfGold-1,0);

        System.out.println(minSteps);
    }

    private static int findSteps(int[][] grid, int aX, int aY, int gX, int gY,int steps) {
        if(aX==gX && aY==gY){
            return steps;
        }

        if(grid[aX][aY]==-1){
            return Integer.MAX_VALUE;
        }

        grid[aX][aY]=-1;

        int top=Integer.MAX_VALUE;
        if(aX>0){
            top=findSteps(grid,aX-1,aY,gX,gY,steps+1);
        }

        int down=Integer.MAX_VALUE;
        if(aX<grid.length-1){
            down=findSteps(grid,aX+1,aY,gX,gY,steps+1);
        }

        int left=Integer.MAX_VALUE;
        if(aY>0){
            left=findSteps(grid,aX,aY-1,gX,gY,steps+1);
        }

        int right=Integer.MAX_VALUE;
        if(aY<grid[0].length-1){
            right=findSteps(grid,aX,aY+1,gX,gY,steps+1);
        }

        grid[aX][aY]=0;

        int min=Math.min(Math.min(top,down),Math.min(left,right));

        return min;
    }
}
