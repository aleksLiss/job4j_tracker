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
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double amountOfSubjects = 0D;
            double amountOfScores = 0D;
            for (Subject subject : pupil.subjects()) {
                amountOfSubjects++;
                amountOfScores += subject.score();
            }
            labels.add(
                    new Label(pupil.name(), (amountOfScores / amountOfSubjects))
            );
        }
        return labels;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labels = new ArrayList<>();
        int amountOfPupils = pupils.size();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                String nameOfSubject = subject.name();
                int amount = map.getOrDefault(nameOfSubject, 0) + subject.score();
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
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double amountOfScores = 0D;
            for (Subject subject : pupil.subjects()) {
                amountOfScores += subject.score();
            }
            labels.add(
                    new Label(pupil.name(), amountOfScores)
            );
        }
        labels.sort(Comparator.naturalOrder());
        return labels.get(labels.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labels = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                String nameOfSubject = subject.name();
                int amount = map.getOrDefault(nameOfSubject, 0) + subject.score();
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