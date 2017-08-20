package com.rbt.common.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class BackMysql {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		/*
		 * 备份和导入是一个互逆的过程。 备份：程序调用mysql的备份命令，读出控制台输入流信息，写入.sql文件；
		 * 导入：程序调用mysql的导入命令，把从.sql文件中读出的信息写入控制台的输出流 注意：此时定向符">"和"<"是不能用的
		 */

		BackMysql bm = new BackMysql();

		bm.loadCreateDataBase("", "", "");

	}

	private String rootPath = PropertiesUtil.getRootpath();

	// 备份所需要的工具文件所在的路径地址，有windows和linux下的文件，共四个
	private String dbUtilFolderPath = rootPath + "WEB-INF" + File.separator
			+ "dbTools" + File.separator;

	private String windowsBakeUtilPath = dbUtilFolderPath + "mysqldumpwin";
	private String windowsImportUtilPath = dbUtilFolderPath + "mysqlwin";

	private String linuxBakeUtilPath = dbUtilFolderPath + "mysqldump";
	private String linuxImportUtilPath = dbUtilFolderPath + "mysql";

	private String bakeUtilPath;
	private String importUtilPath;

	// 数据库信息
	private String host;
	private String user_name;
	private String passwd;
	private String charset = "utf8";
	private String dbName;
	private String dbUrl;

	// 备份文件保存的地址
	private String bakeSavePath = rootPath + "WEB-INF" + File.separator
			+ "dbBackup" + File.separator;;

	private DbUtil dbUtil;
	private PropertiesUtil config;

	public BackMysql() {
		this.dbUtil = new DbUtil();
		this.config = new PropertiesUtil("jdbc.properties");

		this.bakeUtilPath = getBakeUtilPath();
		this.importUtilPath = getImportUtilPath();

		this.dbName = dbUtil.getDbName();
		this.host = dbUtil.getHost();
		try {
			this.user_name = config.readValue("datasource.username");
			this.passwd = config.readValue("datasource.password");
			this.dbUrl = config.readValue("datasource.url");
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 获取备份的工具路径
	public String getBakeUtilPath() {
		String osType = getOsType().toLowerCase();
		if (osType.indexOf("windows") > -1) {
			return this.windowsBakeUtilPath;
		} else if (osType.indexOf("linux") > -1) {
			return this.linuxBakeUtilPath;
		} else {
			return "";
		}
	}

	// 获取导入的工具路径
	public String getImportUtilPath() {
		String osType = getOsType().toLowerCase();
		if (osType.indexOf("windows") > -1) {
			return this.windowsImportUtilPath;
		} else if (osType.indexOf("linux") > -1) {
			return this.linuxImportUtilPath;
		} else {
			return "";
		}
	}

	// 获取操作系统类型
	public String getOsType() {
		Properties props = System.getProperties();
		return props.getProperty("os.name");
	}

	// 备份库
	public String backupDb() {
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyyMMddhhmmss");
		String s = format.format(new Date());
		// 拼出备份数据库文件名
		String fileName = "DB_" + this.dbName + "_" + s + ".sql";
		// 开始备份
		backup(fileName, "");
		return fileName;
	}

	// 备份表
	public String backupDbTable(String tableStr) {
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyyMMddhhmmss");
		String s = format.format(new Date());
		// 循环备份数据库表
		StringBuffer sb = new StringBuffer();
		String str[] = tableStr.split(",");
		if (str.length > 0) {
			for (int i = 0; i < str.length; i++) {
				String tableName = str[i].trim();
				String sqlStr = "TABLE_" + tableName + "_" + s + ".txt";
				sb.append(sqlStr + "  ");
				backup(sqlStr, tableName);
			}
		}
		return sb.toString();
	}

	// 取出数据库或表列表
	// type：DB 数据库 TABLE 数据库表
	@SuppressWarnings("unchecked")
	public List getSqlList(String type) {
		File file = new File(bakeSavePath);
		String flist[] = file.list();
		List list = new ArrayList();
		if (flist != null && flist.length > 0) {
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].startsWith(type)) {
					list.add(flist[i]);
				}
			}
		}
		return list;
	}

	/**
	 * 备份检验一个sql文件是否可以做导入文件用的一个判断方法：把该sql文件分别用记事本和ultra
	 * edit打开，如果看到的中文均正常没有乱码，则可以用来做导入的源文件（不管sql文件的编码格式如何，也不管db的编码格式如何）
	 */
	// fileName:包括保存路径和文件名
	public void backup(String fileName, String tableName) {
		
		
		try {
			Runtime rt = Runtime.getRuntime();

			String execStr = this.bakeUtilPath + " -h " + this.host + " -u "
					+ this.user_name + " -p" + this.passwd + " --set-charset="
					+ this.charset + " " + dbName;
			// 备份表
			if (!tableName.equals("")) {
				execStr = execStr + " " + tableName;
			}

			System.out.println(execStr);

			// 调用 mysql 的 cmd:
			Process child = rt.exec(execStr);// 设置导出编码为utf8。这里必须是utf8

			// 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
			InputStream in = child.getInputStream();// 控制台的输出信息作为输入流

			InputStreamReader xx = new InputStreamReader(in, this.charset);// 设置输出流编码为utf8。这里必须是utf8，否则从流中读入的是乱码

			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			// 组合控制台输出信息字符串
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();

			// 要用来做导入用的sql目标文件：
			FileOutputStream fout = new FileOutputStream(bakeSavePath
					+ fileName);
			OutputStreamWriter writer = new OutputStreamWriter(fout,
					this.charset);
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();

			// 别忘记关闭输入输出流
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();


		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String loadCreateDataBase(String databasename, String user_name,
			String passwd) {

		String ret = "0";
		
		FileUtil fu = new FileUtil();
		String tempSql = "temp.sql";
		String bakeSql = "DB_rbtB2B_v1.sql";
		String dbSqlCont = fu.readTxt(bakeSavePath + bakeSql);
		dbSqlCont = dbSqlCont.replace("${dbname}", databasename);
		String passwdmd5 = com.rbt.common.Md5.getMD5(passwd.getBytes());
		dbSqlCont = dbSqlCont.replace("${user_name}", user_name);
		dbSqlCont = dbSqlCont.replace("${passwd}", passwdmd5);

		dbSqlCont = replaceZS(dbSqlCont);

		fu.writeTxt(bakeSavePath, tempSql, dbSqlCont);
		
		//读取sql脚本，并去掉注释部分，生成临时文件temp.sql

		List<String> sqlList = new ArrayList<String>();
		String filePathAndName = bakeSavePath + tempSql;
		FileInputStream fs = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fs = new FileInputStream(filePathAndName);
			isr = new InputStreamReader(fs, "UTF-8");
			br = new BufferedReader(isr);
			try {
				String data = "";
				StringBuffer sb = new StringBuffer();
				while ((data = br.readLine()) != null) {
					data = data.trim();
					if (!data.equals("") && !data.equals(";")) {
						if (data.startsWith("insert")) {
							sqlList.add(data);
						} else if (data.endsWith(";")) {
							sb.append(data);
							sqlList.add(sb.toString());
							sb = new StringBuffer();
						} else {
							sb.append(data+"\n");
						}
					}
				}
			} catch (Exception e) {
				ret = "read sql file error";
			}
			fs.close();
			isr.close();
			br.close();
		} catch (IOException es) {
			ret = filePathAndName + "file path read error";
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
			ret = "database connect close error";
			File fp = new File(filePathAndName);
			fp.delete();
			
		}
		
		//把可执行脚本都放到list里面去
		
		
		Connection conn = null;
		Statement st = null;
		String connUrl = "jdbc:mysql://"+this.host+":3306/test?useUnicode=true&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(connUrl, this.user_name, this.passwd);
			st = conn.createStatement();
			for (String sql : sqlList) {
				st.executeUpdate(sql);
				//执行每个可执行sql脚本
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ret = connUrl+" error";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
					st = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
		
	}

	// 去掉注释
	public String replaceZS(String sql) {
		if (sql == null || sql.equals("")) {
			return sql;
		}
		String start = "/*", end = "*/";
		while (sql.indexOf(start) > -1 && sql.indexOf(end) > -1) {
			int i = sql.indexOf(start);
			int j = sql.indexOf(end);
			String k = sql.substring(i, j + 2);
			sql = sql.replace(k, "");
		}

		return sql;
	}

	/**
	 * 导入
	 * 
	 */
	// fileName：导入的文件名
	public void load(String fileName) {
		try {
			String fPath = bakeSavePath + fileName;
			Runtime rt = Runtime.getRuntime();

			String execStr = this.importUtilPath + " -h " + this.host + " -u "
					+ this.user_name + " -p" + this.passwd + " " + this.dbName;

			System.out.println(execStr);

			// 调用 mysql 的 cmd:
			Process child = rt.exec(execStr);
			OutputStream out = child.getOutputStream();// 控制台的输入信息作为输出流
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fPath), this.charset));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();

			OutputStreamWriter writer = new OutputStreamWriter(out,
					this.charset);
			writer.write(outStr);
			// 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
			writer.flush();
			// 别忘记关闭输入输出流
			out.close();
			br.close();
			writer.close();

			System.out.println("/* Load OK! */");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Method Description :删除指定表的功能
	 * @author : LJQ
	 * @date : Nov 9, 2014 3:17:02 PM
	 */
	public boolean deleteTable(String fileName) {
		String filepath = bakeSavePath + fileName;
		File file = new File(filepath);
		if (file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}

}
