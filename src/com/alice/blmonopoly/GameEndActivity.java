package com.alice.blmonopoly;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class GameEndActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		DrawGameEnd draw = new DrawGameEnd(this);
		setContentView(draw);
	}
	
	public class DrawGameEnd extends View {

		public DrawGameEnd(Context context) {
			super(context);

		}

	}
}