import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class GUI {

	int i = 0;
	String sucesso = "Host disponivel\n\n";
	String falha = "Host invalido\n\n";
	JTextPane tp = new JTextPane();
	URL url;
	String original = "";
	
	public void solicitacaoPing(String enderecoIP)
	throws UnknownHostException, IOException{
		InetAddress vivver = InetAddress.getByName(enderecoIP);
		String ip = vivver.toString();
		ip = ip.replace("/","");
		
		if(vivver.isReachable(20)){
			//System.out.println(url + " " +  ip + " - até 20ms");
		}else if(vivver.isReachable(30)){
			//System.out.println(url + " " + ip + " - até 30ms");
		}else if(vivver.isReachable(40)){
			//System.out.println(url + " " + ip + " - até 40ms");
		}else if(vivver.isReachable(50)){
			//System.out.println(url + " " + ip + " - até 50ms");
		}else if(vivver.isReachable(100)){
			//System.out.println(url + " " + ip + " - até 100ms");
			appendToPane(tp, url + "  " + original + " " + falha, new Color(0xdaa520));
		}else {
			//System.out.println(url + " " + ip + " - Lentidão no sistema");
			appendToPane(tp, url + "  " + original + " " + falha, new Color(0xff8c00));
		}
	}
	
	GUI(){
	
		ImageIcon icon = new ImageIcon("img/transparentVV.png");
		ImageIcon logo = new ImageIcon("img/logo.png");
		Image image = logo.getImage();
		Image novaImg = image.getScaledInstance(200, 55, java.awt.Image.SCALE_SMOOTH);
		logo = new ImageIcon(novaImg);
		
		JFrame frame = new JFrame("Vivver Sistemas - Verificador de Status");
		JPanel panel0 = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JLabel label1 = new JLabel();
		JLabel label2 = new JLabel("Verificador de Status", SwingConstants.CENTER);
		JLabel label3 = new JLabel("Clientes", SwingConstants.CENTER);
		JLabel labelB = new JLabel();
		JButton button1 = new JButton("Verificação por ping");
		JButton button3 = new JButton("Verificação por HTTP");
		JButton button2 = new JButton("Limpar");
		JScrollPane scroll = new JScrollPane();
		tp.setBackground(null);
		tp.setBorder(new EmptyBorder(10,10,10,10));
		
		File arquivo1 = new File("clientes.csv");
		try {
			if(!arquivo1.exists()){
				System.out.println("Arquivo não encontrado");
			}
			FileReader fr = new FileReader(arquivo1);
			BufferedReader br = new BufferedReader(fr);
		
			Vector<String> linhas = new Vector<String>();
			String linha = "";
			
			while((linha = br.readLine()) != null) {
				String[] partes = linha.split(";");
				linhas.add(partes[1]);
				i++;
			}
			JList<String> lista = new JList<String>(linhas);
			MouseListener mouseListener = new MouseAdapter() {
			    public void mouseClicked(MouseEvent e) {
			        if (e.getClickCount() == 2) {	
						int index = lista.getSelectedIndex();
						//System.out.println(index + 1);
						//String s = lista.getSelectedValue();
						//System.out.println(s);
						String linha2 = "";
						
						try {
							FileReader frT = new FileReader(arquivo1);
							BufferedReader brT = new BufferedReader(frT);
							
							try {
								while((linha2 = brT.readLine()) != null) {
									String[] parts = linha2.split(";");
									int partInt = Integer.parseInt(parts[0]);
									if((index + 1) == partInt) {
										//System.out.println(parts[1]);
										//System.out.println(parts[2]);
										URL url = new URL(parts[2]);
										try {
											InetAddress endereco = InetAddress.getByName(url.getHost());
											String temp = endereco.toString();
											String ip = temp.substring(temp.indexOf("/")+1, temp.length());
											if(url.toString().length() < 70) {
												int numPontos = 70 - url.toString().length();
												char c = '.';
																			
												char[] repeat = new char[numPontos];
												Arrays.fill(repeat, c);
												original = new String(repeat);
												//System.out.print(url + "  " + original + " " + sucesso);
												appendToPane(tp, url + "  " + original + " " + sucesso, new Color(0x259821));
											}
											else {
												System.out.print(url);
											}
											solicitacaoPing(ip);
										}catch(UnknownHostException e2){
											if(url.toString().length() < 70) {
												int numPontos = 70 - url.toString().length();
												char c = '.';
												
												char[] repeat = new char[numPontos];
												Arrays.fill(repeat, c);
												original = new String(repeat);
												//System.out.println(url + "  " + original + " " + falha);
												appendToPane(tp, url + "  " + original + " " + falha, Color.red);
											}
											else {
												System.out.print(url);
											}
										}
									}
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						} catch (FileNotFoundException e1) {
							e1.printStackTrace();
						}
			        }
			    }
			};
			lista.addMouseListener(mouseListener);
			lista.setForeground(Color.black);
			lista.setFont(new Font("Calibri", Font.PLAIN, 14));
			lista.setBackground(new Color(0xcccccc));
			lista.setBorder(BorderFactory.createEmptyBorder());
			lista.setSelectionBackground(new Color(0xcccccc));
			scroll.setViewportView(lista);
		
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		button1.setVisible(true);
		button1.setPreferredSize(new Dimension(375, 40));
		button1.setFocusable(false);
		button1.setFont(new Font("Arial", Font.BOLD, 20));
		button1.setForeground(new Color(0xeeeeee));
		button1.setBackground(new Color(0x01659e));
		button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button1.setBackground(new Color(0x259821));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button1.setBackground(new Color(0x01659e));
			}
		});
		appendToPane(tp, "", Color.green);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button1) {
					tp.setText("");
					File arquivo = new File("clientes.csv");
					try{
						if(!arquivo.exists()){
							//System.out.println("Arquivo não encontrado");
							appendToPane(tp, "Arquivo nao encontrado", Color.black);
						}
						FileReader fr = new FileReader(arquivo);
						BufferedReader br = new BufferedReader(fr);
						while(br.ready()){
							String linha = br.readLine();
							String[] partes = linha.split(";");
							url = new URL(partes[2]);
							try{
								InetAddress endereco = InetAddress.getByName(url.getHost());
								String temp = endereco.toString();
								String ip = temp.substring(temp.indexOf("/")+1, temp.length());
								//System.out.print(url + "  ...........  ");
								if(url.toString().length() < 70) {
									int numPontos = 70 - url.toString().length();
									
									char c = '.';
									
									char[] repeat = new char[numPontos];
									Arrays.fill(repeat, c);
									original = new String(repeat);
									//System.out.print(url + "  " + original + " " + sucesso);
									appendToPane(tp, url + "  " + original + " " + sucesso, new Color(0x259821));
								}
								else {
									System.out.print(url);
								}
								solicitacaoPing(ip);
								//System.out.println(i);
								
							}catch(UnknownHostException e2){
								if(url.toString().length() < 70) {
									int numPontos = 70 - url.toString().length();
									//String original = "";
									char c = '.';
									
									char[] repeat = new char[numPontos];
									Arrays.fill(repeat, c);
									original = new String(repeat);
									//System.out.println(url + "  " + original + " " + falha);
									appendToPane(tp, url + "  " + original + " " + falha, Color.red);
								}
								else {
									System.out.print(url);
								}
							}
						}
						br.close();
						fr.close();
					}catch(IOException ex){
						ex.printStackTrace();
					}
				}
			}
		});
		
		button3.setVisible(true);
		button3.setPreferredSize(new Dimension(375, 40));
		button3.setFocusable(false);
		button3.setFont(new Font("Arial", Font.BOLD, 20));
		button3.setForeground(new Color(0xeeeeee));
		button3.setBackground(new Color(0x01659e));
		button3.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button3.setBackground(new Color(0x259821));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button3.setBackground(new Color(0x01659e));
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button3) {
					tp.setText("");
					File arquivo = new File("clientes.csv");
					try{
						if(!arquivo.exists()){
							appendToPane(tp, "Arquivo nao encontrado", Color.black);
						}
						FileReader fr = new FileReader(arquivo);
						BufferedReader br = new BufferedReader(fr);
						
						Vector<String> linhas = new Vector<String>();
						String linha = "";
						
						URL url = null;
						
						while((linha = br.readLine()) != null) {
							String[] partes = linha.split(";");
							linhas.add(partes[2]);
							url = new URL(partes[2]);
							HttpURLConnection connection = (HttpURLConnection)url.openConnection();
							connection.setRequestMethod("GET");
							connection.connect();
					
							int code = connection.getResponseCode();
							String string = connection.getResponseMessage();
							
							String codeS = String.valueOf(code);
							
							if(code == 200 || code == 301) {
								if(url.toString().length() < 70) {
									int numPontos = 70 - url.toString().length();
									
									char c = '.';
									
									char[] repeat = new char[numPontos];
									Arrays.fill(repeat, c);
									original = new String(repeat);
									appendToPane(tp, url + "  " + original + " " + string + "\n\n", new Color(0x259821));
								}
							}else {
								if(url.toString().length() < 70) {
									int numPontos = 70 - url.toString().length();
									
									char c = '.';
									
									char[] repeat = new char[numPontos];
									Arrays.fill(repeat, c);
									original = new String(repeat);
									appendToPane(tp, url + "  " + original + " " + string + "\n\n", Color.red);
								}
							}
							
							//System.out.println(url.toString() + " - " + code);
							//System.out.println(url.toString() + " - " + string);
						}
						
						br.close();
						fr.close();
					}catch(IOException ex){
						ex.printStackTrace();
					}
				}
			}
		});
		
		button2.setHorizontalAlignment(SwingConstants.CENTER);
		button2.setPreferredSize(new Dimension(0, 40));
		button2.setFocusable(false);
		button2.setFont(new Font("Arial", Font.BOLD, 20));
		button2.setForeground(new Color(0xeeeeee));
		button2.setBackground(new Color(0x01659e));
		button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button2.setBackground(new Color(0x259821));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button2.setBackground(new Color(0x01659e));
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button2) {
					tp.setText("");
				}
			}
		});
		
		scroll.setPreferredSize(new Dimension(20,0));
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getVerticalScrollBar().setBackground(new Color(0xcccccc));
		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(0x01659e);
			}
		});
		
		label2.setFont(new Font("Arial", Font.BOLD, 36));
		
		label3.setFont(new Font("Arial", Font.BOLD, 20));
		label3.setForeground(new Color(0x333333));
		
		labelB.setLayout(new BorderLayout());
		labelB.add(button1, BorderLayout.WEST);
		labelB.add(button3, BorderLayout.EAST);
		labelB.setPreferredSize(new Dimension(800,40));

		panel5.setBorder(new EmptyBorder(5,5,0,5));
		panel5.setBackground(new Color(0xcccccc));
		panel5.setLayout(new BorderLayout());
		panel5.add(scroll, BorderLayout.CENTER);
		
		panel0.setBackground(new Color(0x01659e));
		panel0.setPreferredSize(new Dimension(0, 30));
		
		panel1.setBackground(new Color(0xeeeeee));
		panel1.setPreferredSize(new Dimension(600, 300));
		panel1.setBorder(new EmptyBorder(10,10,10,10));
		panel1.setLayout(new BorderLayout());
		panel1.add(labelB, BorderLayout.NORTH);
		panel1.add(button2, BorderLayout.SOUTH);
		
		//panel1.add(scroll2, BorderLayout.CENTER);
		JScrollPane scroll2 = new JScrollPane(tp);
		scroll2.setPreferredSize(new Dimension(20,0));
		scroll2.setBorder(BorderFactory.createEmptyBorder());
		scroll2.getVerticalScrollBar().setBackground(new Color(0xeeeeee));
		scroll2.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(0x01659e);
			}
		});
		
		panel1.add(scroll2);
		
		panel2.setBackground(new Color(0xcccccc));
		panel2.setPreferredSize(new Dimension(200, 0));
		panel2.setLayout(new BorderLayout());
		panel2.setBorder(new EmptyBorder(10, 0, 0, 0));
		
		panel3.setBackground(Color.white);
		panel3.setPreferredSize(new Dimension(0, 100));
		panel3.setLayout(new BorderLayout());
		label1.setIcon(logo);
		
		panel4.setBackground(new Color(0x01659e));
		panel4.setPreferredSize(new Dimension(0, 20));
		
		frame.setLayout(new BorderLayout());
		
		panel3.add(label1, BorderLayout.WEST);
		panel3.add(panel0, BorderLayout.NORTH);
		panel3.add(label2, BorderLayout.CENTER);
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.WEST);
		frame.add(panel3, BorderLayout.NORTH);
		panel2.add(panel4, BorderLayout.SOUTH);
		panel2.add(label3, BorderLayout.NORTH);
		panel2.add(panel5, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,600);
		frame.setMinimumSize(new Dimension(700,400));
		frame.setIconImage(icon.getImage());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        StyledDocument doc = tp.getStyledDocument();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Courier New");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);
        aset = sc.addAttribute(aset, StyleConstants.FontSize, 14);
        
        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
        tp.setEditable(false);
        
        try {
			doc.insertString(doc.getLength(), msg, aset);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
    }
}