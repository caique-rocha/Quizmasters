package myusarisoy.quizapp;

import java.io.Serializable;

public class GetScore implements Serializable {
    public int myScore;
    public String name;

    public GetScore() {

    }

    public GetScore(int myScore, String name) {
        this.myScore = myScore;
        this.name = name;
    }

    public int getMyScore() {
        return myScore;
    }

    public void setMyScore(int myScore) {
        this.myScore = myScore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // e.g., Yuşa scored 100 points.
    public String toString() {
        return name + " scored " + myScore + " points.";
    }

}
