package LoadMap;

public class Map {
	private int[][] levelData;
    
    Map(int[][] levelData2){
    	
    	this.levelData = levelData2;
    }
    
    
    public int get_levelData_index(int x, int y) {
	    return levelData[y][x];
   }

    public int[][] get_levelData() {
    	return levelData;
    }
    
}
