/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * AddCookieFrame.java
 *
 * Created on 2010-1-18, 18:48:09
 */

package email;

import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class AddCookieFrame extends javax.swing.JFrame {

    /** Creates new form AddCookieFrame */
    public AddCookieFrame(MailFuncInter mailInter) {
        initComponents();
        m_mailInter=mailInter;
        this.jComboBoxMailtyoe.setSelectedIndex(-1);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonExit = new javax.swing.JButton();
        jButtonadd = new javax.swing.JButton();
        jButtonreset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextIp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextBrowser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextlogo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextcookie = new javax.swing.JTextArea();
        jComboBoxMailtyoe = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(email.EmailApp.class).getContext().getResourceMap(AddCookieFrame.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jButtonExit.setText(resourceMap.getString("jButtonExit.text")); // NOI18N
        jButtonExit.setName("jButtonExit"); // NOI18N
        jButtonExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonExitMouseClicked(evt);
            }
        });

        jButtonadd.setText(resourceMap.getString("jButtonadd.text")); // NOI18N
        jButtonadd.setName("jButtonadd"); // NOI18N
        jButtonadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonaddMouseClicked(evt);
            }
        });
        jButtonadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddActionPerformed(evt);
            }
        });

        jButtonreset.setText(resourceMap.getString("jButtonreset.text")); // NOI18N
        jButtonreset.setName("jButtonreset"); // NOI18N
        jButtonreset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonresetMouseClicked(evt);
            }
        });

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jTextUser.setText(resourceMap.getString("jTextUser.text")); // NOI18N
        jTextUser.setName("jTextUser"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextIp.setText(resourceMap.getString("jTextIp.text")); // NOI18N
        jTextIp.setName("jTextIp"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jTextBrowser.setText(resourceMap.getString("jTextBrowser.text")); // NOI18N
        jTextBrowser.setName("jTextBrowser"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jTextlogo.setText(resourceMap.getString("jTextlogo.text")); // NOI18N
        jTextlogo.setName("jTextlogo"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextcookie.setColumns(20);
        jTextcookie.setRows(5);
        jTextcookie.setName("jTextcookie"); // NOI18N
        jScrollPane1.setViewportView(jTextcookie);

        jComboBoxMailtyoe.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "163", "126", "yeah", "qq", "sina" }));
        jComboBoxMailtyoe.setName("jComboBoxMailtyoe"); // NOI18N
        jComboBoxMailtyoe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMailtyoeActionPerformed(evt);
            }
        });

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonadd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(jButtonreset, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxMailtyoe, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextUser, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(jTextBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextIp, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                        .addComponent(jTextlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel2)
                        .addGap(36, 36, 36))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextBrowser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextlogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxMailtyoe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonadd)
                    .addComponent(jButtonreset)
                    .addComponent(jButtonExit))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonExitMouseClicked
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_jButtonExitMouseClicked

    private void jButtonresetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonresetMouseClicked
        // TODO add your handling code here:
        this.jTextUser.setText(null);
        this.jTextIp.setText(null);
        this.jTextBrowser.setText(null);
        this.jTextlogo.setText(null);
        this.jTextcookie.setText(null);
    }//GEN-LAST:event_jButtonresetMouseClicked

    private void jButtonaddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonaddMouseClicked
        // TODO add your handling code here:
        //m_mailInter.Connectdb();
        if(CheckData())
        {
            int index;
            int count=0;
            String urltemp="";
            String sid="";
            String user=jTextUser.getText();
            String ip=jTextIp.getText();
            String browser=jTextBrowser.getText();
            String logo=jTextlogo.getText();
            String cookie=jTextcookie.getText();
            String url="";
            String MailType=(String) jComboBoxMailtyoe.getSelectedItem();
            if(MailType.indexOf("163") != -1)
            {
                if ((index = cookie.indexOf("mail_host")) != -1) 
                {
                    urltemp = putstr(cookie, "=", "com", index); 
                }
                else
                {
                    urltemp="twebmail.mail.163.";
                }
                if ((index = cookie.indexOf("Coremail")) != -1) 
                {
                    sid = putstr(cookie, "%", "%", index); 
                }
                url="http://"+urltemp+"com/js6/main.jsp?sid="+sid;
               //jTextFieldUrl.setText(url);
            }
            if(MailType.indexOf("126") != -1)
            {
                if ((index = cookie.indexOf("mail_host")) != -1) 
                {
                    urltemp = putstr(cookie, "=", "com", index);
                    if(urltemp.isEmpty())
                      {
                         urltemp="mail.126.";
                      }
                }
                else
                {
                    urltemp="twebmail.mail.126.";
                }
                if ((index = cookie.indexOf("Coremail=")) != -1) 
                {
                    sid = putstr(cookie, "%", "%", index); 
                }
                url="http://"+urltemp+"com/js6/main.jsp?sid="+sid;
               //jTextFieldUrl.setText(url);
            }
            if(MailType.indexOf("yeah") != -1)
            {
                if ((index = cookie.indexOf("mail_host")) != -1) 
                {
                    urltemp = putstr(cookie, "=", "net", index); 
                }
                else
                {
                    urltemp="twebmail.mail.yeah.";
                }
                if ((index = cookie.indexOf("Coremail")) != -1) 
                {
                    sid = putstr(cookie, "%", "%", index); 
                }
                url="http://"+urltemp+"net/js6/main.jsp?sid="+sid;
               //jTextFieldUrl.setText(url);
            }
            if(MailType.indexOf("qq") != -1)
            {
                //待确认qq收邮的url
                url="http://mail.qq.com/cgi-bin/login?fun=passport&from=webqq";
               //jTextFieldUrl.setText(url);
            }
            Date now = new Date(System.currentTimeMillis());
            DateFormat date = DateFormat.getDateTimeInstance();
            String Date = date.format(now);
            int RevLogo=0;
            int Revstate=0;
            String sql = "insert into emailcookies(ID,user,cookie,url,IP,Date,Browser,logo,RevLogo,RevState,MailType)" +
                        " values (null,'"+user+"','"+cookie+"','"+url+"','"+ip+"','"+Date+"','"+browser+"','"+logo+"','"+RevLogo+"','"+Revstate+"','"+MailType+"')";
            if(m_mailInter.Excutemdl(sql)==1)
            {
                JOptionPane.showMessageDialog(null, "添加成功！");
                m_mailInter.RefashCookie();

            }
            else
            {
                JOptionPane.showMessageDialog(null, "添加失败！");
            }

        }
    }//GEN-LAST:event_jButtonaddMouseClicked

    private void jComboBoxMailtyoeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMailtyoeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMailtyoeActionPerformed

    private void jButtonaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonaddActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MailFuncInter mail=null;
                new AddCookieFrame(mail).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonadd;
    private javax.swing.JButton jButtonreset;
    private javax.swing.JComboBox jComboBoxMailtyoe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextBrowser;
    private javax.swing.JTextField jTextIp;
    private javax.swing.JTextField jTextUser;
    private javax.swing.JTextArea jTextcookie;
    private javax.swing.JTextField jTextlogo;
    // End of variables declaration//GEN-END:variables
    private MailFuncInter m_mailInter;
    /**
     * 判断信息是否为空
     * @return
     */
    private boolean CheckData() {
        
        if(jTextUser.getText().length()==0)
        {
            JOptionPane.showMessageDialog(null, "用户信息不能为空！","提示",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(jTextIp.getText().length()==0)
        {
            JOptionPane.showMessageDialog(null, "IP信息不能为空！","提示",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(jTextBrowser.getText().length()==0)
        {
            JOptionPane.showMessageDialog(null,"浏览器信息不能为空！","提示",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(jTextlogo.getText().length()==0)
        {
            JOptionPane.showMessageDialog(null,"操作信息不能为空！","提示",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(jTextcookie.getText().length()==0)
        {
            JOptionPane.showMessageDialog(null,"cookie信息不能为空！","提示",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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
}
