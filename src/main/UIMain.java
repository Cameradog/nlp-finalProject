package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import common.Constant;

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
	boolean hasSelectedExtractData = false;
	boolean hasSelectedPreprocess = false;
	boolean hasSelectedClassifier = false;

	// preprocessing
	JLabel preprocessingMes;
	JRadioButton removePun;
	JRadioButton removeStopWord;
	JRadioButton removeMultiple;
	JRadioButton negataion;
	JRadioButton stem;
	JRadioButton stopwords;

	// classifier
	JLabel classifierName;
	JLabel classifierChoose;
	JLabel navieBayes;
	JLabel me;
	JCheckBox unigram;
	JCheckBox bigram;
	JCheckBox uniPlbigram;
	JCheckBox uniPlPos;
	JCheckBox no;

	public static void main(String[] args) {
		new UIMain();

	}

	public void openFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			Constant.FilePath = selectedFile.getAbsolutePath();
			System.out.println(selectedFile.getAbsolutePath());
		}
	}

	public UIMain() {
		openFileChooser();
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
		extractDataLabel.setBackground(Color.white);
		preProcessing.setBackground(Color.white);
		classifier.setBackground(Color.white);
		result.setBackground(Color.white);
		// set border
		extractDataLabel.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 3,
				Color.black));
		preProcessing.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 3,
				Color.black));
		classifier.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 3,
				Color.black));
		result.setBorder(BorderFactory.createMatteBorder(3, 5, 3, 3,
				Color.black));
		sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 3,
				Color.black));

		if (hasSelectedExtractData) {
			extractDataLabel.setBorder(BorderFactory.createMatteBorder(3, 20,
					3, 3, Color.black));
		}

		if (hasSelectedPreprocess) {
			preProcessing.setBorder(BorderFactory.createMatteBorder(3, 20, 3,
					3, Color.black));

		}

		if (hasSelectedClassifier) {
			classifier.setBorder(BorderFactory.createMatteBorder(3, 20, 3, 3,
					Color.black));
		}

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
		sideBarLayout.putConstraint(SpringLayout.WEST, extractDataLabel, 15,
				SpringLayout.WEST, sidebar);
		sideBarLayout.putConstraint(SpringLayout.NORTH, preProcessing, 15,
				SpringLayout.SOUTH, extractDataLabel);
		sideBarLayout.putConstraint(SpringLayout.WEST, preProcessing, 15,
				SpringLayout.WEST, sidebar);
		sideBarLayout.putConstraint(SpringLayout.NORTH, classifier, 15,
				SpringLayout.SOUTH, preProcessing);
		sideBarLayout.putConstraint(SpringLayout.WEST, classifier, 15,
				SpringLayout.WEST, sidebar);
		sideBarLayout.putConstraint(SpringLayout.NORTH, result, 15,
				SpringLayout.SOUTH, classifier);
		sideBarLayout.putConstraint(SpringLayout.WEST, result, 15,
				SpringLayout.WEST, sidebar);
	}

	public void setSideBarMouseListener() {
		extractDataLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (!hasSelectedExtractData) {
					sidebar.removeAll();
					sidebar.repaint();
					extractPage();
					initSideBar();
				}
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
				if (!hasSelectedPreprocess) {
					sidebar.removeAll();
					sidebar.repaint();
					preprocssingPage();
					initSideBar();
				}
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
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if (!hasSelectedClassifier) {
					sidebar.removeAll();
					sidebar.repaint();
					classifier();
					initSideBar();
				}

			}

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
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				System.out
						.println(Constant.rawDataProcess + "  RawDataProcess");
				System.out.println(Constant.removePunAndNum + "  removePu ");
				System.out.println(Constant.removeUnMeaning
						+ "  removeUnMeaning");
				System.out.println(Constant.stem + "  stem");
				System.out.println(Constant.negation + "  negation");
				System.out.println(Constant.classifier + "  classifier");
				System.out.println(Constant.classifierFeature
						+ "  classifierFeature");
				System.out.println(Constant.hasStopword +" stopword");
				if (!hasSelectedExtractData || !hasSelectedPreprocess
						|| !hasSelectedClassifier) {
					JOptionPane.showMessageDialog(getContentPane(),
							"請選完選項");
				} else{
					new Main().start();
				}
			}

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
				Constant.rawDataProcess = "default";
				hasSelectedExtractData = true;
				extractDataLabel.setBorder(BorderFactory.createMatteBorder(3,
						20, 3, 3, Color.black));
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
				Constant.rawDataProcess = "lexicon";
				hasSelectedExtractData = true;
				extractDataLabel.setBorder(BorderFactory.createMatteBorder(3,
						20, 3, 3, Color.black));
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
				Constant.rawDataProcess = "improve";
				hasSelectedExtractData = true;
				extractDataLabel.setBorder(BorderFactory.createMatteBorder(3,
						20, 3, 3, Color.black));
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

	public void setClassifierActionListener() {
		navieBayes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				classifierChoose.setText("你選擇了:navieBayes");
				Constant.classifier = "navie";
				hasSelectedClassifier = true;
				classifier.setBorder(BorderFactory.createMatteBorder(3, 20, 3,
						3, Color.black));
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				navieBayes.setForeground(Color.white);
				navieBayes.setBackground(Color.black);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				navieBayes.setForeground(Color.black);
				navieBayes.setBackground(Color.white);
			}
		});
		me.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				classifierChoose.setText("你選擇了:ME");
				Constant.classifier = "me";
				hasSelectedClassifier = true;
				classifier.setBorder(BorderFactory.createMatteBorder(3, 20, 3,
						3, Color.black));
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				me.setForeground(Color.white);
				me.setBackground(Color.black);
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
				me.setForeground(Color.black);
				me.setBackground(Color.white);
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
		extract_default.setBackground(Color.white);
		extract_lexicon.setBackground(Color.white);
		extract_improve_default.setBackground(Color.white);
		setExtractOptionListener();

		extract_default.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
				Color.black));
		extract_lexicon.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5,
				Color.black));
		extract_improve_default.setBorder(BorderFactory.createMatteBorder(5, 5,
				5, 5, Color.black));

		sideBarLayout.putConstraint(SpringLayout.NORTH, message, 10,
				SpringLayout.NORTH, sidebar);
		sideBarLayout.putConstraint(SpringLayout.WEST, message, 250,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.NORTH, extractChoose, 15,
				SpringLayout.SOUTH, message);
		sideBarLayout.putConstraint(SpringLayout.WEST, extractChoose, 250,
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

	public void preprocssingPage() {
		preprocessingMes = new JLabel("前處理");
		removePun = new JRadioButton("移除標點符號和數字");
//		removeStopWord = new JRadioButton("移除stopwords");
		removeMultiple = new JRadioButton("移除出現多次無意義詞");
		negataion = new JRadioButton("否定的ngram改進");
		stem = new JRadioButton("stem");
		stopwords = new JRadioButton("移除stopwords");

		sidebar.add(preprocessingMes);
		sidebar.add(removePun);
		sidebar.add(removeMultiple);
		sidebar.add(negataion);
		sidebar.add(stem);
		sidebar.add(stopwords);
		preProcessingItemListener pi = new preProcessingItemListener();
		removePun.addItemListener(pi);
		//removeStopWord.addItemListener(pi);
		removeMultiple.addItemListener(pi);
		negataion.addItemListener(pi);
		stem.addItemListener(pi);
		stopwords.addItemListener(pi);
		preprocessingMes.setFont(new Font("Serif", Font.BOLD, 30));
		removePun.setFont(new Font("Serif", Font.PLAIN, 45));
		removeMultiple.setFont(new Font("Serif", Font.PLAIN, 45));
		//removeStopWord.setFont(new Font("Serif", Font.PLAIN, 45));
		negataion.setFont(new Font("Serif", Font.PLAIN, 45));
		stem.setFont(new Font("Serif", Font.PLAIN, 45));
		stopwords.setFont(new Font("Serif", Font.PLAIN, 45));

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
		
		sideBarLayout.putConstraint(SpringLayout.NORTH, stopwords, 70,
				SpringLayout.NORTH, stem);
		sideBarLayout.putConstraint(SpringLayout.WEST, stopwords, 230,
				SpringLayout.WEST, sidebar);
	}

	public void classifier() {
		classifierName = new JLabel("分類器");
		classifierChoose = new JLabel("你選擇了:");
		unigram = new JCheckBox("unigram");
		bigram = new JCheckBox("bigram");
		uniPlbigram = new JCheckBox("unigram+bigram");
		uniPlPos = new JCheckBox("unigram+pos");
		no = new JCheckBox("no");
		navieBayes = new JLabel("navie Bayes");
		me = new JLabel("maximum entropy");

		// ButtonGroup b = new ButtonGroup();
		ButtonGroup group = new ButtonGroup();
		sidebar.add(classifierName);
		sidebar.add(unigram);
		sidebar.add(bigram);
		sidebar.add(uniPlbigram);
		sidebar.add(uniPlPos);
		sidebar.add(no);
		sidebar.add(classifierChoose);
		sidebar.add(navieBayes);
		sidebar.add(me);
		navieBayes.setBackground(Color.white);
		me.setBackground(Color.white);
		group.add(unigram);
		group.add(bigram);
		group.add(uniPlbigram);
		group.add(uniPlPos);
		group.add(no);
		navieBayes.setOpaque(true);
		me.setOpaque(true);
		classifierItemListener cl = new classifierItemListener();
		unigram.addItemListener(cl);
		bigram.addItemListener(cl);
		uniPlbigram.addItemListener(cl);
		uniPlPos.addItemListener(cl);
		no.addItemListener(cl);
		setClassifierActionListener();
		navieBayes.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3,
				Color.black));
		me.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
		classifierName.setFont(new Font("Serif", Font.BOLD, 30));
		classifierChoose.setFont(new Font("Serif", Font.BOLD, 20));
		unigram.setFont(new Font("Serif", Font.PLAIN, 45));
		bigram.setFont(new Font("Serif", Font.PLAIN, 45));
		uniPlbigram.setFont(new Font("Serif", Font.PLAIN, 45));
		uniPlPos.setFont(new Font("Serif", Font.PLAIN, 45));
		no.setFont(new Font("Serif", Font.PLAIN, 45));
		navieBayes.setFont(new Font("Serif", Font.PLAIN, 25));
		me.setFont(new Font("Serif", Font.PLAIN, 25));

		sideBarLayout.putConstraint(SpringLayout.NORTH, classifierName, 20,
				SpringLayout.NORTH, sidebar);
		sideBarLayout.putConstraint(SpringLayout.WEST, classifierName, 240,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.NORTH, classifierChoose, 50,
				SpringLayout.NORTH, classifierName);
		sideBarLayout.putConstraint(SpringLayout.WEST, classifierChoose, 240,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.NORTH, navieBayes, 50,
				SpringLayout.NORTH, classifierChoose);
		sideBarLayout.putConstraint(SpringLayout.WEST, navieBayes, 240,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.WEST, me, 50,
				SpringLayout.EAST, navieBayes);
		sideBarLayout.putConstraint(SpringLayout.NORTH, me, 50,
				SpringLayout.NORTH, classifierChoose);

		sideBarLayout.putConstraint(SpringLayout.NORTH, unigram, 40,
				SpringLayout.NORTH, me);
		sideBarLayout.putConstraint(SpringLayout.WEST, unigram, 240,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.NORTH, bigram, 50,
				SpringLayout.NORTH, unigram);
		sideBarLayout.putConstraint(SpringLayout.WEST, bigram, 240,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.NORTH, uniPlbigram, 50,
				SpringLayout.NORTH, bigram);
		sideBarLayout.putConstraint(SpringLayout.WEST, uniPlbigram, 240,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.NORTH, uniPlPos, 50,
				SpringLayout.NORTH, uniPlbigram);
		sideBarLayout.putConstraint(SpringLayout.WEST, uniPlPos, 240,
				SpringLayout.WEST, sidebar);

		sideBarLayout.putConstraint(SpringLayout.NORTH, no, 50,
				SpringLayout.NORTH, uniPlPos);
		sideBarLayout.putConstraint(SpringLayout.WEST, no, 240,
				SpringLayout.WEST, sidebar);
	}

	class preProcessingItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JRadioButton jb = (JRadioButton) (e.getSource());
			if (jb.isSelected()) {
				hasSelectedPreprocess = true;
				preProcessing.setBorder(BorderFactory.createMatteBorder(3, 20,
						3, 3, Color.black));

			}
			if (jb.getText().equals("移除標點符號和數字")) {
				Constant.removePunAndNum = jb.isSelected();
			} else if (jb.getText().equals("移除出現多次無意義詞")) {
				Constant.removeUnMeaning = jb.isSelected();
			} else if (jb.getText().equals("否定的ngram改進")) {
				Constant.negation = jb.isSelected();
			} else if (jb.getText().equals("stem")) {
				Constant.stem = jb.isSelected();
			} else if( jb.getText().equals("移除stopwords")){
				Constant.hasStopword = jb.isSelected();
			}
		}
	}

	class classifierItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			JCheckBox jb = (JCheckBox) (e.getSource());
			if (jb.getText().equals("unigram")) {
				Constant.classifierFeature = "uni";
			} else if (jb.getText().equals("bigram")) {
				Constant.classifierFeature = "bi";
			} else if (jb.getText().equals("unigram+bigram")) {
				Constant.classifierFeature = "unibi";
			} else if (jb.getText().equals("unigram+pos")) {
				Constant.classifierFeature = "unipo";
			} else if (jb.getText().equals("no")) {
				Constant.classifierFeature = "no";
			}
		}
	}
}