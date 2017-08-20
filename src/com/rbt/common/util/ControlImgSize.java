package com.rbt.common.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ControlImgSize {

	public int[] returnSrcImgWH(String img_path) {
		java.io.File file = new java.io.File(img_path);
		int[] a = new int[2];
		if (file.exists()) {
			BufferedImage bi = null;
			boolean imgwrong = false;
			try {
				bi = javax.imageio.ImageIO.read(file);
				imgwrong = true;
			} catch (IOException ex) {
				System.out.println(this.getClass().toString()+"["+img_path+"],get image size error!");
			}
			if (imgwrong) {
				a[0] = bi.getWidth();
				a[1] = bi.getHeight();
			} else {
				a[0] = 0;
				a[1] = 0;
			}
		} else {
			a[0] = 0;
			a[1] = 0;
		}
		return a;

	}

	public String returnDisImgWH(String file_path, int setWidth, int setHeight) {

		int[] a = returnSrcImgWH(file_path);

		int srcWidth = a[0];
		int srcHeight = a[1];

		String imgsize = " width=\"" + srcWidth + "\" ";

		if (a[0] == 0) {
			return imgsize;
		}

		// 原图大小的宽高都比设置的宽高小，不考虑
		if (srcWidth <= setWidth && srcHeight <= setHeight) {
			return imgsize;
		} else {
			int width = setHeight * srcWidth / srcHeight;// 得到比例后的宽
			int height = setWidth * srcHeight / srcWidth;// 得到比例后的高
			if (width >= setWidth && height <= setHeight) {
				imgsize = " width=\"" + setWidth + "\" ";
			} else if (width <= setWidth && height >= setHeight) {
				imgsize = " height=\"" + setHeight + "\" ";
			}
		}
		System.out.print(imgsize);
		return imgsize;
		
	}

	public static void main(String[] args) {

	}

}
