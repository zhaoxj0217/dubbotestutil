package com.example.genericlocal.demo.swing;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.genericlocal.demo.util.DubboGenericInvokeUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SwingArea extends JFrame {

    private static SwingArea instance = null;
    private SwingArea() {
    }
    public static SwingArea getInstance() {
        if (null == instance) {
            synchronized (SwingArea.class) {
                if (null == instance) {
                    instance = new SwingArea();
                }
            }
        }
        return instance;
    }
    public void initUI() {
        this.setTitle("dubbo接口测试工具");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 200, 700, 800);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setContentPane(panel);

        //文本区域
        JLabel interfaceNameFild = new JLabel("接口类全名");
        interfaceNameFild.setBounds(10,10,100,25);
        this.getContentPane().add(interfaceNameFild);

        JTextField interfaceNameText = new JTextField("com.example.demo.demoService");
        interfaceNameText.setBounds(120,10,500,25);
        panel.add(interfaceNameText);

        JLabel methodFild = new JLabel("方法名称");
        methodFild.setBounds(10,40,100,25);
        this.getContentPane().add(methodFild);

        JTextField methodText = new JTextField("demoMethod");
        methodText.setBounds(120,40,500,25);
        panel.add(methodText);

        JLabel paramTypeFild = new JLabel("入参类型数组");
        paramTypeFild.setBounds(10,70,100,25);
        this.getContentPane().add(paramTypeFild);

        JTextField paramTypeText = new JTextField("[\"java.util.Map\"]");
        paramTypeText.setBounds(120,70,500,25);
        panel.add(paramTypeText);

        JLabel paramValueFild = new JLabel("入参类型数组");
        paramValueFild.setBounds(10,100,100,25);
        this.getContentPane().add(paramValueFild);

        JTextArea  paramValueText = new JTextArea("[{\n" +
                "                \"id\":\"123\",\n" +
                "                \"userId\":\"123\"\n" +
                "             }]",10,10);
        paramValueText.setBounds(120,100,500,250);
        paramValueText.setLineWrap(true);        //激活自动换行功能
        paramValueText.setWrapStyleWord(true);
        JScrollPane jsp2 = new JScrollPane(paramValueText);
        jsp2.setBounds(120,100,500,250);
        panel.add(jsp2);
//        panel.add(paramValueText);

        JLabel addressFild = new JLabel("dubbo注册地址");
        addressFild.setBounds(10,360,100,25);
        this.getContentPane().add(addressFild);

        JTextField addressText = new JTextField("zookeeper://ip:port");
        addressText.setBounds(120,360,500,25);
        panel.add(addressText);


        JLabel veriosnFild = new JLabel("接口版本");
        veriosnFild.setBounds(10,400,100,25);
        this.getContentPane().add(veriosnFild);

        JTextField veriosnText = new JTextField("1.0.0");
        veriosnText.setBounds(120,400,500,25);
        panel.add(veriosnText);

        JLabel responseFild = new JLabel("数据返回");
        responseFild.setBounds(10,480,100,25);
        this.getContentPane().add(responseFild);

        JTextArea  responseText = new JTextArea(10,10);
        responseText.setBounds(120,480,500,250);
        responseText.setLineWrap(true);        //激活自动换行功能
        responseText.setWrapStyleWord(true);
        JScrollPane jsp = new JScrollPane(responseText);
        jsp.setBounds(120,480,500,250);
        panel.add(jsp);


        JLabel readmeFild = new JLabel("主要通过dubbo的泛化调用实现");
        readmeFild.setBounds(120,740,500,25);
        panel.add(readmeFild);

        JButton invokeButton = new JButton("接口调用");
        invokeButton.setBounds(200, 440, 100, 25);
        invokeButton.addActionListener(new ActionListener() {		//为按钮添加点击事件监听器
            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "弹出对话框"+veriosnText.getText());
                String interfaceName = interfaceNameText.getText();
                if (StringUtils.isEmpty(interfaceName)){
                    JOptionPane.showMessageDialog(null, "接口类为空!");
                    return;
                }
                String method = methodText.getText();
                if (StringUtils.isEmpty(method)){
                    JOptionPane.showMessageDialog(null, "方法为空!");
                    return;
                }
                String paramType = paramTypeText.getText();
                String paramValue = paramValueText.getText();
                String address = addressText.getText();
                if (StringUtils.isEmpty(method)){
                    JOptionPane.showMessageDialog(null, "dubbo地址为空!");
                    return;
                }
                String version = veriosnText.getText();

                try{
                    Object result  = DubboGenericInvokeUtil.invoke(interfaceName, method,
                            JSONArray.parseArray(paramType,String.class),
                            JSONArray.parseArray(paramValue,Object.class), address, version);
                    responseText.setText(JSONObject.toJSONString(result));
                }catch (Exception e1){
                    responseText.setText("调用异常"+e1);
                }

            }
        });
        panel.add(invokeButton);

        this.setVisible(true);

    }

}

