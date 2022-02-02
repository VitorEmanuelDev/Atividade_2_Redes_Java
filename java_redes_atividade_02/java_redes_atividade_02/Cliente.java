package java_redes_atividade_02;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Cliente extends JFrame implements ActionListener{

	private static JTextField textFieldNome;
	private static JLabel labelNome;
	private static JTextField textFieldIdade;
	private static JLabel labelIdade;
	private static JTextField textFieldResposta;
	private static JLabel labelResposta;
	private static JFrame frame;
	private static JButton button;

	private static Socket socket;

	private static ObjectOutputStream saida;

	public Cliente() {

	}

	public static void main(String[] args) {

		frame = new JFrame("Janela de input");
		labelNome = new JLabel("Nome");
		labelIdade =	new JLabel("Idade");
		labelResposta =	new JLabel("Retorno do servidor");
		button = new JButton("Enviar");

		Cliente cliente = new Cliente();

		button.addActionListener(cliente);

		textFieldNome = new JTextField(20);
		textFieldIdade = new JTextField(20);
		textFieldResposta = new JTextField(25);

		JPanel panel = new JPanel();

		panel.add(labelNome);
		panel.add(textFieldNome);
		panel.add(labelIdade);
		panel.add(textFieldIdade);
		panel.add(labelResposta);
		panel.add(textFieldResposta);
		panel.add(button);

		frame.add(panel);

		frame.setSize(300, 300);

		frame.show();

		try {

			socket = new Socket("127.0.0.1", 50000);

			saida = new ObjectOutputStream(socket.getOutputStream());

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();

		if (s.equals("Enviar")) {

			Pessoa pessoa = new Pessoa();
			String nome = textFieldNome.getText();
			pessoa.setNome(nome);

			String idadeStr = textFieldIdade.getText();
			int idade = Integer.parseInt(idadeStr);
			pessoa.setIdade(idade);

			textFieldNome.setText(textFieldNome.getText());
			textFieldIdade.setText(textFieldIdade.getText());

			try {
				saida.writeObject(pessoa);
				textFieldResposta.setText("Dados recebidos corretamente!");
				socket.close();
			} catch (IOException e1) {		
				textFieldResposta.setText("Dados não recebidos!");
				e1.printStackTrace();
			}
		}
	}
}
