package com.dgut.blog.utls;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;


/***
 *
 *@author: lishengdian / 932978775@qq.com
 *@date: 2/9/2021
 *@description: 验证码工具类
 *
 */
public class VerificationCode {

	/**
	 * 生成验证码图片的宽度
	 */
	private final int WIDTH = 100;
	/**
	 * 生成验证码图片的高度
	 */
	private final int HEIGHT = 30;
	/**
	 * 字体
	 */
	private final String[] FONTNAMES = { "宋体", "楷体", "隶书", "微软雅黑" };
	/**
	 * code
	 */
	private final String CODE = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 随机字符串
	 */
	private String text;

	/**
	 * 定义验证码图片的背景颜色为白色
	 */
	private Color bgColor = new Color(255, 255, 255);

	private Random random = new Random();

	/***
	*@description: 获取一个随意颜色
	*@author: lsd 932978775@qq.com
	*@createDate: 2021/2/13
	*/
	private Color randomColor() {
		int red = random.nextInt(150);
		int green = random.nextInt(150);
		int blue = random.nextInt(150);
		return new Color(red, green, blue);
	}

	/***
	 *@description: 获取一个随机字体
	 *@author: lsd 932978775@qq.com
	 *@createDate: 2021/2/13
	 */
	private Font randomFont() {
		String name = FONTNAMES[random.nextInt(FONTNAMES.length)];
		int style = random.nextInt(4);
		int size = random.nextInt(5) + 24;
		return new Font(name, style, size);
	}

	/***
	 *@description: 获取一个随机字符
	 *@author: lsd 932978775@qq.com
	 *@createDate: 2021/2/13
	 */
	private char randomChar() {
		return CODE.charAt(random.nextInt(CODE.length()));
	}

	/***
	 *@description: 创建一个空白的BufferedImage对象
	 *@author: lsd 932978775@qq.com
	 *@createDate: 2021/2/13
	 */
	private BufferedImage createImage() {
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		// 设置验证码图片的背景颜色
		g2.setColor(bgColor);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		return image;
	}

	/***
	 *@description: 获取image
	 *@author: lsd 932978775@qq.com
	 *@createDate: 2021/2/13
	 */
	public BufferedImage getImage() {
		BufferedImage image = createImage();
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			String s = randomChar() + "";
			sb.append(s);
			g2.setColor(randomColor());
			g2.setFont(randomFont());
			float x = i * WIDTH * 1.0f / 4;
			g2.drawString(s, x, HEIGHT - 8);
		}
		this.text = sb.toString();
		drawLine(image);
		return image;
	}

	/***
	 *@description: 绘制干扰线
	 *@author: lsd 932978775@qq.com
	 *@createDate: 2021/2/13
	 */
	private void drawLine(BufferedImage image) {
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		int num = 5;
		for (int i = 0; i < num; i++) {
			int x1 = random.nextInt(WIDTH);
			int y1 = random.nextInt(HEIGHT);
			int x2 = random.nextInt(WIDTH);
			int y2 = random.nextInt(HEIGHT);
			g2.setColor(randomColor());
			g2.setStroke(new BasicStroke(1.5f));
			g2.drawLine(x1, y1, x2, y2);
		}
	}
	/***
	 *@description: 获取文本内容
	 *@author: lsd 932978775@qq.com
	 *@createDate: 2021/2/13
	 */
	public String getText() {
		return text;
	}

	/***
	 *@description: 存储图片
	 *@author: lsd 932978775@qq.com
	 *@createDate: 2021/2/13
	 */
	public static void output(BufferedImage image, OutputStream out) throws IOException {
		ImageIO.write(image, "JPEG", out);
	}
}