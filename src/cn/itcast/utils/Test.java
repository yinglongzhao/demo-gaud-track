package cn.itcast.utils;

import java.util.Random;

public class Test {

	public static int num;
	public static boolean flag = true;

	public static void main(String[] args) {

		Object o = new Object();
		Object o1 = new Object();
		Object o2 = new Object();

		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (o) {
					System.out.println("��ʼ����");
					// TODO Auto-generated method stub
					for (int i = 0; i < 20; i++) {
						System.out.println("-----------��"+i+"��ʼ����--------------------------");
						Random r = new Random();
						num = r.nextInt(100);
						System.out.println(Thread.currentThread().getName() + "������:" + num);
						try {
							o.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						o.notifyAll();
					}
					flag = false;
					System.out.println("��������");
				}
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				while(flag) {
					
					synchronized (o) {
						if (num % 2 == 0) {
							System.out.println(Thread.currentThread().getName() + "ż���̻߳�ȡ:" + num);
							o.notify();
							try {
								o.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				while(flag) {
					synchronized (o) {
						if (num % 2 != 0) {
							System.out.println(Thread.currentThread().getName() + "�����̻߳�ȡ:" + num);
							o.notifyAll();
							try {
								o.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}).start();
	}

}
