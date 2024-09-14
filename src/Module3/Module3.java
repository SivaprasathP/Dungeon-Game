package com.dungeonGame.module3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module3 {
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

        List<String > adventurerPath=new ArrayList<>();
        List<String> monsterPath=new ArrayList<>();

        int stepsForAdventurer=findSteps(grid,positionXOfAdventurer-1,positionYOfAdventurer-1,positionXOfGold-1,positionYOfGold-1,0,new ArrayList<>(),adventurerPath);
        int stepsForMonster=findSteps(grid,positionXOfMonster-1,positionYOfMonster-1,positionXOfGold-1,positionYOfGold-1,0,new ArrayList<>(),monsterPath);

        if(stepsForAdventurer>stepsForMonster){
            System.out.println("No Possible Solution");
        }else{
            System.out.println("Minimum number of steps: "+stepsForAdventurer);
            System.out.println(adventurerPath);
        }
    }

    private static int findSteps(int[][] grid, int x, int y, int gX, int gY,int steps,List<String> currPath,List<String> minPath) {
        if(x==gX && y==gY){
            currPath.add("("+(x+1)+" "+(y+1)+")");
            if(minPath.isEmpty() || currPath.size()<minPath.size()){
                minPath.clear();
                minPath.addAll(new ArrayList<>(currPath));
            }
            currPath.removeLast();
            return steps;
        }

        if(grid[x][y]==-1){
            return Integer.MAX_VALUE;
        }

        grid[x][y]=-1;
        currPath.add("("+(x+1)+","+(y+1)+")");


        int top=Integer.MAX_VALUE;
        if(x>0){
            top=findSteps(grid,x-1,y,gX,gY,steps+1,currPath,minPath);
        }

        int down=Integer.MAX_VALUE;
        if(x<grid.length-1){
            down=findSteps(grid,x+1,y,gX,gY,steps+1,currPath,minPath);
        }

        int left=Integer.MAX_VALUE;
        if(y>0){
            left=findSteps(grid,x,y-1,gX,gY,steps+1,currPath,minPath);
        }

        int right=Integer.MAX_VALUE;
        if(y<grid[0].length-1){
            right=findSteps(grid,x,y+1,gX,gY,steps+1,currPath,minPath);
        }

        grid[x][y]=0;
        currPath.remove(currPath.size()-1);

        int min=Math.min(Math.min(top,down),Math.min(left,right));

        return min;
    }
}
