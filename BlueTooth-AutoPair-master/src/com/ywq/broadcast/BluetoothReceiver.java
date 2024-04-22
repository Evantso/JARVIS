package com.ywq.broadcast;

import com.ywq.tools.ClsUtils;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class BluetoothReceiver extends BroadcastReceiver{

	String pin = "1234";  //�˴�Ϊ��Ҫ���ӵ������豸�ĳ�ʼ��Կ��һ��Ϊ1234��0000
	public BluetoothReceiver() {
		
	}

	//�㲥����������Զ�������豸������ʱ���ص�����onReceiver()�ᱻִ�� 
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction(); //�õ�action
		Log.e("action1=", action);
		BluetoothDevice btDevice=null;  //����һ������device����
		 // ��Intent�л�ȡ�豸����
		btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE); 
		
		if(BluetoothDevice.ACTION_FOUND.equals(action)){  //�����豸
			Log.e("�����豸:", "["+btDevice.getName()+"]"+":"+btDevice.getAddress());
			
			if(btDevice.getName().contains("HC-05"))//HC-05�豸����ж������һ���ѵ����Ǹ��ᱻ���ԡ�
			{
				if (btDevice.getBondState() == BluetoothDevice.BOND_NONE) {  
					
					Log.e("ywq", "attemp to bond:"+"["+btDevice.getName()+"]");
					try {
						//ͨ��������ClsUtils,����createBond����
						ClsUtils.createBond(btDevice.getClass(), btDevice);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
			}else
				Log.e("error", "Is faild");
		}else if(action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) //�ٴεõ���action�������PAIRING_REQUEST
		{
			Log.e("action2=", action);
			if(btDevice.getName().contains("HC-05"))
			{
				Log.e("here", "OKOKOK");
				
				try {
					
					//1.ȷ�����
					ClsUtils.setPairingConfirmation(btDevice.getClass(), btDevice, true);
					//2.��ֹ����㲥
					Log.i("order...", "isOrderedBroadcast:"+isOrderedBroadcast()+",isInitialStickyBroadcast:"+isInitialStickyBroadcast());
					abortBroadcast();//���û�н��㲥��ֹ��������һ��һ����������Կ�
					//3.����setPin�����������...
					boolean ret = ClsUtils.setPin(btDevice.getClass(), btDevice, pin);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
				Log.e("��ʾ��Ϣ", "����豸����Ŀ�������豸");
			
		}
	}
}