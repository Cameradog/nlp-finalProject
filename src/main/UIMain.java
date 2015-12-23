package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

public class UIMain extends JFrame {
	// sidebar
	JLabel extractDataLabel;
	JLabel preProcessing;
	JLabel classifier;
	JLabel result;
	//
	SpringLayout sideBarLayout;
	SpringLayout mainLayout;
	Container contentPane;
	JSeparator sp;
	SpringLayout overAllLayout;
	//
	JPanel sidebar;
	JPanel main;

	// extractDataLabel;
	JLabel message;
	JLabel extractChoose;

	JLabel extract_default;
	JLabel extract_lexicon;
	JLabel extract_improve_default;
	
	//preprocessing
	JLabel preprocessingMes;
	JRadioButton removePun;
	JRadioButton removeStopWord;
	JRadioButton removeMultiple;
	JRadioButton negataion;
	JRadioButton stem;

	public static void main(String[] args) {
		new UIMain();
	}

	public UIMain() {
		this.setTitle("Seniment Analysis");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		this.add(mainPanel);
		sidebar = new JPanel();
		sidebar.setSize(1000, 500);

		sideBarLayout = new SpringLayout();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		sidebar.setLayout(sideBarLayout);
		initSideBar();
		mainPanel.add(sidebar);

		// overAllLayout.putConstraint(SpringLayout.NORTH, sidebar, 5,
		// SpringLayout.NORTH, contentPane);
		// overAllLayout.putConstraint(SpringLayout.WEST, sidebar, 50,
		// SpringLayout.WEST, main);

		this.setSize(1000, 500);
		this.setVisible(true);
	}

	public void initSideBar() {
		extractDataLabel = new JLabel("Extract Data");
		preProcessing = new JLabel("Preprocessing Data");
		classifier = new JLabel("Classifier");
		result = new JLabel("Result");

		// set opaque
		extractDataLabel.setOpaque(true);
		preProcessing.setOpaque(true);
		classifier.setOpaque(true);
		result.setOpaque(true);

		// setfont
		extractDataLabel.setFont(new Font("Serif", Font.PLAIN, 25));
		preProcessing.setFont(new Font("Serif", Font.PLAIN, 25));
		classifier.setFont(new Font("Serif", Font.PLAIN, 25));
		result.setFont(new Font("Serif", Font.PLAIN, 25));

		// set border
		extractDataLabel.setBorder(BorderFactory.createMatteBorder(3, 20, 3, 3,
				Color.black));
		preProcessing.setBorder(BorderFactory.createMatteBorder(3, 20, 3, 3,
				Color.black));
		classifier.setBorder(BorderFactory.createMatteBorder(3, 20, 3, 3,
				Color.black));
		result.setBorder(BorderFactory.createMatteBorder(3, 20, 3, 3,
				Color.black));
		sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3,
				Color.black));

		// set action listener
		setSideBarMouseListener();

		sidebar.add(extractDataLabel);
		sidebar.add(preProcessing);
		sidebar.add(classifier);
		sidebar.add(result);
		// message = new JLabel("處理Raw Data");
		// main.add(message);
		// message.setFont(new Font("Serif", Font.PLAIN, 50));
		// mainLayout.putConstraint(SpringLayout.NORTH, message, 30,
		// SpringLayout.NORTH, main);
		sideBarLayout.putConstraint(SpringLayout.NORTH, extractDataLabel, 15,
				SpringLayout.NORTH, sidebar);
		sideBarLayout.putConstraint(SpringLayout.NORTH, preProcessing, 15,
				SpringLayout.SOUTH, extractDataLabel);
		sideBarLayout.putConstraint(SpringLayout.NORTH, classifier, 15,
				SpringLayout.SOUTH, preProcessing);
		sideBarLayout.putConstraint(SpringLayout.NORTH, result, 15,
				SpringLayout.SOUTH, classifier);
	}

	public void setSideBarMouseListener() {
		extractDataLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				sidebar.removeAll();				
				sidebar.repaint();
				extractPage();
				initSideBar();

			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				extractDataLabel.setForeground(Color.white);
				extractDataLabel.setBackground(Color.black);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				extractDataLabel.setForeground(Color.black);
				extractDataLabel.setBackground(Color.white);
			}
		});
		preProcessing.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				sidebar.removeAll();				
				sidebar.repaint();
				preprocssingPage();
				initSideBar();

			}
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				preProcessing.setForeground(Color.white);
				preProcessing.setBackground(Color.black);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				preProcessing.setForeground(Color.black);
				preProcessing.setBackground(Color.white);
			}
		});
		classifier.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				classifier.setForeground(Color.white);
				classifier.setBackground(Color.black);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				classifier.setForeground(Color.black);
				classifier.setBackground(Color.white);
			}
		});
		result.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				result.setForeground(Color.white);
				result.setBackground(Color.black);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				result.setForeground(Color.black);
				result.setBackground(Color.white);
			}
		});
	}

	public void setExtractOptionListener() {

		extract_default.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// extractPage();
				//
				extractChoose.setText("你選擇的方法是:預設方法");
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				extract_default.setForeground(Color.white);
				extract_default.setBackground(Color.black);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				extract_default.setForeground(Color.black);
				extract_default.setBackground(Color.white);
			}
		});

		extract_lexicon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// extractPage();
				extractChoose.setText("你選擇的方法是:抓取語料庫");
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				extract_lexicon.setForeground(Color.white);
				extract_lexicon.setBackground(Color.black);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				extract_lexicon.setForeground(Color.black);
				extract_lexicon.setBackground(Color.white);
			}
		});

		extract_improve_default.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// extractPage();
				extractChoose.setText("你選擇的方法是:emoji改善");
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				extract_improve_default.setForeground(Color.white);
				extract_improve_default.setBackground(Color.black);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				extract_improve_default.setForeground(Color.black);
				extract_improve_default.setBackground(Color.white);
			}
		});
	}

	public void extractPage() {
		message = new JLabel("處理Raw Data");
		extractChoose = new JLabel("你選擇的方法是: ");

		extract_default = new JLabel("預設方法");
		extract_lexicon = new JLabel("抓取語料庫");
		extract_improve_default = new JLabel("emoji改善");

		sidebar.add(message);
		sidebar.add(extractChoose);
		sidebar.add(extract_default);
		sidebar.add(extract_lexicon);
		sidebar.add(extract_improve_default);
		// main.add(classifier);
		// main.add(result);
		message.setFont(new Font("Serif", Font.BOLD, 30));
		extractChoose.setFont(new Font("Serif", Font.PLAIN, 20));
		extract_default.setFont(new Font("Serif", Font.PLAIN, 45));
		extract_lexicon.setFont(new Font("Serif", Font.PLAIN, 45));
		extract_improve_default.setFont(new Font("Serif", Font.PLAIN, 45));

		extract_default.setOpaque(true);
		extract_lexicon.setOpaque(true);
		extract_improve_default.setOpaque(true);

		setExtractOptionListener();

		extract_default.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
				Color.black));
		extract_lexicon.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
				Color.black));
		extract_improve_default.setBorder(BorderFactory.createMatteBorder(5, 5,
				5, 5, Color.black));

		sideBarLayout.putConstraint(SpringLayout.NORTH, message, 10,
				SpringLayout.NORTH, sidebar);
		sideBarLayout.putConstraint(SpringLayout.WEST, message, 230,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.NORTH, extractChoose, 15,
				SpringLayout.SOUTH, message);
		sideBarLayout.putConstraint(SpringLayout.WEST, extractChoose, 230,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.WEST, extract_default, 500,
				SpringLayout.WEST, sidebar);
		sideBarLayout.putConstraint(SpringLayout.NORTH, extract_default, 65,
				SpringLayout.SOUTH, extractChoose);

		sideBarLayout.putConstraint(SpringLayout.WEST, extract_lexicon, 500,
				SpringLayout.WEST, sidebar);
		sideBarLayout.putConstraint(SpringLayout.NORTH, extract_lexicon, 65,
				SpringLayout.SOUTH, extract_default);

		sideBarLayout.putConstraint(SpringLayout.WEST, extract_improve_default,
				500, SpringLayout.WEST, sidebar);
		sideBarLayout.putConstraint(SpringLayout.NORTH,
				extract_improve_default, 65, SpringLayout.SOUTH,
				extract_lexicon);
	}
	
	public void preprocssingPage(){
		System.out.println("330");
		preprocessingMes = new JLabel("前處理");
		removePun =new JRadioButton("移除標點符號");
		removeStopWord =new JRadioButton("移除stopwords");
		removeMultiple =new JRadioButton("移除出現多次無意義詞");
		negataion =new JRadioButton("否定的ngram改進");
		stem =new JRadioButton("stem");
		
		sidebar.add(preprocessingMes);
		sidebar.add(removePun);
		sidebar.add(removeMultiple);
		sidebar.add(negataion);
		sidebar.add(stem);
		preprocessingMes.setFont(new Font("Serif", Font.BOLD, 30));
		removePun.setFont(new Font("Serif", Font.PLAIN, 45));
		removeMultiple.setFont(new Font("Serif", Font.PLAIN, 45));
		removeStopWord.setFont(new Font("Serif", Font.PLAIN, 45));
		negataion.setFont(new Font("Serif", Font.PLAIN, 45));
		stem.setFont(new Font("Serif", Font.PLAIN, 45));

		sideBarLayout.putConstraint(SpringLayout.NORTH, preprocessingMes, 20,
				SpringLayout.NORTH, sidebar);
		sideBarLayout.putConstraint(SpringLayout.WEST, preprocessingMes, 240,
				SpringLayout.WEST, sidebar);
		
		sideBarLayout.putConstraint(SpringLayout.NORTH, removePun, 70,
				SpringLayout.NORTH, preprocessingMes);
		sideBarLayout.putConstraint(SpringLayout.WEST, removePun, 230,
				SpringLayout.WEST, sidebar);
		
		sideBarLayout.putConstraint(SpringLayout.NORTH, removeMultiple, 70,
				SpringLayout.NORTH, removePun);
		sideBarLayout.putConstraint(SpringLayout.WEST, removeMultiple, 230,
				SpringLayout.WEST, sidebar);
		
		sideBarLayout.putConstraint(SpringLayout.NORTH, negataion, 70,
				SpringLayout.NORTH, removeMultiple);
		sideBarLayout.putConstraint(SpringLayout.WEST, negataion, 230,
				SpringLayout.WEST, sidebar);
		
		sideBarLayout.putConstraint(SpringLayout.NORTH, stem, 70,
				SpringLayout.NORTH, negataion);
		sideBarLayout.putConstraint(SpringLayout.WEST, stem, 230,
				SpringLayout.WEST, sidebar);
	}
}