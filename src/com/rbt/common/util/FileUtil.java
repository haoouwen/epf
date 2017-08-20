/*
 
 * Package:com.rbt.common.util
 * FileName: FileUtil.java
 */
package com.rbt.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * 用户文件的 读 写 目录的创建等
 * 
 * @author HXK
 * 
 */
public class FileUtil {
	private String message;

	private String FILESPARA = "/";

	public FileUtil() {
	}

	/**
	 * 强制用UTF-8 编码读 整个系统编码采用UTF-8
	 * 
	 * @param filePathAndName
	 * @return
	 */
	public String readTxt(String filePathAndName) {
		return readTxt(filePathAndName, "UTF-8");
	}

	/**
	 * 读取文本文件内容
	 * 
	 */
	public String readTxt(String filePathAndName, String encoding) {
		encoding = encoding.trim();
		StringBuffer str = new StringBuffer("");
		String st = "";
		FileInputStream fs = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fs = new FileInputStream(filePathAndName);

			if (encoding.equals("")) {
				isr = new InputStreamReader(fs);
			} else {
				isr = new InputStreamReader(fs, encoding);
			}
			br = new BufferedReader(isr);
			try {
				String data = "";
				while ((data = br.readLine()) != null) {
					str.append(data + "\n");
				}
			} catch (Exception e) {
				str.append(e.toString());
			}
			st = str.toString();
			fs.close();
			isr.close();
			br.close();
		} catch (IOException es) {
			System.out.println(es.getMessage() + "///" + filePathAndName + "文件路径读取错误");
		} finally {
			if (fs != null) {
				try {
					fs.close();
				} catch (IOException e1) {
				}
			}
			if (isr != null) {
				try {
					isr.close();
				} catch (IOException e1) {
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e1) {
				}
			}
		}
		return st;
	}

	/**
	 * 强制用UTF-8 编码保存 避免因为操作系统不同而编码不同。
	 * 
	 * @param path
	 * @param filename
	 * @param fileContent
	 */
	public void writeTxt(String path, String filename, String fileContent) {
		writeTxt(path, filename, fileContent, "UTF-8");

	}

	/**
	 * 有编码方式的文件创建
	 */
	public void writeTxt(String path, String filename, String fileContent,
			String encoding) {
		PrintWriter pwrite = null;
		try {
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			file = new File(path + FILESPARA + filename);
			if (encoding != null && !"".equals(encoding)) {
				pwrite = new PrintWriter(file, encoding);
			} else {
				pwrite = new PrintWriter(file);
			}
			pwrite.println(fileContent);
			pwrite.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (pwrite != null) {
				pwrite.close();
			}
		}
	}

	/**
	 * 拷贝目录树 把一个目录下的所有东西包括子目录 同时拷贝到 另外一个目录
	 * 
	 * @param sourceDir
	 * @param targetDir
	 * @throws Exception
	 */
	public void copyDir(String sourceDir, String targetDir) throws Exception {
		FileInputStream input = null;
		FileOutputStream output = null;
		String url1 = sourceDir;
		String url2 = targetDir;
		if (!(new File(url2)).exists()) {
			(new File(url2)).mkdirs();
		}
		File[] file = (new File(url1)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				file[i].toString();
				input = new FileInputStream(file[i]);
				output = new FileOutputStream(url2
						+ System.getProperty("file.separator")
						+ (file[i].getName()).toString());
				byte[] b = new byte[1024 * 5];
				int len;
				while ((len = input.read(b)) != -1) {
					output.write(b, 0, len);
				}
				output.flush();
				output.close();
				input.close();
			} else if (file[i].isDirectory()) {
				String url_2_dir = url2 + System.getProperty("file.separator")
						+ file[i].getName();
				copyDir(file[i].getPath(), url_2_dir);
			}
		}
		if (input != null) {
			input.close();
		}
		if (output != null) {
			output.close();
		}
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            目录
	 * @return 返回目录创建后的路径
	 */
	public String createFolder(String folderPath) {
		String txt = folderPath;
		try {
			File myFilePath = new File(txt);
			txt = folderPath;
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			message = "创建目录操作出错";
		}
		return txt;
	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @return Boolean 成功删除返回true遭遇异常返回false
	 */
	public boolean delFile(String filePathAndName) {
		boolean bea = false;
		try {
			String filePath = filePathAndName;
			File myDelFile = new File(filePath);
			if (myDelFile.exists()) {
				myDelFile.delete();
				bea = true;
			} else {
				bea = false;
				message = (filePathAndName + "<br>删除文件操作出错");
			}
		} catch (Exception e) {
			message = e.toString();
		}
		return bea;
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            文件夹完整绝对路径
	 * @return
	 */
	public void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			message = ("删除文件夹操作出错");
		}
	}

	/**
	 * 删除指定文件夹下所有文件
	 * 
	 * @param path
	 *            文件夹完整绝对路径
	 * @return
	 * @return
	 */
	public boolean delAllFile(String path) {
		boolean bea = false;
		File file = new File(path);
		if (!file.exists()) {
			return bea;
		}
		if (!file.isDirectory()) {
			return bea;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				bea = true;
			}
		}
		return bea;
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPathFile
	 *            准备复制的文件源
	 * @param newPathFile
	 *            拷贝到新绝对路径带文件名
	 * @return
	 */
	public void copyFile(String oldPathFile, String newPathFile) {
		InputStream is = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { // 文件存在时
				is = new FileInputStream(oldPathFile); // 读入原文件
				fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = is.read(buffer)) != -1) {
					bytesum += byteread; // 字节  数 文件大小
					fs.write(buffer, 0, byteread);
				}
				is.close();
				fs.close();
			}
		} catch (Exception e) {
			message = ("复制单个文件操作出错");
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fs!=null){
				try {
					fs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/** 
     * 复制单个文件 
     *  
     * @param srcFileName 
     *            待复制的文件名 
     * @param descFileName 
     *            目标文件名 
     * @param overlay 
     *            如果目标文件存在，是否覆盖 
     * @return 如果复制成功返回true，否则返回false 
     */  
    public static boolean copyFile(String srcFileName, String destFileName,boolean overlay) {  
        File srcFile = new File(srcFileName);  
  
        // 判断源文件是否存在  
        if (!srcFile.exists()) {  
            return false;  
        } else if (!srcFile.isFile()) {  
            return false;  
        }  
  
        // 判断目标文件是否存在  
        File destFile = new File(destFileName);  
        if (destFile.exists()) {  
            // 如果目标文件存在并允许覆盖  
            if (overlay) {  
                // 删除已经存在的目标文件，无论目标文件是目录还是单个文件  
                new File(destFileName).delete();  
            }  
        } else {  
            // 如果目标文件所在目录不存在，则创建目录  
            if (!destFile.getParentFile().exists()) {  
                // 目标文件所在目录不存在  
                if (!destFile.getParentFile().mkdirs()) {  
                    // 复制文件失败：创建目标文件所在目录失败  
                    return false;  
                }  
            }  
        }  
  
        // 复制文件  
        int byteread = 0; // 读取的字节数  
        InputStream in = null;  
        OutputStream out = null;  
  
        try {  
            in = new FileInputStream(srcFile);  
            out = new FileOutputStream(destFile);  
            byte[] buffer = new byte[1024];  
  
            while ((byteread = in.read(buffer)) != -1) {  
                out.write(buffer, 0, byteread);  
            }  
            return true;  
        } catch (FileNotFoundException e) {  
            return false;  
        } catch (IOException e) {  
            return false;  
        } finally {  
            try {  
                if (out != null)  
                    out.close();  
                if (in != null)  
                    in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
	/**
	 * 移动文件
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动目录
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 * @throws Exception
	 */
	public void moveFolder(String sourceDir, String targetDir) throws Exception {
		copyDir(sourceDir, targetDir);
		delFolder(sourceDir);
	}

	public String getMessage() {
		return this.message;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public String getFileExt(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1).toLowerCase();
			}
		}
		return "";
	}
	/**
	 * @Method Description :判断本地文件是否存在
	 * @author: HXK
	 * @date : Apr 28, 2015 2:57:23 PM
	 * @param 
	 * @return return_type false 文件不存在  ture文件存在
	 */
     public static boolean localFileIfExist(String filepath){
    	
		File file = new File(filepath);
        //文件不存在时
        if(!file.exists()){	
        	return false;
        }
    	return true;
     }
	
	

}