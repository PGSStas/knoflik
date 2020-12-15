package com.knoflik.services;

import com.knoflik.questions.Pack;
import com.knoflik.questions.Question;
import com.knoflik.questions.Theme;
import com.knoflik.repositories.questions.PackRepository;
import com.knoflik.repositories.questions.QuestionRepository;
import com.knoflik.repositories.questions.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@Service
public class PackageParsingService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private PackRepository packRepository;

    public void parsePackage(final String path) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(path));

        Pack pack = new Pack();
        pack.setPackageName(scanner.nextLine());
        pack.setAuthorsName(scanner.nextLine());
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            Theme theme = new Theme();
            theme.setThemeName(scanner.nextLine());

            String temp = "";
            do {
                Question question = new Question();

                StringBuilder stringBuilder = new StringBuilder();
                while (!temp.startsWith("Ответ:")) {
                    stringBuilder.append(temp).append("\n");
                    temp = scanner.nextLine();
                }
                question.setQuestion(stringBuilder.toString());

                stringBuilder = new StringBuilder();
                while (!temp.isEmpty() && (temp.charAt(1) != '0'
                        || temp.charAt(2) != '.')) {
                    stringBuilder.append(temp).append("\n");
                    if (scanner.hasNextLine()) {
                        temp = scanner.nextLine();
                    } else {
                        temp = "";
                    }
                }
                question.setAnswer(stringBuilder.toString());

                questionRepository.save(question);
                theme.addQuestion(question);
            } while (!temp.isEmpty());
            themeRepository.save(theme);
            pack.addTheme(theme);
        }
        packRepository.save(pack);
    }

    public long getPackageCount() {
        return packRepository.count();
    }
}
