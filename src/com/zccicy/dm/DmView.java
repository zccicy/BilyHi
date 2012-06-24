
package com.zccicy.dm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.zccicy.common.util.GlobalConstants;
import com.zccicy.model.AvDmItem;


public class DmView extends View {

	private Context context;
	private List<AvDmItem> dmItems;
	private float playerCurrentPosition=(float) 0.0;
	private Integer dmPlayIndex=0;
	private HashMap<Integer, AvDmItem> dmPool;
	private Short[] dmChannel;
	private Integer screenWidth;
	private Integer screenHeight;
	private List<Integer> cleanList;
 
	
	public DmView(Context context,int screenWidth,int screenHeight) {
		super(context);
		this.context=context;
		dmPlayIndex=0;
		this.screenWidth=screenWidth;
		this.screenHeight=screenHeight;
		dmChannel=new Short[screenHeight];
		initDmChannel();
		playerCurrentPosition=0;
		dmPool=new HashMap<Integer, AvDmItem>();
		cleanList=new ArrayList<Integer>();
		// TODO Auto-generated constructor stub
	}
	

	private void initDmChannel() {
		// TODO Auto-generated method stub
		for (int i=0;i<screenHeight;i++)
		{
			dmChannel[i]=0;
		}
	}


	public List<AvDmItem> getDmItems() {
		return dmItems;
	}


	public void setDmItems(List<AvDmItem> dmItems) {
		this.dmItems = dmItems;
		refreshDmItems();
	}


	private void refreshDmItems() {
		// TODO Auto-generated method stub
		for (int i=0;i<dmItems.size();i++)
		{
			AvDmItem avDmItem=dmItems.get(i);
			avDmItem.setDm_width(avDmItem.getDm_content().length()*avDmItem.getFont_size());
			avDmItem.setDm_x(screenWidth);
			avDmItem.setDm_y(0);
		}
	}


	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		long time1=System.currentTimeMillis();
		if (dmItems==null||dmItems.size()<=0)
		{
			super.onDraw(canvas);
			return;
		}
		pushDmIntoPool();
		drawDms(canvas);
		long time2=System.currentTimeMillis();
		System.out.println("time2-time1"+(time2-time1));
//		Paint paint=new Paint();
//		paint.setAlpha(255);
//		paint.setColor(0xffffffff);
//		paint.setTextSize(100);
//		canvas.drawText("aaa", 123, 123, paint);
		 
//		canvas.save();
		
		super.onDraw(canvas);
		
	}

	private void drawDms(Canvas canvas) {
		// TODO Auto-generated method stub
		Iterator iter = null;
		iter = dmPool.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			AvDmItem avDmItem=(AvDmItem)entry.getValue();
			//释放弹幕 
			if(avDmItem.getDm_x()+avDmItem.getDm_width()<0)
			{
				cleanList.add(avDmItem.getDm_id());
				
				continue;
			}
//			System.out.println("avDmItemX"+avDmItem.getDm_x()+"---"+avDmItem.getDm_width()+"==="+screenWidth);
 
			if (avDmItem.getDm_x()+avDmItem.getDm_width()<screenWidth-80&&avDmItem.isChannel__lock()) //如果脱离右侧边框
			{
				//释放弹幕通道
				for (int i=avDmItem.getDm_y();i<(avDmItem.getDm_y()+avDmItem.getFont_size());i++)
				{
					dmChannel[i]=0;
					
				}	
				avDmItem.setChannel__lock(false);
			}
			//画弹幕
			avDmItem.setDm_x(avDmItem.getDm_x()-GlobalConstants.DM_SPEED);
//			System.out.println("getc123"+avDmItem.getDm_y()+"size"+avDmItem.getFont_size());
			if (avDmItem.getDm_y()<=0)
			{
				int getC=getChannel(avDmItem.getFont_size());
				avDmItem.setChannel__lock(true);
//				System.out.println("getc"+getC+"size"+avDmItem.getFont_size());
				avDmItem.setDm_y(getC);
			}
//			System.out.println(avDmItem.toString());
			drawDm(avDmItem,canvas);
		}
		cleanDmPool();
	}


	private void cleanDmPool() {
		// TODO Auto-generated method stub
		for (int i=0;i<cleanList.size();i++)
		{
			//从弹幕池中移除
			dmPool.remove(cleanList.get(i));
		}
		cleanList.clear();
 
	}


	private void drawDm(AvDmItem avDmItem,Canvas canvas) {
		// TODO Auto-generated method stub
 		Paint paint=new Paint();
 		paint.setAlpha(255);
 		paint.setColor(0xffffffff);
 		paint.setTextSize(avDmItem.getFont_size());
 		canvas.drawText(avDmItem.getDm_content(), avDmItem.getDm_x(), avDmItem.getDm_y(), paint);
		canvas.save();
	}


 


	private void pushDmIntoPool() {
		// TODO Auto-generated method stub
	
		while (dmPlayIndex<dmItems.size()&&dmItems.get(dmPlayIndex).getSend_time()<playerCurrentPosition)
		{
			System.out.println("sendtime"+dmItems.get(dmPlayIndex).getSend_time());
			System.out.println("currentTime"+playerCurrentPosition);
			dmPool.put(dmItems.get(dmPlayIndex).getDm_id(), dmItems.get(dmPlayIndex));
			dmPlayIndex++;
		}
	}

	//获取Dm的通道
	private Integer getChannel(int chanelWidth)
	{
		int chanelIndex=70;
		int lastIdleIndex=70;
		while (chanelIndex<(screenHeight-2*chanelWidth))
		{
			if (dmChannel[chanelIndex]==1)
			{
				chanelIndex+=10;
				continue;
			}
			lastIdleIndex=chanelIndex;
			
			if (dmChannel[chanelIndex+chanelWidth]==0)
			{
				for (int i=chanelIndex;i<chanelIndex+chanelWidth;i++)
				{
					dmChannel[i]=1;
				}
				
				return chanelIndex;
			}
			else
			{
				chanelIndex=chanelIndex+chanelWidth;
			}
		}
		
		int downBound=chanelIndex+chanelWidth;
		 
		
		for (int i=lastIdleIndex;i<downBound;i++)
		{
			dmChannel[i]=1;
		}
		 return lastIdleIndex;
	}

	public float getPlayerCurrentPosition() {
		return playerCurrentPosition;
	}

	public void setPlayerCurrentPosition(float playerCurrentPosition) {
		this.playerCurrentPosition = playerCurrentPosition;
	}
	
	

}
