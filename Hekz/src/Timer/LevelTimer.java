package Timer;

import java.util.Timer;
import java.util.TimerTask;

public class LevelTimer {
	private int secondsPassed;
	private int msPassed;
	private Timer myTimer;
	private TimerTask task;
	private TimerTask task2;
	public LevelTimer()
	{
		secondsPassed = 0;
		myTimer = new Timer();
		task = new TimerTask()
		{
			public void run() {
				
				secondsPassed++;
				}
		};
		this.start();
		task2 = new TimerTask()
				{
					public void run()
					{
						msPassed++;
						if(msPassed>=60)
						{
							msPassed=0;
						}
						
					}
				};
				this.start2();
	}
	public void start()
	{
		myTimer.scheduleAtFixedRate(task, 1000, 1000);
	}
	public void start2()
	{
		myTimer.scheduleAtFixedRate(task2, 17,17);
	}
	public String tostring()
	{
		return ""+(50-secondsPassed)+"."+Math.abs((50-msPassed));
	}
	public int getSeconds()
	{
		return secondsPassed;
	}
}
