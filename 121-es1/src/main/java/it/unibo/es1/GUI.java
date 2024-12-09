package it.unibo.es1;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * A class that creates a GUI with some buttons.
 */
public class GUI extends JFrame {
	private static final int WIDTH = 500;
	private static final int HEIGHT = 100;
	private final List<JButton> jbs = new ArrayList<>();

	/**
	 * A constructor that creates a number
	 * of buttons that depends on how many
	 * are indacated by the parameter.
	 * @param size the number of buttons
	 */
	@SuppressWarnings("unused")
	public GUI(final int size) {
		final Logics logics = new LogicsImpl(size);
		this.setSize(WIDTH, HEIGHT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		ActionListener ac = e -> {
			final JButton buttonClicked = (JButton) e.getSource();
			final int buttonPosition = jbs.indexOf(buttonClicked);
			buttonClicked.setText(String.valueOf(logics.hit(buttonPosition)));
			buttonClicked.setEnabled(logics.enablings().get(buttonPosition));
			if (logics.toQuit()) {
				System.exit(0);
			}
		};
		logics.values().forEach(v -> {
			JButton jb = new JButton(String.valueOf(v));
			jbs.add(jb);
			jb.addActionListener(ac);
			this.getContentPane().add(jb);
		});
		final JButton ok = new JButton("Print");
		this.getContentPane().add(ok);
		ok.addActionListener(e -> System.out.println(logics.result()));
		this.setVisible(true);
	}

}
