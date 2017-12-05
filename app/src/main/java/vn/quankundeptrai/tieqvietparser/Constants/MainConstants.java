package vn.quankundeptrai.tieqvietparser.Constants;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by TQN on 12/5/2017.
 */

public class MainConstants {
    public static ArrayList<Pair<String,String>> generateDictionary(){
        ArrayList<Pair<String,String>> dict = new ArrayList<>();
        dict.add(new Pair<>("k(h|H)","x"));
        dict.add(new Pair<>("K(h|H)","X"));
        dict.add(new Pair<>("c(?!(h|H))|q","k"));
        dict.add(new Pair<>("C(?!(h|H))|Q","K"));
        dict.add(new Pair<>("t(r|R)|c(h|H)","c"));
        dict.add(new Pair<>("T(r|R)|C(h|H)","C"));
        dict.add(new Pair<>("d|g(i|I)|r","z"));
        dict.add(new Pair<>("D|G(i|I)|R","Z"));
        dict.add(new Pair<>("g(i|I|\\xEC|\\xED|\u1EC9|\u0129|\u1ECB|\\xCC|\\xCD|\u1EC8|\u0128|\u1ECA)","z$1"));
        dict.add(new Pair<>("G(i|I|\\xEC|\\xED|\u1EC9|\u0129|\u1ECB|\\xCC|\\xCD|\u1EC8|\u0128|\u1ECA)","Z$1"));
        dict.add(new Pair<>("\u0111","d"));
        dict.add(new Pair<>("\u0110","D"));
        dict.add(new Pair<>("p(h|H)","f"));
        dict.add(new Pair<>("P(h|H)","F"));
        dict.add(new Pair<>("n(g|G)(h|H)?","q"));
        dict.add(new Pair<>("N(g|G)(h|H)?","Q"));
        dict.add(new Pair<>("(g|G)(h|H)","$1"));
        dict.add(new Pair<>("t(h|H)","w"));
        dict.add(new Pair<>("T(h|H)","W"));
        dict.add(new Pair<>("(n|N)(h|H)","$1\'"));
        
        return dict;
    }
}
