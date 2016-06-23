/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package email;

import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringEscapeUtils;
//qq密码md5加密
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Cryin
 */
public class QQMailRecev extends Mail{
    private MailFuncInter m_mailFuncInterqq;
    boolean endrecv=false;
    public  String urls="";
    public  String cookiesStr="";
    public  String Host="";
    public String BoxName;
    public QQMailRecev( MailFuncInter mailFuncInter )
    {
        m_mailFuncInterqq = mailFuncInter;
    }
    @Override
    void login() {

        if(m_cookie!=null)                      //以cookie是否为空进行判断是cookie收邮还是密码收邮
        {
            Cookielogin();
        }
        else
        {
            Userlogin();
        }
        m_mailFuncInterqq.ShowMessage(m_accounts+" :下载完毕");
    }
    private void Cookielogin()
    {
        String url = m_url;
	String strHtml = "";
        cookiesStr=m_cookie;
    //    cookiesStr="pgv_pvid=7853367474; pt2gguin=o0416049355; ptcz=8d89c4fbf1c1c44921368079b66e23cba5cd94e0d52b41efed1faf105dc2ceec; uin=o0416049355; skey=@oa7nM2Am3; ptisp=ctc; qm_sid=f4b84dbcbda470057d04bde5afad909c,qc3doKnJNZ3N0WExRTTdDQ1pOWFBMc3B0Z3A4SG03VlVGaXdtRGlZZmlzRV8.; qm_username=416049355; qm_domain=http://mail.qq.com";
	int index;
        String urlHead;
        String targetUrl=null;
        String targetr;
        String tempUrl;
        String sid=null;
        String targeturl=null;
        String clientuin=null;
        if(url.length()==0)
        {
            m_mailFuncInterqq.ShowMessage(m_accounts+" :url不能为空！");
            return;
        }
        m_mailFuncInterqq.ShowMessage(m_accounts+" :开始登录");
	try {  
                if(cookiesStr.indexOf("url=")>0)
                {
                    targeturl=putstr(cookiesStr, " url=", "sid", 0);
                    sid=putstr(cookiesStr + ";", "html?sid=", "&r", 0);
                    targetr=putstr(cookiesStr + ";", "r=", ";", 0);
                    targetUrl=targeturl+"sid="+sid+"&r="+targetr;
                    strHtml = getRequest(targetUrl);
                    
                    if (strHtml.indexOf("收件箱") > 0)
                    {
                        m_mailFuncInterqq.ShowMessage(m_accounts+" :登录成功");
                        if (strHtml.indexOf("switchFolder(this.id)") != -1) 
                        {
                            getBoxName(strHtml);
                            m_mailFuncInterqq.ShowMessage(m_accounts + " :下载完毕");
                        }
                    }
                    else
                    {
                        m_mailFuncInterqq.ShowMessage(m_accounts + " :Cookie已失效!");
                        return;
                    }
                }
                else
                {

		strHtml = getRequest(url);
                if (strHtml.indexOf("收件箱") > 0)
                    {
                        m_mailFuncInterqq.ShowMessage(m_accounts+" :登录成功");
                        if (strHtml.indexOf("switchFolder(this.id)") != -1) 
                        {
                            getBoxName(strHtml);
                            //m_mailFuncInterqq.ShowMessage(m_accounts + " :下载完毕");
                        }
                    }
                    else
                    {
                    if(strHtml.indexOf("login_box_msg noCookie\" id=\"infobarNoCookie")>0)
                    {
                        m_mailFuncInterqq.ShowMessage(m_accounts + " :无效Cookie");
                    }
                       
		if (strHtml.indexOf("window.location.replace(targetUrl)") != -1) 
                {
                    
                    urlHead = putstr(strHtml, "var urlHead=\"", "\"", 0);
                    tempUrl = putstr(strHtml, "targetUrl = urlHead + \"", "\";", 0);
                    targeturl=putstr(strHtml, "targetUrl += \"&", "\";", 0);
                    targetr=putstr(strHtml, "targetUrl+=\"", "\"", 0);
                    if (tempUrl.indexOf("sid")!=-1 )
                    {   
                        sid = putstr(tempUrl + ";", "sid=", ";", 0);
                        targetUrl = urlHead+"frame_html?sid="+sid;
                    }
                    else
                    {
                        String newstr=sss(tempUrl);
                        newstr=newstr.replace(" ", "");
                        tempUrl=newstr.replace("\"+\"", "");
                  
                    
                         if (tempUrl.indexOf("loginpage") > -1)
                        {
                            targetUrl = urlHead+ tempUrl+"&"+targeturl+targetr;
                        }
                    }

                    strHtml = getRequest(targetUrl);
                    
                    if (strHtml.indexOf("收件箱") > 0)
                    {
                        m_mailFuncInterqq.ShowMessage(m_accounts+" :登录成功");
                        if (strHtml.indexOf("switchFolder(this.id)") != -1) 
                        {
                            getBoxName(strHtml);
                           // m_mailFuncInterqq.ShowMessage(m_accounts + " :下载完毕");
                        }
                    }
                    else
                    {
                        m_mailFuncInterqq.ShowMessage(m_accounts + " :Cookie已过期!");
                        return;
                    }
                    
                }
                
                }
                }
                
            } catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            }
    }
    private void Userlogin()
    {
        String url = "";
	String strHtml = "";
        String pwd = "";
        String ts = "";
	int index;
        m_mailFuncInterqq.ShowMessage(m_accounts+" :开始登录");
        url = "http://m.mail.qq.com/cgi-bin/login?sid=";
        try{
            pwd = URLEncoder.encode(m_password, "UTF-8");
        }catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        
        NameValuePair[] InData = { 
			new NameValuePair("f", "xhtmlmp"),
                        new NameValuePair("delegate_url", ""),
                        new NameValuePair("f", "xhtmlmp"),
                        new NameValuePair("action", ""),
                        new NameValuePair("tfcont", ""),
			new NameValuePair("uin", m_accounts ),
			new NameValuePair("aliastype", "@qq.com"),
                        new NameValuePair("pwd", pwd ),
                        new NameValuePair("mss", "1"),
                        new NameValuePair("mtk", ""),
			new NameValuePair("btlogin", "%E7%99%BB%E5%BD%95" )};
	
                //2014-12-15 
        try {
		strHtml=postRequest(url,InData);
                if ((index=strHtml.indexOf("\"Refresh\"")) > 0) {
			url = putstr(strHtml, "url=", "\"", index);
                        url = url.replace(",9,", ",4,");
                        url = "https://w.mail.qq.com/cgi-bin/today?sid="+putstr(strHtml, "sid=", "&", index)+"&first=1&mcookie=disabled";
                        url = url.replace(",9,", ",4,");
		}
                strHtml=getRequest(url);
		if (strHtml != "") {
                    if ((index=urls.indexOf("sid"))!=-1)
                    {
                        String sid=putstr(urls,"sid=",",",index);
                        strHtml ="";
                        url = "http://mail.qq.com/cgi-bin/frame_html?f=html&sid="+URLEncoder.encode(sid, "UTF-8");
                        strHtml = getRequest(url);
			if (strHtml.indexOf("收件箱") > 0) {
                            m_mailFuncInterqq.ShowMessage(m_accounts+" :登录成功");
                            getMailAddress(strHtml);
                            getBoxName(strHtml);
                        }

                    }
		}
            } catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            }
    }
    /**
    * Get
    * @param url
    * @return
    * @throws HttpException
    * @throws IOException
    */
    private String getRequest(String url) throws HttpException, IOException
    {
		//
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setCookiePolicy(
				CookiePolicy.BROWSER_COMPATIBILITY);
		//
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(
				HttpMethodParams.SINGLE_COOKIE_HEADER, true);
		if (cookiesStr != "")
		{
			getMethod.setRequestHeader("Cookie", cookiesStr);
		}
		getMethod.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		getMethod.setRequestHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 5.1; rv:20.0) Gecko/20100101 Firefox/20.0");
		getMethod.setRequestHeader("Accept-Language", "en-us,ar-SA;q=0.9,de-DE;q=0.8,es-ES;q=0.7,tr-TR;q=0.6,ja-JP;q=0.5,en-GB;q=0.4,fr-FR;q=0.3,zh-CN;q=0.2,zh-TW;q=0.1");
		String requestStr = new String();
		StringBuffer resultBuffer = new StringBuffer();
		try
		{
			//
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK)
			{
				m_mailFuncInterqq.ShowMessage(m_accounts+" :qq Request Method failed: "
						+ getMethod.getStatusLine());
			}
			//
			BufferedReader in = new BufferedReader(new InputStreamReader(
					getMethod.getResponseBodyAsStream(), getMethod
							.getResponseCharSet()));
			URI uri = getMethod.getURI();
			urls = uri.getURI();
			String inputLine = null;
			while ((inputLine = in.readLine()) != null)
			{
				resultBuffer.append(inputLine);
				resultBuffer.append("\n");
			}
			requestStr = resultBuffer.toString();
			Cookie[] cookiesTemp = httpClient.getState().getCookies();
			if (cookiesTemp != null && cookiesTemp.length > 0)
			{
				for (int i = 0; i < cookiesTemp.length; i++)
				{
					if (cookiesStr == "")
					{
						cookiesStr += cookiesTemp[i].getName() + "="
								+ cookiesTemp[i].getValue() + "; ";
					}
					else
					{
						if (cookiesStr.endsWith("; "))
						{
							cookiesStr += cookiesTemp[i].getName() + "="
									+ cookiesTemp[i].getValue() + "; ";
						}
						else
						{
							cookiesStr += "; " + cookiesTemp[i].getName() + "="
									+ cookiesTemp[i].getValue();
						}
					}
				}
			}
		}
		catch (HttpException e)
		{
			//
			m_mailFuncInterqq.ShowMessage(m_accounts+" :Please check your provided http address!");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			//
			e.printStackTrace();
		}
		finally
		{
			//
			getMethod.releaseConnection();
		}
		return requestStr;
	}
	/**
	 *
	 * @param url
	 * @param InData
	 * @return Post Response
	 * @throws HttpException
	 * @throws IOException
	 */
	private String postRequest(String url, NameValuePair[] InData) throws HttpException, IOException
	{

		String html = "";
		StringBuffer resultBuffer = new StringBuffer();
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setCookiePolicy(
				CookiePolicy.BROWSER_COMPATIBILITY);
		PostMethod postMethod = new PostMethod(url);

		postMethod.setRequestHeader("Accept", "*/*");
		postMethod.setRequestHeader("Connection", "Keep-Alive");
		postMethod.setRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 5.1; rv:20.0) Gecko/20100101 Firefox/20.0");
		postMethod.setRequestHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
		if (cookiesStr != "")
		{
			postMethod.setRequestHeader("Cookie", cookiesStr);
		}
		postMethod.setRequestBody(InData);
		int statusCode = httpClient.executeMethod(postMethod);
		//
		//
		if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
				|| (statusCode == HttpStatus.SC_MOVED_PERMANENTLY)
				|| (statusCode == HttpStatus.SC_SEE_OTHER)
				|| (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT))
		{
			Header locationHeader = postMethod.getResponseHeader("location");
			String location = null;
			if (locationHeader != null)
			{
				location = locationHeader.getValue();
				if (location.startsWith("http"))
				{
					GetMethod getMethod = new GetMethod(url);
					statusCode = httpClient.executeMethod(getMethod);

					if (statusCode != HttpStatus.SC_OK)
					{
						m_mailFuncInterqq.ShowMessage(m_accounts+" :126 Request Method failed: "
								+ getMethod.getStatusLine());
					}
					//
					BufferedReader in = new BufferedReader(
							new InputStreamReader(getMethod
									.getResponseBodyAsStream(), getMethod
									.getResponseCharSet()));
					URI uri = getMethod.getURI();
					urls = uri.getURI();
					String inputLine = null;
					while ((inputLine = in.readLine()) != null)
					{
						resultBuffer.append(inputLine);
						resultBuffer.append("\n");
					}
					html = resultBuffer.toString();

					//System.out.println("The page was redirected to:" + location);
				}
				else
				{
					url = "http://" + postMethod.getURI().getHost() + location;
					GetMethod getMethod = new GetMethod(url);
					statusCode = httpClient.executeMethod(getMethod);

					if (statusCode != HttpStatus.SC_OK)
					{
						m_mailFuncInterqq.ShowMessage(m_accounts+" :hotmail Request Method failed: "
								+ getMethod.getStatusLine());
					}
					//
					BufferedReader in = new BufferedReader(
							new InputStreamReader(getMethod
									.getResponseBodyAsStream(), getMethod
									.getResponseCharSet()));
					URI uri = getMethod.getURI();
					urls = uri.getURI();
					String inputLine = null;
					while ((inputLine = in.readLine()) != null)
					{
						resultBuffer.append(inputLine);
						resultBuffer.append("\n");
					}
					html = resultBuffer.toString();
					//System.out.println("The page was error:" + location);
				}

				return html;

			}
                        else
                        {
				m_mailFuncInterqq.ShowMessage(m_accounts+" :Location field value is null.");
			}
			return html;
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(postMethod
				.getResponseBodyAsStream(), postMethod.getResponseCharSet()));
		StringBuffer strBuffer = new StringBuffer();
		String inputLine = null;
		while ((inputLine = in.readLine()) != null)
		{
			strBuffer.append(inputLine);
			strBuffer.append("\n");
		}
		html = strBuffer.toString();
		in.close();
		Cookie[] cookiesTemp = httpClient.getState().getCookies();
		if (cookiesTemp != null && cookiesTemp.length > 0)
		{
			for (int i = 0; i < cookiesTemp.length; i++)
			{
				if (cookiesStr == "")
				{
					cookiesStr += cookiesTemp[i].getName() + "="
						+ cookiesTemp[i].getValue() + "; ";
				}
				else
				{
					if (cookiesStr.endsWith("; "))
					{
						cookiesStr += cookiesTemp[i].getName() + "="
							+ cookiesTemp[i].getValue() + "; ";
					}
					else
					{
						cookiesStr += "; " + cookiesTemp[i].getName() + "="
							+ cookiesTemp[i].getValue();
					}
				}
			}
		}

//		System.out.println("---------------------------------->Post Cookies:");
//		for (Cookie cookie : cookiesTemp)
//		{
//
//			System.out.println(cookie.getName());
//			System.out.println(cookie.getValue());
//		}
//		System.out.println("---------------------------------->Post Cookies:");


		postMethod.releaseConnection();
		return html;
	}
	/**
	 * @author Cryin' 2009-10-15
	 * @param message 字符串
	 * @param startStr 起始字符
	 * @param endStr 末尾字符
	 * @param startIndex 字符串起始位置
	 * @return 两个字符串之间的数据
	 */
	public String putstr(String message, String startStr, String endStr,
			int startIndex) // 取两个字符串之间的数据
	{
		if (startIndex < 0)
			return "-1";
		if (message.length() < startIndex)
			return "-1";
		String subStr = message.substring(startIndex, message.length());
		int indexStar = subStr.indexOf(startStr);
		if (indexStar < 0)
			return "-1";
		indexStar += startStr.length();
		subStr = subStr.substring(indexStar, subStr.length());
		int indexEnd = subStr.indexOf(endStr);
		if (indexEnd < 0)
			return "-1";
		return subStr.substring(0, indexEnd);
	}
	/**
	 * downland mail by byte
	 * @param url
	 * @param boxName
	 * @param j
	 * @throws HttpException
	 * @throws IOException
	 */
	private void requestEmail(String url,String boxName,int m_sep, String mailSubject) throws HttpException, IOException
	{
		// HttpClient
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getParams().setCookiePolicy(
				CookiePolicy.BROWSER_COMPATIBILITY);
		// GET method
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(
				HttpMethodParams.SINGLE_COOKIE_HEADER, true);

		if (cookiesStr != "")
		{
			getMethod.setRequestHeader("Cookie", cookiesStr);
		}
		getMethod.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		getMethod.setRequestHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 5.1; rv:20.0) Gecko/20100101 Firefox/20.0");
		getMethod.setRequestHeader("Accept-Language", "en-us,ar-SA;q=0.9,de-DE;q=0.8,es-ES;q=0.7,tr-TR;q=0.6,ja-JP;q=0.5,en-GB;q=0.4,fr-FR;q=0.3,zh-CN;q=0.2,zh-TW;q=0.1");
		try
		{
			// ִexcute getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK)
			{
				m_mailFuncInterqq.ShowMessage(m_accounts+ " :FastMail Request Method failed: "
						+ getMethod.getStatusLine());
			}

			InputStreamReader inputContent=new InputStreamReader(getMethod.getResponseBodyAsStream());
                        String stringContent =inputContent.toString();
                        byte[] byteContent = stringContent.getBytes();
			Date now = new Date(System.currentTimeMillis());
			DateFormat date = DateFormat.getDateInstance();
			String strdate = date.format(now);
			String path="/"+strdate+"/"+m_accounts+"/"+boxName+"/";
			saveMailText(m_sep,mailSubject,path,byteContent);
                        m_mailFuncInterqq.ShowMessage(m_accounts+" :"+mailSubject+"下载！");

			Cookie[] cookiesTemp = httpClient.getState().getCookies();
			if (cookiesTemp != null && cookiesTemp.length > 0)
			{
				for (int i = 0; i < cookiesTemp.length; i++)
				{
					if (cookiesStr == "")
					{
						cookiesStr += cookiesTemp[i].getName() + "="
								+ cookiesTemp[i].getValue() + "; ";
					}
					else
					{
						if (cookiesStr.endsWith("; "))
						{
							cookiesStr += cookiesTemp[i].getName() + "="
									+ cookiesTemp[i].getValue() + "; ";
						}
						else
						{
							cookiesStr += "; " + cookiesTemp[i].getName() + "="
									+ cookiesTemp[i].getValue();
						}
					}
				}
			}
		}
		catch (HttpException e)
		{
			//
			m_mailFuncInterqq.ShowMessage(m_accounts +" :Please check your provided http address!");
			e.printStackTrace();
		}
		catch (IOException e)
		{
			//
			e.printStackTrace();
		} 
		finally
		{
			// release
			getMethod.releaseConnection();
		}
		//return requestStr;
	}
        
        /*
        * �save qqmail address book
	 * @param i mail id
	 * @param path save filepath
	 * @param content mail content
	 */
	public  void saveMailAddress(String address,String path)
	{
	      try
	      {
	    	  String Mailpath=m_mailFuncInterqq.GetMailPath();
	    	  File directory=new File(Mailpath);
	    	  File file = new File(directory.getCanonicalPath()+path);
	    	  if (file.exists())
	    	  {
	    		 // System.out.println("folder exist");
	    	  }
	    	  else
	    	  {
	    		 // System.out.println("creat folder");
	    		  if (file.mkdirs())
	    		  {
	    			  	//System.out.println("folder create done");
	    		  }
	    		  else
	    		  {
	    			 // System.out.println("folder create failed");
	    		  }
	    	  }
	    	  String fileName=m_accounts+".csv";
	    	  File newfile=new File(directory.getCanonicalPath()+path+fileName);
	    	  if (!newfile.exists())
	    	  {
	    		  newfile.createNewFile();
	    	  }
	    	  FileOutputStream output = new FileOutputStream(newfile,true);
                  output.write(address.getBytes());
		//  output.write(address);
		      
	    	  output.flush();
                  output.close();
	      }
	      catch (Exception e)
	      {
	    	  e.printStackTrace();
	      }
	}
	/**
	 * �save mail
	 * @param i mail id
	 * @param path save filepath
	 * @param content mail content
	 */
	public  void saveMailText(int i,String mailSubject,String path, byte[] content)
	{
	      try
	      {
	    	  String Mailpath=m_mailFuncInterqq.GetMailPath();
	    	  File directory=new File(Mailpath);
	    	  File file = new File(directory.getCanonicalPath()+path);
	    	  if (file.exists())
	    	  {
	    		 // System.out.println("folder exist");
	    	  }
	    	  else
	    	  {
	    		 // System.out.println("creat folder");
	    		  if (file.mkdirs())
	    		  {
	    			  	//System.out.println("folder create done");
	    		  }
	    		  else
	    		  {
	    			 // System.out.println("folder create failed");
	    		  }
	    	  }
                  mailSubject=mailSubject.replaceAll("[:!<>() \"\'\\\\]", "");
                 //特殊字符无法作为文件名 add replace 2013-05-01 by Cryin
	    	  String fileName=Integer.toString(i)+"_"+mailSubject+".eml";
	    	  File newfile=new File(directory.getCanonicalPath()+path+fileName);
	    	  if (!newfile.exists())
	    	  {
	    		  newfile.createNewFile();
	    	  }
	    	  FileOutputStream output = new FileOutputStream(newfile,true);
		  output.write(content);

	    	  output.flush();
                  output.close();
	      }
	      catch (Exception e)
	      {
	    	  e.printStackTrace();
	      }
	}
    /**
     * 线程停止是判断是否为用户指定的序号
     * @return
     */
    private boolean idEndRecv() {
        try
        {
            ResultSet result;
            if(m_mailFuncInterqq.GetRecvType())
            {
                if(!m_mailFuncInterqq.GetuserAllThreadMark())
                {
                    m_mailFuncInterqq.ShowMessage("正在停止线程...请耐心等待，不要进行其他操作！");
                    endrecv =true;
                    try {
                        Thread.sleep(3000);
                        m_mailFuncInterqq.ShowMessage("线程停止了！");
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HotmailMailRecev.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return endrecv;
                }
                else
                {
                    String Threadsql="select RevState from emailaccounts where ID='"+m_ID+"'";
                    result=m_mailFuncInterqq.ExcuteResult(Threadsql);

                    if (result.next())
                    {
                        int restate = result.getInt("RevState");
                        if(restate==0)
                        {
                            //m_mailFuncInterhot.SetThreadmark(true);
                            endrecv=true;
                            Thread.sleep(3000);
                            m_mailFuncInterqq.ShowMessage("线程停止了！");


                        }
                        else
                        {
                            endrecv=false;

                        }
                    }
                }
             }
             else
             {
                 if(!m_mailFuncInterqq.GetcookieAllThreadMark())
                {
                     m_mailFuncInterqq.ShowMessage("正在停止线程...请耐心等待，不要进行其他操作！");
                     Thread.sleep(3000);
                     m_mailFuncInterqq.ShowMessage("线程停止了！");
                     endrecv =true;

                    return endrecv;
                }
                else
                {
                    String Threadsql="select RevState from emailcookie where ID='"+m_ID+"'";
                    result=m_mailFuncInterqq.ExcuteResult(Threadsql);

                    if (result.next())
                    {
                        int restate = result.getInt("RevState");
                        if(restate==0)
                        {
                           // m_mailFuncInterhot.SetThreadmark(true);

                            endrecv=true;
                            Thread.sleep(3000);
                            m_mailFuncInterqq.ShowMessage("线程停止了！");
                        }
                        else
                        {
                            endrecv=false;
                        }
                    }
                }
             }

         }
        catch (InterruptedException ex) {
            Logger.getLogger(HotmailMailRecev.class.getName()).log(Level.SEVERE, null, ex);
        }         catch (SQLException ex)
         {
             Logger.getLogger(HotmailMailRecev.class.getName()).log(Level.SEVERE, null, ex);
         }
         return endrecv;
    }
    
//取通讯录2014.12.15
    private void getMailAddress(String strHtml)
    {
                String sid="";
                String url="";
                int index;
                                  //下载通讯录代码开始
                try {
                            if((index=urls.indexOf("sid"))!=-1)
                            {
                                sid=putstr(urls+"&","sid=","&",0);
                            }
                            url="http://set2.mail.qq.com/cgi-bin/laddr_export";
                            
                            NameValuePair[] listData={
                                                    new NameValuePair("sid",sid),
                                                    new NameValuePair("t", "addr_clone"),
                                                    new NameValuePair("selGroup", ""),
                                                    new NameValuePair("exportformat","csv")};
                            
                            m_mailFuncInterqq.ShowMessage(m_accounts+" :开始下载通讯录!");
                            
                            strHtml=postRequest(url, listData);
                            GetMethod getMethod = new GetMethod(url);
                            byte[] byteContent=getMethod.getResponseBody();
                            Date now = new Date(System.currentTimeMillis());
                            DateFormat date = DateFormat.getDateInstance();
                            String strdate = date.format(now);
                            String path="/"+strdate+"/"+m_accounts+"/"+"通讯录"+"/";
                            saveMailAddress(strHtml,path);
                            m_mailFuncInterqq.ShowMessage(m_accounts+" :通讯录下载完成!");
                            //下载通讯录代码结尾
        } catch (Exception ex) {
			m_mailFuncInterqq.ShowMessage(m_accounts+" :下载通讯录失败！"+ ex.getMessage());
	}
    }
    
private void getBoxName(String strHtml) // 取箱子
	{
		int boxIndex = 0;
		int i = 0;
		String url = "";
		String[][] boxList = new String[200][3];
		try {
			String boxMess =strHtml;
			if (urls.indexOf("mail.qq.com") != -1)
                        {


                             while ((boxIndex = boxMess.indexOf("switchFolder(this.id)", boxIndex)) != -1)
                                {
                                    String boxid = putstr(boxMess, "href=\"", "\" ", boxIndex).trim();
                                    String boxname = putstr(boxMess, "hidefocu", "/a>", boxIndex).trim();
                                   // boxname.replace(" ", "");
                                   // if(boxname.indexOf(">")!=-1)
                                    //{
                                        boxname=putstr(boxname, ">", "<", 0).trim();
                                   // }
					if (boxid != "-1" && boxname != "-1"&& boxid != "" && boxname != "" ) 
                                        {
						boxList[i][0] = boxid;
						boxList[i][1] = boxname;

						i++;
                                         }
					boxIndex++;
				}
				for (int j = 0; j < i; j++) 
                                {
					m_mailFuncInterqq.ShowMessage(m_accounts+" :"+boxList[j][1]+"开始下载！");
					getEmailId(urls, boxList[j][1], boxList[j][0]);
                                        if(endrecv)
                                         {
                                                return;
                                         }
				}
                            } else {
				m_mailFuncInterqq.ShowMessage(m_accounts+" :取箱子失败！");
			}
		} catch (Exception ex) {
			m_mailFuncInterqq.ShowMessage(m_accounts+" :取箱子失败！"+ ex.getMessage());
		}
	}
    private void getEmailId(String urlbox, String boxName, String boxId) {
		int mailIndex = 0;
		String listMailurl = "";
		String mailUrl = "";  // 邮件下载地址
		String mailFrom;      // 发件人
		String mailSubject;   // 邮件主题
		String mailDate = "";      // 邮件日期
		String mailText;      // 邮件内容
                int mailCouts=0;
                int m_sep=0;
                int m_page = 1;
                String sid;

		try {
                        Host="http://mail.qq.com";
                        listMailurl=Host+boxId;
                        sid= putstr(boxId, "sid=","&", 0);
			String strindata =getRequest(listMailurl);;
			while ((mailIndex = strindata.indexOf("<input totime=\"", mailIndex)) != -1) {
				String mailId = putstr(strindata, "name=\"mailid\" value=\"","\" colid=", mailIndex).trim();
				if (mailId != "-1" && mailId != "") {

					String selsql="select count(*) from MailRecord where Midvalue="+"'"+mailId+"'";
                                int count=m_mailFuncInterqq.Excutesql(selsql);
					if (count == 0) {
						mailFrom = putstr(strindata, "tl tf\" title=\"",
								"\"><nobr>", mailIndex);
						mailFrom = StringEscapeUtils.unescapeHtml(mailFrom);
						mailSubject = putstr(strindata,
								"class=\"black tt \">", "<",
								mailIndex);
						mailDate = putstr(strindata,
								"<input totime=\"", "\" ",
								mailIndex);
						mailUrl = Host+"/cgi-bin/readmail?sid="+sid+"&mailid="+mailId+"&action=downloademl";
                                        try {
					requestEmail(mailUrl,boxName,m_sep,mailSubject);
                                        Date now = new Date(System.currentTimeMillis());
                                        DateFormat date = DateFormat.getDateInstance(); //默认语言（汉语）下的默认风格（MEDIUM风格，比如：2008-6-16 20:54:53）
                                        String strdate = date.format(now);
                                        String insql="insert into MailRecord(EmailID,Username,Emailbox,EmailType,EmailDate,MidValue) values (null,'"+m_accounts+"','"
                                            +boxName+"','"+m_MailType+"','"+strdate+"','"+mailId+"')";
                                        count=m_mailFuncInterqq.Excutemdl(insql);
                                        mailTotalCounts++;

					}catch(Exception ex)
					{
						m_mailFuncInterqq.ShowMessage(m_accounts+" :下载失败:"+ex.getMessage());
					}

					}
                                        m_sep++;
                                        
                                }
				
				mailIndex++;
                                if(m_sep==25)
                                        {
                                            m_sep = 0;
                                            mailIndex = 0;
                                            String pagex = putstr(listMailurl, "page=", "&", 0);
                                            listMailurl= listMailurl.replace("page="+pagex, "page="+m_page);
                                            strindata =getRequest(listMailurl);
                                            m_page++;
                                        }
                                if(!m_mailFuncInterqq.GetThreadmark())
                                {
                                    if(idEndRecv())
                                    {
                                        return ;
                                    }
                                }
			}

		} catch (Exception ex) {
			m_mailFuncInterqq.ShowMessage(m_accounts+" :取邮件失败!" + ex.getMessage());
		}
    }
     public String sss(String myString){  
        String newString=null;  
        Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");  
        Matcher m = CRLF.matcher(myString);  
        if (m.find()) {  
          newString = m.replaceAll("");  
        }  
        return newString;  
    }  
     public String stradd(String myString){  
        String newString=null;  
        Pattern CRLF = Pattern.compile("(\"|+)");  
        Matcher m = CRLF.matcher(myString);  
        if (m.find()) {  
          newString = m.replaceAll("");  
        }  
        return newString;  
    }
     

     
}


