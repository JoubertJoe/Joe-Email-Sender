package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import protocol.ProtocoloEmail;
import javax.swing.JPasswordField;


@SuppressWarnings("serial")
public class JoeEmailSender extends JFrame {
	private JTextField txtEmaildominiocom;
	private JTextField txtAssuntoDoEmail;
	private JButton btnEnviar;
	private JTextArea textCorpoDoEmail;
	private JTextField txtRemetente;
	private JLabel lblSenha;
	private JPasswordField passField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoeEmailSender window = new JoeEmailSender();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * Create the application.
	 * 
	 * Criando a aplica��o.
	 */
	public JoeEmailSender() {
		getContentPane().setBackground(Color.BLACK);
		setTitle("Joe Email Sender");
		setResizable(false);
		createContent();
		createActions();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * Inicializando conteudo da janela.
	 */
	private void initialize() {

		this.setBounds(100, 100, 560, 494);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
	}

	/**
	 * Creating the content of the frame.
	 * 
	 * Criando o conte�do da janela.
	 */
	private void createContent() {

		getContentPane().setLayout(null);
		// Email subject - Assunto do email
		txtAssuntoDoEmail = new JTextField();
		txtAssuntoDoEmail.setText("ASSUNTO");
		txtAssuntoDoEmail.setColumns(10);
		txtAssuntoDoEmail.setBounds(120, 120, 344, 20);
		getContentPane().add(txtAssuntoDoEmail);

		// Email recipient - Destinat�rio do Email
		txtEmaildominiocom = new JTextField();
		txtEmaildominiocom.setBounds(120, 89, 344, 20);
		txtEmaildominiocom.setText("");
		getContentPane().add(txtEmaildominiocom);
		txtEmaildominiocom.setColumns(10);

		JLabel lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDestino.setForeground(Color.WHITE);
		lblDestino.setBounds(21, 91, 89, 16);
		lblDestino.setFont(new Font("Verdana", Font.BOLD, 12));
		getContentPane().add(lblDestino);

		JLabel lblImagem = new JLabel("");
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.setIcon(new ImageIcon(
				JoeEmailSender.class.getResource("/com/sun/javafx/scene/control/skin/caspian/images/vk-hide.png")));
		lblImagem.setBounds(473, 57, 46, 32);
		getContentPane().add(lblImagem);

		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAssunto.setForeground(Color.WHITE);
		lblAssunto.setFont(new Font("Verdana", Font.BOLD, 12));
		lblAssunto.setBounds(21, 122, 89, 16);
		getContentPane().add(lblAssunto);

		JScrollPane scrollText = new JScrollPane();
		scrollText.setBounds(41, 151, 480, 220);
		getContentPane().add(scrollText);

		textCorpoDoEmail = new JTextArea();
		textCorpoDoEmail.setText("CORPO DO EMAIL");
		textCorpoDoEmail.setSelectionColor(Color.GREEN);
		textCorpoDoEmail.setSelectedTextColor(Color.BLACK);
		textCorpoDoEmail.setDisabledTextColor(Color.LIGHT_GRAY);
		textCorpoDoEmail.setCaretColor(Color.BLUE);
		textCorpoDoEmail.setFont(new Font("Verdana", Font.PLAIN, 14));
		textCorpoDoEmail.setForeground(Color.WHITE);
		textCorpoDoEmail.setBackground(Color.DARK_GRAY);
		scrollText.setViewportView(textCorpoDoEmail);

		// Send Button - Bot�o Enviar
		btnEnviar = new JButton("ENVIAR");
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setFont(new Font("Verdana", Font.BOLD, 15));
		btnEnviar.setBackground(Color.DARK_GRAY);
		btnEnviar.setBounds(206, 395, 119, 41);
		getContentPane().add(btnEnviar);
		
		JLabel lblRemetente = new JLabel("Remetente:");
		lblRemetente.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRemetente.setForeground(Color.WHITE);
		lblRemetente.setFont(new Font("Verdana", Font.BOLD, 12));
		lblRemetente.setBounds(21, 12, 89, 16);
		getContentPane().add(lblRemetente);
		
		txtRemetente = new JTextField();
		txtRemetente.setForeground(Color.WHITE);
		txtRemetente.setBackground(Color.BLACK);
		txtRemetente.setText("aluno@ailton.eng.br");
		txtRemetente.setColumns(10);
		txtRemetente.setBounds(120, 12, 344, 20);
		getContentPane().add(txtRemetente);
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Verdana", Font.BOLD, 12));
		lblSenha.setBounds(21, 39, 89, 16);
		getContentPane().add(lblSenha);
		
		passField = new JPasswordField();
		passField.setForeground(Color.WHITE);
		passField.setBackground(Color.BLACK);
		passField.setText("");
		passField.setColumns(10);
		passField.setBounds(120, 39, 344, 20);
		getContentPane().add(passField);
	}

	private void createActions() {
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ProtocoloEmail protocolSMTP = new ProtocoloEmail();
					protocolSMTP.enviar(textCorpoDoEmail.getText(),txtAssuntoDoEmail.getText(), "aluno@ailton.eng.br",txtEmaildominiocom.getText(),"smtp.ailton.eng.br", String.valueOf(passField.getPassword()));
					JOptionPane.showMessageDialog(btnEnviar, protocolSMTP.log);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(btnEnviar, "Erro : " + e + "/n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
