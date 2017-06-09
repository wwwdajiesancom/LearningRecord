package com.loujie.www.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.io.Serializable;

import org.junit.Test;

/**
 * 其它的文件操作流
 * 
 * @author loujie
 *
 */
public class OtherIoDemo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5430533866812945654L;

	@Test
	public void byteArrayDemo1() throws IOException {
		// 字节数组流
		String str = "大家好呀!!!";
		read(write(str));
	}

	// 字节数组流,读取
	public void read(byte[] buf) throws IOException {
		// 将字节数组读取到输入流中
		InputStream is = new BufferedInputStream(new ByteArrayInputStream(buf));
		byte[] b = new byte[1024];
		int len = 0;
		do {
			len = is.read(b);
			if (len != -1) {
				// 输出信息
				System.err.println(new String(b, 0, len));
			}
		} while (len != -1);
		is.close();
	}

	// 字节数组流,写入byte[]中
	public byte[] write(String str) throws IOException {
		byte[] b = str.getBytes();
		// 因为有新增方法,不可以用多态
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 将字节数组,写入输出流中
		baos.write(b);
		return baos.toByteArray();
	}

	@Test
	public void dataDemo2() throws IOException {
		// writeData();
		readData();
	}

	// 写一些基本类型到文件中
	public void writeData() throws IOException {
		// 1.定义的基本数据类型数据
		int i = 12;
		long l = 123456l;
		float f = 8932;
		double d = 23;
		String pathname = "D:\\logss\\abc.txt";
		File descFile = new File(pathname);
		// 2.可以保存数据的数据类型
		// 一些数据基本类型
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(descFile, true));
		// 新增的writeXxxx(xx);按照一定顺序写入,因为和读出的顺序要保存一致
		dos.writeInt(i);
		dos.writeLong(l);
		dos.writeFloat(f);
		dos.writeDouble(d);
		dos.flush();
		dos.close();
	}

	// 读取文件中的基本数据类型
	public void readData() throws IOException {
		String pathname = "D:\\logss\\abc.txt";
		File srcFile = new File(pathname);
		DataInputStream ois = new DataInputStream(new FileInputStream(srcFile));
		// 新增的readXxx();方法;必须按照写入的数序读取
		System.err.println(ois.readInt());
		System.err.println(ois.readFloat());
		System.err.println(ois.readLong());
		System.err.println(ois.readDouble());
		ois.close();
	}

	class People implements Serializable {
		private static final long serialVersionUID = -5198860822417987467L;

		private transient String name;// 姓名

		private String birthday;// 生日

		public People(String name, String birthday) {
			super();
			this.name = name;
			this.birthday = birthday;
		}

		public People() {
			super();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		@Override
		public String toString() {
			return "People [name=" + name + ", birthday=" + birthday + "]";
		}

	}

	@Test
	public void objectDemo() throws ClassNotFoundException, IOException {
		People people = new People("jiege", "1989-02-15");
		System.err.println(people);
		People people2 = (People) unSearliza(seraliza(people));
		System.err.println(people2);
	}

	// 序列化对象成字节数组
	public byte[] seraliza(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(obj);
		return out.toByteArray();
	}

	// 反序列化,将字节组反序列化成对象
	public Object unSearliza(byte[] buf) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		ObjectInputStream ois = new ObjectInputStream(in);
		Object object = ois.readObject();
		in.close();

		return object;
	}

	@Test
	public void printlnDemo() {
		PrintStream ps = System.err;
		// 有一个print,println(Obj)方法,可以输出任意类型的对象
		ps.println("ooxxx");
		ps.println('x');
		ps.println(1234);
	}

	public void randomDemo() throws IOException {
		RandomAccessFile raf = new RandomAccessFile("D:\\logss\\dir\\PbsConstants.java", "r");
		raf.seek(10);
		
	}

}
