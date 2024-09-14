package com.dungeonGame.module5;

import java.util.ArrayList;
import java.util.List;

public class Module5 {
    public static void main(String[] args) {
        int girdRow=5;
        int gridCol=4;

        int[][] grid=new int[girdRow][gridCol];
        grid[1][0]=-1;
        grid[3][2]=-1;
        grid[2][0]=-1;
        grid[4][0]=-1;
        grid[3][1]=-1;
        grid[2][2]=-1;

        int positionXOfAdventurer=4;
        int positionYOfAdventurer=1;

        int positionXOfGold=2;
        int positionYOfGold=3;

        List<String> adventurerPath=new ArrayList<>();

        int stepsForAdventurer=findSteps(grid,positionXOfAdventurer-1,positionYOfAdventurer-1,positionXOfGold-1,positionYOfGold-1,0,new ArrayList<>(),adventurerPath);

        if(stepsForAdventurer==Integer.MAX_VALUE){
            System.out.println("No Possible paths");
        }else{
            System.out.println("Steps to Reach the Gold: "+ stepsForAdventurer);
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
