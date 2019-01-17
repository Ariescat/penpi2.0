package org.penpi.test.commons;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class IPTest {

	@Test
	public void getIP() throws Exception {
		try {
			InetAddress address = InetAddress.getLocalHost();// 获取的是本地的IP地址 //PC-20140317PXKX/192.168.0.121
			String hostAddress = address.getHostAddress();// 192.168.0.121
			System.out.println(hostAddress);
			InetAddress address1 = InetAddress.getByName("www.wodexiangce.cn");// 获取的是该网站的ip地址，比如我们所有的请求都通过nginx的，所以这里获取到的其实是nginx服务器的IP地
			String hostAddress1 = address1.getHostAddress();// 124.237.121.122
			InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");// 根据主机名返回其可能的所有InetAddress对象
			for (InetAddress addr : addresses) {
				System.out.println(addr);// www.baidu.com/14.215.177.38
				// www.baidu.com/14.215.177.37
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
