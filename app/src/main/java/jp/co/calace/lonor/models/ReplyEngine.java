package jp.co.calace.lonor.models;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReplyEngine {

    List<ConversationSet> conversationSets;

    public ReplyEngine() {
        conversationSets = new ArrayList<>();

        // default conversations
        conversationSets.add(new ConversationSet(
                new String[]{"hello", "hi", "こんにちは", "おはよう"},
                new String[]{"hello", "hi", "こんにちは", "おはよう"}));
        conversationSets.add(new ConversationSet(
                new String[]{"bye", "さよなら", "バイバイ", "じゃあね"},
                new String[]{"bye", "さよなら", "バイバイ", "じゃあね"}));
        conversationSets.add(new ConversationSet(
                new String[]{"ありがとう", "thank"},
                new String[]{"こちらこそ", "ありがとうございます"}));
    }

    public ReplyEngine(List<ConversationSet> conversationSets) {
        this.conversationSets = conversationSets;
    }

    public void addConversationSet(String[] identifiers, String[] replies) {
        conversationSets.add(new ConversationSet(identifiers, replies));
    }

    public List<String> reply(List<String> refMsgList, Character character) {
        List<String> replyList = new ArrayList<>();

        for (int i = 0; i < refMsgList.size(); i ++) {
            String refMsg = refMsgList.get(i);
            for (ConversationSet conversationSet : conversationSets) {
                if (conversationSet.match(refMsg)) {
                    replyList.add(conversationSet.reply());
                }
            }
        }

        return replyList;
    }
}
