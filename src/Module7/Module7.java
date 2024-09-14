package com.dungeonGame.module7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Module7 {
    public static void main(String[] args) {
        int girdRow=5;
        int gridCol=4;

        int[][] grid=new int[girdRow][gridCol];
        grid[1][0]=-2;
        grid[3][1]=-2;
        grid[3][2]=-2;

        int positionXOfAdventurer=4;
        int positionYOfAdventurer=1;

        int positionXOfMonster=1;
        int positionYOfMonster=1;

        int positionXOfGold=1;
        int positionYOfGold=3;

        int positionXOfTrigger=4;
        int positionYOfTrigger=4;

        List<String> adventurerPath=new ArrayList<>();
        List<String> monsterPath=new ArrayList<>();


        int stepsForAdventurer=findSteps(grid,positionXOfAdventurer-1,positionYOfAdventurer-1,positionXOfGold-1,positionYOfGold-1,0,new ArrayList<>(),adventurerPath,false);
        int stepsForMonster=findSteps(grid,positionXOfMonster-1,positionYOfMonster-1,positionXOfGold-1,positionYOfGold-1,0,new ArrayList<>(),monsterPath,true);


        if(stepsForAdventurer==Integer.MAX_VALUE){
            System.out.println("No Possible paths");
        }else if(stepsForAdventurer>stepsForMonster){
            List<String> triggerpath=new ArrayList<>();
            int adventurerToTrigger=findSteps(grid,positionXOfAdventurer-1,positionYOfAdventurer-1,positionXOfTrigger-1,positionYOfTrigger-1,0,new ArrayList<>(),triggerpath,false);

            List<String> triggerMonster=new ArrayList<>();
            int monsterToTrigger=findSteps(grid,positionXOfMonster-1,positionYOfMonster-1,positionXOfTrigger-1,positionYOfTrigger-1,0,new ArrayList<>(),triggerMonster,true);


            if(adventurerToTrigger<=monsterToTrigger){
                adventurerPath=new ArrayList<>();
                stepsForAdventurer=findSteps(grid,positionXOfTrigger-1,positionYOfTrigger-1,positionXOfGold-1,positionYOfGold-1,0,new ArrayList<>(),adventurerPath,false);

                System.out.println("Minimum number of steps : "+(adventurerToTrigger+stepsForAdventurer));
                adventurerPath.remove(0);
                triggerpath.addAll(adventurerPath);
                System.out.println(triggerpath);
            }else {
                System.out.println("No Possible Solution");
            }
        }else{
            System.out.println("Minimum number of steps: "+stepsForAdventurer);
            System.out.println(adventurerPath);
        }
    }

    private static int findSteps(int[][] grid, int x, int y, int gX, int gY,int steps,List<String> currPath,List<String> minPath,boolean isMonster) {
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

        if(!isMonster && grid[x][y]==-2){
            return Integer.MAX_VALUE;
        }
        int temp = grid[x][y];
        grid[x][y]=-1;
        currPath.add("("+(x+1)+","+(y+1)+")");


        int top=Integer.MAX_VALUE;
        if(x>0){
            top=findSteps(grid,x-1,y,gX,gY,steps+1,currPath,minPath,isMonster);
        }

        int down=Integer.MAX_VALUE;
        if(x<grid.length-1){
            down=findSteps(grid,x+1,y,gX,gY,steps+1,currPath,minPath,isMonster);
        }

        int left=Integer.MAX_VALUE;
        if(y>0){
            left=findSteps(grid,x,y-1,gX,gY,steps+1,currPath,minPath,isMonster);
        }

        int right=Integer.MAX_VALUE;
        if(y<grid[0].length-1){
            right=findSteps(grid,x,y+1,gX,gY,steps+1,currPath,minPath,isMonster);
        }

        grid[x][y]= temp;
        currPath.remove(currPath.size()-1);

        int min=Math.min(Math.min(top,down),Math.min(left,right));

        return min;
    }
}
