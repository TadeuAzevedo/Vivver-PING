import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Vector;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class GUI {

	int i = 0;
	String sucesso = "Host disponivel\n";
	JTextArea textArea;
	
	public void solicitacaoPing(String enderecoIP)
	throws UnknownHostException, IOException{
		InetAddress vivver = InetAddress.getByName(enderecoIP);
		System.out.println("Enviando solicitacao de ping para " + enderecoIP);
		if(vivver.isReachable(5000)){
			System.out.println(sucesso);
			
		}else{
			System.out.println("Ping não alcançável");
		}
	}
	
	private void printLog() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }
	
	PrintStream standardOut;
	
	GUI(){
	
		ImageIcon icon = new ImageIcon("transparentVV.png");
		ImageIcon logo = new ImageIcon("logo.png");
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
		JButton button1 = new JButton("Iniciar verificação");
		JScrollPane scroll = new JScrollPane();
		JScrollPane scroll2 = new JScrollPane();
		
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(new Color(0xeeeeee));
		textArea.setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
		textArea.setFont(new Font("Lucida console", Font.PLAIN, 14));
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		
		standardOut = System.out;
		
		System.setOut(printStream);
		System.setErr(printStream);
		
		File arquivo1 = new File("nomes.txt");
		try {
			if(!arquivo1.exists()){
				System.out.println("Arquivo não encontrado");
			}
			FileReader fr = new FileReader(arquivo1);
			BufferedReader br = new BufferedReader(fr);
		
			Vector<String> linhas = new Vector<String>();
			String linha = "";
			while((linha = br.readLine()) != null) {
				linhas.add(linha);
			}
			JList<String> lista = new JList<String>(linhas);
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
		
		/*File arquivo2 = new File("clientes.txt");
		try {
			if(!arquivo2.exists()){
				System.out.println("Arquivo não encontrado");
			}
			FileReader fr = new FileReader(arquivo2);
			BufferedReader br = new BufferedReader(fr);
		
			Vector<String> linhas = new Vector<String>();
			String linha = "";
			while((linha = br.readLine()) != null) {
				linhas.add(linha);
			}
			JList<String> lista = new JList<String>(linhas);
			lista.setForeground(Color.black);
			lista.setFont(new Font("Lucida console", Font.PLAIN, 14));
			lista.setBackground(new Color(0xeeeeee));
			lista.setBorder(BorderFactory.createEmptyBorder());
			lista.setSelectionBackground(new Color(0xeeeeee));
			scroll2.setViewportView(lista);
		
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		} */
		
		button1.setVisible(true);
		button1.setPreferredSize(new Dimension(0, 40));
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
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button1) {
					printLog();
					File arquivo = new File("clientes.txt");
					try{
						if(!arquivo.exists()){
							System.out.println("Arquivo não encontrado");
						}
						FileReader fr = new FileReader(arquivo);
						BufferedReader br = new BufferedReader(fr);
						while(br.ready()){
							i++;
							String linha = br.readLine();
							URL url = new URL(linha);
							try{
								InetAddress endereco = InetAddress.getByName(url.getHost());
								String temp = endereco.toString();
								String ip = temp.substring(temp.indexOf("/")+1, temp.length());
								System.out.println(url);
								solicitacaoPing(ip);
								//System.out.println(i);
								
							}catch(UnknownHostException e2){
								System.out.println(url + " - Host invalido");
								//System.out.println(e);
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
		
		scroll.setPreferredSize(new Dimension(20,0));
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.getVerticalScrollBar().setBackground(new Color(0xcccccc));
		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(0x01659e);
			}
		});
		
		scroll2.setBorder(BorderFactory.createEmptyBorder());
		scroll2.getVerticalScrollBar().setBackground(new Color(0xeeeeee));
		scroll2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		scroll2.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			protected void configureScrollBarColors() {
				this.thumbColor = new Color(0x01659e);
			}
		});
		
		label2.setFont(new Font("Arial", Font.BOLD, 36));
		
		label3.setFont(new Font("Arial", Font.BOLD, 20));
		label3.setForeground(new Color(0x333333));
		
		labelB.setLayout(new BorderLayout());
		labelB.add(button1, BorderLayout.NORTH);
		labelB.setPreferredSize(new Dimension(0,40));

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
		//panel1.add(scroll2, BorderLayout.CENTER);
		panel1.add(new JScrollPane(textArea));
		
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
		frame.setIconImage(icon.getImage());
		frame.setVisible(true);
	}
}