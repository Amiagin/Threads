package com.demo.tx;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Main extends ActionBarActivity {
	private TextView textView1;
	private TextView textView2;
	private int thread1Value,thread2Value;
	private Thread threadOne;
	private Thread threadTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Random random = new Random();
        
        textView1 = (TextView)findViewById(R.id.thread_1_text);
        textView2 = (TextView)findViewById(R.id.thread_2_text);
        
        thread1Value = (random.nextInt(100) % 1000);
        thread2Value = (random.nextInt(100) % 1000);
        
        threadOne = new One();
        threadTwo = new Two();
        
        threadOne.start();
        threadTwo.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    class One extends Thread {
    	public void run() {
    		while (thread1Value < 100000) {
    			try {
    				Main.this.runOnUiThread(new Runnable() {
    					public void run() {
    						if (textView1 != null) {
    							String text = "";
    							if ((thread1Value % 8) == 0) {
    								text = " Is Working....";
    							}
    							textView1.setText("Count: " + thread1Value + text);
    						}
    					}
    				});
    			    Thread.sleep(1000);
    			} catch (Exception exception) {		
    			}
    			thread1Value++;
    		}
    	}
    }

    class Two extends Thread {
    	public void run() {
    		while (thread1Value < 100000) {
    			try {
    				Main.this.runOnUiThread(new Runnable() {
    					public void run() {
							String text = "";
							if ((thread2Value % 8) == 0) {
								text = " Is Working....";
							}
							textView2.setText("Count: " + thread2Value + text);
    					}
    				});
    			    Thread.sleep(600);
    			} catch (Exception exception) {		
    			}
    			thread2Value++;
    		}
    	}
    }
}
