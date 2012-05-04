package reconoceletras;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import javax.swing.*;




//import handwriting.learning.*;

@SuppressWarnings("serial")
public class DrawingEditor extends JFrame {
	private MousePencil c;
	private DrawingPanel view;
	//private SampleData data;
//	private RecognizerAI ai;
	
        /*
	private JMenuItem open, save, clear, recordDrawing, drawErase;
	private JFileChooser chooser;
	private JComboBox labeler, indexer;
	private JButton swapImage, createNet, applyNet;
	private JTextField netLabel;
	*/
        
	//private Drawing d() {return view.getDrawing();}
	
	private Drawing makeNewDrawing() {return new Drawing(20, 20);}
	
	public DrawingEditor() {
		setSize(550, 550);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		
		view = new DrawingPanel(makeNewDrawing());
		c = new MousePencil(view);
		getContentPane().add(view, BorderLayout.CENTER);
		/*
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		
		addFileMenu(bar);
		addViewMenu(bar);
		addNetworkControls();
		addViewByLabel();*/
	}
	/*
	private void addFileMenu(JMenuBar bar) {
		JMenu fileMenu = new JMenu("File");
		bar.add(fileMenu);
		
		open = new JMenuItem("Open");
		open.addActionListener(new Opener());
		fileMenu.add(open);
		
		save = new JMenuItem("Save");
		save.addActionListener(new Saver());
		fileMenu.add(save);
		
		chooser = new JFileChooser();
	}
	
	private void addViewMenu(JMenuBar bar) {
		JMenu viewMenu = new JMenu("View");
		bar.add(viewMenu);
		
		clear = new JMenuItem("Clear");
		clear.addActionListener(new Clearer());
		viewMenu.add(clear);
		
		recordDrawing = new JMenuItem("Record drawing");
		recordDrawing.addActionListener(new Recorder());
		viewMenu.add(recordDrawing);
		
		drawErase = new JMenuItem("Erase");
		drawErase.addActionListener(new DrawEraser());
		viewMenu.add(drawErase);
	}*/
	/*
	private void addNetworkControls() {
		JPanel netPanel = new JPanel();
		createNet = new JButton("Create neural net");
		createNet.addActionListener(new NetCreator());
		netPanel.add(createNet);
		
		applyNet = new JButton("Classify drawing");
		applyNet.addActionListener(new NetApplier());
		netPanel.add(applyNet);
		
		netPanel.add(new JLabel("Drawing label:"));
		netLabel = new JTextField(10);
		netLabel.setEditable(false);
		netPanel.add(netLabel);
		
		getContentPane().add(netPanel, BorderLayout.SOUTH);
	}
	*/
        /*
	private void addViewByLabel() {
		data = new SampleData();
		
		JPanel dataPanel = new JPanel();
		labeler = new JComboBox();
		labeler.addActionListener(new Labeler());
		dataPanel.add(labeler);
		
		indexer = new JComboBox();
		dataPanel.add(indexer);
		
		swapImage = new JButton("Look up image");
		swapImage.addActionListener(new Swapper());
		dataPanel.add(swapImage);
		
		getContentPane().add(dataPanel, BorderLayout.NORTH);
	}
	*/
	public static void main(String[] args) {
		DrawingEditor gui = new DrawingEditor();
		gui.setVisible(true);
	}
	/*
	private class Opener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int choice = chooser.showOpenDialog(null);
			if (choice == JFileChooser.APPROVE_OPTION) {
				try {
					data = SampleData.parseDataFrom(new Scanner(chooser.getSelectedFile()));
					loadLabels();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	*/
        
        /*
	private void loadLabels() {
		labeler.removeAllItems();
		for (String label: data.allLabels()) {
			labeler.addItem(label);
		}
	}
	*/
        /*
	private class Saver implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int choice = chooser.showSaveDialog(null);
			if (choice == JFileChooser.APPROVE_OPTION) {
				try {
					PrintStream ps = new PrintStream(chooser.getSelectedFile());
					ps.println(data.toString());
					ps.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
         */
        
	/*
	private class Labeler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (hasCurrentLabel()) {
				String label = getCurrentLabel();
				if (data.hasLabel(label)) {
					indexer.removeAllItems();
					for (int i = 0; i < data.numDrawingsFor(label); ++i) {
						indexer.addItem(i);
					}
					changeIndexedDrawing();
				} else {
					data.addLabel(label);
				}
			}
		}
	}
	
	private boolean hasCurrentLabel() {
		return labeler.getItemCount() > 0;
	}
	
	private String getCurrentLabel() {
		return labeler.getSelectedItem().toString();
	}

	private void changeIndexedDrawing() {
		if (indexer.getItemCount() > 0) {
			String label = getCurrentLabel();
			int index = Integer.parseInt(indexer.getSelectedItem().toString());
			if (data.numDrawingsFor(label) > index) {
				view.resetDrawing(data.getDrawing(label, index));
			}
		}
	}
	*/
        /*
	private class Recorder implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String label = JOptionPane.showInputDialog("Enter drawing label");
			if (label != null) {
				indexer.addItem(data.numDrawingsFor(label));
				data.addDrawing(label, view.getDrawing());
				loadLabels();
			}
		}
	}
	*/
        
        /*
	private class Clearer implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			view.resetDrawing(makeNewDrawing());
		}
	}
	
	private class Swapper implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeIndexedDrawing();
		}
	}
	
	private class DrawEraser implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (c.isDrawing()) {
				drawErase.setText("Draw");
				c.erase();
			} else {
				drawErase.setText("Erase");
				c.draw();
			}
		}
	}
	*/
        /*
	private class NetCreator implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
            /*
		public void actionPerformed(ActionEvent e) {
			int choice = chooser.showOpenDialog(null);
			if (choice == JFileChooser.APPROVE_OPTION) {
				try {
					SampleData training = SampleData.parseDataFrom(new Scanner(chooser.getSelectedFile()));
					int numHidden = Integer.parseInt(JOptionPane.showInputDialog("Enter number of hidden nodes"));
					double learningRate = Double.parseDouble(JOptionPane.showInputDialog("Enter learning rate"));
					int max = Integer.parseInt(JOptionPane.showInputDialog("Enter maximum training iterations"));
					
					ai = new RecognizerAI(training, numHidden);//this line needs to be changed to use the child of recognizer
					int steps = ai.trainUntil(learningRate, max);
					if (steps < max) {
						JOptionPane.showMessageDialog(null, "Network converged in " + steps + " steps");
					} else {
						JOptionPane.showMessageDialog(null, "Network did not converge");
					}
					
					System.out.println("Number of correct tests: " + ai.numCorrectTests(data) + " / " + data.numDrawings());
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (NumberFormatException e2) {
					// Do nothing; just let the operation fail.
				}
			}
		}
                */
                /*
	}
	*/
/*
	private class NetApplier implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
            /*
		public void actionPerformed(ActionEvent e) {
			if (ai != null) {
				netLabel.setText(ai.classify(d()));
			}
		}*/
	/*}*/
}
