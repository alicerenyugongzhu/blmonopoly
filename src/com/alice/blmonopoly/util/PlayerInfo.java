package com.alice.blmonopoly.util;

public class PlayerInfo {

    MapCell leftTop;
    MapCell rightBottom;
    MapCell nameStart;
    MapCell levelStart;
    MapCell careerStart;
    MapCell photoStart;
    
    final static float infoWidthX = 100;
    final static float infoWidthY = 100;
    final static float infoHeightX = 260;
    final static float infoHeightY = 200;
    final static float infoNameX = 120;
    final static float infoNameY = 120;
    final static float infoTextSize = 25;
    final static float infoPhotoWidth = 60;
    final static float infoPhotoHeight = 20;
    
    float textSize;
    float infoWidth;
    float infoHeight;
    float nameWidth;
    float nameHeight;
    
	public PlayerInfo(MapCell start, MapCell end, MapCell name, float textSize){
		leftTop = new MapCell();
		rightBottom = new MapCell();
		nameStart = new MapCell();
		levelStart = new MapCell();
		careerStart = new MapCell();
		
		leftTop = start;
		rightBottom = end;
		nameStart = name;
		this.textSize =textSize;  
		levelStart.setX(nameStart.getX());
		levelStart.setY(nameStart.getY() + textSize);
		careerStart.setX(nameStart.getX());
		careerStart.setY(nameStart.getY() + textSize*2);
		infoWidth = end.getX() - start.getX();
		infoHeight = end.getY() - start.getY();
		nameWidth = nameStart.getX() - leftTop.getX();
		nameHeight = nameStart.getY() - leftTop.getY();
	}
	
	public PlayerInfo(){
		leftTop = new MapCell();
		leftTop.setX(infoWidthX);
		leftTop.setY(infoWidthY);
		rightBottom = new MapCell();
		rightBottom.setX(infoHeightX);
		rightBottom.setY(infoHeightY);
		nameStart = new MapCell();
		nameStart.setX(infoNameX);
		nameStart.setY(infoNameY);
		textSize = infoTextSize;
		levelStart = new MapCell();
		levelStart.setX(nameStart.getX());
		levelStart.setY(nameStart.getY() + textSize);
		careerStart = new MapCell();
		careerStart.setX(nameStart.getX());
		careerStart.setY(nameStart.getY() + textSize*2);
		infoWidth = rightBottom.getX() - leftTop.getX();
		infoHeight = rightBottom.getY() - leftTop.getY();
		nameWidth = nameStart.getX() - leftTop.getX();
		nameHeight = nameStart.getY() - leftTop.getY();
	}
	
	public PlayerInfo(int textSize){
		leftTop = new MapCell();
		rightBottom = new MapCell();
		nameStart = new MapCell();
		levelStart = new MapCell();
		careerStart = new MapCell();
		
		leftTop.setX(infoWidthX);
		leftTop.setY(infoWidthY);
		rightBottom.setX(infoHeightX);
		rightBottom.setY(infoHeightY);
		nameStart.setX(infoNameX);
		nameStart.setY(infoNameY);
		this.textSize = textSize;
		levelStart.setX(nameStart.getX());
		levelStart.setY(nameStart.getY() + textSize);
		careerStart.setX(nameStart.getX());
		careerStart.setY(nameStart.getY() + textSize*2);
		infoWidth = rightBottom.getX() - leftTop.getX();
		infoHeight = rightBottom.getY() - leftTop.getY();
		nameWidth = nameStart.getX() - leftTop.getX();
		nameHeight = nameStart.getY() - leftTop.getY();
	}
	
	public void update(MapCell leftTop){
		this.leftTop = leftTop;
		rightBottom.setX(this.leftTop.getX() + infoWidth);
		rightBottom.setY(this.leftTop.getY() + infoHeight);
		nameStart.setX(this.leftTop.getX() + nameWidth);
		nameStart.setY(this.leftTop.getY() + nameHeight);
		levelStart.setX(nameStart.getX());
		levelStart.setY(nameStart.getY() + textSize);
		careerStart.setX(nameStart.getX());
		careerStart.setY(nameStart.getY() + textSize*2);
	}
	
	public void setLeftTop(MapCell start){
		this.leftTop = start;
	}
	
	public MapCell getLeftTop(){
		return leftTop;
	}
	
	public void setRightBottom(MapCell end){
		this.rightBottom = end;
	}
	
	public MapCell getRightBottom(){
		return rightBottom;
	}
	
	public void setNameStart(MapCell nameStart){
		this.nameStart = nameStart;
	}
	
	public MapCell getNameStart(){
		return nameStart;
	}
	
	public void setTextSize(int textSize){
		this.textSize = textSize;
	}
	
	public float getTextSize(){
		return textSize;
	}
	
	public void setLevelStart(MapCell levelStart){
		this.levelStart = levelStart;
	}
	
	public MapCell getLevelStart(){
		return levelStart;
	}
	
	public void setCareerStart(MapCell careerStart){
		this.careerStart = careerStart;
	}
	
	public MapCell getCareerStart(){
		return careerStart;
	}
	
	public float getInfoWidth(){
		return infoWidth;
	}
	
	public float getInfoHeight(){
		return infoHeight;
	}
	
	public float getNameWidth(){
		return nameWidth;
	}
	
	public float getNameHeight(){
		return nameHeight;
	}
}