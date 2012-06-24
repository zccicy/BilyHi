package com.zccicy.common.util;

import java.io.Serializable;

import android.os.Bundle;
import android.os.Message;

public class MessageHelper {

	public static Serializable getMsgParameter(Message message)
	{
		return message.getData().getSerializable("extra.key.msg.obj");
		
		
	}
	public static Message obtainCustomMessage(int param)
	{
		return Message.obtain(null,param);
	}
	public static Message obtainCustomMessage(int param,Serializable paramSer)
	{
		Bundle bundle=new Bundle();
		bundle.putSerializable("extra.key.msg.obj", paramSer);
		Message message=Message.obtain(null,param);
		message.setData(bundle);
		return message;
	}
	public static Message obtainCustomMessage(int param,Runnable paramRun)
	{
		Message message=Message.obtain(null,paramRun);
		message.what=param;
		return message;
	}
	public static Message obtainCustomMessage(int param,Object obj)
	{
		Message message=Message.obtain(null, param, obj);
		return message;
	}
 
}
