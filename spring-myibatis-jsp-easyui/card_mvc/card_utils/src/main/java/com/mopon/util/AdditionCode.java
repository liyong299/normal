package com.mopon.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

// 把类实例化后就可以调用draw()把图像画出来.
// 或者调用getImage来取出图像.
// 取出的图像可以导出成BMP或PNG格式.
public class AdditionCode {
	// 生成的图像
	protected BufferedImage image;
	// 预定义的可以用来做附加码的字符串.
	public static final String DIGITAL = "0123456789";
	public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	// 数字和字母的混合,其中去掉了部分易混淆的字母
	public static final String MIXED = "0123456789ACdEFHiJKLMNPQRSTUVWXY";

	// 字体名,以后使用不同的字体来生成图片.
	// 这些字体是在WINDOWS下运行时得到的，
	// 如果要在其它系统下运行，这些字体都要做更换。
	/*
	 * protected static String[] fontsname = { "Arial", //"Basemic Symbol", //
	 * 字体好像太小了点 "Basemic Times", "Book Antiqua", "Bookman Old Style", "Century
	 * Gothic", "Comic Sans MS", "Courier New", "方正舒体", "方正姚体", "仿宋_GB2312",
	 * "Garamond", "Georgia", "楷体_GB2312", "隶书", "Lucida Console", "Lucida Sans
	 * Unicode", "MS Outlook", "Microsoft Sans Serif", "新宋体", "Palatino Linotype",
	 * "华文细黑", "华文新魏", "华文中宋", "黑体", "宋体", "Tahoma", "Times New Roman", "Trebuchet
	 * MS", "Verdana", "幼圆", "dialog.plain", "dialoginput.plain",
	 * "monospaced.plain", "sansserif.plain", "serif.plain" }; // 字体对象列表.
	 * protected static Font[] fonts = null;
	 */
	// 逻辑字体
	protected static String[] logical_fontsname = { "Dialog", "DialogInput",
			"Monospaced", "Serif", "SansSerif" };
	// 逻辑字体对象列表.
	protected static Font[] logical_fonts = null;

	// 当前选定的字符集.默认为数字
	protected String CurrentSet = DIGITAL;
	// 生成或传入的附加码.
	protected String GenCode = new String();
	// 背景图
	// public static Image bgimg = null;

	// 初始化背景图
	/*
	 * static { try { bgimg = ImageIO.read(new File("testbk.jpg")); } catch
	 * (Exception e) { e.printStackTrace(System.err); bgimg = null; } }
	 */
	// 初始化调色板,二色
	protected static ColorModel colorModel = generateColorModel();

	// 设置外界传入的背景图
	/*
	 * public static void SetBackgroundImage(Image img) { bgimg = img; }
	 */

	// 随机生成附加码
	protected String genCode(int len) {
		return genCode(CurrentSet, len);
	}

	// 随机生成附加码
	static public String genCode(String set, int len) {
		int lenset = set.length();
		Random rand = new Random(System.currentTimeMillis());// getTimeInMillis());//
		// jdk 1.3 , this
		// method is
		// protected!!!
		char out[] = new char[len];
		for (int i = 0; i < len; i++) {
			out[i] = set.charAt(rand.nextInt(lenset));
		}
		return new String(out);
	}

	// 构造函数,指定宽度和高度
	public AdditionCode(int width, int height) {
		this(width, height, null, null);
	}

	// 构造函数,指定宽度和高度和附加码,附加码为null时自动生成4位长附加码
	public AdditionCode(int width, int height, String digital) {
		this(width, height, digital, null);
	}

	// 构造函数,指定宽度和高度和附加码和字符集,附加码为null时自动生成4位长附加码
	public AdditionCode(int width, int height, String digital, String set) {
		// System.out.println("********************** add code test
		// **********************");
		// 设置字符集
		if (set != null) {
			CurrentSet = set;
		}
		// 生成附加码
		if (digital == null) {
			digital = genCode(4);
		}
		GenCode = digital;
		// 字符集
		/*
		 * if(fonts==null) { GraphicsEnvironment ge =
		 * GraphicsEnvironment.getLocalGraphicsEnvironment(); Font[] fonts_tmp =
		 * ge.getAllFonts(); //for(int i=0;i<fonts.length;i++) { //
		 * System.out.println(fonts[i].getFontName()+" ---- "+fonts[i].getFamily()+"
		 * ---- "+fonts[i].getSize()+" ---- "+fonts[i].getSize2D()); //} //fontsname =
		 * ge.getAvailableFontFamilyNames(); fonts = new Font[fontsname.length]; / *
		 * for(int i=0;i<fontsname.length;i++) { fonts[i]=null; for(int j=0;j<fonts_tmp.length;j++) {
		 * if(fonts_tmp[j].getFontName().equals(fontsname[i])) {
		 * fonts[i]=fonts_tmp[j]; break; } } } / for(int i=0;i<fonts.length;i++) {
		 * fonts[i]=null; } for(int i=0;i<fonts_tmp.length;i++) { int j; for(j=0;j<fontsname.length;j++) {
		 * if(fonts_tmp[i].getFontName().equals(fontsname[j])) {
		 * fonts[j]=fonts_tmp[i]; break; } } //if(j==fontsname.length) { //
		 * System.out.println(fonts_tmp[i].getFontName()); //} } }
		 */
		/*
		 * if(logical_fonts==null) { logical_fonts = new
		 * Font[logical_fontsname.length]; for(int i=0;i<logical_fontsname.length;i++) {
		 * logical_fonts[i] = new Font(logical_fontsname[i],Font.PLAIN,1);
		 * if(logical_fonts[i]==null) { System.out.println(logical_fontsname[i]); } } }
		 */
		// 选择使用哪组字库
		/*
		 * Font[] select_fonts = logical_fonts;
		 */
		// 此处忽略了传入的宽度和高度
		/*
		 * width = 5+14*digital.length()+5; //height = 30; width =
		 * 2+10*digital.length()+2; //height = 20; // 生成新的图像 image = new
		 * BufferedImage(width,height,BufferedImage.TYPE_BYTE_INDEXED,(IndexColorModel)colorModel); //
		 * 获取画板 Graphics2D graph = image.createGraphics(); // 初始化随机数 Random rand =
		 * new Random(System.currentTimeMillis());//.getTimeInMillis());// jdk 1.3 ,
		 * this method is protected!!!
		 */
		// 随机截取背景图
		/*
		 * if(bgimg!=null) { try { // 检查背景图的宽度和高度 if(bgimg.getWidth(null)<width||bgimg.getHeight(null)<height) {
		 * throw new Exception("the background image is not big enouth!!!!"); } //
		 * 目标位置 int dx1 = 0; int dy1 = 0; int dx2 = width-1; int dy2 = height-1; //
		 * 源位置，随机生成位置 int sx1 =
		 * (int)(rand.nextDouble()*(bgimg.getWidth(null)-width)); int sy1 =
		 * (int)(rand.nextDouble()*(bgimg.getHeight(null)-height)); int sx2 =
		 * sx1+width-1; int sy2 = sy1 + height - 1; // 把背景图的一部分画到画板上去
		 * graph.drawImage(bgimg,dx1,dy1,dx2,dy2,sx1,sy1,sx2,sy2,null); } catch
		 * (Exception e) { e.printStackTrace(); } }
		 */
		// 设置画笔的着色，设为黑色
		/*
		 * graph.setColor(new Color(0,0,0)); //System.out.println("CODE :
		 * "+digital); // 生成字体 Font font =
		 * select_fonts[0].deriveFont(Font.PLAIN,16); // 设置字体 graph.setFont(font); //
		 * 画出字符 graph.drawString(digital,2,16+2);
		 */
		/*
		 * for(int i=0;i<digital.length();i++) { // 选取字体大小，这里只是改变了大小，以后还要换字体 int
		 * fontsize = 16+rand.nextInt(7); //System.out.println("font size
		 * "+fontsize); // 选择字体 int fontid=0; do { fontid =
		 * rand.nextInt(select_fonts.length); } while(select_fonts[fontid]==null); //
		 * 生成字体 //Font font = new Font(fonts[fontid],Font.PLAIN,fontsize); Font font =
		 * select_fonts[fontid].deriveFont(Font.PLAIN,fontsize); // 把要画的字符取出来。
		 * char[] ch = new char[1]; ch[0]=digital.charAt(i); // 生成倾斜的角度.
		 * nextDouble返回0.0-1.0的浮点数 double radian = rand.nextDouble(); //
		 * 转换成角度,最大为45度. //radian*=45.0; radian*=30.0; // 为了保证有一定的角度,当角度太小时增加一点
		 * if(radian<5.0) radian += 5.0; // 转换为弧度 一周=360角度=2*PI弧度 radian =
		 * radian*3.1415926*2.0/360.0; // 生成倾斜的方向 radian *=
		 * rand.nextInt(100)%2==0?-1.0:1.0; // 生成倾斜的字体 AffineTransform affinet = new
		 * AffineTransform(); //affinet.setToRotation(radian,4,fontsize/2);
		 * affinet.setToRotation(radian); font = font.deriveFont(affinet); // 设置字体
		 * graph.setFont(font); // 画出字符 graph.drawString(new
		 * String(ch),5+14*i,16+5); }
		 */
		// 完成绘图
		/*
		 * graph.dispose();
		 */

		image = (BufferedImage) createImageText(digital);
	}

	// 画出生成的附加码图形,调试用.
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}

	// 取生成的图像
	public Image getImage() {
		return image;
	}

	// 把图像输出到数组中,指定格式,支持的格式为"PNG" "JPG"
	public byte[] toByteArray(String format) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		if (format.equalsIgnoreCase("PNG")) {
			PngEncoder pe = new PngEncoder(image);
			pe.setCompressionLevel(9);
			return pe.pngEncode(true);
		}
		if (format.equalsIgnoreCase("GIF")) {
			try {
				GIFEncoder ge = new GIFEncoder(image);
				ge.Write(bos);
			} catch (Exception e) {
				return null;
			}
			return bos.toByteArray();
		}
		try {
			ImageIO.write(image, format, bos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bos.toByteArray();
		/**
		 * if(format.equalsIgnoreCase("PNG")) { PngEncoder pe = new
		 * PngEncoder(image); pe.setCompressionLevel(9); return pe.pngEncode(true); }
		 * return null;
		 */
	}

	// 把图像按PNG格式输出到数组中
	public byte[] toByteArray() {
		return toByteArray("PNG");
	}

	// 把图像输出到文件中,格式为PNG
	public void toFile(String path) {
		toFile(path, "PNG");
	}

	// 把图像输出到文件中,指定格式,支持的格式为"PNG" "JPG"
	public void toFile(String path, String format) {
		try {
			ImageIO.write(image, format, new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 把图像输出到文件中
	private static ColorModel generateColorModel() {
		byte[] r = new byte[2];
		byte[] g = new byte[2];
		byte[] b = new byte[2];

		r[0] = (byte) 255;
		g[0] = (byte) 255;
		b[0] = (byte) 255; // 白色
		r[1] = 0;
		g[1] = 0;
		b[1] = 0; // 黑色

		return new IndexColorModel(1, 2, r, g, b);
	}

	// 获取生成的附加码。
	public String getCode() {
		return GenCode;
	}

	// 生成调试串
	public String toString() {
		byte[] data = toByteArray();
		StringBuffer sb = new StringBuffer(10 + GenCode.length() + 3 * data.length
				+ 100);
		// sb.append(getClass().getName());
		// sb.append('\n');
		sb.append(super.toString());
		sb.append('\n');
		sb.append("CODE is : ");
		sb.append(GenCode);
		sb.append('\n');
		sb.append("DATA is ( in hex mode ) : \n");
		char[] ch = new char[70];
		for (int i = 0; i < ch.length; i++) {
			ch[i] = ' ';
		}
		ch[24] = '-';
		ch[69] = '\n';
		for (int i = 0; i < data.length;) {
			byte b = data[i];
			int ii = i % 16;
			i++;
			if (b >= 0x20 && b <= 0x7e) {
				ch[53 + ii] = (char) b;
			} else {
				ch[53 + ii] = '.';
			}
			int b1 = (((int) b) >> 4) & 0x0F;
			int b2 = ((int) b) & 0x0F;
			b1 = (b1 >= 10 ? 'A' + (b1 - 10) : '0' + b1);
			b2 = (b2 >= 10 ? 'A' + (b2 - 10) : '0' + b2);
			if (ii <= 7) {
				ch[ii * 3] = (char) b1;
				ch[ii * 3 + 1] = (char) b2;
			} else {
				ch[2 + ii * 3] = (char) b1;
				ch[2 + ii * 3 + 1] = (char) b2;
			}
			if (ii == 15 || i == data.length) {
				sb.append(ch);
				for (int k = 0; k < ch.length; k++) {
					ch[k] = ' ';
				}
				ch[24] = '-';
				ch[69] = '\n';
			}
		}
		return sb.toString();
	}

	Image createImageText(String digital) {
		BufferedImage img = new BufferedImage(1 + digital.length() * 9, 18,
				BufferedImage.TYPE_BYTE_INDEXED, (IndexColorModel) colorModel);
		for (int i = 0; i < digital.length(); i++) {
			char ch = digital.charAt(i);
			if (ch > 0x7f || ch < ' ')
				continue;
			int x = i * 9 + 1;
			int y = 1;
			int offset = (ch - ' ') * 16;
			for (int j = 0; j < 16; j++, offset++, y++) {
				byte b = asc_matrix[offset];
				for (int m = 0; m < 8; m++) {
					if ((b & testbits[m]) != 0) {
						img.setRGB(x + m, y, BLACK);
					}

				}
			}
		}
		return img;
	}

	private static final byte[] testbits = new byte[] { (byte) 0x80, 0x40, 0x20,
			0x10, 0x08, 0x04, 0x02, 0x01 };
	private static final int BLACK = 0;// 0x00FFFFFF;
	private static final byte[] asc_matrix = new byte[] {
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // ' '
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x3C,
			(byte) 0x3C,
			(byte) 0x3C,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '!'
			(byte) 0x00,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x24,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '"'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x6C,
			(byte) 0x6C,
			(byte) 0xFE,
			(byte) 0x6C,
			(byte) 0x6C,
			(byte) 0x6C,
			(byte) 0xFE,
			(byte) 0x6C,
			(byte) 0x6C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '#'
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC2,
			(byte) 0xC0,
			(byte) 0x7C,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x86,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00, // '$'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC2,
			(byte) 0xC6,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x60,
			(byte) 0xC6,
			(byte) 0x86,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '%'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x38,
			(byte) 0x6C,
			(byte) 0x6C,
			(byte) 0x38,
			(byte) 0x76,
			(byte) 0xDC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0x76,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '&'
			(byte) 0x00,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x60,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '''
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x18,
			(byte) 0x0C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '('
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x30,
			(byte) 0x18,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // ')'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x66,
			(byte) 0x3C,
			(byte) 0xFF,
			(byte) 0x3C,
			(byte) 0x66,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '*'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x7E,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '+'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // ','
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFE,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '-'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '.'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x02,
			(byte) 0x06,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x60,
			(byte) 0xC0,
			(byte) 0x80,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '/'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x38,
			(byte) 0x6C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xD6,
			(byte) 0xD6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x6C,
			(byte) 0x38,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '0'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x38,
			(byte) 0x78,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x7E,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '1'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0x06,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x60,
			(byte) 0xC0,
			(byte) 0xC6,
			(byte) 0xFE,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '2'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x3C,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '3'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x0C,
			(byte) 0x1C,
			(byte) 0x3C,
			(byte) 0x6C,
			(byte) 0xCC,
			(byte) 0xFE,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x1E,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '4'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFE,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xFC,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '5'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x38,
			(byte) 0x60,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xFC,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '6'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFE,
			(byte) 0xC6,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '7'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '8'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x7E,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x0C,
			(byte) 0x78,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '9'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // ':'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // ';'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x06,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x60,
			(byte) 0x30,
			(byte) 0x18,
			(byte) 0x0C,
			(byte) 0x06,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '<'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7E,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7E,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '='
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x60,
			(byte) 0x30,
			(byte) 0x18,
			(byte) 0x0C,
			(byte) 0x06,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x60,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '>'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '?'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xDE,
			(byte) 0xDE,
			(byte) 0xDE,
			(byte) 0xDC,
			(byte) 0xC0,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '@'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x10,
			(byte) 0x38,
			(byte) 0x6C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xFE,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'A'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFC,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x7C,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0xFC,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'B'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x3C,
			(byte) 0x66,
			(byte) 0xC2,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xC2,
			(byte) 0x66,
			(byte) 0x3C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'C'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xF8,
			(byte) 0x6C,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x6C,
			(byte) 0xF8,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'D'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFE,
			(byte) 0x66,
			(byte) 0x62,
			(byte) 0x68,
			(byte) 0x78,
			(byte) 0x68,
			(byte) 0x60,
			(byte) 0x62,
			(byte) 0x66,
			(byte) 0xFE,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'E'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFE,
			(byte) 0x66,
			(byte) 0x62,
			(byte) 0x68,
			(byte) 0x78,
			(byte) 0x68,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0xF0,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'F'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x3C,
			(byte) 0x66,
			(byte) 0xC2,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xDE,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x66,
			(byte) 0x3A,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'G'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xFE,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'H'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x3C,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x3C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'I'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x1E,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0x78,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'J'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xE6,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x6C,
			(byte) 0x78,
			(byte) 0x78,
			(byte) 0x6C,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0xE6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'K'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xF0,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x62,
			(byte) 0x66,
			(byte) 0xFE,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'L'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0xEE,
			(byte) 0xFE,
			(byte) 0xFE,
			(byte) 0xD6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'M'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0xE6,
			(byte) 0xF6,
			(byte) 0xFE,
			(byte) 0xDE,
			(byte) 0xCE,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'N'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'O'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFC,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x7C,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0xF0,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'P'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xD6,
			(byte) 0xDE,
			(byte) 0x7C,
			(byte) 0x0C,
			(byte) 0x0E,
			(byte) 0x00,
			(byte) 0x00, // 'Q'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFC,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x7C,
			(byte) 0x6C,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0xE6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'R'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x60,
			(byte) 0x38,
			(byte) 0x0C,
			(byte) 0x06,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'S'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7E,
			(byte) 0x7E,
			(byte) 0x5A,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x3C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'T'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'U'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x6C,
			(byte) 0x38,
			(byte) 0x10,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'V'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xD6,
			(byte) 0xD6,
			(byte) 0xD6,
			(byte) 0xFE,
			(byte) 0xEE,
			(byte) 0x6C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'W'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x6C,
			(byte) 0x7C,
			(byte) 0x38,
			(byte) 0x38,
			(byte) 0x7C,
			(byte) 0x6C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'X'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x3C,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x3C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'Y'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFE,
			(byte) 0xC6,
			(byte) 0x86,
			(byte) 0x0C,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x60,
			(byte) 0xC2,
			(byte) 0xC6,
			(byte) 0xFE,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'Z'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x3C,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x3C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '['
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x80,
			(byte) 0xC0,
			(byte) 0xE0,
			(byte) 0x70,
			(byte) 0x38,
			(byte) 0x1C,
			(byte) 0x0E,
			(byte) 0x06,
			(byte) 0x02,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '\'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x3C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x3C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // ']'
			(byte) 0x10,
			(byte) 0x38,
			(byte) 0x6C,
			(byte) 0xC6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '^'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFF,
			(byte) 0x00,
			(byte) 0x00, // '_'
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '`'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x78,
			(byte) 0x0C,
			(byte) 0x7C,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0x76,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'a'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xE0,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x78,
			(byte) 0x6C,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'b'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'c'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x1C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x3C,
			(byte) 0x6C,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0x76,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'd'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xFE,
			(byte) 0xC0,
			(byte) 0xC0,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'e'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x38,
			(byte) 0x6C,
			(byte) 0x64,
			(byte) 0x60,
			(byte) 0xF0,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0xF0,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'f'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x76,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0x7C,
			(byte) 0x0C,
			(byte) 0xCC,
			(byte) 0x78,
			(byte) 0x00, // 'g'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xE0,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x6C,
			(byte) 0x76,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0xE6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'h'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x38,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x3C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'i'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x00,
			(byte) 0x0E,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x06,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x3C,
			(byte) 0x00, // 'j'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xE0,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x66,
			(byte) 0x6C,
			(byte) 0x78,
			(byte) 0x78,
			(byte) 0x6C,
			(byte) 0x66,
			(byte) 0xE6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'k'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x38,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x3C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'l'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xEC,
			(byte) 0xFE,
			(byte) 0xD6,
			(byte) 0xD6,
			(byte) 0xD6,
			(byte) 0xD6,
			(byte) 0xC6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'm'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xDC,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'n'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'o'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xDC,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x7C,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0xF0,
			(byte) 0x00, // 'p'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x76,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0x7C,
			(byte) 0x0C,
			(byte) 0x0C,
			(byte) 0x1E,
			(byte) 0x00, // 'q'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xDC,
			(byte) 0x76,
			(byte) 0x66,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0x60,
			(byte) 0xF0,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'r'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x7C,
			(byte) 0xC6,
			(byte) 0x60,
			(byte) 0x38,
			(byte) 0x0C,
			(byte) 0xC6,
			(byte) 0x7C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 's'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x10,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0xFC,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x30,
			(byte) 0x36,
			(byte) 0x1C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 't'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0xCC,
			(byte) 0x76,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'u'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x66,
			(byte) 0x3C,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'v'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xD6,
			(byte) 0xD6,
			(byte) 0xD6,
			(byte) 0xFE,
			(byte) 0x6C,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'w'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0x6C,
			(byte) 0x38,
			(byte) 0x38,
			(byte) 0x38,
			(byte) 0x6C,
			(byte) 0xC6,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'x'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0xC6,
			(byte) 0x7E,
			(byte) 0x06,
			(byte) 0x0C,
			(byte) 0xF8,
			(byte) 0x00, // 'y'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0xFE,
			(byte) 0xCC,
			(byte) 0x18,
			(byte) 0x30,
			(byte) 0x60,
			(byte) 0xC6,
			(byte) 0xFE,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // 'z'
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x0E,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x70,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x0E,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '{'
			(byte) 0x00, (byte) 0x00, (byte) 0x18, (byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '|'
			(byte) 0x00, (byte) 0x00, (byte) 0x70, (byte) 0x18, (byte) 0x18,
			(byte) 0x18, (byte) 0x0E, (byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x18,
			(byte) 0x70,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '}'
			(byte) 0x00, (byte) 0x00, (byte) 0x76, (byte) 0xDC, (byte) 0x00,
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, (byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00,
			(byte) 0x00, // '~'
			(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x10,
			(byte) 0x38, (byte) 0x6C, (byte) 0xC6, (byte) 0xC6, (byte) 0xC6,
			(byte) 0xFE, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
			(byte) 0x00, // ''
			0 };

}
