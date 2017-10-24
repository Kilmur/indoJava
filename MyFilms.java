import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class MyFilms {
	
	JFrame frame;
	JPanel panel;
	JTextField nameTF;
	JTextField yearTF;
	JTextField searchTF;
	JTextArea area;

	public static void main(String[] args) {
		new MyFilms().go();
	}

    public void go(){
		
		frame = new JFrame();
		frame.setTitle("My films");
		frame.setSize(600, 250);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		nameTF = new JTextField(35);
		yearTF = new JTextField(4);
		searchTF = new JTextField(30);
		
		area = new JTextArea(4, 30);
		JScrollPane scr = new JScrollPane(area);
		area.setLineWrap(true);
		scr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scr.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		area.setText("Информациия для пользователя");
		Font fontArea = new Font("arial", Font.BOLD, 16);
		area.setFont(fontArea);
		
		JLabel labelName = new JLabel("Название фильма");
		JLabel labelYear = new JLabel("Год");
		JLabel labelSearch = new JLabel("Поиск");
		
		JButton butAdd = new JButton("Добавить фильм");
		JButton butSearch = new JButton("Найти фильм");
		JButton butCountFilms = new JButton("Кол-во фильмов");
		
		panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		JPanel panel5 = new JPanel();
				
		frame.getContentPane().add(panel);
		
		panel.add(scr);
		
		panel1.add(labelName);
		panel1.add(nameTF);
		panel1.setBackground(Color.black);
		panel.add(panel1);
		
		panel2.add(labelYear);
		panel2.add(yearTF);
		panel2.setBackground(Color.black);
		panel.add(panel2);
		
		panel3.add(butAdd);
		panel3.setBackground(Color.black);
		panel.add(panel3);
		
		panel4.add(labelSearch);
		panel4.add(searchTF);
		panel4.add(butSearch);
		panel4.setBackground(Color.black);
		panel.add(panel4);
		
		panel5.add(butCountFilms);
		panel5.setBackground(Color.black);
		panel.add(panel5);
		
		frame.setVisible(true);
		
		butAdd.addActionListener(new ButAdd());
		butSearch.addActionListener(new ButSearch());
		butCountFilms.addActionListener(new ButCountFilms());
	}
	
	class ButAdd implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String name = nameTF.getText();
			String year = yearTF.getText();
			File file = new File("films.txt");
			FileWriter wr = null;
			try{
				wr = new FileWriter(file, true);
				wr.append(name + " - " + year + "/");
				wr.close();
				area.setText("Фильм \"" + name + "\" " + year + " года, добавлен");
				nameTF.setText("");
				yearTF.setText("");
				nameTF.requestFocus();
			}
			catch (IOException ex){
				ex.printStackTrace();
			}
		}
	}
	//            КНОПКА ПОИСКА
	class ButSearch implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String poisk = searchTF.getText();
			try{
				File file = new File("films.txt");
				FileReader fl = new FileReader(file);
				BufferedReader bf = new BufferedReader(fl);
				String fileText = bf.readLine();
				String [] mas = fileText.split("/");
				area.setText("");
				for(String x: mas){
					if (x.contains(poisk)){
						area.append(x + "\n");
					}
				}
				if (area.getText().equals("")){
					area.setText("Фильм не найден");
				}
				searchTF.setText("");
				bf.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
//              КНОПКА ПОДСЧЕТА КОЛ-ВА ФИЛЬМОВ
	class ButCountFilms implements ActionListener{
		public void actionPerformed(ActionEvent e){
			try{
				File file = new File("films.txt");
				FileReader fl = new FileReader(file);
				BufferedReader bf = new BufferedReader(fl);
				String fileText = bf.readLine();
				String [] mas = fileText.split("/");
				area.setText("Фильмов в списке - " + mas.length);
				bf.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}	
	
	
	
	
}//конец класса
