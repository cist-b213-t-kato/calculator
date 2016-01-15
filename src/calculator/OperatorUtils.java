/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import java.util.HashMap;

public class OperatorUtils {

    //演算子の優先順の比較 o1がo2より優先順が高い場合1以上が返る
    protected static int comp(String o1, String o2){
        HashMap<String, Integer> map = new HashMap<>();

        map.put("+", 0);
        map.put("-", 0);
        map.put("(", 0);
        map.put(")", 0);
        map.put("*", 1);
        map.put("/", 1);

        return (map.get(o1)-map.get(o2));
    }
}
