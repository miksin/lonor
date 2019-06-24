package jp.co.calace.lonor.models;

import java.util.Random;

public class ConversationSet {

    private String[] identifiers;
    private String[] replies;
    private static Random random = new Random();

    public ConversationSet(String[] identifiers, String[] replies) {
        this.identifiers = identifiers;
        this.replies = replies;
    }

    public boolean match(String msg) {
        for (int i = 0; i < identifiers.length; i++) {
            if (msg.toLowerCase().contains(identifiers[i])) {
                return true;
            }
        }
        return false;
    }

    public String reply() {
        return replies[random.nextInt(replies.length)];
    }
}
