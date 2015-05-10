package com.alice.blmonopoly;

import java.util.ArrayList;
import java.util.Random;

import com.alice.blmonopoly.util.GameInfo;
import com.alice.blmonopoly.util.GameInfo.Career;
import com.alice.blmonopoly.util.GameInfo.StoryFriend;
import com.alice.blmonopoly.util.GameInfo.gameCharacter;
import com.alice.blmonopoly.util.GameInfo.gameEvent;
import com.alice.blmonopoly.util.MapCell;
import com.alice.blmonopoly.util.PlayerInfo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends Activity{

	//Position of Qiuren
	float playerX;
	float playerY;
	
	//Max width
	Integer screenWidth;
	//Max height
	Integer screenHeight;
	
	//Random seed
	Random random;
	
	//cube number
	final int CUBE_NUMBER_X = 10;
	final int CUBE_NUMBER_Y = 20;
	
	//step cube
	int cubeWidth;
	int cubeHeight;
	
	//player Info
	PlayerInfo showPlayerInfo;
	
	//Game info
	GameInfo gameInfo;
	MapCell[] mapCell;
	MapCell startMapCell;
	MapCell endMapCell;
	
	//Game stage
	boolean gameEnd = false;
	
	//Move the info position
	boolean withinPlayerInfo = false;
	
	//Delta position for the player Info
	float deltaWidth;
	float deltaHeight;
	
	//Map Event
	int[] defaultEventNumber = {1, //start
			10, //make friends with characters
			5, //change career
			30, //date
			40, //lucky draw
			5, //move forward
			5, //move back
			10, //task
			1, //end
			};
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		InitGame();
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		DrawGameView gameView = new DrawGameView(this);
		setContentView(gameView);
	    
	}
	
	private void InitGame() {
		//Init map background
		DisplayMetrics dm =getResources().getDisplayMetrics();
		screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
		cubeWidth = screenWidth/CUBE_NUMBER_X;
		cubeHeight = screenHeight/CUBE_NUMBER_Y;
		Log.d("alice", "cubeWidth is " + cubeWidth + " cubeHeight is " + cubeHeight);
		
        //Init character info
		
		gameInfo = new GameInfo();
		gameInfo.getRandomPlayer();
		Log.d("alice","character name is " + gameInfo.getPlayerName());
		gameInfo.setMapEventNumber(defaultEventNumber);
		
		//Map Event initization
		startMapCell = new MapCell(0, 0, gameEvent.START);
		endMapCell = new MapCell(((CUBE_NUMBER_Y%2)>0 ? CUBE_NUMBER_X-1 : 0), CUBE_NUMBER_Y-1, gameEvent.END);
		int eventAmount = gameInfo.getMakeFriendNumber() +
				gameInfo.getChangeCareerNumber() + gameInfo.getDateNumber() +
				gameInfo.getLuckyDrawNumber() + gameInfo.getTaskNumber() +
				gameInfo.getMoveForwardNumber() + gameInfo.getMoveBackNumber();
		int[] range = new int[2];
		range[0] = CUBE_NUMBER_X;
		range[1] = CUBE_NUMBER_Y;
		mapCell = new MapCell[eventAmount];
		mapCell = getRandArray(range, eventAmount);
		int i, j=0;
		for(i = 0; i< gameInfo.getMakeFriendNumber(); i++){
			mapCell[j++].setEvent(gameEvent.MAKE_FRIEND);
		}
		for(i = 0;i<gameInfo.getChangeCareerNumber(); i++){
			mapCell[j++].setEvent(gameEvent.CHANGE_CAREER);
		}
		for(i = 0; i<gameInfo.getDateNumber(); i++){
			mapCell[j++].setEvent(gameEvent.DATE);
		}
		for(i = 0; i<gameInfo.getLuckyDrawNumber(); i++){
			mapCell[j++].setEvent(gameEvent.LUCKY_DRAW);
		}
		for(i = 0; i<gameInfo.getMoveBackNumber(); i++){
			mapCell[j++].setEvent(gameEvent.MOVE_BACK);
		}
		for(i = 0; i<gameInfo.getMoveForwardNumber(); i++){
			mapCell[j++].setEvent(gameEvent.MOVE_FORWARD);
		}
		for(i = 0; i<gameInfo.getTaskNumber(); i++){
			mapCell[j++].setEvent(gameEvent.TASK);
		}
		
		//Init position
		playerX = startMapCell.getX(); //Start position
		playerY = startMapCell.getY(); //Start position	
		
		//Init info position
		showPlayerInfo = new PlayerInfo();
		//Log.d("alice", "get x is " +showPlayerInfo.getLeftTop().getX());
	}
	
	public MapCell[] getRandArray(int range[], int amount){
		Random rand = new Random();
		MapCell[] randArray = new MapCell[amount];
		boolean[][] dupCheck = new boolean[range[0]][range[1]];
		dupCheck[0][0] = true;
		dupCheck[range[0]-1][range[1]-1] = true;
		for(int i = 0; i < amount; i++){
			randArray[i] = new MapCell();
			int randXValue= 0;
			int randYValue= 0;
			do{
				randXValue = rand.nextInt(range[0]);
				randYValue = rand.nextInt(range[1]);
			}while(dupCheck[randXValue][randYValue]);
			dupCheck[randXValue][randYValue] = true;
			//Log.d("alice", "i = " + i + " X is " + randXValue + " Y is " + randYValue);
			randArray[i].setX(randXValue);
			randArray[i].setY(randYValue);
		}
		
		return randArray;
	}

	public class DrawGameView extends View{
		Paint mapPaint = null;
		Bitmap player = null;
		Paint playerPaint = null;
		Paint playerInfo = null;
		Bitmap playerNew = null;
		Paint playerInfoBg = null;
		Paint startPaint = null;
		Paint makeFriendsPaint = null;
		Paint changeCareerPaint = null;
		Paint datePaint = null;
		Paint luckyDrawPaint = null;
		Paint moveForwardPaint = null;
		Paint moveBackPaint = null;
		Paint taskPaint = null;
		Paint endPaint = null;
		Bitmap playerPhoto = null;
				
		public DrawGameView(Context context) {
			super(context);
			InitMap();
			InitCharactor();
			InitPicScale();
		}
		
		private void InitPicScale() {
			Matrix matrix = new Matrix();
			matrix.reset();
			player = ((BitmapDrawable)getResources().getDrawable(R.drawable.qmove)).getBitmap();
			float scaleX = (float)cubeWidth/(float)(player.getWidth());
			float scaleY = (float)cubeHeight/(float)player.getHeight();

			Log.d("alice", "scaleX " + scaleX );
			Log.d("alice", "player width " + player.getWidth());
			matrix.postScale(scaleX, scaleY);
			
			playerNew = Bitmap.createBitmap(player, 0, 0, player.getWidth(), player.getHeight(), matrix, true);
		}

		private void InitCharactor() {
			Log.d("alice", "init playerInfoBg and playerInfo");
			playerInfoBg = new Paint();
			playerInfoBg.setAlpha(50);
			playerInfoBg.setColor(Color.WHITE);
			playerInfo = new Paint();
			playerInfo.setColor(Color.BLACK);
			playerInfo.setTextSize(showPlayerInfo.getTextSize() - 15);
			playerPhoto = ((BitmapDrawable)getResources().getDrawable(gameInfo.getPlayerPhoto())).getBitmap();
		}

		private void InitMap() {

			mapPaint = new Paint();
			mapPaint.setColor(Color.BLUE);
			mapPaint.setStyle(Style.STROKE);

			//event distribution
			startPaint = new Paint();
			startPaint.setColor(0xFFAED581);  //Light green
			startPaint.setStyle(Style.FILL);
			
			endPaint = new Paint();
			endPaint.setColor(0xFFFFCDD2);    //Light red
			endPaint.setStyle(Style.FILL);
			
			makeFriendsPaint = new Paint();
			makeFriendsPaint.setColor(0xFFE91E63);  //Pink
			makeFriendsPaint.setStyle(Style.FILL);
			
			changeCareerPaint = new Paint();
			changeCareerPaint.setColor(0xFF9C27B0);  //Purple
			changeCareerPaint.setStyle(Style.FILL);
			
			datePaint = new Paint();
			datePaint.setColor(0xFFB39DDB);  //Light Purple
			datePaint.setStyle(Style.FILL);
			
			luckyDrawPaint = new Paint();
			luckyDrawPaint.setColor(0xFF69F0AE);  //BlueGreen
			luckyDrawPaint.setStyle(Style.FILL);
			
			moveForwardPaint = new Paint();
			moveForwardPaint.setColor(0xFFFFEB3B);  //Yellow
			moveForwardPaint.setStyle(Style.FILL);
			
			moveBackPaint = new Paint();
			moveBackPaint.setColor(0xFFFF5722);  //Orange
			moveBackPaint.setStyle(Style.FILL);
			
			taskPaint = new Paint();
			taskPaint.setColor(0xFF795548);  //Brown
			taskPaint.setStyle(Style.FILL);

		}

		@Override
		public void onDraw(Canvas canvas){
			//Reset the canvas
			canvas.drawColor(Color.BLACK);
			DrawCube(canvas);
			canvas.drawBitmap(playerNew, playerX, playerY, mapPaint);
		//	Log.d("alice", "I am here to call draw player");
			DrawPlayerInfo(canvas);
			
		}

		private void DrawCube(Canvas canvas) {
			float rx = cubeWidth/5;
			float ry = cubeHeight/5;
			RectF rect = new RectF();
			for(int i = 0; i < CUBE_NUMBER_X; i++){
				for(int j = 0; j < CUBE_NUMBER_Y; j++){
					rect.left = i*cubeWidth;
					rect.top = j*cubeHeight;
					rect.right = i*cubeWidth + cubeWidth;
					rect.bottom = j*cubeHeight + cubeHeight;
					//Log.d("alice", "draw rect at i = " + i + " j = " + j);
					canvas.drawRoundRect(rect, rx, ry, mapPaint);
				}
			}
			rect.left = startMapCell.getX()*cubeWidth;
			rect.top = startMapCell.getY()*cubeHeight;
			rect.right = startMapCell.getX()*cubeWidth + cubeWidth;
			rect.bottom = startMapCell.getY()*cubeHeight + cubeHeight;
			canvas.drawRoundRect(rect, rx, ry, startPaint);
		//	Log.d("alice", "the position is " + startMapCell.getX() + " " + startMapCell.getY());
			
			rect.left = endMapCell.getX()*cubeWidth;
			rect.top = endMapCell.getY()*cubeHeight;
			rect.right = endMapCell.getX()*cubeWidth + cubeWidth;
			rect.bottom = endMapCell.getY()*cubeHeight + cubeHeight;
		//	Log.d("alice", "the position is " + endMapCell.getX() + " " + endMapCell.getY());
			canvas.drawRoundRect(rect, rx, ry, endPaint);
			
			int i, j = 0;
			for(i = 0; i< gameInfo.getMakeFriendNumber(); i++){
				rect.left = mapCell[j].getX()*cubeWidth;
				rect.top = mapCell[j].getY()*cubeHeight;
				rect.right = mapCell[j].getX()*cubeWidth + cubeWidth;
				rect.bottom = mapCell[j++].getY()*cubeHeight + cubeHeight;
				canvas.drawRoundRect(rect, rx, ry, makeFriendsPaint);
			}
			for(i = 0;i<gameInfo.getChangeCareerNumber(); i++){
				rect.left = mapCell[j].getX()*cubeWidth;
				rect.top = mapCell[j].getY()*cubeHeight;
				rect.right = mapCell[j].getX()*cubeWidth + cubeWidth;
				rect.bottom = mapCell[j++].getY()*cubeHeight + cubeHeight;
				canvas.drawRoundRect(rect, rx, ry, changeCareerPaint);
			}
			for(i = 0; i<gameInfo.getDateNumber(); i++){
				rect.left = mapCell[j].getX()*cubeWidth;
				rect.top = mapCell[j].getY()*cubeHeight;
				rect.right = mapCell[j].getX()*cubeWidth + cubeWidth;
				rect.bottom = mapCell[j++].getY()*cubeHeight + cubeHeight;
				canvas.drawRoundRect(rect, rx, ry, datePaint);
			}
			for(i = 0; i<gameInfo.getLuckyDrawNumber(); i++){
				rect.left = mapCell[j].getX()*cubeWidth;
				rect.top = mapCell[j].getY()*cubeHeight;
				rect.right = mapCell[j].getX()*cubeWidth + cubeWidth;
				rect.bottom = mapCell[j++].getY()*cubeHeight + cubeHeight;
				canvas.drawRoundRect(rect, rx, ry, luckyDrawPaint);
			}
			for(i = 0; i<gameInfo.getMoveBackNumber(); i++){
				rect.left = mapCell[j].getX()*cubeWidth;
				rect.top = mapCell[j].getY()*cubeHeight;
				rect.right = mapCell[j].getX()*cubeWidth + cubeWidth;
				rect.bottom = mapCell[j++].getY()*cubeHeight + cubeHeight;
				canvas.drawRoundRect(rect, rx, ry, moveBackPaint);
			}
			for(i = 0; i<gameInfo.getMoveForwardNumber(); i++){
				rect.left = mapCell[j].getX()*cubeWidth;
				rect.top = mapCell[j].getY()*cubeHeight;
				rect.right = mapCell[j].getX()*cubeWidth + cubeWidth;
				rect.bottom = mapCell[j++].getY()*cubeHeight + cubeHeight;
				canvas.drawRoundRect(rect, rx, ry, moveForwardPaint);
			}
			for(i = 0; i<gameInfo.getTaskNumber(); i++){
				rect.left = mapCell[j].getX()*cubeWidth;
				rect.top = mapCell[j].getY()*cubeHeight;
				rect.right = mapCell[j].getX()*cubeWidth + cubeWidth;
				rect.bottom = mapCell[j++].getY()*cubeHeight + cubeHeight;
				canvas.drawRoundRect(rect, rx, ry, taskPaint);
			}
			
		}

		private void DrawPlayerInfo(Canvas canvas) {
			//Log.d("alice","leftTop X is " + showPlayerInfo.getLeftTop().getX());
			//Log.d("alice", "leftTop y is " + showPlayerInfo.getLeftTop().getY());
			//Log.d("alice", "rightBottom x is " + showPlayerInfo.getRightBottom().getX());
			//Log.d("alice","rightBottom y is " + showPlayerInfo.getRightBottom().getY());
			canvas.drawRect(showPlayerInfo.getLeftTop().getX(), showPlayerInfo.getLeftTop().getY(), 
					showPlayerInfo.getRightBottom().getX(), showPlayerInfo.getRightBottom().getY(), playerInfoBg);
			canvas.drawText(gameInfo.getPlayerName(), showPlayerInfo.getNameStart().getX(), 
					showPlayerInfo.getNameStart().getY(), playerInfo);
			String level = getString(com.alice.blmonopoly.R.string.level) + " " + gameInfo.getPlayerGValue();
			canvas.drawText(level, showPlayerInfo.getLevelStart().getX(), 
					showPlayerInfo.getLevelStart().getY(), playerInfo);
			canvas.drawText(gameInfo.getPlayerCareer().getCareerName(), showPlayerInfo.getCareerStart().getX(),
					showPlayerInfo.getCareerStart().getY(), playerInfo);	
			//Rect rect = new Rect();
			
			canvas.drawBitmap(playerPhoto, 
					showPlayerInfo.getLeftTop().getX() + showPlayerInfo.getInfoWidth()/2, 
					showPlayerInfo.getLeftTop().getY(), playerInfo);
		}


		@Override
		public boolean onTouchEvent(MotionEvent event){
			if(gameEnd){
				GameEnd();
				return false;
			}
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				if(event.getX() < showPlayerInfo.getLeftTop().getX() ||
						event.getX() > showPlayerInfo.getRightBottom().getX() ||
						event.getY() < showPlayerInfo.getLeftTop().getY() ||
						event.getY() > showPlayerInfo.getRightBottom().getY()){
					random = new Random();
				    Integer diceValue = random.nextInt(6)+1;
				    gameEnd = updatePosition(diceValue);
				    
				} else {
					withinPlayerInfo = true;
					deltaWidth = event.getX() - showPlayerInfo.getLeftTop().getX();
					deltaHeight = event.getY() - showPlayerInfo.getLeftTop().getY();
				}
			} else if(event.getAction() == MotionEvent.ACTION_UP){
				//Log.d("alice", "I am in ACTION_UP");
				
				if(!withinPlayerInfo)
					CompareEvent();
				else {
					Log.d("alice", "I am in the player info. Need do something");
					withinPlayerInfo = false;
				}
			} else if(event.getAction() == MotionEvent.ACTION_MOVE){
				if(withinPlayerInfo) {
					MapCell temp = new MapCell();
					temp.setX(event.getX() - deltaWidth);
					temp.setY(event.getY() - deltaHeight);
					showPlayerInfo.update(temp);
					//temp.setX(temp.getX() + showPlayerInfo.getInfoWidth());
					//temp.setY(temp.getY() + showPlayerInfo.getInfoHeight());
					//showPlayerInfo.setRightBottom(temp);
				}
			}
			invalidate();
			return true;
		}
		
	}
	
	private void GameEnd() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean updatePosition(int step) {
        boolean end = false;
        //Log.d("alice", "step is " + step);
			
		if((CUBE_NUMBER_Y%2) == 1){
			if(((playerX + cubeWidth*step) >= (screenWidth-cubeWidth)) && (playerY + cubeHeight) == screenHeight) {
				playerX = screenWidth - cubeWidth;
				playerY = screenHeight - cubeHeight;
				end = true;
				return end;
			}
		} else {
			if(((playerX - cubeWidth*step) <= 0) &&  (playerY + cubeHeight) == screenHeight){
				playerX = 0;
				playerY = screenHeight - cubeHeight;
				end = true;
				return end;
			}
		}
			
			
		//}

		if((playerY/cubeHeight)%2 == 0){
			if((playerX + cubeWidth*step) < screenWidth){
				playerX = playerX + cubeWidth*step;
			} else {
				playerX = 2*screenWidth - cubeWidth*(step+1) - playerX;
				playerY = playerY + cubeHeight;
			}
		} else {
			if((playerX - cubeWidth*step) >= 0){
				playerX = playerX - cubeWidth*step;
			} else {
				playerX = cubeWidth*(step-1) - playerX; 
				playerY = playerY + cubeHeight;
			}
		}
		return end;
		
	}
	
	private void CompareEvent() {
		boolean find = false;
		int i, j = 0;
		for(i = 0; i< gameInfo.getMakeFriendNumber() && find != true; i++){
			if(playerX/cubeWidth == mapCell[j].getX() && playerY/cubeHeight == mapCell[j].getY()){
				find = true;
				gameCharacter newKnown = gameInfo.appendKnownList();
				//ShowMsg("Hit Make Friends");
				/*ArrayList<gameCharacter> friends = gameInfo.getKnownList();
				for(int k=0; k<friends.size(); k++){
					ShowMsg("You know " + friends.get(k).getName());
				}*/
				popSingleStory(gameInfo.getPlayerCareer(), newKnown);

			}
			j++;
		}
		for(i = 0;i<gameInfo.getChangeCareerNumber() && find != true; i++){
			if(playerX/cubeWidth == mapCell[j].getX() && playerY/cubeHeight == mapCell[j].getY()){
				find = true;
				//ShowMsg("Hit Change Career");
				String oldCareer = gameInfo.getPlayerCareer().getCareerName();
				gameInfo.changeCareer();
				popChangeCareerStory(oldCareer, gameInfo.getPlayerCareer());
			}
			j++;
		}
		for(i = 0; i<gameInfo.getDateNumber() && find != true; i++){
			if(playerX/cubeWidth == mapCell[j].getX() && playerY/cubeHeight == mapCell[j].getY()){
				find = true;
				ShowMsg("Hit Date");
			}
			j++;
		}
		for(i = 0; i<gameInfo.getLuckyDrawNumber() && find != true; i++){
			if(playerX/cubeWidth == mapCell[j].getX() && playerY/cubeHeight == mapCell[j].getY()){
				find = true;
				ShowMsg("Hit the Lucky Draw");
			}
			j++;
		}
		for(i = 0; i<gameInfo.getMoveBackNumber() && find != true; i++){
			if(playerX/cubeWidth == mapCell[j].getX() && playerY/cubeHeight == mapCell[j].getY()){
				find = true;
				ShowMsg("Hit Move back");
			}
			j++;
		}
		for(i = 0; i<gameInfo.getMoveForwardNumber() && find != true; i++){
			if(playerX/cubeWidth == mapCell[j].getX() && playerY/cubeHeight == mapCell[j].getY()){
				find = true;
				//ShowMsg("Hit Move Fowrad");
				random = new Random();
			    Integer diceValue = random.nextInt(6)+1;
			    popMovingForward(diceValue);
			    gameEnd = updatePosition(diceValue);

			}
			j++;
		}
		for(i = 0; i<gameInfo.getTaskNumber() && find != true; i++){
			if(playerX/cubeWidth == mapCell[j].getX() && playerY/cubeHeight == mapCell[j].getY()){
				find = true;
				ShowMsg("Hit Task");
			}
			j++;	
		}
	}


	private void popMovingForward(int diceValue) {
		LayoutInflater factory = LayoutInflater.from(GameActivity.this);
		final View textEntryView = factory.inflate(R.layout.move_popup, null);
		ImageView image = (ImageView)textEntryView.findViewById(R.id.friendPhoto);
		image.setImageResource(gameInfo.getPlayerPhoto());
		TextView textView = (TextView)textEntryView.findViewById(R.id.friendStory); 
		String text = getString(R.string.move_forward1) + " " + diceValue + " " + getString(R.string.move_forward2);
		textView.setText(text);
		AlertDialog dlg = new AlertDialog.Builder(GameActivity.this)
		.setTitle(R.string.move_forward_title)
		.setView(textEntryView)
		.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.cancel();
			    //CompareEvent();
			}
		})
		.create();
		dlg.show();
	}

	private void popChangeCareerStory(String oldCareer, Career career) {
		LayoutInflater factory = LayoutInflater.from(GameActivity.this);
		final View textEntryView = factory.inflate(R.layout.make_frident_dialog, null);
		ImageView image = (ImageView)textEntryView.findViewById(R.id.friendPhoto);
		image.setImageResource(gameInfo.getPlayerPhoto());
		TextView textView = (TextView)textEntryView.findViewById(R.id.friendStory); 
		Log.d("alice", "new career is " + career.getCareerName());
		String text = getString(R.string.change_career1) + oldCareer + 
				getString(R.string.change_career2) + career.getCareerName();
		textView.setText(text);
		AlertDialog dlg = new AlertDialog.Builder(GameActivity.this)
		.setTitle(R.string.change_career_title)
		.setView(textEntryView)
		.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.cancel();
			}
		})
		.create();
		dlg.show();
	}

	private void popSingleStory(Career playerCareer,
			gameCharacter character) {

		LayoutInflater factory = LayoutInflater.from(GameActivity.this);
		final View textEntryView = factory.inflate(R.layout.make_frident_dialog, null);
		ImageView image = (ImageView)textEntryView.findViewById(R.id.friendPhoto);
		image.setImageResource(character.getPicture());
		TextView textView = (TextView)textEntryView.findViewById(R.id.friendStory); 
		Log.d("alice", getString(StoryFriend.getStoryFriend(playerCareer, character)));
		String text = getString(R.string.you_know) + character.getName() + 
				getString(StoryFriend.getStoryFriend(playerCareer, character));
		textView.setText(text);

		AlertDialog dlg = new AlertDialog.Builder(GameActivity.this)
		.setTitle(R.string.make_friend_title)
		.setView(textEntryView)
		.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				arg0.cancel();
			}
		})
		.create();
		dlg.show();
		
		
	}
	private void ShowMsg(String string) {
		Toast.makeText(GameActivity.this, string, Toast.LENGTH_SHORT).show();
	}
}

