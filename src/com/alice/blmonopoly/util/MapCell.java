package com.alice.blmonopoly.util;

import com.alice.blmonopoly.util.GameInfo.gameEvent;

public class MapCell {
    private float x;
    private float y;
    
    private gameEvent event;
 
    public MapCell(){
    	
    }
    
    public MapCell(float x, float y, gameEvent event){
    	this.x = x;
    	this.y = y;
    	this.event = event;
    }
    
    public void setX(float x){
    	this.x = x;
    }
    
    public float getX(){
    	return x;
    }
    
    public void setY(float y){
    	this.y = y;
    }
    
    public float getY(){
    	return y;
    }
    
    public void setEvent(gameEvent event){
    	this.event = event;
    }
    
    public gameEvent getEvent(){
    	return event;
    }
    
}
