import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;


public class Panel extends JPanel {

    public Panel() throws IOException {
        // ----- основные константы окна программы -----

        setLayout(null);
        final int height = 2;
        final int heightBtn = 24;
        final int widthBtn = 130;

        // ----- основной экран программы -----

        // поле ввода текста
        JTextArea areaText = new JTextArea();
        areaText.setBounds(5, height + heightBtn + 5, 405,
                GUI.height - height - heightBtn - 50);
        areaText.setLineWrap(true);
        areaText.setWrapStyleWord(true);
        add(areaText);
        // скролл-бар поля ввода текста
        JScrollPane areaTextScroll = new JScrollPane(areaText);
        areaTextScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        areaTextScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        areaTextScroll.setBounds(5, height + heightBtn + 5, 405,
                GUI.height - height - heightBtn - 50);
        add(areaTextScroll, BorderLayout.CENTER);

        // поле вывода слов
        JTextArea areaWords = new JTextArea("");
        areaWords.setBounds(425, height + heightBtn + 5, 2 * widthBtn + 30,
                GUI.height - height - heightBtn - 50);
        add(areaWords);
        // скролл-бар для поля вывода
        JScrollPane areaWordsScroll = new JScrollPane(areaWords);
        areaWordsScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        areaWordsScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        areaWordsScroll.setBounds(425, height + heightBtn + 5, 2 * widthBtn + 30,
                GUI.height - height - heightBtn - 50);
        add(areaWordsScroll);

        // ----- строка меню программы -----

        // кнопка вставить текст
        JButton btnPasteText = new JButton("Вставить текст");
        btnPasteText.setBounds(5, height, widthBtn, heightBtn);
        btnPasteText.addActionListener(e -> {
            //if (areaText.getText() != null) {
            try {
                areaText.paste();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        add(btnPasteText);


        // радио-кнопка использования словаря при разбивке текста
        JRadioButton radBtnDictionary = new JRadioButton("С учетом словаря");
        radBtnDictionary.setBounds(275, height, widthBtn, heightBtn);
        radBtnDictionary.setSelected(true);
        add(radBtnDictionary);

        // кнопка разбить текст
        JButton btnParsText = new JButton("Разбить текст");
        btnParsText.setBounds(140, height, widthBtn, heightBtn);

        btnParsText.addActionListener(e -> {
            try {
                SortedSet<String> trySet;
                trySet = ReadWrite.parsing(areaText.getText());
                areaWords.setText("");

                if (!radBtnDictionary.isSelected()) {
                    for (String line : trySet) {
                        areaWords.append(line + "\r\n");
                    }
                } else {
                    SortedSet<String> treeSetVocabulary = ReadWrite.readUsingScanner("vocabulary.txt");
                    SortedSet<String> exeptionSet = new TreeSet<>();
                    for (String s : trySet){
                        if (treeSetVocabulary.contains(s)) {
                            exeptionSet.add(s);}
                        else {
                            areaWords.append(s + "\r\n");
                        }
                    }

                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        add(btnParsText);



        // кнопка копирования слов в буфер обмена
        JButton btnCopyBuffer = new JButton("Копировать все");
        btnCopyBuffer.setBounds(425, height, widthBtn, heightBtn);
        btnCopyBuffer.addActionListener(e -> {
            try {
                //areaWords.copy();
                areaWords.selectAll();
                areaWords.copy();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        add(btnCopyBuffer);

        // кнопка добавления в словарь полученных слов
        JButton btnAddDictionary = new JButton("Добавить в словарь");
        btnAddDictionary.setBounds(560, height, widthBtn + 25, heightBtn);
        btnAddDictionary.addActionListener(e -> {
            try {
                ///чтение данных из поля и файла с их объединением
                SortedSet<String> trySet;
                SortedSet<String> trySetFile;
                trySet = ReadWrite.parsing(areaWords.getText());
                trySetFile = ReadWrite.readUsingScanner("vocabulary.txt");
                for (String s : trySet){
                    trySetFile.add(s);
                }
                /// запись коллекции в словарь
                ReadWrite.writeFile("vocabulary.txt", trySetFile);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        add(btnAddDictionary);
    }

}
