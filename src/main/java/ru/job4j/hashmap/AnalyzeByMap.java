package ru.job4j.hashmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double amountOfAllScore = 0D;
        double amountOfAllSubjects = 0D;
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                amountOfAllScore += subject.score();
                amountOfAllSubjects++;
            }
        }
        return amountOfAllScore / amountOfAllSubjects;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        double amountOfSubjects = 0D;
        double amountOfScores = 0D;
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                amountOfSubjects++;
                amountOfScores += subject.score();
            }
            labels.add(
                    new Label(pupil.name(), (amountOfScores / amountOfSubjects))
            );
            amountOfSubjects = 0D;
            amountOfScores = 0D;
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labels = new ArrayList<>();
        int amountOfPupils = pupils.size();
        int allScoresOfMath = 0;
        int allScoresOfPhilosophy = 0;
        int allScoresOfLang = 0;
        map.put("Math", allScoresOfMath);
        map.put("Lang", allScoresOfLang);
        map.put("Philosophy", allScoresOfPhilosophy);
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                String nameOfSubject = subject.name();
                int amount = map.get(nameOfSubject) + subject.score();
                map.put(nameOfSubject, amount);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            labels.add(
                    new Label(entry.getKey(), (double) entry.getValue() / amountOfPupils)
            );
        }
        return labels;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        double amountOfScores = 0D;
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                amountOfScores += subject.score();
            }
            labels.add(
                    new Label(pupil.name(), amountOfScores)
            );
            amountOfScores = 0D;
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labels = new ArrayList<>();
        int allScoresOfMath = 0;
        int allScoresOfPhilosophy = 0;
        int allScoresOfLang = 0;
        map.put("Math", allScoresOfMath);
        map.put("Lang", allScoresOfLang);
        map.put("Philosophy", allScoresOfPhilosophy);
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                String nameOfSubject = subject.name();
                int amount = map.get(nameOfSubject) + subject.score();
                map.put(nameOfSubject, amount);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            labels.add(
                    new Label(entry.getKey(), entry.getValue())
            );
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }
}