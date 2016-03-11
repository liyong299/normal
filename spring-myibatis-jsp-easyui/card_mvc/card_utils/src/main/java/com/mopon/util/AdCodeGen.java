package com.mopon.util;

import java.security.MessageDigest;
import java.util.Random;


class LOCK {
	private int availiable;

	private int total;

	public LOCK() {
		total = 1;
		availiable = 1;
	}

	public LOCK(int total) {
		this.total = total;
		availiable = total;
	}

	public LOCK(int total, int availiable) {
		this.total = total;
		this.availiable = availiable > total ? total : availiable;
	}

	public boolean isLocked() {
		return getFree() < 1;
	}

	public int getFree() {
		synchronized (this) {
			return availiable;
		}
	}

	public boolean lock() {
		return lock(1);
	}

	public boolean lock(int num) {
		synchronized (this) {
			if (availiable >= num) {
				availiable -= num;
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean lockBlock(long waittime) {
		// System.out.println("LOCK waittime="+waittime);
		return lockBlock(1, waittime);
	}

	public boolean lockBlock(int num, long waittime) {
		int count = 0;
		long expire = -1;
		// System.out.println("LOCK num="+num+" waittime="+waittime);
		if (waittime >= 0) {
			expire = System.currentTimeMillis() + waittime;
			// System.out.println("LOCK expire="+expire+"
			// current="+System.currentTimeMillis());
		}
		do {
			if (lock(1)) {
				count++;
				if (count == num) {
					return true;
				}
			} else {
				if (waittime >= 0) {
					// System.out.println("LOCK waittime>=0");
					if (System.currentTimeMillis() > expire) {
						// System.out.println("LOCK expired");
						break;
					}
					synchronized (this) {
						try {
							wait(100);
						} catch (InterruptedException e) {
						}
					}
				} else if (waittime < 0) {
					synchronized (this) {
						try {
							wait();
						} catch (InterruptedException e) {
						}
					}
				}
			}
		} while (true);
		if (count > 0) {
			unlock(count);
		}
		return false;
	}

	public void unlock() {
		unlock(1);
	}

	public void unlock(int num) {
		synchronized (this) {
			availiable += num;
			if (availiable > total) {
				availiable = total;
			}
			notifyAll();
		}
	}
}

class MyKey {
	byte[] key = new byte[1024];

	int pos = 0;

	long time = -1;
};

/**
 * <p>
 * Title: �����������
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2005
 * 
 * @version 1.0
 */
public class AdCodeGen {
	private static MyKey key_cur = new MyKey();

	private static MyKey key_bak = new MyKey();

	private static LOCK key_lock = new LOCK(1);
	static {
		createKey();
	}

	// private final static char[] arrLetter = { 'a', 'b', '2', '3', '4', '5',
	// '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
	// 'j', 'k', '7', 'm', 'n', '8', 'p', '9', 'r', 's', 't', 'u', 'v',
	// 'w', 'x', 'y', 'z' };

	private final static char[] arrLetter = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9' };

	private final int ADDTION_LENGTH = 4;

	private String Seed;

	private static void createKey() {
		// System.out.println("Createing key");
		key_cur.time = System.currentTimeMillis() + 1000L * 60L * 60L * 24L;
		// System.out.println("seed : "+key_cur.time);
		Random rand = new Random(key_cur.time);
		rand.nextBytes(key_cur.key);
		key_cur.pos = rand.nextInt(key_cur.key.length - 16);
	}

	private static void checkKey() {
		// System.out.println("checkKey start...");
		if (key_lock.lock()) {
			if (key_cur.time < 0) {
				createKey();
			} else if (key_cur.time < System.currentTimeMillis()) {
				// System.out.println("Backing key...");
				key_bak = key_cur;
				key_bak.time += 1000L * 60L * 30L;
				key_cur = new MyKey();
				createKey();
			}
			if (key_bak.time > 0L && key_bak.time < System.currentTimeMillis()) {
				// System.out.println("releaseing bak key ...");
				key_bak.time = -1;
			}
			key_lock.unlock(1);
		}
	}

	private static MyKey getCurKey() {
		checkKey();
		try {
			key_lock.lockBlock(-1L);
			return key_cur;
		} finally {
			key_lock.unlock();
		}
	}

	private static MyKey getBakKey() {
		checkKey();
		try {
			key_lock.lockBlock(-1L);
			if (key_bak.time < 0) {
				// System.out.println("Get bak key null");
				return null;
			} else {
				return key_bak;
			}
		} finally {
			key_lock.unlock();
		}
	}

	public AdCodeGen(String p_seed) {
		// System.out.println("CodeGen->seed: " + p_seed);
		// Seed = new String(URLDecoder.decode(p_seed));
		Seed = new String(p_seed);
	}

	public boolean verifyCode(String code) {
		if(StringUtil.isEmpty(code)){
			return false;
		}
		String code1 = getCode(getCurKey());
		if (code1.equalsIgnoreCase(code)) {
			return true;
		}
		code1 = getCode(getBakKey());
		if (code1.equalsIgnoreCase(code)) {
			return true;
		}
		return false;
	}

	private String getCode(MyKey key) {
		String sCode = "";
		if (key == null) {
			return sCode;
		}
		byte[] b = new byte[key.key.length];
		System.arraycopy(key.key, 0, b, 0, b.length);
		byte[] b2 = Seed.getBytes();
		for (int i = 0; i < b2.length; i++) {
			b[i % b.length] ^= b2[i];
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			for (int i = 0; i < 3; i++) {
				b = md.digest(b);
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			return sCode;
		}
		for (int i = 0; i < b.length; i++) {
			b[i] ^= key.key[key.pos + i];
		}
		char[] ch = new char[ADDTION_LENGTH];
		for (int i = 0; i < ADDTION_LENGTH; i++) {
			int num = b[i];
			num = num & 0x0FF;
			num = num % arrLetter.length;
			ch[i] = arrLetter[num];
		}
		sCode = new String(ch);
		return sCode;
	}

	public String getCode() {
		String AddCode = getCode(getCurKey());
		// System.out.println("CodeGen->AddCode: " + AddCode);
		return AddCode;
		/*
		 * String sCode = ""; int num=0; byte tmp;
		 * 
		 * try{ MessageDigest md = MessageDigest.getInstance("MD5");
		 * //��Seed�������MD5���� md.update(Seed.getBytes()); byte[] sDigest =
		 * md.digest(md.digest(md.digest()));
		 * 
		 * //ȡժҪ��ǰ��λ��ɸ����봮 for(int i=0; i<ADDTION_LENGTH; i++){ num =
		 * sDigest[i]; num = num & 0x0FF; sCode += arrLetter[num%36]; } }catch
		 * (Exception e){ }
		 * 
		 * return sCode;
		 */
	}

	// private static String hexToLogStr(byte[] data) {
	// StringBuffer sb = new StringBuffer(((data.length + 15) / 16) * 70);
	// char[] ch = new char[71];
	// for (int i = 0; i < ch.length; i++) {
	// ch[i] = ' ';
	// }
	// ch[24] = '-';
	// ch[69] = '\r';
	// ch[70] = '\n';
	// for (int i = 0; i < data.length;) {
	// byte b = data[i];
	// int ii = i % 16;
	// i++;
	// if (b >= 0x20 && b <= 0x7e) {
	// ch[53 + ii] = (char) b;
	// } else {
	// ch[53 + ii] = '.';
	// }
	// int b1 = (((int) b) >> 4) & 0x0F;
	// int b2 = ((int) b) & 0x0F;
	// b1 = (b1 >= 10 ? 'A' + (b1 - 10) : '0' + b1);
	// b2 = (b2 >= 10 ? 'A' + (b2 - 10) : '0' + b2);
	// if (ii <= 7) {
	// ch[ii * 3] = (char) b1;
	// ch[ii * 3 + 1] = (char) b2;
	// } else {
	// ch[2 + ii * 3] = (char) b1;
	// ch[2 + ii * 3 + 1] = (char) b2;
	// }
	// if (ii == 15 || i == data.length) {
	// sb.append(ch);
	// for (int k = 0; k < ch.length; k++) {
	// ch[k] = ' ';
	// }
	// ch[24] = '-';
	// ch[69] = '\r';
	// ch[70] = '\n';
	// }
	// }
	// return sb.toString();
	// }

	public static void main(String args[]) {
		// System.out.println("KEY is \n"+hexToLogStr(CodeGen.key_cur.key));
		// System.out.println("POS is \n"+CodeGen.key_cur.pos);

		String s;
		AdCodeGen code = null;

		if (args.length == 0) {
			code = new AdCodeGen("AAAAAAAAAAAAA");
		} else {
			code = new AdCodeGen(args[0]);
		}
		s = code.getCode();
		System.out.println(s);

		if (args.length > 1) {
			AdCodeGen Code1 = new AdCodeGen(args[0]);
			System.out.println(args[0]);
			s = args[1];
			System.out.println(s);

			System.out.println("" + Code1.verifyCode(s));
		}

	}
}
