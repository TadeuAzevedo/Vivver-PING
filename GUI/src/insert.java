import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class insert {
	
	insert(){
		// Image
		ImageIcon icon = new ImageIcon("img/transparentVV.png");
		ImageIcon logo = new ImageIcon("img/logo.png");
		Image image = logo.getImage();
		Image novaImg = image.getScaledInstance(200, 55, java.awt.Image.SCALE_SMOOTH);
		logo = new ImageIcon(novaImg);
		// Declaration
		JFrame frame = new JFrame("Vivver Sistemas - Inserir cliente");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
		JLabel lb1 = new JLabel("Inserir cliente", SwingConstants.CENTER);
		JLabel lb2 = new JLabel("Cliente:");
		JLabel lb3 = new JLabel("URL:");
		JTextField tf1 = new JTextField();
		JTextField tf2 = new JTextField();
		JButton button = new JButton("Enviar");
		// Button
		button.setVisible(true);
		button.setFocusable(false);
		button.setFont(new Font("Arial", Font.BOLD, 18));
		button.setForeground(new Color(0xeeeeee));
		button.setBackground(new Color(0x01659e));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBorder(new EmptyBorder(10,0,10,0));
		button.setPreferredSize(new Dimension(400,50));
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0x259821));
			}
			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setBackground(new Color(0x01659e));
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==button) {
					System.out.println("teste");
				}
			}
		});
		// Label 1
		lb1.setFont(new Font("Arial", Font.BOLD, 34));
		//lb1.setPreferredSize(new Dimension(500, 50));
		// Label2
		lb2.setFont(new Font("Arial", Font.BOLD, 18));
		lb2.setPreferredSize(new Dimension(400, 50));
		// Label3
		lb3.setFont(new Font("Arial", Font.BOLD, 18));
		lb3.setPreferredSize(new Dimension(400, 50));
		// TextField 1
		tf1.setPreferredSize(new Dimension(400, 30));
		tf1.setFont(new Font("Arial", Font.PLAIN, 15));
		// TextField 2
		tf2.setPreferredSize(new Dimension(400, 30));
		tf2.setFont(new Font("Arial", Font.PLAIN, 15));
		// Panel 1
		panel1.setBackground(new Color(0xeeeeee));
		panel1.setBorder(new EmptyBorder(10,10,10,10));
		panel1.setLayout(new BorderLayout());
			panel1.add(panel5, BorderLayout.CENTER);
				panel5.add(lb2);
				panel5.add(tf1);
				panel5.add(lb3);
				panel5.add(tf2);
		panel1.add(button, BorderLayout.SOUTH);
		// Panel 2
		panel2.setLayout(new BorderLayout());
		panel2.setBackground(Color.white);
		panel2.setPreferredSize(new Dimension(0, 100));
		panel2.add(lb1);
		
		// Panel 3
		panel3.setBackground(new Color(0x01659e));
		panel3.setPreferredSize(new Dimension(0, 30));
		
		panel2.add(panel3, BorderLayout.NORTH);
		
		// Panel 4
		panel4.setBackground(new Color(0x01659e));
		panel4.setPreferredSize(new Dimension(0, 20));
		// Frame
		frame.setLayout(new BorderLayout());
		frame.add(panel1, BorderLayout.CENTER);
		frame.add(panel2, BorderLayout.NORTH);
		frame.add(panel4, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500,600);
		frame.setIconImage(icon.getImage());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
