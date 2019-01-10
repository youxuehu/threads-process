package com.gq.data.report.common.http;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.security.KeyStore;


public class MySSLProtocolSocketFactory implements ProtocolSocketFactory {

	private SSLContext sslcontext = null;


	// 读取证书，并创建信任
	public SSLContext createSSLContext() {
		SSLContext sslc = null;
		try {
//			InputStream bais =MySSLProtocolSocketFactory.class.getClassLoader().getResourceAsStream("root.crt") ;
//			CertificateFactory cerFactory = CertificateFactory.getInstance("X.509");
//			Certificate cer = cerFactory.generateCertificate(bais);
			
			KeyStore keyStore = KeyStore.getInstance("JKS");
			InputStream is=MySSLProtocolSocketFactory.class.getClassLoader().getResourceAsStream("cacerts");
			keyStore.load(is, "changeit".toCharArray());
//			keyStore.setCertificateEntry("trust", cer);
			
			// 创建TrustManagerFactory,管理授权证书
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509", "SunJSSE");
			tmf.init(keyStore);
			// 构造SSL环境，指定SSL版本为3.0，也可以使用TLSv1，但是SSLv3更加常用。
			sslc = SSLContext.getInstance("SSLv3");
			sslc.init(null, tmf.getTrustManagers(), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sslc;
	}
	

	private SSLContext getSSLContext() {
		if (this.sslcontext == null) {
			this.sslcontext = createSSLContext();
		}
		return this.sslcontext;
	}

	public Socket createSocket(Socket socket, String host, int port, boolean autoClose)
			throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(socket, host, port, autoClose);
	}

	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(host, port);
	}

	public Socket createSocket(String host, int port, InetAddress clientHost, int clientPort)
			throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(host, port, clientHost, clientPort);
	}

	public Socket createSocket(String host, int port, InetAddress localAddress, int localPort, HttpConnectionParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
		if (params == null) {
			throw new IllegalArgumentException("Parameters may not be null");
		}
		int timeout = params.getConnectionTimeout();
		SocketFactory socketfactory = getSSLContext().getSocketFactory();
		if (timeout == 0) {
			return socketfactory.createSocket(host, port, localAddress, localPort);
		} else {
			Socket socket = socketfactory.createSocket();
			SocketAddress localaddr = new InetSocketAddress(localAddress, localPort);
			SocketAddress remoteaddr = new InetSocketAddress(host, port);
			socket.bind(localaddr);
			socket.connect(remoteaddr, timeout);
			return socket;
		}
	}
}